/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.exam.dao;

import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSignup;
import com.thinkgem.jeesite.acca.web.exam.entity.WebSignup;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 考试类DAO接口
 * @author Michael
 * @version 2016-08-27
 */
@MyBatisDao
public interface WebExamSignupDao extends CrudDao<WebExamSignup> {

    /**
     * 通过选取的科目,获取考试关联的用户的id列表
     * @param examCourse
     * @return
     */
    List<Long> findListByCourseIds(@Param(value = "examCourse") String examCourse);

    /**
     * 获取某天内的报考列表
     * @param dayStart
     * @param dayEnd
     * @return
     */
    List<WebExamSignup> getWillExams(@Param(value = "dayStart") Date dayStart,@Param(value = "dayEnd") Date dayEnd);

    /**
     * 获取考试列表
     * @param condition
     * @return
     */
    List<WebSignup> findMyList(WebSignup condition);

	List<WebSignup> findListByOrder(WebSignup ws);

	void changeExam(WebExamSignup wes);

	List<WebExamSignup> findDayList(WebExamSignup webSig);
}