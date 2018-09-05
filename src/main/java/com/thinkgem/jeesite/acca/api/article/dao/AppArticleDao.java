/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.freetek.api.model.PageApi;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticle;

/**
 * AppArticleDAO接口
 * @author Ivan
 * @version 2016-08-10
 */
@MyBatisDao
public interface AppArticleDao extends CrudDao<AppArticle> {
	
	public List<AppArticle> getListByArticleCategoryId(AppArticle article);
	
	public List<AppArticle> getListByLearningAndTearcher(AppArticle article);
	
	
	public void updateViewNum(AppArticle article);
	
	public List<AppArticle> getListByExamCourseList(@Param("list")List<Long> list,@Param("pageApi")PageApi page);
	

	public List<AppArticle> getCollectArticleList(@Param("accaUserId")Long accaUserId,@Param("pageApi")PageApi page);
	
	public List<AppArticle> getListByTitleAndCategoryId(AppArticle article);
}