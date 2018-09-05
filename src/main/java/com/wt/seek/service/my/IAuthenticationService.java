package com.wt.seek.service.my;

import java.util.List;

import com.wt.seek.entity.Authentication;

public interface IAuthenticationService {
	
	/**
	 * 查询所有的认证记录
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Authentication> listAuthentication(Integer currentPageNo,Integer pageSize);

	/**
	 * 保存认证
	 * 
	 * @param Authentication
	 * @return
	 * @throws Exception
	 */
	boolean saveAuthentication(Authentication Authentication) throws Exception;

	/**
	 * 查询每个用户的认证记录
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	Authentication getAuthentication(int customerId) throws Exception;

	/**
	 * 修改用户的认证
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer updateAuthentication(int id) throws Exception;

	/**
	 * 
	 * 查询所有的记录
	 */
	Integer countAuthentication();
}
