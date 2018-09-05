/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.sysconf.dao.WebPayConfDao;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebPayConf;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 收款账户管理Service
 * @author Fan
 * @version 2016-08-22
 */
@Service
@Transactional(readOnly = true)
public class WebPayConfService extends CrudService<WebPayConfDao, WebPayConf> {

	@Override
    public WebPayConf get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebPayConf> findList(WebPayConf webPayConf) {
		return super.findList(webPayConf);
	}
	
	@Override
    public Page<WebPayConf> findPage(Page<WebPayConf> page, WebPayConf webPayConf) {
		return super.findPage(page, webPayConf);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebPayConf webPayConf) {
		super.save(webPayConf);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebPayConf webPayConf) {
		super.delete(webPayConf);
	}
	
}