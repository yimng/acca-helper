package com.thinkgem.jeesite.acca.api.exam.entity;

import java.io.Serializable;

import com.thinkgem.jeesite.freetek.api.model.BaseModel;

public class AppExamVersion extends BaseModel implements Serializable  {
	
	private static final long serialVersionUID = 2444965368519742330L;

	private Long examVersionId;
	
	private String examVersionName;

	public Long getExamVersionId() {
		return examVersionId;
	}

	public void setExamVersionId(Long examVersionId) {
		this.examVersionId = examVersionId;
	}

	public String getExamVersionName() {
		return examVersionName;
	}

	public void setExamVersionName(String examVersionName) {
		this.examVersionName = examVersionName;
	}


}
