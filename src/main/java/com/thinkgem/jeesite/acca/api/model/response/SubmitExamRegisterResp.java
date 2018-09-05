package com.thinkgem.jeesite.acca.api.model.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCart;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSignup;
import com.thinkgem.jeesite.acca.api.order.entity.AppOrder;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;

public class SubmitExamRegisterResp extends BasePageResponse<AppExamSignup>{
	
	@ApiModelProperty(value = "订单信息",required=true)
	private AppOrder order;
	@ApiModelProperty(value = "订单支付提示信息",required=true)
	private String payConfTips;
	
	public SubmitExamRegisterResp(Integer respCode) {
		super(respCode);
	}
	
	public SubmitExamRegisterResp(List<AppExamSignup> list,AppOrder order) {
		super(list);
		this.order = order;
	}
	public SubmitExamRegisterResp(List<AppExamSignup> list,AppOrder order,String payConfTips) {
		super(list);
		this.order = order;
		this.payConfTips=payConfTips;
	}

	public AppOrder getOrder() {
		return order;
	}

	public void setOrder(AppOrder order) {
		this.order = order;
	}

	public String getPayConfTips() {
		return payConfTips;
	}

	public void setPayConfTips(String payConfTips) {
		this.payConfTips = payConfTips;
	}

}
