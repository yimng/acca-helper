package com.thinkgem.jeesite.acca.web.exam.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class WebSmallCity extends DataEntity<WebSmallCity> {

	private static final long serialVersionUID = 4924433471844080519L;
	
	private Long openCityId;
	
	private Integer examCityId;
	
	private String cityName;
	
	private Integer examType;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public Long getOpenCityId() {
		return openCityId;
	}

	public void setOpenCityId(Long openCityId) {
		this.openCityId = openCityId;
	}

	public Integer getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(Integer examCityId) {
		this.examCityId = examCityId;
	}

}
