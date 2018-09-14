package com.wt.seek.controller.my;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.seek.service.my.IMessageService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;

@RestController("")
@RequestMapping("/message")
public class MessageCtrl {
	@Autowired
	private IMessageService messageService;

	private Logger logger = LogManager.getLogger();

	/**
	 * 查看是否有新消息提醒
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getmessage")
	public boolean getMessage(@RequestParam("customerId") Integer customerId) throws Exception {
		return messageService.getMessage(customerId);
	}

	/**
	 * 修改状态
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatemessage")
	public Map<String, Object> updateMessage(@RequestParam("customerId") Integer customerId) throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		boolean flag = messageService.updateMessage(customerId);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}

}
