/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.PageApi;
import com.thinkgem.jeesite.acca.api.article.entity.AppActivityLog;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;

/**
 * AppArwardDao接口
 * @author Ivan
 * @version 2016-09-08
 */
@MyBatisDao
public interface AppActivityLogDao extends CrudDao<AppActivityLog> {

	public Long getCountTodayByPhone(String phone,Long ActiveId);
	
}