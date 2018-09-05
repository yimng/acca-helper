package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "DelSelfExamCartReq",description="F1-F4考试：从物车中删除某一个考试")
public class DelSelfExamCartReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "自由考试购物车id：selfExamCartId")
	private Long selfExamCartId;
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.selfExamCartId==null || this.selfExamCartId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		
		return RespConstants.GLOBAL_SUCCESS;
	}


	public Long getSelfExamCartId() {
		return selfExamCartId;
	}


	public void setSelfExamCartId(Long selfExamCartId) {
		this.selfExamCartId = selfExamCartId;
	}


}
