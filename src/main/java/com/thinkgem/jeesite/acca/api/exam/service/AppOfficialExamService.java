/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.api.exam.dao.AppOfficialExamDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExam;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamMonth;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 考试类Service
 * @author Michael
 * @version 2016-08-15
 */
@Service
@Transactional(readOnly = true)
public class AppOfficialExamService extends CrudService<AppOfficialExamDao, AppOfficialExam> {

	@Override
    public AppOfficialExam get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<AppOfficialExam> findList(AppOfficialExam appOfficialExam) {
		return super.findList(appOfficialExam);
	}
	
	@Override
    public Page<AppOfficialExam> findPage(Page<AppOfficialExam> page, AppOfficialExam appOfficialExam) {
		return super.findPage(page, appOfficialExam);
	}
	
	public List<AppOfficialExamMonth> getExamMonth(AppOfficialExam appOfficialExam){
		return dao.getExamMonth(appOfficialExam);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(AppOfficialExam appOfficialExam) {
		super.save(appOfficialExam);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(AppOfficialExam appOfficialExam) {
		super.delete(appOfficialExam);
	}
	
}