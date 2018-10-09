package com.wt.seek.servimpl.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.back.IRBACMapper;
import com.wt.seek.entity.Menu;
import com.wt.seek.entity.Permission;
import com.wt.seek.entity.Role;
import com.wt.seek.service.back.IRBACService;
import com.wt.seek.tool.BusinessUtils;

@Service
public class RBACServiceImpl implements IRBACService {

	@Autowired
	private IRBACMapper rbacMapper;
	
	@Override
	public List<Role> listAllRoles() {
		return rbacMapper.listAllRoles();
	}

	@Override
	public List<Permission> listAllPermissions() {
		return rbacMapper.listAllPermissions();
	}

	@Override
	public boolean savePermissionsInBulk() {
		return false;
	}

	@Override
	public List<Menu> listMenusByRoleId(Integer roleId) {
		return rbacMapper.listMenusByRoleId(roleId);
	}

	@Override
	public List<Menu> listAllMenus() {
		return rbacMapper.listAllMenus();
	}

	@Override
	public boolean removeMenusByRoleId(Integer roelId) {
		return rbacMapper.removeMenusByRoleId(roelId);
	}

	@Override
	public boolean updateRoleMenusInBulk(Integer roelId, Integer[] menuIds) {
		boolean flag = this.removeMenusByRoleId(roelId);
		if(menuIds == null || menuIds.length == 0)
			return flag;
		flag = rbacMapper.saveRoleMenusInBulk(roelId, menuIds);
		if(!flag)
			BusinessUtils.throwNewBusinessException("菜单权限修改失败,请重试");
		return flag;
	}

	@Override
	public boolean saveRoleMenusInBulk(Integer roelId, Integer[] menuIds) {
		if(menuIds == null || menuIds.length == 0)
			return true;
		return rbacMapper.saveRoleMenusInBulk(roelId, menuIds);
	}

	
	@Override
	public List<Menu> listPermissionsByRoleId(Integer roleId) {
		return rbacMapper.listPermissionsByRoleId(roleId);
	}

	@Override
	public boolean removePermissionsByRoleId(Integer roelId) {
		return rbacMapper.removePermissionsByRoleId(roelId);
	}

	@Override
	public boolean saveRolePermissionsInBulk(Integer roelId, Integer[] permissionIds) {
		if(permissionIds == null || permissionIds.length == 0)
			return true;
		return rbacMapper.saveRolePermissionsInBulk(roelId, permissionIds);
	}

	@Override
	public boolean updateRolePermissionsInBulk(Integer roelId, Integer[] permissionIds) {
		boolean flag = rbacMapper.removePermissionsByRoleId(roelId);
		if(permissionIds == null || permissionIds.length == 0)
			return flag;
		flag = rbacMapper.saveRolePermissionsInBulk(roelId, permissionIds);
		if(!flag)
			BusinessUtils.throwNewBusinessException("菜单权限修改失败,请重试");
		return flag;
	}

	@Override
	public List<Menu> listRolesByUserId(String userCode) {
		return rbacMapper.listRolesByUserId(userCode);
	}

	@Override
	public boolean removeRolesByUserId(String userCode) {
		return rbacMapper.removeRolesByUserId(userCode);
	}

	@Override
	public boolean saveUserRolesInBulk(String userCode, Integer[] roleIds) {
		if(roleIds == null || roleIds.length == 0)
			return true;
		return rbacMapper.saveUserRolesInBulk(userCode, roleIds);
	}

	@Override
	public boolean updateUserRolesInBulk(String userCode, Integer[] roleIds) {
		boolean flag = rbacMapper.removeRolesByUserId(userCode);
		if(roleIds == null || roleIds.length == 0)
			return flag;
		flag = rbacMapper.saveUserRolesInBulk(userCode, roleIds);
		if(!flag)
			BusinessUtils.throwNewBusinessException("用户角色修改失败,请重试");
		return flag;
	}

}
