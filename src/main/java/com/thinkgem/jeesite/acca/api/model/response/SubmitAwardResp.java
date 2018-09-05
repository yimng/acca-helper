package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.article.entity.AppAward;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

public class SubmitAwardResp extends BaseResponse {

	private static final long serialVersionUID = -8471199804544776610L;
	
	private AppAward award;
	
	
	public SubmitAwardResp(){
		
	}
	
	public SubmitAwardResp(int respCode){
		super(respCode);
	}
	public SubmitAwardResp(int respCode,String respDesc){
		super(respCode,respDesc);
	}
	
	public SubmitAwardResp(AppAward award){
		super(RespConstants.GLOBAL_SUCCESS);
		this.award = award;
	}

	public AppAward getAward() {
		return award;
	}

	public void setAward(AppAward comment) {
		this.award = comment;
	}

	



}
