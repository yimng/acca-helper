/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.service;

import com.thinkgem.jeesite.acca.web.exam.dao.WebExamPlaceDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamPlace;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考点类Service
 * @author Michael
 * @version 2016-08-28
 */
@Service
@Transactional(readOnly = true)
public class WebExamPlaceService extends CrudService<WebExamPlaceDao, WebExamPlace> {

	@Override
    public WebExamPlace get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<WebExamPlace> findList(WebExamPlace webExamPlace) {
		return super.findList(webExamPlace);
	}
	
	@Override
    public Page<WebExamPlace> findPage(Page<WebExamPlace> page, WebExamPlace webExamPlace) {
		return super.findPage(page, webExamPlace);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(WebExamPlace webExamPlace) {
		if(webExamPlace.getExamPlaceId() == null){
			webExamPlace.preInsert();
			dao.insert(webExamPlace);
		} else {
			webExamPlace.preUpdate();
			dao.update(webExamPlace);
		}
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(WebExamPlace webExamPlace) {
		super.delete(webExamPlace);
	}

	/**
	 * 获取考点id列表
	 * @param place
	 * @return
     */
	public List<Long> findPlacesId(WebExamPlace place) {
		return dao.findPlacesId(place);
	}
}