/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * AppArticleCategoryEntity
 * @author Ivan
 * @version 2016-08-10
 */
public class AppArticleCategory extends DataEntity<AppArticleCategory> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "article_category_id")
	private Long articleCategoryId;		// article_category_id
	@ApiModelProperty(value = "文章分类名称")
	private String name;		// 文章分类名称
	@JsonIgnore
	private Integer sortNum;		// 排序字段，越小越靠前
	@ApiModelProperty(value = "文章分类类型：1html文章，2公开课")
	private Integer type;		// 类型：1html文章，2公开课
	
	@JsonIgnore
	private Integer sysData;		// 是否系统数据：1不是，2是，系统数据不允许删除
	
	public AppArticleCategory() {
		super();
	}

	public AppArticleCategory(String id){
		super(id);
	}

	@NotNull(message="article_category_id不能为空")
	public Long getArticleCategoryId() {
		return articleCategoryId;
	}

	public void setArticleCategoryId(Long articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}
	
	@Length(min=0, max=200, message="文章分类名称长度必须介于 0 和 200 之间")
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
	
	@NotNull(message="类型：1html文章，2公开课不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@NotNull(message="是否系统数据：1不是，2是，系统数据不允许删除不能为空")
	public Integer getSysData() {
		return sysData;
	}

	public void setSysData(Integer sysData) {
		this.sysData = sysData;
	}
	
}