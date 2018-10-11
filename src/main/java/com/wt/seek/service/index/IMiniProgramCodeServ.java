package com.wt.seek.service.index;

import com.wt.seek.entity.MiniProgramCodeParam;

/**
 * 小程序码相关的接口
 * @author Daryl
 */
public interface IMiniProgramCodeServ {

	/**
	 * GET请求
	 * @return 获取小程序 access_token
	 */
	String fetchAccessToken();
	
	/**
	 * POST请求
	 * @return 获取小程序码是否成功
	 */
	boolean fetchWXACodeUnlimit(MiniProgramCodeParam param,String absoluteDirectory);
	
}
