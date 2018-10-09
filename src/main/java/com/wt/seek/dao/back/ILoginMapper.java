package com.wt.seek.dao.back;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Login;

public interface ILoginMapper {
	/**
	 * 通过userCode获取User
	 * 
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public Login getLoginUser(@Param("userCode") String userCode) throws Exception;

	/**
	 * 更新密码
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(Login login) throws Exception;
	
	public Login getAllPermissionByUserCode(@Param("userCode") String userCode);
	
<<<<<<< HEAD
	public List<Login> listAllUsers(@Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize);
=======
	public List<Login> listAllUsers();
>>>>>>> bf7e92491e800526aea0d3629f6646b829f08133
}
