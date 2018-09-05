/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.register.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 开通城市地区Entity
 * @author Young
 * @version 2016-08-09
 */
public class AppArea extends DataEntity<AppArea> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "城市名称")
	private String areaName;		// 栏目名
	@JsonIgnore
	private Integer parent;		// 父栏目
	@JsonIgnore
	private String shortName;		// short_name
	@JsonIgnore
	private Integer areaCode;		// area_code
	@JsonIgnore
	private Integer zipCode;		// zip_code
	@JsonIgnore
	private String pinyin;		// pinyin
	@JsonIgnore
	private BigDecimal lng;		// lng
	@JsonIgnore
	private BigDecimal lat;		// lat
	@JsonIgnore
	private Integer level;		// level
	@JsonIgnore
	private String position;		// position
	@JsonIgnore
	private Integer sortNum;		// 排序
	
	public AppArea() {
		super();
	}

	public AppArea(String id){
		super(id);
	}

	@Length(min=1, max=50, message="栏目名长度必须介于 1 和 50 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@NotNull(message="父栏目不能为空")
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=50, message="short_name长度必须介于 0 和 50 之间")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}
	
	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=100, message="pinyin长度必须介于 0 和 100 之间")
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	
	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	
	@NotNull(message="level不能为空")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Length(min=1, max=255, message="position长度必须介于 1 和 255 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
}