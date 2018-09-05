package com.thinkgem.jeesite.acca.api.model.request;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "获取验证码参数")
public class GetSmsVcodeReq extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "手机号",required=true)
	private String phone;
	
	public int isCorrectParams() {
		
		if (StringUtils.isEmpty(phone)) {
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	
}
