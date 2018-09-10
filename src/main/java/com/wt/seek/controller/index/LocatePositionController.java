package com.wt.seek.controller.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wt.seek.service.index.Coordinate2AddressService;
import com.wt.seek.tool.Constants;


@RestController
public class LocatePositionController {

	@Autowired
	private Coordinate2AddressService converter;
	
	
	/**
	 * 小程序客户端传回来 经纬度,这边根据经纬度定位到位置
	 * @param response
	 * @param latitude		纬度
	 * @param longitude		经度
	 * @throws IOException 
	 */
	@RequestMapping("/locatePosition")
	public Map<String,Object> parseLocation(HttpServletResponse response,@RequestParam String latitude,@RequestParam String longitude,HttpServletRequest request) throws IOException{
		
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject result;
		JSONObject httpResult = null;
		try {
			String s = converter.convert(latitude, longitude);
			httpResult = JSONObject.parseObject(s);
			System.out.println(httpResult);
			map.put(Constants.STATUS, Constants.SUCCESS);
		} catch (IOException e) {
			map.put(Constants.STATUS, Constants.FAIL);
			e.printStackTrace();
		}finally {
			JSONObject address = null;
			map.put("message", httpResult.getString("message"));
			if(httpResult.getString("message").equals("query ok")) {
				address = httpResult.getJSONObject("result").getJSONObject("address_component");
			}
			
			map.put("address", address);
		}
		return map;
		
	}
	
//	/**
//	 * 
//	 * @param response
//	 * @param provinceID	查询provinceID下的所有城市
//	 * @param cityID		查询cityID下的所有区县
//	 * @throws IOException
//	 */
//	@RequestMapping("/initPosition")
//	public void initAreaInfo(HttpServletResponse response,HttpServletRequest request, @RequestParam Integer provinceID,@RequestParam Integer cityID) throws IOException {
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		
//		map.put(Constants.STATUS, Constants.SUCCESS);
//		map.put("province", service.findAllProvince());
//		map.put("city", service.findCityByProvinceID(provinceID));
//		map.put("county", service.findAllCountyByCityID(cityID));
//		
//		JSONObject json = new JSONObject(map);
//		response.getWriter().println(json.toJSONString());
//	}
//	@RequestMapping("/city")
//	public void getAllCityByProvinceID(HttpServletResponse response,HttpServletRequest request,@RequestParam Integer ID) throws IOException {
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put(Constants.STATUS, Constants.SUCCESS);
//		map.put("city", service.findCityByProvinceID(ID));
//		
//		JSONObject json = new JSONObject(map);
//		response.getWriter().println(json.toJSONString());
//	}
//	
//	@RequestMapping("/county")
//	public void getAllCountyByCityID(HttpServletResponse response,HttpServletRequest request,@RequestParam Integer ID) throws IOException {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put(Constants.STATUS, Constants.SUCCESS);
//		map.put("county", service.findAllCountyByCityID(ID));
//		
//		JSONObject json = new JSONObject(map);
//		response.getWriter().println(json.toJSONString());
//	}
	
}
