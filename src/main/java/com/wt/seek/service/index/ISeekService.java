package com.wt.seek.service.index;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.Seek;

public interface ISeekService {
	
	List<Seek> listSeek(Seek seek,String hadBrowsed,Integer currentPageNo,Integer pageSize);
	
	Integer saveSeek(Seek seek) throws Exception;
	
	boolean saveSeekImg(Integer seekId, MultipartFile file,String staticsPath) throws Exception;
	
	Seek getSeek(int id);
	
	List<Seek> listSeekByCustomerId(Integer customerId,Integer currentPageNo, Integer pageSize);
	
	Integer countSeek();
	
	Integer countSeekByCustomerId(Integer customerId);

	
	/**
	 * 查找某个用户发表的寻人/亲信息
	 * @param customerId
	 * @param seekType
	 */
	List<Seek> listSeekByCustomerIdAndSeekType(
			Integer customerId,String seekType);
	
	/**
	 * 做模糊匹配使用,查找和seek相似的寻亲记录
	 * 主要从出生省份,失踪省份,寻亲类型匹配
	 */
	List<Seek> listSimilarSeek(Seek seek);

}
