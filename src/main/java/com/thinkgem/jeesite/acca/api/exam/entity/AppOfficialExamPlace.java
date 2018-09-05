package com.thinkgem.jeesite.acca.api.exam.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class AppOfficialExamPlace extends DataEntity<AppOfficialExamPlace> {

	private static final long serialVersionUID = -1635037610161985642L;
	
	private Long examId;
	private Long examPlaceId;
	
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考，此处只能单一取值
	private String examCityId;		// 城市id
	private String examCityName;		// 考点城市名称
	private String examDetailAddress;		// 详细地址
	private String examPlaceName;		// 考点名称
	private String examPlaceSn;		// 考点编号
	private Long examPlaceImageId;		// 考点位置图片id
	private String lng;		// 经度
	private String lat;		// 纬度
	private String examPlaceContantName;		// 联系人
	private String examPlaceContantPhone;		// 联系电话
	
	private String examStartTimeStr;
	
	public AppOfficialExamPlace(){}
	
	public AppOfficialExamPlace(Long examPlaceId){
		this.examPlaceId = examPlaceId;
	}
	
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public String getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(String examCityId) {
		this.examCityId = examCityId;
	}

	public String getExamCityName() {
		return examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
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

	public Long getExamPlaceImageId() {
		return examPlaceImageId;
	}

	public void setExamPlaceImageId(Long examPlaceImageId) {
		this.examPlaceImageId = examPlaceImageId;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getExamPlaceContantName() {
		return examPlaceContantName;
	}

	public void setExamPlaceContantName(String examPlaceContantName) {
		this.examPlaceContantName = examPlaceContantName;
	}

	public String getExamPlaceContantPhone() {
		return examPlaceContantPhone;
	}

	public void setExamPlaceContantPhone(String examPlaceContantPhone) {
		this.examPlaceContantPhone = examPlaceContantPhone;
	}

	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
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

	public String getExamStartTimeStr() {
		return examStartTimeStr;
	}

	public void setExamStartTimeStr(String examStartTimeStr) {
		this.examStartTimeStr = examStartTimeStr;
	}
}
