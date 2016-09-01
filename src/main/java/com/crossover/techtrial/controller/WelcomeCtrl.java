package com.crossover.techtrial.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/")
//@SessionAttributes("game_id")
public class WelcomeCtrl {

	private static final Logger log = Logger.getLogger(WelcomeCtrl.class);
	
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
		
		return "welcome";
	}
	
}