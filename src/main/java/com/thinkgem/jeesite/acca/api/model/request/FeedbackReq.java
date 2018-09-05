package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "FeedbackReq",description="用户反馈接口")
public class FeedbackReq extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "反馈人手机号")
	private String phone;
	@ApiModelProperty(value = "反馈内容",required=true)
	private String content;
	
	
	
	public int isCorrectParams() {
		
		if(StringUtils.isEmpty(this.content)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(this.content.length()>256){
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



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	


	
}
