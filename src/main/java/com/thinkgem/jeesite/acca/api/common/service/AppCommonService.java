/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.common.service;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.thinkgem.jeesite.acca.api.common.dao.AppConfHelpDao;
import com.thinkgem.jeesite.acca.api.common.dao.AppMobileDeviceContactDao;
import com.thinkgem.jeesite.acca.api.common.dao.AppMobileDeviceDao;
import com.thinkgem.jeesite.acca.api.common.dao.AppPayConfDao;
import com.thinkgem.jeesite.acca.api.common.entity.AppConfHelp;
import com.thinkgem.jeesite.acca.api.common.entity.AppMobileDevice;
import com.thinkgem.jeesite.acca.api.common.entity.AppMobileDeviceContact;
import com.thinkgem.jeesite.acca.api.common.entity.AppPayConf;
import com.thinkgem.jeesite.acca.api.exam.dao.AppExamSignupDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSignup;
import com.thinkgem.jeesite.acca.api.model.request.RegisterClientReq;
import com.thinkgem.jeesite.acca.api.model.response.GetPayConfInfoResp;
import com.thinkgem.jeesite.acca.api.register.dao.AppAccaRegisterDao;
import com.thinkgem.jeesite.acca.api.register.entity.AppAccaRegister;
import com.thinkgem.jeesite.acca.api.user.dao.AppConfExamTipsDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.entity.AppConfExamTips;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;


/**
 * APP 通用接口 Service
 * @author Ivan
 * @version 2016-07-25
 */
@Service
@Transactional(readOnly = true)
public class AppCommonService  extends BaseService{

	@Autowired
	private AppMobileDeviceDao appMobileDeviceDao;
	
	@Autowired
	private AppMobileDeviceContactDao appMobileDeviceContactDao;
	
	@Autowired
	private AppPayConfDao appPayConfDao;
	
	@Autowired
	private AppConfHelpDao appConfHelpDao;
	
	@Autowired
	private AppAccaRegisterDao appAccaRegisterDao;
	
	@Autowired
	private AppExamSignupDao appExamSignupDao;
	
	@Autowired
	private AppConfExamTipsDao appConfExamTipsDao;
	
	
	@Transactional(readOnly = false)
	public BaseObjResponse<AppMobileDevice> registerClient(RegisterClientReq req) {
		
		String deviceId = "device"+IdGen.uuid();
		
		AppMobileDevice device = new AppMobileDevice();
		try {
			BeanUtils.copyProperties(device, req);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		device.setDeviceId(deviceId);
		device.setCreateDate(new Date());
		device.setUpdateDate(new Date());
		device.setAuthStatus(Constants.DeviceAuthStatus.unauth);
		
		logger.info("AppMobileDevice:"+device);
		
		appMobileDeviceDao.insert(device);
		
		return new BaseObjResponse<AppMobileDevice>(device);
	}

	@Transactional(readOnly = false)
	public BaseResponse sendMobileContant(String deviceId,List<AppMobileDeviceContact> list) {
		
		for(AppMobileDeviceContact index:list){
			index.setDeviceId(deviceId);
		}
		logger.info("AppMobileDeviceContactList:"+list);
		
		appMobileDeviceContactDao.deleteBatchByDeviceId(deviceId);
		appMobileDeviceContactDao.insertBatch(list);
		appMobileDeviceDao.updateAuthStatus(deviceId);
		
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	/*public BasePageResponse<AppPayConf> getPayConfInfo() {
		
		List<AppPayConf> list = appPayConfDao.getAllPayConfList();
		
		return new BasePageResponse<AppPayConf>(list);
	}*/
	
	
	public GetPayConfInfoResp getPayConfInfo(AppAccaUser user,Integer type,Long id) {
		
		//List<AppPayConf> list = appPayConfDao.getAllPayConfList();
		
		int cityId = 0;
		
		int examType = 1;
		
		if(type==1){
			AppAccaRegister entity = new AppAccaRegister();
			entity.setAccaRegisterId(id);
			AppAccaRegister register = appAccaRegisterDao.get(entity);
			logger.info("AppAccaRegister:{}   ... by accaRegisterId:{}",register,id);
			if(register !=null ){
				cityId = register.getCityId();
			}
		}else{
			//type=2，考试订单
			
			AppExamSignup signup = appExamSignupDao.getByOrderIdForPayConf(id);
			logger.info("AppExamSignup:{}   ... by orderId:{}",signup,id);
			if(signup!=null){
				cityId = signup.getExamCityId();
				examType = signup.getExamType();
			}
		}
		
		AppConfExamTips conf =null;
		if(type==1){
			conf = appConfExamTipsDao.get(new AppConfExamTips("6"));
		}else{
			if(examType==1){
				conf = appConfExamTipsDao.get(new AppConfExamTips("4"));
			}else{
				conf = appConfExamTipsDao.get(new AppConfExamTips("5"));
			}
		}
		
        String payConfTips =conf.getOfficialExamProcess();
		
		
		if(cityId!=0){
			List<AppPayConf> list = appPayConfDao.getAllPayConfListByCityId(cityId);
			if(list!=null && !list.isEmpty()){
				return new GetPayConfInfoResp(list,payConfTips);	
			}
		}
		
		//获取数据总部的支付信息
		List<AppPayConf> list = appPayConfDao.getAllPayConfListByAccountType(1);
		
		return new GetPayConfInfoResp(list,payConfTips);
		
	}

	
	
	
	
	
	
	
	
	
	//************************************************************
	// * 页面接口逻辑
	// *、/
	public String confHelpDetail(AppConfHelp appConfHelp, Model model) {
		
		AppConfHelp conf = appConfHelpDao.getByType(appConfHelp.getType());
		if(conf==null){
			return "error/404.jsp";
		}
		model.addAttribute("conf",conf);
		
		return "api/confhelp/confHelp";
	}
	
	
	
	
}