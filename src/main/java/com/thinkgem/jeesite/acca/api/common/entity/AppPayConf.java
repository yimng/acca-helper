/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.entity;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * AppPayConfEntity
 * @author Ivan
 * @version 2016-08-22
 */
public class AppPayConf extends DataEntity<AppPayConf> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "支付名称")
	private String payName;		// 支付名称
	@JsonIgnore
	private Long imageId;		// icon图片id
	@JsonIgnore
	private String receivePayJson;		// 收款信息，json字符串
	@ApiModelProperty(value = "支付icon图片")
	private FileInfo image;
	@ApiModelProperty(value = "支付信息列表")
	private List<AppReceivePayInfo> receivePayList;
	public AppPayConf() {
		super();
	}

	public AppPayConf(String id){
		super(id);
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}
	
	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	
	public String getReceivePayJson() {
		return receivePayJson;
	}

	public void setReceivePayJson(String receivePayJson) {
		this.receivePayJson = receivePayJson;
	}

	public FileInfo getImage() {
		return image;
	}

	public void setImage(FileInfo image) {
		this.image = image;
	}

	public List<AppReceivePayInfo> getReceivePayList() {
		if(StringUtils.isEmpty(this.receivePayJson)){
			this.receivePayList =new ArrayList<AppReceivePayInfo>();
		}else{
			this.receivePayList =JsonMapper.getInstance().fromJson(this.receivePayJson, JsonMapper.getInstance().createCollectionType(ArrayList.class, AppReceivePayInfo.class));
		}
		return receivePayList;
	}

	public void setReceivePayList(List<AppReceivePayInfo> receivePayList) {
		this.receivePayList = receivePayList;
	}
	
}