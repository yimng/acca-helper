/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.feedback.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * WebFeedbackEntity
 * @author Ivan
 * @version 2016-08-20
 */
public class WebFeedback extends DataEntity<WebFeedback> {
	
	private static final long serialVersionUID = 1L;
	private Long feedbackId;		// feedback_id
	private String content;		// 反馈内容
	private String phone;		// 联系方式
	
	public WebFeedback() {
		super();
	}

	public WebFeedback(String id){
		super(id);
	}

	@NotNull(message="feedback_id不能为空")
	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
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