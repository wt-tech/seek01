package com.wt.seek.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Seek;
import com.wt.seek.entity.SeekImg;

public interface ISeekMapper {

	/**
	 * 根据筛选条件查询寻亲记录，其中hadBrowsed代表是否过滤已经查看，true为过滤，false为不过滤
	 * 
	 * @param hadBrowsed
	 * @param currentPageNo   当前页
	 * @param pageSize   每页的数量
	 * @param seek
	 * @return
	 */
	List<Seek> listSeek(@Param("seek") Seek seek, @Param("hadBrowsed") String hadBrowsed,
			@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 插入寻亲记录
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	Integer saveSeek(Seek seek) throws Exception;

	/**
	 * 插入寻亲的图片
	 * 
	 * @param seekimg
	 * @return
	 * @throws Exception
	 */
	Integer saveSeekImg(SeekImg seekimg) throws Exception;

	/**
	 * 查询单条寻亲记录
	 * 
	 * @param id
	 * @return
	 */
	Seek getSeek(@Param("id") int id);

	
	/**
	 * 查询我的发布
	 * 
	 * @param id
	 * @return
	 */
	List<Seek> listSeekByCustomerId(@Param("customerId") Integer customerId);

	/**
	 * 查询表中所有的记录
	 * 
	 * @return
	 */
	Integer countSeek();

	
	List<Seek> listSeekByCustomerIdAndSeekType(
			@Param("customerId")Integer customerId,@Param("seekType")String seekType);
	
	List<Seek> listSimilarSeek(@Param("seek") Seek seek);

}
