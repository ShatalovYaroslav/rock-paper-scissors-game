
package org.myproject.game.fixtures;

import org.myproject.game.model.GameMove;
import org.myproject.game.model.PlayerMove;


/**
 * Created by Iaroslav on 4/29/2016.
 */
public class PlayerMoveFixture {

    public static PlayerMove simplePlayerMove() {
        return new PlayerMove("Yaro", GameMove.ROCK);
    }
    public static PlayerMove playerMoveWithID(String playerId) {
        return new PlayerMove(playerId, GameMove.ROCK);
    }
    public static PlayerMove playerMoveWithIDMove(String playerId, String move) {
        return new PlayerMove(playerId, GameMove.convert(move));
    }
    public static PlayerMove playerMoveWithIDGameMove(String playerId, GameMove move) {
        return new PlayerMove(playerId, move);
    }
}
