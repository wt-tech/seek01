package com.wt.seek.servimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.index.ISeekMapper;
import com.wt.seek.entity.Seek;
import com.wt.seek.entity.SeekImg;
import com.wt.seek.service.index.ISeekService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ImageUtils;

@Service()
public class SeekServiceImpl implements ISeekService {

	@Autowired
	private ISeekMapper seekMapper;

	@Override
	public List<Seek> listSeek(Seek seek, String hadBrowsed, Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return seekMapper.listSeek(seek, hadBrowsed, currentPageNo, pageSize);
	}

	@Override
	public boolean saveSeek(Seek seek, MultipartFile file,String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		SeekImg seekimg = new SeekImg();
		//保存除了图片之外的其它信息
		int seekId = seekMapper.saveSeek(seek);
		if (!file.isEmpty()) {
			//获取文件名
			String suffix = ImageUtils.getImageTypeWithDot(file);
			//根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
			String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath,Constants.SEEK_IMG, 
					seekId, suffix);
			if (seekId > 0) {     //保存成功
				//上传图片
				flag=ImageUtils.saveImage(file, absolutePath);
				//生成网络访问的路径
				String url = ImageUtils.genrateVirtualImgPath(Constants.SEEK_IMG,seekId, suffix);
				seek.setId(seekId);
				seekimg.setSeek(seek);
				seekimg.setUrl(url);
				int num = seekMapper.saveSeekImg(seekimg);
				if (num > 0) {
					flag = true;
				}
			}
		}
		return flag;
	}

	@Override
	public Seek getSeek(int id) {
		// TODO Auto-generated method stub
		return seekMapper.getSeek(id);
	}

	@Override
	public Integer countSeek() {
		// TODO Auto-generated method stub
		return seekMapper.countSeek();
	}

	@Override
	public List<Seek> listSeekByCustomerIdAndSeekType(Integer customerId, String seekType) {
		return seekMapper.listSeekByCustomerIdAndSeekType(customerId, seekType);
	}

	@Override
	public List<Seek> listSimilarSeek(Seek seek) {
		return seekMapper.listSimilarSeek(seek);
	}

}
