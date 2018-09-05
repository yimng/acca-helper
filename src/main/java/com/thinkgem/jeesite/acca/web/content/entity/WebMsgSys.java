/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 系统消息Entity
 * @author Young
 * @version 2016-08-24
 */
public class WebMsgSys extends DataEntity<WebMsgSys> {
	
	private static final long serialVersionUID = 1L;
	private String msgContent;		// 消息内容
	private String msgTitle;		// 消息标题
	private Long msgId;		// msg_id
	private Integer pushType;		// 推送类型: 0 立即发送 1 定时发送
	private Date pushTime;		// 后台设置的消息推送时间
	private Integer pushStatus;		// 推送状态 0 已创建，1已发送，2发送失败
	private Integer pushPeople;		// 推送对象:0所有用户;1待考试用户
	private String examCourse;		// 带考试科目,逗号隔开的科目id字符串
	private Integer jumpType;		// 点击消息跳转方式:0:仅打开App;1:查看文章
	private Long articleId;		// 文章id
	private String courseName;   //考试名称
	
	public WebMsgSys() {
		super();
	}

	public WebMsgSys(String id){
		super(id);
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	@Length(min=0, max=256, message="消息标题长度必须介于 0 和 256 之间")
	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	
	public Integer getPushType() {
		return pushType;
	}

	public void setPushType(Integer pushType) {
		this.pushType = pushType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	
	public Integer getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	
	public Integer getPushPeople() {
		return pushPeople;
	}

	public void setPushPeople(Integer pushPeople) {
		this.pushPeople = pushPeople;
	}
	
	@Length(min=0, max=255, message="待考试科目,逗号隔开的科目id字符串长度必须介于 0 和 255 之间")
	public String getExamCourse() {
		return examCourse;
	}

	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}
	
	public Integer getJumpType() {
		return jumpType;
	}

	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getCourseName() {
		if (examCourse != null && !"".equals(examCourse)){
			String[] courseIds = examCourse.split(",");
			for (int i = 0;i < courseIds.length;i++){
				int courseId = Integer.parseInt(courseIds[i]);
				if (courseId < 10){
					courseName += "F" + courseId + "/";
				} else if (courseId == 10){
					courseName += "P1/";
				} else if (courseId == 11){
					courseName += "P2/";
				} else if (courseId == 12){
					courseName += "P3/";
				} else if (courseId == 13){
					courseName += "P4/";
				} else if (courseId == 14){
					courseName += "P5/";
				} else if (courseId == 15){
					courseName += "P6/";
				} else if (courseId == 16){
					courseName += "P7/";
				}
			}
			courseName = courseName.substring(4,courseName.length() - 1);
		}
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}