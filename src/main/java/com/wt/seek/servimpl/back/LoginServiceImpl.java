package com.wt.seek.servimpl.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.back.ILoginMapper;
import com.wt.seek.entity.Login;
import com.wt.seek.service.back.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private ILoginMapper loginMapper;

	@Override
	public Login getLoginUser(String userCode, String userPassword) throws Exception {
		// TODO Auto-generated method stub
		Login login = null;
		login = loginMapper.getLoginUser(userCode);
		// 匹配密码
		if (null != login) {
			if (!login.getUserPassword().equals(userPassword))
				login = null;
		}
		return login;
	}

	@Override
	public boolean updatePwd(Login login) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = loginMapper.updatePwd(login);
		if (num > 0) {
			flag = true;
		}
		return flag;
	}

}
