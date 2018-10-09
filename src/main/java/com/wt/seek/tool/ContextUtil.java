package com.wt.seek.tool;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.wt.seek.entity.Permission;

public class ContextUtil {


	private static final String getContextRealPath(HttpServletRequest request) {
		if (request != null)
			return request.getSession().getServletContext().getRealPath(File.separator);
		return null;
	}

	/**
	 * 获取静态资源(就是指图片)存储的绝对路径 eg:<br/>
	 * C:\Apache TomCat\apache-tomcat-7.0.88\webapps\statics
	 * 
	 * @param request
	 *            http请求
	 * @return
	 */
	public static final String getStaticResourceAbsolutePath(HttpServletRequest request) {

		String contextPath = getContextRealPath(request);

		if (contextPath != null) {
			return contextPath.replace(Constants.ContextPath, Constants.IMGBASEPATH);
		}
		return null;
	}

	public static final String getClientIpAddress(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取后台管理所有的uri,并封装成Permission
	 * @param addedPermissions 已经保存在数据库中的permission记录
	 * @param request
	 * @return
	 */
	public static Set<Permission> getAllPermission(List<Permission> addedPermissions,HttpServletRequest request){
		Set<Permission> totalPermissions = new HashSet<Permission>();
		WebApplicationContext webCtx = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RequestMappingHandlerMapping handlerMapping = webCtx.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
		for(RequestMappingInfo mappingInfo : handlerMethods.keySet()) {
			PatternsRequestCondition requestCondition = mappingInfo.getPatternsCondition();
            Set<String> uris = requestCondition.getPatterns();
            for(String uri : uris) {
            	if(isBackEndUri(uri)) {
            		totalPermissions.add(new Permission(uri));
            	}
            }
		}
		totalPermissions.addAll(addedPermissions);
		return totalPermissions;
	}
	private static boolean isBackEndUri(String uri) {
		return null == uri ? false : uri.contains("back");
	}
}
