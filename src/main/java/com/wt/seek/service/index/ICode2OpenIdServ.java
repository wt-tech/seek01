package com.wt.seek.service.index;

public interface ICode2OpenIdServ {

	/**
	 * 
	 * @param code
	 * @return 若code不是合法的,返回值是null
	 */
	String getOpenID(String code);
	
}
