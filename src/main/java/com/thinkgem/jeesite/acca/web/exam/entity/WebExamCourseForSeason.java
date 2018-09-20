/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.entity;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.model.BaseModel;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 考试Entity
 * @author Fan
 * @version 2016-08-18
 */
public class WebExamCourseForSeason extends BaseModel{

	private static final long serialVersionUID = 1L;
	private Long examCourseId;		// exam_course_id
	private String examCourse;		// 科目
	/*private String courseName;		// 科目名称
	private String englishName;		// 英文名称
	private String englishShortName;		// 英文缩写
	private String price;		// 报名费
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考，如果同时支持多个，则采用位运算&ldquo;或&rdquo;进行，比如，取值为3，则支持自有考试和官方机考*/
	private String prePrice;
	private String normalPrice;
	private String postPrice;
	
	
	private List<WebExamVersion> versionList;	//考试版本信息
	
	private String showVersionStr;

	private String examVersionJson;
	
	public WebExamCourseForSeason() {
		super();
	}

	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}

	public String getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}

	public List<WebExamVersion> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<WebExamVersion> versionList) {
		this.versionList = versionList;
	}

	public String getExamVersionJson() {
		if (StringUtils.isNotEmpty(this.examVersionJson)) {
			return this.examVersionJson;
		}
		this.examVersionJson = JsonMapper.toJsonString(this.getVersionList());
		return examVersionJson;
	}

	public void setExamVersionJson(String examVersionJson) {
		this.examVersionJson = examVersionJson;
		if(!StringUtils.isEmpty(this.examVersionJson)){
			this.versionList = JsonMapper.getInstance().fromJson(this.examVersionJson, JsonMapper.getInstance().createCollectionType(ArrayList.class, WebExamVersion.class));
			
			if(this.versionList!=null && !this.versionList.isEmpty()){
				String str = "";
				for(WebExamVersion index:this.versionList){
					str+=index.getExamVersionName();
					str+="、";
				}
				this.showVersionStr = str;
			}
		}
	}

	public String getShowVersionStr() {
		return showVersionStr;
	}

	public void setShowVersionStr(String showVersionStr) {
		this.showVersionStr = showVersionStr;
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