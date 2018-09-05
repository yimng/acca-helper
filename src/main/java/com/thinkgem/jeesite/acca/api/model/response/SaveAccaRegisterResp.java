package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.register.entity.AppAccaRegister;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;


/**
 * 学习规划结果
 * @author Young
 * @version 2016-08-13
 */
public class SaveAccaRegisterResp extends BaseResponse {

	private AppAccaRegister obj;
	private String payConfTips;


	public SaveAccaRegisterResp(){
		
	}
	
	public SaveAccaRegisterResp(int respCode){
		super(respCode);
	}
	public SaveAccaRegisterResp(int respCode,String respDesc){
		super(respCode,respDesc);
	}
	
	public SaveAccaRegisterResp(AppAccaRegister obj, String payConfTips) {
		super(RespConstants.GLOBAL_SUCCESS);
		this.obj = obj;
		this.payConfTips = payConfTips;
	}
	public AppAccaRegister getObj() {
		return obj;
	}
	public void setObj(AppAccaRegister obj) {
		this.obj = obj;
	}
	public String getPayConfTips() {
		return payConfTips;
	}
	public void setPayConfTips(String payConfTips) {
		this.payConfTips = payConfTips;
	}
	
	
	
}
