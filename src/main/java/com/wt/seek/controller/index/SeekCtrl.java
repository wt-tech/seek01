package com.wt.seek.controller.index;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.wt.seek.entity.Coment;
import com.wt.seek.entity.Seek;
import com.wt.seek.entity.TopComent;
import com.wt.seek.service.index.IComentService;
import com.wt.seek.service.index.ISeekService;
import com.wt.seek.service.index.ITopComentService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

@RestController("")
@RequestMapping("/seek")
public class SeekCtrl {
	@Autowired
	private ISeekService seekService;

	@Autowired
	private ITopComentService topComentService;

	@Autowired
	private IComentService comentService;

	private Logger logger = LogManager.getLogger();

	/**
	 * 根据筛选条件查询寻亲记录，其中hadBrowsed代表是否过滤已经查看，true为过滤，false为不过滤
	 * 
	 * @param seek
	 * @param hadBrowsed
	 * @param currentPageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listseek")
	public Map<String, Object> listSeek(@RequestBody Object seek/*, @RequestBody String hadBrowsed,
			@RequestBody Integer currentPageNo*/) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
//		System.err.println(seek);
//		System.err.println(seek.getClass());
		JSONObject json = (JSONObject)seek;
		String hadBrowsed = json.getString("hadBrowsed");
		json.remove("hadBrowsed");
		Integer currentPageNo = json.getInteger("currentPageNo");
		json.remove("currentPageNo");
		
		Seek seekObj = JSONObject.parseObject(json.toString(), Seek.class);
		
		//System.err.println(hadBrowsed);
		// 总数量（表）
		int totalCount = seekService.countSeek();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<Seek> seeks = seekService.listSeek(seekObj, hadBrowsed, currentPageNos, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("Seeks", seeks);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSize);
		return map;
	}

	/**
	 * 插入寻亲记录
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveseek")
	public Map<String, Object> saveSeek(HttpServletRequest request, Seek seek,
			@RequestParam(value = "seekImg", required = false) MultipartFile file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		//获取图片的公共存储路径（例如：D:\ApacheTomcat7\apache-tomcat-7.0.53\webapps\statics）
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = seekService.saveSeek(seek, file,staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	/**
	 * 查询单条寻亲记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getseek")
	public Map<String, Object> getSeek(Seek seek, @RequestParam("currentPageNo") Integer currentPageNo)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = topComentService.countTopComent(seek);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		//根据传递的id查询单条寻亲记录的详情内容
		Seek seekcontent = seekService.getSeek(seek.getId());
		//根据传递的id查询单条寻亲记录的第一级评论内容（包括分页）
		List<TopComent> topComents = topComentService.listTopComent(seek.getId(), currentPageNos, Constants.pageSize);
		//根据查询出来的第一级评论内容，查询下面所有的子评论
		List<Coment> coments = comentService.listComent(seek.getId(), currentPageNo, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("seekcontent", seekcontent);
		map.put("topComents", topComents);
		map.put("coments", coments);
		map.put("totalCount", totalCount);
		return map;
	}
}
