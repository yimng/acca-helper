package com.thinkgem.jeesite.acca.api.plan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 学习计划Entity
 * @author Young
 * @version 2016-08-10
 */
public class AppUserLearningPlan extends DataEntity<AppUserLearningPlan> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "学习计划id")
	private Long learningPlanId;		// learning_plan_id
	@JsonIgnore
	private Long accaUserId;		// 注册用户id
	@JsonIgnore
	private Long courseId;		// 科目id
	@ApiModelProperty(value = "科目名称")
	private String courseName;		// 冗余字段，科目名称
	@ApiModelProperty(value = "开始学习时间")
	private Date startDate;		// 开始学习时间
	@ApiModelProperty(value = "学习结束时间")
	private Date endDate;		// 学习结束时间
	@ApiModelProperty(value = "考试科目的颜色")
	private String courseColor;		// 颜色

	public AppUserLearningPlan() {
		super();
	}

	public AppUserLearningPlan(String id){
		super(id);
	}

	@NotNull(message="learning_plan_id不能为空")
	public Long getLearningPlanId() {
		return learningPlanId;
	}

	public void setLearningPlanId(Long learningPlanId) {
		this.learningPlanId = learningPlanId;
	}
	
	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	@Length(min=0, max=64, message="冗余字段，科目名称长度必须介于 0 和 64 之间")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 考试的颜色
	 * @return
	 */
	public String getCourseColor() {
		courseColor = RespConstants.courseColorMap.get(courseName);
		return courseColor;
	}
	
}