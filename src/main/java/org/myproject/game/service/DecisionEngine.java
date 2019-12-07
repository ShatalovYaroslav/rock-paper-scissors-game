
package org.myproject.game.service;

import org.myproject.game.model.GameMove;
import org.myproject.game.model.GameResult;
import org.myproject.game.model.PlayerMove;
import org.myproject.game.model.PlayerResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service()
public class DecisionEngine {

    /*
    for now it is considered a game between 2 players, but an extension can be for more then 2 players
     */
    public List<PlayerResult> decide(List<PlayerMove> playerMoves) {
        int playersNumber = playerMoves.size();
        if (playersNumber == 2)
            return twoValuesDecide(playerMoves.get(0), playerMoves.get(1));
        else
            throw new IllegalStateException("The players number: " + playersNumber + " is not supported in the current implementation");
    }

    private List<PlayerResult> twoValuesDecide(PlayerMove playerMove1, PlayerMove playerMove2) {
        List<PlayerResult> results = new ArrayList<>();
        int playerValue1 = playerMove1.getMove().getValue();
        int playerValue2 = playerMove2.getMove().getValue();
        GameResult resultPlayer1;
        GameResult resultPlayer2;
        if (playerValue1 == playerValue2) {
            resultPlayer1 = GameResult.DRAW;
            resultPlayer2 = GameResult.DRAW;
        } else if ((playerValue1 + 1) % GameMove.values().length == playerValue2) {
            resultPlayer1 = GameResult.WIN;
            resultPlayer2 = GameResult.LOOSE;
        } else {
            resultPlayer1 = GameResult.LOOSE;
            resultPlayer2 = GameResult.WIN;
        }
        results.add(new PlayerResult(playerMove1, resultPlayer1));
        results.add(new PlayerResult(playerMove2, resultPlayer2));
        return results;
    }
}
