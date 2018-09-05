/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 文章分类Entity
 * @author Young
 * @version 2016-08-18
 */
public class WebArticleCategory extends DataEntity<WebArticleCategory> {
	
	private static final long serialVersionUID = 1L;
	private Long articleCategoryId;		// article_category_id
	private String name;		// 文章分类名称
	private Integer sortNum;		// 排序字段，越小越靠前
	private Integer type;		// 类型：1html文章，2公开课
	private Integer sysData;		// 是否系统数据：1不是，2是，系统数据不允许删除
	
	public WebArticleCategory() {
		super();
	}

	public WebArticleCategory(String id){
		super(id);
	}

	public Long getArticleCategoryId() {
		return articleCategoryId;
	}

	public void setArticleCategoryId(Long articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}
	
	@Length(min=0, max=20, message="文章分类名称长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	@NotNull(message = "文章分类不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getSysData() {
		return sysData;
	}

	public void setSysData(Integer sysData) {
		this.sysData = sysData;
	}
	
}