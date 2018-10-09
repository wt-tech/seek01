package com.wt.seek.controller.back;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wt.seek.entity.Permission;
import com.wt.seek.service.back.IRBACService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;

/**
 * 后台所有的角色,权限管理都在这里
 * @author Daryl
 */
@RestController
public class RBACtrl {

	@Autowired
	private IRBACService rbacServiceImpl;
	
	@RequestMapping("permission")
	public Map<String, Object> permissionDenied() {

		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.TIPS, "对不起,您没有权限!");
		return map;
	}
	
	@RequestMapping(value="back/menus",method=RequestMethod.GET)
	public Map<String, Object> listAllMenus(HttpSession session) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("menus",rbacServiceImpl.listAllMenus());
		return map;
	}
	
	@RequestMapping(value="back/user/menus",method=RequestMethod.GET)
	public Map<String, Object> listCurrentUserMenus(HttpSession session) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put(Constants.USER_MENUS,session.getAttribute(Constants.USER_MENUS));
		return map;
	}
	
	@RequestMapping(value="back/role/{id}/menus",method=RequestMethod.GET)
	public Map<String, Object> listMenusByRoleId(@PathVariable(value="id",required=true) Integer roleId) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("menus",rbacServiceImpl.listMenusByRoleId(roleId));
		return map;
	}
	
	@RequestMapping(value="back/roles",method=RequestMethod.GET)
	public Map<String, Object> listRoles() {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("roles",rbacServiceImpl.listAllRoles());
		return map;
	}
	
	@RequestMapping(value="back/permissions",method=RequestMethod.GET)
	public Map<String, Object> listPermissions(HttpServletRequest request) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("permissions",ContextUtil.getAllPermission(rbacServiceImpl.listAllPermissions(), request));
		return map;
	}
	
	@RequestMapping("back/updatepermission")
	public Map<String, Object> updatePermissions(Permission permission) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag=rbacServiceImpl.updatePermissions(permission);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}
	
	@RequestMapping("back/savepermission")
	public Map<String, Object> savePermissions(Permission permission) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag=rbacServiceImpl.savePermissions(permission);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		map.put("id", permission.getId());
		return map;
	}
}
