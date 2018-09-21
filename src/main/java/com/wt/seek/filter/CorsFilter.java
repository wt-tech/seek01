package com.wt.seek.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wt.seek.tool.ContextUtil;

public class CorsFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		getSchemaIpAndPort(request);
		httpresponse.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		httpresponse.setHeader("Access-Control-Allow-Credentials","true"); 
		httpresponse.setHeader("Access-Control-Allow-Methods","GET,POST,DELETE,PUT"); 
		httpresponse.setHeader("Access-Control-Allow-Headers","Content-Type"); 

		httpresponse.setHeader("Access-Control-Allow-Methods","GET,POST,DELETE,PUT"); 
		httpresponse.setHeader("Access-Control-Allow-Headers","Content-Type"); 

		chain.doFilter(request, httpresponse);

	}
	
	private String getSchemaIpAndPort(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String ip = ContextUtil.getClientIpAddress(req);
		int port =8848;
		String url = req.getRequestURL().toString();
		
		
		
		System.err.println("http://127.0.0.1:8848");
		System.err.println(ip);
		System.err.println(port);
		System.err.println(url);
		return "http://"+ip+":"+port;
	}
	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
