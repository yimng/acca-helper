/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import com.thinkgem.jeesite.freetek.util.TimeUtils;

/**
 * 考试类Entity
 * @author Michael
 * @version 2016-08-27
 */
public class WebExamSignup extends DataEntity<WebExamSignup> {
	
	private static final long serialVersionUID = 1L;
	private Long examSignupId;		// exam_signup_id
	private String examType;		// 考试类型：1自有考试，2官方机考，4官方笔考
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
	private String org;
	private String iszbg;
	
	private String examCourse;		// 考试科目
	private String examCourseName;		// exam_course_name
	private Date examSignupTime;		// 报名时间
	private Integer examSignupStatus;		// 状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消
	private String price;		// 报名费用
	private Long orderId;		// order_id
	private String cardType;		// acca身份证件类型：1.身份证;2.护照
	private String examCityId;		// 考试城市id
	private String examCityName;		// 考试城市名称
	private Date examStartTime;		// 考试开始时间
	private Date examEndTime;		// 考试结束时间
	private Date examSignupEndtime;		// 报名截止日期
	private String examDetailAddress;		// 考试详细地址
	private String examPlaceName;		// 考点名称
	private String examPlaceSn;		// 考点编号
	private Long examPlaceImageId;		// 考点位置图片id
	private String examPlaceContantName;		// 联系人
	private String examPlaceContantPhone;		// 联系电话
	private String englishName;		// 科目英文名称
	private String englishShortName;		// 科目英文缩写
	private Long examPlaceId;		// exam_place_id
	
	private String orderStatusName;
//	private String timeStr;
	
	private FileInfo image;
	private String imageStr;
	private String versionName;
	
	public String getIszbg() {
		return iszbg;
	}

	public void setIszbg(String iszbg) {
		this.iszbg = iszbg;
	}
	
	
	public WebExamSignup() {
		super();
	}

	public WebExamSignup(String id){
		super(id);
	}

	@NotNull(message="exam_signup_id不能为空")
	public Long getExamSignupId() {
		return examSignupId;
	}

	public void setExamSignupId(Long examSignupId) {
		this.examSignupId = examSignupId;
	}
	
	@Length(min=0, max=11, message="考试类型：1自有考试，2官方机考，4官方笔考长度必须介于 0 和 11 之间")
	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
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
		if(!StringUtils.isEmpty(examVersionJson)){
			SmallVersion sv = (SmallVersion) JsonMapper.fromJsonString(examVersionJson, SmallVersion.class);
			this.versionName = sv.getExamVersionName();
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
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	@Length(min=0, max=11, message="考试城市id长度必须介于 0 和 11 之间")
	public String getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(String examCityId) {
		this.examCityId = examCityId;
	}
	
	@Length(min=0, max=64, message="考试城市名称长度必须介于 0 和 64 之间")
	public String getExamCityName() {
		return examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
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
	
	@Length(min=0, max=256, message="考试详细地址长度必须介于 0 和 256 之间")
	public String getExamDetailAddress() {
		return examDetailAddress;
	}

	public void setExamDetailAddress(String examDetailAddress) {
		this.examDetailAddress = examDetailAddress;
	}
	
	@Length(min=0, max=64, message="考点名称长度必须介于 0 和 64 之间")
	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}
	
	@Length(min=0, max=64, message="考点编号长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getExamPlaceContantName() {
		return examPlaceContantName;
	}

	public void setExamPlaceContantName(String examPlaceContantName) {
		this.examPlaceContantName = examPlaceContantName;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getExamPlaceContantPhone() {
		return examPlaceContantPhone;
	}

	public void setExamPlaceContantPhone(String examPlaceContantPhone) {
		this.examPlaceContantPhone = examPlaceContantPhone;
	}
	
	@Length(min=0, max=64, message="科目英文名称长度必须介于 0 和 64 之间")
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	@Length(min=0, max=64, message="科目英文缩写长度必须介于 0 和 64 之间")
	public String getEnglishShortName() {
		return englishShortName;
	}

	public void setEnglishShortName(String englishShortName) {
		this.englishShortName = englishShortName;
	}
	
	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public String getOrderStatusName() {
		return Constants.ORDER_STATUS_TYPES.get(this.examSignupStatus);
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

//	public String getTimeStr() {
//		return TimeUtils.DateToStr(this.examStartTime, TimeUtils.dateFormat1)+"-"+TimeUtils.DateToStr(this.examEndTime, TimeUtils.dateFormat2);
//	}
//
//	public void setTimeStr(String timeStr) {
//		this.timeStr = timeStr;
//	}

	public FileInfo getImage() {
		return image;
	}

	public void setImage(FileInfo image) {
		this.image = image;
	}

	public String getImageStr() {
		return JsonMapper.getInstance().toJson(this.image);
	}

	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
	
}