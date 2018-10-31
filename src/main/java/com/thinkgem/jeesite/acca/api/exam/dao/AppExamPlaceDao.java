/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import com.thinkgem.jeesite.acca.api.exam.entity.AppSmallExamPlace;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamPlace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AppExamPlaceDAO接口
 * @author Ivan
 * @version 2016-08-15
 */
@MyBatisDao
public interface AppExamPlaceDao extends CrudDao<AppExamPlace> {

	AppExamPlace getSelfExamByExamPlaceId(Integer examPlaceId);
	List<AppSmallExamPlace> getExamCenterList(@Param("cityId") Integer cityId);
}