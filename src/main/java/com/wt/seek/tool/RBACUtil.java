package com.wt.seek.tool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			if(permission.equals(temp))
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
}
