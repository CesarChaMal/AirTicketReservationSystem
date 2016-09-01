package com.crossover.techtrial.rest.helpers;

import org.apache.log4j.Logger;

import com.crossover.techtrial.rest.helpers.HttpRequestUtil.HttpRequestCallback;
import com.crossover.techtrial.util.HttpRequestType;

public class GenRequest implements HttpRequestCallback {
	private boolean haserrors;
	private String response = null;
	private Throwable respexcept;
	private Logger logger = Logger.getLogger(HttpRequestUtil.class.getName());
	private String endpoint = null;
	/**
     * respond with status of request object if it has errors.
     * return true or false, true means there are errors.
     * 
     */
	public GenRequest(){}
	public GenRequest(String endpoint){
		this.endpoint = endpoint;
	}
	
	public String get(String path){
		int retries = 30;
		int i = 0;
		if(endpoint != null){
			while(i++ < retries){
				try{
					HttpRequestUtil.makeRequest(endpoint + path, null, HttpRequestType.GET, this);
					if(this.hasError())
					{
						Thread.sleep(10000);//10 seconds, a total of 5 min's
						logger.debug("waiting before retrying " + i);
						if(i == 30){// on the last iteration, log the error
							logger.error("Errors in make request, retries were unsuccessful", respexcept);
						}
					}else{
						break;
					}
				}catch(Exception e){
					logger.error("Exception caught while calling makeRequest " + endpoint + path, e);
				}
			}
			return response;
		}else{
			return null;
		}
			
	}
	
	public String post(String path, String data){
		int retries = 30;
		int i = 0;
		if(endpoint != null){
			while(i++ < retries){
				try{
					HttpRequestUtil.makeRequest(endpoint + path, data, HttpRequestType.POST, this);
					if(this.hasError())
					{
						Thread.sleep(10000);//10 seconds, a total of 5 min's
						logger.debug("waiting before retrying " + i);
						if(i == 30){// on the last iteration, log the error
							logger.error("Errors in make request, retries were unsuccessful", respexcept);
						}
					}else{
						break;
					}
				}catch(Exception e){
					logger.error("Exception caught while calling makeRequest " + endpoint + path, e);
				}
			}
			return response;
		}else{
			return null;
		}
			
	}
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
    public void Error(String request, Throwable exception)
    {
    	//special handling?
    	logger.debug(request);
    	logger.debug(exception.toString());
    	haserrors  = (exception!=null);
    	respexcept = exception;
    		
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
	public String getResponse()
	{
		return response;
	}
	public String toString()
	{
		return response;
	}

}