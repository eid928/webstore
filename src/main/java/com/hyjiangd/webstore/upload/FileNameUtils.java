package com.hyjiangd.webstore.upload;

public class FileNameUtils {
	
	public static String getSuffix(String fileName) { // 取得副檔名
		
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	public static String getFileName(String fileOriginName) {
		
		return UUIDUtils.getUUID() + getSuffix(fileOriginName);
	}
}
