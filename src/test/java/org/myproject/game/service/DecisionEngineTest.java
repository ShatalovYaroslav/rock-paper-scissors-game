package org.myproject.game.service;

import org.junit.Before;
import org.junit.Test;
import org.myproject.game.exception.ClientException;
import org.myproject.game.fixtures.PlayerMoveFixture;
import org.myproject.game.model.GameMove;
import org.myproject.game.model.GameResult;
import org.myproject.game.model.PlayerMove;
import org.myproject.game.model.PlayerResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;


/**
 * @author ActiveEon Team on 4/29/2016.
 */
public class DecisionEngineTest {

    private PlayerMove playerMove;

    private DecisionEngine decisionEngine;

    @Before
    public void init() {
        playerMove = PlayerMoveFixture.simplePlayerMove();
        decisionEngine = new DecisionEngine();
    }

    // DRAW test cases
    @Test
    public void testDecisionResultsAllSetUpValues() {
        PlayerMove playerMovePC = PlayerMoveFixture.playerMoveWithIDMove("some_id", "rock");
        List<PlayerResult> resultsExpected = new ArrayList<>();
        resultsExpected.add(new PlayerResult(playerMove, GameResult.DRAW));
        resultsExpected.add(new PlayerResult(playerMovePC, GameResult.DRAW));

        List<PlayerMove> playerMoves = new ArrayList<>();
        playerMoves.add(playerMove);
        playerMoves.add(playerMovePC);
        List<PlayerResult> results = decisionEngine.decide(playerMoves);

        assertThat(results.size(), is(2));
        assertThat(results, is(resultsExpected));
    }

    @Test
    public void testDecisionResultsByAllValues() {
        PlayerMove playerMovePC = PlayerMoveFixture.playerMoveWithIDMove("some_id", "rock");
        List<PlayerResult> resultsExpected = new ArrayList<>();
        resultsExpected.add(new PlayerResult(playerMove, GameResult.DRAW));
        resultsExpected.add(new PlayerResult(playerMovePC, GameResult.DRAW));

        List<PlayerMove> playerMoves = new ArrayList<>();
        playerMoves.add(PlayerMoveFixture.playerMoveWithIDMove("some_other_id", "rock"));
        playerMoves.add(playerMovePC);
        List<PlayerResult> results = decisionEngine.decide(playerMoves);

        assertThat(results.size(), is(2));
        assertThat(results, not(is(resultsExpected)));
    }

    @Test
    public void testDecisionDrawPaper() {
        generalTestCase(GameMove.PAPER, GameMove.PAPER, GameResult.DRAW, GameResult.DRAW);
    }

    @Test
    public void testDecisionDrawScissors() {
        generalTestCase(GameMove.SCISSORS, GameMove.SCISSORS, GameResult.DRAW, GameResult.DRAW);
    }

    @Test
    public void testDecisionDrawRock() {
        generalTestCase(GameMove.ROCK, GameMove.ROCK, GameResult.DRAW, GameResult.DRAW);
    }

    // WIN test cases
    @Test
    public void testDecisionWinRock() {
        generalTestCase(GameMove.ROCK, GameMove.SCISSORS, GameResult.WIN, GameResult.LOOSE);
    }

    @Test
    public void testDecisionWinScissors() {
        generalTestCase(GameMove.PAPER, GameMove.SCISSORS, GameResult.LOOSE, GameResult.WIN);
    }
    @Test
    public void testDecisionWinPaper() {
        generalTestCase(GameMove.PAPER, GameMove.ROCK, GameResult.WIN, GameResult.LOOSE);
    }

    @Test
    public void testDecisionWinPaperOppositePlayer() {
        generalTestCase(GameMove.ROCK, GameMove.PAPER, GameResult.LOOSE, GameResult.WIN);
    }

    @Test(expected = ClientException.class)
    public void testPlayWithPCBadRequest() {
        List<PlayerMove> playerMoves = new ArrayList<>();
        playerMoves.add(playerMove);

        decisionEngine.decide(playerMoves);
    }

    private void generalTestCase(GameMove move1, GameMove move2, GameResult res1, GameResult res2) {
        List<PlayerMove> playerMoves = new ArrayList<>();
        List<PlayerResult> resultsExpected = createMovesAndExpectedResults(playerMoves, move1, move2, res1, res2);
        assertThat(decisionEngine.decide(playerMoves), is(resultsExpected));
    }

    private List<PlayerResult> createMovesAndExpectedResults(List<PlayerMove> playerMoves, GameMove move1, GameMove move2, GameResult res1, GameResult res2) {
        PlayerMove player1 = PlayerMoveFixture.playerMoveWithIDGameMove("player1", move1);
        PlayerMove player2 = PlayerMoveFixture.playerMoveWithIDGameMove("player2", move2);
        playerMoves.add(player1);
        playerMoves.add(player2);

        List<PlayerResult> resultsExpected = new ArrayList<>();
        resultsExpected.add(new PlayerResult(player1, res1));
        resultsExpected.add(new PlayerResult(player2, res2));
        return resultsExpected;
    }
}
