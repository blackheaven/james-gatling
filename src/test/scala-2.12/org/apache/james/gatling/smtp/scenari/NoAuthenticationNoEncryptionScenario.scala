package org.apache.james.gatling.smtp.scenari

import io.gatling.core.Predef._
import org.apache.james.gatling.control._
import org.apache.james.gatling.jmap.scenari.common.Configuration._
import org.apache.james.gatling.smtp.PreDef._

import scala.concurrent.Await
import scala.concurrent.duration._

class NoAuthenticationNoEncryptionScenario extends Simulation {

  val users = new UserCreator(BaseJamesWebAdministrationUrl).createUsersWithInboxAndOutbox(UserCount)
  val queues = new QueueRequester(BaseJamesWebAdministrationUrl)

  val scn = scenario("SMTP_No_Authentication_No_Encryption")
    .feed(UserFeeder.createCompletedUserFeederWithInboxAndOutbox(users))
    .pause(1 second)
    .group("Queuing") {
      during(30 second) {
        exec(smtp("sendMail")
            .subject("subject")
            .body("This is the mail body being sent"))
          .pause(1 second)
      }
    }
    // .group("Dequeuing") {
    //   asLongAs(session => !Await.result(queues.areMailQueuesEmpty(), 1 second)) {
    //     pause(0.5 second)
    //   }
    // }

  setUp(scn.inject(nothingFor(10 seconds), rampUsers(UserCount) over(10 seconds))).protocols(smtp)
}
