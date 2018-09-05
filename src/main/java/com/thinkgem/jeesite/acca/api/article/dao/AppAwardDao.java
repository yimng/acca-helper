/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.PageApi;
import com.thinkgem.jeesite.acca.api.article.entity.AppAward;

/**
 * AppArwardDao接口
 * @author Ivan
 * @version 2016-09-08
 */
@MyBatisDao
public interface AppAwardDao extends CrudDao<AppAward> {
	public long getAwardNumByAwardId(Long articleId);
	
	public List<AppAward> getListByUserId(@Param("articleId")Long articleId, @Param("pageApi")PageApi page);
	
	public AppAward getByAwardId(Long awardId);

	public Long getCountByContent(AppAward aa);

	public List<AppAward> getLAwardList(Long activeId);

	public List<AppAward> getLAwardListByPhone(Long activeId,String phone);

	public Long getUserAwardNum(String phone);

	public Long getCountByContent(String string);
	
	public Long getCountTodayByContent(String String);

	public Long getUserAddtionNum(String phone, String activeId);
	
	//public Long getCountTodayByPhone(@Param("ActiveId")Long ActiveId,@Param("phone")String phone);
	public Long getCountTodayByPhone(Long ActiveId,String phone);

}