package com.thinkgem.jeesite.acca.web.exam.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.util.TimeUtils;

public class WebSignup extends DataEntity<WebSignup> {

	private static final long serialVersionUID = -6056135240704396439L;
	
	private Long examSignupId;
	
	private String examCityName;
	
	private String examPlaceName;
	
	private Date examStartTime;
	
	private Date examEndTime;
	
	private String course;
	
	private String examVersionJson;
	
	private Double price;
	
	private String timeScope;
	
	private String versionName;
	
	private Integer examSignupStatus;
	
	private Long examCourseId;
	
	private Long accaUserId;
	
	public Long getExamSignupId() {
		return examSignupId;
	}

	public void setExamSignupId(Long examSignupId) {
		this.examSignupId = examSignupId;
	}

	public String getExamCityName() {
		return examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
	}

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public Date getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(Date examStartTime) {
		this.examStartTime = examStartTime;
	}

	public Date getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(Date examEndTime) {
		this.examEndTime = examEndTime;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getExamVersionJson() {
		return examVersionJson;
	}

	public void setExamVersionJson(String examVersionJson) {
		this.examVersionJson = examVersionJson;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTimeScope() {
		return TimeUtils.DateToStr(this.examStartTime, TimeUtils.dateFormat1)+"-"+TimeUtils.DateToStr(this.examEndTime, TimeUtils.dateFormat2);
	}

	public void setTimeScope(String timeScope) {
		this.timeScope = timeScope;
	}

	public String getVersionName() {
		if(!StringUtils.isEmpty(this.examVersionJson)){
			WebExamVersion o = (WebExamVersion) JsonMapper.fromJsonString(this.examVersionJson, WebExamVersion.class);
			return o.getExamVersionName();
		} else {
			return this.versionName;
		}
		
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Integer getExamSignupStatus() {
		return examSignupStatus;
	}

	public void setExamSignupStatus(Integer examSignupStatus) {
		this.examSignupStatus = examSignupStatus;
	}

	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

}
