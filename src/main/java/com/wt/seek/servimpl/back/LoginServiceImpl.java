package com.wt.seek.servimpl.back;

import java.util.List;

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

	@Override
	public Login getAllPermissionByUserCode(String userCode) {
		return loginMapper.getAllPermissionByUserCode(userCode);
	}

	@Override
	public List<Login> listAllUsers() {
		return loginMapper.listAllUsers();
	}

	@Override
	public boolean saveLoginUser(String userCode, String password, String nickname) {
		return loginMapper.saveLoginUser(userCode, password, nickname);
	}

	@Override
	public boolean checkIfUserCodeAvaliable(String userCode) throws Exception {
		// TODO Auto-generated method stub
		return loginMapper.getLoginUser(userCode) == null;
	}
	


}
