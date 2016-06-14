package org.apache.james.gatling.jmap.scenari

import io.gatling.core.Predef._
import org.apache.james.gatling.jmap.scenari.common.CommonSteps
import org.apache.james.gatling.jmap.{HttpSettings, JmapMailboxes}

import scala.concurrent.duration._

class JmapGetMailboxesScenario extends Simulation {
  val userCount = 200
  val loopVariableName: String = "any"

  val scn = scenario("JmapGetMailboxes")
    .exec(CommonSteps.authentication(userCount))
    .repeat(360, loopVariableName) {
      exec(JmapMailboxes.getSystemMailboxes)
        .pause(1 second , 2 seconds)
    }

  setUp(scn.inject(atOnceUsers(userCount))).protocols(HttpSettings.httpProtocol)
}
