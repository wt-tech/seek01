package com.wt.seek.service.index;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.Seek;

public interface ISeekService {
	
	List<Seek> listSeek(Seek seek,String hadBrowsed,Integer currentPageNo,Integer pageSize);
	
	boolean saveSeek(Seek seek, MultipartFile file,String staticsPath) throws Exception;
	
	Seek getSeek(int id);
	
	Integer countSeek();
}
