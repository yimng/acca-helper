/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.dao;

import com.thinkgem.jeesite.acca.web.content.entity.WebArticleComment;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 文章评论DAO接口
 * @author Young
 * @version 2016-08-18
 */
@MyBatisDao
public interface WebArticleCommentDao extends CrudDao<WebArticleComment> {
	
}