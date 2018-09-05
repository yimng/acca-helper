package com.thinkgem.jeesite.freetek.api.constant;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class PublicConstants {
	
	public static final String STR_DATETIMEFORMAT = "yyyy-MM-dd HH:mm";
	public static final SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat(STR_DATETIMEFORMAT);
	
	public static String file_base_url = "";//文件服务器所在跟url
	public static String file_base_dir = "";//文件服务器物理根地址
	
	public static final String SMS_VCODE_DEFAULT = "8888";

	@Value("${upload.file.base.url}")
	public  void setFile_base_url(String file_base_url) {
		PublicConstants.file_base_url = file_base_url;
	}
	
	public  String getFile_base_dir() {
		return file_base_dir;
	}
	@Value("${upload.file.base.dir}")
	public  void setFile_base_dir(String file_base_dir) {
		PublicConstants.file_base_dir = file_base_dir;
	}

	public static String getFile_base_url() {
		return file_base_url;
	}

	
}
