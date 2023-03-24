ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = ( project in file( "." ) )
    .settings(
        name := "akka-swagger-npe-example"
    )

val AkkaVersion = "2.6.20"
val AkkaHttpVersion = "10.2.10"

val akkaActor = "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
val akkaHttp = "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
val akkaStream = "com.typesafe.akka" %% "akka-stream" % AkkaVersion
val logback = "ch.qos.logback" % "logback-classic" % "1.2.11"

val swaggerDependencies = Seq(
    "jakarta.ws.rs" % "jakarta.ws.rs-api" % "3.1.0",
    "com.github.swagger-akka-http" %% "swagger-akka-http" % "2.7.0",
    "com.github.swagger-akka-http" %% "swagger-scala-module" % "2.6.0",
    "io.swagger.core.v3" % "swagger-jaxrs2-jakarta" % "2.2.0"
)

libraryDependencies ++= Seq(
    akkaActor,
    akkaHttp,
    akkaStream,
    logback
)
libraryDependencies ++= swaggerDependencies