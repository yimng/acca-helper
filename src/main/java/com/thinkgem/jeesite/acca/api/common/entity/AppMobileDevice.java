/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.entity;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * AppMobileDeviceEntity
 * @author Ivan
 * @version 2016-08-10
 */
public class AppMobileDevice extends DataEntity<AppMobileDevice> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备id
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
	
	private Integer authStatus;//手否通讯录授权状态：1未授权，2已授权
	
	public AppMobileDevice() {
		super();
	}

	public AppMobileDevice(String id){
		super(id);
	}

	@Length(min=1, max=100, message="设备id长度必须介于 1 和 100 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=20, message="imei号长度必须介于 0 和 20 之间")
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@Length(min=0, max=30, message="mac地址长度必须介于 0 和 30 之间")
	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	
	@Length(min=0, max=30, message="sim号码长度必须介于 0 和 30 之间")
	public String getSimNum() {
		return simNum;
	}

	public void setSimNum(String simNum) {
		this.simNum = simNum;
	}
	
	@Length(min=0, max=20, message="操作系统长度必须介于 0 和 20 之间")
	public String getMobileOs() {
		return mobileOs;
	}

	public void setMobileOs(String mobileOs) {
		this.mobileOs = mobileOs;
	}
	
	@Length(min=0, max=20, message="分辨率长度必须介于 0 和 20 之间")
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	
	@Length(min=0, max=30, message="机型长度必须介于 0 和 30 之间")
	public String getAgentModel() {
		return agentModel;
	}

	public void setAgentModel(String agentModel) {
		this.agentModel = agentModel;
	}
	
	@Length(min=1, max=20, message="当前手机手机号长度必须介于 1 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	
}