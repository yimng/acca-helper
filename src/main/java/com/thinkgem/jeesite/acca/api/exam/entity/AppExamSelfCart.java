/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import com.thinkgem.jeesite.freetek.util.JsonSerializer.CustomDoubleSerialize;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * AppExamSelfCartEntity
 * @author Ivan
 * @version 2016-08-17
 */
public class AppExamSelfCart extends DataEntity<AppExamSelfCart> {
	
	public double getStudentPrice() {
		return studentPrice;
	}

	public void setStudentPrice(double studentPrice) {
		this.studentPrice = studentPrice;
	}

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "购物车id")
	private Long selfExamCartId;		// self_exam_cart_id
	@ApiModelProperty(value = "考试科目id")
	private Long examCourseId;		// 考试科目id
	@ApiModelProperty(value = "考试考点id")
	private Long examPlaceId;		// 考试考点id
	@JsonIgnore
	private String examVersionJson;		// 考试版本json字符串
	@ApiModelProperty(value = "考试科目版本")
	private AppExamVersion examVersion;
	
	private Long accaUserId;		// 注册用户
	@ApiModelProperty(value = "考试id")
	private Long examId;		// 考试id
	
	
	
	@ApiModelProperty(value = "考试开始时间")
	private Date examStartTime;		// 考试开始时间
	@ApiModelProperty(value = "考试结束时间")
	private Date examEndTime;		// 考试结束时间
	//@ApiModelProperty(value = "考试报名截止时间")
	@JsonIgnore
	private Date examSignupEndtime;		// 报名截止时间
	@ApiModelProperty(value = "考试时间格式化显示字符串")
	private String showExamTimeStr;
	@JsonIgnore
	private Integer totalSeats;		// 总席位数
	//@ApiModelProperty(value = "考试已经被占用座位数")
	@JsonIgnore
	private Integer usedSeats;		// 已经被占用的席位数
	@ApiModelProperty(value = "考试剩余可用座位")
	private Integer availableSeats;//剩余可用座位
	
	
	@ApiModelProperty(value = "考点所在城市id")
	private Integer examCityId;		// 城市id
	@ApiModelProperty(value = "考点所在城市名称")
	private String examCityName;		// 考点城市名称
	@ApiModelProperty(value = "考点详细地址")
	private String examDetailAddress;		// 详细地址
	@ApiModelProperty(value = "考点名称")
	private String examPlaceName;		// 考点名称
	@ApiModelProperty(value = "考点编号")
	private String examPlaceSn;		// 考点编号
	
	
	
	@ApiModelProperty(value = "科目")
	private String course;		// 科目
	@ApiModelProperty(value = "科目名称")
	private String courseName;		// 科目名称
	@ApiModelProperty(value = "科目英文名称")
	private String englishName;		// 英文名称
	@ApiModelProperty(value = "科目英文缩写")
	private String englishShortName;		// 英文缩写
	@ApiModelProperty(value = "科目价格")
	@JsonSerialize(using = CustomDoubleSerialize.class) 
	private double price;		// 报名费
	@JsonIgnore
	private double studentPrice;		// 学员报名费
	
	@JsonIgnore
	private Long examPlaceImageId;		// 考点位置图片id
	@ApiModelProperty(value = "考点联系人名称")
	private String examPlaceContantName;		// 联系人
	@ApiModelProperty(value = "考点联系人电话")
	private String examPlaceContantPhone;		// 联系电话

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色
	
	public AppExamSelfCart() {
		super();
	}

	public AppExamSelfCart(String id){
		super(id);
	}

	@NotNull(message="self_exam_cart_id不能为空")
	public Long getSelfExamCartId() {
		return selfExamCartId;
	}

	public void setSelfExamCartId(Long selfExamCartId) {
		this.selfExamCartId = selfExamCartId;
	}
	
	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}
	
	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}
	
	@Length(min=0, max=256, message="考试版本json字符串长度必须介于 0 和 256 之间")
	public String getExamVersionJson() {
		return examVersionJson;
	}

	public void setExamVersionJson(String examVersionJson) {
		this.examVersionJson = examVersionJson;
	}
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
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

	public String getExamDetailAddress() {
		return examDetailAddress;
	}

	public void setExamDetailAddress(String examDetailAddress) {
		this.examDetailAddress = examDetailAddress;
	}

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public String getExamPlaceSn() {
		return examPlaceSn;
	}

	public void setExamPlaceSn(String examPlaceSn) {
		this.examPlaceSn = examPlaceSn;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public AppExamVersion getExamVersion() {
		this.examVersion = JsonMapper.getInstance().fromJson(this.examVersionJson, AppExamVersion.class);
		return examVersion;
	}

	public void setExamVersion(AppExamVersion examVersion) {
		this.examVersion = examVersion;
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
		courseColor = RespConstants.courseColorMap.get(course);
		return courseColor;
	}
	
}