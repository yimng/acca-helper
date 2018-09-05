/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleCategory;

/**
 * AppArticleCategoryDAO接口
 * @author Ivan
 * @version 2016-08-10
 */
@MyBatisDao
public interface AppArticleCategoryDao extends CrudDao<AppArticleCategory> {
	
}