/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * ACCA财经词汇Entity
 * @author Young
 * @version 2016-08-22
 */
public class WebAccaEnglishWord extends DataEntity<WebAccaEnglishWord> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String chineseName;		// 中文解释
	private String description;		// 详细解释，富文本
	private String linkUrl;			//详情页链接
	
	private String yinbiao;
	private String endescription;
	private String jiyifangfa;
	private String yinbiaourl;
	private String endescriptionurl;
	private String jiyifangfaurl;		//详情页链接
	private String nameurl;
	private String descriptionurl;

	private String certificate;
	private String subject;

	public String getNameurl() {
		return nameurl;
	}

	public void setNameurl(String nameurl) {
		this.nameurl = nameurl;
	}

	public String getDescriptionurl() {
		return descriptionurl;
	}

	public void setDescriptionurl(String descriptionurl) {
		this.descriptionurl = descriptionurl;
	}

	
		
	
	public String getYinbiaourl() {
		return yinbiaourl;
	}

	public void setYinbiaourl(String yinbiaourl) {
		this.yinbiaourl = yinbiaourl;
	}

	public String getEndescriptionurl() {
		return endescriptionurl;
	}

	public void setEndescriptionurl(String endescriptionurl) {
		this.endescriptionurl = endescriptionurl;
	}

	public String getJiyifangfaurl() {
		return jiyifangfaurl;
	}

	public void setJiyifangfaurl(String jiyifangfaurl) {
		this.jiyifangfaurl = jiyifangfaurl;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	
	public WebAccaEnglishWord() {
		super();
	}

	public String getYinbiao() {
		return yinbiao;
	}

	public void setYinbiao(String yinbiao) {
		this.yinbiao = yinbiao;
	}

	public String getEndescription() {
		return endescription;
	}

	public void setEndescription(String endescription) {
		this.endescription = endescription;
	}

	public String getJiyifangfa() {
		return jiyifangfa;
	}

	public void setJiyifangfa(String jiyifangfa) {
		this.jiyifangfa = jiyifangfa;
	}

	public WebAccaEnglishWord(String id){
		super(id);
	}

	@Length(min=0, max=128, message="name长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="中文解释长度必须介于 0 和 64 之间")
	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}