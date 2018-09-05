/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 文章Entity
 * @author Young
 * @version 2016-08-18
 */
public class WebArticle extends DataEntity<WebArticle> {
	
	private static final long serialVersionUID = 1L;
	private Long articleId;		// article_id
	private Integer type;		// 文章类别：1有资有料html文章，2公开课，3学习达人分享html文章，4名师html文章
	private String title;		// 文章标题
	private Integer sortNum = 255;		// 排序好，越小越靠前
	private Integer publish;		// 是否立即发布：1立即发布，2暂不发布
	private String pageTitle;		// 页面标题
	private String linkUrl;		// 文章显示url
	private Long articleCategoryId;		// 所属文章分类id
	private Long htmlImageId;		// 图片id
	private String htmlContent;		// html文章富文本内容
	private String courseTeacher;		// 讲师
	private String courseTeacherPosition;    //讲师职务
	private String courseAddress;		// 授课地址
	private Date courseStartTime;		// 授课开始时间
	private Date courseEndTime;		// 授课结束时间
	private BigDecimal coursePrice;		// 价格
	private Integer courseType;		// 课程形式：1在线课程，2地面讲座 ,3录播课程
	private String courseLinkUrl;		// 在线课程链接地址
	private Long courseImageId;		// 公开课图片id
	private String courseContent;		// 公开课富文本内容
	private Long learnShareUserId;		// 学习达人分享手机号
	private Long teacherId;		// 名师id
	private Long viewNum;		// 浏览量
	//文章分类
	private String categoryName;
	//文章分类类型
	private Integer categoryType;
	//关联科目列表
	private List<WebExamCourse> examCourseList;
	//关联科目名称
	private String relationCourse;
	//学习达人姓名
	private String learnShareUserName;

	//图片
	private FileInfo htmlImage;
	private String htmlImageStr;
	public String getHtmlImageStr(){
		return JsonMapper.getInstance().toJson(this.htmlImage);
	}
	private FileInfo courseImage;
	private String courseImageStr;
	public String getCourseImageStr(){
		return JsonMapper.getInstance().toJson(this.courseImage);
	}
	
	public WebArticle() {
		super();
	}

	public WebArticle(String id){
		super(id);
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="文章标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Min(value = 1 ,message = "请输入1-255之间的数字")
	@Max(value = 255 ,message = "请输入1-255之间的数字")
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@NotNull(message="是否立即发布：1立即发布，2暂不发布不能为空")
	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}
	
	@Length(min=0, max=200, message="页面标题长度必须介于 0 和 200 之间")
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	@Length(min=0, max=200, message="文章显示url长度必须介于 0 和 200 之间")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public Long getArticleCategoryId() {
		return articleCategoryId;
	}

	public void setArticleCategoryId(Long articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	public Long getHtmlImageId() {
		return htmlImageId;
	}

	public void setHtmlImageId(Long htmlImageId) {
		this.htmlImageId = htmlImageId;
	}
	
	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
	@Length(min=0, max=200, message="讲师长度必须介于 0 和 200 之间")
	public String getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(String courseTeacher) {
		this.courseTeacher = courseTeacher;
	}
	
	@Length(min=0, max=200, message="授课地址长度必须介于 0 和 200 之间")
	public String getCourseAddress() {
		return courseAddress;
	}

	public void setCourseAddress(String courseAddress) {
		this.courseAddress = courseAddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCourseStartTime() {
		return courseStartTime;
	}

	public void setCourseStartTime(Date courseStartTime) {
		this.courseStartTime = courseStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCourseEndTime() {
		return courseEndTime;
	}

	public void setCourseEndTime(Date courseEndTime) {
		this.courseEndTime = courseEndTime;
	}
	
	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}
	
	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	
	@Length(min=0, max=500, message="在线课程链接地址长度必须介于 0 和 500 之间")
	public String getCourseLinkUrl() {
		return courseLinkUrl;
	}

	public void setCourseLinkUrl(String courseLinkUrl) {
		this.courseLinkUrl = courseLinkUrl;
	}
	
	public Long getCourseImageId() {
		return courseImageId;
	}

	public void setCourseImageId(Long courseImageId) {
		this.courseImageId = courseImageId;
	}
	
	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	@Min(value = 1 ,message = "请选择学习达人")
	public Long getLearnShareUserId() {
		return learnShareUserId;
	}

	public void setLearnShareUserId(Long learnShareUserId) {
		this.learnShareUserId = learnShareUserId;
	}
	
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	
	public Long getViewNum() {
		return viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<WebExamCourse> getExamCourseList() {
		return examCourseList;
	}

	public void setExamCourseList(List<WebExamCourse> examCourseList) {
		this.examCourseList = examCourseList;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public FileInfo getHtmlImage() {
		return htmlImage;
	}

	public void setHtmlImage(FileInfo htmlImage) {
		this.htmlImage = htmlImage;
	}

	public FileInfo getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(FileInfo courseImage) {
		this.courseImage = courseImage;
	}

	public String getRelationCourse() {
		return relationCourse;
	}

	public void setRelationCourse(String relationCourse) {
		this.relationCourse = relationCourse;
	}

	@Length(min=0, max=32, message="讲师职务长度必须介于 0 和 32 之间")
	public String getCourseTeacherPosition() {
		return courseTeacherPosition;
	}

	public void setCourseTeacherPosition(String courseTeacherPosition) {
		this.courseTeacherPosition = courseTeacherPosition;
	}

	public void setHtmlImageStr(String htmlImageStr) {
		this.htmlImageStr = htmlImageStr;
	}

	public void setCourseImageStr(String courseImageStr) {
		this.courseImageStr = courseImageStr;
	}

	public String getLearnShareUserName() {
		return learnShareUserName;
	}

	public void setLearnShareUserName(String learnShareUserName) {
		this.learnShareUserName = learnShareUserName;
	}
}