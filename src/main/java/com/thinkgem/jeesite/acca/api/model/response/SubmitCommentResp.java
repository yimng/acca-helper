package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.article.entity.AppArticleComment;
import com.thinkgem.jeesite.acca.api.order.entity.SmallDetailOrder;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

public class SubmitCommentResp extends BaseResponse {

	private static final long serialVersionUID = -8471199804544776610L;
	
	private AppArticleComment comment;
	
	private String commentNumStr;
	
	public SubmitCommentResp(){
		
	}
	
	public SubmitCommentResp(int respCode){
		super(respCode);
	}
	public SubmitCommentResp(int respCode,String respDesc){
		super(respCode,respDesc);
	}
	
	public SubmitCommentResp(AppArticleComment comment,String commentNumStr){
		super(RespConstants.GLOBAL_SUCCESS);
		this.comment = comment;
		this.commentNumStr = commentNumStr;
	}

	public AppArticleComment getComment() {
		return comment;
	}

	public void setComment(AppArticleComment comment) {
		this.comment = comment;
	}

	public String getCommentNumStr() {
		return commentNumStr;
	}

	public void setCommentNumStr(String commentNumStr) {
		this.commentNumStr = commentNumStr;
	}



}
