/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * AppMobileDeviceContactEntity
 * @author Ivan
 * @version 2016-08-19
 */
public class MobileDeviceContact extends DataEntity<MobileDeviceContact> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// device_id
	private String contactName;		// contact_name
	private String contactPhone;		// contact_phone
	
	private Long accaUserId;
	
	public MobileDeviceContact() {
		super();
	}

	public MobileDeviceContact(String id){
		super(id);
	}

	@Length(min=0, max=100, message="device_id长度必须介于 0 和 100 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=64, message="contact_name长度必须介于 0 和 64 之间")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Length(min=0, max=64, message="contact_phone长度必须介于 0 和 64 之间")
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
}