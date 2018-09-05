/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.acca.web.content.dao.WebArticleDao;
import com.thinkgem.jeesite.acca.web.content.dao.WebArticleSubjectRelationDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticle;
import com.thinkgem.jeesite.acca.web.content.entity.WebArticleSubjectRelation;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.HttpUrlConnectionUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章Service
 * @author Young
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WebArticleService extends CrudService<WebArticleDao, WebArticle> {

	@Autowired
	private WebArticleSubjectRelationDao articleSubjectRelationDao;

	@Override
    public WebArticle get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebArticle> findList(WebArticle webArticle) {
		return super.findList(webArticle);
	}
	
	@Override
    public Page<WebArticle> findPage(Page<WebArticle> page, WebArticle webArticle) {
		return super.findPage(page, webArticle);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebArticle webArticle) {
		super.save(webArticle);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebArticle webArticle) {
		super.delete(webArticle);
	}

	/**
	 * 保存文章信息
	 * @param webArticle
	 * @param courseIdList
     */
	@Transactional(readOnly = false)
	public void saveArticle(WebArticle webArticle, String courseIdList) {
		//对HTML进行转码
		if (StringUtils.isNotEmpty(webArticle.getHtmlContent())) {
            webArticle.setHtmlContent(Encodes.unescapeHtml(webArticle.getHtmlContent()));
        }
		if (StringUtils.isNotEmpty(webArticle.getCourseContent())) {
            webArticle.setCourseContent(Encodes.unescapeHtml(webArticle.getCourseContent()));
        }
		//设置初始的浏览量为0
		webArticle.setViewNum((long) 0);
		//保存文章信息
		dao.insert(webArticle);
		if (courseIdList != null && courseIdList.length() > 0){
			//再批量插入新的关联科目
			this.insertBatchSubjectRelation(webArticle.getArticleId(),courseIdList);
		}
		//保存链接地址
		webArticle.setLinkUrl(Global.getProjectBaseUrl() + "/api/article/detail?articleId=" + webArticle.getArticleId());
		dao.update(webArticle);
	}

	/**
	 * 更新文章及文字相关信息
	 * @param webArticle
	 * @param courseIdList
     */
	@Transactional(readOnly = false)
	public void updateArticle(WebArticle webArticle, String courseIdList) {
		//对HTML进行转码
		if (StringUtils.isNotEmpty(webArticle.getHtmlContent())) {
            webArticle.setHtmlContent(Encodes.unescapeHtml(webArticle.getHtmlContent()));
        }
		if (StringUtils.isNotEmpty(webArticle.getCourseContent())) {
            webArticle.setCourseContent(Encodes.unescapeHtml(webArticle.getCourseContent()));
        }
		//先删除所有的关联科目
		WebArticleSubjectRelation condition = new WebArticleSubjectRelation();
		condition.setArticleId(webArticle.getArticleId());
		articleSubjectRelationDao.delete(condition);
		if (courseIdList != null && courseIdList.length() > 0){
			//再批量插入新的关联科目
			this.insertBatchSubjectRelation(webArticle.getArticleId(),courseIdList);
		}
		//保存链接地址
		webArticle.setLinkUrl(Global.getProjectBaseUrl() + "/api/article/detail?articleId=" + webArticle.getArticleId());
		//更新文章信息
		dao.update(webArticle);
	}

	/**
	 * 批量插入文章关联科目信息
	 * @param articleId
	 * @param courseIdList
     */
	private void insertBatchSubjectRelation(Long articleId,String courseIdList){
		String[] courseIdArr = courseIdList.split(",");
		//批量插入文章关联的科目
		if (courseIdArr.length > 0){
			for (String courseId:courseIdArr){
				WebArticleSubjectRelation relation = new WebArticleSubjectRelation();
				relation.setArticleId(articleId);
				relation.setSubjectId(Long.valueOf(courseId));
				//保存关联的科目信息
				articleSubjectRelationDao.insert(relation);
			}
		}
	}
	
}