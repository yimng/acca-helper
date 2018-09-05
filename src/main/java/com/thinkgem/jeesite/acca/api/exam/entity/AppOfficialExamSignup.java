/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinkgem.jeesite.acca.api.exam.util.ExamConstant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.util.JsonSerializer.CustomDoubleSerialize;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * 官方考试报名类Entity
 * @author Michael
 * @version 2016-08-17
 */
public class AppOfficialExamSignup extends DataEntity<AppOfficialExamSignup> {
	
	private static final long serialVersionUID = 1L;
	private Long examSignupId;		// exam_signup_id
	private Integer examType;		// 考试类型：1自有考试，2官方机考，4官方笔考
	private Long examId;		// 考试id
	private Long examCourseId;		// 考试科目id
	private String examVersionJson;		// 版本json字符串，如{&ldquo;examVersionId&rdquo;:1,
	private Long accaUserId;		// 用户id
	private String registerName;		// 注册acca的真是姓名
	private String registerCardNumber;		// 身份证号码
	private String registerPhone;		// acca注册手机号
	private String registerEmail;		// acca注册邮箱
	private Long registerWhiteColorImgId;		// acca注册用2寸白底彩色照片id
	private String accaRegisterName;		// acca注册账号
	private String accaRegisterPassword;		// acca密码
	private String examCourse;		// 考试科目
	private String examCourseName;		// exam_course_name
	private Date examSignupTime;		// 报名时间
	private Integer examSignupStatus;		// 状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消
	private String org;		//学校/单位
	
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double price;		// 报名费用
	
	private Long orderId;
	
	private Integer cardType;
	private String examCityId;		// exam_city_id
	private String examCityName;		// exam_city_name
	private Date examStartTime;		// 考试开始时间
	private Date examEndTime;		// 考试结束时间
	private Date examSignupEndtime;		// 报名截止时间
	private String examPlaceName;
	private Long examPlaceId;
	
	private String englishName;
	private String englishShortName;
	private String examDetailAddress;
	private String examPlaceSn;
	private Long examPlaceImageId;
	private String examPlaceContantName;
	private String examPlaceContantPhone;

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色
	
	private AppExamVersion examVersion;
	
	public AppOfficialExamSignup() {
		super();
	}

	public AppOfficialExamSignup(String id){
		super(id);
	}

	public AppOfficialExamSignup(AppOfficialExam officialExam,
			AppOfficialOrder appOfficialOrder) {
		if(officialExam != null){
			this.examType = officialExam.getExamType();
			this.examId = officialExam.getExamId();
			/*this.examCourseId = officialExam.getExamCourseId();
			this.examVersionJson = officialExam.getExamVersionJson();*/
			this.examCourse = officialExam.getExamCourse();
			this.examCourseName = officialExam.getExamCourseName();
			this.price = officialExam.getPrice();
			this.examCityId = officialExam.getExamCityId();
			this.examCityName = officialExam.getExamCityName();
			this.examStartTime = officialExam.getExamStartTime();
			this.examEndTime = officialExam.getExamEndTime();
			this.examSignupEndtime = officialExam.getExamSignupEndtime();
			this.examDetailAddress = officialExam.getExamDetailAddress();
			this.examPlaceName = officialExam.getExamPlaceName();
			this.examPlaceSn = officialExam.getExamPlaceSn();
			this.examPlaceImageId = officialExam.getExamPlaceImageId();
			this.examPlaceContantName = officialExam.getExamPlaceContantName();
			this.examPlaceContantPhone = officialExam.getExamPlaceContantPhone();
			this.englishName = officialExam.getEnglishName();
			this.englishShortName = officialExam.getEnglishShortName();
			this.examPlaceId = officialExam.getExamPlaceId();
		}
		this.examSignupStatus = ExamConstant.SIGNUP_TYPE_WAIT_PAY;
		this.examSignupTime = new Date();
		this.accaUserId = appOfficialOrder.getAccaUserId();
		this.registerName = appOfficialOrder.getRegisterName();
		this.registerCardNumber = appOfficialOrder.getRegisterCardNumber();
		this.registerPhone = appOfficialOrder.getRegisterPhone();
		this.registerEmail = appOfficialOrder.getRegisterEmail();
		this.registerWhiteColorImgId = appOfficialOrder.getRegisterWhiteColorImgId();
		this.accaRegisterName = appOfficialOrder.getAccaRegisterName();
		this.accaRegisterPassword = appOfficialOrder.getAccaRegisterPassword();
		this.orderId = appOfficialOrder.getOrderId();
		this.cardType = appOfficialOrder.getCardType();
	}

	@NotNull(message="exam_signup_id不能为空")
	public Long getExamSignupId() {
		return examSignupId;
	}

	public void setExamSignupId(Long examSignupId) {
		this.examSignupId = examSignupId;
	}
	
	@Length(min=0, max=11, message="考试类型：1自有考试，2官方机考，4官方笔考长度必须介于 0 和 11 之间")
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}
	
	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
	
	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}
	
	@Length(min=0, max=512, message="版本json字符串，如{&ldquo;examVersionId&rdquo;:1,长度必须介于 0 和 512 之间")
	public String getExamVersionJson() {
		return examVersionJson;
	}

	public void setExamVersionJson(String examVersionJson) {
		if(examVersionJson != null){
			this.examVersion = (AppExamVersion) JsonMapper.fromJsonString(examVersionJson, AppExamVersion.class);
		}
		this.examVersionJson = examVersionJson;
	}
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	@Length(min=0, max=64, message="注册acca的真是姓名长度必须介于 0 和 64 之间")
	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	
	@Length(min=0, max=64, message="身份证号码长度必须介于 0 和 64 之间")
	public String getRegisterCardNumber() {
		return registerCardNumber;
	}

	public void setRegisterCardNumber(String registerCardNumber) {
		this.registerCardNumber = registerCardNumber;
	}
	
	@Length(min=0, max=32, message="acca注册手机号长度必须介于 0 和 32 之间")
	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}
	
	@Length(min=0, max=64, message="acca注册邮箱长度必须介于 0 和 64 之间")
	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}
	
	public Long getRegisterWhiteColorImgId() {
		return registerWhiteColorImgId;
	}

	public void setRegisterWhiteColorImgId(Long registerWhiteColorImgId) {
		this.registerWhiteColorImgId = registerWhiteColorImgId;
	}
	
	@Length(min=0, max=64, message="acca注册账号长度必须介于 0 和 64 之间")
	public String getAccaRegisterName() {
		return accaRegisterName;
	}

	public void setAccaRegisterName(String accaRegisterName) {
		this.accaRegisterName = accaRegisterName;
	}
	
	@Length(min=0, max=64, message="acca密码长度必须介于 0 和 64 之间")
	public String getAccaRegisterPassword() {
		return accaRegisterPassword;
	}

	public void setAccaRegisterPassword(String accaRegisterPassword) {
		this.accaRegisterPassword = accaRegisterPassword;
	}
	
	@Length(min=0, max=64, message="考试科目长度必须介于 0 和 64 之间")
	public String getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}
	
	@Length(min=0, max=64, message="exam_course_name长度必须介于 0 和 64 之间")
	public String getExamCourseName() {
		return examCourseName;
	}

	public void setExamCourseName(String examCourseName) {
		this.examCourseName = examCourseName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamSignupTime() {
		return examSignupTime;
	}

	public void setExamSignupTime(Date examSignupTime) {
		this.examSignupTime = examSignupTime;
	}
	
	@Length(min=0, max=11, message="状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消长度必须介于 0 和 11 之间")
	public Integer getExamSignupStatus() {
		return examSignupStatus;
	}

	public void setExamSignupStatus(Integer examSignupStatus) {
		this.examSignupStatus = examSignupStatus;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public AppExamVersion getExamVersion() {
		return examVersion;
	}

	public void setExamVersion(AppExamVersion examVersion) {
		this.examVersion = examVersion;
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

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(String examCityId) {
		this.examCityId = examCityId;
	}

	public String getExamCityName() {
		return examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
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

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
}