package com.crossover.techtrial.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.crossover.techtrial.util.Bootstrap;


@Configuration
@Import( {PropertyConfig.class, MySQLConfig.class} )
//@Import( {PropertyConfig.class} )
public class AppConfig {
    
    @Bean
    public Bootstrap bootstrap() {
        return new Bootstrap();
    }
}