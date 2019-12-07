package org.myproject.game.client;

import org.myproject.game.model.GameMove;
import org.springframework.stereotype.Component;

@Component
public abstract class SmartMoveSelector implements MoveSelectorClient {

    /**
     * the aim of an extension of this class is to implement smart algorithm for choosing new player move according to previous results
     * this class is abstract and it is just an example of possible strategy implementation, as current game version doesn't store history of previous game results
     *
     * @return the game move generated according to some strategy
     */
    @Override
    public GameMove selectMove() {
        GameMove previousPlayerMove = getPreviousPlayerMove();
        //always change move to new value
        return GameMove.valueOf((previousPlayerMove.getValue() + 1) % GameMove.values().length);
    }

    /**
     * the implementation should be done in children classes
     *
     * @return previous Player's move
     */
    protected abstract GameMove getPreviousPlayerMove();
}
