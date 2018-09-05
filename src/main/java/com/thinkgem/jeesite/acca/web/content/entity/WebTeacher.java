/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.entity;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import org.hibernate.validator.constraints.Length;

/**
 * 名师指导Entity
 * @author Young
 * @version 2016-08-22
 */
public class WebTeacher extends DataEntity<WebTeacher> {
	
	private static final long serialVersionUID = 1L;
	private Long teacherId;		// teacher_id
	private String chName;		// 姓名
	private String enName;		// 英文名
	private String phone;		// 手机号
	private Long headId;		// 头像
	private String position;		// 职务
	private Integer accaLevel;		// acca会员等级:1准acca会员，2acca会员，3fcca会员
	private String description;		// 履历及个人简介
	private FileInfo head;    //头像
	private String headStr;   //头像字符串
	
	public WebTeacher() {
		super();
	}

	public WebTeacher(String id){
		super(id);
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=200, message="姓名长度必须介于 0 和 200 之间")
	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}
	
	@Length(min=0, max=200, message="英文名长度必须介于 0 和 200 之间")
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	@Length(min=0, max=11, message="手机号长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Long getHeadId() {
		return headId;
	}

	public void setHeadId(Long headId) {
		this.headId = headId;
	}
	
	@Length(min=0, max=200, message="职务长度必须介于 0 和 200 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public Integer getAccaLevel() {
		return accaLevel;
	}

	public void setAccaLevel(Integer accaLevel) {
		this.accaLevel = accaLevel;
	}
	
	@Length(min=0, max=500, message="履历及个人简介长度必须介于 0 和 500 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FileInfo getHead() {
		return head;
	}

	public void setHead(FileInfo head) {
		this.head = head;
	}

	public String getHeadStr(){
		return JsonMapper.getInstance().toJson(this.head);
	}
}