package com.crossover.techtrial.airline.context;

public class LoginService {
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("Cesar") && password.equals("pass");
	}
}
