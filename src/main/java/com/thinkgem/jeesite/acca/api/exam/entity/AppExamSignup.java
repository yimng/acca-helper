/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * AppExamSignupEntity
 * @author Ivan
 * @version 2016-08-19
 */
public class AppExamSignup extends DataEntity<AppExamSignup> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "考试报名id")
	private Long examSignupId;		// exam_signup_id
	@ApiModelProperty(value = "考试类型：1自有考试，2官方机考，4官方笔考")
	private Integer examType;		// 考试类型：1自有考试，2官方机考，4官方笔考
	@ApiModelProperty(value = "考试id")
	private Long examId;		// 考试id
	@ApiModelProperty(value = "考试科目id")
	private Long examCourseId;		// 考试科目id
	@ApiModelProperty(value = "报考科目考试版本json")
	private String examVersionJson;		// 版本json字符串，如{&ldquo;examVersionId&rdquo;:1,
	@ApiModelProperty(value = "用户id")
	private Long accaUserId;		// 用户id
	@ApiModelProperty(value = "注册acca的真是姓名")
	private String registerName;		// 注册acca的真是姓名
	@ApiModelProperty(value = "注册acca的身份证件号码")
	private String registerCardNumber;		// 身份证号码
	@ApiModelProperty(value = "注册acca的手机号")
	private String registerPhone;		// acca注册手机号
	@ApiModelProperty(value = "注册acca的邮箱")
	private String registerEmail;		// acca注册邮箱
	@ApiModelProperty(value = "注册acca用2寸白底彩色照片id")
	private Long registerWhiteColorImgId;		// acca注册用2寸白底彩色照片id
	@ApiModelProperty(value = "acca注册账号")
	private String accaRegisterName;		// acca注册账号
	@ApiModelProperty(value = "acca密码")
	private String accaRegisterPassword;		// acca密码
	@ApiModelProperty(value = "考试科目：如F1")
	private String examCourse;		// 考试科目
	@ApiModelProperty(value = "考试科目名称")
	private String examCourseName;		// exam_course_name
	@ApiModelProperty(value = "考试报名时间")
	private Date examSignupTime;		// 报名时间
	@ApiModelProperty(value = "考试报名状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消")
	private Integer examSignupStatus;		// 状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消
	@ApiModelProperty(value = "考试报名费用")
	private Double price;		// 报名费用
	@ApiModelProperty(value = "acca身份证件类型")
	private Integer cardType;		// acca身份证件类型：1.身份证;2.护照
	@ApiModelProperty(value = "学校/单位")
	private String org;		//学校/单位

	
	@ApiModelProperty(value = "考试所在城市id")
	private Integer examCityId;		// exam_city_id
	@ApiModelProperty(value = "考试所在城市名称")
	private String examCityName;		// exam_city_name
	
	
	@ApiModelProperty(value = "考试开始时间")
	private Date examStartTime;		// 考试开始时间
	@ApiModelProperty(value = "考试结束时间")
	private Date examEndTime;		// 考试结束时间
	//@ApiModelProperty(value = "考试报名截止时间")
	@JsonIgnore
	private Date examSignupEndtime;		// 报名截止时间
	
	
	@ApiModelProperty(value = "考试考点id")
	private Long examPlaceId;		
	@ApiModelProperty(value = "考点名称")
	private String examPlaceName;		// 考点名称// 考点id
	@ApiModelProperty(value = "考点详细地址")
	private String examDetailAddress;		// 详细地址
	@ApiModelProperty(value = "考点编号")
	private String examPlaceSn;		// 考点编号
	@JsonIgnore
	private Long examPlaceImageId;		// 考点位置图片id
	@ApiModelProperty(value = "考点联系人名称")
	private String examPlaceContantName;		// 联系人
	@ApiModelProperty(value = "考点联系人电话")
	private String examPlaceContantPhone;		// 联系电话 
	
	@ApiModelProperty(value = "科目英文名称")
	private String englishName;		// 英文名称
	@ApiModelProperty(value = "科目英文缩写")
	private String englishShortName;		// 英文缩写
	@ApiModelProperty(value = "所属订单id")
	private Long orderId;
	@ApiModelProperty(value = "版本信息")
	private AppExamVersion examVersion;
	
	@ApiModelProperty(value = "考试时间格式化显示字符串")
	private String showExamTimeStr;

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色

	public AppExamSignup() {
		super();
	}

	public AppExamSignup(String id){
		super(id);
	}

	@NotNull(message="exam_signup_id不能为空")
	public Long getExamSignupId() {
		return examSignupId;
	}

	public void setExamSignupId(Long examSignupId) {
		this.examSignupId = examSignupId;
	}
	
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
	
	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public AppExamVersion getExamVersion() {
		if(StringUtils.isEmpty(this.examVersionJson)){
			return null;
		}
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

	/**
	 * 考试的颜色
	 * @return
	 */
	public String getCourseColor() {
		courseColor = RespConstants.courseColorMap.get(examCourse);
		return courseColor;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
}