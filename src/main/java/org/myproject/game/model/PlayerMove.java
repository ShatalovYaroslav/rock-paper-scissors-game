package org.myproject.game.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayerMove implements Serializable {

    @JsonProperty("gamer_id")
    private String gamerId;

    @JsonProperty
    private GameMove move;
}
