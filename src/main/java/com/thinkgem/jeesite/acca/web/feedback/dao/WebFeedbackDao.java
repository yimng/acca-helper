/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.feedback.dao;

import com.thinkgem.jeesite.acca.web.feedback.entity.WebFeedback;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * WebFeedbackDAO接口
 * @author Ivan
 * @version 2016-08-20
 */
@MyBatisDao
public interface WebFeedbackDao extends CrudDao<WebFeedback> {
	
}