/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.plan.dao;

import com.thinkgem.jeesite.acca.api.plan.entity.AppExamCourse;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 考试科目DAO接口
 * @author Young
 * @version 2016-08-12
 */
@MyBatisDao
public interface AppExamCourseDao extends CrudDao<AppExamCourse> {
	
}