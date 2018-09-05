/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.home.dao;

import com.thinkgem.jeesite.acca.api.home.entity.AppTips;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * App首页提醒DAO接口
 * @author Young
 * @version 2016-09-07
 */
@MyBatisDao
public interface AppTipsDao extends CrudDao<AppTips> {

    /**
     * 获取某月的提醒列表
     * @param appUserId
     * @param tipMonth
     * @return
     */
    List<AppTips> findByMonth(@Param(value = "appUserId") Long appUserId, @Param(value = "tipMonth") String tipMonth);

    /**
     * 获取某天的提醒列表
     * @param pushTime
     * @return
     */
    List<AppTips> findListByDay(@Param(value = "pushTime") String pushTime);

    /**
     * 获取某月的个人提醒列表
     * @param appUserId
     * @param tipMonth
     * @return
     */
    Long findMyListByMonth(@Param(value = "appUserId") Long appUserId, @Param(value = "tipMonth") String tipMonth);
}