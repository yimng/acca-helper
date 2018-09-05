package com.thinkgem.jeesite.acca.api.model.request;


import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "UpdateUserInfoReq",description="修改用户参数")
public class UpdateUserInfoReq extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "头像id",required=true)
	private Long headId;
	
	@ApiModelProperty(value = "昵称",required=true)
	private String nickname;
	
	@ApiModelProperty(value = "acca学员资料-姓名",required=true)
	private String registerName;
	@ApiModelProperty(value = "acca学员资料-身份证号",required=true)
	private String registerCardNumber;
	@ApiModelProperty(value = "acca学员资料-手机号",required=true)
	private String registerPhone;
	@ApiModelProperty(value = "acca学员资料-邮箱",required=true)
	private String registerEmail;
	@ApiModelProperty(value = "acca学员资料-二寸免冠证件照图片id",required=true)
	private Long registerWhiteColorImgId;
	@ApiModelProperty(value = "acca学员资料-acca账号",required=true)
	private String accaRegisterName;
	@ApiModelProperty(value = "acca学员资料-acca密码",required=true)
	private String accaRegisterPassword;
	
	@Override
	public int isCorrectParams() {
		
		if(super.isCorrectParams()!=RespConstants.GLOBAL_SUCCESS){
			return super.isCorrectParams();
		}
		
		return RespConstants.GLOBAL_SUCCESS;
	}


	public Long getHeadId() {
		return headId;
	}


	public void setHeadId(Long headId) {
		this.headId = headId;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
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


	
}
