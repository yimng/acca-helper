package com.thinkgem.jeesite.freetek.file.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;


public class FileInfo{
	
	private int id;//数据库id
	private String originalName;//上传前的文件名
	private String newName;//服务端重命名后的文件名
	private String basePath;//文件保存服务器物理根地址
	private String relativePath;//文件保存的相对路径。
	private String relativeUrl;//文件保存的相对url
	private String fileUrl;//文件的绝对http地址
	private String mimeType;//文件类型
	private Date createTime;
	private Date updateTime;
	
	private String iconUrl;
	
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String getRelativeUrl() {
		return relativeUrl;
	}
	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
		this.fileUrl = Global.getConfig("upload.file.base.url")+this.relativeUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	
	@Override
    public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
	public String getIconUrl() {
		
		if(StringUtils.isEmpty(this.mimeType)){
			return null;
		}
		
		if(!this.mimeType.contains("image/")){
			return null;
		}
		//String fileExtName = FileUtils.getFileExtName(this.fileUrl);
		//String tempUrl = this.fileUrl.rep;
		
		int lastIndex = this.fileUrl.lastIndexOf(".");
		
		String tempUrl = this.fileUrl.substring(0,lastIndex)+"_icon"+this.fileUrl.substring(lastIndex,this.fileUrl.length());
		
		return tempUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
}
