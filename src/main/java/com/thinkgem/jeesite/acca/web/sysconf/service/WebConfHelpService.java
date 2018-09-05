/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfHelp;
import com.thinkgem.jeesite.acca.web.sysconf.dao.WebConfHelpDao;

/**
 * WebConfHelpService
 * @author Ivan
 * @version 2016-08-24
 */
@Service
@Transactional(readOnly = true)
public class WebConfHelpService extends CrudService<WebConfHelpDao, WebConfHelp> {

	@Override
    public WebConfHelp get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebConfHelp> findList(WebConfHelp webConfHelp) {
		return super.findList(webConfHelp);
	}
	
	@Override
    public Page<WebConfHelp> findPage(Page<WebConfHelp> page, WebConfHelp webConfHelp) {
		return super.findPage(page, webConfHelp);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebConfHelp webConfHelp) {
		super.save(webConfHelp);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebConfHelp webConfHelp) {
		super.delete(webConfHelp);
	}
	
}