package com.wt.seek.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		httpresponse.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		httpresponse.setHeader("Access-Control-Allow-Credentials","true"); 
		Collection<String> headers = httpresponse.getHeaderNames();
		
		chain.doFilter(request, httpresponse);
//		Iterator<String> it = headers.iterator();
//		
//		while(it.hasNext()) {
//			String header =  it.next();
//			System.err.print(header+":");
//			System.err.println(httpresponse.getHeader(header));
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
