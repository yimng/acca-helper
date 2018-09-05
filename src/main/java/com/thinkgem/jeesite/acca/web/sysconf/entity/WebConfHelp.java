/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * WebConfHelpEntity
 * @author Ivan
 * @version 2016-08-24
 */
public class WebConfHelp extends DataEntity<WebConfHelp> {
	
	private static final long serialVersionUID = 1L;
	private Integer type;		// 类型：1.注册acca的条件，2.什么是fia，3.ACCA官方授权机考中心免责声明，4.代报考免责协议及注意事项
	private String name;		// 名称
	private String htmlContent;		// 富文本内容
	
	public WebConfHelp() {
		super();
	}

	public WebConfHelp(String id){
		super(id);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
}