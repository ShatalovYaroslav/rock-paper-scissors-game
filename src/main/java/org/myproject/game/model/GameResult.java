package org.myproject.game.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

/**
 * this class defines the results in the game for each player
 */
@ToString
public enum GameResult {
    WIN(0),
    LOOSE(1),
    DRAW(2);

    private final int value;

    GameResult(int value) {
        this.value = value;
    }

    public static GameResult convert(String state) {
        if (state == null) {
            return null;
        }
        return GameResult.valueOf(state.toUpperCase());
    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getState() {
        return this.name();
    }
}
