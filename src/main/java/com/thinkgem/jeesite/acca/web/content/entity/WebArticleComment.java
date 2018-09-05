/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 文章评论Entity
 * @author Young
 * @version 2016-08-18
 */
public class WebArticleComment extends DataEntity<WebArticleComment> {
	
	private static final long serialVersionUID = 1L;
	private Long articleCommentId;		// article_comment_id
	private Long articleId;		// 文章id
	private Long accaUserId;		// userid
	private String content;		// 评论内容
	private Long parentId;		// 关联自身表：回复的评论id。
	
	private String articleTitle;//文章title
	private WebAccaUser user;
	
	private Integer type;
	
	public WebArticleComment() {
		super();
	}

	public WebArticleComment(String id){
		super(id);
	}

	@NotNull(message="article_comment_id不能为空")
	public Long getArticleCommentId() {
		return articleCommentId;
	}

	public void setArticleCommentId(Long articleCommentId) {
		this.articleCommentId = articleCommentId;
		super.id = String.valueOf(articleCommentId);
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	
	@Length(min=0, max=500, message="评论内容长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public WebAccaUser getUser() {
		return user;
	}

	public void setUser(WebAccaUser user) {
		this.user = user;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	
}