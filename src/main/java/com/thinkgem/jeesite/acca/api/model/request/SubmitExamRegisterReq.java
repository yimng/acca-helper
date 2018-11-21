package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApiModel(value = "SubmitExamRegisterReq",description="F1-F4考试：确认报名生成支付订单")
public class SubmitExamRegisterReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "acca学员资料-姓名",required=true)
	private String registerName;
	@ApiModelProperty(value = "acca学员资料-身份证件号码",required=true)
	private String registerCardNumber;
	@ApiModelProperty(value = "acca学员资料-手机号",required=true)
	private String registerPhone;
	@ApiModelProperty(value = "acca学员资料-邮箱",required=true)
	private String registerEmail;
	@ApiModelProperty(value = "acca学员资料-二寸免冠证件照图片id",required=true)
	private Long registerWhiteColorImgId;
	@ApiModelProperty(value = "acca学员资料-acca账号",required=true)
	private String accaRegisterName;
	@ApiModelProperty(value = "acca学员资料-acca密码")
	private String accaRegisterPassword;
	@ApiModelProperty(value = "acca身份证件类型：1.身份证;2.护照",required=true)
	private Integer cardType;
	@ApiModelProperty(value = "学校/单位",required = true)
	private String org;
	@ApiModelProperty(value = "考点ID", required = true)
	private Long examPlaceId;
	@ApiModelProperty(value = "优惠券ID", required = false)
	private List<Long> userCouponIds;
	
	
	@Override
    public int isCorrectParams() {
		int resp = super.isCorrectParams();
		if(resp!=RespConstants.GLOBAL_SUCCESS){
			return resp;
		}
		if(StringUtils.isEmpty(this.registerName)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(StringUtils.isEmpty(this.registerPhone)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(StringUtils.isEmpty(this.registerEmail)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(StringUtils.isEmpty(this.accaRegisterName)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(StringUtils.isEmpty(this.org)){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(StringUtils.isEmpty(this.registerCardNumber) || !IdcardUtils.validateCard(this.registerCardNumber)){
			return RespConstants.EXAM_IDCARD_ERROR;
		}
		//邮箱校验
		Pattern emailPatten = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher emailMatcher = emailPatten.matcher(registerEmail);
		boolean emailFlag = emailMatcher.matches();
		if (!emailFlag) {
            return RespConstants.EXAM_EMAIL_ERROR;
        }
		if(this.registerWhiteColorImgId==null || this.registerWhiteColorImgId==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(cardType==null || cardType==0){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		if(this.cardType!=1 && this.cardType!=2){
			return RespConstants.GLOBAL_PARAM_ERROR;
		}
		
		
		return RespConstants.GLOBAL_SUCCESS;
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


	public Integer getCardType() {
		return cardType;
	}


	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}


	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public List<Long> getUserCouponIds() {
		return userCouponIds;
	}

	public void setUserCouponIds(List<Long> userCouponIds) {
		this.userCouponIds = userCouponIds;
	}
}
