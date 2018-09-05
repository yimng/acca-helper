package com.thinkgem.jeesite.acca.api.exam.entity;

import java.util.List;

public class Appointment {
	
	private Long examPlaceId;
	
	/*private String examStartTimeStr;
	private AppExamVersion examVersion;
	private Long courseId;*/
	
	private List<SmallCourseInfo> courseInfos;
	
	public Long getExamPlaceId() {
		return examPlaceId;
	}

	public void setExamPlaceId(Long examPlaceId) {
		this.examPlaceId = examPlaceId;
	}

	public List<SmallCourseInfo> getCourseInfos() {
		return courseInfos;
	}

	public void setCourseInfos(List<SmallCourseInfo> courseInfos) {
		this.courseInfos = courseInfos;
	}

}
