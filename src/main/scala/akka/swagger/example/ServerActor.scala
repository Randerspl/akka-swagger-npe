package akka.swagger.example

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Directives, Route}

import java.time.LocalDateTime

object ServerActor extends Directives {
    sealed trait ServerCommand

    case class DummyServerCommand( ts: LocalDateTime ) extends ServerCommand

    def apply( ): Behavior[ ServerCommand ] = Behaviors.setup { ctx =>

        implicit val system: ActorSystem[ Nothing ] = ctx.system

        //Create example routes
        val exampleRoute = ExampleRoute()

        //Create swagger generator + routes
        val swagger = new SwaggerRoute(
            domain = "localhost",
            httpSchemes = List( "http" )
        )

        //Concatenate routes
        val apiRoute: Route = exampleRoute.apiRoute ~ swagger.apiRoute

        //Create server isntance
        Http().newServerAt( "localhost", 9000 ).bind( apiRoute )
        ctx.log.info( s"HTTP server listening on localhost:9000" )

        //Dummy receive
        Behaviors.receive { ( _, msg ) =>
            msg match {
                case _: DummyServerCommand =>
                    Behaviors.same
            }
        }
    }
}
