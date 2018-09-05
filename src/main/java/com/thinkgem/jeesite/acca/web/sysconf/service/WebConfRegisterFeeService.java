/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.sysconf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.sysconf.dao.WebConfRegisterFeeDao;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebConfRegisterFee;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 注册用户费用管理Service
 * @author Fan
 * @version 2016-08-23
 */
@Service
@Transactional(readOnly = true)
public class WebConfRegisterFeeService extends CrudService<WebConfRegisterFeeDao, WebConfRegisterFee> {

	@Override
    public WebConfRegisterFee get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebConfRegisterFee> findList(WebConfRegisterFee webConfRegisterFee) {
		return super.findList(webConfRegisterFee);
	}
	
	@Override
    public Page<WebConfRegisterFee> findPage(Page<WebConfRegisterFee> page, WebConfRegisterFee webConfRegisterFee) {
		return super.findPage(page, webConfRegisterFee);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebConfRegisterFee webConfRegisterFee) {
		super.save(webConfRegisterFee);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebConfRegisterFee webConfRegisterFee) {
		super.delete(webConfRegisterFee);
	}
	
}