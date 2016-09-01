package com.crossover.techtrial.rest;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/customerServiceAgent")
public class CustomerServiceAgentSvc {

	
	private static final Logger log = Logger.getLogger(CustomerServiceAgentSvc.class);
	
	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	@Autowired
	ApplicationContext ctx;
	
	public ApplicationContext getCtx() {
		return ctx;
	}
	
	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	@Autowired
	private WebApplicationContext wctx;

	public WebApplicationContext getWctx() {
		return wctx;
	}

	public void setWctx(WebApplicationContext wctx) {
		this.wctx = wctx;
	}

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String sayHelloGet(@PathVariable String name){
		return "Hello " + name;
	}

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.POST)
	public String sayHelloPost(@PathVariable String name){
		return "Hello " + name;
	}
	
	@RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
	public Map<String, String> testGet(@PathVariable String name){
		Map<String, String> spaceship_protocol = new HashMap<String, String>();
		spaceship_protocol.put("player", "Cesar");
		spaceship_protocol.put("hostname", "127.0.0.1");
		spaceship_protocol.put("port", "9001");
		
		return spaceship_protocol;
	}
	
	@RequestMapping(value = "/test/{name}", method = RequestMethod.POST)
	public Map<String, String> testPost(@PathVariable String name){
		Map<String, String> spaceship_protocol = new HashMap<String, String>();
		spaceship_protocol.put("player", "Cesar");
		spaceship_protocol.put("hostname", "127.0.0.1");
		spaceship_protocol.put("port", "9001");

		return spaceship_protocol;
	}

//	@RequestMapping(
//			value = "/airticket/game/new", 
//			method = RequestMethod.POST, 
//			produces = "application/json;"
//	)
//	public String createNewGame(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) throws Exception {		
//		
//		Map<String, String> res = new HashMap<>();
//		Type type = new TypeToken<HashMap<String, String>>(){}.getType();
//		try {
//			Player player = gson.fromJson(payload, Player.class);
//			JsonGameRules rules = gson.fromJson(payload, JsonGameRules.class);
//			
//			if (	player.getUserId() == null || 
//					player.getFullName() == null ||
//					player.getSpaceship_protocol().get("hostname") == null ||
//					player.getSpaceship_protocol().get("port") == null ||
//					player.getUserId().equals("") ||
//					player.getFullName().equals("") ||
//					player.getSpaceship_protocol().get("hostname").equals("") ||
//					player.getSpaceship_protocol().get("port").equals("")
//				)
//			{
//				throw new ExceptionController("Not null or empty values allowed");
//			}
//			
//			Player p1 = new Player.Builder(CommonConstants.PLAYER_ID, CommonConstants.PLAYER)
//								.setSpaceship_protocol(player.getSpaceship_protocol())
//								.setSalvo(new Salvo())
//								.setTurn(0)
//								.setStatus(emPlayerStatus.PLAYING)
//								.build();
//			Player p2 = new Player.Builder(player.getUserId(), player.getFullName())
//								.setSpaceship_protocol(player.getSpaceship_protocol())
//								.setSalvo(new Salvo())
//								.setTurn(0)
//								.setStatus(emPlayerStatus.PLAYING)
//								.build();
//	 
////			gameContext = gameContext.getInstance();
//			Map<String, Game> games = gameContext.getGames();
//			Game game = new Game(p1, p2);
////			game = Game.getInstance(p1, p2);
//			game.setId(CommonConstants.GAME_ID + "-" + gameContext.createID());
//			game.setRound(0);
//			emGameRules rule = null;
//			int xshot = 0;
//			if(!(rules.rules == null)){
//				if (rules.rules.equals(CommonConstants.STANDARD_GAME_RULES)){
//					rule = emGameRules.STANDARD;
//				} else if (rules.rules.equals(CommonConstants.SUPER_CHARGE_GAME_RULES)){
//					rule = emGameRules.SUPER_CHARGE;
//				} else if (rules.rules.equals(CommonConstants.DESPERATION_GAME_RULES)){
//					rule = emGameRules.DESPERATION;
//				} else if (rules.rules.contains(CommonConstants.X_SHOT_GAME_RULES)){
//					String[] ruleArray = rules.rules.split("-");
//					rule = emGameRules.X_SHOT;
//					xshot = Integer.parseInt(ruleArray[0]);
//					if(xshot >=0 && xshot <=10)
//						rule.setEnumValue(xshot);
//					else
//						throw new ExceptionController("Wrong rule");
//				}
//				game.setRules(rule);
//			}
//			
//			games.put(game.getId(), game);
//			
//			p1 = game.getPlayer1();
//			p2 = game.getPlayer2();
//			
//			int starting = (int) Math.floor(Math.random() * 2);
//			if (starting == 0) {
//				game.setStartingPlayer(p1);
//				game.setNextTurnAttackerPlayer(p1);
//				game.setNextTurnAttackedPlayer(p2);
//			}
//			else if (starting == 1) {
//				game.setStartingPlayer(p2);
//				game.setNextTurnAttackerPlayer(p2);
//				game.setNextTurnAttackedPlayer(p1);
//			}
//			String startingPlayer = game.getStartingPlayer().getUserId();
//
//			log.info("Starting player: " + startingPlayer);
//			
//			res = new HashMap<>();
//			res.put("user_id", p1.getUserId());
//			res.put("full_name", p1.getFullName());
//			res.put("game_id", game.getId());
//			res.put("starting", startingPlayer);
//			
//			String ruleType = "";
//			if ( rule!=null ){
//				switch (rule) {
//				case STANDARD:
//				case SUPER_CHARGE:
//				case DESPERATION:
//					ruleType = rule.getEnumName();
//					if(!CommonConstants.HACK_SHOT_ATTEMPS) {
//						if(rule == emGameRules.DESPERATION){
//							p1.getSalvo().setShotsAttemps(1);
//							p2.getSalvo().setShotsAttemps(1);
//						}
//					}
//					break;
//					
//				default:
//					ruleType = xshot + CommonConstants.X_SHOT_GAME_RULES;
//					break;
//				}
//				res.put("rules", ruleType);
//			}
//			response.setStatus(HttpServletResponse.SC_CREATED);
//		} catch (Exception e) {
//			log.error(e);
//			throw new WebApplicationException(Response
//					.status(Status.INTERNAL_SERVER_ERROR)
//					.entity(e.getMessage()).build());
//		}
//		return gson.toJson(res, type);
//	}
	
	
//	public class JSON_STUBS {}
//	public class JsonSalvo extends JSON_STUBS {String[] salvo;};
//	public class JsonSalvoShot extends JSON_STUBS {String salvo; String shots;};
//	public class JsonGameRules extends JSON_STUBS {String rules;};
//	public class JsonPlayerGameRules extends JSON_STUBS {String user_id; String full_name; Map<String, String> spaceship_protocol; String rules;};
//	public class JsonNewGame extends JSON_STUBS {String user_id; String full_name; String starting; String rules; String game_id;};
}