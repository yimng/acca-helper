package com.thinkgem.jeesite.acca.api.home.service;

import com.thinkgem.jeesite.acca.api.home.dao.AppMsgPushLogDao;
import com.thinkgem.jeesite.acca.api.home.entity.AppMsgPushLog;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收的消息Service
 * @author Young
 * @version 2016-08-15
 */
@Service
@Transactional(readOnly = true)
public class AppMsgPushLogService extends CrudService<AppMsgPushLogDao, AppMsgPushLog> {

	/**
	 * 客户端获取所有的消息列表
	 * @param req
	 * @return
     */
	public BasePageResponse<AppMsgPushLog> getMsgList(BasePageRequest req) {
		int correctParams = req.isCorrectParams();
		if (correctParams != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppMsgPushLog>(correctParams);
		}
		//设置查询条件,登录信息和分页信息
		AppMsgPushLog condition = new AppMsgPushLog();
		condition.setPageApi(req.getPage());
		condition.setReceiveId(req.getAppUserId());
		List<AppMsgPushLog> pushLogList = dao.getMsgList(condition);
		logger.info("{}获取消息列表{}成功!", req.getAppUserId(), pushLogList);
		return new BasePageResponse<AppMsgPushLog>(pushLogList);
	}

	/**
	 * 获取未读消息的数量,用来判断是否有小红点
	 * @param req
	 * @return
     */
	public BaseObjResponse<Long> getUnReadMsgCount(BaseRequest req) {
		int correctParams = req.isCorrectParams();
		if (correctParams != RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<Long>(correctParams);
		}
		//获取到上次阅读的时间
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("receiveId", req.getAppUserId());
		Long count = dao.getUnReadMsgCount(params);
		logger.info("{}获取未读消息数为{}!", req.getAppUserId(), count);
		return new BaseObjResponse<Long>(count);
	}

	/**
	 * 更新消息阅读状态
	 * 如果msgId不为-1,则修改对应的id数据;否则,全部修改为已读状态
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BaseResponse updateMsgReadStatus(BaseObjRequest<Long> req) {
		int correctParams = req.isCorrectParams();
		if (correctParams != RespConstants.GLOBAL_SUCCESS) {
			return new BaseResponse(correctParams);
		}
		Long msgId = req.getObj();
		Long accaUserId = req.getAppUserId();
		// 修改消息状态,如果参数不为-1,则修改对应的id数据;否则,全部修改为已读状态
		dao.updateMsgReadStatus(msgId,accaUserId);
		logger.info("{}修改消息状态成功!消息id:{}", accaUserId, msgId);
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	/**
	 * 批量删除消息
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BaseResponse delBatchMsg(BaseBatchRequest<Long> req) {
		int correctParams = req.isCorrectParams();
		if (correctParams != RespConstants.GLOBAL_SUCCESS) {
			return new BaseResponse(correctParams);
		}
		//批量删除消息
		dao.delBatchMsg(req.getList());
		logger.info("{}删除消息状态成功!消息id:{}", req.getAppUserId(), req.getList());
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

}