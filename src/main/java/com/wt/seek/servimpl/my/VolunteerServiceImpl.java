package com.wt.seek.servimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.my.IVolunteerMapper;
import com.wt.seek.entity.Volunteer;
import com.wt.seek.service.my.IVolunteerService;
import com.wt.seek.tool.VolunteerImage;

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
	public boolean saveVolunteer(Volunteer volunteer, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		// 保存除了图片之外的其它信息
		int volunteerId = volunteerMapper.saveVolunteer(volunteer);
		boolean flag = VolunteerImage.volunteerImage(volunteerId, volunteer, negativIdentityUrl, positiveIdentityUrl,
				staticsPath, volunteerMapper);
		return flag;
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
	public boolean updateVolunteer(Volunteer volunteer, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = VolunteerImage.volunteerImage(volunteer.getId(), volunteer, negativIdentityUrl,
				positiveIdentityUrl, staticsPath, volunteerMapper);
		return flag;

	}

}
