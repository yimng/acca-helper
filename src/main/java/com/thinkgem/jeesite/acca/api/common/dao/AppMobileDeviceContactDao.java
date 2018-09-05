/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.common.entity.AppMobileDeviceContact;

/**
 * AppMobileDeviceContactDAO接口
 * @author Ivan
 * @version 2016-08-10
 */
@MyBatisDao
public interface AppMobileDeviceContactDao extends CrudDao<AppMobileDeviceContact> {
	
	
	public void insertBatch(List<AppMobileDeviceContact> list);
	
	public void deleteBatchByDeviceId(String deviceId);
	
}