/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebMsgPushLogDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebMsgPushLog;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 推送消息Service
 * @author Young
 * @version 2016-08-25
 */
@Service
@Transactional(readOnly = true)
public class WebMsgPushLogService extends CrudService<WebMsgPushLogDao, WebMsgPushLog> {

	@Override
    public WebMsgPushLog get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebMsgPushLog> findList(WebMsgPushLog webMsgPushLog) {
		return super.findList(webMsgPushLog);
	}
	
	@Override
    public Page<WebMsgPushLog> findPage(Page<WebMsgPushLog> page, WebMsgPushLog webMsgPushLog) {
		return super.findPage(page, webMsgPushLog);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebMsgPushLog webMsgPushLog) {
		super.save(webMsgPushLog);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebMsgPushLog webMsgPushLog) {
		super.delete(webMsgPushLog);
	}
	
}