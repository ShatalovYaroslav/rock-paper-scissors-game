package org.myproject.game.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * this class defines the moves in the game, it can be easily extended for more moves
 * The value of the moves are important as they define winning rules of the game
 */
@ToString
public enum GameMove {
    ROCK(0),
    SCISSORS(1),
    PAPER(2);

    private static Map<Integer, GameMove> map;

    static {
        map = Arrays.stream(GameMove.values())
                .collect(Collectors.toMap(i -> i.value, i -> i));
    }

    private final int value;

    GameMove(int value) {
        this.value = value;
    }

    public static GameMove valueOf(int gameMove) {
        return map.get(gameMove);
    }

    @JsonCreator
    public static GameMove convert(String state) {
        if (state == null) {
            return null;
        }
        return GameMove.valueOf(state.toUpperCase());
    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getState() {
        return this.name();
    }
}
