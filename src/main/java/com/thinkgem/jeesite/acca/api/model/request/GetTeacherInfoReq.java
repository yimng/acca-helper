package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "GetTeacherInfoReq",description="获取名师详情及分享文章列表参数")
public class GetTeacherInfoReq extends BasePageRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "名师id")
	private Long teacherId;
	
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams(false);
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.teacherId==null || this.teacherId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

}
