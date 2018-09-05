/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;

/**
 * AppTeacherEntity
 * @author Ivan
 * @version 2016-08-12
 */
public class AppTeacher extends DataEntity<AppTeacher> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "名师id")
	private Long teacherId;		// teacher_id
	@ApiModelProperty(value = "名师中文名")
	private String chName;		// 中文名
	@ApiModelProperty(value = "名师英文名")
	private String enName;		// 英文名
	@ApiModelProperty(value = "名师手机号")
	private String phone;		// 手机号
	@ApiModelProperty(value = "名师头像id")
	private Long headId;		// 头像id
	@ApiModelProperty(value = "名师职务")
	private String position;		// 职务
	@ApiModelProperty(value = "名师acca会员等级：1准acca会员，2acca会员，3fcca会员")
	private Long accaLevel;		// acca会员等级：1准acca会员，2acca会员，3fcca会员
	@ApiModelProperty(value = "名师履历及个人简介")
	private String description;		// 履历及个人简介
	@ApiModelProperty(value = "名师头像")
	private FileInfo headImg=new FileInfo();
	
	public AppTeacher() {
		super();
	}

	public AppTeacher(String id){
		super(id);
	}

	public AppTeacher(Long teacherId) {
		this.teacherId = teacherId;
	}

	@NotNull(message="teacher_id不能为空")
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	
	@Length(min=0, max=200, message="中文名长度必须介于 0 和 200 之间")
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
	
	public Long getAccaLevel() {
		return accaLevel;
	}

	public void setAccaLevel(Long accaLevel) {
		this.accaLevel = accaLevel;
	}
	
	@Length(min=0, max=500, message="履历及个人简介长度必须介于 0 和 500 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FileInfo getHeadImg() {
		return headImg;
	}

	public void setHeadImg(FileInfo headImg) {
		this.headImg = headImg;
	}
	
}