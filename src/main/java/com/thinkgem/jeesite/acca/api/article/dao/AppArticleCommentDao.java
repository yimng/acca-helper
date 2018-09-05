/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.freetek.api.model.PageApi;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleComment;

/**
 * AppArticleCommentDAO接口
 * @author Ivan
 * @version 2016-09-08
 */
@MyBatisDao
public interface AppArticleCommentDao extends CrudDao<AppArticleComment> {
	public long getCommentNumByArticleId(Long articleId);
	
	public List<AppArticleComment> getListByArticleId(@Param("articleId")Long articleId, @Param("pageApi")PageApi page);
	
	public AppArticleComment getByArticleCommentId(Long articleCommentId);
}