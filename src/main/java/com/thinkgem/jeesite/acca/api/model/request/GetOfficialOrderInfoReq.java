package com.thinkgem.jeesite.acca.api.model.request;

import io.swagger.annotations.ApiModel;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

@ApiModel("GetOfficialOrderInfoReq")
public class GetOfficialOrderInfoReq extends BaseRequest {

	private static final long serialVersionUID = 6901956833747153759L;
	
	private Long orderId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
