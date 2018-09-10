package com.wt.seek.dao.my;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.BrowseHistory;

public interface IBrowseHistoryMapper {
	/**
	 * 查询个人所有的浏览记录
	 * 
	 * @param customerId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<BrowseHistory> listBrowseHistory(@Param("customerId") Integer customerId,
			@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 添加浏览记录
	 * 
	 * @param BrowseHistory
	 * @return
	 * @throws Exception
	 */
	Integer saveBrowseHistory(BrowseHistory browseHistory) throws Exception;

	/**
	 * 删除浏览记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer deleteBrowseHistory(@Param("id") int id) throws Exception;

	/**
	 * 查询是否有浏览记录
	 * 
	 * @param BrowseHistory
	 * @return
	 * @throws Exception
	 */
	BrowseHistory getBrowseHistory(@Param("customerId") Integer customerId,@Param("seekId") int seekId) throws Exception;

	/**
	 * 如果存在浏览记录，更新时间为最后一次浏览时间
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer updateBrowseHistory(@Param("id") int id) throws Exception;

	/**
	 * 查询表中所有的记录
	 * 
	 * @return
	 */
	Integer countBrowseHistory(@Param("customerId") int customerId);
}
