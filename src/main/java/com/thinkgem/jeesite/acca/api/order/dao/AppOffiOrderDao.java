package com.thinkgem.jeesite.acca.api.order.dao;

import java.util.List;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialOrder;
import com.thinkgem.jeesite.acca.api.order.entity.SmallDetailOrder;
import com.thinkgem.jeesite.acca.api.order.entity.SmallOrder;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface AppOffiOrderDao {

	List<SmallOrder> getOrderListByStatis(AppOfficialOrder appOfficialOrder);
	
	SmallDetailOrder getOfficialOrderInfo(AppOfficialOrder appOfficialOrder);
}
