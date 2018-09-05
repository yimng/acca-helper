package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;

import cn.jpush.api.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "GetArticleListByTitleAndCategoryReq",description="按照标题和分类获取有资有料文章列表参数")
public class GetArticleListByTitleAndCategoryReq extends GetArticleListByCategoryReq {
private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "文章标题")
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams(false);
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(!StringUtils.isNotEmpty(title)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}
}
