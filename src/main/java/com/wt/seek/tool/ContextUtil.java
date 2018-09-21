package com.wt.seek.tool;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

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

}
