/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.entity;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * AppApkVersionEntity
 * @author Ivan
 * @version 2016-08-10
 */
public class AppApkVersion extends DataEntity<AppApkVersion> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "版本号")
	private String version;		// 版本号
	@ApiModelProperty(value = "版本编码")
	private String buildNumber;		// 版本编号
	@ApiModelProperty(value = "包名")
	private String packageName;		// 包名
	@ApiModelProperty(value = "升级描述")
	private String description;		// 升级描述
	private String downloadId;		// 下载文件服务器地址
	@ApiModelProperty(value = "下载地址")
	private String downloadUrl; //下载地址
	@JsonIgnore
	private FileInfo file;
	@ApiModelProperty(value = "文件名称")
	private String fileName;
	
	public AppApkVersion() {
		super();
	}

	public AppApkVersion(String id){
		super(id);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDownloadId() {
		return downloadId;
	}

	public void setDownloadId(String downloadId) {
		this.downloadId = downloadId;
	}
	

	public FileInfo getFile() {
		return file;
	}

	public void setFile(FileInfo file) {
		this.file = file;
		
	}

	public String getFileName() {
		if(this.file!=null){
			this.fileName = this.file.getOriginalName();
		}
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDownloadUrl() {
		
		if(this.file!=null){
			this.downloadUrl = this.file.getFileUrl();
		}
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		
		this.downloadUrl = downloadUrl;
	}
	
}