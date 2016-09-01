package com.crossover.techtrial.util;

import org.springframework.stereotype.Service;

@Service("commonConstants")
public class CommonConstants {
	public static String ENV = "dev";
	public static String SCHEMA_TYPE = "mysql";	
	public static String CUSTOMER_SERVICE_AGENT_SVC = "";	
    public static String JAVAPROCESS_HEAPMEMORY = "128M";
    public static String JAVAPROCESS_NONHEAPMEMORY = "90M";
}
