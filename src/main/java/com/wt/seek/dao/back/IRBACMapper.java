package com.wt.seek.dao.back;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Menu;
import com.wt.seek.entity.Permission;
import com.wt.seek.entity.Role;

public interface IRBACMapper {

	List<Role> listAllRoles();
	
	List<Permission> listAllPermissions();
	
	boolean savePermissionsInBulk();
	
	List<Menu> listMenusByRoleId(@Param("roleId") Integer roleId);
	
	List<Menu> listAllMenus();
<<<<<<< HEAD
	
	/**
	 * 修改说明
	 * @param permission
	 * @return
	 */
	Integer updatePermissions(Permission permission);
	/**
	 * 
	 * @param permission
	 * @return
	 */
	Integer savePermissions(Permission permission);
=======
>>>>>>> bf7e92491e800526aea0d3629f6646b829f08133
}
