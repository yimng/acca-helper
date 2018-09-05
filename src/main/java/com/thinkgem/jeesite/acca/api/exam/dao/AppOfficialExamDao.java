/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import java.util.List;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExam;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamMonth;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 考试类DAO接口
 * @author Michael
 * @version 2016-08-15
 */
@MyBatisDao
public interface AppOfficialExamDao extends CrudDao<AppOfficialExam> {
	
	List<AppOfficialExamMonth> getExamMonth(AppOfficialExam appOfficialExam);
	
	AppOfficialExam getExamByCourseAndPlace(AppOfficialExam appOfficialExam);
	
}