## Reproduction of NullPointerException in swagger-akka-http
This application is a simple akka-http server.  
It handles 3 routes.   
Two dummy routes (`localhost:9000/user/1` and `localhost:9000/user/2`) and generation of swagger.json (`localhost:9000/swagger`)

When you try to to use /swagger endpoint then NPE will be thrown.   
If you remove private annotation from one of endpoints (firstApi ro secondApi) or comment `unwantedDefinitions` in SwaggerRoute then it works normally.