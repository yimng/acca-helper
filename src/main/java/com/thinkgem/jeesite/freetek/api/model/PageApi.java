/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.freetek.api.model;

import com.thinkgem.jeesite.common.config.Global;

import io.swagger.annotations.ApiModelProperty;

/**
 * api接口部分分页类
 * @author Ivan
 * @version 2013-7-2
 */
public class PageApi {

	@ApiModelProperty(value = "分页大小",required=true)
	private int pageSize = Integer.valueOf(Global.getConfig("page.pageSize")); // 
	@ApiModelProperty(value = "分页启始位置",required=true)
	private int startIndex = 0; // 分页启始位置，从0开始
	
	public PageApi() {
		super();
	}
	
	public PageApi(int pageSize, int startIndex) {
		super();
		this.pageSize = pageSize;
		this.startIndex = startIndex;
	}
	
	

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	

	
	
}
