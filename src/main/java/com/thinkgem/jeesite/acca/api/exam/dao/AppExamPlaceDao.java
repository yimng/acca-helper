/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamPlace;

/**
 * AppExamPlaceDAO接口
 * @author Ivan
 * @version 2016-08-15
 */
@MyBatisDao
public interface AppExamPlaceDao extends CrudDao<AppExamPlace> {

	AppExamPlace getSelfExamByExamPlaceId(Integer examPlaceId);

	AppExamPlace getStudentSelfExamByExamPlaceId(Integer examPlaceId);
	
}