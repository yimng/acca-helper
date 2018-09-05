/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.dao;

import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfRegisterFee;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 注册用户费用管理DAO接口
 * @author Fan
 * @version 2016-08-23
 */
@MyBatisDao
public interface WebConfRegisterFeeDao extends CrudDao<WebConfRegisterFee> {
	
}