package com.wt.seek.controller.back;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wt.seek.entity.Login;
import com.wt.seek.entity.Volunteer;
import com.wt.seek.service.back.ILoginService;
import com.wt.seek.service.my.IVolunteerService;
import com.wt.seek.tool.BusinessUtils;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;
import com.wt.seek.tool.RBACUtil;

@RestController()
public class LoginCtrl {
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IVolunteerService volunteerService;
	
	@RequestMapping(value="preflight",method=RequestMethod.GET)
	public Map<String, Object> checkIfUserCodeAvaliable(@RequestParam String username) throws Exception{
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, loginService.checkIfUserCodeAvaliable(username)?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> doLogin(@RequestParam String userCode, @RequestParam String userPassword,
			HttpSession session) throws Exception {

		Map<String, Object> map = MapUtils.getHashMapInstance();
		Login user = loginService.getLoginUser(userCode, userPassword);
		map.put(Constants.STATUS, Constants.FAIL);
		if (null != user) {// 登录成功
			//查询该用户的所有角色权限
			user = loginService.getAllPermissionByUserCode(user.getUserCode());
			// 放入session
			session.setAttribute(Constants.USER_SESSION, user);
			session.setAttribute(Constants.USER_PERMISSIONS, RBACUtil.getPermissions(user));
			session.setAttribute(Constants.USER_MENUS, RBACUtil.getMenus(user));
			map.put(Constants.STATUS, Constants.SUCCESS);
		}else
			BusinessUtils.throwNewBusinessException("账号密码不匹配");
		return map;
	}

	
	@RequestMapping(value = "/logout")
	public Map<String, Object> logout(HttpSession session) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 清除session
		//session.removeAttribute(Constants.USER_SESSION);
		if(session != null) {
			session.invalidate();
		}
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
	public Map<String, Object> listAllUsers(HttpServletResponse response,@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = loginService.countUsers();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo,Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("users", loginService.listAllUsers(currentPageNos,Constants.pageSize));
		map.put("pageSize", Constants.pageSize);
		map.put("totalCount", totalCount);
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
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public Map<String, Object> register(@RequestBody Object obj) {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		if(obj == null) {
			BusinessUtils.throwNewBusinessException("请填写必要信息!");
		}
		JSONObject jsonObj = (JSONObject)obj;
		String ID = jsonObj.getString("idCard");
		String realName = jsonObj.getString("realName");
		
		String userCode = jsonObj.getString("username");
		String password = jsonObj.getString("password");
		String nickname = jsonObj.getString("nickname");
		
		Volunteer volunteer = volunteerService.getVolunteerByIDAndName(ID, realName);
		if(volunteer == null || !Constants.VOLUNTEER_APPROVED.equals(volunteer.getVolResult())) {
			BusinessUtils.throwNewBusinessException("您不是志愿者,无法申请注册");
		}
		
		Login login=new Login();
		login.setUserCode(userCode);
		login.setUserName(nickname);
		login.setUserPassword(password);
		
		int loginId=loginService.saveLoginUser(login);
		
		if(loginId>0) {
			map.put(Constants.STATUS,loginService.saveLoginAndVolunteer(loginId, volunteer.getId()) ?Constants.SUCCESS:Constants.FAIL);
		}
		return map;
	}
	
	
}
