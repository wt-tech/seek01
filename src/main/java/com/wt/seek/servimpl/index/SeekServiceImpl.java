package com.wt.seek.servimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.index.ISeekMapper;
import com.wt.seek.entity.City;
import com.wt.seek.entity.County;
import com.wt.seek.entity.Province;
import com.wt.seek.entity.Seek;
import com.wt.seek.entity.SeekImg;
import com.wt.seek.entity.VolunteerArea;
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
	public Integer saveSeek(Seek seek) throws Exception {
		// TODO Auto-generated method stub
		// 保存除了图片之外的其它信息
		seekMapper.saveSeek(seek);
		// 注意返回的是对象.getId()
		return seek.getId();
	}

	@Override
	public boolean saveSeekImg(Integer seekId, MultipartFile file, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		SeekImg seekimg = new SeekImg();
		Seek seek = new Seek();
		seek.setId(seekId);
		seekimg.setSeek(seek);
		if (file != null && !file.isEmpty()) {
			seekMapper.saveSeekImg(seekimg);
			// System.err.println(seekimg.getId());
			// 获取文件名
			String suffix = ImageUtils.getImageTypeWithDot(file);
			// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
			String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.SEEK_IMG, seekimg.getId(),
					suffix);
			if (seekId > 0) { // 保存成功
				// 上传图片
				flag = ImageUtils.saveImage(file, absolutePath);
				// 生成网络访问的路径
				if (flag) {
					String url = ImageUtils.genrateVirtualImgPath(Constants.SEEK_IMG, seekimg.getId(), suffix);
					seekimg.setId(seekimg.getId());
					seekimg.setUrl(url);
					int num = seekMapper.updateSeekImg(seekimg);
					flag = false;
					if (num > 0) {
						flag = true;
					}

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
	public Integer countSeek(Seek seek, String hadBrowsed) {
		// TODO Auto-generated method stub
		return seekMapper.countSeek(seek, hadBrowsed);
	}

	@Override
	public Integer countSeekByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		return seekMapper.countSeekByCustomerId(customerId);
	}

	@Override
	public List<Seek> listSeekByCustomerIdAndSeekType(Integer customerId, String seekType) {
		return seekMapper.listSeekByCustomerIdAndSeekType(customerId, seekType);
	}

	@Override
	public List<Seek> listSimilarSeek(Seek seek) {
		return seekMapper.listSimilarSeek(seek);
	}

	@Override
	public List<Seek> listSeekByCustomerId(Integer customerId, Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return seekMapper.listSeekByCustomerId(customerId, currentPageNo, pageSize);
	}

	@Override
	public boolean deleteSeek(int id) {
		// TODO Auto-generated method stub
		return seekMapper.deleteSeek(id) > 0;
	}

	@Override
	public boolean updateSeek(Seek seek) {
		// TODO Auto-generated method stub
		return seekMapper.updateSeek(seek) > 0;
	}
	
	@Override
	public List<Province> listProvince() {
		// TODO Auto-generated method stub
		return seekMapper.listProvince();
	}

	@Override
	public List<City> listCity(Integer id) {
		// TODO Auto-generated method stub
		return seekMapper.listCity(id);
	}

	@Override
	public List<County> listCounty(Integer id) {
		// TODO Auto-generated method stub
		return seekMapper.listCounty(id);
	}

	@Override
	public VolunteerArea getVolunteerArea(Integer loginid) {
		// TODO Auto-generated method stub
		return seekMapper.getVolunteerArea(loginid);
	}

}
