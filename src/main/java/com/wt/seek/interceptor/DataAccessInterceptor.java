package com.wt.seek.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wt.seek.entity.Login;
import com.wt.seek.entity.Permission;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.RBACUtil;

@SuppressWarnings({"unchecked","unused"})
public class DataAccessInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LogManager.getLogger();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//检测用户是否登录
//		if(!this.hasLogin(request, response)) 
//			return false;
		//检测用户是否有权限
		return true;// this.hasPermission(request, response);
	}
	
	private boolean hasLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute(Constants.USER_SESSION);
		if (null == user) {//用户未登录 , 跳转到首页
			String url = request.getContextPath() + "/authorization";
			response.setHeader("Location", url);
			response.setStatus(Constants.HTTP_STATUS_302);
			return false;
		}
		return true;		
	}
	
	private boolean hasPermission(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath(); 
		List<Permission> permissions = (List<Permission>) session.getAttribute(Constants.USER_PERMISSIONS);
		if(!RBACUtil.hasPermission(permissions, servletPath)) {
			System.out.println("no permission "+servletPath);
			String url = request.getContextPath() + "/permission";
			response.setHeader("Location", url);
			response.setStatus(Constants.HTTP_STATUS_302);
			return false;
		}
		System.out.println("with permission "+servletPath);
		return true;
	}
	
}
