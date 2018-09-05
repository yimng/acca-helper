package com.thinkgem.jeesite.acca.web.exam.entity;

import java.util.List;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;

public class WebExamCourseRelation extends DataEntity<WebExamCourseRelation> {

	private static final long serialVersionUID = -7310626825417659774L;
	
	private Long relationId;
	private Long examId;
	private Long examCourseId;
	private String examVersionJson;
	private List<WebExamVersion> examVersions;
	
	public Long getRelationId() {
		return relationId;
	}
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getExamCourseId() {
		return examCourseId;
	}
	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}
	public String getExamVersionJson() {
		return examVersionJson;
	}
	public void setExamVersionJson(String examVersionJson) {
		this.examVersionJson = examVersionJson;
	}
	public List<WebExamVersion> getExamVersions() {
		return examVersions;
	}
	public void setExamVersions(List<WebExamVersion> examVersions) {
		if(examVersions != null && examVersions.size() != 0){
			this.examVersionJson = JsonMapper.toJsonString(examVersions);
		}
		this.examVersions = examVersions;
	}

}
