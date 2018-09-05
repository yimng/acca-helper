/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 考试Entity
 * @author Fan
 * @version 2016-08-18
 */
public class WebExamCourse extends DataEntity<WebExamCourse> {

	private static final long serialVersionUID = 1L;
	private Long examCourseId;		// exam_course_id
	private String course;		// 科目
	private String courseName;		// 科目名称
	private String englishName;		// 英文名称
	private String englishShortName;		// 英文缩写
	private String price;		// 报名费
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考，如果同时支持多个，则采用位运算&ldquo;或&rdquo;进行，比如，取值为3，则支持自有考试和官方机考
	private List<WebExamVersion> versionList;	//考试版本信息

	private Long accaUserId;

	public WebExamCourse() {
		super();
	}

	public WebExamCourse(String id){
		super(id);
	}

	private boolean flag1;
	private boolean flag2;
	private boolean flag3;

	public boolean getFlag1() {
		if (examType == null) {
			return false;
		}
		if((examType&1)==1){
			this.flag1 = true;
		}else{
			this.flag1=false;
		}
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}
	public boolean getFlag2() {
		if (examType == null) {
			return false;
		}
		if((examType&2)==2){
			this.flag2 = true;
		}else{
			this.flag2=false;
		}
		return flag2;
	}

	public void setFlag2(boolean flag2) {
		this.flag2 = flag2;
	}

	public boolean getFlag3() {
		if (examType == null) {
			return false;
		}
		if((examType&4)==4){
			this.flag3 = true;
		}else{
			this.flag3=false;
		}
		return flag3;
	}

	public void setFlag3(boolean flag3) {
		this.flag3 = flag3;
	}

	@NotNull(message="exam_course_id不能为空")
	public Long getExamCourseId() {
		return examCourseId;
	}

	public void setExamCourseId(Long examCourseId) {
		this.examCourseId = examCourseId;
	}

	@Length(min=0, max=64, message="科目长度必须介于 0 和 64 之间")
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Length(min=0, max=64, message="科目名称长度必须介于 0 和 64 之间")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Length(min=0, max=64, message="英文名称长度必须介于 0 和 64 之间")
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Length(min=0, max=64, message="英文缩写长度必须介于 0 和 64 之间")
	public String getEnglishShortName() {
		return englishShortName;
	}

	public void setEnglishShortName(String englishShortName) {
		this.englishShortName = englishShortName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}


	public List<WebExamVersion> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<WebExamVersion> versionList) {
		this.versionList = versionList;
	}
}