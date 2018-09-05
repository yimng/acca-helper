package com.thinkgem.jeesite.acca.api.model.request;

import io.swagger.annotations.ApiModel;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

@ApiModel("CancelOrderReq")
public class CancelOrderReq extends BaseRequest {

	private static final long serialVersionUID = 8842329953404086706L;
	
	private Long orderId;
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		if(this.orderId==null || this.orderId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		return RespConstants.GLOBAL_SUCCESS;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
