package com.thinkgem.jeesite.acca.web.exam.service;

import com.thinkgem.jeesite.acca.web.exam.dao.WebExamDao;
import com.thinkgem.jeesite.acca.web.exam.entity.*;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class WebExamService extends CrudService<WebExamDao, WebExam> {

	public List<WebExam> countSignups(Map<String, Object> exam){
		return dao.countSignups(exam);
	}
	
	public List<WebExam> findCourseByIds(List<WebExam> list){
		return dao.findCourseByIds(list);
	}
	
	public List<WebSmallCity> selectCitysByType(WebExam exam){
		return dao.selectCitysByType(exam);
	}
	
	public List<WebExamPlace> selectPlaceByType(WebExam exam){
		return dao.selectPlaceByType(exam);
	}
	
	public List<SmallCourse> selectCoursesByType(WebExam exam){
		return dao.selectCoursesByType(exam);
	}
	
	public List<SmallCourse> selectCoursesByExamId(WebExam exam){
		return dao.selectCoursesByExamId(exam);
	}
	
	public List<WebExam> countSignupNum(WebExam webExam){
		return dao.countSignupNum(webExam);
	}
	
	public List<WebExam> selectExamByTypeAndSeason(WebExam webExam){
		return dao.selectExamByTypeAndSeason(webExam);
	}
	
	public List<WebExam> selectSeasons(WebExam webExam){
		return dao.selectSeasons(webExam);
	}
	
	@Transactional(readOnly = false)
	public void updateSeat(WebExam webExam){
		dao.updateSeat(webExam);
	}
	
	@Transactional(readOnly = false)
	public void editOfficial(WebExam webExam){
		if(webExam.getExamId() == null){
			for(WebExam we : webExam.getList()){
				we.setExamCityId(webExam.getExamCityId());
				we.setExamCityName(webExam.getExamCityName());
				we.setExamPlaceId(webExam.getExamPlaceId());
				we.setExamPlaceName(webExam.getExamPlaceName());
				we.setExamType(webExam.getExamType());
				we.setTotalSeats(0);
				we.setUsedSeats(0);
				we.preInsert();
				dao.insert(we);
				WebExamCourseRelation relation = new WebExamCourseRelation();
				relation.setExamId(we.getExamId());
				relation.setExamCourseId(we.getExamCourseId());
				relation.setExamVersionJson(we.getExamVersionJson());
				relation.preInsert();
				dao.insertVersion(relation);
			}
		} else {
			dao.deleteVersionByIds(webExam.getList());
			for(WebExam we : webExam.getList()){
				we.setExamCityId(webExam.getExamCityId());
				we.setExamCityName(webExam.getExamCityName());
				we.setExamPlaceId(webExam.getExamPlaceId());
				we.setExamPlaceName(webExam.getExamPlaceName());
				if(we.getExamId() != null){
					we.preUpdate();
					dao.update(we);
				} else {
					we.setExamType(webExam.getExamType());
					we.preInsert();
					dao.insert(we);
				}
				WebExamCourseRelation relation = new WebExamCourseRelation();
				relation.setExamId(we.getExamId());
				relation.setExamCourseId(we.getExamCourseId());
				relation.setExamVersionJson(we.getExamVersionJson());
				relation.preInsert();
				dao.insertVersion(relation);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void edit(WebExam exam, List<WebExamCourseRelation> list){
		if(exam.getExamId() == null){
			exam.preInsert();
			//exam.setTotalSeats(0);
			exam.setUsedSeats(0);
			dao.insert(exam);
			for(WebExamCourseRelation relation : list){
				relation.setExamId(exam.getExamId());
				dao.insertVersion(relation);
			}
		} else {
			exam.preUpdate();
			dao.update(exam);
			dao.deleteVersion(exam);
			for(WebExamCourseRelation relation : list){
				relation.setExamId(exam.getExamId());
				dao.insertVersion(relation);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void deleteAll(WebExam webExam){
		dao.delete(webExam);
		dao.deleteVersion(webExam);
	}

	public WebExam getById(String id) {
		// TODO Auto-generated method stub
		return dao.getById(id);
	}


	public List<WebExam> getDayList(WebExam webExam) {
		// TODO Auto-generated method stub
		return dao.getDayList(webExam);
	}


}