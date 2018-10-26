/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.api.exam.dao.AppOfficialExamCourseDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamCourse;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 课程类Service
 * @author Michael
 * @version 2016-08-16
 */
@Service
@Transactional(readOnly = true)
public class AppOfficialExamCourseService extends CrudService<AppOfficialExamCourseDao, AppOfficialExamCourse> {

	@Override
    public AppOfficialExamCourse get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<AppOfficialExamCourse> findList(AppOfficialExamCourse appOfficialExamCourse) {
		return super.findList(appOfficialExamCourse);
	}
	
	@Override
    public Page<AppOfficialExamCourse> findPage(Page<AppOfficialExamCourse> page, AppOfficialExamCourse appOfficialExamCourse) {
		return super.findPage(page, appOfficialExamCourse);
	}
	
	public List<AppOfficialExamCourse> getOfficialExamCourseList(AppOfficialExamCourse appOfficialExamCourse){
		return dao.getOfficialExamCourseList(appOfficialExamCourse);
	}

	public List<AppOfficialExamCourse> getOfficialExamCourseDetailList(AppOfficialExamCourse appOfficialExamCourse){
		return dao.getOfficialExamCourseDetailList(appOfficialExamCourse);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(AppOfficialExamCourse appOfficialExamCourse) {
		super.save(appOfficialExamCourse);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(AppOfficialExamCourse appOfficialExamCourse) {
		super.delete(appOfficialExamCourse);
	}
	
}