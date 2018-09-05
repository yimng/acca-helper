/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebArticleSubjectRelationDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleSubjectRelation;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章关联科目Service
 * @author Young
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebArticleSubjectRelationService extends CrudService<WebArticleSubjectRelationDao, WebArticleSubjectRelation> {

	@Override
    public WebArticleSubjectRelation get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebArticleSubjectRelation> findList(WebArticleSubjectRelation webArticleSubjectRelation) {
		return super.findList(webArticleSubjectRelation);
	}
	
	@Override
    public Page<WebArticleSubjectRelation> findPage(Page<WebArticleSubjectRelation> page, WebArticleSubjectRelation webArticleSubjectRelation) {
		return super.findPage(page, webArticleSubjectRelation);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebArticleSubjectRelation webArticleSubjectRelation) {
		super.save(webArticleSubjectRelation);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebArticleSubjectRelation webArticleSubjectRelation) {
		super.delete(webArticleSubjectRelation);
	}

	public List<WebArticleSubjectRelation> findListByArticleIds(List<Long> articleIds) {
		return dao.findListByArticleIds(articleIds);
	}
}