package com.wt.seek.controller.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.Banner;
import com.wt.seek.service.back.IBannerService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;

import com.wt.seek.tool.AuthenticationImage;

import com.wt.seek.tool.UploadImage;



@RestController("")
@RequestMapping("/banner")
public class BannerCtrl {
	@Autowired
	private IBannerService bannerService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listbanner")
	public Map<String, Object> listBanner() throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		List<Banner> banners = bannerService.listBanner();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("banners", banners);
		return map;
	}

	@RequestMapping("/updatebanner")
	public HashMap<String, String> updateBanner(Banner banner) throws Exception {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		boolean flag = bannerService.updateBanner(banner);
		if (flag) {
			resultMap.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			resultMap.put(Constants.STATUS, Constants.FAIL);
		}
		return resultMap;
	}

	@RequestMapping("/savebanner")
	public Map<String, Object> saveBanner(HttpServletRequest request,
			@RequestParam(value = "bannerImg", required = false) MultipartFile[] file) throws Exception {
		Map<String, Object> resultMap = MapUtils.getHashMapInstance();
		//获取图片的公共存储路径（例如：D:\ApacheTomcat7\apache-tomcat-7.0.53\webapps\statics）
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = bannerService.saveBanner(file,staticsPath);
		resultMap.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return resultMap;
	}

	@RequestMapping("/removebanner")
	public HashMap<String, String> removeBanner(@RequestParam("id") Integer id) throws Exception {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		boolean flag = bannerService.removeBanner(id);
		if (flag) {
			resultMap.put(Constants.STATUS, Constants.SUCCESS);
		} else {
			resultMap.put(Constants.STATUS, Constants.FAIL);
		}
		return resultMap;
	}

	@RequestMapping("/getbanner")
	public Banner getBanner(@RequestParam("id") Integer id) {
		return bannerService.getBanner(id);
	}
}
