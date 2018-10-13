package com.wt.seek.controller.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wt.seek.entity.Permission;
import com.wt.seek.service.back.IRBACService;
import com.wt.seek.tool.BackPage;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

/**
 * 后台所有的角色,权限管理都在这里
 * @author Daryl
 */
@RestController
public class RBACtrl {

	@Autowired
	private IRBACService rbacServiceImpl;
	
	//==========================该请求是内部重定向请求===============================
	@RequestMapping("permission")
	public Map<String, Object> permissionDenied() {

		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.TIPS, "对不起,您没有权限!");
		return map;
	}
	
	//===========================查询后台所有的URI====================================
	@SuppressWarnings("unchecked")
	@RequestMapping(value="back/uri/permissions",method=RequestMethod.GET)
	public Map<String, Object> listBackendAllPermissionsURI(HttpSession session,
			HttpServletRequest request,@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = null;
		List<Object> allPermissionList = (List<Object>) session.getAttribute(Constants.ALL_PERMISSIONS);
		if(null == allPermissionList) {
			allPermissionList = new ArrayList<Object>(ContextUtil.getAllPermission(rbacServiceImpl.listAllPermissions(), request));
			session.setAttribute(Constants.ALL_PERMISSIONS, allPermissionList);
		}
		Integer currentPageNos = new PageUtil().Page(allPermissionList.size(), currentPageNo, Constants.pageSizes);
		map = BackPage.commonPage(allPermissionList, currentPageNos, allPermissionList.size(), Constants.pageSizes,"permissions");
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("allPermissionLength", allPermissionList.size());
		return map;
	}
	//=======================获取当前用户可见的菜单项=========================
	@RequestMapping(value="back/user/menus",method=RequestMethod.GET)
	public Map<String, Object> listCurrentUserMenus(HttpSession session) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put(Constants.USER_MENUS,session.getAttribute(Constants.USER_MENUS));
		return map;
	}
	
	//=========================角色菜单权限相关================================
	@RequestMapping(value="back/menus",method=RequestMethod.GET)
	public Map<String, Object> listAllMenus(HttpSession session) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("menus",rbacServiceImpl.listAllMenus());
		return map;
	}
	
	@RequestMapping(value="back/role/{id}/menus",method=RequestMethod.GET)
	public Map<String, Object> listMenusByRoleId(@PathVariable(value="id",required=true) Integer roleId) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("menus",rbacServiceImpl.listMenusByRoleId(roleId));
		return map;
	}
	
	@RequestMapping(value="back/role/menus",method=RequestMethod.PUT)
	public Map<String, Object> updateRoleMenusInBulk(@RequestBody() Object obj){
		JSONObject jsonObj = (JSONObject)obj;
		Integer roleId = jsonObj.getInteger("roleId");
		Integer[] menuIds = jsonObj.getObject("menuIds", Integer[].class);
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, rbacServiceImpl.updateRoleMenusInBulk(roleId, menuIds)?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
	
	//=========================角色数据权限相关==============================
	@RequestMapping(value="back/permissions",method=RequestMethod.GET)
	public Map<String, Object> listAllPermissions() {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("permissions",rbacServiceImpl.listAllPermissions());
		return map;
	}
	@RequestMapping(value="back/role/{id}/permissions",method=RequestMethod.GET)
	public Map<String, Object> listPermissionsByRoleId(@PathVariable(value="id",required=true) Integer roleId) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("permissions",rbacServiceImpl.listPermissionsByRoleId(roleId));
		return map;
	}
	@RequestMapping(value="back/role/permissions",method=RequestMethod.PUT)
	public Map<String, Object> updateRolePermissionsInBulk(@RequestBody() Object obj){
		JSONObject jsonObj = (JSONObject)obj;
		Integer roleId = jsonObj.getInteger("roleId");
		Integer[] permissionIds = jsonObj.getObject("permissionIds", Integer[].class);
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, rbacServiceImpl.updateRolePermissionsInBulk(roleId, permissionIds)?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
	
	//=====================用户角色管理相关=============================
	@RequestMapping(value="back/roles",method=RequestMethod.GET)
	public Map<String, Object> listRoles() {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("roles",rbacServiceImpl.listAllRoles());
		return map;
	}
	@RequestMapping(value="back/user/{id}/roles",method=RequestMethod.GET)
	public Map<String, Object> listRolesByUserId(@PathVariable(value="id",required=true) Integer id) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("roles",rbacServiceImpl.listRolesByUserId(id));
		return map;
	}
	

	@RequestMapping(value="back/user/roles",method=RequestMethod.PUT)
	public Map<String, Object> updateUserRolesInBulk(@RequestBody() Object obj) {
		JSONObject jsonObj = (JSONObject)obj;
		String userCode = jsonObj.getString("userCode");
		Integer[] roleIds = jsonObj.getObject("roleIds", Integer[].class);
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, rbacServiceImpl.updateUserRolesInBulk(userCode, roleIds)?Constants.SUCCESS:Constants.FAIL);
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
