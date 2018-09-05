/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户相关接口Entity
 * @author lookjun
 * @version 2016-07-25
 */
public class AppSmsVcode extends DataEntity<AppSmsVcode> {
	
	private static final long serialVersionUID = 1L;
	private Long smsVcodeId;
	private String vcode;
	private String mobile;
	private Date sendDate;
	/**
	 验证码发送状态，0 推送成功，其他异常
	  */
	private Integer sendStatus;
	private Date validDate;
	/**
	 验证码是否被使用过：0未被使用，1已使用
	  */
	private Integer validStatus;
	
	public AppSmsVcode() {
		super();
	}

	public AppSmsVcode(String id){
		super(id);
	}
	
	public AppSmsVcode(String mobile, String vcode){
		this.mobile = mobile;
		this.vcode = vcode;
	}

	@NotNull(message="sms_vcode_id不能为空")
	public Long getSmsVcodeId() {
		return smsVcodeId;
	}

	public void setSmsVcodeId(Long smsVcodeId) {
		this.smsVcodeId = smsVcodeId;
	}
	
	@Length(min=1, max=10, message="验证码长度必须介于 1 和 10 之间")
	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	
	@Length(min=1, max=20, message="手机号长度必须介于 1 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="验证码生成时间不能为空")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@NotNull(message="验证码发送状态，0 推送成功，其他异常不能为空")
	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	@NotNull(message="验证码是否被使用过：0未被使用，1已使用不能为空")
	public Integer getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(Integer validStatus) {
		this.validStatus = validStatus;
	}
	
}