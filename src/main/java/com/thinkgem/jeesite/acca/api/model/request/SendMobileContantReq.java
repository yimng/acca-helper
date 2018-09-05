package com.thinkgem.jeesite.acca.api.model.request;


import java.util.List;

import com.thinkgem.jeesite.acca.api.common.entity.AppMobileDeviceContact;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "SendMobileContantReq",description="手机通讯上传参数")
public class SendMobileContantReq extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "硬件设备注册id",required=true)
	private String deviceId;
	@ApiModelProperty(value = "手机通讯录列表",required=true)
	private List<AppMobileDeviceContact> list;
	
	
	
	public int isCorrectParams() {
		if(StringUtils.isEmpty(this.deviceId)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(list==null || list.isEmpty()){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		
		return RespConstants.GLOBAL_SUCCESS;
	}



	public String getDeviceId() {
		return deviceId;
	}



	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}



	public List<AppMobileDeviceContact> getList() {
		return list;
	}



	public void setList(List<AppMobileDeviceContact> list) {
		this.list = list;
	}




}
