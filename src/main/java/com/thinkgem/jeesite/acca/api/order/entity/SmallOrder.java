package com.thinkgem.jeesite.acca.api.order.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinkgem.jeesite.freetek.util.JsonSerializer.CustomDoubleSerialize;

public class SmallOrder implements Serializable {

	private static final long serialVersionUID = -1293992623701108588L;
	
	private Long orderId;       // 订单ID
	
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double amount;		// 报名费用
	
	//private String checkReason; //拒绝理由
	
	private List<SmallSignup> signupList;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<SmallSignup> getSignupList() {
		return signupList;
	}

	public void setSignupList(List<SmallSignup> signupList) {
		this.signupList = signupList;
	}

}
