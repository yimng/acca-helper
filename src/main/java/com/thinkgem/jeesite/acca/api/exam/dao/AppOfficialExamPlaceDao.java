package com.thinkgem.jeesite.acca.api.exam.dao;

import java.util.List;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamPlace;
import com.thinkgem.jeesite.acca.api.exam.entity.SmallPlace;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface AppOfficialExamPlaceDao extends CrudDao<AppOfficialExamPlace> {

	List<SmallPlace> getExamPlace(AppOfficialExamPlace place);
}
