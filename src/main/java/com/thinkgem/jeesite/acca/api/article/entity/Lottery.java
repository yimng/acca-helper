package com.thinkgem.jeesite.acca.api.article.entity;

import java.io.Serializable;

/**
 * 微信用户分享中奖基础数据类
 * 
 * @author yanst 2016/4/23 9:36
 */
public class Lottery implements Serializable {
	private static final long serialVersionUID = -1595371216905016135L;

	private Long id;
	private Integer num; //奖项数量
	private String name; //奖项名称
	private Integer v;	 //中奖率
	private Long activityId;		 //活动id
	private Long awardCategoryId;	 //分类id
	private String awardCategoryName;	 //分类名称		
	private String cardRuleId;			//邦定规则id
	
	public Integer getNum() {
		return num;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
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

	public void setCardRuleId(String cardRuleId) {
		this.cardRuleId = cardRuleId;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lottery( Long id,Integer num, String name, Integer v, Long activityId, Long awardCategoryId, String awardCategoryName, String cardRuleId) {
		this.id=id;
		this.num=num;//奖项数量
		this.name=name;//奖项名称
		this.v=v;	//中奖率
		this.activityId=activityId;		//活动id
		this.awardCategoryId=awardCategoryId;	//分类id
		this.awardCategoryName=awardCategoryName;	//分类名称		
		this.cardRuleId=cardRuleId;			
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getV() {
		return v;
	}

	public void setV(Integer v) {
		this.v = v;
	}
	
}