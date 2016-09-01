package com.crossover.techtrial.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public enum Environment {
    DEVELOPMENT, PRODUCTION, TEST, CUSTOM;

    private static final Logger log = Logger.getLogger(Environment.class);
    
    public static String KEY = "xlspaceship.env";

    public static final String DEFAULT = "xlspaceship.env.default";
    
    private static final String PRODUCTION_ENV_SHORT_NAME          = "prod";
    private static final String DEVELOPMENT_ENVIRONMENT_SHORT_NAME = "dev";
    private static final String TEST_ENVIRONMENT_SHORT_NAME        = "test";
    private String name;
    
    private static HashMap<String, String> envNameMappings = 
        new HashMap<String, String>() {{
            put(DEVELOPMENT_ENVIRONMENT_SHORT_NAME, 
                Environment.DEVELOPMENT.getName());
            put(PRODUCTION_ENV_SHORT_NAME, 
                Environment.PRODUCTION.getName());
            put(TEST_ENVIRONMENT_SHORT_NAME, 
                Environment.TEST.getName());
    }};
    //private static UseITEnvironment environment = new UseITEnvironment();

    @Autowired
    public static Properties appProperties;

    public static Environment getCurrent() {
        
        String envName = System.getProperty(Environment.KEY);
        
        if(isBlank(envName)) {
            log.warn("xlspaceship.env system property not set -> dev mode");
            return DEVELOPMENT;
        }
        else {
            Environment env = getEnvironment(envName);
            if(env == null) {
                try {
                    env = Environment.valueOf(envName.toUpperCase());
                }
                catch (IllegalArgumentException e) {
                    // ignore
                }
            }
            if(env == null) {
                env = Environment.CUSTOM;
                env.setName(envName);
            }
            return env;
        }
    }

    
     public static boolean isSystemSet() {
         return System.getProperty(KEY) != null;
     }

     public static Environment getEnvironment(String shortName) {
         final String envName = envNameMappings.get(shortName);
         if(envName != null) {
             return Environment.valueOf(envName.toUpperCase());
         }
         return null;
     }

    public static void setProperties(Properties appProperties) {
        Environment.appProperties = appProperties;
    }
    
    public static String getProperty(String propName) {
        final String header = Environment.getShortName() + ".";
        final String prop = header + propName;
        log.debug("Looking for property " + prop);
        
        return appProperties.getProperty(prop);
    }
    
    public static Properties getProperties()
    {
    	return appProperties;
    }

    public String getName() {
        if(name == null) {
            return this.toString().toLowerCase(Locale.getDefault());
        }
        return name;
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    public static String getShortName() {
        String env;
        Environment curEnv = Environment.getCurrent();
        switch(curEnv) {
            case DEVELOPMENT:
                env = "dev"; break;
            case TEST:
                env = "itg"; break;
            case PRODUCTION:
                env = "pro"; break;
            case CUSTOM:
            default:
                env = curEnv.getName(); break;
        }
        return env;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }
}
