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
import com.wt.seek.service.index.IComentService;
import com.wt.seek.service.index.ITopComentService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

@RestController("")
@RequestMapping("/topcoment")
public class TopComentCtrl {
	@Autowired
	private ITopComentService topComentService;

	@Autowired
	private IComentService comentService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listtopcoment")
	public Map<String, Object> listTopComent(Seek seek, @RequestParam("currentPageNo") Integer currentPageNo)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		int totalCount = topComentService.countTopComent(seek);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo, Constants.pageSize);
		List<TopComent> topComents = topComentService.listTopComent(seek.getId(), currentPageNos, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("TopComents", topComents);
		return map;
	}

	/**
	 * 我的评论
	 * 
	 * @param customerId
	 * @param currentPageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listtopcomentbycustomerid")
	public Map<String, Object> listTopComentByCustomerId(@RequestParam("customerId") Integer customerId,
			@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		int totalCount = topComentService.countTopComentByCustomerId(customerId);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo,Constants.pageSizess);
		List<TopComent> allComents = topComentService.listTopComentByCustomerId(customerId, currentPageNos,
				Constants.pageSizess);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("mycoment", allComents);
		return map;
	}

	/**
	 * 谁评论我
	 * 
	 * @param customerId
	 * @param currentPageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listcomentbycustomerid")
	public Map<String, Object> listComentByCustomerId(@RequestParam("customerId") Integer customerId,
			@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		int totalCount = topComentService.countComentByCustomerId(customerId);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo,Constants.pageSizess);
		List<TopComent> allComents = topComentService.listComentByCustomerId(customerId, currentPageNos,
				Constants.pageSizess);
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

	/**
	 * 后台删除评论
	 * 
	 * @param topcomentId
	 * @param comentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/back/deletecoment")
	public Map<String, Object> deleteComent(
			@RequestParam(value = "topcomentId", required = false) Integer[] topcomentId,
			@RequestParam(value = "comentId", required = false) Integer[] comentId) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = true;
		boolean flags = true;
		if (topcomentId.length > 0 || comentId.length > 0) {// 如果外层评论或内层评论的id不为空
			if (topcomentId.length > 0) {// 如果外层评论的id不为空
				for (Integer id : topcomentId) {
					flag = topComentService.deleteTopComent(id);
				}
			}

			if (comentId.length > 0) {// 如果内层评论的id不为空
				for (Integer id : comentId) {
					flags = comentService.deleteComent(id);
				}
			}

			if (flag && flags) {
				map.put(Constants.STATUS, Constants.SUCCESS);
			} else {
				map.put(Constants.STATUS, Constants.FAIL);
			}
		} else {// 传过来的两个数组为空
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}

}