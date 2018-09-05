/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinkgem.jeesite.acca.api.model.request.AppOfficialOrderReq;
import com.thinkgem.jeesite.acca.api.model.request.GetOfficialOrderInfoReq;
import com.thinkgem.jeesite.acca.api.model.request.GetOrderByMonthReq;
import com.thinkgem.jeesite.acca.api.model.request.GetOrderListByStatisReq;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.JsonSerializer.CustomDoubleSerialize;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 官方考试订单类Entity
 * @author Michael
 * @version 2016-08-17
 */
public class AppOfficialOrder extends DataEntity<AppOfficialOrder> {
	
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
	private Date examSignupTime;		// 报名时间
	private Integer orderStatus;		// 状态：10待支付，20待审核，30审核通过，40审核不通过，50已取消
	private String org;		//学校/单位
	
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double amount;		// 报名费用
	
	private Long orderPayImgId;		// 支付截图
	private String checkPersonId;		// 审核人id
	private String checkPersonName;		// 审核人名称
	private Date checkTime;		// 审核时间
	private String checkReason;		// 审核不通过原因
	
	private Integer cardType;
	private String examTimeStr;
	private Long appUserId;
	
	public AppOfficialOrder() {
		super();
	}

	public AppOfficialOrder(String id){
		super(id);
	}
	
	public AppOfficialOrder(Long orderId){
		this.orderId = orderId;
	}
	
	public AppOfficialOrder(Long orderId, int orderStatus){
		this.orderId = orderId;
		this.orderStatus = orderStatus;
	}
	
	public AppOfficialOrder(AppOfficialOrderReq req){
		this.accaUserId = req.getAccaUserId();
		this.phone = req.getPhone();
		this.registerName = req.getRegisterName();
		this.registerCardNumber = req.getRegisterCardNumber();
		this.registerPhone = req.getRegisterPhone();
		this.registerEmail = req.getRegisterEmail();
		this.registerWhiteColorImgId = req.getRegisterWhiteColorImgId();
		this.accaRegisterName = req.getAccaRegisterName();
		this.accaRegisterPassword = req.getAccaRegisterPassword();
		this.examSignupTime = new Date();
		this.cardType = req.getCardType();
		this.examTimeStr = req.getExamTimeStr();
	}
	
	public AppOfficialOrder(GetOrderListByStatisReq req){
		this.accaUserId = req.getAccaUserId();
		this.orderStatus = req.getOrderStatus();
		this.pageApi = req.getPage();
	}
	
	public AppOfficialOrder(GetOrderByMonthReq req){
		this.examTimeStr = req.getExamTimeStr();
		this.accaUserId = req.getAccaUserId();
	}
	
	public AppOfficialOrder(GetOfficialOrderInfoReq req){
		this.orderId = req.getOrderId();
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
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
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
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

	public String getExamTimeStr() {
		return examTimeStr;
	}

	public void setExamTimeStr(String examTimeStr) {
		this.examTimeStr = examTimeStr;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
}