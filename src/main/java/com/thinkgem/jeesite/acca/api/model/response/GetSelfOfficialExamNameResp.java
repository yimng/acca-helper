package com.thinkgem.jeesite.acca.api.model.response;

import io.swagger.annotations.ApiModelProperty;




import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

public class GetSelfOfficialExamNameResp extends BaseResponse{
	
	@ApiModelProperty(value = "自有考试名称",required=true)
	private String selfExamName;
	
	@ApiModelProperty(value = "官方考试名称",required=true)
	private String officialExamName;
	
	public GetSelfOfficialExamNameResp(Integer respCode) {
		super(respCode);
	}

	
	
	public GetSelfOfficialExamNameResp(String selfExamName,
			String officialExamName) {
		super(RespConstants.GLOBAL_SUCCESS);
		this.selfExamName = selfExamName;
		this.officialExamName = officialExamName;
	}



	public String getSelfExamName() {
		return selfExamName;
	}

	public void setSelfExamName(String selfExamName) {
		this.selfExamName = selfExamName;
	}

	public String getOfficialExamName() {
		return officialExamName;
	}

	public void setOfficialExamName(String officialExamName) {
		this.officialExamName = officialExamName;
	}
	
	

	
	
	
}
