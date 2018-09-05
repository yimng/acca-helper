/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.plan.entity;

import com.thinkgem.jeesite.acca.api.home.entity.AppTips;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 计划提醒
 * @author Young
 * @version 2016-09-07
 */
public class PlanTips extends DataEntity<PlanTips> {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "是否有提醒 0表示没有;1代表有")
	private Integer hasTips;
	@ApiModelProperty(value = "提醒列表")
	private List<AppTips> tipsList;

	public PlanTips() {
		super();
	}

	public PlanTips(String id){
		super(id);
	}

	public Integer getHasTips() {
		return hasTips;
	}

	public void setHasTips(Integer hasTips) {
		this.hasTips = hasTips;
	}

	public List<AppTips> getTipsList() {
		return tipsList;
	}

	public void setTipsList(List<AppTips> tipsList) {
		this.tipsList = tipsList;
	}
}