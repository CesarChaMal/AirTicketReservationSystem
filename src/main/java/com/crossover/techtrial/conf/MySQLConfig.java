package com.crossover.techtrial.conf;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class MySQLConfig {
    private static final Logger log = Logger.getLogger(MySQLConfig.class);

    @Value("${dbcp.initialSize}")
    private int initialSize;
    @Value("${dbcp.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${dbcp.maxActive}")
    private int maxActive;
    @Value("${dbcp.maxWait}")
    private int maxWait;
    @Value("${dbcp.maxIdle}")
    private int maxIdle;
    
    @Value("${${airticket.env}.mysqlDS.driverClassName}")
    private String mysqlDriver;
    @Value("${${airticket.env}.mysqlDS.url}")
    private String mysqlURL;
    @Value("${${airticket.env}.mysqlDS.username}")
    private String mysqlUsername;
    @Value("${${airticket.env}.mysqlDS.password}")
    private String sqlServerPassword;
    

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSourceSQL() {
        // create a basic dbcp DataSource...
        BasicDataSource ds = new BasicDataSource();
        
        // JDBC specific settings
        ds.setDriverClassName(mysqlDriver);
        ds.setUrl(mysqlURL);
        ds.setUsername(mysqlUsername);
        ds.setPassword(sqlServerPassword);
        log.debug(mysqlURL);

        // DBCP specific settings
        ds.setInitialSize(initialSize);
        ds.setPoolPreparedStatements(poolPreparedStatements);
        ds.setMaxActive(maxActive);
        ds.setMaxWait(maxWait);
        ds.setMaxIdle(maxIdle);

        return ds;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate(dataSourceSQL());
        return template;
    }

    @Bean 
    public PlatformTransactionManager transactionManager() {
        PlatformTransactionManager transactionManager = 
            new DataSourceTransactionManager(dataSourceSQL());
        return transactionManager; 
    }
    
    public String toString(){
		return "MYSQL:"+this.mysqlDriver + ":"+ this.mysqlURL;
    }
    
}
