package com.thinkgem.jeesite.acca.api.model.response;

import com.thinkgem.jeesite.acca.api.plan.entity.AppUserLearningPlan;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 学习规划结果
 * @author Young
 * @version 2016-08-26
 */
public class LearningPlanInfo extends BaseModel{
	@ApiModelProperty(value = "规划年份",required=true)
	private String year;

	@ApiModelProperty(value = "规划详情",required=true)
	private Map<String,List<AppUserLearningPlan>> content;

	@ApiModelProperty(value = "提醒信息",required=true)
	private Map<String,Integer> tipsInfo;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Map<String, List<AppUserLearningPlan>> getContent() {
		return content;
	}

	public void setContent(Map<String, List<AppUserLearningPlan>> content) {
		this.content = content;
	}

	public Map<String, Integer> getTipsInfo() {
		return tipsInfo;
	}

	public void setTipsInfo(Map<String, Integer> tipsInfo) {
		this.tipsInfo = tipsInfo;
	}
}
