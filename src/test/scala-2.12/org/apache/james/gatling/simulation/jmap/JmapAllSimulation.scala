package org.apache.james.gatling.simulation.jmap

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import org.apache.james.gatling.control.{RecipientFeeder, UserCreator, UserFeeder}
import org.apache.james.gatling.jmap.scenari.JmapAllScenario
import org.apache.james.gatling.simulation.{Configuration, HttpSettings}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.{Await, Future}

class JmapAllSimulation extends Simulation {

  private val users = Await.result(
      awaitable = Future.sequence(
        new UserCreator(Configuration.BaseJamesWebAdministrationUrl).createUsersWithInboxAndOutbox(Configuration.UserCount)),
      atMost = Inf)

  private val scenario = new JmapAllScenario()

  setUp(scenario.generate(UserFeeder.toFeeder(users), Configuration.ScenarioDuration, RecipientFeeder.usersToFeeder(users))
      .inject(atOnceUsers(Configuration.UserCount)))
    .protocols(HttpSettings.httpProtocol)
}