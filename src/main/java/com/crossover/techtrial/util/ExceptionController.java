package com.crossover.techtrial.util;

import org.apache.log4j.Logger;

public class ExceptionController extends Exception {

	private static final Logger log = Logger.getLogger(ExceptionController.class);

	public ExceptionController(String message) {
		super(message);
		log.error("Threw an Exception");
		log.error(message);
	}
	
    public ExceptionController(String message, Throwable throwable) {
        super(message, throwable);
		log.error("Threw an Exception");
		log.error(message);
    }	
    
    public String getMessage()
    {
    	return super.getMessage();
    }
}
