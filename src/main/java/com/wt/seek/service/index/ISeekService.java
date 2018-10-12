package com.wt.seek.service.index;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.City;
import com.wt.seek.entity.County;
import com.wt.seek.entity.Province;
import com.wt.seek.entity.Seek;
import com.wt.seek.entity.VolunteerArea;

public interface ISeekService {
	
	List<Seek> listSeek(Seek seek,String hadBrowsed,Integer currentPageNo,Integer pageSize);
	
	Integer saveSeek(Seek seek) throws Exception;
	
	boolean saveSeekImg(Integer seekId, MultipartFile file,String staticsPath) throws Exception;
	
	Seek getSeek(int id);
	
	List<Seek> listSeekByCustomerId(Integer customerId,Integer currentPageNo, Integer pageSize);
	
	boolean deleteSeek(int id);

	boolean updateSeek(Seek seek);
	
	Integer countSeek(Seek seek,String hadBrowsed);
	
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
    
	/**
	 * 查询省份
	 * @return
	 */
	public List<Province> listProvince();
	/**
	 * 查询城市
	 * @param id
	 * @return
	 */
	public List<City> listCity(Integer id);
	/**
	 * 查询县区
	 * @param id
	 * @return
	 */
	public List<County> listCounty(Integer id);
	
	 /**
     * loginid为用户id，在确定该用户为志愿者后，
     * 根据用户id查询志愿者id，再根据志愿者id查询所在的省市县
     * loginid
     * @return
     */
	VolunteerArea getVolunteerArea(Integer loginid);
}
