/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.entity;

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * appAccaClub模块Entity
 * @author Ivan
 * @version 2016-08-09
 */
public class AppAccaClub extends DataEntity<AppAccaClub> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "qq群名称")
	private String name;		// qq群名称
	@ApiModelProperty(value = "qq群号")
	private String qqNo;		// qq群号
	private Long accaClubId;
	
	public AppAccaClub() {
		super();
	}

	public AppAccaClub(String id){
		super(id);
	}

	@Length(min=0, max=64, message="qq群名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="qq群号长度必须介于 0 和 64 之间")
	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public Long getAccaClubId() {
		return accaClubId;
	}

	public void setAccaClubId(Long accaClubId) {
		this.accaClubId = accaClubId;
	}
	
}