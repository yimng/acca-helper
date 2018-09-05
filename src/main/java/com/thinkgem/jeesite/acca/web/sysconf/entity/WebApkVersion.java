/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * WebApkVersionEntity
 * @author Ivan
 * @version 2016-08-23
 */
public class WebApkVersion extends DataEntity<WebApkVersion> {
	
	private static final long serialVersionUID = 1L;
	private String version;		// 版本号
	private String buildNumber;		// 版本编号
	private String packageName;		// 包名
	private String description;		// 升级描述
	private int downloadId;		// 下载文件服务器地址
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	private FileInfo image;
	
	
	public WebApkVersion() {
		super();
	}

	public WebApkVersion(String id){
		super(id);
	}

	@Length(min=0, max=50, message="版本号长度必须介于 0 和 50 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=11, message="版本编号长度必须介于 0 和 11 之间")
	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	@Length(min=0, max=200, message="包名长度必须介于 0 和 200 之间")
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	@Length(min=0, max=500, message="升级描述长度必须介于 0 和 500 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getDownloadId() {
		return downloadId;
	}

	public void setDownloadId(int downloadId) {
		this.downloadId = downloadId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="create_time不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="update_time不能为空")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public FileInfo getImage() {
		return image;
	}

	public void setImage(FileInfo image) {
		this.image = image;
	}

	
}