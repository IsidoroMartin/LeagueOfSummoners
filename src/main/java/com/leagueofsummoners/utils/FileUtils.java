package com.leagueofsummoners.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	public static String saveImg(MultipartFile file, String username) {
		String imgName = null;
		if (!file.isEmpty()) {
			try {
				imgName = "avatar_" + username + "." + FilenameUtils.getExtension(file.getOriginalFilename());
				String avatarPath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "static" + File.separator + "img"
						+ File.separator + "avatars" + File.separator + username;
				new File(avatarPath).mkdirs();
				
				File saveimg = new File(avatarPath + File.separator + imgName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(saveimg));
				FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
				imgName = null;
			}
		} else {
			System.out.println("File vacio...");
		}
		return imgName;
	}
}
