package com.wt.seek.controller.back;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.BannerDetail;
import com.wt.seek.inface.Save;
import com.wt.seek.inface.Update;
import com.wt.seek.service.back.IBannerDetailService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;

@RestController()
@RequestMapping("detail")
public class BannerDetailCtrl {

	@Autowired
	private IBannerDetailService detailService;
	
	
	@RequestMapping(value="/back/detail",method=RequestMethod.POST)
	public Map<String,Object> saveBannerDetail(
			@Validated({Save.class}) BannerDetail bannerDetail){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		boolean flag = detailService.saveBannerDetail(bannerDetail);
		map.put(Constants.STATUS, flag?Constants.SUCCESS:Constants.FAIL);
		map.put("id", bannerDetail.getId());
		return map;
	}
	
	
	@RequestMapping(value="/back/detail",method=RequestMethod.DELETE)
	public Map<String,Object> removeBannerDetail(Integer id){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		boolean flag = detailService.removeBannerDetail(id);
		map.put(Constants.STATUS, flag?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
	
	
	@RequestMapping(value="/back/detail",method=RequestMethod.PUT)
	public Map<String,Object> updateBannerDetail(
			@Validated({Update.class}) @RequestBody BannerDetail bannerDetail){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		boolean flag = detailService.updateBannerDetail(bannerDetail);
		map.put(Constants.STATUS, flag?Constants.SUCCESS:Constants.FAIL);
		return map;
	}
	
	
	@RequestMapping(value= {"/detail/{id}","/back/detail/{id}"},method=RequestMethod.GET)
	public Map<String,Object> getBannerDetailById(@PathVariable("id") Integer id){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		BannerDetail bannerDetail = detailService.getById(id);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("detail", bannerDetail);
		return map;
	}
	
	@RequestMapping(value= {"/banner/detail/{bannerId}",
			"/back/banner/detail/{bannerId}"},method=RequestMethod.GET)
	public Map<String,Object> getBannerDetailByBannerId(@PathVariable("bannerId") Integer bannerId){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		BannerDetail bannerDetail = detailService.getByBannerId(bannerId);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("detail", bannerDetail);
		return map;
	}
	
	@RequestMapping("/back/imgs")
	public Map<String,Object> saveDetailImg(HttpServletRequest request,
			@RequestParam(value="detailImgs",required=true) MultipartFile[] detailImgs){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		String absoluteStaticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("urls",detailService.storeDetailImgs(absoluteStaticsPath,detailImgs));
		return map;
	}
}
