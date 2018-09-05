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
public class AppExamSelfPlaceTiny extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "考点id")
	private Long examPlaceId;		// exam_id

	@ApiModelProperty(value = "考点名称")
	private String examPlaceName;
	
	@ApiModelProperty(value = "考点详细地址")
	private String examDetailAddress;
	@ApiModelProperty(value = "考点编号")
	private String examPlaceSn;
	
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
	
	
}