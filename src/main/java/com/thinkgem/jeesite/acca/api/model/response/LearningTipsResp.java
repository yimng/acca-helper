package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

public class LearningTipsResp extends BaseObjResponse {

	@ApiModelProperty(value = "是否有规划 1代表有,0代表无")
	private Integer hasPlan;
	@ApiModelProperty(value = "规划的开始年份")
	private Integer startYear;
	@ApiModelProperty(value = "规划的结束年份")
	private Integer endYear;

	public LearningTipsResp(Integer respCode) {
		super(respCode);
	}

	public LearningTipsResp(List<Map<String,String>> list) {
		super(list);
	}

	public Integer getHasPlan() {
		return hasPlan;
	}

	public void setHasPlan(Integer hasPlan) {
		this.hasPlan = hasPlan;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public Integer getEndYear() {
		return endYear;
	}

	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}
}
