package akka.swagger.example

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import jakarta.ws.rs.{GET, Path}

@Path( "user" )
case class ExampleRoute( ) {

    //Concatenation of routes with path and http type definitions
    def apiRoute: Route = pathPrefix( "user" ) {
        Directives.concat(
            path( "1" ) {
                get( firstApi )
            },
            path( "2" ) {
                get( secondApi )
            }
        )
    }

    //Dummy route 1
    @GET
    @Path( "1" )
    private def firstApi: Route = {
        complete( "Ok" )
    }

    //Dummy route 2
    @GET
    @Path( "2" )
    private def secondApi: Route = {
        complete( "Ok" )
    }
}