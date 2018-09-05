package com.thinkgem.jeesite.acca.api.model.response;

import java.util.List;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamPlace;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamSignup;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialOrder;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

public class OrderInfoResponse extends BaseResponse {

	private static final long serialVersionUID = 1117066240627027437L;
	
	private AppOfficialOrder order;
	
	private AppOfficialExamPlace wriPlace;
	
	private AppOfficialExamPlace macPlace;
	
	private List<AppOfficialExamSignup> listWriOrders;
	
	private List<AppOfficialExamSignup> listMacOrders;
	
	private String tips;
	
	private String payConfTips;
	
	public OrderInfoResponse(){
		
	}
	
	public OrderInfoResponse(int respCode){
		super(respCode);
	}

	public AppOfficialOrder getOrder() {
		return order;
	}

	public void setOrder(AppOfficialOrder order) {
		this.order = order;
	}

	public AppOfficialExamPlace getWriPlace() {
		return wriPlace;
	}

	public void setWriPlace(AppOfficialExamPlace wriPlace) {
		this.wriPlace = wriPlace;
	}

	public AppOfficialExamPlace getMacPlace() {
		return macPlace;
	}

	public void setMacPlace(AppOfficialExamPlace macPlace) {
		this.macPlace = macPlace;
	}

	public List<AppOfficialExamSignup> getListWriOrders() {
		return listWriOrders;
	}

	public void setListWriOrders(List<AppOfficialExamSignup> listWriOrders) {
		this.listWriOrders = listWriOrders;
	}

	public List<AppOfficialExamSignup> getListMacOrders() {
		return listMacOrders;
	}

	public void setListMacOrders(List<AppOfficialExamSignup> listMacOrders) {
		this.listMacOrders = listMacOrders;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getPayConfTips() {
		return payConfTips;
	}

	public void setPayConfTips(String payConfTips) {
		this.payConfTips = payConfTips;
	}

}
