package com.crossover.techtrial.util;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.crossover.techtrial.conf.PropertyConfig;
import com.crossover.techtrial.util.CommonConstants;
import com.crossover.techtrial.util.Environment;

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
//	@Value("#{appProperties['com.xlspaceship.util.PropertyConfig']}")
	@Value("{appProperties}")
    Properties appProperties;

    @Value("#{systemProperties}")
    Properties systemProperties;
    
    @Value("#{systemProperties['xlspaceship.env']}")
    private String env;
    
    @Value("#{systemProperties['log.dir']}")
    private String logDir;
    
    @Value("${shot_attemps}")
    private String shot_attemps;
    
    @Value("${hack_shot_attemps}")
    private String hack_shot_attemps;
    
    @Value("${random_shots}")
    private String random_shots;
    
    @Value("${grid_rows}")
    private String grid_rows;
    
    @Value("${grid_columns}")
    private String grid_columns;
    
    @Value("${game_id}")
    private String game_id;

    @Value("${id}")
    private String id;
    
    @Value("${localPlayer}")
    private String localPlayer;
    
    @Value("${hostname}")
    private String hostname;
    
    @Value("${port}")
    private String port;
    
    @Value("${bullet}")
    private String bullet;
    
    @Value("${winger}")
    private String winger;
    
    @Value("${angle}")
    private String angle;
    
    @Value("${a_class}")
    private String a_class;
    
    @Value("${b_class}")
    private String b_class;
    
    @Value("${s_class}")
    private String s_class;
    
	@Value("${useDBType}")
	private String schemaType;
	
    @Value("${${xlspaceship.env}.XLSpaceShip.RequestSvc}")
    private String requestSvc;
	
	@Value("${JavaProcessBuilder.HeapMemory}")
	private String JavaProcessHeapMemory;
	
	@Value("${JavaProcessBuilder.NonHeapMemory}")
	private String JavaProcessNonHeapMemory;

    @Value("${winger_disable}")
	private String winger_disable;

    @Value("${angle_disable}")
	private String angle_disable;

    @Value("${a_class_disable}")
	private String a_class_disable;

    @Value("${b_class_disable}")
	private String b_class_disable;

    @Value("${s_class_disable}")
	private String s_class_disable;
	
    public Bootstrap() { }
    
    public void afterPropertiesSet() throws Exception {
        
        if (appProperties == null) {
            log.error("The properties was not found, cannot bootstrap!!");
            
        }
        
//        this.ctx.getBean(StdOutErrLog.class).tieSystemOutAndErrToLog();

        CommonConstants.ENV = env;
        CommonConstants.SHOT_ATTEMPS = Integer.parseInt(shot_attemps);
        CommonConstants.HACK_SHOT_ATTEMPS = Boolean.valueOf(hack_shot_attemps);
        CommonConstants.RANDOM_SHOTS = Boolean.valueOf(random_shots);
        CommonConstants.GRID_ROWS = Integer.parseInt(grid_rows);
        CommonConstants.GRID_COLUMNS = Integer.parseInt(grid_columns);
        CommonConstants.GAME_ID = game_id;
        CommonConstants.PLAYER_ID = id;
        CommonConstants.PLAYER = localPlayer;
        CommonConstants.HOSTNAME = hostname;
        CommonConstants.PORT = port;
        CommonConstants.BULLET = bullet.charAt(0);
        CommonConstants.WINGER = winger.charAt(0);
        CommonConstants.ANGLE = angle.charAt(0);
        CommonConstants.A_CLASS = a_class.charAt(0);
        CommonConstants.B_CLASS = b_class.charAt(0);
        CommonConstants.S_CLASS = s_class.charAt(0);
        CommonConstants.WINGER_DISABLE = Boolean.valueOf(winger_disable);
        CommonConstants.ANGLE_DISABLE = Boolean.valueOf(angle_disable);
        CommonConstants.A_CLASS_DISABLE = Boolean.valueOf(a_class_disable);
        CommonConstants.B_CLASS_DISABLE = Boolean.valueOf(b_class_disable);
        CommonConstants.S_CLASS_DISABLE = Boolean.valueOf(s_class_disable);
        CommonConstants.SCHEMA_TYPE = schemaType;
        
        CommonConstants.REQUEST_SVC = requestSvc;
        
        CommonConstants.JAVAPROCESS_HEAPMEMORY = JavaProcessHeapMemory;
        CommonConstants.JAVAPROCESS_NONHEAPMEMORY = JavaProcessNonHeapMemory;
        
        log.info("SchemaType is set to: " + schemaType);
        
        log.info("Bootstrapping environment : " + Environment.getShortName());
        log.info("Log directory : " + logDir);

        Environment.setProperties(appProperties);
        log.info("App properties set.");
        
        log.info(appProperties.getProperty("xlspaceship.env"));
        log.info(appProperties.getProperty("XLSpaceShip.RequestSvc"));
        log.debug(appProperties.getProperty("XLSpaceShip.RequestSvc"));
        
        log.info("Bootstrapped XLSpaceShip Java...");
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