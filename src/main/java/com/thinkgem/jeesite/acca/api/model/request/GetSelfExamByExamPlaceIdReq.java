package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "GetSelfExamByExamPlaceIdReq",description="F1-F4按照考点id展示考试详情")
public class GetSelfExamByExamPlaceIdReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "考点id")
	private Integer examPlaceId;
	
	
	@Override
	public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.examPlaceId==null || this.examPlaceId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}


	public Integer getExamPlaceId() {
		return examPlaceId;
	}


	public void setExamPlaceId(Integer examPlaceId) {
		this.examPlaceId = examPlaceId;
	}


}
