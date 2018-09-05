/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.web.exam.dao.WebExamSeasonDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSeason;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;

/**
 * 考季版本类Service
 * @author Michael
 * @version 2016-08-27
 */
@Service
@Transactional(readOnly = true)
public class WebExamSeasonService extends CrudService<WebExamSeasonDao, WebExamSeason> {

	@Override
    public WebExamSeason get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebExamSeason> findList(WebExamSeason webExamSeason) {
		return super.findList(webExamSeason);
	}
	
	public List<WebExamSeason> findAllList(WebExamSeason webExamSeason){
		return dao.findAllList(webExamSeason);
	}
	
	@Override
    public Page<WebExamSeason> findPage(Page<WebExamSeason> page, WebExamSeason webExamSeason) {
		return super.findPage(page, webExamSeason);
	}
	
	@Transactional(readOnly = false)
	public void saveSeasons(WebExamSeason webExamSeason, List<WebExamSeason> list){
		dao.delete(webExamSeason);
		for(WebExamSeason we : list){
			dao.insert(we);
		}
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebExamSeason webExamSeason) {
		super.save(webExamSeason);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebExamSeason webExamSeason) {
		super.delete(webExamSeason);
	}
	
}