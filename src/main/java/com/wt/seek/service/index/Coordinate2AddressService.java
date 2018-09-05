package com.wt.seek.service.index;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.alibaba.fastjson.JSONObject;

public interface Coordinate2AddressService {
	
	String convert(String latitude, String longitude) throws UnsupportedEncodingException, MalformedURLException, IOException;
	String processJsonData(JSONObject address);
}
