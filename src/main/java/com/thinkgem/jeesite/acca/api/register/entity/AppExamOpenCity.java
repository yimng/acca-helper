/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.register.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.PinyinUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 考试开通城市Entity
 * @author Young
 * @version 2016-08-09
 */
@ApiModel("考试开通城市")
public class AppExamOpenCity extends DataEntity<AppExamOpenCity> implements Comparable<AppExamOpenCity>{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "城市id")
	private Integer cityId;		// 城市id
	@JsonIgnore
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考,8中博诚通分部，如果同时支持多个，则采用位运算&ldquo;或&rdquo;进行，比如，取值为3，则支持自有考试和官方机考
	@ApiModelProperty(value = "城市名称")
	private String areaName;    //城市名称
	
	@JsonIgnore
	private String shortSpell;//平阴简写

	public AppExamOpenCity() {
		super();
	}

	public AppExamOpenCity(String id){
		super(id);
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getShortSpell() {
		this.shortSpell = PinyinUtils.converterToFirstSpell(this.areaName);
		return shortSpell;
	}

	public void setShortSpell(String shortSpell) {
		this.shortSpell = shortSpell;
	}

	@Override
	public int compareTo(AppExamOpenCity o) {
		if(this.getShortSpell().compareTo(o.getShortSpell())>0){
			return 1;
		}
		if(this.getShortSpell().compareTo(o.getShortSpell())<0){
			return -1;
		}
		return 0;
		
	}
}