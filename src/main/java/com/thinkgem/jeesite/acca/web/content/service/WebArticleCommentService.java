/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebArticleCommentDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleComment;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章评论Service
 * @author Young
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebArticleCommentService extends CrudService<WebArticleCommentDao, WebArticleComment> {

	@Override
    public WebArticleComment get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebArticleComment> findList(WebArticleComment webArticleComment) {
		return super.findList(webArticleComment);
	}
	
	@Override
    public Page<WebArticleComment> findPage(Page<WebArticleComment> page, WebArticleComment webArticleComment) {
		return super.findPage(page, webArticleComment);
	}
	
	/*@Transactional(readOnly = false)
	public void save(WebArticleComment webArticleComment) {
		super.save(webArticleComment);
	}*/
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebArticleComment webArticleComment) {
		super.delete(webArticleComment);
	}
	
}