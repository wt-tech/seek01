package com.wt.seek.service.index;

import java.util.List;

import com.wt.seek.entity.Coment;

public interface IComentService {

	List<Coment> listComent(Integer id, Integer currentPageNo, Integer pageSize);

	boolean saveComent(Coment coment) throws Exception;
	
	boolean deleteComent(Integer id) throws Exception;
}
