/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * AppExamPlaceEntity
 * @author Ivan
 * @version 2016-08-15
 */
public class AppExamPlace extends DataEntity<AppExamPlace> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "考点id")
	private Long examPlaceId;		// exam_place_id
	@JsonIgnore
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考，此处只能单一取值
	@ApiModelProperty(value = "考点所在城市id")
	private Integer examCityId;		// 城市id
	@ApiModelProperty(value = "考点所在城市名称")
	private String examCityName;		// 考点城市名称
	@ApiModelProperty(value = "考点详细地址")
	private String examDetailAddress;		// 详细地址
	@ApiModelProperty(value = "考点名称")
	private String examPlaceName;		// 考点名称
	@ApiModelProperty(value = "考点编号")
	private String examPlaceSn;		// 考点编号
	private Long examPlaceImageId;		// 考点位置图片id
	@ApiModelProperty(value = "经度")
	private Double lng;		// 经度
	@ApiModelProperty(value = "纬度")
	private Double lat;		// 纬度
	@ApiModelProperty(value = "考点联系人名称")
	private String examPlaceContantName;		// 联系人
	@ApiModelProperty(value = "考点联系人电话")
	private String examPlaceContantPhone;		// 联系电话
	@ApiModelProperty(value = "考点所含有的考试列表")
	private List<AppExam> examList;
	//private AppExam examList;
	
	@ApiModelProperty(value = "考点位置图片")
	private FileInfo examPlaceImage;
	
	public AppExamPlace() {
		super();
	}

	public AppExamPlace(String id){
		super(id);
	}

	@NotNull(message="exam_place_id不能为空")
	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}
	
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}
	
	public Integer getExamCityId() {
		return examCityId;
	}

	public void setExamCityId(Integer examCityId) {
		this.examCityId = examCityId;
	}
	
	@Length(min=0, max=32, message="考点城市名称长度必须介于 0 和 32 之间")
	public String getExamCityName() {
		return examCityName;
	}

	public void setExamCityName(String examCityName) {
		this.examCityName = examCityName;
	}
	
	@Length(min=0, max=256, message="详细地址长度必须介于 0 和 256 之间")
	public String getExamDetailAddress() {
		return examDetailAddress;
	}

	public void setExamDetailAddress(String examDetailAddress) {
		this.examDetailAddress = examDetailAddress;
	}
	
	@Length(min=0, max=64, message="考点名称长度必须介于 0 和 64 之间")
	public String getExamPlaceName() {
		return examPlaceName;
	}

	public void setExamPlaceName(String examPlaceName) {
		this.examPlaceName = examPlaceName;
	}
	
	@Length(min=0, max=64, message="考点编号长度必须介于 0 和 64 之间")
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
	
	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getExamPlaceContantName() {
		return examPlaceContantName;
	}

	public void setExamPlaceContantName(String examPlaceContantName) {
		this.examPlaceContantName = examPlaceContantName;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getExamPlaceContantPhone() {
		return examPlaceContantPhone;
	}

	public void setExamPlaceContantPhone(String examPlaceContantPhone) {
		this.examPlaceContantPhone = examPlaceContantPhone;
	}

	public FileInfo getExamPlaceImage() {
		return examPlaceImage;
	}

	public void setExamPlaceImage(FileInfo examPlaceImage) {
		this.examPlaceImage = examPlaceImage;
	}

	public List<AppExam> getExamList() {
		return examList;
	}

	public void setExamList(List<AppExam> examList) {
		this.examList = examList;
	}


}