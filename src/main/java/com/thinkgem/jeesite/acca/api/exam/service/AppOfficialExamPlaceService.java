package com.thinkgem.jeesite.acca.api.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.acca.api.exam.dao.AppOfficialExamPlaceDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamPlace;
import com.thinkgem.jeesite.acca.api.exam.entity.SmallPlace;
import com.thinkgem.jeesite.common.service.CrudService;

@Service
@Transactional(readOnly = true)
public class AppOfficialExamPlaceService extends CrudService<AppOfficialExamPlaceDao, AppOfficialExamPlace> {

	public List<SmallPlace> getExamPlace(AppOfficialExamPlace place){
		return dao.getExamPlace(place);
	}

	public List<SmallPlace> getExamPlaceBySeason(AppOfficialExamPlace place) {
		return dao.getExamPlaceBySeason(place);
	}

}
