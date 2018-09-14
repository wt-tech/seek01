package com.wt.seek.dao.my;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Message;

public interface IMessageMapper {

	/**
	 * 查看是否有新消息提醒
	 * @param customerId
	 * @return
	 */
	Message getMessage(@Param("customerId") Integer customerId);


    /**
     * 修改状态
     * @param message
     * @return
     * @throws Exception
     */
	Integer updateMessage(@Param("customerId") Integer customerId) throws Exception;

}
