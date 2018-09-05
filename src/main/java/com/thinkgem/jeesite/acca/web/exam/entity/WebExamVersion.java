package com.thinkgem.jeesite.acca.web.exam.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class WebExamVersion extends DataEntity<WebExamVersion> {
	
	private static final long serialVersionUID = 2444965368519742330L;

	private Long examVersionId;
	
	private String examVersionName;
	
	private boolean checked;
	
	public WebExamVersion(){}
	
	public WebExamVersion(Long examVersionId, String examVersionName){
		this.examVersionId = examVersionId;
		this.examVersionName = examVersionName;
	}

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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null){
			WebExamVersion wev = (WebExamVersion) obj;
			if(this.examVersionId.equals(wev.getExamVersionId()) 
					&& this.examVersionName.equals(wev.getExamVersionName())){
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}


}
