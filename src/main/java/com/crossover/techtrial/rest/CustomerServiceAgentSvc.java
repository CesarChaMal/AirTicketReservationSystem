package com.crossover.techtrial.rest;

import com.crossover.techtrial.airline.context.CustomerServiceAgentInterface;
import com.crossover.techtrial.airline.context.User;
import com.crossover.techtrial.dao.FlightDao;
import com.crossover.techtrial.dao.PassengerDao;

public class CustomerServiceAgentSvc implements CustomerServiceAgentInterface {
 
	private User user;
	 
	private PassengerDao passengerDao;
	 
	private FlightDao flightDao;
	 
	/**
	 * @see CustomerServiceAgentInterface#authenticateUser()
	 */
	public void authenticateUser() {
	 
	}
	 
	/**
	 * @see CustomerServiceAgentInterface#createUserProfile()
	 */
	public void createUserProfile() {
	 
	}
	 
	/**
	 * @see CustomerServiceAgentInterface#viewAvailableFlights()
	 */
	public void viewAvailableFlights() {
	 
	}
	 
}
 
