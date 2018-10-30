package com.thinkgem.jeesite.acca.api.exam.entity;

import java.io.Serializable;

public class SmallPlace implements Serializable {

	private static final long serialVersionUID = -253835085159913217L;
	
	private Long examPlaceId;
	
	private String examPlaceName;		// 考点名称
	
	private String examDetailAddress;   //考点详细地址
	
	private String examPlaceSn;

	private String examCityId;

	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}

	public String getExamDetailAddress() {
		return examDetailAddress;
	}

	public void setExamDetailAddress(String examDetailAddress) {
		this.examDetailAddress = examDetailAddress;
	}

	public String getExamPlaceSn() {
		return examPlaceSn;
	}

	public void setExamPlaceSn(String examPlaceSn) {
		this.examPlaceSn = examPlaceSn;
	}

	public String getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(String examCityId) {
		this.examCityId = examCityId;
	}
}
