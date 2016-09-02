package com.crossover.techtrial.controller;

import java.io.IOException;

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
@RequestMapping(value = "/login.do")
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
		
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		boolean isValidUser = service.validateUser(name, password);
		log.debug("isValidUser: " + isValidUser);

		if (isValidUser) {
			request.getSession().setAttribute("LOGGEDIN_USER", name);
//			response.sendRedirect("/list-todos.do");
			return "list-flights";
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!!");
//			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
//			request.getRequestDispatcher("/login.do").forward(request, response);
			return "login";
		}
	}

}