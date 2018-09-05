package com.thinkgem.jeesite.acca.web.exam.dao;

import com.thinkgem.jeesite.acca.web.exam.entity.*;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
import java.util.Map;

@MyBatisDao
@CacheNamespace
public interface WebExamDao extends CrudDao<WebExam> {

	List<WebExam> countSignups(Map<String, Object> exam);
	
	List<WebExam> findCourseByIds(List<WebExam> list);
	
	List<WebSmallCity> selectCitysByType(WebExam exam);
	
	List<WebExamPlace> selectPlaceByType(WebExam exam);
	
	List<SmallCourse> selectCoursesByType(WebExam exam);
	
	void insertVersion(WebExamCourseRelation relation);
	
	void deleteVersion(WebExam exam);
	
	List<SmallCourse> selectCoursesByExamId(WebExam exam);
	
	void updateSeat(WebExam webExam);
	
	List<WebExam> countSignupNum(WebExam webExam);
	
	List<WebExam> selectExamByTypeAndSeason(WebExam webExam);
	
	void deleteVersionByIds(List<WebExam> list);
	
	List<WebExam> selectSeasons(WebExam webExam);

	WebExam getById(String id);

	List<WebExam> getDayList(WebExam webExam);
}
