package com.wt.seek.service.my;

import java.util.List;

import com.wt.seek.entity.BrowseHistory;

public interface IBrowseHistoryService {
	
	/**
	 * 查询个人所有的浏览记录
	 * 
	 * @param customerId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<BrowseHistory> listBrowseHistory(Integer customerId,Integer currentPageNo,Integer pageSize);

	/**
	 * 添加浏览记录
	 * 
	 * @param BrowseHistory
	 * @return
	 * @throws Exception
	 */
	boolean saveBrowseHistory(BrowseHistory browseHistory) throws Exception;

	/**
	 * 删除浏览记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean deleteBrowseHistory(int id) throws Exception;

	/**
	 * 查询表中所有的记录
	 * 
	 * @return
	 */
	Integer countBrowseHistory();
}
