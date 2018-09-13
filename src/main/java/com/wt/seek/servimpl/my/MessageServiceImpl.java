package com.wt.seek.servimpl.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.my.IMessageMapper;
import com.wt.seek.entity.Message;
import com.wt.seek.service.my.IMessageService;

@Service()
public class MessageServiceImpl implements IMessageService {
	@Autowired
	private IMessageMapper messageMapper;

	@Override
	public boolean getMessage(Integer customerId) {
		// TODO Auto-generated method stub
		Message message=messageMapper.getMessage(customerId);
		if(null !=message)
		return message.getNewMessage()==1;
		else return false;
	}

	@Override
	public boolean updateMessage(Integer customerId) throws Exception {
		// TODO Auto-generated method stub
		return messageMapper.updateMessage(customerId)>0;
	}

}
