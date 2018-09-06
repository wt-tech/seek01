package com.wt.seek.servimpl.index;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wt.seek.service.index.ICode2OpenIdServ;
import com.wt.seek.tool.Constants;
import com.wt.seek.entity.HttpClient;

@Service
public class Code2OpenIdServImpl implements ICode2OpenIdServ {

	@Override
	public String getOpenID(String code) {
		String url = Constants.CODE2OPENIDURL+code;
		HttpClient client = new HttpClient(url);
		client.connect("GET");
		String jsonStr = client.fetchResponseContentInfo();
		
		//官网上描述,即使code不合法,也会返回一个json字符串,但这里却会出现null的情况
		if(null == jsonStr) {
			return null;
		}
		JSONObject json = JSONObject.parseObject(jsonStr);
		//获取openID字符串
		String openID = json.getString("openid");
		return openID;
	}

}
