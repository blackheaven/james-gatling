package org.apache.james.gatling

import org.apache.james.gatling.imap.scenari.ImapFullScenario
import scala.concurrent.duration._

class ImapFullIT extends ImapIT {

  before {
    users.foreach(server.sendMessage(Fixture.homer.username))
  }

  scenario(feederBuilder => {
    new ImapFullScenario().generate(10 seconds, feederBuilder)
  })
}
