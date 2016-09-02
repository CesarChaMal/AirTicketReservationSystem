package com.crossover.techtrial.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.crossover.techtrial.filter.LoginRequiredFilter;

public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger log = Logger.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("Interceptor: ");
		log.info(request.getRequestURI());

		String uri = request.getRequestURI();
		
		if(!uri.endsWith("/login.do") && !uri.endsWith("/logout.do"))
//		if(uri.endsWith("/login.do") || uri.endsWith("/logout.do"))
		{
//			User userData = (User) request.getSession().getAttribute("LOGGEDIN_USER");
			String userData = (String) request.getSession().getAttribute("LOGGEDIN_USER");
			if(userData == null)
			{
				response.sendRedirect("login.do");
				return false;
			}   
		}
//		log.info("---Before Method Execution---");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("---method executed---");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("---Request Completed---");
	}
}