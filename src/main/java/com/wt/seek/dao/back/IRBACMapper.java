package com.wt.seek.dao.back;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Menu;
import com.wt.seek.entity.Permission;
import com.wt.seek.entity.Role;

public interface IRBACMapper {
	

	boolean savePermissionsInBulk();
	
	
	List<Menu> listAllMenus();
	List<Permission> listAllPermissions();
	List<Role> listAllRoles();
	
	
	List<Menu> listMenusByRoleId(@Param("roleId") Integer roleId);
	List<Menu> listPermissionsByRoleId(@Param("roleId") Integer roleId);
	List<Menu> listRolesByUserId(@Param("userCode") String userCode);
	
	
	boolean removeMenusByRoleId(@Param("roleId") Integer roelId);
	boolean removePermissionsByRoleId(@Param("roleId") Integer roelId);
	boolean removeRolesByUserId(@Param("userCode") String userCode);
	
	boolean saveRoleMenusInBulk(@Param("roleId")Integer roelId, @Param("menuIds") Integer[] menuIds);
	boolean saveRolePermissionsInBulk(@Param("roleId")Integer roelId, @Param("permissionIds") Integer[] permissionIds);
	boolean saveUserRolesInBulk(@Param("userCode")String userCode, @Param("roleIds") Integer[] roleIds);
}
