package com.hp.useit.conf;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.hp.pdeit.useit.dao.CALDAPGroupUserDao;

/**
 * Responsible for the DB related operations regarding UseIT.
 * @author 
 *
 */

@Configuration
public class SQLServerConfig {
    private static final Logger log = Logger.getLogger(SQLServerConfig.class);

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
    
    @Value("${${useitjava.env}.sqlServerDS.driverClassName}")
    private String sqlServerDriver;
    @Value("${${useitjava.env}.sqlServerDS.url}")
    private String sqlServerURL;
    @Value("${${useitjava.env}.sqlServerDS.username}")
    private String sqlServerUsername;
    @Value("${${useitjava.env}.sqlServerDS.password}")
    private String sqlServerPassword;
    

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSourceSQL() {
        // create a basic dbcp DataSource...
        BasicDataSource ds = new BasicDataSource();
        
        // JDBC specific settings
        ds.setDriverClassName(sqlServerDriver);
        ds.setUrl(sqlServerURL);
        ds.setUsername(sqlServerUsername);
        ds.setPassword(sqlServerPassword);
        log.debug(sqlServerURL);

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
    
//    public String toString(){
//    	
//		return "MSSQL:"+this.sqlServerDriver + ":"+ this.sqlServerURL;
//    	
//    }
    
}
