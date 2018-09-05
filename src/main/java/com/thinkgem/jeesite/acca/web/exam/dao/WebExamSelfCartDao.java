/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.dao;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSelfCart;

/**
 * WebExamSelfCartDAO接口
 * @author Ivan
 * @version 2016-08-29
 */
@MyBatisDao
public interface WebExamSelfCartDao extends CrudDao<WebExamSelfCart> {
	
	public List<WebExamSelfCart> getListByExpireTime(Date expireTime);
	
	public void deleteByExpireTime(Date expireTime);
	
}