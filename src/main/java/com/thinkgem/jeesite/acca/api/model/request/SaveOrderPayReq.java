package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "SaveOrderPayReq",description="按照订单id提交支付凭证，")
public class SaveOrderPayReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "订单id",required=true)
	private Long orderId;
	@ApiModelProperty(value = "支付截图id",required=true)
	private Long orderPayImgId;
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.orderId==null || this.orderId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(orderPayImgId==null || orderPayImgId==0){
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


	public Long getOrderPayImgId() {
		return orderPayImgId;
	}


	public void setOrderPayImgId(Long orderPayImgId) {
		this.orderPayImgId = orderPayImgId;
	}

}
