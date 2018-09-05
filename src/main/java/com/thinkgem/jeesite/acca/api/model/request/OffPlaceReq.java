package com.thinkgem.jeesite.acca.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

@ApiModel("OffPlaceReq")
public class OffPlaceReq extends BaseRequest {

	private static final long serialVersionUID = 7143937094762391903L;
	
	@ApiModelProperty(value = "考点对应的考试ID集合")
	private Long[] examCourseIds;
	
	@ApiModelProperty(value = "所选考试年月")
	private String examStartTimeStr;
	
	@ApiModelProperty(value = "所选考试类型")
	private Integer examType;

	public String getExamStartTimeStr() {
		return examStartTimeStr;
	}

	public void setExamStartTimeStr(String examStartTimeStr) {
		this.examStartTimeStr = examStartTimeStr;
	}

	public Long[] getExamCourseIds() {
		return examCourseIds;
	}

	public void setExamCourseIds(Long[] examCourseIds) {
		this.examCourseIds = examCourseIds;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

}
