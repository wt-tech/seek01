package com.wt.seek.controller.my;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.Province;
import com.wt.seek.entity.Volunteer;
import com.wt.seek.entity.VolunteerArea;
import com.wt.seek.service.index.ISeekService;
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
	
	@Autowired
	private ISeekService seekService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping(value= {"/listvolunteer","/back/listvolunteer"})
	public Map<String, Object> listVolunteer(@RequestParam("currentPageNo") Integer currentPageNo)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = volunteerService.countVolunteer();
		Integer currentPageNos = new PageUtil().Page(totalCount,currentPageNo,Constants.pageSize);
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
	public Map<String, Object> saveVolunteer(@RequestBody Volunteer volunteer)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Integer volunteerId = volunteerService.saveVolunteer(volunteer);
		if (volunteerId > 0) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		map.put("volunteerId", volunteerId);
		return map;
	}
	
	/**
	 * 志愿者加入图片
	 * 
	 * @param volunteer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/savevolunteerImage")
	public Map<String, Object> saveVolunteerImage(HttpServletRequest request,
			@RequestParam Integer volunteerId,
			@RequestParam(value = "negativIdentityUrl", required = false) MultipartFile negativIdentityUrl,
			@RequestParam(value = "positiveIdentityUrl", required = false) MultipartFile positiveIdentityUrl)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		Volunteer volunteer=new Volunteer();
		volunteer.setId(volunteerId);
		boolean flag = volunteerService.updateVolunteer(volunteer,negativIdentityUrl, positiveIdentityUrl,
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
	@RequestMapping(value = "/getvolunteer",method=RequestMethod.GET)
	public Map<String, Object> getVolunteer(@RequestParam("customerId") Integer customerId) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Volunteer volunteer=volunteerService.getVolunteer(customerId);
		if(null !=volunteer) {
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put("volunteer", volunteer);
		}else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}

	@RequestMapping(value = "/back/getvolunteer",method=RequestMethod.GET)
	public Map<String, Object> getBackVolunteer(@RequestParam("id") Integer id) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		Volunteer volunteer=volunteerService.getBackVolunteer(id);
		if(null !=volunteer) {
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put("volunteer", volunteer);
		}else {
			map.put(Constants.STATUS, Constants.FAIL);
		}
		return map;
	}
	
	/**
	 * 修改志愿者的记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= {"/updatevolunteer","/back/updatevolunteer"})
	public Map<String, Object> updateVolunteer(HttpServletRequest request,
			/*@RequestBody()*/ Volunteer volunteer,
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
    /**
     * 查询单个志愿者负责的区域
     * @param volunteerId
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/back/listvolunteerarea",method=RequestMethod.GET)
	public Map<String, Object> listVolunteerArea(@RequestParam("volunteerId") Integer volunteerId)throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<VolunteerArea> volunteerareas = volunteerService.listVolunteerAreaById(volunteerId);
		List<Province> listprovince = seekService.listProvince();
		map.put(Constants.STATUS,Constants.SUCCESS);
		map.put("volunteerareas", volunteerareas);
		map.put("listprovince", listprovince);
		return map;
	}
	
	@RequestMapping(value = "/back/savevolunteerarea",method=RequestMethod.POST)
	public Map<String, Object> saveVolunteerArea(
			@RequestParam(value = "volunteerId", required = true) Integer volunteerId,
			@RequestParam(value = "provinceId", required = true) Integer[] provinceId,
			@RequestParam(value = "cityId", required = true) Integer[] cityId,
			@RequestParam(value = "countyId", required = true) Integer[] countyId)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = volunteerService.saveVolunteerAddress(volunteerId,provinceId,cityId,countyId);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}
	
	@RequestMapping(value = "/back/deletevolunteerarea",method=RequestMethod.DELETE)
	public Map<String, Object> deleteVolunteerArea(@RequestParam(value = "id", required = true) Integer[] id)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = volunteerService.deleteVolunteerArea(id);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}
}
