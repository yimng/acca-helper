package com.thinkgem.jeesite.acca.api.model.request;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.jeesite.common.utils.AppUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "登录请求参数")
public class LoginReq extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "手机号",required=true)
	private String phone;
	
	@ApiModelProperty(value = "验证码",required=true)
	private String smsVcode;
	
	@ApiModelProperty(value = "硬件设备注册id",required=false)
	private String deviceId;
	
	public int isCorrectParams() {
		
		if (StringUtils.isEmpty(phone)) {
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if (StringUtils.isEmpty(smsVcode)) {
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if (!AppUtils.isMobileNum(phone)) {
			return RespConstants.SMS_VCODE_MOBILE_TYPE_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSmsVcode() {
		return smsVcode;
	}

	public void setSmsVcode(String smsVcode) {
		this.smsVcode = smsVcode;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	

	
}
