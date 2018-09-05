/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.entity;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;

/**
 * AppMobileDeviceContactEntity
 * @author Ivan
 * @version 2016-08-10
 */
public class AppMobileDeviceContact extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// device_id
	@ApiModelProperty(value = "通讯录联系人名称",required=true)
	private String contactName;		// contact_name
	@ApiModelProperty(value = "手机号码",required=true)
	private String contactPhone;		// contact_phone
	
	public AppMobileDeviceContact() {
		super();
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
}