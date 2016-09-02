package com.crossover.techtrial.airline.context;

import org.apache.log4j.Logger;

import com.crossover.techtrial.dao.UserDao;
import com.crossover.techtrial.rest.helpers.HttpRequest;
import com.crossover.techtrial.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginService {
	private static final Logger log = Logger.getLogger(UserDao.class);

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	public boolean validateUser(String user, String password) {
		boolean valid = false;

		User stub = new User.Builder(user, password).build();
		String json = gson.toJson(stub, User.class); 
		HttpRequest authenticateUser = new HttpRequest();
		valid = authenticateUser.HttpRequestManager(CommonConstants.AUTHENTICATE_USER_SVC, json);
		log.debug("valid in validateUser(String user, String password): " + valid);
		
		return valid;
//		return user.equalsIgnoreCase("admin") && password.equals("pass");
	}
}
