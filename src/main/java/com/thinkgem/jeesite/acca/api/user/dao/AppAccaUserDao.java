/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;

/**
 * AppAccaUserDAO接口
 * @author Ivan
 * @version 2016-08-09
 */
@MyBatisDao
public interface AppAccaUserDao extends CrudDao<AppAccaUser> {

	AppAccaUser getAccaUserByPhone(String phone);
	
	public void updateRegister(AppAccaUser user);
	
	public Long updateAuthor(AppAccaUser user);
	
}