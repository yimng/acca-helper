/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.sysconf.dao.WebAccaClubDao;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebAccaClub;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * WebAccaClub模块Service
 * @author Ivan
 * @version 2016-08-23
 */
@Service
@Transactional(readOnly = true)
public class WebAccaClubService extends CrudService<WebAccaClubDao, WebAccaClub> {

	@Override
    public WebAccaClub get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebAccaClub> findList(WebAccaClub webAccaClub) {
		return super.findList(webAccaClub);
	}
	
	@Override
    public Page<WebAccaClub> findPage(Page<WebAccaClub> page, WebAccaClub webAccaClub) {
		return super.findPage(page, webAccaClub);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebAccaClub webAccaClub) {
		super.save(webAccaClub);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebAccaClub webAccaClub) {
		super.delete(webAccaClub);
	}
	
}