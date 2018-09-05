package com.wt.seek.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Seek;
import com.wt.seek.entity.TopComent;

public interface ITopComentMapper {

	/**
	 * 根据传过来的寻亲id,查询第一级评论
	 * @param seek
	 * @return
	 */
	List<TopComent> listTopComent(@Param("id") Integer id,@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 保存第一级评论
	 * @param topComent
	 * @return
	 * @throws Exception
	 */
	Integer saveTopComent(TopComent topComent) throws Exception;
	
	/**
	 * 查询表中所有的记录
	 * 
	 * @return
	 */
	Integer countTopComent(Seek seek);
}
