package com.crossover.techtrial.conf;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.crossover.techtrial.filter.LoginRequiredFilter;

@Configuration
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new LoginRequiredFilter()};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
}