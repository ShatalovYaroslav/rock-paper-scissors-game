package org.myproject.game.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myproject.game.model.PlayerMove;
import org.myproject.game.model.PlayerResult;
import org.myproject.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Implement methods for REST service
 */

@RestController
@RequestMapping(value = "/game/")
public class GameRest {

    private final Logger logger = LogManager.getRootLogger();

    @Autowired
    private GameService gameService;

    //-------------------play game with PC--------------------------------------------------------
    @ApiOperation(value = "Play the 'rock paper scissors' game with PC")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request, provided not correct value from the player")})
    @RequestMapping(value = "playWithPC/", method = RequestMethod.POST)
    public ResponseEntity<List<PlayerResult>> playWithPC(
            @ApiParam(value = "The user should provide requested model in json format to play: \n " +
                    " 'player_id' - the ID of your player; \n" +
                    " 'move' - the player's move choice: 'rock', 'paper' or 'scissors' (case insensitive) ")
            @RequestBody PlayerMove playerMove) {
        return new ResponseEntity<>(gameService.playWithPC(playerMove), HttpStatus.OK);
    }

    //-------------------play game between multiple Players--------------------------------------------------------
    @ApiOperation(value = "Play the 'rock paper scissors' game between multiple Players")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request, provided not correct values from the players")})
    @RequestMapping(value = "playMultiPlayers/", method = RequestMethod.POST)
    public ResponseEntity<List<PlayerResult>> playMultiPlayers(
            @ApiParam(value = "The players should provide requested model in json format for each player choice to play: \n " +
                    " 'player_id' - the ID of your player; \n" +
                    " 'move' - the player's move choice: 'rock', 'paper' or 'scissors' (case insensitive) ")
            @RequestBody List<PlayerMove> playerMoves) {
        return new ResponseEntity<>(gameService.playMultiPlayers(playerMoves), HttpStatus.OK);
    }
}
