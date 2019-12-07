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

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.myproject.game.Application;
import org.myproject.game.model.GameMove;
import org.myproject.game.model.GameResult;
import org.myproject.game.model.PlayerMove;
import org.myproject.game.model.PlayerResult;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;


/**
 * Created by Yaroslav on 7/12/2019.
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
        RestAssured.port = serverPort;
    }

    //integration testing for REST API using RestAssured
    @Test
    public void testPlayWithPC() {
        PlayerMove playerMove = new PlayerMove("Yaro", GameMove.ROCK);

        Response response = given().body(playerMove)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-Type", ContentType.JSON)
                .when()
                .post(REST_SERVICE_URI + "playWithPC/");

        response.then()
                .assertThat()
                .statusCode(org.apache.http.HttpStatus.SC_OK)
                .body("", hasSize(2));

        response.then().
                root("find {it.player_id == '%s'}").
                body("move", withArgs("Yaro"), is("ROCK"));

        //check the results as it depends on the produced value
        PlayerResult[] results = response.body().as(PlayerResult[].class);

        assertThat(results[0].getPlayerId(), is("Yaro"));
        GameResult mineGameResult = results[0].getGameResult();
        switch (mineGameResult) {
            case DRAW:
                assertThat(results[1].getGameResult(), is(GameResult.DRAW));
                assertThat(results[1].getMove(), is(GameMove.ROCK));
                break;
            case WIN:
                assertThat(results[1].getGameResult(), is(GameResult.LOOSE));
                assertThat(results[1].getMove(), is(GameMove.SCISSORS));
                break;
            case LOOSE:
                assertThat(results[1].getGameResult(), is(GameResult.WIN));
                assertThat(results[1].getMove(), is(GameMove.PAPER));
                break;
        }
    }
}
