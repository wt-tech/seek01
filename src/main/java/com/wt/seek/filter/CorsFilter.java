package com.wt.seek.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CorsFilter implements Filter{

	private Logger logger = LogManager.getLogger();
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest httprequest = (HttpServletRequest)request;
		
		Enumeration<String> headers = httprequest.getHeaderNames();
		System.err.println("=======================请求头=======================");
		while(headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + ":" + httprequest.getHeader(header)); 
		}
		
		
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		chain.doFilter(request, httpresponse);
		Collection<String> headers1 = httpresponse.getHeaderNames();
		System.err.println("=======================响应头=======================");
		for(String header : headers1) {
			System.out.println(header + ":" + httpresponse.getHeader(header)); 
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		logger.error("filterName : " + filterConfig.getFilterName());
		logger.error("=============filterConfig==initParameterNames==============");
		Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
		while(initParameterNames.hasMoreElements()) {
			String param = initParameterNames.nextElement();
			logger.error(param + ":" + filterConfig.getInitParameter(param));
		}
		ServletContext servletContext = filterConfig.getServletContext();
		logger.error("servletContext.getEffectiveMajorVersion() : " + servletContext.getEffectiveMajorVersion());
		logger.error("servletContext.getEffectiveMinorVersion() : " + servletContext.getEffectiveMinorVersion());
		
		logger.error("=============servletContext==attributeNames==============");
		Enumeration<String> attributeNames = servletContext.getAttributeNames();
		while(attributeNames.hasMoreElements()) {
			String param = attributeNames.nextElement();
			logger.error(param + ":" + servletContext.getAttribute(param));
		}
		
		logger.error("=============servletContext==initParameterNames==============");
		initParameterNames = servletContext.getInitParameterNames();
		while(initParameterNames.hasMoreElements()) {
			String param = initParameterNames.nextElement();
			logger.error(param + ":" + servletContext.getInitParameter(param));
		}
	}

}
