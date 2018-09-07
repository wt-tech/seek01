package com.wt.seek.exception.handler;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.seek.exception.BusinessException;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;

@ControllerAdvice
public class GloblalExceptionHandler {

	
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	Map<String,Object> SQLHandler(DataAccessException e){
		System.err.println(e.getClass());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.SYS_MESSAGE, e.getMessage());
		map.put(Constants.TIPS, "数据处理错误");
		return map;
	}
	
/*	@ExceptionHandler(Exception.class)
	@ResponseBody
	Map<String,Object> defaultHandler(Exception e) {
		System.err.println(e.getClass());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.SYS_MESSAGE, e.getMessage());
		map.put(Constants.TIPS, "未知错误");
		return map;
	}*/
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	Map<String,Object> businessHanlder(BusinessException e) {
		System.err.println(e.getClass());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.SYS_MESSAGE, e.getMessage());
		map.put(Constants.TIPS, "业务逻辑错误");
		return map;
	}
	
	
	/* *//**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     *//*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Map<String,Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
    	System.err.println(e.getClass());
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.SYS_MESSAGE, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		map.put(Constants.TIPS, "业务逻辑错误");
		return map;
    }*/
}
