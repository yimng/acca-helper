package com.thinkgem.jeesite.acca.api.order.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamVersion;
import com.thinkgem.jeesite.acca.api.exam.util.ExamConstant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import com.thinkgem.jeesite.freetek.util.JsonSerializer.CustomDoubleSerialize;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SmallSignup implements Serializable {

	private static final long serialVersionUID = -3805385359911627310L;
	
	private Long examSignupId;
	
	private String examCourse;
	
	private Integer examType;
	
	private String examTypeName;
	
	private Date examStartTime;
	
	private Date examEndTime;
	
	private String examDetailAddress;
	
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double price;
	
	private String showTime;
	
	private String examVersionJson;
	
	private AppExamVersion examVersion;
	
	private Integer examSignupStatus;
	
	private Long examId;

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色

	public Long getExamSignupId() {
		return examSignupId;
	}

	public void setExamSignupId(Long examSignupId) {
		this.examSignupId = examSignupId;
	}

	public String getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public String getExamTypeName() {
		return examTypeName;
	}

	public void setExamTypeName(String examTypeName) {
		if(examType != null){
			this.examTypeName = ExamConstant.EXAM_TYPE_STRS.get(examType);
		} else {
			this.examTypeName = examTypeName;
		}
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

	public String getExamDetailAddress() {
		return examDetailAddress;
	}

	public void setExamDetailAddress(String examDetailAddress) {
		this.examDetailAddress = examDetailAddress;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getShowTime() {
		if(this.examStartTime == null || this.examEndTime == null){
			return null;
		} else {
			return TimeUtils.DateToStr(this.examStartTime, TimeUtils.dateFormat1)+"-"+TimeUtils.DateToStr(this.examEndTime, TimeUtils.dateFormat2);
		}
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getExamVersionJson() {
		return examVersionJson;
	}

	public void setExamVersionJson(String examVersionJson) {
		if(examVersionJson != null){
			this.examVersion = (AppExamVersion) JsonMapper.fromJsonString(examVersionJson, AppExamVersion.class);
		}
		this.examVersionJson = examVersionJson;
	}

	public AppExamVersion getExamVersion() {
		return examVersion;
	}

	public void setExamVersion(AppExamVersion examVersion) {
		this.examVersion = examVersion;
	}

	public Integer getExamSignupStatus() {
		return examSignupStatus;
	}

	public void setExamSignupStatus(Integer examSignupStatus) {
		this.examSignupStatus = examSignupStatus;
	}

	/**
	 * 考试的颜色
	 * @return
	 */
	public String getCourseColor() {
		courseColor = RespConstants.courseColorMap.get(examCourse);
		return courseColor;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
}
