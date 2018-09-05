package com.thinkgem.jeesite.acca.api.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialOrder;
import com.thinkgem.jeesite.acca.api.model.request.GetOfficialOrderInfoReq;
import com.thinkgem.jeesite.acca.api.model.response.GetOfficialOrderInfoResp;
import com.thinkgem.jeesite.acca.api.order.dao.AppOffiOrderDao;
import com.thinkgem.jeesite.acca.api.order.entity.SmallDetailOrder;
import com.thinkgem.jeesite.acca.api.order.entity.SmallOrder;
import com.thinkgem.jeesite.acca.api.order.entity.SmallSignup;
import com.thinkgem.jeesite.acca.api.user.dao.AppConfExamTipsDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppConfExamTips;

@Service
@Transactional(readOnly = true)
public class AppOffiOrderService {
	
	@Autowired
	private AppOffiOrderDao appOffiOrderDao;
	
	@Autowired
	private AppConfExamTipsDao appConfExamTipsDao;

	public List<SmallOrder> getOrderListByStatis(AppOfficialOrder appOfficialOrder){
		return appOffiOrderDao.getOrderListByStatis(appOfficialOrder);
	}
	
	public SmallDetailOrder getOfficialOrderInfo(AppOfficialOrder appOfficialOrder){
		return appOffiOrderDao.getOfficialOrderInfo(appOfficialOrder);
	}

	/*
	 * 2016-09-21
	 * Ivan新增
	 */
	public GetOfficialOrderInfoResp getOfficialOrderInfo(GetOfficialOrderInfoReq req) {
		
		SmallDetailOrder order = this.getOfficialOrderInfo(new AppOfficialOrder(req));
		SmallSignup signup = order.getSignupList().get(0);
		int examType = signup.getExamType();
		
		
		String payConfTips ="";
		if(examType==1){
			AppConfExamTips conf = appConfExamTipsDao.get(new AppConfExamTips("4"));
	        payConfTips =conf.getOfficialExamProcess();
		}else{
			AppConfExamTips conf = appConfExamTipsDao.get(new AppConfExamTips("5"));
	        payConfTips =conf.getOfficialExamProcess();
		}
		
		
		return new GetOfficialOrderInfoResp(order,payConfTips);
	}
}
