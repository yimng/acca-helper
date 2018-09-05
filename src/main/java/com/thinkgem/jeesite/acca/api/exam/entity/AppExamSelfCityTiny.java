/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.acca.api.plan.entity.AppExamCourse;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;

/**
 * AppExamEntity
 * @author Ivan
 * @version 2016-08-15
 */
public class AppExamSelfCityTiny extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "城市id")
	private Long cityId;		// exam_id

	@ApiModelProperty(value = "城市名称")
	private String cityName;
	
	@ApiModelProperty(value = "考点列表")
	private List<AppExamSelfPlaceTiny> examPlaceList;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<AppExamSelfPlaceTiny> getExamPlaceList() {
		return examPlaceList;
	}

	public void setExamPlaceList(List<AppExamSelfPlaceTiny> examPlaceList) {
		this.examPlaceList = examPlaceList;
	}
	
	
}