package com.wt.seek.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Volunteer;
import com.wt.seek.entity.VolunteerArea;

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

	Integer saveVolunteerAddress(@Param("volunteers") List<Volunteer> volunteers) throws Exception;

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
	 * 根据志愿者的姓名,身份证号做匹配
	 */
	Volunteer getVolunteerByIDAndName(@Param("ID") String ID, @Param("realName") String realName);

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

	/**
	 * 查询单个志愿者负责的区域
	 * 
	 * @param volunteerId
	 * @return
	 */
	List<VolunteerArea> listVolunteerAreaById(@Param("volunteerId") Integer volunteerId);

	/**
	 * 删除单个志愿者负责的区域
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer deleteVolunteerArea(@Param("ids") Integer[] ids) throws Exception;

	/**
	 * 查询志愿者的customerId
	 * 
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	Integer getVolunteerCustomerId(@Param("loginId") Integer loginId) throws Exception;
}
