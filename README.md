# API First - Spring Boot Demo

## How to start

Build:

        mvn clean verify
Run: 

        mvn spring-boot:run

Test with Swagger-UI:

        http://localhost:8080

## Details
Implemented Error Handling using Spring Boot's [ControllerAdvice](https://www.baeldung.com/exception-handling-for-rest-with-spring#controlleradvice)

## Notes
When using IntelliJ, you'll need to mark the `target/generated-sources/openapi/src/main/java` 
directory as `Generated Sources Root` to allow generated sources to be recognized and compiled. 