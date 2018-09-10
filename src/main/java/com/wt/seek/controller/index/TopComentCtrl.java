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

import com.wt.seek.entity.Seek;
import com.wt.seek.entity.TopComent;
import com.wt.seek.service.index.ITopComentService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

@RestController("")
@RequestMapping("/topcoment")
public class TopComentCtrl {
	@Autowired
	private ITopComentService topComentService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listtopcoment")
	public Map<String, Object> listTopComent(Seek seek,@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		int totalCount = topComentService.countTopComent(seek);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<TopComent> topComents = topComentService.listTopComent(seek.getId(), currentPageNos, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("TopComents", topComents);
		return map;
	}
	
    /**
     * 我的评论
     * @param customerId
     * @param currentPageNo
     * @return
     * @throws Exception
     */
	@RequestMapping("/listtopcomentbycustomerid")
	public Map<String, Object> listTopComentByCustomerId(@RequestParam("customerId") Integer customerId,@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		int totalCount = topComentService.countTopComentByCustomerId(customerId);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<TopComent> allComents = topComentService.listTopComentByCustomerId(customerId, currentPageNos, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("mycoment", allComents);
		return map;
	}
	
	/**
     * 谁评论我
     * @param customerId
     * @param currentPageNo
     * @return
     * @throws Exception
     */
	@RequestMapping("/listcomentbycustomerid")
	public Map<String, Object> listComentByCustomerId(@RequestParam("customerId") Integer customerId,@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		int totalCount = topComentService.countComentByCustomerId(customerId);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<TopComent> allComents = topComentService.listComentByCustomerId(customerId, currentPageNos, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("comentwho", allComents);
		return map;
	}
	
	@RequestMapping("/savetopcoment")
	public Map<String, Object> saveTopComent(@RequestBody() TopComent topComent) throws Exception {

		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = topComentService.saveTopComent(topComent);
		if (flag) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}
}