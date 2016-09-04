package com.crossover.techtrial.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.techtrial.airline.context.LoginService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/")
public class LoginCtrl {

	private static final Logger log = Logger.getLogger(LoginCtrl.class);

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
	
	@RequestMapping(method = RequestMethod.GET)
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		return "index";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	public class Flight {
		String name;
		public String destination;
		public String getName(){return this.name;};
		public void setName(String name){this.name=name;};
		public String getDestination(){return this.destination;};
		public void setDestination(String destination){this.destination=destination;};
		public Flight(String name, String destination) {
			this.name = name;
			this.destination=destination;
	    }
	}

 	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws IOException, ServletException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		boolean isValidUser = service.validateUser(name, password);
		log.debug("isValidUser: " + isValidUser);

		if (isValidUser) {
			request.getSession().setAttribute("LOGGEDIN_USER", name);

			List<Flight> flights= new ArrayList<>();
			
			Flight test1 = new Flight("test1", "now where");
			Flight test2 = new Flight("test2", "now where");
			
			flights.add(test1);
			flights.add(test2);
			flights.add(test1);
			flights.add(test2);

//			Map<String, Object> model = new ModelMap();
//			model = new ModelMap();
			model.addAttribute("flights", flights);
//			model.put("flights", flights);
			model.put("app", "Air Ticket Reservation System");
			
			return "list-flights";
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!!");
			return "login";
		}
	}
	
/*	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		boolean isValidUser = service.validateUser(name, password);
		log.debug("isValidUser: " + isValidUser);

		ModelAndView mav = new ModelAndView();
		if (isValidUser) {
			request.getSession().setAttribute("LOGGEDIN_USER", name);

			List<Flight> flights= new ArrayList<>();
			
			Flight test1 = new Flight("test1", "now where");
			Flight test2 = new Flight("test2", "now where");
			
			flights.add(test1);
			flights.add(test2);
			flights.add(test1);
			flights.add(test2);

			mav.setViewName("list-flights");
			mav.addObject("flights", flights);
			mav.addObject("app", "Air Ticket Reservation System");
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!!");
			mav.setViewName("login");
		}
		return mav;
	}
*/

}