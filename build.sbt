name := "james-gatling"
cancelable in Global := true

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.3"

enablePlugins(GatlingPlugin)

EclipseKeys.withSource := true

resolvers += "lightshed-maven" at "http://dl.bintray.com/content/lightshed/maven"
resolvers += "Fabricator" at "http://dl.bintray.com/biercoff/Fabricator"

libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.10"
libraryDependencies += "com.typesafe.play" %% "play-ahc-ws" % "2.6.18"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.3.1"
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.1"

libraryDependencies += "ch.lightshed" %% "courier" % "0.1.4"
libraryDependencies += "com.github.azakordonets" %% "fabricator" % "2.1.5"
