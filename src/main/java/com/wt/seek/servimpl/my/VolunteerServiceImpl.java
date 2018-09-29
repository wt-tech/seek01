package com.wt.seek.servimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.my.IVolunteerMapper;
import com.wt.seek.entity.Volunteer;
import com.wt.seek.service.my.IVolunteerService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ImageUtils;

@Service()
public class VolunteerServiceImpl implements IVolunteerService {

	@Autowired
	private IVolunteerMapper volunteerMapper;

	@Override
	public List<Volunteer> listVolunteer(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return volunteerMapper.listVolunteer(currentPageNo, pageSize);
	}

	@Override
	public Integer saveVolunteer(Volunteer volunteer) throws Exception {
		// TODO Auto-generated method stub
		// 保存除了图片之外的其它信息
	    volunteerMapper.saveVolunteer(volunteer);
		return volunteer.getId();
	}

	@Override
	public Integer countVolunteer() {
		// TODO Auto-generated method stub
		return volunteerMapper.countVolunteer();
	}

	@Override
	public Volunteer getVolunteer(int customerId) throws Exception {
		// TODO Auto-generated method stub
		return volunteerMapper.getVolunteer(customerId);
	}
	
	@Override
	public Volunteer getBackVolunteer(int id) throws Exception {
		// TODO Auto-generated method stub
		return volunteerMapper.getBackVolunteer(id);
	}
	@Override
	public boolean updateVolunteer(Volunteer volunteer, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
				boolean flag = false;
				MultipartFile file = null;
				if (null != negativIdentityUrl)
					file = negativIdentityUrl;
				if (null != positiveIdentityUrl)
					file = positiveIdentityUrl;
				// 把两张图片放到一个数组里
				// MultipartFile[] file = { negativIdentityUrl, positiveIdentityUrl };
				if (volunteer.getId() > 0) { // 保存成功
					if (null != file) {
						// for (int i = 0; i < file.length; i++) {
						// 存储图片
						String suffix = ImageUtils.getImageTypeWithDot(file);
						// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
						String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.VOLUNTEER, volunteer.getId(),
								suffix);
						// 上传图片
						flag= ImageUtils.saveImage(file, absolutePath);
						String url = ImageUtils.genrateVirtualImgPath(Constants.VOLUNTEER, volunteer.getId(), suffix);
						if (flag) {// 图片存储成功
							if (null != negativIdentityUrl)
								volunteer.setNegativeIdentityUrl(url);
							if (null != positiveIdentityUrl)
								volunteer.setPositiveIdentityUrl(url);
							// 更新认证的图片
						}
					}
				}
				flag = volunteerMapper.updateVolunteer(volunteer) > 0;

				return flag;
			}
}
