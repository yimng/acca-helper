/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 收款账户管理Entity
 * @author Fan
 * @version 2016-08-22
 */
public class WebPayConf extends DataEntity<WebPayConf> {
	
	private static final long serialVersionUID = 1L;
	private String payName;		// 支付名称
	private Long imageId;		// icon图片id
	private String receivePayJson;		// 收款信息，json字符串
	
	private String name;
	private Integer accountType;//类型：1总部账号，2分部账号


	private FileInfo image;
	
	private List<WebReceivePayInfo> receivePayList;
	
	private String imageStr;
	
	public String getImageStr(){
		return JsonMapper.getInstance().toJson(this.image);
	}
	
	
	public FileInfo getImage() {
		return image;
	}


	public void setImage(FileInfo image) {
		this.image = image;
	}


	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}


	public WebPayConf() {
		super();
	}

	public WebPayConf(String id){
		super(id);
	}

	@Length(min=0, max=32, message="支付名称长度必须介于 0 和 32 之间")
	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}
	@NotNull(message="图片不能为空")
	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	
	@Length(min=3, max=1000, message="收款信息，字符长度必须介于 1和 256 之间")
	public String getReceivePayJson() {
		return receivePayJson;
	}

	public void setReceivePayJson(String receivePayJson) {
		//Encodes.unescapeHtml(receivePayJson);

		this.receivePayJson = Encodes.unescapeHtml(receivePayJson);
	}
	
	public List<WebReceivePayInfo> getReceivePayList() {
		if(StringUtils.isEmpty(this.receivePayJson)){
			this.receivePayList =new ArrayList<WebReceivePayInfo>();
		}else{
			this.receivePayList =JsonMapper.getInstance().fromJson(this.receivePayJson, JsonMapper.getInstance().createCollectionType(ArrayList.class, WebReceivePayInfo.class));
		}
		return receivePayList;
	}

	public void setReceivePayList(List<WebReceivePayInfo> receivePayList) {
		this.receivePayList = receivePayList;
	}


	@Length(min=3, max=1000, message="账户名称，字符床都必须介于1到64")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@NotNull(message="账户类型不能为空")
	public Integer getAccountType() {
		return accountType;
	}


	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
}