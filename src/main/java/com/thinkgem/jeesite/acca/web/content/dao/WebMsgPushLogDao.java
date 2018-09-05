/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.content.dao;

import com.thinkgem.jeesite.acca.web.content.entity.WebMsgPushLog;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 推送消息DAO接口
 * @author Young
 * @version 2016-08-25
 */
@MyBatisDao
public interface WebMsgPushLogDao extends CrudDao<WebMsgPushLog> {

    /**
     * 批量插入推送消息
     * @param logList
     */
    void insertBatch(List<WebMsgPushLog> logList);
}