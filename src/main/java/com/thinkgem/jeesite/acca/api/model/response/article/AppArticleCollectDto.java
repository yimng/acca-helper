package com.thinkgem.jeesite.acca.api.model.response.article;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleCategory;
import com.thinkgem.jeesite.acca.api.article.entity.AppTeacher;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import com.thinkgem.jeesite.freetek.util.TextUtils;

public class AppArticleCollectDto {
	/**
	 * Copyright &copy; 2012-2016 <a
	 * href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
	 * reserved.
	 */

	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "articleId文章id")
	private Long articleId; // article_id
	
	@ApiModelProperty(value = "文章类别：1有资有料html文章，2公开课，3学习达人分享html文章，4名师html文章")
	private Integer type; // 文章类别：1有资有料html文章，2公开课，3学习达人分享html文章，4名师html文章
	
	@ApiModelProperty(value = "文章标题")
	private String title; // 文章标题
	@ApiModelProperty(value = "文章页面标题")
	private String pageTitle; // 页面标题
	@ApiModelProperty(value = "文章详情url")
	private String linkUrl; // 文章显示url
	@ApiModelProperty(value = "文章分类名称")
	private String articleCategoryName;

	@ApiModelProperty(value = "公开课直播状态：1未开始，2正在直播，3已结束")
	private Integer courseStatus;

	@ApiModelProperty(value = "文章预览，用于分享时的分享描述")
	private String htmlPreview;
	
	@ApiModelProperty(value = "文章封面图片url")
	private String imageUrl;
	@ApiModelProperty(value = "公开课形式：1在线直播，2地面讲座，3录播视频")
	private Integer courseType;

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getArticleCategoryName() {
		return articleCategoryName;
	}

	public void setArticleCategoryName(String articleCategoryName) {
		this.articleCategoryName = articleCategoryName;
	}

	public Integer getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getHtmlPreview() {
		return htmlPreview;
	}

	public void setHtmlPreview(String htmlPreview) {
		this.htmlPreview = htmlPreview;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	
	



}
