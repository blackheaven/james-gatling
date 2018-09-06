package org.apache.james.gatling.control

import java.net.URL

import io.gatling.core.Predef._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

class QueueRequester(val baseJamesWebAdministrationUrl: URL) {
  private val jamesWebAdministration = new JamesWebAdministration(baseJamesWebAdministrationUrl)

  def areMailQueuesEmpty(): Future[Boolean] =
    jamesWebAdministration.listMailQueues()
      .flatMap(names => Future.sequence(names.map(jamesWebAdministration.getMailQueue)))
      .map((queues) => queues.forall(queue => queue.isEmpty()))
}
