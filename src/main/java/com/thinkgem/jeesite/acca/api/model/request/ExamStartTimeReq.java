package com.thinkgem.jeesite.acca.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

@ApiModel("ExamStartTimeReq")
public class ExamStartTimeReq extends BaseRequest {

	private static final long serialVersionUID = 3240016231656942396L;
	
	@ApiModelProperty(value = "考试开始时间字符串")
	private String examStartTimeStr;

	@ApiModelProperty(value = "考试城市ID")
	private Long examCityId;

	public String getExamStartTimeStr() {
		return examStartTimeStr;
	}

	public void setExamStartTimeStr(String examStartTimeStr) {
		this.examStartTimeStr = examStartTimeStr;
	}

	public Long getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(Long examCityId) {
		this.examCityId = examCityId;
	}

	@Override
	public int isCorrectParams() {
		// TODO Auto-generated method stub
		int flag = super.isCorrectParams();
		if(flag == RespConstants.GLOBAL_SUCCESS){
			if(StringUtils.isEmpty(examStartTimeStr)){
				return RespConstants.GLOBAL_PARAM_ERROR;
			} else {
				return RespConstants.GLOBAL_SUCCESS;
			}
		} else {
			return flag;
		}
	}

	
}
