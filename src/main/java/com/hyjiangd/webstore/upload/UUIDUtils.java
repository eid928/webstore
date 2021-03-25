package com.hyjiangd.webstore.upload;

import java.util.UUID;

public class UUIDUtils {
	
	public static String getUUID() {
		
		return UUID.randomUUID().toString().replace("-", "");
	}
}
