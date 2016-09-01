package com.crossover.techtrial.controller;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.techtrial.rest.helpers.HttpRequest;
import com.crossover.techtrial.util.CommonConstants;
import com.crossover.techtrial.util.emGameRules;
import com.crossover.techtrial.util.emPlayerStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/")
//@SessionAttributes("game_id")
public class Welcome {

	private static final Logger log = Logger.getLogger(Welcome.class);
	
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
	
	@RequestMapping(method = RequestMethod.GET)
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		return "login";
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public String login(
//							@RequestParam String user_id , @RequestParam String full_name , 
//							@RequestParam String hostname , @RequestParam String port, 
//							ModelMap model, 
//							HttpServletRequest request, HttpServletResponse response
//						) {
//		
//		Type type = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
//		JsonPlayerGameRules stub = new JsonPlayerGameRules();
//		stub.user_id = user_id;
//		stub.full_name = full_name;
//		Map<String,String> sp = new HashMap<>();
//		sp.put("hostname", hostname);
//		sp.put("port", port);
//		stub.spaceship_protocol = sp;
//		stub.rules = "standard";
//		
//		String json = gson.toJson(stub, JsonPlayerGameRules.class); 
//		HttpRequest newGameRequest = new HttpRequest(CommonConstants.NEWGAME_REQUEST, json);
//		String res = newGameRequest.toString();
//		log.info(newGameRequest);
//		JsonNewGame newGame = gson.fromJson(res, JsonNewGame.class);
//		
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		
//		model.put("user_id", newGame.user_id);
//		model.put("full_name", newGame.full_name);
//		model.put("rules", newGame.rules);
//		model.put("starting", newGame.starting);
//		model.put("game_id", newGame.game_id);
//		model.put("status", "true");
//		
////		log.info(player.getUserId());
////		log.info(player.getFullName());
////		log.info(player.getSpaceship_protocol().get("hostname"));
////		log.info(player.getSpaceship_protocol().get("port"));
//		
//		return "newGame";
//	}

	
}