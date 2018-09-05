/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import com.thinkgem.jeesite.freetek.util.TextUtils;

/**
 * AppArticleEntity
 * @author Ivan
 * @version 2016-08-10
 */
public class AppArticle extends DataEntity<AppArticle> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "articleId文章id")
	private Long articleId;		// article_id
	@ApiModelProperty(value = "文章类别：1有资有料html文章，2公开课，3学习达人分享html文章，4名师html文章")
	private Integer type;		// 文章类别：1有资有料html文章，2公开课，3学习达人分享html文章，4名师html文章
	@ApiModelProperty(value = "文章标题")
	private String title;		// 文章标题
	@JsonIgnore
	private Integer sortNum;		// 排序好，越小越靠前
	@JsonIgnore
	private Integer publish;		// 是否立即发布：1立即发布，2暂不发布
	@ApiModelProperty(value = "文章页面标题")
	private String pageTitle;		// 页面标题
	@ApiModelProperty(value = "文章详情url")
	private String linkUrl;		// 文章显示url
	@ApiModelProperty(value = "文章分类id")
	private Long articleCategoryId;		// 所属文章分类id
	@ApiModelProperty(value = "文章分类名称")
	private String articleCategoryName;
	
	@ApiModelProperty(value = "文章封面图片id")
	private Long htmlImageId;		// 图片id
	@JsonIgnore
	private String htmlContent;		// html文章富文本内容
	@ApiModelProperty(value = "公开课授课老师")
	private String courseTeacher;		// 讲师
	@ApiModelProperty(value = "公开课授课老师职称")
	private String courseTeacherPosition;
	@ApiModelProperty(value = "公开课授课地址")
	private String courseAddress;		// 授课地址
	@ApiModelProperty(value = "公开课开始时间")
	private Date courseStartTime;		// 授课开始时间
	@ApiModelProperty(value = "公开课结束时间")
	private Date courseEndTime;		// 授课结束时间
	@ApiModelProperty(value = "公开课开始-结束时间：格式：2016-08-20 16:00-18:00")
	private String showCourseStartEndTimeStr;
	
	@ApiModelProperty(value = "公开课价格")
	private Double coursePrice;		// 价格
	@ApiModelProperty(value = "公开课形式：1在线直播，2地面讲座，3录播视频")
	private Integer courseType;		// 课程形式：1在线课程，2地面讲座
	@ApiModelProperty(value = "公开课链接地址")
	private String courseLinkUrl;		// 在线课程链接地址
	@ApiModelProperty(value = "公开课图片id")
	private Long courseImageId;		// 公开课图片id
	@JsonIgnore
	private String courseContent;		// 公开课富文本内容
	@JsonIgnore
	private Long learnShareUserId;		// 学习达人分享手机号
	@JsonIgnore
	private Long teacherId;		// 名师id
	@ApiModelProperty(value = "文章浏览量")
	private Long viewNum;		// 浏览量

	
	@ApiModelProperty(value = "有资有料文章列表封面图片")
	private FileInfo htmlImage;
	@ApiModelProperty(value = "公开课顶部图片")
	private FileInfo courseImage;
	
	@ApiModelProperty(value = "公开课直播状态：1未开始，2正在直播，3已结束")
	private Integer courseStatus;
	
	@JsonIgnore
	private AppArticleCategory articleCategory;//所属的分类
	
	@ApiModelProperty(value = "学习达人资料信息，客户端只取其中的nickname字段即可")
	private AppAccaUser learningUser;
	
	@ApiModelProperty(value = "名师信息")
	private AppTeacher teacher;
	
	@ApiModelProperty(value = "文章浏览量-字符串类型")
	private String viewNumStr;
	@ApiModelProperty(value = "文章点赞数量-字符串类型")
	private String praiseNumStr;
	@ApiModelProperty(value = "文章浏览量")
	private Long praiseNum;
	@ApiModelProperty(value = "文章预览，用于分享时的分享描述")
	private String htmlPreview;
	@ApiModelProperty(value = "文章评论数")
	private Long commentNum;
	@ApiModelProperty(value = "文章评论数量-字符串类型")
	private String commentNumStr;
	
	
	public AppArticle() {
		super();
	}

	public AppArticle(String id){
		super(id);
	}

	@NotNull(message="article_id不能为空")
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
		if(StringUtils.isEmpty(this.pageTitle)){
			this.pageTitle = this.title;
		}
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
	
	public Double getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(Double coursePrice) {
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
	
	@NotNull(message="浏览量不能为空")
	public Long getViewNum() {
		return viewNum;
	}

	public void setViewNum(Long viewNum) {
		this.viewNum = viewNum;
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

	public AppArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(AppArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}

	public AppAccaUser getLearningUser() {
		return learningUser;
	}

	public void setLearningUser(AppAccaUser learningUser) {
		this.learningUser = learningUser;
	}

	public AppTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(AppTeacher teacher) {
		this.teacher = teacher;
	}

	public String getShowCourseStartEndTimeStr() {
		if(this.courseStartTime!=null && this.courseEndTime!=null){
			String startStr = DateTimeUtils.convertDate2String(this.courseStartTime, "yyyy-MM-dd HH:mm");
			String endStr = DateTimeUtils.convertDate2String(this.courseEndTime, "-HH:mm");
			this.showCourseStartEndTimeStr = startStr+endStr;
		}
		
		return showCourseStartEndTimeStr;
	}

	public void setShowCourseStartEndTimeStr(String showCourseStartEndTimeStr) {
		this.showCourseStartEndTimeStr = showCourseStartEndTimeStr;
	}

	public Integer getCourseStatus() {
		if(this.courseStartTime==null || this.courseEndTime==null){
			this.courseStatus=Constants.ArticleCourseLiveStatus.unstart;
			return this.courseStatus;
		}
		Date date = new Date();
		if(date.getTime()<this.getCourseStartTime().getTime()){
			this.courseStatus=Constants.ArticleCourseLiveStatus.unstart;
			return this.courseStatus;
		}
		if(date.getTime()>this.getCourseEndTime().getTime()){
			this.courseStatus=Constants.ArticleCourseLiveStatus.closed;
			return this.courseStatus;
		}
		this.courseStatus=Constants.ArticleCourseLiveStatus.living;
		
		return this.courseStatus;
	}

	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}

	public String getArticleCategoryName() {
		if(this.articleCategory!=null){
			this.articleCategoryName = this.articleCategory.getName();
		}
		return articleCategoryName;
	}

	public void setArticleCategoryName(String articleCategoryName) {
		this.articleCategoryName = articleCategoryName;
	}

	public String getCourseTeacherPosition() {
		return courseTeacherPosition;
	}

	public void setCourseTeacherPosition(String courseTeacherPosition) {
		this.courseTeacherPosition = courseTeacherPosition;
	}

	public String getViewNumStr() {
		this.viewNumStr = TextUtils.formatViewNum(this.viewNum);
		
		return viewNumStr;
	}

	public void setViewNumStr(String viewNumStr) {
		this.viewNumStr = viewNumStr;
	}

	public String getPraiseNumStr() {
		this.praiseNumStr = TextUtils.formatViewNum(this.praiseNum);
		
		return praiseNumStr;
	}

	public void setPraiseNumStr(String praiseNumStr) {
		this.praiseNumStr = praiseNumStr;
	}

	public Long getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Long praiseNum) {
		this.praiseNum = praiseNum;
	}

	public String getHtmlPreview() {
		if(this.type==Constants.ArticleType.course){
			this.htmlPreview=TextUtils.getContentPreview(this.courseContent, 50);
		}else{
			this.htmlPreview=TextUtils.getContentPreview(this.htmlContent, 50);
		}
		return htmlPreview;
	}

	public void setHtmlPreview(String htmlPreview) {
		this.htmlPreview = htmlPreview;
	}

	public Long getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}

	public String getCommentNumStr() {
		this.commentNumStr = TextUtils.formatViewNum(this.commentNum);
		return commentNumStr;
	}

	public void setCommentNumStr(String commentNumStr) {
		this.commentNumStr = commentNumStr;
	}
	
}