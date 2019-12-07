package org.myproject.game.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

@ToString
/*
this class defines the moves in the game, it can be easily extended for more moves
The value of the moves are important as they define winning rules of the game
 */
public enum GameMove {
    ROCK(0),
    SCISSORS(1),
    PAPER(2);

    private final int value;

    private GameMove(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GameMove convert(String state) {
        if (state == null) {
            return null;
        }
        return GameMove.valueOf(state.toUpperCase());
    }

    @JsonValue
    public String getState() {
        return this.name();
    }
}
