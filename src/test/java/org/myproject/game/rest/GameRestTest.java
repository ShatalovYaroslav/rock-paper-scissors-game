/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package org.myproject.game.rest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.myproject.game.fixtures.PlayerMoveFixture;
import org.myproject.game.model.GameResult;
import org.myproject.game.model.PlayerMove;
import org.myproject.game.model.PlayerResult;
import org.myproject.game.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


/**
 * Created by Iaroslav on 4/28/2016.
 */
public class GameRestTest {

    @InjectMocks
    private GameRest gameRest;

    @Mock
    private GameService gameService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlayWithPC() {
        PlayerMove playerMove = PlayerMoveFixture.simplePlayerMove();
        PlayerResult playerResult = new PlayerResult(PlayerMoveFixture.simplePlayerMove(), GameResult.WIN);
        List<PlayerResult> mockedResults = new ArrayList<>();
        mockedResults.add(playerResult);
        when(gameService.playWithPC(playerMove)).thenReturn(mockedResults);

        ResponseEntity<List<PlayerResult>> actualUsers = gameRest.playWithPC(playerMove);

        assertThat(actualUsers.getStatusCode(), is(HttpStatus.OK));
        assertThat(actualUsers.getBody().size(), is(1));
        assertThat(actualUsers.getBody().iterator().next(), is(playerResult));
        verify(gameService, times(1)).playWithPC(playerMove);
    }

    @Test
    public void testPlayMultiPlayers() {
        List<PlayerMove> playerMoveList = new ArrayList<>();
        PlayerMove playerMove = PlayerMoveFixture.simplePlayerMove();
        playerMoveList.add(playerMove);
        PlayerResult playerResult = new PlayerResult(PlayerMoveFixture.simplePlayerMove(), GameResult.WIN);
        List<PlayerResult> mockedResults = new ArrayList<>();
        mockedResults.add(playerResult);
        when(gameService.playMultiPlayers(playerMoveList)).thenReturn(mockedResults);

        ResponseEntity<List<PlayerResult>> actualUsers = gameRest.playMultiPlayers(playerMoveList);

        assertThat(actualUsers.getStatusCode(), is(HttpStatus.OK));
        assertThat(actualUsers.getBody().size(), is(1));
        assertThat(actualUsers.getBody().iterator().next(), is(playerResult));
        verify(gameService, times(1)).playMultiPlayers(playerMoveList);
    }
}
