package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.order.entity.SmallDetailOrder;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

public class GetOfficialOrderInfoResp extends BaseResponse {

	private static final long serialVersionUID = -8471199804544776610L;
	
	private SmallDetailOrder detailOrder;
	
	private String payConfTips;
	
	public GetOfficialOrderInfoResp(){
		
	}
	
	public GetOfficialOrderInfoResp(int respCode){
		super(respCode);
	}
	
	public GetOfficialOrderInfoResp(SmallDetailOrder detailOrder){
		super(RespConstants.GLOBAL_SUCCESS);
		this.detailOrder = detailOrder;
	}
	
	public GetOfficialOrderInfoResp(SmallDetailOrder detailOrder,String payConfTips){
		super(RespConstants.GLOBAL_SUCCESS);
		this.detailOrder = detailOrder;
		this.payConfTips= payConfTips;
	}

	public SmallDetailOrder getDetailOrder() {
		return detailOrder;
	}

	public void setDetailOrder(SmallDetailOrder detailOrder) {
		this.detailOrder = detailOrder;
	}

	public String getPayConfTips() {
		return payConfTips;
	}

	public void setPayConfTips(String payConfTips) {
		this.payConfTips = payConfTips;
	}

}
