/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.common.entity.AppConfHelp;

/**
 * AppConfHelpDAO接口
 * @author Ivan
 * @version 2016-08-24
 */
@MyBatisDao
public interface AppConfHelpDao extends CrudDao<AppConfHelp> {
	
	public AppConfHelp getByType(Integer type);
	
}