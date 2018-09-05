package com.thinkgem.jeesite.acca.api.model.request;


import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "DeleteCollectBatchReq",description="批量删除收藏文章参数")
public class DeleteCollectBatchReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "articleId文章id列表",required=true)
	private List<Long> articleIdList;
	
	
	
	@Override
    public int isCorrectParams() {
		
		if(super.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){
			return super.isCorrectParams();
		}
		if(articleIdList==null || articleIdList.isEmpty()){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		
		return RespConstants.GLOBAL_SUCCESS;
	}



	public List<Long> getArticleIdList() {
		return articleIdList;
	}



	public void setArticleIdList(List<Long> articleIdList) {
		this.articleIdList = articleIdList;
	}


}
