package com.thinkgem.jeesite.acca.api.model.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.thinkgem.jeesite.acca.api.common.entity.AppPayConf;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCart;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;

public class GetPayConfInfoResp extends BasePageResponse<AppPayConf>{
	
	@ApiModelProperty(value = "考试支付提示",required=true)
	private String payConfTips;
	
	public GetPayConfInfoResp(Integer respCode) {
		super(respCode);
	}
	
	public GetPayConfInfoResp(List<AppPayConf> list) {
		super(list);
	}
	public GetPayConfInfoResp(List<AppPayConf> list,String payConfTips) {
		super(list);
		this.payConfTips=payConfTips;
	}

	public String getPayConfTips() {
		return payConfTips;
	}

	public void setPayConfTips(String payConfTips) {
		this.payConfTips = payConfTips;
	}
	
}
