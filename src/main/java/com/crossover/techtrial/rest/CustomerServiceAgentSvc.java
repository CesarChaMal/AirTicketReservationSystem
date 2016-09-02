package com.crossover.techtrial.rest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.airline.context.CustomerServiceAgentInterface;
import com.crossover.techtrial.airline.context.User;
import com.crossover.techtrial.dao.FlightDao;
import com.crossover.techtrial.dao.PassengerDao;
import com.crossover.techtrial.dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/airticket")
public class CustomerServiceAgentSvc implements CustomerServiceAgentInterface {
 
	private static final Logger log = Logger.getLogger(CustomerServiceAgentSvc.class);
	
	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	private User user;
	 
	private PassengerDao passengerDao;
	 
	private FlightDao flightDao;
	 
	@Autowired
	ApplicationContext ctx;
	
	public ApplicationContext getCtx() {
		return ctx;
	}
	
	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

	@RequestMapping(
			value = "/auth", 
			method = RequestMethod.POST, 
			produces = "application/json;"
	)
	public String authenticateUser(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Map<String, String> res = new HashMap<>();
		Type type = new TypeToken<HashMap<String, String>>(){}.getType();

		try {
			User user = gson.fromJson(payload, User.class);
			
			boolean valid = authenticateUser(user);
			res.put("ok", Boolean.toString(valid));

			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			log.error(e);
			throw new WebApplicationException(Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build());
		}
		return gson.toJson(res, type);
	}
	 
	public void createUserProfile() {
	 
	}
	 
	public void viewAvailableFlights() {
	 
	}

	@Override
	public boolean authenticateUser(User user) {
//		UserDao userDao = new UserDao(user);
		UserDao userDao = new UserDao(ctx.getBean(BasicDataSource.class));

		user = userDao.findByUserNamePassword(user.getUserName(),user.getPassword());
		log.debug("userDao.findByUserNamePassword(user.getUserName(),user.getPassword()) : " + user);
		
		if (user != null)
			return true;
			
		return false;
	}
	 
}
 
