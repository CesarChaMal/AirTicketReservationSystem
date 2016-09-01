package com.crossover.techtrial.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Configuration
@Component("bootstrap")
@DependsOn("commonConstants")
public class Bootstrap implements InitializingBean,
        ApplicationContextAware, ApplicationListener<ApplicationEvent> {

    private static final Logger log = Logger.getLogger(Bootstrap.class);

    @Autowired
    ApplicationContext ctx;

    public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}

//	@Value("{classpath:app.properties}")
	@Value("{appProperties}")
    Properties appProperties;

    @Value("#{systemProperties}")
    Properties systemProperties;
    
    @Value("#{systemProperties['airticket.env']}")
    private String env;
    
    @Value("#{systemProperties['log.dir']}")
    private String logDir;
    
	@Value("${useDBType}")
	private String schemaType;
	
    @Value("${${airticket.env}.AirTicket.CustomerServiceAgentSvc}")
    private String customerServiceAgentSvc;
	
	@Value("${JavaProcessBuilder.HeapMemory}")
	private String JavaProcessHeapMemory;
	
	@Value("${JavaProcessBuilder.NonHeapMemory}")
	private String JavaProcessNonHeapMemory;

    public Bootstrap() { }
    
    public void afterPropertiesSet() throws Exception {
        
        if (appProperties == null) {
            log.error("The properties was not found, cannot bootstrap!!");
            
        }
        
//        this.ctx.getBean(StdOutErrLog.class).tieSystemOutAndErrToLog();

        CommonConstants.ENV = env;
        CommonConstants.SCHEMA_TYPE = schemaType;
        
        CommonConstants.CUSTOMER_SERVICE_AGENT_SVC = customerServiceAgentSvc;
        
        CommonConstants.JAVAPROCESS_HEAPMEMORY = JavaProcessHeapMemory;
        CommonConstants.JAVAPROCESS_NONHEAPMEMORY = JavaProcessNonHeapMemory;
        
        log.info("SchemaType is set to: " + schemaType);
        
        log.info("Bootstrapping environment : " + Environment.getShortName());
        log.info("Log directory : " + logDir);

        Environment.setProperties(appProperties);
        log.info("App properties set.");
        
        log.info(appProperties.getProperty("airticket.env"));
        log.info(appProperties.getProperty("AirTicket.WelcomeCtrl"));
        log.debug(appProperties.getProperty("AirTicket.WelcomeCtrl"));
        
        log.info("Bootstrapped Air Ticket Reservation System Java...");
    }

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.ctx = context;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        log.debug("ApplicationEvent::" + event.getClass().getName());

        if (event instanceof ContextRefreshedEvent) {
            log.debug("ContextRefreshedEvent received...");
            
            // we would want to fire an event to start various things
        }
    }

}