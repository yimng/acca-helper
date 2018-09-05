/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.entity;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * AppArticleCollectEntity
 * @author Ivan
 * @version 2016-09-07
 */
public class AppArticleCollect extends DataEntity<AppArticleCollect> {
	
	private static final long serialVersionUID = 1L;
	private Long articleCollectId;		// article_collect_id
	private Long accaUserId;		// acca注册用户id
	private Long articleId;		// 文章id
	
	public AppArticleCollect() {
		super();
	}

	public AppArticleCollect(String id){
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