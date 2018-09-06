package com.wt.seek.service.index;

import java.util.List;

import com.wt.seek.entity.Seek;
import com.wt.seek.entity.TopComent;

public interface ITopComentService {
	
	List<TopComent> listTopComent(Integer id,Integer currentPageNo,Integer pageSize);

	boolean saveTopComent(TopComent topComent) throws Exception;
	
	Integer countTopComent(Seek seek);
}
