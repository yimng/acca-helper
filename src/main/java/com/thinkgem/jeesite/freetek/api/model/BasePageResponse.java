package com.thinkgem.jeesite.freetek.api.model;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;

public class BasePageResponse<T> extends BaseResponse {
	
	private static final long serialVersionUID = 14698503181606304L;
	
	private List<T> list = new ArrayList<T>();

	
	public BasePageResponse() {
	}
	
	public BasePageResponse(Integer respCode) {
		super(respCode);
	}
	
	public BasePageResponse(Integer respCode, String respDesc) {
		super(respCode, respDesc);
	}
	
	public BasePageResponse(List<T> list) {
		super(RespConstants.GLOBAL_SUCCESS);
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
