/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 文章点赞Entity
 * @author Young
 * @version 2016-08-18
 */
public class WebArticlePraise extends DataEntity<WebArticlePraise> {
	
	private static final long serialVersionUID = 1L;
	private Long articlePraiseId;		// article_praise_id
	private Long articleId;		// 文章id
	private Long userId;		// 点赞人id
	
	public WebArticlePraise() {
		super();
	}

	public WebArticlePraise(String id){
		super(id);
	}

	@NotNull(message="article_praise_id不能为空")
	public Long getArticlePraiseId() {
		return articlePraiseId;
	}

	public void setArticlePraiseId(Long articlePraiseId) {
		this.articlePraiseId = articlePraiseId;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}