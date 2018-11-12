/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.entity;

import com.thinkgem.jeesite.acca.api.model.request.ExamStartTimeReq;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程类Entity
 * @author Michael
 * @version 2016-08-16
 */
public class AppOfficialExamCourse extends DataEntity<AppOfficialExamCourse> {
	
	private static final long serialVersionUID = 1L;
	private String examPlaceId;
	private Long examCourseId;		// exam_course_id
	private String course;		// 科目
	private String courseName;		// 科目名称
	private String englishName;		// 英文名称
	private String englishShortName;		// 英文缩写
	private Double price;		// 报名费
	private Date preSignup;
	private Date signup;
	private Date postSignup;
	private Double prePrice;
	private Double normalPrice;
	private Double postPrice;
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考，如果同时支持多个，则采用位运算&ldquo;或&rdquo;进行，比如，取值为3，则支持自有考试和官方机考
	private String examStartTimeStr;
	private String examTime;
	
	private String examVersionJson;
	private Long examId;
	private List<AppExamVersion> versionList;
	private Date examSignupEndtime;

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色
	
	public AppOfficialExamCourse() {
		super();
	}

	public AppOfficialExamCourse(String id){
		super(id);
	}
	
	public AppOfficialExamCourse(ExamStartTimeReq req){
		this.examStartTimeStr = req.getExamStartTimeStr();
		this.examPlaceId = req.getExamPlaceId();
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
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Length(min=0, max=11, message="支持的考试类型：1自有考试，2官方机考，4官方笔考，如果同时支持多个，则采用位运算&ldquo;或&rdquo;进行，比如，取值为3，则支持自有考试和官方机考长度必须介于 0 和 11 之间")
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public String getExamStartTimeStr() {
		return examStartTimeStr;
	}

	public void setExamStartTimeStr(String examStartTimeStr) {
		this.examStartTimeStr = examStartTimeStr;
	}

	public String getExamVersionJson() {
		return examVersionJson;
	}

	@SuppressWarnings("unchecked")
	public void setExamVersionJson(String examVersionJson) {
		if(examVersionJson != null){
			versionList = (List<AppExamVersion>) JsonMapper.fromJsonString(examVersionJson, List.class);
		}
		this.examVersionJson = examVersionJson;
	}

	public List<AppExamVersion> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<AppExamVersion> versionList) {
		this.versionList = versionList;
	}
	
	public static void main(String[] args) {
		AppExamVersion se1 = new AppExamVersion();
		se1.setExamVersionId(1L);
		se1.setExamVersionName("a");
		AppExamVersion se2 = new AppExamVersion();
		se2.setExamVersionId(2L);
		se2.setExamVersionName("b");
		List<AppExamVersion> list = new ArrayList<AppExamVersion>();
		list.add(se1);
		list.add(se2);
		System.out.println(JsonMapper.toJsonString(list));
		List<AppExamVersion> o = (List<AppExamVersion>) JsonMapper.fromJsonString(JsonMapper.toJsonString(list), List.class);
		System.out.println(o);
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Date getExamSignupEndtime() {
		return examSignupEndtime;
	}

	public void setExamSignupEndtime(Date examSignupEndtime) {
		this.examSignupEndtime = examSignupEndtime;
	}

	/**
	 * 考试的颜色
	 * @return
	 */
	public String getCourseColor() {
		courseColor = RespConstants.courseColorMap.get(course);
		return courseColor;
	}

    public String getExamPlaceId() {
        return examPlaceId;
    }

    public void setExamPlaceId(String examPlaceId) {
        this.examPlaceId = examPlaceId;
    }

    public Date getPreSignup() {
		return preSignup;
	}

	public void setPreSignup(Date preSignup) {
		this.preSignup = preSignup;
	}

	public Date getSignup() {
		return signup;
	}

	public void setSignup(Date signup) {
		this.signup = signup;
	}

	public Date getPostSignup() {
		return postSignup;
	}

	public void setPostSignup(Date postSignup) {
		this.postSignup = postSignup;
	}

	public Double getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(Double prePrice) {
		this.prePrice = prePrice;
	}

	public Double getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(Double normalPrice) {
		this.normalPrice = normalPrice;
	}

	public Double getPostPrice() {
		return postPrice;
	}

	public void setPostPrice(Double postPrice) {
		this.postPrice = postPrice;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
}