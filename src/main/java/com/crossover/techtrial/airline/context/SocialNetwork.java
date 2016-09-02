package com.crossover.techtrial.airline.context;

public class SocialNetwork implements AuthenticationStrategy {
 
	private String name;
	 
	private String id;
	 
	private String password;
	 
	private AuthenticationStrategy strategy;
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SocialNetwork(String id) {
		this.id = id;
	}

	public AuthenticationStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(AuthenticationStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void authenticate(String username, String password) {
		if (this.strategy != null)
			this.strategy.authenticate(username, password);
	}

}
 
