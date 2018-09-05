package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "GetArticleListReq",description="获取达人分享文件列表/获取名师指导文章列表参数")
public class GetArticleListReq extends BasePageRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "类型：3学习达人分享文章列表，4，名师指导文章列表")
	private Integer type;
	
	
	
	@Override
    public int isCorrectParams() {
		if(super.isCorrectParams(false)!=RespConstants.GLOBAL_SUCCESS){
			return super.isCorrectParams(false);
		}
		
		if(this.type==null ){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(this.type!=Constants.ArticleType.learning && this.type!=Constants.ArticleType.tearcher){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}
}
