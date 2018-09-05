/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.acca.api.user.dao.AppSmsVcodeDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppSmsVcode;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.AppUtils;
import com.thinkgem.jeesite.common.utils.TimeUtils;
import com.thinkgem.jeesite.common.utils.sms.SmsUtils;
import com.thinkgem.jeesite.freetek.api.constant.PublicConstants;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

/**
 * APP 通用接口 Service
 * @author Ivan
 * @version 2016-07-25
 */
@Service
@Transactional(readOnly = true)
public class AppSmsVcodeService extends CrudService<AppSmsVcodeDao, AppSmsVcode> {
	
	@Autowired
	AppSmsVcodeDao appSmsVcodeDao;
	
	@Value("${smsVcode.sendGap}")
	private int sendGap = 60;
	
	@Value("${smsVcode.expireGap}")
	private int expireGap = 60;
	
	@Value("${smsVcode.debugMode}")
	private boolean debugMode = false;
	
	@Transactional(readOnly = false)
	public BaseResponse getSmsVcode(String mobile) {
		
		// 参数校验
		if (!AppUtils.isMobileNum(mobile)) {
			return new BaseResponse(RespConstants.SMS_VCODE_MOBILE_TYPE_ERROR);
		}
		// 查询验证码限制条件（下发间隔、黑名单等）
		AppSmsVcode smsVcode = appSmsVcodeDao.getByMobile(mobile);
		if (smsVcode!=null && smsVcode.getSendDate()!=null && !TimeUtils.isExpire(smsVcode.getSendDate(), sendGap)) {
			logger.info("获取验证码失败，发送间隔内: 手机号:{}，上次发送时间:{}", mobile, smsVcode.getSendDate());
			return new BaseResponse(RespConstants.SMS_VCODE_IN_SEND_GAP);
		}
		// 生成验证码
		smsVcode = new AppSmsVcode();
		String vcode = RandomStringUtils.randomNumeric(4);
		if (debugMode) {
			vcode = PublicConstants.SMS_VCODE_DEFAULT;
		}
		smsVcode.setMobile(mobile);
		smsVcode.setVcode(vcode);
		smsVcode.setValidStatus(Global.NORMAL);
		smsVcode.setSendDate(new Date());

		// 发送验证码
		if (!debugMode) {
			HashMap<String, Object> smsres = SmsUtils.sendSms2Vcode(mobile, new String[]{vcode,expireGap/60+""});
			if(!"000000".equals(smsres.get("statusCode"))) {
				smsVcode.setSendStatus(Global.ABNORMAL);
				appSmsVcodeDao.insert(smsVcode);
				logger.error("验证码发送失败: 手机号:{}, 验证码:{}", mobile, vcode);
				logger.error("验证码发送失败原因: 错误码:{}, 错误描述:{}", smsres.get("statusCode"), smsres.get("statusMsg"));
				return new BaseResponse(RespConstants.SMS_VCODE_SEND_FAIL);
			}
		}
		
		// 保存验证码
		smsVcode.setSendStatus(Global.NORMAL);
		appSmsVcodeDao.insert(smsVcode);
		logger.error("验证码发送成功: 手机号:{}, 验证码:{}", mobile, vcode);
		
		BaseResponse resp = new BaseResponse(RespConstants.GLOBAL_SUCCESS);
		if (debugMode) {
			resp.setRespDesc("演示模式，请输入验证码 " + PublicConstants.SMS_VCODE_DEFAULT);
		}
		return resp;
	}	
	
	@Transactional(readOnly = false)
	public boolean checkSmsVcode (String mobile, String vcode) {
		
		// 验证码有效性判断
		AppSmsVcode smsVcode = appSmsVcodeDao.getByMobile(mobile);
		if (smsVcode == null || StringUtils.isEmpty(smsVcode.getVcode()) || !smsVcode.getVcode().equals(vcode)) {
			logger.info("验证码不正确: 手机号:{}, 验证码:{}", mobile, vcode);
			return false;
		}
		if (!Global.NORMAL.equals(smsVcode.getValidStatus())) {
			logger.info("验证码已被验证过: 手机号:{}, 验证码:{}", mobile, vcode);
			return false;
		}
		if (!Global.NORMAL.equals(smsVcode.getSendStatus())) {
			logger.info("验证码未发送成功: 手机号:{}, 验证码:{}", mobile, vcode);
			return false;
		}
		if (TimeUtils.isExpire(smsVcode.getSendDate(), expireGap)) {
			logger.info("验证码失效: 手机号:{}, 验证码:{}", mobile, vcode);
			return false;
		}
		
		logger.info("验证码有效: 手机号:{}, 验证码:{}", mobile, vcode);
		// 更新数据库验证状态
		smsVcode.setValidStatus(1);
		smsVcode.setValidDate(new Date());
		appSmsVcodeDao.update(smsVcode);
		
		return true;
	}

	
	
}