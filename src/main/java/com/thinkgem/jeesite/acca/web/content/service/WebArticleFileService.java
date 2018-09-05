/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebArticleFileDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleFile;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章附件Service
 * @author Young
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebArticleFileService extends CrudService<WebArticleFileDao, WebArticleFile> {

	@Override
    public WebArticleFile get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebArticleFile> findList(WebArticleFile webArticleFile) {
		return super.findList(webArticleFile);
	}
	
	@Override
    public Page<WebArticleFile> findPage(Page<WebArticleFile> page, WebArticleFile webArticleFile) {
		return super.findPage(page, webArticleFile);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebArticleFile webArticleFile) {
		super.save(webArticleFile);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebArticleFile webArticleFile) {
		super.delete(webArticleFile);
	}
	
}