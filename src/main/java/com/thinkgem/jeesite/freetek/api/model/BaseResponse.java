package com.thinkgem.jeesite.freetek.api.model;

import java.util.Date;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;

import io.swagger.annotations.ApiModelProperty;

public class BaseResponse extends BaseModel{
	
	private static final long serialVersionUID = 14698503181606304L;
	
	@ApiModelProperty(value = "返回码",required=true)
	private Integer respCode;
	@ApiModelProperty(value = "返回描述",required=true)
	private String respDesc;
	@ApiModelProperty(value = "系统时间")
	private Date serverTime = new Date();
	
	public BaseResponse() {
	}
	
	public BaseResponse(Integer respCode) {
		super();
		this.respCode = respCode;
		this.respDesc = RespConstants.respMap.get(respCode);
	}
	
	public BaseResponse(Integer respCode, String respDesc) {
		super();
		this.respCode = respCode;
		this.respDesc = respDesc;
	}
	public Integer getRespCode() {
		return respCode;
	}
	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}
	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public Date getServerTime() {
		return serverTime;
	}

	public void setServerTime(Date serverTime) {
		this.serverTime = serverTime;
	}
	
}
