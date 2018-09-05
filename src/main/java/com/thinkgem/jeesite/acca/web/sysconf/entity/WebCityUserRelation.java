/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 城市管理Entity
 * @author Young

 * @version 2016-09-22
 */
public class WebCityUserRelation extends DataEntity<WebCityUserRelation> {

	private static final long serialVersionUID = 1L;
	private Integer cityId;		// 城市id
	private String sysUserId;		//管理员用户id
	private String userName;		//管理员用户名

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}