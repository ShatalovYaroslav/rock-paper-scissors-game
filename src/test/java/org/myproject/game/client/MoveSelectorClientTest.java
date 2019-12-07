package org.myproject.game.client;

import org.junit.Before;
import org.junit.Test;
import org.myproject.game.model.GameMove;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MoveSelectorClientTest {

    private MoveSelectorClient moveSelectorClient;

    @Before
    public void init() {
        moveSelectorClient = new RandomMoveSelector();
    }

    @Test
    public void testSelectMove() {
        GameMove move = moveSelectorClient.selectMove();

        assertThat(move, is(notNullValue()));
    }
}
