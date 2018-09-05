package com.wt.seek.servimpl.index;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wt.seek.entity.HttpClient;
import com.wt.seek.service.index.Coordinate2AddressService;
import com.wt.seek.tool.Constants;

/**
 * 
 * @author Daryl
 * 经纬度坐标转地址
 *
 */
@Service
public class Coordinate2AddressImpl implements Coordinate2AddressService{

	/**
	 *发送HTTP请求，到腾讯提供的指定接口,用经纬度换取具体的省市县地址
	 */
	@Override
	public String convert(String latitude, String longitude) throws MalformedURLException, IOException {
		
		String convertURL = getRandomRarpURL(latitude, longitude);
		HttpClient client = new HttpClient(convertURL);
		client.connect("GET");
		String s = client.fetchResponseContentInfo();
		return s;
	}

	/**
	 * address的格式如下：<br/>
	 *  address: { <br/>
	       &nbsp&nbsp &nbsp&nbsp  "street": "包河大道",<br/>
	       &nbsp&nbsp &nbsp&nbsp  "province": "安徽省",<br/>
	       &nbsp&nbsp &nbsp&nbsp  "street_number": "包河大道118号",<br/>
	       &nbsp&nbsp &nbsp&nbsp  "district": "包河区",<br/>
	       &nbsp&nbsp &nbsp&nbsp  "nation": "中国",<br/>
	       &nbsp&nbsp &nbsp&nbsp  "city": "合肥市"<br/>
    	}<br/>
    	这里只处理 province,city,district.其他数据不关心.
	 */
	@Override
	public String processJsonData(JSONObject address) {//考虑到服务器带宽,将这项工作移到前端实现
		
		return null;
	}
	
	public String getRandomRarpURL(String latitude, String longitude) throws UnsupportedEncodingException {
		
		//腾讯官方给出的接口调用示例如下：
		//http://apis.map.qq.com/ws/geocoder/v1/?location=39.984154,116.307490&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&get_poi=1
		int randomKey = ((int) (Math.random()*Constants.KEYNUMBERS));
		StringBuilder finalURL = new StringBuilder(Constants.BASERarpURL);
		finalURL.append("?location=");
		finalURL.append(URLEncoder.encode(latitude+","+longitude, "UTF-8"));
		finalURL.append("&get_poi=0");
		finalURL.append("&key=");
		
		switch(randomKey) {
			case 0:
				finalURL.append(URLEncoder.encode(Constants.KEY1,"UTF-8"));
				System.out.println("KEY1");
				break;
			case 1:
				finalURL.append(URLEncoder.encode(Constants.KEY2,"UTF-8"));
				System.out.println("KEY2");
				break;
			case 2:
				finalURL.append(URLEncoder.encode(Constants.KEY3,"UTF-8"));
				System.out.println("KEY3");
				break;
			case 3:
				finalURL.append(URLEncoder.encode(Constants.KEY4,"UTF-8"));
				System.out.println("KEY4");
				break;
			case 4:
				finalURL.append(URLEncoder.encode(Constants.KEY5,"UTF-8"));
				System.out.println("KEY5");
				break;
		}
		
		return finalURL.toString();
	}


	
}
