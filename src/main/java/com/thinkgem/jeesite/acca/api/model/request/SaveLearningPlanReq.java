package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 待支付修改学习规划
 * @author Young
 * @version 2016-09-09
 */
@ApiModel(value = "SaveLearningPlanReq",description="待支付修改学习规划")
public class SaveLearningPlanReq extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "订单id",required=true)
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
