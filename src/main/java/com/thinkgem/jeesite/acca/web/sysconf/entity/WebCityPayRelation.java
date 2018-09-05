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
public class WebCityPayRelation extends DataEntity<WebCityPayRelation> {

	private static final long serialVersionUID = 1L;
	private Integer cityId;		// 城市id
	private Integer payConfId;		//支付帐户id
	private String payConfName;		//支付帐户名称

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getPayConfId() {
		return payConfId;
	}

	public void setPayConfId(Integer payConfId) {
		this.payConfId = payConfId;
	}

	public String getPayConfName() {
		return payConfName;
	}

	public void setPayConfName(String payConfName) {
		this.payConfName = payConfName;
	}
}