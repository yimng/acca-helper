/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 文章收藏Entity
 * @author Young
 * @version 2016-08-18
 */
public class WebArticleCollect extends DataEntity<WebArticleCollect> {
	
	private static final long serialVersionUID = 1L;
	private Long articleCollectId;		// article_collect_id
	private Long accaUserId;		// acca注册用户id
	private Long articleId;		// 文章id
	
	public WebArticleCollect() {
		super();
	}

	public WebArticleCollect(String id){
		super(id);
	}

	@NotNull(message="article_collect_id不能为空")
	public Long getArticleCollectId() {
		return articleCollectId;
	}

	public void setArticleCollectId(Long articleCollectId) {
		this.articleCollectId = articleCollectId;
	}
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
}