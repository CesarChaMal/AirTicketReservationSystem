package com.crossover.techtrial.rest.helpers;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.crossover.techtrial.rest.helpers.HttpRequestUtil.HttpRequestCallback;
import com.crossover.techtrial.util.CommonConstants;
import com.crossover.techtrial.util.Environment;
import com.crossover.techtrial.util.HttpRequestType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class HttpRequest implements HttpRequestCallback {
	private boolean haserrors;
	private String response;
	private Throwable respexcept;
	private WorkRequestData wrdata = null;

	private static Logger logger = Logger.getLogger(HttpRequestUtil.class.getName());
	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	private String baseurl = null; // initialize the base url with app.properties files
	private String urlpath = ""; // service path
	private String querystring = ""; //query string for the request
	private String json = ""; //json for the request

	public HttpRequest(String game_id, String type, String json)
	{
//		String baseurlFormat = "";
//		if (type.equals(CommonConstants.RECEIVE_REQUEST)){
//			receiveRequest(game_id, json, HttpRequestType.PUT);
//		}
//		else if (type.equals(CommonConstants.FIRE_REQUEST)){
//			fireRequest(game_id, json, HttpRequestType.PUT);
//		}
//		else if (type.equals(CommonConstants.STATUS_REQUEST)) {
//			statusGameRequest(game_id, json, HttpRequestType.GET);
//		}
	}
	
	public HttpRequest(String type, String json)
	{
//		String baseurlFormat = "";
//		if (type.equals(CommonConstants.NEWGAME_REQUEST)){
//			newGameRequest(json, HttpRequestType.POST);
//		}
	}
	
	public void receiveRequest(String game_id, String json, HttpRequestType type)
	{
		String baseurlFormat = "";
		baseurlFormat = "/protocol/game/%s";
//			baseurlFormat = "/user/game/%s/fire";
		
		this.json = json;
		
		urlpath = String.format(baseurlFormat, game_id.trim());
		
//		baseurl = Environment.getProperty("XLSpaceShip.RequestSvc");
		baseurl = CommonConstants.WELCOME_CTRL;
		
		if(baseurl != null) {
			this.getRequest(type);	
		}
	}
	
	public void fireRequest(String game_id, String json, HttpRequestType type)
	{
		String baseurlFormat = "";
		baseurlFormat = "/user/game/%s/fire";
		
		this.json = json;
		
		urlpath = String.format(baseurlFormat, game_id.trim());
		
//		baseurl = Environment.getProperty("XLSpaceShip.RequestSvc");
		baseurl = CommonConstants.WELCOME_CTRL;
		
		if(baseurl != null) {
			this.getRequest(type);	
		}
	}
	
	public void newGameRequest(String json, HttpRequestType type)
	{
		String baseurlFormat = "";
		baseurlFormat = "/protocol/game/new";
		
		urlpath = String.format(baseurlFormat);
		this.json = json;
		
//		baseurl = Environment.getProperty("XLSpaceShip.RequestSvc");
		baseurl = CommonConstants.WELCOME_CTRL;
		
		if(baseurl != null) {
			this.getRequest(type);	
		}
	}
	
	public void statusGameRequest(String game_id, String json, HttpRequestType type)
	{
		String baseurlFormat = "";
		baseurlFormat = "/user/game/%s";
		
		this.json = json;
		
		urlpath = String.format(baseurlFormat, game_id.trim());
		
//		baseurl = Environment.getProperty("XLSpaceShip.RequestSvc");
		baseurl = CommonConstants.WELCOME_CTRL;
		
		if(baseurl != null) {
			this.getRequest(type);	
		}
	}
	
	public void getRequest(HttpRequestType type)
	{
		switch (type) {
		case GET:
			HttpRequestUtil.makeRequest(baseurl + urlpath, querystring, HttpRequestType.GET, this);
			break;
		case POST:
			HttpRequestUtil.makeRequest(baseurl + urlpath, json, HttpRequestType.POST, this);
			break;
		case PUT:
			HttpRequestUtil.makeRequest(baseurl + urlpath, json, HttpRequestType.PUT, this);
			break;
		case DELETE:
			HttpRequestUtil.makeRequest(baseurl + urlpath, querystring, HttpRequestType.DELETE, this);
			break;
		default:
			break;
		}
		if(!this.hasError())
		{
			// no errors, lets do some work and populate this object
			wrdata = new WorkRequestData(this.response);
		}
		else
		{
			logger.error("unable to get work response : " + baseurl);
		}
	}
	
	/**
	 * @return - the value for workrequest data
	 */
	public WorkRequestData getData()
	{
		return wrdata;
	}
	
	/**
	 * return the response string
	 */
	public String toString()
	{
		return response;
	}
	
	/**
	 * used by call back to populate response strings
	 */
	public void ResponseRecieved(String resp)
	{
		response = resp;
		// parse the string into gson object
		// perform any parse or generation of the object
	}

	/**
	 * store the error received from the http request.
	 * 
	 * @param request - request error 
	 * @param exception - exception from http request
	 */
	public void Error(String request, Throwable exception)
	{
		//special handling?
		logger.debug(request);
		logger.debug(exception.toString());
		haserrors  = (exception!=null);
		respexcept = exception;

	}
	
	/**
	 * respond with status of request object if it has errors.
	 * @return true or false, true means there are errors.
	 */
	public boolean hasError()
	{
		return haserrors;
	}
	
	/**
	 * @return - return exception
	 */
	public Throwable getException()
	{
		return respexcept;
	}

	/**
	 * use gson object to serialize one of these to life
	 * @author hp-employee
	 *
	 */
	public class WorkRequestData {

		private ArrayList<String> salvo = null;
		
		private Map<String, Map<String, String>> response = null;


		private boolean isInitialized = false;
		
		public WorkRequestData(String stream)
		{
			// for simple responses, not for xl spaceship complex responses 
//			WorkRequestPrimative wrp = new WorkRequestPrimative();
//			Gson gson = new Gson();
//			wrp = gson.fromJson(stream, WorkRequestPrimative.class);
			response = new HashMap<>();
			Type type = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
			gson.toJson(response, type);
//			init(wrp);
		}
		
		private void init(WorkRequestPrimative wrp)
		{
			
//			ArrayList<String> salvoShots = new ArrayList<String>();
//			if(wrp.salvo != null)
//			{
//				if(Collections.addAll(salvoShots, wrp.salvo))
//					setSalvoShots(salvoShots);
//				else
//					setSalvoShots(null);
//			}
//			else
//			{
//				setSalvoShots(null);
//			}

//			setInitialized(true);
		}
		
		public Map<String, Map<String, String>> getResponse() {
			return response;
		}

		public void setResponse(Map<String, Map<String, String>> response) {
			this.response = response;
		}

		public ArrayList<String> getSalvoShots()
		{
			return salvo;
		}
		public void setSalvoShots(ArrayList<String> salvoShots) {
			this.salvo = salvoShots;
		}

		public boolean isInitialized() {
			return isInitialized;
		}

		public void setInitialized(boolean isInitialized) {
			this.isInitialized = isInitialized;
		}

	}
}
