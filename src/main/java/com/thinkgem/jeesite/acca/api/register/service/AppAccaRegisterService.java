/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.register.service;

import com.thinkgem.jeesite.acca.api.model.request.SaveAccaRegisterPayReq;
import com.thinkgem.jeesite.acca.api.model.request.SaveAccaRegisterReq;
import com.thinkgem.jeesite.acca.api.model.response.SaveAccaRegisterResp;
import com.thinkgem.jeesite.acca.api.register.dao.AppAccaRegisterDao;
import com.thinkgem.jeesite.acca.api.register.dao.AppConfRegisterFeeDao;
import com.thinkgem.jeesite.acca.api.register.dao.AppExamOpenCityDao;
import com.thinkgem.jeesite.acca.api.register.entity.AppAccaRegister;
import com.thinkgem.jeesite.acca.api.register.entity.AppConfRegisterFee;
import com.thinkgem.jeesite.acca.api.register.entity.AppExamOpenCity;
import com.thinkgem.jeesite.acca.api.user.dao.AppConfExamTipsDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppConfExamTips;
import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * 代注册Service
 * @author Young
 * @version 2016-08-09
 */
@Service
@Transactional(readOnly = true)
public class AppAccaRegisterService extends CrudService<AppAccaRegisterDao, AppAccaRegister> {

	@Autowired
	private AppConfRegisterFeeDao confRegisterFeeDao;
	@Autowired
	private AppExamOpenCityDao examOpenCityDao;
	
	@Autowired
	private AppConfExamTipsDao appConfExamTipsDao;

	@Override
	public AppAccaRegister get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<AppAccaRegister> findList(AppAccaRegister appAccaRegister) {
		return super.findList(appAccaRegister);
	}
	
	@Override
	public Page<AppAccaRegister> findPage(Page<AppAccaRegister> page, AppAccaRegister appAccaRegister) {
		return super.findPage(page, appAccaRegister);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(AppAccaRegister appAccaRegister) {
		super.save(appAccaRegister);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(AppAccaRegister appAccaRegister) {
		super.delete(appAccaRegister);
	}

	/**
	 * 获取代注册信息
	 * @param req
	 * @return
     */
	public BaseObjResponse<AppAccaRegister> getRegisterInfo(BaseRequest req) {
		//获取注册信息
		AppAccaRegister appAccaRegister = dao.getByUserId(req.getAppUserId());
		logger.info("获取用户{}代注册信息成功！,信息{}", req.getAppUserId(),appAccaRegister);
		return new BaseObjResponse<AppAccaRegister>(appAccaRegister);
	}

	/**
	 * 提交/修改代注册用户信息
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public SaveAccaRegisterResp saveAccaRegister(SaveAccaRegisterReq req) {
		//获取传进来的注册id和注册类型
		Long appUserId = req.getAppUserId();
		Integer registerType = req.getRegisterType();
		AppAccaRegister accaRegister = new AppAccaRegister();
		//将提交的参数复制到实体中
		try {
			BeanUtils.copyProperties(accaRegister,req);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		accaRegister.setAccaUserId(appUserId);
		AppAccaRegister oldRegister  = dao.get(accaRegister);
		Long registerId = null;
		//判断代注册的id是否为空,如果为空,则进入保存流程;如果不为空,说明已经注册了,获取信息,对状态进行判断
		if (oldRegister != null){
			//代注册id不为空,先获取代注册信息
			Integer status = oldRegister.getStatus();
			registerId = oldRegister.getAccaRegisterId();
			accaRegister.setAccaRegisterId(registerId);
			//判断状态
			if (status == ApiConstant.REGISTER_AUDIT || status == ApiConstant.REGISTER_EGIS || status == ApiConstant.REGISTER_SUCCESS){
				//当状态为待审核,审核通过,注册成功时,不让修改.提示参数错误
				return new SaveAccaRegisterResp(RespConstants.ACCA_REGISTER_STATUS);
			} else if (status == ApiConstant.REGISTER_NOT_PASS && registerType.equals(oldRegister.getRegisterType())){
				//当审核不通过的时候并且和以前的类型相同时,需要先把以前的审核和支付信息清空,进行更新
				accaRegister.setPayImgId(null);
				accaRegister.setPayImg(null);
				accaRegister.setCreateDate(new Date());
				accaRegister.setCheckPersonId(null);
				accaRegister.setCheckPersonName(null);
				accaRegister.setCheckReason(null);
				accaRegister.setCheckTime(null);
			} else if ((status == ApiConstant.REGISTER_NOT_PASS || status == ApiConstant.REGISTER_OBLIGATION) && !registerType.equals(oldRegister.getRegisterType())){
				//当审核不通过或者待付款,并且和以前注册的类型不同的时候,则将以前的信息删除,重新生成
				dao.delete(oldRegister);
				//并将id设置为空
				registerId = null;
				accaRegister.setAccaRegisterId(null);
			}
		}

		//如果参数合法的话,再获取代注册费用
		AppConfRegisterFee confRegisterFee = confRegisterFeeDao.getByType(accaRegister.getRegisterType());
		//保存代注册费用
		accaRegister.setPayAmout(confRegisterFee.getAmount());
		//保存成功后,更改代注册状态为待支付
		accaRegister.setStatus(ApiConstant.REGISTER_OBLIGATION);

		if (registerId == null){
			//保存代注册信息
			dao.insert(accaRegister);
			logger.info("用户{}保存代注册信息{}成功!", appUserId, accaRegister);
		} else {
			//更新代注册信息
			dao.update(accaRegister);
			logger.info("用户{}修改代注册信息{}成功!", appUserId, accaRegister);
		}

		//获取提交的信息
		AppAccaRegister result = dao.get(accaRegister);
		
		
		AppConfExamTips conf = appConfExamTipsDao.get(new AppConfExamTips("5"));
        String payConfTips =conf.getOfficialExamProcess();
		
		return new SaveAccaRegisterResp(result,payConfTips);
	}

	/**
	 * 上传支付凭证,成功后同时需要更改状态为待审核
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BaseResponse saveAccaRegisterPay(SaveAccaRegisterPayReq req) {
		Long accaRegisterId = req.getAccaRegisterId();
		Long payImgId = req.getPayImgId();
		AppAccaRegister accaRegister = new AppAccaRegister();
		accaRegister.setAccaRegisterId(accaRegisterId);
		accaRegister.setPayImgId(payImgId);
		//同时需修改状态为待审核
		accaRegister.setStatus(ApiConstant.REGISTER_AUDIT);
		dao.update(accaRegister);
		logger.info("用户{}上传支付凭证{}成功!", req.getAppUserId(), accaRegister);
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	/**
	 * 获取附近城市列表
	 * @param req
	 * @return
     */
	public BasePageResponse<AppExamOpenCity> getRegisterCity(BaseRequest req) {
		AppExamOpenCity examOpenCity = new AppExamOpenCity();
		//默认为8中博诚通分部8
		examOpenCity.setExamType(ApiConstant.EXAM_TYPE_ZHONGCHENG);
		List<AppExamOpenCity> list = examOpenCityDao.findList(examOpenCity);
		logger.info("用户{}获取附近城市列表{}成功!", req.getAppUserId(), list);
		return new BasePageResponse<AppExamOpenCity>(list);
	}

	/**
	 * 删除代注册信息
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BaseResponse delRegisterInfo(BaseRequest req) {
		//删除登录用户的代注册信息
		dao.delRegisterInfo(req.getAppUserId());
		logger.info("用户{}删除代注册信息成功!", req.getAppUserId());
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}
}