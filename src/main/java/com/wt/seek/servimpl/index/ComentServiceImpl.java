package com.wt.seek.servimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.index.IComentMapper;
import com.wt.seek.entity.Coment;
import com.wt.seek.service.index.IComentService;

@Service()
public class ComentServiceImpl implements IComentService {

	@Autowired
	private IComentMapper comentMapper;

	@Override
	public List<Coment> listComent(Integer id, Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return comentMapper.listComent(id,currentPageNo,pageSize);
	}

	@Override
	public boolean saveComent(Coment coment) throws Exception {
		// TODO Auto-generated method stub
		return comentMapper.saveComent(coment)>0;
	}

	@Override
	public boolean deleteComent(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return comentMapper.deleteComent(id)>0;
	}
}
