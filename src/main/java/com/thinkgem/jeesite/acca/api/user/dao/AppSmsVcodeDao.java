/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.dao;

import com.thinkgem.jeesite.acca.api.user.entity.AppSmsVcode;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 用户相关接口DAO接口
 * @author lookjun
 * @version 2016-07-25
 */
@MyBatisDao
public interface AppSmsVcodeDao extends CrudDao<AppSmsVcode> {
	
	public AppSmsVcode getByMobileAndVcode(AppSmsVcode smsVcode);

	public AppSmsVcode getByMobile(String mobile);
	
}