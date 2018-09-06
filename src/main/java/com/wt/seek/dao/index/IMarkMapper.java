package com.wt.seek.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Mark;

public interface IMarkMapper {
	/**
	 * 查询个人所有的收藏
	 * 
	 * @param customerId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Mark> listMark(@Param("customerId") Integer customerId, @Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize);

	/**
	 * 添加收藏
	 * 
	 * @param mark
	 * @return
	 * @throws Exception
	 */
	Integer saveMark(Mark mark) throws Exception;

	/**
	 * 删除收藏
	 * @param customerId
	 * @param seekId
	 * @return
	 * @throws Exception
	 */
	Integer deleteMark(@Param("customerId") int customerId,@Param("seekId") int seekId) throws Exception;
	
	/**
	 * 查询是否收藏过
	 * 
	 * @param customerId
	 * @param seekId
	 * @return
	 * @throws Exception
	 */
	Mark getMark(@Param("customerId") int customerId,@Param("seekId") int seekId) throws Exception;

	/**
	 * 查询表中所有的记录
	 * 
	 * @return
	 */
	Integer countMark();
}
