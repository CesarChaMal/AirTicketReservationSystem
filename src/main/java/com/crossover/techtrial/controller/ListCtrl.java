package com.crossover.techtrial.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import com.crossover.techtrial.airline.context.LoginService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/list-flights.do")
public class ListCtrl {

	private static final Logger log = Logger.getLogger(ListCtrl.class);

	private LoginService service = new LoginService();
	
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
	
	
	public class Flights {String name; String destination;}
	@RequestMapping(method = RequestMethod.GET)
	public String listFlights(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		Flights test1 = new Flights() {
            String name = "Flight 01";
            String destination = "nowwhere";
        };
        Flights test2 = new Flights() {
        	String name = "Flight 02";
        	String destination = "nowwhere";
        };
        
		List<Flights> flights= new ArrayList<Flights>();
		
		flights.add(test1);
		flights.add(test2);

		model.put("flights", flights);

		return "list-flights";
	}
	
}