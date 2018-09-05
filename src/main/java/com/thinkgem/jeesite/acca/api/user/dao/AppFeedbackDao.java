/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppFeedback;

/**
 * AppFeedbackDAO接口
 * @author Ivan
 * @version 2016-08-10
 */
@MyBatisDao
public interface AppFeedbackDao extends CrudDao<AppFeedback> {
	
}