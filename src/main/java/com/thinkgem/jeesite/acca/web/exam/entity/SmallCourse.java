package com.thinkgem.jeesite.acca.web.exam.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;

public class SmallCourse extends DataEntity<SmallCourse> {

	private static final long serialVersionUID = -6116576043315029332L;
	
	private Long examCourseId;
	
	private String course;
	
	private String examVersionJson;
	
	private Long examId;
	
	private Integer examType;
	
	private Date examStartTime;		// 考试开始时间
	
	private Date examEndTime;		// 考试结束时间
	
	private Date examSignupEndtime;		// 报名截止时间
	
	private WebExamVersion examVersion;
	
	private List<WebExamVersion> examVersions;
	
	private boolean checked = false;

	private String prePrice;
	private String normalPrice;
	private String postPrice;

	@Override
	public boolean equals(Object obj) {
		if(obj != null){
			SmallCourse sc = (SmallCourse) obj;
			if(this.examCourseId.equals(sc.getExamCourseId())){
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}

	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getExamVersionJson() {
		return examVersionJson;
	}

	@SuppressWarnings("unchecked")
	public void setExamVersionJson(String examVersionJson) {
		if(examVersionJson != null){
			List<Map<String, Object>> maps = (List<Map<String, Object>>) JsonMapper.fromJsonString(examVersionJson, List.class);
			List<WebExamVersion> list = new ArrayList<WebExamVersion>();
			for(Map<String, Object> map : maps){
				list.add(new WebExamVersion(((Integer)map.get("examVersionId")).longValue(), (String)map.get("examVersionName")));
			}
			this.examVersions = list;
		}
		this.examVersionJson = examVersionJson;
	}

	public WebExamVersion getExamVersion() {
		return examVersion;
	}

	public void setExamVersion(WebExamVersion examVersion) {
		this.examVersion = examVersion;
	}

	public List<WebExamVersion> getExamVersions() {
		return examVersions;
	}

	public void setExamVersions(List<WebExamVersion> examVersions) {
		this.examVersions = examVersions;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
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

	public Date getExamSignupEndtime() {
		return examSignupEndtime;
	}

	public void setExamSignupEndtime(Date examSignupEndtime) {
		this.examSignupEndtime = examSignupEndtime;
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
}