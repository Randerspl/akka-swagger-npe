package akka.swagger.example

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}

import java.time.LocalDateTime

object Main {
    trait MainCommand

    case class DummyCommand( ts: LocalDateTime ) extends MainCommand

    def apply( ): Behavior[ MainCommand ] = Behaviors.setup { ctx =>

        //Http starting
        val httpApi = ctx.spawn(
            ServerActor(),
            name = "http-server"
        )

        ctx.watch( httpApi )

        Behaviors.receive[ MainCommand ] { ( _, msg ) =>
            msg match {
                case _: DummyCommand =>
                    Behaviors.same
            }
        }
    }

    def main( args: Array[ String ] ): Unit = {
        val system = ActorSystem( Main(), "example" )
        system.log.info( "Starting" )
    }

}
