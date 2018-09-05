package com.thinkgem.jeesite.acca.api.exam.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.thinkgem.jeesite.common.persistence.DataEntity;

@ApiModel("考季类")
public class AppOfficialExamMonth extends DataEntity<AppOfficialExamMonth> {

	private static final long serialVersionUID = 7529754907805538944L;

	@ApiModelProperty("传给后台的考试年月字符串")
	private String monthSend;//向后台传递的年月字符串
	
	@ApiModelProperty("前台显示的考试年月字符串")
	private String monthShow;//app显示的年月字符串

	public String getMonthSend() {
		return monthSend;
	}

	public void setMonthSend(String monthSend) {
		this.monthSend = monthSend;
	}

	public String getMonthShow() {
		return monthShow;
	}

	public void setMonthShow(String monthShow) {
		this.monthShow = monthShow;
	}

	@Override
	public boolean equals(Object obj) {
		AppOfficialExamMonth ae = (AppOfficialExamMonth) obj;
		if(monthSend.equals(ae.getMonthSend())){
			return true;
		} else {
			return false;
		}
	}
	
}
