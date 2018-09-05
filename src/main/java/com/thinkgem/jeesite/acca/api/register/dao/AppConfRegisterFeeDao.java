/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.register.dao;

import com.thinkgem.jeesite.acca.api.register.entity.AppConfRegisterFee;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * 代注册费用DAO接口
 * @author Young
 * @version 2016-08-09
 */
@MyBatisDao
public interface AppConfRegisterFeeDao extends CrudDao<AppConfRegisterFee> {

    /**
     * 通过类型获取代注册费用信息
     * @param type
     * @return
     */
    AppConfRegisterFee getByType(Integer type);
}