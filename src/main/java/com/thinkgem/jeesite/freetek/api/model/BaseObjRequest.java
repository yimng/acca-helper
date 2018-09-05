package com.thinkgem.jeesite.freetek.api.model;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;

import io.swagger.annotations.ApiModelProperty;

public class BaseObjRequest<T> extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "obj参数")
	private T obj;
	
	/**
	 * 
	 * @param checkUserFlag 是否需要检查用户基本信息
	 * @return
	 */
	public int isCorrectParams(boolean checkUserFlag) {
		
		if (checkUserFlag && super.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return super.isCorrectParams();
		}
		if (obj == null) {
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		return RespConstants.GLOBAL_SUCCESS;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
}
