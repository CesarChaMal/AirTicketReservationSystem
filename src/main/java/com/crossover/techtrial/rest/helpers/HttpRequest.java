package com.crossover.techtrial.rest.helpers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.crossover.techtrial.rest.helpers.HttpRequestUtil.HttpRequestCallback;
import com.crossover.techtrial.util.CommonConstants;
import com.crossover.techtrial.util.HttpRequestType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	
	public boolean HttpRequestManager(String type, String json)
	{
		boolean valid = false;
		String baseurlFormat = "";
		if (type.equals(CommonConstants.AUTHENTICATE_USER_SVC)){
			valid = aunthenticateUserRequest(json, HttpRequestType.POST);
		}
//		else if (type.equals(CommonConstants.FIRE_REQUEST)){
//			fireRequest(game_id, json, HttpRequestType.PUT);
//		}
//		else if (type.equals(CommonConstants.STATUS_REQUEST)) {
//			statusGameRequest(game_id, json, HttpRequestType.GET);
//		}
		logger.debug("valid in HttpRequestManager(String type, String json): " + valid);
		
		return valid;
	}
	
	public boolean aunthenticateUserRequest(String json, HttpRequestType type)
	{
		boolean valid = false;
		String baseurlFormat = "";
		baseurlFormat = "/auth";
		
		this.json = json;
		
		urlpath = String.format(baseurlFormat);
		
//		baseurl = Environment.getProperty("AirTicket.AuthenticateSvc");
//		baseurl = CommonConstants.AUTHENTICATE_USER_SVC;
		
//		baseurl = Environment.getProperty("AirTicket.CustomerServiceAgentSvc");
		baseurl = CommonConstants.CUSTOMER_SERVICE_AGENT_SVC;
		
		if(baseurl != null) {
//			return this.getRequest(type);	
			valid = this.getRequest(type);	
		}
		logger.debug("valid in aunthenticateUserRequest(String json, HttpRequestType type): " + valid);
		return valid;
}
	
	public boolean getRequest(HttpRequestType type)
	{
		boolean valid = false;
		switch (type) {
		case GET:
			valid = HttpRequestUtil.makeRequest(baseurl + urlpath, querystring, HttpRequestType.GET, this);
			break;
		case POST:
			valid = HttpRequestUtil.makeRequest(baseurl + urlpath, json, HttpRequestType.POST, this);
			break;
		case PUT:
			valid = HttpRequestUtil.makeRequest(baseurl + urlpath, json, HttpRequestType.PUT, this);
			break;
		case DELETE:
			valid = HttpRequestUtil.makeRequest(baseurl + urlpath, querystring, HttpRequestType.DELETE, this);
			break;
		default:
			break;
		}
		
		logger.debug("valid in getRequest(HttpRequestType type) before WorkRequestData(this.response) : " + valid);

		if(!this.hasError())
		{
			// no errors, lets do some work and populate this object
			wrdata = new WorkRequestData(this.response);
			logger.debug("WorkRequestData(this.response) : " + wrdata.response);
			
			WorkRequestPrimative res = new WorkRequestPrimative();

			res = wrdata.getResponse();
			logger.debug("res: " + res);
			 
			if (res!= null) {
				if (res.ok != null) {
					
					logger.debug("res.ok: " + res.ok);

					if (res.ok.equals("true")){
						valid = true;
					} else if (res.ok.equals("false")){
						valid = false;
					}
					
				} else {
					valid = false;
				}
			} else {
				valid = false;
			}
		}
		else
		{
			logger.error("unable to get work response : " + baseurl);
			valid = false;
		}

		logger.debug("valid in getRequest(HttpRequestType type) after WorkRequestData(this.response) : " + valid);
		return valid;
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
	 *
	 */
	public class WorkRequestData {

		private WorkRequestPrimative response = null;

		private boolean isInitialized = false;
		
		public WorkRequestData(String stream)
		{
			// for simple responses only not for complex responses 
//			response = new HashMap<>();
			
			WorkRequestPrimative wrp = new WorkRequestPrimative();
			Gson gson = new Gson();
			wrp = gson.fromJson(stream, WorkRequestPrimative.class);

			logger.debug("Steam: " + stream);
			
			logger.debug("wrp: " + wrp);
			logger.debug("wrp: " + wrp.ok);
			
			setResponse(wrp);
		}
		
		public WorkRequestPrimative getResponse() {
			return response;
		}

		public void setResponse(WorkRequestPrimative response) {
			this.response = response;
		}

		public boolean isInitialized() {
			return isInitialized;
		}

		public void setInitialized(boolean isInitialized) {
			this.isInitialized = isInitialized;
		}
	}

}
