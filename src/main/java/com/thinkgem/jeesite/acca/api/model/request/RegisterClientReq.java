package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "RegisterClientReq",description="硬件设备注册参数")
public class RegisterClientReq extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "imei号")
	private String imei;		// imei号
	@ApiModelProperty(value = "mac地址")
	private String macAddr;		// mac地址
	@ApiModelProperty(value = "sim号码")
	private String simNum;		// sim号码
	@ApiModelProperty(value = "操作系统")
	private String mobileOs;		// 操作系统
	@ApiModelProperty(value = "分辨率")
	private String resolution;		// 分辨率
	@ApiModelProperty(value = "手机型号")
	private String agentModel;		// 机型
	@ApiModelProperty(value = "手机号")
	private String phone;		// 当前手机手机号
	
	
	
	public int isCorrectParams() {
		
		return RespConstants.GLOBAL_SUCCESS;
	}



	public String getImei() {
		return imei;
	}



	public void setImei(String imei) {
		this.imei = imei;
	}



	public String getMacAddr() {
		return macAddr;
	}



	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}



	public String getSimNum() {
		return simNum;
	}



	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}



	public String getMobileOs() {
		return mobileOs;
	}



	public void setMobileOs(String mobileOs) {
		this.mobileOs = mobileOs;
	}



	public String getResolution() {
		return resolution;
	}



	public void setResolution(String resolution) {
		this.resolution = resolution;
	}



	public String getAgentModel() {
		return agentModel;
	}



	public void setAgentModel(String agentModel) {
		this.agentModel = agentModel;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}


}
