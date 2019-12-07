package org.myproject.game.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;


/**
 * Created by Iaroslav on 7/12/2019.
 */
public class PlayerResultTest {

    @Test
    public void testEquality1()  {
        PlayerResult a = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);
        PlayerResult b = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);

        assertThat(a, is(b));
    }

    @Test
    public void testEquality2() {
        PlayerResult a = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);
        PlayerResult b = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.LOOSE);

        assertThat(a, not(is(b)));
    }

    @Test
    public void testEquality3() {
        PlayerResult a = new PlayerResult(new PlayerMove("player_id", GameMove.PAPER), GameResult.DRAW);
        PlayerResult b = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);

        assertThat(a, not(is(b)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongGameMove()  {
        PlayerResult a = new PlayerResult(new PlayerMove("player_id", GameMove.valueOf("wrong-value")), GameResult.DRAW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongGameResult()  {
        PlayerResult a = new PlayerResult(new PlayerMove("player_id", GameMove.valueOf(0)), GameResult.valueOf("Bad value"));
    }

    @Test
    public void testHashcodeEquals() {
        Set<PlayerResult> results = new HashSet<>();
        PlayerResult res = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);
        results.add(res);
        results.add(res);
        results.add(res);
        assertThat(results.size(), is(1));
    }

    @Test
    public void testHashCode1()  {
        PlayerResult a = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);
        PlayerResult b = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.LOOSE);

        assertThat(a.hashCode(), not(is(b.hashCode())));
    }

    @Test
    public void testHashCode2() {
        PlayerResult a = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);
        PlayerResult b = new PlayerResult(new PlayerMove("player_id", GameMove.ROCK), GameResult.DRAW);

        assertThat(a.hashCode(), is(b.hashCode()));
    }
}
