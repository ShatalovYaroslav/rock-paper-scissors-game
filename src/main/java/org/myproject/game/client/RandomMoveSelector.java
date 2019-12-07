package org.myproject.game.client;

import org.myproject.game.model.GameMove;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("randomSelector")
public class RandomMoveSelector implements MoveSelectorClient {
    @Override
    public GameMove selectMove() {
        int randomMoveValue = new Random().nextInt(GameMove.values().length);
        return GameMove.valueOf(randomMoveValue);
    }
}
