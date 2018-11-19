/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 考季版本类Entity
 * @author Michael
 * @version 2016-08-27
 */
public class WebExamSeason extends DataEntity<WebExamSeason> {
	
	private static final long serialVersionUID = 1L;
	private Long examSeasonId;		// exam_season_id
	private Long examCourseId;		// exam_course_id
	private String examCourse;		// exam_course
	private String examVersionJson;		// exam_version_json
	private String examSeasonStr;		// 年月字符串
	private Date preSignup;
	private Date signUp;
	private Date postSignup;
	private String prePrice;
	private String normalPrice;
	private String postPrice;
	private Date examStartTime;
	private Date examEndTime;
	
	private String oldSeasonStr;
	
	private List<WebExamCourseForSeason> examCourselist;
	
	public WebExamSeason() {
		super();
	}

	public WebExamSeason(String id){
		super(id);
	}

	@NotNull(message="exam_season_id不能为空")
	public Long getExamSeasonId() {
		return examSeasonId;
	}

	public void setExamSeasonId(Long examSeasonId) {
		this.examSeasonId = examSeasonId;
	}
	
	@NotNull(message="exam_course_id不能为空")
	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}
	
	@Length(min=0, max=64, message="exam_course长度必须介于 0 和 64 之间")
	public String getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}
	
	@Length(min=0, max=255, message="exam_version_json长度必须介于 0 和 255 之间")
	public String getExamVersionJson() {
		return examVersionJson;
	}

	public void setExamVersionJson(String examVersionJson) {
		this.examVersionJson = examVersionJson;
	}
	
	@Length(min=0, max=64, message="年月字符串长度必须介于 0 和 64 之间")
	public String getExamSeasonStr() {
		return examSeasonStr;
	}

	public void setExamSeasonStr(String examSeasonStr) {
		this.examSeasonStr = examSeasonStr;
	}

	public List<WebExamCourseForSeason> getExamCourselist() {
		return examCourselist;
	}

	public void setExamCourselist(List<WebExamCourseForSeason> examCourselist) {
		this.examCourselist = examCourselist;
	}

	public String getOldSeasonStr() {
		return oldSeasonStr;
	}

	public void setOldSeasonStr(String oldSeasonStr) {
		this.oldSeasonStr = oldSeasonStr;
	}

	public Date getPreSignup() {
		return preSignup;
	}

	public void setPreSignup(Date preSignup) {
		this.preSignup = preSignup;
	}

	public Date getSignUp() {
		return signUp;
	}

	public void setSignUp(Date signUp) {
		this.signUp = signUp;
	}

	public Date getPostSignup() {
		return postSignup;
	}

	public void setPostSignup(Date postSignup) {
		this.postSignup = postSignup;
	}

    public String getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(String prePrice) {
        this.prePrice = prePrice;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(String postPrice) {
        this.postPrice = postPrice;
    }

	public Date getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(Date examStartTime) {
		this.examStartTime = examStartTime;
	}

	public Date getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(Date examEndTime) {
		this.examEndTime = examEndTime;
	}
}