package com.crossover.techtrial.airline.context;

public interface AuthenticationStrategy {
	void authenticate(String username, String password);
}
