package com.wt.seek.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Volunteer;

public interface IVolunteerMapper {

	/**
	 * 查询所有的志愿者
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Volunteer> listVolunteer(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 志愿者加入
	 * 
	 * @param Volunteer
	 * @return
	 * @throws Exception
	 */
	Integer saveVolunteer(Volunteer volunteer) throws Exception;

	/**
	 * 查询每个用户的志愿者记录
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */

	Volunteer getVolunteer(@Param("customerId") int customerId) throws Exception;
	
	Volunteer getBackVolunteer(@Param("id") int id) throws Exception;

	/**
	 * 修改用户的志愿者信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	Integer updateVolunteer(Volunteer volunteer) throws Exception;

	/**
	 * 
	 * 查询所有的记录
	 */
	Integer countVolunteer();

}
