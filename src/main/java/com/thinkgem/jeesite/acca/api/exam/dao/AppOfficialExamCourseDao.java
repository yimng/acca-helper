/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import java.util.List;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamCourse;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 课程类DAO接口
 * @author Michael
 * @version 2016-08-16
 */
@MyBatisDao
public interface AppOfficialExamCourseDao extends CrudDao<AppOfficialExamCourse> {
	
	List<AppOfficialExamCourse> getOfficialExamCourseList(AppOfficialExamCourse appOfficialExamCourse);
	
	AppOfficialExamCourse getOfficialExamCourse(AppOfficialExamCourse appOfficialExamCourse);
	
}