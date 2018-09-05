package com.thinkgem.jeesite.acca.api.home.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 首页BannerEntity
 * @author Young
 * @version 2016-08-15
 */
public class AppBanner extends DataEntity<AppBanner> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "banner_id")
	private Long bannerId;		// banner_id
	@ApiModelProperty(value = "banner位置，0首页")
	private Integer position;		// banner位置，0首页
	@JsonIgnore
	private Integer sort;		// 排序，值越小越靠前
	@ApiModelProperty(value = "banner标题")
	private String title;		// banner标题
	@ApiModelProperty(value = "banner类型，1链接，2富文本，3,有资有料")
	private Integer type;		// banner，1链接，2富文本，3,有资有料
	@JsonIgnore
	private Long imgId;		// 图片链接
	@ApiModelProperty(value = "外链地址")
	private String linkUrl;		// 外链地址
	@ApiModelProperty(value = "富文本内容")
	private String htmlContent;		// 富文本内容
	@JsonIgnore
	private Long articleId;		// 有资有料文章id
	@ApiModelProperty(value = "banner开始时间")
	private Date startTime;		// banner开始时间
	@ApiModelProperty(value = "banner结束时间")
	private Date endTime;		// banner结束时间
	@ApiModelProperty(value = "banner展示状态 0正常，1结束")
	private Integer status;		// banner展示状态 0正常，1结束
	@ApiModelProperty(value = "banner图片")
	private FileInfo image;
	@ApiModelProperty(value = "banner分享时预览文字")
	private String htmlPreview="ACCA小助手是国内首款专门为ACCA学生量身定制，集学习规划、报名考试、提醒监督、交流分享于一身的学习辅助工具";
	
	public AppBanner() {
		super();
	}

	public AppBanner(String id){
		super(id);
	}

	@NotNull(message="banner_id不能为空")
	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}
	
	@NotNull(message="banner位置，0首页不能为空")
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	@NotNull(message="排序，值越小越靠前不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=1, max=255, message="banner标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull(message="banner，1链接，2富文本，3,有资有料不能为空")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@NotNull(message="图片链接不能为空")
	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	
	@Length(min=0, max=512, message="外链地址长度必须介于 0 和 512 之间")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@NotNull(message="banner展示状态 0正常，1结束不能为空")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public FileInfo getImage() {
		return image;
	}

	public void setImage(FileInfo image) {
		this.image = image;
	}

	public String getHtmlPreview() {
		return htmlPreview;
	}

	public void setHtmlPreview(String htmlPreview) {
		this.htmlPreview = htmlPreview;
	}
}