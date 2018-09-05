/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.plan.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 考试科目Entity
 * @author Young
 * @version 2016-08-12
 */
public class AppExamCourse extends DataEntity<AppExamCourse> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "考试课程id")
	private Long examCourseId;		// exam_course_id
	@ApiModelProperty(value = "科目")
	private String course;		// 科目
	@ApiModelProperty(value = "科目名称")
	private String courseName;		// 科目名称
	@ApiModelProperty(value = "英文名称")
	private String englishName;		// 英文名称
	@ApiModelProperty(value = "英文缩写")
	private String englishShortName;		// 英文缩写
	@ApiModelProperty(value = "报名费")
	private BigDecimal price;		// 报名费
	@ApiModelProperty(value = "支持的考试类型：1自有考试，2官方机考，4官方笔考")
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考，如果同时支持多个，则采用位运算&ldquo;或&rdquo;进行，比如，取值为3，则支持自有考试和官方机考

	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色

	public AppExamCourse() {
		super();
	}

	public AppExamCourse(String id){
		super(id);
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
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	/**
	 * 考试的颜色
	 * @return
     */
	public String getCourseColor() {
		courseColor = RespConstants.courseColorMap.get(course);
		return courseColor;
	}
}