package org.apache.james.gatling.jmap.scenari

import io.gatling.core.Predef._
import io.gatling.core.feeder.FeederBuilder
import io.gatling.core.structure.ScenarioBuilder
import org.apache.james.gatling.jmap.JmapMessages.openpaasListMessageParameters
import org.apache.james.gatling.jmap._

class DefaultInboxSearchLoadingScenario {

  private object Keys {
    val inbox = "inboxID"
    val messageIds = "messageIds"
    val messagesDetailList = "messagesDetailList"
  }

  def generate(feederBuilder: FeederBuilder): ScenarioBuilder = {
    scenario("JmapDefaultSearchLoadingScenario")
      .feed(feederBuilder)
      .exec(CommonSteps.authentication())
      .exec(RetryAuthentication.execWithRetryAuthentication(JmapMailbox.getMailboxes, JmapMailbox.getMailboxesChecks ++ JmapMailbox.saveInboxAs(Keys.inbox)))
      .group(InboxHomeLoading.name)(
          exec(RetryAuthentication.execWithRetryAuthentication(JmapMessages.listMessages(openpaasListMessageParameters(Keys.inbox)), JmapMessages.listMessagesChecks)))
  }

}
