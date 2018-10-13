package com.wt.seek.service.back;

import java.util.List;

import com.wt.seek.entity.Menu;
import com.wt.seek.entity.Permission;
import com.wt.seek.entity.Role;


/**
 * InBulk-->批量的
 * @author Daryl
 *
 */
public interface IRBACService {

	boolean savePermissionsInBulk();
	
	
	//======================角色菜单权限相关======================
	List<Menu> listAllMenus();
	List<Menu> listMenusByRoleId(Integer roleId);
	boolean removeMenusByRoleId(Integer roelId);
	boolean saveRoleMenusInBulk(Integer roelId,Integer[] menuIds);
	boolean updateRoleMenusInBulk(Integer roelId,Integer[] menuIds);

	
	//======================角色数据权限相关======================
	List<Permission> listAllPermissions();
	List<Permission> listPermissionsByRoleId(Integer roleId);
	boolean removePermissionsByRoleId(Integer roelId);
	boolean saveRolePermissionsInBulk(Integer roelId,Integer[] permissionIds);
	boolean updateRolePermissionsInBulk(Integer roelId,Integer[] permissionIds);
	
	

	//======================用户角色相关======================
	List<Role> listAllRoles();
	List<Role> listRolesByUserId(Integer id);
	boolean removeRolesByUserId(String userCode);
	boolean saveUserRolesInBulk(String userCode,Integer[] roleIds);
	boolean updateUserRolesInBulk(String userCode,Integer[] roleIds);
	

	boolean updatePermissions(Permission permission);
	
	boolean savePermissions(Permission permission);
	
}
