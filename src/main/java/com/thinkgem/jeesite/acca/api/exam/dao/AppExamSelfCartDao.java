/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCart;
import org.apache.ibatis.annotations.Param;

/**
 * AppExamSelfCartDAO接口
 * @author Ivan
 * @version 2016-08-17
 */
@MyBatisDao
public interface AppExamSelfCartDao extends CrudDao<AppExamSelfCart> {
	public List<AppExamSelfCart> getByUserId(Long accaUserId);

	public List<AppExamSelfCart> getByUserIdAndPlaceId(@Param("accaUserId") Long accaUserId, @Param("placeId") Long placeId);
	
	
	public void deleteByUserId(@Param("accaUserId") Long accaUserId, @Param("placeId") Long examPlaceId);
}