/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.constant.WebConstant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * 考试订单类Entity
 * @author Michael
 * @version 2016-08-17
 */
public class WebOrder extends DataEntity<WebOrder> {
	
	private static final long serialVersionUID = 1L;
	private Long orderId;		// order_id
	private Integer examType;		// 考试类型：1自有考试，6官方机考和笔考
	private Long accaUserId;		// acca_user_id
	private String phone;		// 当前用户的登陆手机号
	private String registerName;		// 注册acca的真是姓名
	private String registerCardNumber;		// 身份证号码
	private String registerPhone;		// acca注册手机号
	private String registerEmail;		// acca注册邮箱
	private Long registerWhiteColorImgId;		// acca注册用2寸白底彩色照片id
	private String accaRegisterName;		// acca注册账号
	private String accaRegisterPassword;		// acca密码
	private String org;
	private Date examSignupTime;		// 报名时间
	private Integer orderStatus;		// 状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消
	private String amount;		// 报名费用
	private Long orderPayImgId;		// 支付截图
	private String checkPersonId;		// 审核人id
	private String checkPersonName;		// 审核人名称
	private Date checkTime;		// 审核时间
	private String checkReason;		// 审核不通过原因
	private Integer cardType;
	private String examTimeStr;
	
	private FileInfo image;  //身份证图片
	private FileInfo file;   //支付凭证图片
	private String img1;
	private String img2;
	private String examTypeName;
	private String courseNames;
	private String operaName;
	private String course;
	private String orderStatusName;
	private Map<Integer, String> orderMaps = Constants.ORDER_STATUS_TYPES;
	private Integer uncheck = Constants.OrderStatus.uncheckd;
	private Integer checkSuccess = Constants.OrderStatus.checkSuccess;
	private Integer checkFail = Constants.OrderStatus.checkFail;
	private List<WebSignup> signups;
	private Long examSignupId;
	private List<WebOrder> orders;
	private Date expireDate;
	private Long examId;
	
	public WebOrder() {
		super();
	}

	public WebOrder(String id){
		super(id);
	}

	@NotNull(message="order_id不能为空")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=11, message="考试类型：1自有考试，6官方机考和笔考长度必须介于 0 和 11 之间")
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	@Length(min=0, max=32, message="当前用户的登陆手机号长度必须介于 0 和 32 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamSignupTime() {
		return examSignupTime;
	}

	public void setExamSignupTime(Date examSignupTime) {
		this.examSignupTime = examSignupTime;
	}
	
	@Length(min=0, max=11, message="状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消长度必须介于 0 和 11 之间")
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public Long getOrderPayImgId() {
		return orderPayImgId;
	}

	public void setOrderPayImgId(Long orderPayImgId) {
		this.orderPayImgId = orderPayImgId;
	}
	
	@Length(min=0, max=128, message="审核人id长度必须介于 0 和 128 之间")
	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}
	
	@Length(min=0, max=64, message="审核人名称长度必须介于 0 和 64 之间")
	public String getCheckPersonName() {
		return checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	@Length(min=0, max=512, message="审核不通过原因长度必须介于 0 和 512 之间")
	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public FileInfo getImage() {
		return image;
	}

	public void setImage(FileInfo image) {
		this.image = image;
	}

	public FileInfo getFile() {
		return file;
	}

	public void setFile(FileInfo file) {
		this.file = file;
	}

	public String getImg1() {
		return JsonMapper.getInstance().toJson(this.image);
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return JsonMapper.getInstance().toJson(this.file);
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getExamTypeName() {
		return Constants.EXAM_TYPE_NAMES.get(this.examType);
	}

	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}

	public String getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(String courseNames) {
		this.courseNames = courseNames;
	}

	public String getOperaName() {
		if(this.orderStatus != null && this.orderStatus == Constants.OrderStatus.uncheckd){
			return WebConstant.START_REV;
		} else {
			return WebConstant.FIND;
		}
	}

	public void setOperaName(String operaName) {
		this.operaName = operaName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getOrderStatusName() {
		return Constants.ORDER_STATUS_TYPES.get(this.orderStatus);
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public Map<Integer, String> getOrderMaps() {
		return orderMaps;
	}

	public void setOrderMaps(Map<Integer, String> orderMaps) {
		this.orderMaps = orderMaps;
	}

	public List<WebSignup> getSignups() {
		return signups;
	}

	public void setSignups(List<WebSignup> signups) {
		this.signups = signups;
	}

	public Integer getUncheck() {
		return uncheck;
	}

	public void setUncheck(Integer uncheck) {
		this.uncheck = uncheck;
	}

	public Long getExamSignupId() {
		return examSignupId;
	}

	public void setExamSignupId(Long examSignupId) {
		this.examSignupId = examSignupId;
	}

	public List<WebOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<WebOrder> orders) {
		this.orders = orders;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getExamTimeStr() {
		return examTimeStr;
	}

	public void setExamTimeStr(String examTimeStr) {
		this.examTimeStr = examTimeStr;
	}

	public Integer getCheckSuccess() {
		return checkSuccess;
	}

	public void setCheckSuccess(Integer checkSuccess) {
		this.checkSuccess = checkSuccess;
	}

	public Integer getCheckFail() {
		return checkFail;
	}

	public void setCheckFail(Integer checkFail) {
		this.checkFail = checkFail;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
	
}