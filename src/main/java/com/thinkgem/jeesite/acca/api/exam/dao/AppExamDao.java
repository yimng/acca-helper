/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExam;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamCourseSelf;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCityTiny;

/**
 * AppExamDAO接口
 * @author Ivan
 * @version 2016-08-15
 */
@MyBatisDao
public interface AppExamDao extends CrudDao<AppExam> {
	
	public List<AppExam> findSelfExamListByCityAndTime(@Param("examCityId") Integer examCityId,@Param("startTime") Date startTime);
	public List<AppExam> findSelfExamListByCityAndTimeSpan(@Param("examCityId") Integer examCityId,@Param("startTime") Date startTime, @Param("endTime") Date endTime);

	
	public List<AppExamSelfCityTiny> getSelfExamPlaceList();
	
	public AppExam getExamInfoByExamId(Long examId);
	
	public List<AppExamCourseSelf> getExamCourseListByExamId(Long examId);
	
	public void updateUsedSeats(Long examId);
}