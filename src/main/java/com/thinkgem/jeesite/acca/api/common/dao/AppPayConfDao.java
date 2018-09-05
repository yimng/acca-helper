/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.common.entity.AppPayConf;

/**
 * AppPayConfDAO接口
 * @author Ivan
 * @version 2016-08-22
 */
@MyBatisDao
public interface AppPayConfDao extends CrudDao<AppPayConf> {
	public List<AppPayConf> getAllPayConfList();
	
	
	public List<AppPayConf> getAllPayConfListByCityId(Integer cityId);
	
	
	public List<AppPayConf> getAllPayConfListByAccountType(Integer accountType);
}