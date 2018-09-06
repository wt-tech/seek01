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

import com.wt.seek.entity.Coment;
import com.wt.seek.entity.Seek;
import com.wt.seek.service.index.IComentService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;

@RestController("")
@RequestMapping("/coment")
public class ComentCtrl {
	@Autowired
	private IComentService comentService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listcoment")
	public Map<String, Object> listComent(Seek seek,@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Coment> coments = comentService.listComent(seek.getId(), currentPageNo, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("Coments", coments);
		return map;
	}

	@RequestMapping("/savecoment")
	public Map<String, Object> saveComent(@RequestBody() Coment coment) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = comentService.saveComent(coment);
		if (flag) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}
}