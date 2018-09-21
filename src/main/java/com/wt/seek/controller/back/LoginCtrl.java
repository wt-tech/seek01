package com.wt.seek.controller.back;

import java.util.Map;

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
			// 放入session
			session.setAttribute(Constants.USER_SESSION, user);
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

}
