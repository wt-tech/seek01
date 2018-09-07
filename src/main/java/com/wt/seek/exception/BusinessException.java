package com.wt.seek.exception;

/**
 * 业务异常类.新建一个异常类如下:<br/>
 * new BusinessException ('客户ID不能为负值');
 * @author Daryl
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BusinessException(String message) {
		super(message);
	}
	
}
