/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.register.dao;

import com.thinkgem.jeesite.acca.web.register.entity.WebAccaRegister;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 代注册DAO接口
 * @author Young
 * @version 2016-08-17
 */
@MyBatisDao
public interface WebAccaRegisterDao extends CrudDao<WebAccaRegister> {

    /**
     * 审核通过后,填写学员号和密码
     * @param webAccaRegister
     */
    void updateRegisterPass(WebAccaRegister webAccaRegister);

    /**
     * 审核
     * @param webAccaRegister
     */
    void updateRegisterAudit(WebAccaRegister webAccaRegister);

    List<Map<String,Integer>> getStatusCount(WebAccaRegister registerType);
}