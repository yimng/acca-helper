/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.register.dao;

import com.thinkgem.jeesite.acca.api.register.entity.AppAccaRegister;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 代注册DAO接口
 * @author Young
 * @version 2016-08-09
 */
@MyBatisDao
public interface AppAccaRegisterDao extends CrudDao<AppAccaRegister> {

    /**
     * 通过登录id获取注册信息
     * @param appUserId
     * @return
     */
    AppAccaRegister getByUserId(Long appUserId);

    /**
     * 根据登录用户删除其代注册信息
     * @param appUserId
     */
    void delRegisterInfo(@Param(value = "appUserId") Long appUserId);
}