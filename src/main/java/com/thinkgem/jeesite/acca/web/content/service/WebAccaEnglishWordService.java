/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebAccaEnglishWordDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebAccaEnglishWord;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.Encodes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ACCA财经词汇Service
 * @author Young
 * @version 2016-08-22
 */
@Service
@Transactional(readOnly = true)
public class WebAccaEnglishWordService extends CrudService<WebAccaEnglishWordDao, WebAccaEnglishWord> {

	@Override
    public WebAccaEnglishWord get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebAccaEnglishWord> findList(WebAccaEnglishWord webAccaEnglishWord) {
		return super.findList(webAccaEnglishWord);
	}
	
	@Override
    public Page<WebAccaEnglishWord> findPage(Page<WebAccaEnglishWord> page, WebAccaEnglishWord webAccaEnglishWord) {
		return super.findPage(page, webAccaEnglishWord);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebAccaEnglishWord webAccaEnglishWord) {
		super.save(webAccaEnglishWord);

	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebAccaEnglishWord webAccaEnglishWord) {
		super.delete(webAccaEnglishWord);
	}

	@Transactional(readOnly = false)
	public String saveOrUpdate(WebAccaEnglishWord webAccaEnglishWord) {
		//对保存的HTML内容进行解码
		webAccaEnglishWord.setDescription(Encodes.unescapeHtml(webAccaEnglishWord.getDescription()));
		webAccaEnglishWord.setEndescription(Encodes.unescapeHtml(webAccaEnglishWord.getEndescription()));
		super.save(webAccaEnglishWord);
		String id = webAccaEnglishWord.getId();
		String message = "";
		if (id != null){
			message = "修改ACCA财经词汇成功!";
		} else {
			message = "保存ACCA财经词汇成功!";
		}
		webAccaEnglishWord.setLinkUrl(Global.getProjectBaseUrl() + "/api/plan/getAccaEnglishWordDesc.do?id=" + id);
		dao.update(webAccaEnglishWord);
		return message;
	}
}