/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.register.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 代注册费用Entity
 * @author Young
 * @version 2016-08-09
 */
public class AppConfRegisterFee extends DataEntity<AppConfRegisterFee> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "类型：1acca注册，2fia注册")
	private Integer type;		// 类型：1acca注册，2fia注册
	@ApiModelProperty(value = "注册费用：保留小数点后两位")
	private BigDecimal amount;		// 注册费用：保留小数点后两位
	
	public AppConfRegisterFee() {
		super();
	}

	public AppConfRegisterFee(String id){
		super(id);
	}

	@NotNull(message="类型：1acca注册，2fia注册不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}