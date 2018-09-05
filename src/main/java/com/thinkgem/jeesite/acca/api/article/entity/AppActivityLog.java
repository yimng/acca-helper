/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;

/**
 * AppArticleAwardEntity
 * @author Ivan
 * @version 2016-09-08
 */
public class AppActivityLog extends DataEntity<AppActivityLog> {
	
	private static final long serialVersionUID = 1L;	// article_comment_id
	private Long activityId;				// 奖品id	
	private Long accaUserId;			// 用户id
	private String createDateShowStr;		
	private AppAccaUser user;	
	
	public String getCreateDateShowStr() {
		this.createDateShowStr = DateTimeUtils.convertDate2String(super.createDate, "MM-dd  HH:mm");
		return createDateShowStr;
	}

	public void setCreateDateShowStr(String createDateShowStr) {
		this.createDateShowStr = createDateShowStr;
	}

	public AppActivityLog() {
		super();
	}

	public AppActivityLog(String id){
		super(id);
	}


	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

	public AppAccaUser getUser() {
		return user;
	}

	public void setUser(AppAccaUser user) {
		this.user = user;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	
}