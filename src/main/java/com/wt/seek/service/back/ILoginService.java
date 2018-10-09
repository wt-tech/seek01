package com.wt.seek.service.back;

import java.util.List;

import com.wt.seek.entity.Login;

public interface ILoginService {

	public Login getLoginUser(String userCode, String userPassword) throws Exception;

	public boolean updatePwd(Login login) throws Exception;
	
	/**
	 * 获取该用户的所有角色权限信息
	 * @param userCode
	 * @return
	 */
	public Login getAllPermissionByUserCode(String userCode);
	
	/**
	 * 获取系统中所有用户信息
	 * @return
	 */
	public List<Login> listAllUsers(Integer currentPageNo,Integer pageSize);
}
