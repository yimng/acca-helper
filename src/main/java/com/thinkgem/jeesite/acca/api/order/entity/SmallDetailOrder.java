package com.thinkgem.jeesite.acca.api.order.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.freetek.util.JsonSerializer.CustomDoubleSerialize;

import java.io.Serializable;
import java.util.List;

public class SmallDetailOrder implements Serializable {

	private static final long serialVersionUID = -6592224619157733260L;
	
	private Long orderId;


	private Long whiteImageId;
	private String cardImgUrl;    //身份证图片路径
	
	@JsonSerialize(using = CustomDoubleSerialize.class)
	private Double amount;   //总费用
	
	private String registerName;		// 注册acca的真是姓名
	
	private String registerCardNumber;		// 身份证号码
	
	private String phone;		// 当前用户的登陆手机号
	
	private String registerEmail;		// acca注册邮箱
	
	private String accaRegisterName;		// acca注册账号
	
	private String registerPhone;
	
	private String accaRegisterPassword;

	private Long payImageId;
	private String payImgUrl;      //支付凭证图片路径
	
	private String checkReason;

	private String org;		//学校/单位
	
	private List<SmallSignup> signupList;

	public String getCardImgUrl() {
		return cardImgUrl;
	}

	public void setCardImgUrl(String cardImgUrl) {
		this.cardImgUrl = Global.getConfig("upload.file.base.url") + cardImgUrl;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public String getRegisterCardNumber() {
		return registerCardNumber;
	}

	public void setRegisterCardNumber(String registerCardNumber) {
		this.registerCardNumber = registerCardNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	public String getAccaRegisterName() {
		return accaRegisterName;
	}

	public void setAccaRegisterName(String accaRegisterName) {
		this.accaRegisterName = accaRegisterName;
	}

	public String getPayImgUrl() {
		return payImgUrl;
	}

	public void setPayImgUrl(String payImgUrl) {
		this.payImgUrl = Global.getConfig("upload.file.base.url") + payImgUrl;
	}

	public List<SmallSignup> getSignupList() {
		return signupList;
	}

	public void setSignupList(List<SmallSignup> signupList) {
		this.signupList = signupList;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getAccaRegisterPassword() {
		return accaRegisterPassword;
	}

	public void setAccaRegisterPassword(String accaRegisterPassword) {
		this.accaRegisterPassword = accaRegisterPassword;
	}

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Long getWhiteImageId() {
		return whiteImageId;
	}

	public void setWhiteImageId(Long whiteImageId) {
		this.whiteImageId = whiteImageId;
	}

	public Long getPayImageId() {
		return payImageId;
	}

	public void setPayImageId(Long payImageId) {
		this.payImageId = payImageId;
	}
}
