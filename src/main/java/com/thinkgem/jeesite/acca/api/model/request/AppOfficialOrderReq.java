package com.thinkgem.jeesite.acca.api.model.request;

import com.thinkgem.jeesite.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;

import com.thinkgem.jeesite.acca.api.exam.entity.Appointment;
import com.thinkgem.jeesite.common.utils.AppUtils;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApiModel("AppOfficialOrderReq")
public class AppOfficialOrderReq extends BaseRequest {

	private static final long serialVersionUID = -4995628104717112990L;
	
	private Long accaUserId;		// acca_user_id
	private String phone;		// 当前用户的登陆手机号
	private String registerName;		// 注册acca的真是姓名
	private String registerCardNumber;		// 身份证号码
	private String registerPhone;		// acca注册手机号
	private String registerEmail;		// acca注册邮箱
	private Long registerWhiteColorImgId;		// acca注册用2寸白底彩色照片id
	private String accaRegisterName;		// acca注册账号
	private String accaRegisterPassword;		// acca密码
	private Integer cardType;
	private String examTimeStr;
	@ApiModelProperty(value = "学校/单位")
	private String org;
	
	private Appointment penAppInfo;
	//private List<Long> penCourseIds;
	private Appointment macAppInfo;
	//private List<Long> macCourseIds;
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	@Override
	public int isCorrectParams() {
		// TODO Auto-generated method stub
		int flag = super.isCorrectParams();
		if (StringUtils.isEmpty(org)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(flag != RespConstants.GLOBAL_SUCCESS){
			return flag;
		} else {
			if(!IdcardUtils.validateCard(registerCardNumber)){
				return RespConstants.EXAM_IDCARD_ERROR;
			}
			if(!AppUtils.isMobileNum(registerPhone)){
				return RespConstants.EXAM_MOBILE_ERROR;
			}
			//邮箱校验
			Pattern emailPatten = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
			Matcher emailMatcher = emailPatten.matcher(registerEmail);
			boolean emailFlag = emailMatcher.matches();
			if (!emailFlag) {
				return RespConstants.EXAM_EMAIL_ERROR;
			}
		}
		return RespConstants.GLOBAL_SUCCESS;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getRegisterPhone() {
		return registerPhone;
	}

	public void setRegisterPhone(String registerPhone) {
		this.registerPhone = registerPhone;
	}

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

	public String getAccaRegisterName() {
		return accaRegisterName;
	}

	public void setAccaRegisterName(String accaRegisterName) {
		this.accaRegisterName = accaRegisterName;
	}

	public String getAccaRegisterPassword() {
		return accaRegisterPassword;
	}

	public void setAccaRegisterPassword(String accaRegisterPassword) {
		this.accaRegisterPassword = accaRegisterPassword;
	}

	public Appointment getPenAppInfo() {
		return penAppInfo;
	}

	public void setPenAppInfo(Appointment penAppInfo) {
		this.penAppInfo = penAppInfo;
	}

	public Appointment getMacAppInfo() {
		return macAppInfo;
	}

	public void setMacAppInfo(Appointment macAppInfo) {
		this.macAppInfo = macAppInfo;
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

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
}
