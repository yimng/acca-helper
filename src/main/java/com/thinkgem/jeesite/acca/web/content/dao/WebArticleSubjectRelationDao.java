/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.dao;

import com.thinkgem.jeesite.acca.web.content.entity.WebArticleSubjectRelation;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章关联科目DAO接口
 * @author Young
 * @version 2016-08-18
 */
@MyBatisDao
public interface WebArticleSubjectRelationDao extends CrudDao<WebArticleSubjectRelation> {

    /**
     * 通过文章id串获取文章关联科目列表
     * @param articleIds
     * @return
     */
    List<WebArticleSubjectRelation> findListByArticleIds(@Param(value = "articleIds") List<Long> articleIds);
}