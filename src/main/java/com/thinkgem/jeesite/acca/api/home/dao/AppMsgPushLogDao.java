package com.thinkgem.jeesite.acca.api.home.dao;

import com.thinkgem.jeesite.acca.api.home.entity.AppMsgPushLog;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 接收的消息DAO接口
 * @author Young
 * @version 2016-08-15
 */
@MyBatisDao
public interface AppMsgPushLogDao extends CrudDao<AppMsgPushLog> {

    /**
     * 客户端获取所有的消息列表
     * @param condition
     * @return
     */
    List<AppMsgPushLog> getMsgList(AppMsgPushLog condition);

    /**
     * 获取未读消息数,用来判断是否有小红点
     * @param params
     * @return
     */
    Long getUnReadMsgCount(Map<String, Object> params);

    /**
     * 修改消息状态
     * 如果msgId不为-1,则修改对应的id数据;否则,全部修改为已读状态
     * @param msgId
     * @param accaUserId
     */
    void updateMsgReadStatus(@Param(value = "msgId") Long msgId,@Param(value = "accaUserId") Long accaUserId);

    /**
     * 批量删除消息
     * @param list
     */
    void delBatchMsg(List<Long> list);
}