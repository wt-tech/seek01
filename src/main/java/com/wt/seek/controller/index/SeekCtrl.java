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
import com.wt.seek.entity.Seek;
import com.wt.seek.entity.TopComent;
import com.wt.seek.service.index.IComentService;
import com.wt.seek.service.index.ISeekService;
import com.wt.seek.service.index.ITopComentService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;
import com.wt.seek.tool.SeekSimilarityCalculation;

@RestController("")
@RequestMapping("/seek")
public class SeekCtrl {
	@Autowired
	private ISeekService seekService;

	@Autowired
	private ITopComentService topComentService;

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
	@RequestMapping(value= {"/listseek","/back/listseek"})
	public Map<String, Object> listSeek(@RequestBody Object seek/*,@RequestBody String hadBrowsed,@RequestBody Integer currentPageNo*/) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// System.err.println(seek);
		// System.err.println(seek.getClass());
		JSONObject json = (JSONObject) seek;
		String hadBrowsed = json.getString("hadBrowsed");
		json.remove("hadBrowsed");
		Integer currentPageNo = json.getInteger("currentPageNo");
		json.remove("currentPageNo");

		Seek seekObj = JSONObject.parseObject(json.toString(), Seek.class);

		// System.err.println(hadBrowsed);
		// 总数量（表）
		int totalCount = seekService.countSeek();
//		List<Seek> seklist = new ArrayList<Seek>();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<Seek> seeks = seekService.listSeek(seekObj, hadBrowsed, currentPageNos, Constants.pageSizes);
		for (Seek sek : seeks) {
			String img = sek.getSeekimgs()==null?"":sek.getSeekimgs();
			String firstImg = img;
			if(img.length()>0) {
				firstImg = img.split(",")[0];
			}
			sek.setSeekimgs(firstImg);
//			seklist.add(sek);
		}
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("Seeks", seeks);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSizes);
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
	public Map<String, Object> saveSeek(@RequestBody Seek seek) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);

		// 检测是否重复发布
		List<Seek> list = seekService.listSeekByCustomerIdAndSeekType(seek.getCustomer().getId(), seek.getSeekType());
		if (SeekSimilarityCalculation.hadPublishedSameInfoBefore(seek, list)) {// 重复发布
			map.put(Constants.SYS_MESSAGE, "请勿重复发布信息.");
			return map;
		}

		Integer seeksId = seekService.saveSeek(seek);
		if (seeksId > 0) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		map.put("seeksId", seeksId);
		return map;
	}

	/**
	 * 插入寻亲记录的图片
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveseekImg")
	public Map<String, Object> saveSeekImg(HttpServletRequest request, @RequestParam("seekId") Integer seekId,
			@RequestParam(value = "seekImg", required = false) MultipartFile file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		resultMap.put(Constants.STATUS, Constants.FAIL);

		// 获取图片的公共存储路径（例如：D:\ApacheTomcat7\apache-tomcat-7.0.53\webapps\statics）
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = seekService.saveSeekImg(seekId, file, staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	/**
	 * 匹配相似度
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listsimilarseek")
	public Map<String, Object> listSimilarSeek(@RequestBody Seek seek) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		List<Seek> list = seekService.listSimilarSeek(seek);
		for (Seek sek : list) {
			String img = sek.getSeekimgs()==null?"":sek.getSeekimgs();
			String firstImg = img;
			if(img.length()>0) {
				firstImg = img.split(",")[0];
			}
			sek.setSeekimgs(firstImg);
		}
		resultMap.put("similarSeeks", SeekSimilarityCalculation.similarSeekInfoFilter(seek, list));
		return resultMap;
	}

	/**
	 * 查询单条寻亲记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/getseek","/back/getseek"})
	public Map<String, Object> getSeek(Seek seek, @RequestParam("currentPageNo") Integer currentPageNo)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = topComentService.countTopComent(seek);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		// 根据传递的id查询单条寻亲记录的详情内容
		Seek seekcontent = seekService.getSeek(seek.getId());
		// 根据传递的id查询单条寻亲记录的第一级评论内容（包括分页）
		List<TopComent> topComents = topComentService.listTopComent(seek.getId(), currentPageNos, Constants.pageSize);
		// 根据查询出来的第一级评论内容，查询下面所有的子评论
		//List<Coment> coments = comentService.listComent(seek.getId(), currentPageNo, Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("seekcontent", seekcontent);
		map.put("topComents", topComents);
		//map.put("coments", coments);
		map.put("totalCount", totalCount);
		return map;
	}

	/**
	 * 查询我的发布
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listseekbycustomerid")
	public Map<String, Object> listSeekByCustomerId(@RequestParam("customerId") Integer customerId,@RequestParam("currentPageNo") Integer currentPageNo) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		int totalCount = seekService.countSeekByCustomerId(customerId);
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<Seek> seeks = seekService.listSeekByCustomerId(customerId,currentPageNos,Constants.pageSizes);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("seeks", seeks);
		return map;
	}
	
	/**
	 * 修改发布
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/updateseek","/back/updateseek"})
	public Map<String, Object> updateSeek(Seek seek) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = seekService.updateSeek(seek);
		map.put(Constants.STATUS, flag?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
	
	/**
	 * 删除发布
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/deleteseek","/back/deleteseek"})
	public Map<String, Object> deleteSeek(@RequestParam("id") Integer id) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = seekService.deleteSeek(id);
		map.put(Constants.STATUS, flag?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
}
