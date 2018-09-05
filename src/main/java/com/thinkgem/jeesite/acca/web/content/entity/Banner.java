/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * banner设置Entity
 * @author Young
 * @version 2016-08-22
 */
public class Banner extends DataEntity<Banner> {
	
	private static final long serialVersionUID = 1L;
	private Long bannerId;		// banner_id
	private Integer position;		// banner位置，0首页
	private Integer sort = 255;		// 排序，值越小越靠前
	private String title;		// 名称
	private Integer type;		// banner，1链接，2富文本，3,有资有料
	private Long imgId;		// 图片
	private String linkUrl;		// 外链地址
	private String htmlContent;		// 富文本内容
	private Long articleId;		// 有资有料文章id
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer status;		// banner展示状态 0正常，1结束
	private FileInfo img;
	private String imgStr;

	public Banner() {
		super();
	}

	public Banner(String id){
		super(id);
	}

	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}
	
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	@NotNull(message="排序，值越小越靠前不能为空")
	@Min(value = 1,message = "请输入1-255之间的数字")
	@Max(value = 255,message = "请输入1-255之间的数字")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=1, max=255, message="名称长度必须介于 1 和 255 之间")
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
	
	@NotNull(message="图片不能为空")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public FileInfo getImg() {
		return img;
	}

	public void setImg(FileInfo img) {
		this.img = img;
	}

	public String getImgStr() {
		return JsonMapper.getInstance().toJson(this.img);
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}
}