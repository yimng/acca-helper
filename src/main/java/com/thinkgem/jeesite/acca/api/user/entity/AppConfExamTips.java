/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * AppConfExamTipsEntity
 * @author Ivan
 * @version 2016-08-25
 */
public class AppConfExamTips extends DataEntity<AppConfExamTips> {
	
	private static final long serialVersionUID = 1L;
	private String officialExamProcess;		// F5-P7报考流程
	
	public AppConfExamTips() {
		super();
	}

	public AppConfExamTips(String id){
		super(id);
	}

	@Length(min=0, max=256, message="F5-P7报考流程长度必须介于 0 和 256 之间")
	public String getOfficialExamProcess() {
		return officialExamProcess;
	}

	public void setOfficialExamProcess(String officialExamProcess) {
		this.officialExamProcess = officialExamProcess;
	}
	
}