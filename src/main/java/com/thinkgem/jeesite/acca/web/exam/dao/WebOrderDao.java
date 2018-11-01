package com.thinkgem.jeesite.acca.web.exam.dao;

import java.util.List;

import com.thinkgem.jeesite.acca.web.exam.entity.WebOrder;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface WebOrderDao extends CrudDao<WebOrder> {

	List<WebOrder> findOrders(WebOrder order);
	
	void updOrderStatus(WebOrder order);
	
	void updSignupOrderStatus(WebOrder orders);
	
	void updSignupStatus(WebOrder order);
	
	WebOrder findOne(WebOrder order);
	
	/* 释放考试座位，自动减1 */
	void subUsedSeats(Long examId);

	List<WebOrder> findExpireOrder(WebOrder order);


}
