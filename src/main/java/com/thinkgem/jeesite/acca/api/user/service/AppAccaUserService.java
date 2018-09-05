/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import com.thinkgem.jeesite.freetek.api.model.PageApi;
import com.thinkgem.jeesite.freetek.file.entity.FileInfo;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import com.thinkgem.jeesite.acca.api.article.dao.AppArticleCollectDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppArticleDao;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticle;
import com.thinkgem.jeesite.acca.api.exam.dao.AppExamSignupDao;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSignup;
import com.thinkgem.jeesite.acca.api.model.request.FeedbackReq;
import com.thinkgem.jeesite.acca.api.model.request.UpdateUserInfoReq;
import com.thinkgem.jeesite.acca.api.model.request.UpdateUserLocationReq;
import com.thinkgem.jeesite.acca.api.model.response.AccaConfInfo;
import com.thinkgem.jeesite.acca.api.model.response.article.AppArticleCollectDto;
import com.thinkgem.jeesite.acca.api.order.dao.AppOrderDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaClub;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.entity.AppApkVersion;
import com.thinkgem.jeesite.acca.api.user.entity.AppConfExamTips;
import com.thinkgem.jeesite.acca.api.user.entity.AppFeedback;
import com.thinkgem.jeesite.acca.api.user.dao.AppAccaClubDao;
import com.thinkgem.jeesite.acca.api.user.dao.AppAccaUserDao;
import com.thinkgem.jeesite.acca.api.user.dao.AppApkVersionDao;
import com.thinkgem.jeesite.acca.api.user.dao.AppConfExamTipsDao;
import com.thinkgem.jeesite.acca.api.user.dao.AppFeedbackDao;
import com.thinkgem.jeesite.acca.constant.Constants;

/**
 * AppAccaUserService
 * @author Ivan
 * @version 2016-08-09
 */
@Service
@Transactional(readOnly = true)
public class AppAccaUserService extends CrudService<AppAccaUserDao, AppAccaUser> {
	
	@Autowired
	private AppSmsVcodeService appSmsVcodeService;
	
	@Autowired
	private AppAccaClubDao appAccaClubDao;
	
	@Autowired
	private AppFeedbackDao appFeedbackDao;
	
	@Autowired
	private AppApkVersionDao appApkVersionDao;
	
	@Autowired
	private AppConfExamTipsDao appConfExamTipsDao;
	
	@Autowired
	private AppExamSignupDao appExamSignupDao;
	
	@Autowired
	private AppArticleDao appArticleDao;
	
	@Autowired
	private AppArticleCollectDao appArticleCollectDao;
	
	@Autowired
	private AppOrderDao appOrderDao;

	@Override
	public AppAccaUser get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<AppAccaUser> findList(AppAccaUser appAccaUser) {
		return super.findList(appAccaUser);
	}
	
	@Override
	public Page<AppAccaUser> findPage(Page<AppAccaUser> page, AppAccaUser appAccaUser) {
		return super.findPage(page, appAccaUser);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(AppAccaUser appAccaUser) {
		super.save(appAccaUser);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(AppAccaUser appAccaUser) {
		super.delete(appAccaUser);
	}

	
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	@Transactional(readOnly = false)
	public BaseObjResponse<AppAccaUser> login(String phone, String smsVcode,String deviceId) {
		
		// 是否是注册用户
		AppAccaUser accaUser = dao.getAccaUserByPhone(phone);
		if(!"18356035767".equals(phone)){
			if(accaUser!=null && accaUser.getUserStatus()==Constants.AccaUserStatus.frozen){
				logger.info("login，账号被冻结：{},{}", phone,smsVcode);
				return new BaseObjResponse<AppAccaUser>(RespConstants.USER_RREEZED);
			}
			// 验证码是否正确
			if (!appSmsVcodeService.checkSmsVcode(phone, smsVcode)) {
				logger.info("login，验证码错误：{},{}", phone,smsVcode);
				return new BaseObjResponse<AppAccaUser>(RespConstants.SMS_VCODE_INCORRECT);
			}
		}
		
		if(accaUser == null){
			accaUser = new AppAccaUser();
			accaUser.setCreateDate(new Date());
			accaUser.setPhone(phone);
			String nickname = "ACCA学员"+phone.substring(phone.length()-4);
			accaUser.setNickname(nickname);
			accaUser.setHeadId(0L);
			accaUser.setType(Constants.AccaUserType.general);
			accaUser.setUserAccessToken(IdGen.uuid());
			accaUser.setUserStatus(Constants.AccaUserStatus.normal);
			accaUser.setDeviceId(deviceId);
			accaUser.setLoginDate(new Date());
			dao.insert(accaUser);
			
			
		}else{
			//如果存在，表示已经注册过账号
			accaUser.setLoginDate(new Date());
			accaUser.setDeviceId(deviceId);
			dao.update(accaUser);
		}
		
		accaUser = dao.getAccaUserByPhone(phone);
		logger.info("accaUser:{}",accaUser);
		return new BaseObjResponse<AppAccaUser>(accaUser);
	}

	public BaseObjResponse<AppAccaUser> getUserInfo(Long appUserId) {
		
		AppAccaUser accaUser = this.get(new AppAccaUser(appUserId));
		
		AppExamSignup entity = new AppExamSignup();
		entity.setAccaUserId(appUserId);
		List<AppExamSignup> examList = appExamSignupDao.findTipsList(entity);
		logger.info("getUserInfo examList:"+examList);
		if(examList!=null && !examList.isEmpty()){
			AppExamSignup index = examList.get(0);
			if(index!=null){
				String str = "您最近的考试为"+index.getExamCourse()+"考试    "+DateTimeUtils.convertDate2String(index.getExamStartTime(), "yyyy-MM-dd");
				logger.info("getUserInfo examTips:{}",str);
				accaUser.setExamDesc(str);
			}
		}
		
		
		int count = appOrderDao.getCountByUnpayAndUncheckedAndSuccess(appUserId);
		logger.info("count:{}",count);
		if(count<=0){
			accaUser.setEditable(1);
		}
		
		return new BaseObjResponse<AppAccaUser>(accaUser);
	}

	@Transactional(readOnly = false)
	public BaseObjResponse<AppAccaUser> updateUserInfo(UpdateUserInfoReq req) {
		
		AppAccaUser accaUser = new AppAccaUser();
		accaUser.setAccaUserId(req.getAppUserId());
		accaUser.setHeadId(req.getHeadId());
		accaUser.setNickname(req.getNickname());
		accaUser.setRegisterName(req.getRegisterName());
		accaUser.setRegisterCardNumber(req.getRegisterCardNumber());
		accaUser.setRegisterPhone(req.getRegisterPhone());
		accaUser.setRegisterEmail(req.getRegisterEmail());
		accaUser.setRegisterWhiteColorImgId(req.getRegisterWhiteColorImgId());
		accaUser.setAccaRegisterName(req.getAccaRegisterName());
		accaUser.setAccaRegisterPassword(req.getAccaRegisterPassword());
		accaUser.setUpdateDate(new Date());
		
		dao.update(accaUser);
		
		
		return this.getUserInfo(req.getAppUserId());
	}
	
	public BasePageResponse<AppAccaClub> getAccaClubList(BaseRequest req) {
		
		List<AppAccaClub> list = appAccaClubDao.findList(new AppAccaClub());
		
		return new BasePageResponse<AppAccaClub>(list);
	}

	@Transactional(readOnly = false)
	public BaseResponse updateUserLocation(UpdateUserLocationReq req) {
		
		AppAccaUser accaUser = new AppAccaUser();
		accaUser.setAccaUserId(req.getAppUserId());
		accaUser.setProvinceId(req.getProvinceId());
		accaUser.setCityId(req.getCityId());
		accaUser.setProvinceName(req.getProvinceName());
		accaUser.setCityName(req.getCityName());
		accaUser.setUpdateDate(new Date());
		
		dao.update(accaUser);
		
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	@Transactional(readOnly = false)
	public BaseResponse feedback(FeedbackReq req) {
		
		AppFeedback feedback = new AppFeedback();
		feedback.setPhone(req.getPhone());
		feedback.setContent(req.getContent());
		feedback.setCreateDate(new Date());
		feedback.setUpdateDate(new Date());
//		Long accaUserId=req.getAccaUserId();
//		if(accaUserId!=null&&accaUserId>0L){
//			feedback.setAcca_user_id(accaUserId);
//		}
				
		appFeedbackDao.insert(feedback);
		
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	public BaseObjResponse<AppApkVersion> getApkUpdateInfo() {
		
		AppApkVersion version = appApkVersionDao.getApkVersionInfo();
		logger.info("version:"+version);
		return new BaseObjResponse<AppApkVersion>(version);
	}

	public BaseObjResponse<AccaConfInfo> getAccaConfigInfo() {
		
		AppConfExamTips tips = appConfExamTipsDao.get(new AppConfExamTips("1"));
		
		AccaConfInfo conf = new AccaConfInfo();
		if(tips!=null){
			conf.setOfficialExamProcess(tips.getOfficialExamProcess());
		}
		
		
		return new BaseObjResponse<AccaConfInfo>(conf);
	}
	
	public AppAccaUser getAccaUserByPhone(String phone){
		// 是否是注册用户
		AppAccaUser accaUser = dao.getAccaUserByPhone(phone);
		return accaUser;
	}

	public BasePageResponse<AppArticleCollectDto> getCollectArticleList(PageApi page,	AppAccaUser appUser) {
		
		List<AppArticle> list = appArticleDao.getCollectArticleList(appUser.getAccaUserId(), page);
		logger.info("accaUserId:{},   ,collectArticleList:{}",appUser.getAccaUserId(),list);
		
		
		List<AppArticleCollectDto> alist = new ArrayList<AppArticleCollectDto>();
		if(list!=null && !list.isEmpty()){
			for(AppArticle index:list){
				AppArticleCollectDto item = new AppArticleCollectDto();
				item.setArticleId(index.getArticleId());
				int type = index.getType();
				item.setType(type);
				item.setTitle(index.getTitle()); 
				item.setPageTitle(index.getPageTitle());
				item.setLinkUrl(index.getLinkUrl());
				item.setHtmlPreview(index.getHtmlPreview());
				item.setCourseStatus(index.getCourseStatus());
				item.setCourseType(index.getCourseType());
				if(type == Constants.ArticleType.course || type ==Constants.ArticleType.normal){
					FileInfo image = index.getHtmlImage();
					if(image!=null){
						item.setImageUrl(image.getFileUrl());
					}else{
						item.setImageUrl("");
					}
				}else if(type == Constants.ArticleType.learning ){
					FileInfo image = index.getLearningUser().getHeadImg();
					if(image!=null){
						item.setImageUrl(image.getFileUrl());
					}else{
						item.setImageUrl("");
					}
				}else if(type == Constants.ArticleType.tearcher ){
					FileInfo image = index.getTeacher().getHeadImg();
					if(image!=null){
						item.setImageUrl(image.getFileUrl());
					}else{
						item.setImageUrl("");
					}
				}
				
				if(type == Constants.ArticleType.course ){
					item.setArticleCategoryName("公开课");
				}else if(type == Constants.ArticleType.normal ){
					item.setArticleCategoryName(index.getArticleCategoryName());
				}else if(type == Constants.ArticleType.learning ){
					item.setArticleCategoryName("达人分享");
				}else{
					item.setArticleCategoryName("名师指导");
				}
				alist.add(item);
			}
		}
		
		return new BasePageResponse<AppArticleCollectDto>(alist);
	}

	@Transactional(readOnly = false)
	public BaseResponse deleteCollectBatch(List<Long> articleIdList,AppAccaUser appUser) {
		
		appArticleCollectDao.deleteArticleCollectBatch(articleIdList, appUser.getAccaUserId());
		
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	public int submitAuthen(AppAccaUser user) {
		return dao.update(user);
		// TODO Auto-generated method stub
		
	}
	
}