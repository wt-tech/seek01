package com.wt.seek.controller.index;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wt.seek.entity.MiniProgramCodeParam;
import com.wt.seek.inface.Get;
import com.wt.seek.service.index.IMiniProgramCodeServ;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.ImageUtils;
import com.wt.seek.tool.MapUtils;

@RestController
@RequestMapping("code")
public class MiniProgramCodeCtrl {

	@Autowired
	private IMiniProgramCodeServ service;
	
	@RequestMapping(value="/accesstoken",method=RequestMethod.GET)
	public Map<String,Object> fetchAccessToken() {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("result", service.fetchAccessToken());
		return map;
	}
	
	@RequestMapping(value="/wxcode",method=RequestMethod.POST)
	public Map<String,Object> getImg(
			@Validated(Get.class)
			MiniProgramCodeParam param,HttpServletRequest request){
		String absoluteDirectory = ContextUtil.getStaticResourceAbsolutePath(request);
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		if(service.fetchWXACodeUnlimit(param,absoluteDirectory)) {
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put("url", Constants.imgServerDomain + ImageUtils.getMiniQRVirtualURI(param));
		}
		return map;
	}
	
}
