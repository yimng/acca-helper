/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticlePraise;

/**
 * AppArticlePraiseDAO接口
 * @author Ivan
 * @version 2016-09-08
 */
@MyBatisDao
public interface AppArticlePraiseDao extends CrudDao<AppArticlePraise> {
	
	public AppArticlePraise getByUserIdAndArticleId(@Param("accaUserId")Long accaUserId,@Param("articleId")Long articleId);
	
	public void deleteByAccaUserIdAndArticleId(@Param("accaUserId")Long accaUserId,@Param("articleId")Long articleId);
	
	public Long getPraiseCountByArticleId(Long articleId);
	
}