package com.crossover.techtrial.airline.context;

public interface CustomerServiceAgentInterface {
 
	public abstract boolean authenticateUser(User user);
	public abstract void createUserProfile();
	public abstract void viewAvailableFlights();
}
 
