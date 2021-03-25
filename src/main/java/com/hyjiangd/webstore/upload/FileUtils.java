package com.hyjiangd.webstore.upload;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	public static String upload(MultipartFile file, String path, String fileName) {
		
		String newFileName = FileNameUtils.getFileName(fileName);
		String fullPath = path + "/" + newFileName;
		
		File dest = new File(fullPath);
		
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}
		
		try {
			file.transferTo(dest);
			return newFileName;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}
}
