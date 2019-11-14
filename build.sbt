lazy val commonSettings =
  Seq(
    organization := "io.github.pravin-raha",
    name := "graphql-caliban-example",
    version := "0.1",
    scalaVersion := "2.13.1",
    dockerExposedPorts ++= Seq(8080)
  )

lazy val maantrack = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= http4s,
    libraryDependencies ++= caliban
  )
  .settings(
    addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt"),
    addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
  )

lazy val doobieVersion   = "0.8.6"
lazy val http4sVersion   = "0.21.0-M5"
lazy val flywayVersion   = "6.0.8"
lazy val calibanVersion  = "0.2.1"
lazy val catsVersion     = "2.0.0"
lazy val circeVersion    = "0.12.2"
lazy val circeDerivation = "0.12.0-M7"

lazy val caliban = Seq(
  "com.github.ghostdogpr" %% "caliban"        % calibanVersion,
  "com.github.ghostdogpr" %% "caliban-http4s" % calibanVersion,
  "com.github.ghostdogpr" %% "caliban-cats"   % calibanVersion
)

lazy val http4s = Seq(
  "org.typelevel"  %% "cats-effect"         % catsVersion,
  "org.http4s"     %% "http4s-dsl"          % http4sVersion,
  "org.http4s"     %% "http4s-circe"        % http4sVersion,
  "org.http4s"     %% "http4s-blaze-server" % http4sVersion,
  "io.circe"       %% "circe-parser"        % circeVersion,
  "io.circe"       %% "circe-derivation"    % circeDerivation,
  "ch.qos.logback" % "logback-classic"      % "1.2.3"
)

lazy val doobie = Seq(
  "org.tpolecat" %% "doobie-core"      % doobieVersion,
  "org.tpolecat" %% "doobie-scalatest" % doobieVersion,
  "org.tpolecat" %% "doobie-hikari"    % doobieVersion,
  "org.tpolecat" %% "doobie-postgres"  % doobieVersion,
  "org.tpolecat" %% "doobie-h2"        % doobieVersion,
  "org.tpolecat" %% "doobie-quill"     % doobieVersion,
  "org.flywaydb" % "flyway-core"       % flywayVersion
)
