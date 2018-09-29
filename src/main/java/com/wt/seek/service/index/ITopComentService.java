package com.wt.seek.service.index;

import java.util.List;

import com.wt.seek.entity.Seek;
import com.wt.seek.entity.TopComent;

public interface ITopComentService {
	
	List<TopComent> listTopComent(Integer id,Integer currentPageNo,Integer pageSize);

	List<TopComent> listTopComentByCustomerId(Integer customerId,Integer currentPageNo,Integer pageSize);
	
	List<TopComent> listComentByCustomerId(Integer customerId,Integer currentPageNo,Integer pageSize);
	
	boolean saveTopComent(TopComent topComent) throws Exception;
	
	Integer countTopComent(Seek seek);
	
	Integer countTopComentByCustomerId(Integer customerId);
	
	Integer countComentByCustomerId(Integer customerId);
	
	boolean deleteTopComent(Integer id);
}
