/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.model.request;




import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.freetek.api.model.BaseModel;

/**
 * 考试科目Entity
 * @author Young
 * @version 2016-08-12
 */
public class ExamTicketImageUploadModel extends BaseModel {
	
	
	private Long accaUserId;
	
	private String htmlSrc;
	
	private static final long serialVersionUID = 1L;
		
	public ExamTicketImageUploadModel() {
		super();
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

	public String getHtmlSrc() {
		return htmlSrc;
	}

	public void setHtmlSrc(String htmlSrc) {
		this.htmlSrc = htmlSrc;
	}

		
}