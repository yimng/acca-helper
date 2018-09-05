/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.acca.api.exam.util.ExamConstant;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 考试类Entity
 * @author Michael
 * @version 2016-08-15
 */
public class AppOfficialExam extends DataEntity<AppOfficialExam> {
	
	private static final long serialVersionUID = 1L;
	private Long examId;		// exam_id
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考
	private String examCityId;		// exam_city_id
	private String examCityName;		// exam_city_name
	private Long examPlaceId;		// 考点id
	private String examPlaceName;		// 考点名称
	private Date examStartTime;		// 考试开始时间
	private Date examEndTime;		// 考试结束时间
	private Date examSignupEndtime;		// 报名截止时间
	private String totalSeats;		// 总席位数
	private String usedSeats;		// 已经被占用的席位数
	
	private Integer offiExamMac;
	private Integer offiExamWri;
	
	private String examCourse;
	private String examCourseName;
	private Double price;
	private Long courseId;
	private String examStartTimeStr;
	private String englishName;
	private String englishShortName;
	private String examDetailAddress;
	private String examPlaceSn;
	private Long examPlaceImageId;
	private String examPlaceContantName;
	private String examPlaceContantPhone;

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色

	public AppOfficialExam() {
		super();
	}

	public AppOfficialExam(String id){
		super(id);
	}
	
	/*public AppOfficialExam(Appointment appointment, Long courseId){
		this.setCourseId(courseId);
		this.setExamPlaceId(appointment.getExamPlaceId());
		this.setExamType(appointment.getExamType());
		this.setExamStartTimeStr(appointment.getExamStartTimeStr());
	}*/

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
	
	@Length(min=0, max=11, message="支持的考试类型：1自有考试，2官方机考，4官方笔考长度必须介于 0 和 11 之间")
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}
	
	@Length(min=0, max=11, message="exam_city_id长度必须介于 0 和 11 之间")
	public String getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(String examCityId) {
		this.examCityId = examCityId;
	}
	
	@Length(min=0, max=64, message="exam_city_name长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=255, message="考点名称长度必须介于 0 和 255 之间")
	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(Date examStartTime) {
		this.examStartTime = examStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(Date examEndTime) {
		this.examEndTime = examEndTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamSignupEndtime() {
		return examSignupEndtime;
	}

	public void setExamSignupEndtime(Date examSignupEndtime) {
		this.examSignupEndtime = examSignupEndtime;
	}
	
	@Length(min=0, max=11, message="总席位数长度必须介于 0 和 11 之间")
	public String getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(String totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	@Length(min=1, max=11, message="已经被占用的席位数长度必须介于 1 和 11 之间")
	public String getUsedSeats() {
		return usedSeats;
	}

	public void setUsedSeats(String usedSeats) {
		this.usedSeats = usedSeats;
	}
	
	public Integer getOffiExamMac() {
		offiExamMac = ExamConstant.OFFI_EXAM_MAC;
		return offiExamMac;
	}

	public void setOffiExamMac(Integer offiExamMac) {
		this.offiExamMac = offiExamMac;
	}

	public Integer getOffiExamWri() {
		offiExamWri = ExamConstant.OFFI_EXAM_WRI;
		return offiExamWri;
	}

	public void setOffiExamWri(Integer offiExamWri) {
		this.offiExamWri = offiExamWri;
	}

	public String getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}

	public String getExamCourseName() {
		return examCourseName;
	}

	public void setExamCourseName(String examCourseName) {
		this.examCourseName = examCourseName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getExamStartTimeStr() {
		return examStartTimeStr;
	}

	public void setExamStartTimeStr(String examStartTimeStr) {
		this.examStartTimeStr = examStartTimeStr;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getEnglishShortName() {
		return englishShortName;
	}

	public void setEnglishShortName(String englishShortName) {
		this.englishShortName = englishShortName;
	}

	public String getExamDetailAddress() {
		return examDetailAddress;
	}

	public void setExamDetailAddress(String examDetailAddress) {
		this.examDetailAddress = examDetailAddress;
	}

	public String getExamPlaceSn() {
		return examPlaceSn;
	}

	public void setExamPlaceSn(String examPlaceSn) {
		this.examPlaceSn = examPlaceSn;
	}

	public Long getExamPlaceImageId() {
		return examPlaceImageId;
	}

	public void setExamPlaceImageId(Long examPlaceImageId) {
		this.examPlaceImageId = examPlaceImageId;
	}

	public String getExamPlaceContantName() {
		return examPlaceContantName;
	}

	public void setExamPlaceContantName(String examPlaceContantName) {
		this.examPlaceContantName = examPlaceContantName;
	}

	public String getExamPlaceContantPhone() {
		return examPlaceContantPhone;
	}

	public void setExamPlaceContantPhone(String examPlaceContantPhone) {
		this.examPlaceContantPhone = examPlaceContantPhone;
	}

	/**
	 * 考试的颜色
	 * @return
	 */
	public String getCourseColor() {
		courseColor = RespConstants.courseColorMap.get(examCourse);
		return courseColor;
	}
}