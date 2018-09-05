package com.thinkgem.jeesite.freetek.api.model;

import com.thinkgem.jeesite.freetek.api.constant.RespConstants;


/**
 * 
 * @author lookjun
 *
 * @param <T>
 */
public class BaseObjResponse<T> extends BaseResponse {
	
	private static final long serialVersionUID = 14698503181606304L;
	
	private T obj;
	
	public BaseObjResponse() {
	}
	
	public BaseObjResponse(Integer respCode) {
		super(respCode);
	}
	
	public BaseObjResponse(Integer respCode, String respDesc) {
		super(respCode, respDesc);
	}
	
	public BaseObjResponse(T obj) {
		super(RespConstants.GLOBAL_SUCCESS);
		this.obj = obj;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
}
