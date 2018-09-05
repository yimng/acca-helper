package com.thinkgem.jeesite.freetek.api.model;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "BasePageRequest")
public class BasePageRequest extends BaseRequest {
	
	private static final long serialVersionUID = 2482633119866726335L;
	
	@ApiModelProperty(value = "分页参数",required=true)
	private PageApi page;
	
	/**
	 * 
	 * @param checkUserFlag 是否需要检查用户基本信息
	 * @return
	 */
	public int isCorrectParams(boolean checkUserFlag) {
		if (checkUserFlag && super.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS) {
			return super.isCorrectParams();
		}
		if (page == null || page.getPageSize() <= 0 || page.getStartIndex() < 0) {
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		return RespConstants.GLOBAL_SUCCESS;
	}

	public PageApi getPage() {
		return page;
	}

	public void setPage(PageApi page) {
		this.page = page;
	}



}
