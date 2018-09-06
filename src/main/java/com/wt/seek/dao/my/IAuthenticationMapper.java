package com.wt.seek.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Authentication;

public interface IAuthenticationMapper {

	/**
	 * 查询所有的认证记录
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Authentication> listAuthentication(@Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize);

	/**
	 * 保存认证
	 * 
	 * @param Authentication
	 * @return
	 * @throws Exception
	 */
	Integer saveAuthentication(Authentication Authentication) throws Exception;

	/**
	 * 查询每个用户的认证记录
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */

	Authentication getAuthentication(@Param("customerId") int customerId) throws Exception;


	/**
	 * 修改用户的认证
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	Integer updateAuthentication(Authentication Authentication) throws Exception;


	/**
	 * 
	 * 查询所有的记录
	 */
	Integer countAuthentication();

}
