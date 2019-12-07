package org.myproject.game.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
public class PlayerResult extends PlayerMove {

    @JsonProperty("game_result")
    private GameResult gameResult;

    public PlayerResult(PlayerMove playerMove, GameResult gameResult) {
        super(playerMove.getPlayerId(), playerMove.getMove());
        this.gameResult = gameResult;
    }
}