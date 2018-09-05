/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.order.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.order.entity.AppOrder;

/**
 * AppOrderDAO接口
 * @author Ivan
 * @version 2016-08-19
 */
@MyBatisDao
public interface AppOrderDao extends CrudDao<AppOrder> {
	
	public int getCountByUnpayAndUncheckedAndSuccess(Long accaUserId);
	
}