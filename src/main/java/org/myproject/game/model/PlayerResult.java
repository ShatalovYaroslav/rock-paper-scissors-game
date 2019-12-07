package org.myproject.game.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerResult extends PlayerMove {

    @JsonProperty("game_result")
    private GameResult gameResult;

    public PlayerResult(PlayerMove playerMove, GameResult gameResult) {
        super(playerMove.getGamerId(), playerMove.getMove());
        this.gameResult = gameResult;
    }
}