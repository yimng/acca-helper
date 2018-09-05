package com.thinkgem.jeesite.acca.api.model.request;

import java.util.Date;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "GetSelfExamListByCityAndTimeSpanReq",description="按照城市和时间范围获取F1-F4考试列表")
public class GetSelfExamListByCityAndTimeSpanReq extends BaseRequest {
private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "城市id，全国：-1，其他取值表示具体的城市id")
	private Integer examCityId;
	
	@ApiModelProperty(value = "筛选开始时间")
	private Date startTime;
	
	@ApiModelProperty(value = "筛选结束时间")
	private Date endTime;
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.examCityId==null || this.examCityId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(startTime ==null || endTime==null){
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


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	
}
