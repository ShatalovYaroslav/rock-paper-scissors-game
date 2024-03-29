package org.myproject.game.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.myproject.game.client.MoveSelectorClient;
import org.myproject.game.exception.ClientException;
import org.myproject.game.model.PlayerMove;
import org.myproject.game.model.PlayerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GameService {

    private static final String PC_PLAYER_ID = "PC_id";
    private static final int SUPPORTED_MULTI_PLAYERS_AMOUNT = 2;
    private final Logger logger = LogManager.getRootLogger();

    @Autowired
    private DecisionEngine decisionEngine;

    @Autowired
    @Qualifier("randomSelector")
    private MoveSelectorClient moveSelectorClient;

    /**
     * this method assume play between some player and PC - the value will be generated by PC
     *
     * @param playerMove the move chosen by the Player
     * @return result list of the game for each player
     */
    public List<PlayerResult> playWithPC(PlayerMove playerMove) {
        try {
            PlayerMove PCMove = new PlayerMove(PC_PLAYER_ID, moveSelectorClient.selectMove());
            logger.info("Created answer from PC: " + PCMove);

            logger.info("Received game move from player: " + playerMove);
            List<PlayerMove> playerMoves = new ArrayList<>();
            playerMoves.add(playerMove);
            playerMoves.add(PCMove);

            List<PlayerResult> results = decisionEngine.decide(playerMoves);
            logger.info("The results of the game: " + results);
            return results;
        } catch (Exception e) {
            logger.error(e);
            throw new ClientException("Exception during play against PC: ", e);
        }
    }

    /**
     * this method assume play between multiple players
     *
     * @param playerMoveList the moves chosen by the Players
     * @return result list of the game for each player
     */
    public List<PlayerResult> playMultiPlayers(List<PlayerMove> playerMoveList) {
        int playersNumber = playerMoveList.size();
        if (playersNumber == SUPPORTED_MULTI_PLAYERS_AMOUNT) {
            return decisionEngine.decide(playerMoveList);
        } else {
            String errorMsg = "The multiple Players mode supports now: " + SUPPORTED_MULTI_PLAYERS_AMOUNT + " players. The submitted players number: " + playersNumber;
            logger.error(errorMsg);
            throw new ClientException(errorMsg);
        }
    }
}
