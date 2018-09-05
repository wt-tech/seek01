package com.wt.seek.tool;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadImage {
	public String uploadImage(String path, String projectServerPath, MultipartFile file) throws Exception {
		String originalFilename = file.getOriginalFilename();
		String newfileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
		File filee = new File(path + File.separator + newfileName);
		if (!filee.getParentFile().exists()) {
			filee.getParentFile().mkdirs();
		}
		file.transferTo(filee);
		String serverFilePatn = projectServerPath + newfileName;
		return serverFilePatn;
	}
	
	public static String getProjectServerPath(String tableName, String imageName) {
		String projectServerPath = Constants.imgServerDomain + Constants.ContextPath + File.separator 
								+ tableName + File.separator + imageName;
		return projectServerPath;
	}
}
