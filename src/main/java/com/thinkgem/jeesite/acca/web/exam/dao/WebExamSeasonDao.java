/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.dao;

import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSeason;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 考季版本类DAO接口
 * @author Michael
 * @version 2016-08-27
 */
@MyBatisDao
public interface WebExamSeasonDao extends CrudDao<WebExamSeason> {
	
}