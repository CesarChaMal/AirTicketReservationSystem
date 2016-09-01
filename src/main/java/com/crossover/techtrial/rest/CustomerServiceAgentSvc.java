package com.crossover.techtrial.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.techtrial.airline.context.CustomerServiceAgentInterface;
import com.crossover.techtrial.airline.context.User;
import com.crossover.techtrial.dao.FlightDao;
import com.crossover.techtrial.dao.PassengerDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	public void authenticateUser() {
	 
	}
	 
	public void createUserProfile() {
	 
	}
	 
	public void viewAvailableFlights() {
	 
	}
	 
}
 
