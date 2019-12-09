# Rock, Paper, Scissors game
Famous game implemented in Java using Spring exposing RESTful API

## Purpose

The purpose of this service is to play Rock, Paper, Scissors game: http://en.wikipedia.org/wiki/Rock-paper-scissors

## Description of the game service
This game service is exposing REST API. The service architecture allows to play 'Player vs Computer' or 'Computer vs Computer' with a different game each time.<br>
The 2 players number is supported in the current implementation. The goal of the service is to do the arbitrage of the submitted moves and return the results. <br>
The default UI propose to play 'Player vs Computer' mode, where the Player should submit his choice and the Computer will do some choice according to implemented strategy. <br>
The 'Computer vs Computer' mode can be used if the participants (Computer clients) will send the request to the service API in the specified format. <br>
'Player vs Player' mode is also possible. The frontend client for game service should interact with service Rest API for multiple players. The client should submit the players choices. <br>
This service was designed in mind to ease an extensibility and modifications.<br>

## Default Web UI
The default frontend Web UI client is accessible on the url:
[http://localhost:8080/game/online/](http://localhost:8080/game/online/)
It is just enough to start the server with 'gradlew bootRun' command to enjoy the game. 

## Future extensions
- more then 2 participants in the game. It can be interesting to have more then 2 players for 1 game. Of course the decision algorithm will be updated.
- more moves in the game can be supported as extension.
- for more convenient play in multiple player mode, the submission of players choices can be asynchronous in several Rest calls. This implementation requires a development of session game mechanism. A player should be able to submit his move to specific session.
- the creation of game session should support an amount players, an amount of bots, specific timeout of session.
- the final decision of the result of current game can be taken only after several wins of ine of the player. For example: who will make first 3 wins. It requires persistence of previous players moves.

## Structure
The service is organized with a complete RESTful API. The service follows MVC packaging structure and covered by tests.
The game service is based on Spring framework and can be started with Spring Boot.
To try it out use Swagger or (http://localhost:8080/game).<br>
Frontend client can be implemented and started as separated service. The client should interact with service by Rest API.
For simplicity the default frontend client is developed in this project.

## Building and running the game service

You can start a game service as a standalone application:
```
$ gradlew clean build bootRun
```

You can build a WAR file as follows:

```
$ gradlew clean build war
```

Then, you can directly deploy the service with embedded Tomcat:

```
$ java -jar build/libs/game-X.Y.Z.war
```

The WAR file produced by Gradle can also be deployed in the embedded Jetty container.

Sometimes the gradle processes are not killing properly when you stop the running application. If you receive the message "the port is already in use" on starting microservice, then kill all suspending gradle processes for previous task. You can do it manually or use in IntelliJ IDEA Gradle killer plugin.

## Swagger

Available REST API can be listed and tested with Swagger. <br>
To access Swagger API:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Prepare your IDE for development
In the project is used Lombok library. It is needed to install plugin to be able to use it.
In the IntelliJ IDEA to be able to see Lombok generated code, you should enable the annotation preprocessor. [See documentation here.](https://www.jetbrains.com/help/idea/2016.1/configuring-annotation-processing.html) (Settings->Build->Compiler->Annotation Processors: Enable annotation preprocessing)

## Testing

In order to follow the best testing practises it is included testing part of Spring components with Mockito.<br>
For integration test there is provided code in SpringGameRestTest class and applied usual black-box testing practise. For testing REST methods is used RestAssured.<br>
