/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.user.dao.WebMobileDeviceDao;
import com.thinkgem.jeesite.acca.web.user.entity.MobileDeviceContact;
import com.thinkgem.jeesite.acca.web.user.entity.WebMobileDevice;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * WebMobileDeviceService
 * @author Ivan
 * @version 2016-08-20
 */
@Service
@Transactional(readOnly = true)
public class WebMobileDeviceService extends CrudService<WebMobileDeviceDao, WebMobileDevice> {

	@Override
    public WebMobileDevice get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebMobileDevice> findList(WebMobileDevice webMobileDevice) {
		return super.findList(webMobileDevice);
	}
	
	@Override
    public Page<WebMobileDevice> findPage(Page<WebMobileDevice> page, WebMobileDevice webMobileDevice) {
		return super.findPage(page, webMobileDevice);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebMobileDevice webMobileDevice) {
		super.save(webMobileDevice);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebMobileDevice webMobileDevice) {
		super.delete(webMobileDevice);
	}

	public void findContactPage(MobileDeviceContact contact) {
		
		
	}
	
}