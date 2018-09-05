package com.thinkgem.jeesite.acca.api.exam.entity;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import io.swagger.annotations.ApiModelProperty;

public class SmallCourseInfo {

	private Long courseId;
	
	private AppExamVersion examVersion;

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null){
			SmallCourseInfo info = (SmallCourseInfo) obj;
			if(info.getCourseId().equals(this.courseId)){
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}

	public AppExamVersion getExamVersion() {
		return examVersion;
	}

	public void setExamVersion(AppExamVersion examVersion) {
		this.examVersion = examVersion;
	}

	/**
	 * 考试的颜色
	 * @return
	 */
	public String getCourseColor() {
		courseColor = RespConstants.courseIdColorMap.get(courseId.intValue());
		return courseColor;
	}
	
}
