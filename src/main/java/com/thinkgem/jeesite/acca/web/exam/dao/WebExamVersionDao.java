/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.dao;

import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamVersion;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 考试科目版本DAO接口
 * @author Young
 * @version 2016-08-26
 */
@MyBatisDao
public interface WebExamVersionDao extends CrudDao<WebExamVersion> {

	void findauthorList(WebExamCourse webExamCourse);
	
}