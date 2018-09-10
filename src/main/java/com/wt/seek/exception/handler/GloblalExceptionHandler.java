package com.wt.seek.exception.handler;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.seek.exception.BusinessException;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;


/**
 * 统一异常处理类.主要处理三个方面.<br/>
 * 1.前端接收的参数的验证<br/>
 * 2.业务逻辑本身抛出的异常<br/>
 * 3.未知的错误,异常<br/>
 * @author Daryl
 */
@ControllerAdvice
public class GloblalExceptionHandler {
	
	private Logger logger = LogManager.getLogger(GloblalExceptionHandler.class);
	
	/**
	 * SQL语句执行错误,会被此Handler处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	Map<String,Object> DataAccessExceptionHandler(DataAccessException e){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		this.commonInfoAssemble(map, e);
		map.put(Constants.ERRORS, e.getMessage());
		map.put(Constants.TIPS, Constants.DATA_ACCESS_DESCRIPTION);
		logger.error(Constants.DATA_ACCESS_DESCRIPTION + ":" +e.getMessage());
		return map;
	}
	
	/**
	 * 默认的Handler.其他Handler处理不了,此Handler处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	Map<String,Object> defaultHandler(Exception e) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		this.commonInfoAssemble(map, e);
		map.put(Constants.ERRORS, e.getMessage());
		map.put(Constants.TIPS, Constants.EXCEPTION_DESCRIPTION);
		logger.error(Constants.EXCEPTION_DESCRIPTION + ":" +e.getMessage());
		return map;
	}
	
	/**
	 * 业务逻辑Handler
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	Map<String,Object> businessExceptionHanlder(BusinessException e) {
		Map<String,Object> map = MapUtils.getHashMapInstance();
		this.commonInfoAssemble(map, e);
		map.put(Constants.ERRORS, e.getMessage());
		map.put(Constants.TIPS, Constants.BUSINESS_DESCRIPTION);
		logger.error(Constants.BUSINESS_DESCRIPTION + ":" +e.getMessage());
		return map;
	}
	
	
	/**
     * 当参数使用<b>RequestBody</b>接收时,若参数验证不同过,<br/>
     * 会抛出{@link MethodArgumentNotValidException}异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Map<String,Object> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		this.commonInfoAssemble(map, e);
		map.put(Constants.ERRORS, this.errorInfoAssemble(e.getBindingResult()));
		map.put(Constants.TIPS, Constants.ARGUMENT_ILLEGAL);
		logger.error(Constants.ARGUMENT_ILLEGAL + ":" +e.getMessage());
		return map;
    }
    
    
    /**
     * 当参数使用<b>RequestParam</b>或者不使用注解接收时,
     * 若参数验证不同过,<br/>
     * 会抛出{@link BindException}异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    Map<String,Object> bindExceptionHanlder(BindException e){
    	Map<String,Object> map = MapUtils.getHashMapInstance();
    	this.commonInfoAssemble(map, e);
		map.put(Constants.ERRORS, this.errorInfoAssemble(e.getBindingResult()));
		map.put(Constants.TIPS, Constants.ARGUMENT_ILLEGAL);
		logger.error(Constants.ARGUMENT_ILLEGAL + ":" +e.getMessage());
		return map;
    }
    
    /**
     * 组装result中所有的Error信息到Map,并返回.
     * @param result
     */
    private Map<String,Object> errorInfoAssemble(BindingResult result){
    	Map<String,Object> map = MapUtils.getHashMapInstance();
    	List<ObjectError> list = result.getAllErrors();
    	for(ObjectError err : list) {
    		int cnt = 0;
    		if(err instanceof FieldError) {
    			map.put(((FieldError)err).getField(), err.getDefaultMessage());//获得对应的属性
    		}else
    			map.put(err.getObjectName()+cnt, err.getDefaultMessage());//非FieldError仅仅获得对象名称
    	}
    	return map;
    }
    
    /**
     * 每个ExceptionHandler都会返回的公共Map信息.<br/>
     * 同时在控制台打印错误信息.或者可以在这里将错误写入日志.
     * @param map
     * @param e
     */
    private void commonInfoAssemble(Map<String,Object> map,Exception e) {
    	map.put(Constants.STATUS, Constants.FAIL);
		map.put(Constants.EXCEPTION_CLASS, e.getClass().toString());
		e.printStackTrace();
    }
}
