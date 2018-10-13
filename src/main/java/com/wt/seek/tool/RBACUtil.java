package com.wt.seek.tool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wt.seek.entity.Login;
import com.wt.seek.entity.Menu;
import com.wt.seek.entity.Permission;
import com.wt.seek.entity.Role;

/**
 * RBAC --> role based access control 基于角色的权限管理
 * @author Daryl
 */
public class RBACUtil {
	
	public static List<Role> getRoles(Login user){
		return null == user? null : user.getRoles();
	}
	
	public static List<Permission> getPermissions(Login user){
		if(null == user) return null;
		List<Role> roles = RBACUtil.getRoles(user);
		if(null == roles) return null;
		
		List<Permission> permissions = new ArrayList<Permission>();
		for(Role role : roles) {
			permissions.addAll(role.getPermissions());
		}
		return permissions;
	}
	
	public static boolean hasPermission(List<Permission> permissions, Permission permission) {
		if(null == permission ) return false;
		for(Permission temp : permissions) {
			if(ifPermissionURLEquals(temp,permission))
				return true;
		}
		return false;
	}
	
	public static boolean hasPermission(List<Permission> permissions, String url) {
		if(null == url ) return false;
		Permission permission = new Permission();
		permission.setUrl(url);
		return RBACUtil.hasPermission(permissions, permission);
	}
	
	public static Set<Menu> getMenus(Login user){
		if(null == user) return null;
		List<Role> roles = RBACUtil.getRoles(user);
		if(null == roles) return null;
		
		Set<Menu> menus = new HashSet<Menu>();
		for(Role role : roles) {
			menus.addAll(role.getMenus());
		}
		return menus;
	}

	/**
	 * 
	 * @param permission eg: /detail/back/detail/{id}
	 * @param anotherPermission /detail/back/detail/5
	 * @return 返回true
	 */
	private static boolean ifPermissionURLEquals(Permission permission,Permission anotherPermission) {
		boolean flag = permission.equals(anotherPermission);
		if(flag) //相同,直接返回true
			return flag;
		else {//不相同,判断除去参数部分是否相同
			String url1 = new String(permission.getUrl());//{id}
			String pattern1 = "(?<=\\{)[^}]*(?=\\})";
			Pattern regex1 = Pattern.compile(pattern1);
			Matcher matches1 = regex1.matcher(url1);
			
			String url2 = new String(anotherPermission.getUrl());//5
			String pattern2 = "(\\d+)";
			Pattern regex2 = Pattern.compile(pattern2);
			Matcher matches2 = regex2.matcher(url2);
			
			if(matches1.find() && matches2.find()) {//同时存在动态参数
				String res ="{" + matches1.group(0)+ "}";
				url1 = url1.replace(res, "");
				url2 = url2.replace(matches2.group(1), "");
				return url1.equals(url2);
			}
			return false;
		}
	}
}
