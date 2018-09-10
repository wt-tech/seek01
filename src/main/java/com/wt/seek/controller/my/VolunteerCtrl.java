package com.wt.seek.controller.my;

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

import com.wt.seek.entity.Volunteer;
import com.wt.seek.service.my.IVolunteerService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

@RestController("")
@RequestMapping("/volunteer")
public class VolunteerCtrl {
	@Autowired
	private IVolunteerService volunteerService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listvolunteer")
	public Map<String, Object> listVolunteer(@RequestParam("currentPageNo") Integer currentPageNo)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = volunteerService.countVolunteer();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<Volunteer> volunteers = volunteerService.listVolunteer(currentPageNos,
				Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("volunteers", volunteers);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSize);
		return map;
	}

	/**
	 * 志愿者加入
	 * 
	 * @param volunteer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/savevolunteer")
	public Map<String, Object> saveVolunteer(HttpServletRequest request,
			@RequestBody() Volunteer volunteer,
			@RequestParam(value = "negativIdentityUrl", required = false) MultipartFile negativIdentityUrl,
			@RequestParam(value = "positiveIdentityUrl", required = false) MultipartFile positiveIdentityUrl)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = volunteerService.saveVolunteer(volunteer, negativIdentityUrl, positiveIdentityUrl,
				staticsPath);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}

	/**
	 * 查询志愿者的记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getvolunteer")
	public Volunteer getVolunteer(@RequestParam("customerId") Integer customerId) throws Exception {
		return volunteerService.getVolunteer(customerId);

	}

	/**
	 * 修改志愿者的记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatevolunteer")
	public Map<String, Object> updateVolunteer(HttpServletRequest request,
			@RequestBody() Volunteer volunteer,
			@RequestParam(value = "negativIdentityUrl", required = false) MultipartFile negativIdentityUrl,
			@RequestParam(value = "positiveIdentityUrl", required = false) MultipartFile positiveIdentityUrl)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = volunteerService.updateVolunteer(volunteer, negativIdentityUrl, positiveIdentityUrl,
				staticsPath);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}

}
