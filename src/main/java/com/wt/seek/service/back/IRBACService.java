package com.wt.seek.service.back;

import java.util.List;

import com.wt.seek.entity.Menu;
import com.wt.seek.entity.Permission;
import com.wt.seek.entity.Role;

public interface IRBACService {

	List<Role> listAllRoles();
	
	List<Permission> listAllPermissions();
	
	/**
	 * 批量添加新的权限
	 * @return
	 */
	boolean savePermissionsInBulk();
	
	/**
	 * 根据角色查询可见菜单
	 * @param roleId
	 * @return
	 */
	List<Menu> listMenusByRoleId(Integer roleId);
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Menu> listAllMenus();
	
}
