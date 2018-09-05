package com.thinkgem.jeesite.freetek.file.entity;

import java.util.List;

public class UploadFileResp {
	
	private int respCode;
	private String respDesc;
	
	private String version;
	private String buildNumber;
	private String packageName;
	
	private List<FileInfo> fileList;
	
	public UploadFileResp() {
	}
	
	public UploadFileResp(int respCode) {
		this.respCode = respCode;
		this.respDesc = RespConstants.respMap.get(respCode);
	}
	
	
	
	public UploadFileResp(int respCode, String respDesc) {
		super();
		this.respCode = respCode;
		this.respDesc = respDesc;
	}

	public UploadFileResp(int respCode,List<FileInfo> fileList){
		this(respCode);
		this.fileList=fileList;
	}
	
	public UploadFileResp(int respCode, String version, String buildNumber, String packageName,
			List<FileInfo> fileList) {
		this(respCode);
		this.version = version;
		this.buildNumber = buildNumber;
		this.packageName = packageName;
		this.fileList = fileList;
	}

	public List<FileInfo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileInfo> fileList) {
		this.fileList = fileList;
	}

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
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
	
}
