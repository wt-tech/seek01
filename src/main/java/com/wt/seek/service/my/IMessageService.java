package com.wt.seek.service.my;

public interface IMessageService {

	/**
	 * 查看是否有新消息提醒
	 * @param customerId
	 * @return
	 */
	boolean getMessage(Integer customerId);


    /**
     * 修改状态
     * @param message
     * @return
     * @throws Exception
     */
	boolean updateMessage(Integer customerId) throws Exception;

}
