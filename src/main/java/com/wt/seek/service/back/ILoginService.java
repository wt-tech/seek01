package com.wt.seek.service.back;

import com.wt.seek.entity.Login;

public interface ILoginService {

	public Login getLoginUser(String userCode, String userPassword) throws Exception;

	public boolean updatePwd(Login login) throws Exception;
}
