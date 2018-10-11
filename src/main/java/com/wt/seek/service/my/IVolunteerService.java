package com.wt.seek.service.my;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.Volunteer;

public interface IVolunteerService {


	/**
	 * 查询所有的志愿者
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */

	List<Volunteer> listVolunteer(Integer currentPageNo, Integer pageSize);

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
	Volunteer getVolunteer(int customerId) throws Exception;
	
	Volunteer getBackVolunteer(int id) throws Exception;

	/**
	 * 修改用户的志愿者信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	boolean updateVolunteer(Volunteer volunteer, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath) throws Exception;


	Volunteer getVolunteerByIDAndName(String ID,String realName);
	
	
	/**
	 * 
	 * 查询所有的记录
	 */
	Integer countVolunteer();
}
