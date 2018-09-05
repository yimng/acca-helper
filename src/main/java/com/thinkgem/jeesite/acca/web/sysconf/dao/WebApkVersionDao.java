/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.dao;

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebApkVersion;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * WebApkVersionDAO接口
 * @author Ivan
 * @version 2016-08-23
 */
@MyBatisDao
public interface WebApkVersionDao extends CrudDao<WebApkVersion> {
	
}