/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.user.dao.MobileDeviceContactDao;
import com.thinkgem.jeesite.acca.web.user.entity.MobileDeviceContact;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * AppMobileDeviceContactService
 * @author Ivan
 * @version 2016-08-20
 */
@Service
@Transactional(readOnly = true)
public class MobileDeviceContactService extends CrudService<MobileDeviceContactDao, MobileDeviceContact> {

	@Override
    public MobileDeviceContact get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<MobileDeviceContact> findList(MobileDeviceContact mobileDeviceContact) {
		return super.findList(mobileDeviceContact);
	}
	
	@Override
    public Page<MobileDeviceContact> findPage(Page<MobileDeviceContact> page, MobileDeviceContact mobileDeviceContact) {
		return super.findPage(page, mobileDeviceContact);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(MobileDeviceContact mobileDeviceContact) {
		super.save(mobileDeviceContact);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(MobileDeviceContact mobileDeviceContact) {
		super.delete(mobileDeviceContact);
	}
	
}