/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.dao;

import com.thinkgem.jeesite.acca.web.content.entity.WebArticle;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 文章DAO接口
 * @author Young
 * @version 2016-08-18
 */
@MyBatisDao
public interface WebArticleDao extends CrudDao<WebArticle> {

    /**
     * 获取分类下的文章数量
     * @param articleCategoryId
     * @return
     */
    Long findCategoryCount(@Param(value = "articleCategoryId") Long articleCategoryId);
}