/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.freetek.api.model.PageApi;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleCollect;

/**
 * AppArticleCollectDAO接口
 * @author Ivan
 * @version 2016-09-07
 */
@MyBatisDao
public interface AppArticleCollectDao extends CrudDao<AppArticleCollect> {
	
	public void deleteArticleCollectBatch(@Param("list")List<Long> list,@Param("accaUserId")Long accaUserId);
	
	public AppArticleCollect getByUserIdAndAticleId(@Param("accaUserId")Long accaUserId,@Param("articleId")Long articleId);
	
}