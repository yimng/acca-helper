package com.thinkgem.jeesite.acca.api.plan.service;

import com.thinkgem.jeesite.acca.api.plan.dao.AppAccaEnglishWordDao;
import com.thinkgem.jeesite.acca.api.plan.entity.AppAccaEnglishWord;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ACCA财经词汇Service
 * @author Young
 * @version 2016-08-10
 */
@Service
@Transactional(readOnly = true)
public class AppAccaEnglishWordService extends CrudService<AppAccaEnglishWordDao, AppAccaEnglishWord> {

	@Override
    public AppAccaEnglishWord get(String id) {
		return super.get(id);
	}
	
	@Override
    public List<AppAccaEnglishWord> findList(AppAccaEnglishWord appAccaEnglishWord) {
		return super.findList(appAccaEnglishWord);
	}
	
	@Override
    public Page<AppAccaEnglishWord> findPage(Page<AppAccaEnglishWord> page, AppAccaEnglishWord appAccaEnglishWord) {
		return super.findPage(page, appAccaEnglishWord);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void save(AppAccaEnglishWord appAccaEnglishWord) {
		super.save(appAccaEnglishWord);
	}
	
	@Override
    @Transactional(readOnly = false)
	public void delete(AppAccaEnglishWord appAccaEnglishWord) {
		super.delete(appAccaEnglishWord);
	}
	
}