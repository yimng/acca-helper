package com.thinkgem.jeesite.acca.api.model.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCart;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;

public class GetSelfExamCartResp extends BasePageResponse<AppExamSelfCart>{
	
	@ApiModelProperty(value = "购物车总价格",required=true)
	private double totalAmount;
	@ApiModelProperty(value = "考试支付提示",required=true)
	private String payConfTips;
	
	public GetSelfExamCartResp(Integer respCode) {
		super(respCode);
	}
	
	public GetSelfExamCartResp(List<AppExamSelfCart> list,double totalAmount) {
		super(list);
		this.totalAmount = totalAmount;
	}
	public GetSelfExamCartResp(List<AppExamSelfCart> list,double totalAmount,String payConfTips) {
		super(list);
		this.totalAmount = totalAmount;
		this.payConfTips=payConfTips;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPayConfTips() {
		return payConfTips;
	}

	public void setPayConfTips(String payConfTips) {
		this.payConfTips = payConfTips;
	}
	
}
