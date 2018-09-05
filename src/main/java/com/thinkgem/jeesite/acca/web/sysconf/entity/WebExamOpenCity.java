/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 城市管理Entity
 * @author Fan
 * @version 2016-08-24
 */
public class WebExamOpenCity extends DataEntity<WebExamOpenCity> {
	
	private static final long serialVersionUID = 1L;
	private Integer cityId;		// 城市id
	private Integer examType;		// 支持的考试类型：1自有考试，2官方机考，4官方笔考,8中博诚通分部，如果同时支持多个，则采用位运算&ldquo;与&rdquo;进行，比如，取值为3，则支持自有考试和官方机考
	
	private String cityName;
	private int provinceId;
	private String provinceName;

	//系统用户id,分部管理员
	private String sysUserId;
	
	private boolean flag1;
	private boolean flag2;
	private boolean flag3;
	private boolean flag4;
	
	public boolean getFlag1() {
		if (examType == null) {
			return false;
		}
		if((examType&1)==1){
			this.flag1 = true;
		}else{
			this.flag1=false;
		}
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}
	public boolean getFlag2() {
		if (examType == null) {
			return false;
		}
		if((examType&2)==2){
			this.flag2 = true;
		}else{
			this.flag2=false;
		}
		return flag2;
	}

	public void setFlag2(boolean flag2) {
		this.flag2 = flag2;
	}

	public boolean getFlag3() {
		if (examType == null) {
			return false;
		}
		if((examType&4)==4){
			this.flag3 = true;
		}else{
			this.flag3=false;
		}
		return flag3;
	}

	public void setFlag3(boolean flag3) {
		this.flag3 = flag3;
	}
	
	public boolean getFlag4() {
		if (examType == null) {
			return false;
		}
		if((examType&8)==8){
			this.flag4 = true;
		}else{
			this.flag4=false;
		}
		return flag4;
	}

	public void setFlag4(boolean flag4) {
		this.flag4 = flag4;
	}
	
	public WebExamOpenCity() {
		super();
	}

	public WebExamOpenCity(String id){
		super(id);
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Integer getExamType() {
		return examType;
	}

	public void setExamType(Integer examType) {
		this.examType = examType;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
}