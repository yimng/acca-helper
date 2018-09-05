/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * WebMobileDeviceEntity
 * @author Ivan
 * @version 2016-08-20
 */
public class WebMobileDevice extends DataEntity<WebMobileDevice> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备id
	private String imei;		// imei号
	private String macAddr;		// mac地址
	private String simNum;		// sim号码
	private String mobileOs;		// 操作系统
	private String resolution;		// 分辨率
	private String agentModel;		// 机型
	private String phone;		// 当前手机手机号
	private int authStatus; 
	
	private Long accaUserId;
	
	public WebMobileDevice() {
		super();
	}

	public WebMobileDevice(String id){
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

	public int getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
}