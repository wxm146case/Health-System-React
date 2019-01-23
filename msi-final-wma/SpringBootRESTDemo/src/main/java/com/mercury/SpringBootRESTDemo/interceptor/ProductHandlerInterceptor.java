package com.mercury.SpringBootRESTDemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * HandlerInterceptor vs HandlerInterceptorAdapter
 * the main difference between HandlerInterceptor and HandlerInterceptorAdapter is that 
 * in the first one we need to override all three methods: preHandle(), postHandle() and afterCompletion(), 
 * whereas in the second we may implement only required methods.
 */
@Component
public class ProductHandlerInterceptor extends HandlerInterceptorAdapter {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("User " + request.getRemoteAddr() + " accessed your products.");
	}
	
	
}
