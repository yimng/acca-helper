/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * AppFeedbackEntity
 * @author Ivan
 * @version 2016-08-10
 */
public class AppFeedback extends DataEntity<AppFeedback> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 反馈内容
	private String phone;		// 联系方式
	private Long acca_user_id;
	private AppAccaUser appAccaUser;
	public AppFeedback() {
		super();
	}

	public AppAccaUser getAppAccaUser() {
		return appAccaUser;
	}

	public void setAppAccaUser(AppAccaUser appAccaUser) {
		this.appAccaUser = appAccaUser;
	}

	public Long getAcca_user_id() {
		return acca_user_id;
	}

	public void setAcca_user_id(Long accaUserId) {
		this.acca_user_id = accaUserId;
	}

	public AppFeedback(String id){
		super(id);
	}

	@Length(min=0, max=512, message="反馈内容长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="联系方式长度必须介于 0 和 64 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}