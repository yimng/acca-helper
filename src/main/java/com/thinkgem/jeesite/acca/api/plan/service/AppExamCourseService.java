/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.plan.service;

import com.thinkgem.jeesite.acca.api.plan.dao.AppExamCourseDao;
import com.thinkgem.jeesite.acca.api.plan.entity.AppExamCourse;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考试科目Service
 * @author Young
 * @version 2016-08-12
 */
@Service
@Transactional(readOnly = true)
public class AppExamCourseService extends CrudService<AppExamCourseDao, AppExamCourse> {

	@Override
    public AppExamCourse get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<AppExamCourse> findList(AppExamCourse appExamCourse) {
		return super.findList(appExamCourse);
	}
	
	@Override
    public Page<AppExamCourse> findPage(Page<AppExamCourse> page, AppExamCourse appExamCourse) {
		return super.findPage(page, appExamCourse);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(AppExamCourse appExamCourse) {
		super.save(appExamCourse);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(AppExamCourse appExamCourse) {
		super.delete(appExamCourse);
	}
	
}