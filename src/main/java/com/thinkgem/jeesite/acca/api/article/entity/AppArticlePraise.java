/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * AppArticlePraiseEntity
 * @author Ivan
 * @version 2016-09-08
 */
public class AppArticlePraise extends DataEntity<AppArticlePraise> {
	
	private static final long serialVersionUID = 1L;
	private Long articlePraiseId;		// article_praise_id
	private Long articleId;		// 文章id
	private Long accaUserId;		// 点赞人id
	
	public AppArticlePraise() {
		super();
	}

	public AppArticlePraise(String id){
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
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
}