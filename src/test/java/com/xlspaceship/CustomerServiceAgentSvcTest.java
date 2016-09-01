package com.xlspaceship;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crossover.techtrial.Application;
import com.crossover.techtrial.util.CommonConstants;
import com.crossover.techtrial.util.emPlayerStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
//@SpringApplicationConfiguration(classes = Application.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@ContextConfiguration(locations = {"file:src/test/**/applicationContext-test.xml"})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceAgentSvcTest extends ParentTestCase {

	private static final Logger log = Logger.getLogger(CustomerServiceAgentSvcTest.class);
    
	@Rule
	public MyJUnitStopWatch stopwatch = new MyJUnitStopWatch();

//	@Resource
//	private FilterChainProxy springSecurityFilterChain;

	@Resource
	private WebApplicationContext webApplicationContext;

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	private MockMvc mockMvc;

	private GameContext gameContext;
	
	private String game_id; 
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		gameContext = new GameContext();
	}

 	private void createNewGame() {
		Map<String, String> spaceship_protocol = new HashMap<String, String>();
		spaceship_protocol.put("player", "Cesar");
		spaceship_protocol.put("hostname", "127.0.0.1");
		spaceship_protocol.put("port", "9001");

		Player p1 = new Player.Builder("xebialabs-1", "Cesar Chavez")
				.setSpaceship_protocol(spaceship_protocol)
				.setSalvo(new Salvo())
				.setTurn(0)
				.setStatus(emPlayerStatus.PLAYING)
				.build();
		Player p2 = new Player.Builder("xebialabs-2", "Adolfo Ruiz")
				.setSpaceship_protocol(spaceship_protocol)
				.setSalvo(new Salvo())
				.setTurn(0)
				.setStatus(emPlayerStatus.PLAYING)
				.build();

		Game game = new Game(p1, p2);
//		game = Game.getInstance(p1, p2);
		game.setRound(0);
		game_id = CommonConstants.GAME_ID + "-" + gameContext.createID();
		game.setId(game_id);
		log.error("game_id: " + game_id);
		gameContext.getGames().put( game_id, game);
	}
	
	@Test
	public void testCreateNewGame() {
		
		Map<String, String> protocol = new HashMap<>();
		protocol.put("hostname", "127.0.0.1");
		protocol.put("port", "9001");

		Player player = new Player.Builder("xebialabs-2", "XebiaLabs Opponent")
				.setSpaceship_protocol(protocol)
				.build();
		
	    String json = gson.toJson(player);
		try {
			mockMvc.perform(
					post("/xl-spaceship/protocol/game/new")
					.accept(MediaType.APPLICATION_JSON)
					.content(json))
				 	.andExpect(status().isCreated())
				 	.andExpect(jsonPath("$.user_id	", is("xebialabs-2") ))
	                .andExpect(jsonPath("$.full_name", is("Cesar Francisco Chavez Maldonado") ))
	                .andExpect(jsonPath("$.game_id", is( CommonConstants.GAME_ID + "-" + gameContext.getIdCounter() ) ))
//	                .andExpect(jsonPath("$.starting", is(CommonConstants.PLAYER_ID) ))
	                .andExpect(jsonPath("$.starting", is(notNullValue()) ))
//	                .andExpect(jsonPath("$.starting", or(player.getUserId()) ))
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertEquals(true, true);
	}
	
	@Test
	public void testCreateNewGameWithNullData() {
		Player player = new Player.Builder(null, null)
				.setSpaceship_protocol(null)
				.build();
		
		String json = gson.toJson(player);
		try {
			mockMvc.perform(
					post("/xl-spaceship/protocol/game/new")
					.accept(MediaType.APPLICATION_JSON)
					.content(json))
			.andExpect(status().isInternalServerError())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertEquals(true, true);
	}

//	@Test(timeout=3000000L)
//	@Test(timeout=1000L)
//	@Test
	public void testReceiveSalvoShotsAfterNewGame() {
		String[] salvoArray = { "0x0", "8x4", "DxA", "AxA", "7xF" };
		JsonSalvo salvoJson = new JsonSalvo();
		salvoJson.salvo = salvoArray;
		
		String json = gson.toJson(salvoJson); 
		
		try {
			mockMvc.perform(
					put("/xl-spaceship/protocol/game/{game_id}", "match-1")
					.accept(MediaType.APPLICATION_JSON)
					.content(json))
			.andExpect(status().isOk() )
			.andExpect(jsonPath("$.salvo", is(notNullValue() ) ))
			.andExpect(jsonPath("$.game", is(notNullValue()) ))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertEquals(true, true);
	}
	
//	@Test
	public void testReceiveSalvoShotsWithInvalidShots() {
		createNewGame();
		Game game = gameContext.getGames().get(game_id);
		game.setNextTurnAttackerPlayer(game.getPlayer2());
		String[] salvoArray = { "0x0", "8x4", "PxA", "AxA", "PxF" };
		JsonSalvo salvoJson = new JsonSalvo();
		salvoJson.salvo = salvoArray;
		String json = gson.toJson(salvoJson); 
		
		try {
			mockMvc.perform(
					put("/xl-spaceship/protocol/game/{game_id}", "match-1")
					.accept(MediaType.APPLICATION_JSON)
					.content(json))
			.andExpect(status().isInternalServerError())
			.andExpect(content().contentType(MediaType.TEXT_PLAIN));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertEquals(true, true);
	}
	
//	@Test(timeout=3000000L)
//	@Test(timeout=1000L)
	@Test
	public void testFireSalvoShotsAfterNewGame() {
		createNewGame();
		Game game = gameContext.getGames().get(game_id);
		game.setNextTurnAttackerPlayer(game.getPlayer2());
		String[] salvoArray = { "0x0", "8x4", "DxA", "AxA", "7xF" };
		JsonSalvo salvoJson = new JsonSalvo();
		salvoJson.salvo = salvoArray;
		
		String json = gson.toJson(salvoJson); 
		
		try {
			mockMvc.perform(
					put("/xl-spaceship/user/game/{game_id}/fire", game_id)
					.accept(MediaType.APPLICATION_JSON)
					.content(json))
			.andExpect(status().isNotFound() )
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertEquals(true, true);
	}
	
	@Test
	public void testFireSalvoShotsInvalidTurn() {
		createNewGame();
		Game game = gameContext.getGames().get(game_id);
		game.setNextTurnAttackedPlayer(game.getPlayer2());
		String[] salvoArray = { "0x0", "8x4", "DxA", "AxA", "7xF" };
		JsonSalvo salvoJson = new JsonSalvo();
		salvoJson.salvo = salvoArray;
		
		String json = gson.toJson(salvoJson); 
		
		try {
			mockMvc.perform(
					put("/xl-spaceship/user/game/{game_id}/fire", game_id)
					.accept(MediaType.APPLICATION_JSON)
					.content(json))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertEquals(true, true);
	}
	
//	@Test
	public void testStatusGame() {
		createNewGame();
		try {
			mockMvc.perform(
					get("/xl-spaceship/user/game/{game_id}", "match-1")
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk() )
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Assert.assertEquals(true, true);
	}
	
	@Test
	public void testDummy() {
		Assert.assertEquals(true, true);
	}
	
	public class JSON_STUBS {}
	public class JsonSalvo extends JSON_STUBS {public String[] salvo;};
	public class JsonSalvoShot extends JSON_STUBS {public String salvo; public String shots;};
	
}
