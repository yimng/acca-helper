package com.thinkgem.jeesite.acca.api.plan.dao;

import java.util.List;

import com.thinkgem.jeesite.acca.api.plan.entity.AppAccaEnglishWord;
import com.thinkgem.jeesite.acca.api.plan.entity.AppUserLearningPlan;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * ACCA财经词汇DAO接口
 * @author Young
 * @version 2016-08-10
 */
@MyBatisDao
public interface AppAccaEnglishWordDao extends CrudDao<AppAccaEnglishWord> {

	/**
     * 获取指定单词
     * @param name
     * @return
     */
	AppAccaEnglishWord getByName(String name);

}