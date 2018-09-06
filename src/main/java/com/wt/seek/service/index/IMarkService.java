package com.wt.seek.service.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Mark;

public interface IMarkService {
	
	/**
	 * 查询个人所有的收藏
	 * 
	 * @param customerId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Mark> listMark(Integer customerId,Integer currentPageNo,Integer pageSize);

	/**
	 * 添加收藏
	 * 
	 * @param mark
	 * @return
	 * @throws Exception
	 */
	boolean saveMark(Mark mark) throws Exception;

	/**
	 * 删除收藏
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	boolean deleteMark(int customerId,int seekId) throws Exception;
	
	/**
	 * 查询是否收藏过
	 * 
	 * @param customerId
	 * @param seekId
	 * @return
	 * @throws Exception
	 */
	Mark getMark(int customerId,int seekId) throws Exception;


	/**
	 * 查询表中所有的记录
	 * 
	 * @return
	 */
	Integer countMark();
}
