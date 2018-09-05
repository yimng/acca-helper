package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "GetArticleListByCategoryReq",description="按照分类获取有资有料文章列表参数")
public class GetArticleListByCategoryReq extends BasePageRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "文章分类id")
	private Long articleCategoryId;
	
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams(false);
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		
		if(this.articleCategoryId==null || this.articleCategoryId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}



	public Long getArticleCategoryId() {
		return articleCategoryId;
	}



	public void setArticleCategoryId(Long articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}
}
