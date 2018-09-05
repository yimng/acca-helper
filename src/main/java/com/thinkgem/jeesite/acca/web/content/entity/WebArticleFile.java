/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 文章附件Entity
 * @author Young
 * @version 2016-08-18
 */
public class WebArticleFile extends DataEntity<WebArticleFile> {
	
	private static final long serialVersionUID = 1L;
	private Long articleFileId;		// article_file_id
	private Long articleId;		// 文章id
	private Long fileId;		// 附件id
	
	public WebArticleFile() {
		super();
	}

	public WebArticleFile(String id){
		super(id);
	}

	@NotNull(message="article_file_id不能为空")
	public Long getArticleFileId() {
		return articleFileId;
	}

	public void setArticleFileId(Long articleFileId) {
		this.articleFileId = articleFileId;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
}