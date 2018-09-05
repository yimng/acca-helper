/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考试科目关联Entity
 * @author Young
 * @version 2016-08-26
 */
public class WebExamCourseVersion extends DataEntity<WebExamCourseVersion> {
	
	private static final long serialVersionUID = 1L;
	private Long examCourseId;		// exam_course_id
	private Long examVersionId;		// exam_version_id
	
	public WebExamCourseVersion() {
		super();
	}

	public WebExamCourseVersion(String id){
		super(id);
	}

	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}
	
	public Long getExamVersionId() {
		return examVersionId;
	}

	public void setExamVersionId(Long examVersionId) {
		this.examVersionId = examVersionId;
	}
	
}