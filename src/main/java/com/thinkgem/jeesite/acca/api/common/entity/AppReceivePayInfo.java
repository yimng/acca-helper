/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.entity;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * AppPayConfEntity
 * @author Ivan
 * @version 2016-08-22
 */
public class AppReceivePayInfo extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "支付信息key")
	private String key;		// 支付名称
	@ApiModelProperty(value = "支付信息value")
	private String value;
	
	public AppReceivePayInfo() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}