package org.myproject.game.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.myproject.game.client.MoveSelectorClient;
import org.myproject.game.exception.ClientException;
import org.myproject.game.fixtures.PlayerMoveFixture;
import org.myproject.game.model.PlayerMove;
import org.myproject.game.model.PlayerResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;


/**
 * @author Yaroslav Shatalov on 7/12/2019.
 */
public class GameServiceTest {

    private PlayerMove playerMove;

    @InjectMocks
    private GameService gameService;

    @Mock
    private DecisionEngine decisionEngine;

    @Mock
    private MoveSelectorClient moveSelectorClient;

    @Before
    public void init() {
        playerMove = PlayerMoveFixture.simplePlayerMove();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlayWithPCExecutionOrder() {
        PlayerMove playerMovePC = PlayerMoveFixture.playerMoveWithID("some_id");
        when(moveSelectorClient.selectMove()).thenReturn(playerMovePC.getMove());
        gameService.playWithPC(playerMove);

        InOrder inOrder = Mockito.inOrder(moveSelectorClient,
                decisionEngine);

        inOrder.verify(moveSelectorClient, times(1)).selectMove();
        inOrder.verify(decisionEngine, times(1)).decide(anyList());
    }

    @Test
    public void testPlayWithPCResults() {
        PlayerMove playerMovePC = PlayerMoveFixture.playerMoveWithID("some_id");
        when(moveSelectorClient.selectMove()).thenReturn(playerMovePC.getMove());

        List<PlayerResult> mockedResults = new ArrayList<>();
        when(decisionEngine.decide(anyList())).thenReturn(mockedResults);

        List<PlayerResult> results = gameService.playWithPC(playerMove);

        verify(moveSelectorClient, times(1)).selectMove();
        verify(decisionEngine, times(1)).decide(anyList());
        assertThat(results, is(mockedResults));
    }

    @Test(expected = ClientException.class)
    public void testPlayWithPCBadRequest() {
        when(moveSelectorClient.selectMove()).thenThrow(new IllegalArgumentException());
        List<PlayerResult> mockedResults = new ArrayList<>();
        when(decisionEngine.decide(anyList())).thenReturn(mockedResults);

        gameService.playWithPC(playerMove);
    }

    @Test(expected = ClientException.class)
    public void testPlayMultiPlayersWrongOneParameter() {
        List<PlayerMove> playerMoveList = new ArrayList<>();
        playerMoveList.add(PlayerMoveFixture.simplePlayerMove());

        gameService.playMultiPlayers(playerMoveList);
    }

    @Test(expected = ClientException.class)
    public void testPlayMultiPlayersWrongAmountParameters() {
        List<PlayerMove> playerMoveList = new ArrayList<>();
        playerMoveList.add(PlayerMoveFixture.simplePlayerMove());
        playerMoveList.add(PlayerMoveFixture.playerMoveWithID("second_player"));
        playerMoveList.add(PlayerMoveFixture.playerMoveWithID("third_player"));

        gameService.playMultiPlayers(playerMoveList);
    }

    @Test
    public void testPlayMultiPlayersResults() {
        List<PlayerResult> mockedResults = new ArrayList<>();

        List<PlayerMove> playerMoveList = new ArrayList<>();
        playerMoveList.add(PlayerMoveFixture.playerMoveWithID("first_player"));
        playerMoveList.add(PlayerMoveFixture.playerMoveWithID("second_player"));

        when(decisionEngine.decide(playerMoveList)).thenReturn(mockedResults);
        List<PlayerResult> results = gameService.playMultiPlayers(playerMoveList);

        verify(moveSelectorClient, times(0)).selectMove();
        verify(decisionEngine, times(1)).decide(playerMoveList);
        assertThat(results, is(mockedResults));
    }
}
