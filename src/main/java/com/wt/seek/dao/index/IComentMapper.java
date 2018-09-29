package com.wt.seek.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Coment;

public interface IComentMapper {

	/**
	 * 根据传过来的寻亲id,查询第一级评论下面的评论
	 * 
	 * @param seek
	 * @return
	 */
	List<Coment> listComent(@Param("id") Integer id, @Param("currentPageNo") Integer currentPageNo,
			@Param("pageSize") Integer pageSize);

	/**
	 * 保存第一级下面的评论
	 * 
	 * @param Coment
	 * @return
	 * @throws Exception
	 */
	Integer saveComent(Coment coment) throws Exception;

	/**
	 * 删除评论
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer deleteComent(@Param("id") Integer id) throws Exception;
}
