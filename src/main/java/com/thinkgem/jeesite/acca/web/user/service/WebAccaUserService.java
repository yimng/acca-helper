/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.service;

import com.thinkgem.jeesite.acca.web.user.dao.WebAccaUserDao;
import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * accaUser模块Service
 * @author Ivan
 * @version 2016-08-17
 */
@Service
@Transactional(readOnly = true)
public class WebAccaUserService extends CrudService<WebAccaUserDao, WebAccaUser> {

	@Override
    public WebAccaUser get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebAccaUser> findList(WebAccaUser webAccaUser) {
		return super.findList(webAccaUser);
	}
	
	@Override
    public Page<WebAccaUser> findPage(Page<WebAccaUser> page, WebAccaUser webAccaUser) {
		return super.findPage(page, webAccaUser);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebAccaUser webAccaUser) {
		super.save(webAccaUser);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebAccaUser webAccaUser) {
		super.delete(webAccaUser);
	}

	public List<WebAccaUser> findMyList() {
		// TODO Auto-generated method stub
		return dao.findMyList();
	}
	@Transactional(readOnly = false)
	public void freeze(WebAccaUser webAccaUser) {
		// TODO Auto-generated method stub
		dao.freeze(webAccaUser);
	}
	@Transactional(readOnly = false)
	public void updateType(WebAccaUser webAccaUser) {
		// TODO Auto-generated method stub
		dao.updateType(webAccaUser);
	}
	@Transactional(readOnly = false)
	public void updateIszbg(WebAccaUser webAccaUser) {
		// TODO Auto-generated method stub
		dao.updateIszbg(webAccaUser);
	}

	public List<WebAccaUser> getDetailList(List<WebAccaUser> list) {
		// TODO Auto-generated method stub
		Date date = new Date();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("date", date);
		params.put("list", list);
		logger.info("params:"+params);
		return dao.getDetailList(params);
	}

	@Transactional(readOnly = false)
	public void updateRegister(WebAccaUser webAccaUser) {
		dao.updateRegister(webAccaUser);
	}

	public Page<WebAccaUser> findauthPage(Page<WebAccaUser> page, WebAccaUser webAccaUser) {
		// TODO Auto-generated method stub
		return super.findPage(page, webAccaUser);
	}
}