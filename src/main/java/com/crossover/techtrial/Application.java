package com.crossover.techtrial;

import org.apache.catalina.connector.Connector;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
//@EnableAutoConfiguration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

	private static final Logger log = Logger.getLogger(Application.class);

	public static void main(String[] args) {
//		ApplicationContext context = SpringApplication.run(Application.class, args);
		SpringApplication.run(Application.class, args);
//		Bootstrap bootstrap = context.getBean("bootstrap", Bootstrap.class);
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {
				//connector.setPort(8080);
				// connector.setAsyncTimeout(60000/2); // 30 segundos
				// connector.setAsyncTimeout(60000); // 1 min
				connector.setAsyncTimeout(60000 * 2); // 2 min
			}
		});
		return factory;
	}
}
