/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebArticleCategoryDao;
import com.thinkgem.jeesite.acca.web.content.dao.WebArticleDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleCategory;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章分类Service
 * @author Young
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebArticleCategoryService extends CrudService<WebArticleCategoryDao, WebArticleCategory> {

	@Autowired
	private WebArticleDao articleDao;

	@Override
    public WebArticleCategory get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebArticleCategory> findList(WebArticleCategory webArticleCategory) {
		return super.findList(webArticleCategory);
	}
	
	@Override
    public Page<WebArticleCategory> findPage(Page<WebArticleCategory> page, WebArticleCategory webArticleCategory) {
		return super.findPage(page, webArticleCategory);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebArticleCategory webArticleCategory) {
		super.save(webArticleCategory);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebArticleCategory webArticleCategory) {
		super.delete(webArticleCategory);
	}

	/**
	 * 删除分类
	 * @param webArticleCategory
	 * @return
     */
	@Transactional(readOnly = false)
	public Boolean deleteCategory(WebArticleCategory webArticleCategory) {
		//获取分类下的文章的数量,判断是否为0,为0才可以删除
		Long count = articleDao.findCategoryCount(webArticleCategory.getArticleCategoryId());
		if (count == 0) {
			super.delete(webArticleCategory);
			return true;
		}
		return false;
	}

	/**
	 * 修改文章分类
	 * @param webArticleCategory
     */
	@Transactional(readOnly = false)
	public void update(WebArticleCategory webArticleCategory) {
		dao.update(webArticleCategory);
	}
}