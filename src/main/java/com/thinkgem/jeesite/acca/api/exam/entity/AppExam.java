/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.acca.api.plan.entity.AppExamCourse;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;

/**
 * AppExamEntity
 * @author Ivan
 * @version 2016-08-15
 */
public class AppExam extends DataEntity<AppExam> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "考试id")
	private Long examId;		// exam_id
	@JsonIgnore
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考
	@ApiModelProperty(value = "考试所在城市id")
	private Integer examCityId;		// exam_city_id
	@ApiModelProperty(value = "考试所在城市名称")
	private String examCityName;		// exam_city_name
	@ApiModelProperty(value = "考试考点id")
	private Long examPlaceId;		// 考点id
	@JsonIgnore
	private String examPlaceName;		// 考点名称
	@ApiModelProperty(value = "考试开始时间")
	private Date examStartTime;		// 考试开始时间
	@ApiModelProperty(value = "考试结束时间")
	private Date examEndTime;		// 考试结束时间
	//@ApiModelProperty(value = "考试报名截止时间")
	@JsonIgnore
	private Date examSignupEndtime;		// 报名截止时间
	//@ApiModelProperty(value = "考试总座位数")
	@JsonIgnore
	private Integer totalSeats;		// 总席位数
	//@ApiModelProperty(value = "考试已经被占用座位数")
	@JsonIgnore
	private Integer usedSeats;		// 已经被占用的席位数
	@ApiModelProperty(value = "考试剩余可用座位")
	private Integer availableSeats;//剩余可用座位
	@ApiModelProperty(value = "考试时间格式化显示字符串")
	private String showExamTimeStr;
	@JsonIgnore
	private AppExamPlace examPlace;
	@ApiModelProperty(value = "考试详细地址")
	private String examDetailAddress;
	@ApiModelProperty(value = "考试包含科目")
	private List<AppExamCourseSelf> examCourseList;
	
	public AppExam() {
		super();
	}

	public AppExam(String id){
		super(id);
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
	
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}
	
	public Integer getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(Integer examCityId) {
		this.examCityId = examCityId;
	}
	
	public String getExamCityName() {
		return examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
	}
	
	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
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
	
	public Date getExamSignupEndtime() {
		return examSignupEndtime;
	}

	public void setExamSignupEndtime(Date examSignupEndtime) {
		this.examSignupEndtime = examSignupEndtime;
	}
	
	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public Integer getUsedSeats() {
		return usedSeats;
	}

	public void setUsedSeats(Integer usedSeats) {
		this.usedSeats = usedSeats;
	}

	public Integer getAvailableSeats() {
		this.availableSeats = this.totalSeats-this.usedSeats;
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getShowExamTimeStr() {
		
		String startTimeStr = DateTimeUtils.convertDate2String(this.examStartTime, "yyyy-MM-dd HH:mm");
		String endTimeStr = DateTimeUtils.convertDate2String(this.examEndTime, "-HH:mm");
		this.showExamTimeStr = startTimeStr+endTimeStr;
		
		return showExamTimeStr;
	}

	public void setShowExamTimeStr(String showExamTimeStr) {
		this.showExamTimeStr = showExamTimeStr;
	}

	public AppExamPlace getExamPlace() {
		return examPlace;
	}

	public void setExamPlace(AppExamPlace examPlace) {
		this.examPlace = examPlace;
	}

	public String getExamDetailAddress() {
		if(this.examPlace!=null){
			this.examDetailAddress = this.examPlace.getExamDetailAddress();
		}
		return examDetailAddress;
	}

	public void setExamDetailAddress(String examDetailAddress) {
		this.examDetailAddress = examDetailAddress;
	}

	public List<AppExamCourseSelf> getExamCourseList() {
		return examCourseList;
	}

	public void setExamCourseList(List<AppExamCourseSelf> examCourseList) {
		this.examCourseList = examCourseList;
	}
	
}