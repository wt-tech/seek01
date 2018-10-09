package com.wt.seek.controller.back;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.seek.entity.Login;
import com.wt.seek.service.back.ILoginService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.RBACUtil;

@RestController()
public class LoginCtrl {
	@Autowired
	private ILoginService loginService;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> doLogin(@RequestParam String userCode, @RequestParam String userPassword,
			HttpSession session) throws Exception {

		Map<String, Object> map = MapUtils.getHashMapInstance();
		Login user = loginService.getLoginUser(userCode, userPassword);
		map.put(Constants.STATUS, Constants.FAIL);
		if (null != user) {// 登录成功
			// 查询该用户的所有角色权限
			user = loginService.getAllPermissionByUserCode(user.getUserCode());
			// 放入session
			session.setAttribute(Constants.USER_SESSION, user);
			session.setAttribute(Constants.USER_PERMISSIONS, RBACUtil.getPermissions(user));
			session.setAttribute(Constants.USER_MENUS, RBACUtil.getMenus(user));
			map.put(Constants.STATUS, Constants.SUCCESS);
		}
		return map;
	}

	@RequestMapping(value = "/logout")
	public Map<String, Object> logout(HttpSession session) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 清除session
		session.removeAttribute(Constants.USER_SESSION);
		map.put(Constants.STATUS, Constants.SUCCESS);
		return map;
	}

	@RequestMapping("authorization")
	public Map<String, Object> accessDenied(HttpServletResponse response) {

		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.TIPS, "请先登录!");
		return map;
	}

	@RequestMapping("back/logins")
	public Map<String, Object> listAllUsers(HttpServletResponse response,@RequestParam("currentPageNo") Integer currentPageNo) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("users", loginService.listAllUsers(currentPageNo,Constants.pageSize));
		return map;
	}

	@RequestMapping("back/updatepwd")
	public Map<String, Object> updatePwd(@RequestParam("userPassword") String userPassword,HttpServletRequest request) throws Exception {
		Object o = request.getSession().getAttribute(Constants.USER_SESSION);
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String userCode = ((Login) o).getUserCode();
		System.err.println(userCode);
		Login login=new Login();
		login.setUserCode(userCode);
		login.setUserPassword(userPassword);
		boolean flag = loginService.updatePwd(login);
		if (flag) {
			map.put(Constants.STATUS, Constants.SUCCESS);
			request.getSession().removeAttribute(Constants.USER_SESSION);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}
}
