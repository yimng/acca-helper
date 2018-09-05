/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebArticleCollectDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleCollect;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章收藏Service
 * @author Young
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebArticleCollectService extends CrudService<WebArticleCollectDao, WebArticleCollect> {

	@Override
    public WebArticleCollect get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebArticleCollect> findList(WebArticleCollect webArticleCollect) {
		return super.findList(webArticleCollect);
	}
	
	@Override
    public Page<WebArticleCollect> findPage(Page<WebArticleCollect> page, WebArticleCollect webArticleCollect) {
		return super.findPage(page, webArticleCollect);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebArticleCollect webArticleCollect) {
		super.save(webArticleCollect);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebArticleCollect webArticleCollect) {
		super.delete(webArticleCollect);
	}
	
}