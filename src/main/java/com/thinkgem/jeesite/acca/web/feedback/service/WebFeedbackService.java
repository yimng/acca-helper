/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.feedback.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.feedback.dao.WebFeedbackDao;
import com.thinkgem.jeesite.acca.web.feedback.entity.WebFeedback;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * WebFeedbackService
 * @author Ivan
 * @version 2016-08-20
 */
@Service
@Transactional(readOnly = true)
public class WebFeedbackService extends CrudService<WebFeedbackDao, WebFeedback> {

	@Override
    public WebFeedback get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebFeedback> findList(WebFeedback webFeedback) {
		return super.findList(webFeedback);
	}
	
	@Override
    public Page<WebFeedback> findPage(Page<WebFeedback> page, WebFeedback webFeedback) {
		return super.findPage(page, webFeedback);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebFeedback webFeedback) {
		super.save(webFeedback);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebFeedback webFeedback) {
		super.delete(webFeedback);
	}
	
}