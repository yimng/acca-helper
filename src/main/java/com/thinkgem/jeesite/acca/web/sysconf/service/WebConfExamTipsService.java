/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfExamTips;
import com.thinkgem.jeesite.acca.web.sysconf.dao.WebConfExamTipsDao;

/**
 * WebConfExamTipsService
 * @author Ivan
 * @version 2016-08-25
 */
@Service
@Transactional(readOnly = true)
public class WebConfExamTipsService extends CrudService<WebConfExamTipsDao, WebConfExamTips> {

	@Override
    public WebConfExamTips get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebConfExamTips> findList(WebConfExamTips webConfExamTips) {
		return super.findList(webConfExamTips);
	}
	
	@Override
    public Page<WebConfExamTips> findPage(Page<WebConfExamTips> page, WebConfExamTips webConfExamTips) {
		return super.findPage(page, webConfExamTips);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebConfExamTips webConfExamTips) {
		super.save(webConfExamTips);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebConfExamTips webConfExamTips) {
		super.delete(webConfExamTips);
	}
	
}