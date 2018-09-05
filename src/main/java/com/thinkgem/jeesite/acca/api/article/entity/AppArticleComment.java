/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;

/**
 * AppArticleCommentEntity
 * @author Ivan
 * @version 2016-09-08
 */
public class AppArticleComment extends DataEntity<AppArticleComment> {
	
	private static final long serialVersionUID = 1L;
	private Long articleCommentId;		// article_comment_id
	private Long articleId;		// 文章id
	private Long accaUserId;		// 点赞人id
	private String content;		// 评论内容
	private AppArticleComment pComment;		// 关联自身表：回复的评论id。
	
	private Long parentId;
	
	private AppAccaUser user;
	
	private String nickname;
	private String headImgUrl;
	
	private String createDateShowStr;
	
	public AppArticleComment() {
		super();
	}

	public AppArticleComment(String id){
		super(id);
	}

	@NotNull(message="article_comment_id不能为空")
	public Long getArticleCommentId() {
		return articleCommentId;
	}

	public void setArticleCommentId(Long articleCommentId) {
		this.articleCommentId = articleCommentId;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	@Length(min=0, max=500, message="评论内容长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AppArticleComment getpComment() {
		return pComment;
	}

	public void setpComment(AppArticleComment pComment) {
		this.pComment = pComment;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public AppAccaUser getUser() {
		return user;
	}

	public void setUser(AppAccaUser user) {
		this.user = user;
	}

	public String getCreateDateShowStr() {
		this.createDateShowStr = DateTimeUtils.convertDate2String(super.createDate, "MM-dd  HH:mm");
		return createDateShowStr;
	}

	public void setCreateDateShowStr(String createDateShowStr) {
		this.createDateShowStr = createDateShowStr;
	}

	public String getNickname() {
		if(user!=null){
			this.nickname = user.getNickname();
		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgUrl() {
		if(user!=null && user.getHeadImg()!=null){
			this.headImgUrl = user.getHeadImg().getFileUrl();
		}
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
}