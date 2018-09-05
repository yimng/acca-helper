package com.thinkgem.jeesite.acca.api.model.response;

import io.swagger.annotations.ApiModelProperty;

import com.thinkgem.jeesite.freetek.api.model.BaseModel;

public class AccaConfInfo extends BaseModel{
	
	@ApiModelProperty(value = " F5-P7报考流程",required=true)
	private String officialExamProcess;		// F5-P7报考流程

	public String getOfficialExamProcess() {
		return officialExamProcess;
	}

	public void setOfficialExamProcess(String officialExamProcess) {
		this.officialExamProcess = officialExamProcess;
	}
	

	
}
