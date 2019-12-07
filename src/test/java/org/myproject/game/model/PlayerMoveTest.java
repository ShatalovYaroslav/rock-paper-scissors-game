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
public class PlayerMoveTest {

    @Test
    public void testHashcodeEquals() {
        Set<PlayerMove> moves = new HashSet<>();
        PlayerMove move = new PlayerMove("player_id", GameMove.ROCK);
        moves.add(move);
        moves.add(move);
        moves.add(move);
        assertThat(moves.size(), is(1));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWrongGameMove()  {
        PlayerMove a = new PlayerMove("player_id", GameMove.valueOf("wrong-value"));
    }

    @Test
    public void testEquality1()  {
        PlayerMove a = new PlayerMove("player_id", GameMove.ROCK);
        PlayerMove b = new PlayerMove("player_id", GameMove.ROCK);

        assertThat(a, is(b));
    }

    @Test
    public void testEquality2() throws Exception {
        PlayerMove a = new PlayerMove("player_id", GameMove.ROCK);
        PlayerMove b = new PlayerMove("player_id", GameMove.PAPER);

        assertThat(a, not(is(b)));
    }

    @Test
    public void testEquality3() throws Exception {
        PlayerMove a = new PlayerMove("player_id", GameMove.PAPER);
        PlayerMove b = new PlayerMove("player_id-2", GameMove.PAPER);

        assertThat(a, not(is(b)));
    }

    @Test
    public void testHashCode1() throws Exception {
        PlayerMove a = new PlayerMove("player_id", GameMove.PAPER);
        PlayerMove b = new PlayerMove("player_id-2", GameMove.PAPER);

        assertThat(a.hashCode(), not(is(b.hashCode())));
    }

    @Test
    public void testHashCode2() throws Exception {
        PlayerMove a = new PlayerMove("player_id", GameMove.PAPER);
        PlayerMove b = new PlayerMove("player_id", GameMove.PAPER);

        assertThat(a.hashCode(), is(b.hashCode()));
    }
}
