package com.crossover.techtrial.airline.context;

public abstract class SocialNetwork {
 
	private String name;
	 
	private String id;
	 
	private String password;
	 
	private User user;
	 
	public abstract void authenticate();
}
 
