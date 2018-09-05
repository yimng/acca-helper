package com.thinkgem.jeesite.acca.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

@ApiModel(value = "GetOrderByMonthReq",description="按照月份获取F5-P9订单信息")
public class GetOrderByMonthReq extends BaseRequest {

	private static final long serialVersionUID = -2675954591032940708L;
	
	@ApiModelProperty(value = "考季年月字符串")
	private String examTimeStr;
	
	private Long accaUserId;

	public String getExamTimeStr() {
		return examTimeStr;
	}

	public void setExamTimeStr(String examTimeStr) {
		this.examTimeStr = examTimeStr;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

}
