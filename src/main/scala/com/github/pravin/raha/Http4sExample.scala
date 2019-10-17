package com.github.pravin.raha

import caliban.GraphQL.graphQL
import caliban.schema.GenericSchema
import caliban.{GraphQL, Http4sAdapter, RootResolver}
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.CORS
import zio.clock.Clock
import zio.console.{Console, putStrLn}
import zio.interop.catz._
import zio.{RIO, ZIO}

object Http4sExample extends CatsApp with GenericSchema[Console with Clock] {

  type ExampleTask[A] = RIO[Console with Clock, A]

  case class Character(name: String, series: String)

  def getCharacters: List[Character] = List(
    Character("Naruto", "Naruto"),
    Character("Yato", "Noragami"),
    Character("Natsu", "Fairy tail")
  )

  def getCharacter(name: String): Option[Character] = getCharacters.find(c => c.name == name)

  // schema
  case class CharacterName(name: String)

  case class Queries(characters: List[Character],
                     character: CharacterName => Option[Character])

  // resolver
  val queries = Queries(getCharacters, args => getCharacter(args.name))

  val interpreter: GraphQL[Console with Clock, Queries, Unit, Unit] = graphQL(RootResolver(queries))


  override def run(args: List[String]): ZIO[Environment, Nothing, Int] =
    (for {
      _ <- BlazeServerBuilder[ExampleTask]
        .bindHttp(8088, "localhost")
        .withHttpApp(
          Router(
            "/api/graphql" -> CORS(Http4sAdapter.makeRestService(interpreter)),
          ).orNotFound
        )
        .resource
        .toManaged
        .useForever
    } yield 0).catchAll(err => putStrLn(err.toString).as(1))
}
