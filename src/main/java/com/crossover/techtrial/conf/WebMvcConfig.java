package com.crossover.techtrial.conf;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.crossover.techtrial.Application;
import com.crossover.techtrial.interceptor.LoginInterceptor;

@Configuration
@ComponentScan(basePackages = { "com.crossover.controller" })
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger log = Logger.getLogger(Application.class);

//	@Value("${static.path}")
    @Value("#{systemProperties['static.path']}")
	private String staticPath;
	 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		// registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		if(staticPath != null) {
	        log.info("Serving static content from " + staticPath);
	        registry.addResourceHandler("/**").addResourceLocations("file:" + staticPath);
	    }
	}
	
	 // see https://stackoverflow.com/questions/27381781/java-spring-boot-how-to-map-my-my-app-root-to-index-html
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("redirect:/index.html");
		registry.addViewController("/").setViewName("redirect:/login.do");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(new LogingInterceptor());
	    registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/*.do");
	} 
	
}
