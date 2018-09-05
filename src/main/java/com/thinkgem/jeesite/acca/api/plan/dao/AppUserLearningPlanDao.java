package com.thinkgem.jeesite.acca.api.plan.dao;

import com.thinkgem.jeesite.acca.api.plan.entity.AppUserLearningPlan;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学习计划DAO接口
 * @author Young
 * @version 2016-08-10
 */
@MyBatisDao
public interface AppUserLearningPlanDao extends CrudDao<AppUserLearningPlan> {

    /**
     * 首页获取学习提醒接口,获取学习计划列表
     * @param plan
     * @return
     */
    List<AppUserLearningPlan> findTipsList(AppUserLearningPlan plan);

    /**
     * 获取学习规划列表,通过id排序
     * @param plan
     * @return
     */
    List<AppUserLearningPlan> getPlanListOrderById(AppUserLearningPlan plan);

    /**
     * 获取时间之间的列表
     * @param condition
     * @return
     */
    List<AppUserLearningPlan> getPlanListBetweenDays(AppUserLearningPlan condition);
    
    /**
     * 获取当前时间用户正在学习的学习计划
     * @param accaUserId
     * @return
     */
    List<AppUserLearningPlan> getLearningPlanListByNow(Long accaUserId);

    /**
     * 按条件查询学习规划
     * @param condition
     * @return
     */
    AppUserLearningPlan getByCondition(AppUserLearningPlan condition);

    /**
     * 获取某月某用户的除当前科目外的规划科目数
     * @param accaUserId
     * @param examDateStr
     * @return
     */
    int findMonthList(@Param(value = "accaUserId") Long accaUserId,@Param(value = "courseId") Long examCourseId,@Param(value = "examDateStr") String examDateStr);

    /**
     * 获取被删除的规划数量
     * @param plan
     * @return
     */
    Long getDelPlanCount(AppUserLearningPlan plan);

    /**
     * 获取以前规划过的用户的id列表,非正式使用.只在1.68升级时使用
     * @return
     */
    List<Long> getPreUsers();
}