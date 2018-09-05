package com.thinkgem.jeesite.freetek.api.model;

import java.util.List;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;

import io.swagger.annotations.ApiModelProperty;

public class BaseBatchRequest<T> extends BaseRequest {
	
	private static final long serialVersionUID = 2482633119866726335L;
	
	@ApiModelProperty(value = "批量上传参数",required=true)
	private List<T> list;
	
	@Override
    public int isCorrectParams() {
		if (super.getAppUserId() == null) {
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if (list == null || list.size() <= 0) {
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		return RespConstants.GLOBAL_SUCCESS;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
