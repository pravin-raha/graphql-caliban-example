name := "graphql-caliban-example"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "com.github.ghostdogpr" %% "caliban" % "0.0.6"
libraryDependencies += "com.github.ghostdogpr" %% "caliban-http4s" % "0.0.6"
libraryDependencies ++= http4s

lazy val http4s = Seq(
  "dev.zio" %% "zio-interop-cats" % "2.0.0.0-RC4",
  "org.typelevel" %% "cats-effect" % "2.0.0",
  "org.http4s" %% "http4s-dsl" % "0.21.0-M5",
  "org.http4s" %% "http4s-circe" % "0.21.0-M5",
  "org.http4s" %% "http4s-blaze-server" % "0.21.0-M5",
  "io.circe" %% "circe-parser" % "0.12.1",
  "io.circe" %% "circe-derivation" % "0.12.0-M7",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)