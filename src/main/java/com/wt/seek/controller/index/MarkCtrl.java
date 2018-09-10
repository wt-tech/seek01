package com.wt.seek.controller.index;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.seek.entity.Mark;
import com.wt.seek.service.index.IMarkService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

@RestController("")
@RequestMapping("/mark")
public class MarkCtrl {
	@Autowired
	private IMarkService markService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listmark")
	public Map<String, Object> listMark(@RequestParam("customerId") Integer customerId,
			@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = markService.countMark();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<Mark> marks = markService.listMark(customerId, currentPageNos, Constants.pageSizes);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("marks", marks);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSize);
		return map;
	}

	/**

	 * 保存收藏
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/savemark")
	public Map<String, Object> saveMark(@RequestBody() Mark mark) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = markService.saveMark(mark);
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
	@RequestMapping("/deletemark")
	public Map<String, Object> deleteMark(@RequestParam("customerId") Integer customerId,@RequestParam("seekId") Integer seekId) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = markService.deleteMark(customerId,seekId);
		if (flag) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}
	
	/**
	 * 查询是否收藏过
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getmark")
	public Map<String, Object> getMark(@RequestParam("customerId") Integer customerId,@RequestParam("seekId") Integer seekId) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Mark mark = markService.getMark(customerId,seekId);
		if(mark!=null) {
			map.put(Constants.STATUS, true);
		} else {
			map.put(Constants.STATUS, false);
		}
		return map;
	}
}
