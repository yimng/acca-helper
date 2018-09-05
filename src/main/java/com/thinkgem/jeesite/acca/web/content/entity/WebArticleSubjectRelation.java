/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;


import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 文章关联科目Entity
 * @author Young
 * @version 2016-08-18
 */
public class WebArticleSubjectRelation extends DataEntity<WebArticleSubjectRelation> {
	
	private static final long serialVersionUID = 1L;
	private Long articleId;		// article_id
	private Long subjectId;		// subject_id
	private WebExamCourse course;
	//查课程表,获取名称
	//private String subjectName;
	
	public WebArticleSubjectRelation() {
		super();
	}

	public WebArticleSubjectRelation(String id){
		super(id);
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public WebExamCourse getCourse() {
		return course;
	}

	public void setCourse(WebExamCourse course) {
		this.course = course;
	}

//	public String getSubjectName() {
//		return subjectName;
//	}
//
//	public void setSubjectName(String subjectName) {
//		this.subjectName = subjectName;
//	}
}