/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.dao;

import java.util.List;

import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 考试DAO接口
 * @author Fan
 * @version 2016-08-18
 */
@MyBatisDao
public interface WebExamCourseDao extends CrudDao<WebExamCourse> {

	List<WebExamCourse> findMyList(WebExamCourse webExamCourse);

}