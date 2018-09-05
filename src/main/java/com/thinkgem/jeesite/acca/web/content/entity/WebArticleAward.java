/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;

/**
 * WebArticleAwardEntity
 * @version 2016-09-08
 */
public class WebArticleAward extends DataEntity<WebArticleAward> {
	
	private static final long serialVersionUID = 1L;	// article_comment_id
	private Long awardId;				// 奖品id
	private Long activityId;
	private Long accaUserId;			// 用户id
	private String content;				// 奖项内容
	private Long awardCategoryId; 
	private String awardCategoryName;
	private String createDateShowStr;
	private String cardRuleId;
	private long awardStatus; 			//奖项状态10：可使用 20：已使用 30：已过期
	private AppAccaUser user;
	
	public long getAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(long awardStatus) {
		this.awardStatus = awardStatus;
	}

	public String getCreateDateShowStr() {
		this.createDateShowStr = DateTimeUtils.convertDate2String(super.createDate, "MM-dd  HH:mm");
		return createDateShowStr;
	}

	public void setCreateDateShowStr(String createDateShowStr) {
		this.createDateShowStr = createDateShowStr;
	}

	public Long getAwardCategoryId() {
		return awardCategoryId;
	}

	public void setAwardCategoryId(Long awardCategoryId) {
		this.awardCategoryId = awardCategoryId;
	}

	public String getAwardCategoryName() {
		return awardCategoryName;
	}

	public void setAwardCategoryName(String awardCategoryName) {
		this.awardCategoryName = awardCategoryName;
	}

	public String getCardRuleId() {
		return cardRuleId;
	}

	public void setCardRuleId(String string) {
		this.cardRuleId = string;
	}


	public WebArticleAward() {
		super();
	}

	public WebArticleAward(String id){
		super(id);
	}

	public Long getAwardId() {
		return awardId;
	}

	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getAccaUserId() {
		return accaUserId;
	}

	public void setAccaUserId(Long accaUserId) {
		this.accaUserId = accaUserId;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public AppAccaUser getUser() {
		return user;
	}

	public void setUser(AppAccaUser user) {
		this.user = user;
	}
	
}