/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialOrder;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 官方考试订单类DAO接口
 * @author Michael
 * @version 2016-08-17
 */
@MyBatisDao
public interface AppOfficialOrderDao extends CrudDao<AppOfficialOrder> {
	
}