/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.service;

import com.thinkgem.jeesite.acca.web.content.dao.WebTeacherDao;
import com.thinkgem.jeesite.acca.web.content.entity.WebTeacher;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 名师指导Service
 * @author Young
 * @version 2016-08-22
 */
@Service
@Transactional(readOnly = true)
public class WebTeacherService extends CrudService<WebTeacherDao, WebTeacher> {

	@Override
    public WebTeacher get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebTeacher> findList(WebTeacher webTeacher) {
		return super.findList(webTeacher);
	}
	
	@Override
    public Page<WebTeacher> findPage(Page<WebTeacher> page, WebTeacher webTeacher) {
		return super.findPage(page, webTeacher);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebTeacher webTeacher) {
		super.save(webTeacher);
	}

	/**
	 * 保存名师信息
	 * @param webTeacher
	 * @return
     */
	@Transactional(readOnly = false)
	public String saveTeacher(WebTeacher webTeacher) {
		String message = "";
		if (webTeacher.getTeacherId() == null){
			super.save(webTeacher);
			message = "保存名师成功";
		} else {
			dao.update(webTeacher);
			message = "修改名师成功";
		}
		return message;
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebTeacher webTeacher) {
		super.delete(webTeacher);
	}
	
}