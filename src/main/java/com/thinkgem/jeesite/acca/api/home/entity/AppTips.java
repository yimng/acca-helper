/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.home.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * App首页提醒Entity
 * @author Young
 * @version 2016-09-07
 */
public class AppTips extends DataEntity<AppTips> {
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Date pushTime;		// 提醒的开始时间,预留的
	@ApiModelProperty(value = "提醒时间")
	private Date tipTime;		// 提醒时间
	@JsonIgnore
	private Long accaUserId;      //用户id
	@JsonIgnore
	private String tipTitle;		// 提醒的标题,用于推送的
	@ApiModelProperty(value = "提醒的内容")
	private String tipContent;		// 提醒的内容
	@JsonIgnore
	private String pushContent;		// 推送的内容
	@ApiModelProperty(value = "提醒设置,1:提前一天,2:提前一周")
	private Integer tipSet;		// 提醒设置,1:提前一天,2:提前一周
	@ApiModelProperty(value = "是否为系统消息,0为不是,1为是.默认为系统消息")
	private Integer isSys;		// 是否为系统消息,0为不是,1为是.默认为系统消息
	@JsonIgnore
	private Integer tipType;		// 消息类型,1.考试提醒;2.规划提醒;3.个人提醒
	@JsonIgnore
	private String course;		//提醒科目

	public AppTips() {
		super();
	}

	public AppTips(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="提醒时间不能为空")
	public Date getTipTime() {
		return tipTime;
	}

	public void setTipTime(Date tipTime) {
		this.tipTime = tipTime;
	}
	
	@Length(min=1, max=50, message="提醒的内容长度必须介于 1 和 50 之间")
	public String getTipContent() {
		return tipContent;
	}

	public void setTipContent(String tipContent) {
		this.tipContent = tipContent;
	}
	
	public Integer getTipSet() {
		return tipSet;
	}

	public void setTipSet(Integer tipSet) {
		this.tipSet = tipSet;
	}
	
	public Integer getIsSys() {
		return isSys;
	}

	public void setIsSys(Integer isSys) {
		this.isSys = isSys;
	}
	
	public Integer getTipType() {
		return tipType;
	}

	public void setTipType(Integer tipType) {
		this.tipType = tipType;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTipTitle() {
		return tipTitle;
	}

	public void setTipTitle(String tipTitle) {
		this.tipTitle = tipTitle;
	}

	public String getPushContent() {
		return pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}
}