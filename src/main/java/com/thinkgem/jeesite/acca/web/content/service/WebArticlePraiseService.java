/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebArticlePraiseDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticlePraise;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章点赞Service
 * @author Young
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebArticlePraiseService extends CrudService<WebArticlePraiseDao, WebArticlePraise> {

	@Override
    public WebArticlePraise get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebArticlePraise> findList(WebArticlePraise webArticlePraise) {
		return super.findList(webArticlePraise);
	}
	
	@Override
    public Page<WebArticlePraise> findPage(Page<WebArticlePraise> page, WebArticlePraise webArticlePraise) {
		return super.findPage(page, webArticlePraise);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebArticlePraise webArticlePraise) {
		super.save(webArticlePraise);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebArticlePraise webArticlePraise) {
		super.delete(webArticlePraise);
	}
	
}