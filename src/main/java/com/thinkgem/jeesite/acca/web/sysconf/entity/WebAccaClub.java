/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * WebAccaClub模块Entity
 * @author Ivan
 * @version 2016-08-23
 */
public class WebAccaClub extends DataEntity<WebAccaClub> {
	
	private static final long serialVersionUID = 1L;
	private Long accaClubId;		// acca_club_id
	private String name;		// qq群名称
	private String qqNo;		// qq群号
	
	public WebAccaClub() {
		super();
	}

	public WebAccaClub(String id){
		super(id);
	}

	public Long getAccaClubId() {
		return accaClubId;
	}

	public void setAccaClubId(Long accaClubId) {
		this.accaClubId = accaClubId;
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
	
}