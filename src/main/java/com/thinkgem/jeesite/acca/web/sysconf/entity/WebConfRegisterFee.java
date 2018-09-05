/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 注册用户费用管理Entity
 * @author Fan
 * @version 2016-08-23
 */
public class WebConfRegisterFee extends DataEntity<WebConfRegisterFee> {
	
	private static final long serialVersionUID = 1L;
	private Integer type;		// 类型：1acca注册，2fia注册
	private Double amount;		// 注册费用：保留小数点后两位
	
	public WebConfRegisterFee() {
		super();
	}

	public WebConfRegisterFee(String id){
		super(id);
	}

	@NotNull(message="类型：1acca注册，2fia注册不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@NotNull(message="类型：1acca注册，2fia注册不能为空")
	@Min(value=0,message="请输入正确的价格")
	@Max(value=10000,message="请输入正确的价格")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}