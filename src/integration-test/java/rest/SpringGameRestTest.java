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
package rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.myproject.game.Application;
import org.myproject.game.model.GameMove;
import org.myproject.game.model.PlayerMove;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;


/**
 * Created by ActiveEon Team on 7/12/2019.
 */

@ActiveProfiles("test")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest(randomPort = true)
public class SpringGameRestTest extends AbstractRestTest {

    final private RestTemplate restTemplate = new RestTemplate();

    private String REST_SERVICE_URI;

    @Before
    public void configureRestAssured() {
        REST_SERVICE_URI = "http://localhost:" + serverPort + "/game/";
    }

    //testing using springframework RestTemplate
    /* POST, PUT and GET */
    @Test
    public void testPlatWithPC() {
        PlayerMove playerMove = new PlayerMove("Yaro", GameMove.convert("rock"));

        // play with PC
        ResponseEntity respUResults = restTemplate.postForEntity(REST_SERVICE_URI + "playWithPC/", playerMove, PlayerMove.class);
        assertThat(respUResults.getStatusCode(), is(HttpStatus.OK));
        assertThat(respUResults.getBody(), is(playerMove));

    }
}
