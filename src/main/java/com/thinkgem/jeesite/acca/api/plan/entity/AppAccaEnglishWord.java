package com.thinkgem.jeesite.acca.api.plan.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * ACCA财经词汇Entity
 * @author Young
 * @version 2016-08-10
 */
public class AppAccaEnglishWord extends DataEntity<AppAccaEnglishWord> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "名称")
	private String name;		// name
	@ApiModelProperty(value = "中文解释")
	private String chineseName;		// 中文解释
	@ApiModelProperty(value = "详细解释")
	private String description;		// 详细解释，富文本
	@ApiModelProperty(value = "名称的首字母")
	private String nameFirstWord;		// name
	@ApiModelProperty(value = "详情页链接")
	private String linkUrl;      //详情页链接	
	@ApiModelProperty(value = "音标")	
	private String yinbiao;
	@ApiModelProperty(value = "英文释义")
	private String endescription;
	@ApiModelProperty(value = "记忆方法")
	private String jiyifangfa;
	@ApiModelProperty(value = "音标音频")
	private String yinbiaourl;
	@ApiModelProperty(value = "英文释义音频")
	private String endescriptionurl;
	@ApiModelProperty(value = "记忆方法音频")
	private String jiyifangfaurl;		//详情页链接
	@ApiModelProperty(value = "英文读音音频")
	private String nameurl;
	@ApiModelProperty(value = "中文释义音频")
	private String descriptionurl;
	@ApiModelProperty(value = "证书")
	private String certificate;
	@ApiModelProperty(value = "科目")
	private String subject;
	
	
	
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


	public AppAccaEnglishWord() {
		super();
	}

	public AppAccaEnglishWord(String id){
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

	public String getNameFirstWord() {
		this.setNameFirstWord(this.getName().substring(0,1).toUpperCase());
		return nameFirstWord;
	}

	public void setNameFirstWord(String nameFirstWord) {
		this.nameFirstWord = nameFirstWord;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}