package akka.swagger.example

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, ExceptionHandler, Route}
import com.github.swagger.akka.SwaggerGenerator
import org.slf4j.{Logger, LoggerFactory}

class SwaggerRoute( domain: String,
                    httpSchemes: List[ String ],
                  ) extends Directives with SwaggerGenerator {

    val log: Logger = LoggerFactory.getLogger( classOf[ SwaggerRoute ] )

    override val apiClasses: Set[ Class[ _ ] ] = Set(
        classOf[ ExampleRoute ]
    )

    override val host: String = domain

    override val schemes: List[ String ] = httpSchemes

    override val apiDocsPath = "swagger"

    //When commented swagger is generated properly
    override val unwantedDefinitions: Seq[ String ] = Seq( "Function1", "Function1RequestContextFutureRouteResult" )

    //For printing to logs error thrown by generator
    private def exceptionHandler: ExceptionHandler = ExceptionHandler {
        case e: NullPointerException =>
            log.error( "NullPointerException occurred", e )
            complete( StatusCodes.InternalServerError, "NullPointerException" )

        case e: Exception =>
            log.error( "Unexpected exception occurred", e )
            complete( StatusCodes.InternalServerError, "InternalServerError" )
    }

    def apiRoute: Route = path( "swagger" ) {
        handleExceptions( exceptionHandler ) {
            //Just always return swagger generation
            complete( generateSwaggerJson )
        }
    }

}