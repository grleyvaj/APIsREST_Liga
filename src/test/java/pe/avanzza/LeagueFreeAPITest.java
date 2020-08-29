package pe.avanzza;

import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pe.avanzza.league.domain.entity.League;
import pe.avanzza.league.domain.entity.Player;
import pe.avanzza.league.domain.entity.Team;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LeagueFreeAPITest extends AbstractTest{

    private Long id;
    private League league;
    private static RequestSpecification specification;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        String token = TokenAuthenticationService.createToken("leyvajerezgr");

        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/avanzza/v1/ligas").header("Authorization", token)).andExpect(status().isUnauthorized());
        //probar que de error cuando se accede sin autenticarse
    }

    //Test aplicados antes de adicionar la seguridad

    /**
     * Testing the getAllLeagues method of LeaqueAPI
     *
     * @throws Exception
     */
    @Test
    public void testA() throws Exception {
        String uri = "http://localhost:8081/avanzza/v1/ligas";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        League[] leagues = super.mapFromJson(content, League[].class);
        assertTrue(leagues.length > 0);
    }

    /**
     * Testing the getAllTeams method of LeaqueAPI
     *
     * @throws Exception
     */
    @Test
    public void testB() throws Exception {
        String uri = "http://localhost:8081/avanzza/v1/equipos";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Team[] teams = super.mapFromJson(content, Team[].class);
        assertTrue(teams.length > 0);
    }

    /**
     * Testing the getAllPlayers method of LeaqueAPI
     *
     * @throws Exception
     */
    @Test
    public void testC() throws Exception {
        String uri = "http://localhost:8081/avanzza/v1/players";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Player[] players = super.mapFromJson(content, Player[].class);
        assertTrue(players.length > 0);
    }
}

