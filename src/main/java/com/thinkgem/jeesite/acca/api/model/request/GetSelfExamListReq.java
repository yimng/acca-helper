package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "GetSelfExamListReq",description="按照城市和时间段获取F1-F4考试列表")
public class GetSelfExamListReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "城市id，全国：-1，其他取值表示具体的城市id")
	private Integer examCityId;
	
	@ApiModelProperty(value = "筛选时间段，取值如下：-1：不限时间；1：近1个月；2：近2个月；3：近3个月；")
	private Integer timeType;
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.examCityId==null || this.examCityId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(timeType ==null || timeType==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}


	public Integer getExamCityId() {
		return examCityId;
	}


	public void setExamCityId(Integer examCityId) {
		this.examCityId = examCityId;
	}


	public Integer getTimeType() {
		return timeType;
	}


	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

}
