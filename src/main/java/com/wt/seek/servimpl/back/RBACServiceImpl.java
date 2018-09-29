package com.wt.seek.servimpl.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.back.IRBACMapper;
import com.wt.seek.entity.Menu;
import com.wt.seek.entity.Permission;
import com.wt.seek.entity.Role;
import com.wt.seek.service.back.IRBACService;

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

}
