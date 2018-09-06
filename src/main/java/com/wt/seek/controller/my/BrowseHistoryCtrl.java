package com.wt.seek.controller.my;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.seek.entity.BrowseHistory;
import com.wt.seek.service.my.IBrowseHistoryService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

@RestController("")
@RequestMapping("/browsehistory")
public class BrowseHistoryCtrl {
	@Autowired
	private IBrowseHistoryService browsehistoryService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listbrowsehistory")
	public Map<String, Object> listBrowseHistory(@RequestParam("customerId") Integer customerId,
			@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = browsehistoryService.countBrowseHistory();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<BrowseHistory> browsehistorys = browsehistoryService.listBrowseHistory(customerId, currentPageNos, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("browsehistorys", browsehistorys);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSize);
		return map;
	}

	/**
	 * 插入浏览记录
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/savebrowsehistory")
	public Map<String, Object> saveBrowseHistory(@RequestBody() BrowseHistory browsehistory) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = browsehistoryService.saveBrowseHistory(browsehistory);
		if (flag) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}

	/**
	 * 删除单条记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deletebrowsehistory")
	public Map<String, Object> deleteBrowseHistory(@RequestParam("id") Integer id) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = browsehistoryService.deleteBrowseHistory(id);
		if (flag) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		map.put(Constants.STATUS, Constants.SUCCESS);
		return map;

	}
}
