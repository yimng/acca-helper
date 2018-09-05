/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.sysconf.dao.WebApkVersionDao;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebApkVersion;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * WebApkVersionService
 * @author Ivan
 * @version 2016-08-23
 */
@Service
@Transactional(readOnly = true)
public class WebApkVersionService extends CrudService<WebApkVersionDao, WebApkVersion> {

	@Override
    public WebApkVersion get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebApkVersion> findList(WebApkVersion webApkVersion) {
		return super.findList(webApkVersion);
	}
	
	@Override
    public Page<WebApkVersion> findPage(Page<WebApkVersion> page, WebApkVersion webApkVersion) {
		return super.findPage(page, webApkVersion);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebApkVersion webApkVersion) {
		super.save(webApkVersion);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebApkVersion webApkVersion) {
		super.delete(webApkVersion);
	}
	
}