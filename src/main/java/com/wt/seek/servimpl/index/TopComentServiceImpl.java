package com.wt.seek.servimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.index.ITopComentMapper;
import com.wt.seek.entity.Seek;
import com.wt.seek.entity.TopComent;
import com.wt.seek.service.index.ITopComentService;

@Service()
public class TopComentServiceImpl implements ITopComentService {

	@Autowired
	private ITopComentMapper topComentMapper;

	@Override
	public List<TopComent> listTopComent(Integer id,Integer currentPageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		return topComentMapper.listTopComent(id,currentPageNo,pageSize);
	}
	
	@Override
	public List<TopComent> listTopComentByCustomerId(Integer customerId,Integer currentPageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		return topComentMapper.listTopComentByCustomerId(customerId,currentPageNo,pageSize);
	}
	
	@Override
	public List<TopComent> listComentByCustomerId(Integer customerId,Integer currentPageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		return topComentMapper.listComentByCustomerId(customerId,currentPageNo,pageSize);
	}

	@Override
	public boolean saveTopComent(TopComent topComent) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = topComentMapper.saveTopComent(topComent);
		if (num > 0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public Integer countTopComent(Seek seek) {
		// TODO Auto-generated method stub
		return topComentMapper.countTopComent(seek);
	}
	
	@Override
	public Integer countTopComentByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return topComentMapper.countTopComentByCustomerId(customerId);
	}
	
	@Override
	public Integer countComentByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return topComentMapper.countComentByCustomerId(customerId);
	}

}
