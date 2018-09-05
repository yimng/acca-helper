/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.home.service;

import com.thinkgem.jeesite.acca.api.home.dao.AppTipsDao;
import com.thinkgem.jeesite.acca.api.home.entity.AppTips;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * App首页提醒Service
 * @author Young
 * @version 2016-09-07
 */
@Service
@Transactional(readOnly = true)
public class AppTipsService extends CrudService<AppTipsDao, AppTips> {

	@Override
    public AppTips get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<AppTips> findList(AppTips appTips) {
		return super.findList(appTips);
	}
	
	@Override
    public Page<AppTips> findPage(Page<AppTips> page, AppTips appTips) {
		return super.findPage(page, appTips);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(AppTips appTips) {
		super.save(appTips);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(AppTips appTips) {
		super.delete(appTips);
	}
	
}