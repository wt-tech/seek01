package com.wt.seek.tool;

import com.wt.seek.exception.BusinessException;

/**
 * 业务工具类,
 * 目前只能抛出业务异常
 * @author Daryl
 *
 */
public class BusinessUtils {

	/**
	 * 手动抛出一个业务异常
	 * @param message
	 */
	public static void throwNewBusinessException(String message) {
		throw new BusinessException(message);
	}
	
}
