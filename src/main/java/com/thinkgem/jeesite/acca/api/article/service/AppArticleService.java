/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.article.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.utils.http.ZBGUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.acca.api.article.dao.AppActivityLogDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppArticleCategoryDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppArticleCollectDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppArticleCommentDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppArticleDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppArticlePraiseDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppAwardDao;
import com.thinkgem.jeesite.acca.api.article.dao.AppTeacherDao;
import com.thinkgem.jeesite.acca.api.article.entity.AppActivityLog;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticle;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleCategory;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleCollect;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticleComment;
import com.thinkgem.jeesite.acca.api.article.entity.AppArticlePraise;
import com.thinkgem.jeesite.acca.api.article.entity.AppAward;
import com.thinkgem.jeesite.acca.api.article.entity.AppTeacher;
import com.thinkgem.jeesite.acca.api.article.entity.Lottery;
import com.thinkgem.jeesite.acca.api.model.request.GetArticleListByCategoryReq;
import com.thinkgem.jeesite.acca.api.model.request.GetArticleListByTitleAndCategoryReq;
import com.thinkgem.jeesite.acca.api.model.request.GetArticleListReq;
import com.thinkgem.jeesite.acca.api.model.request.GetTeacherInfoReq;
import com.thinkgem.jeesite.acca.api.model.response.GetTeacherInfoResp;
import com.thinkgem.jeesite.acca.api.model.response.SubmitAwardResp;
import com.thinkgem.jeesite.acca.api.model.response.SubmitCommentResp;
import com.thinkgem.jeesite.acca.api.plan.dao.AppUserLearningPlanDao;
import com.thinkgem.jeesite.acca.api.plan.entity.AppUserLearningPlan;
import com.thinkgem.jeesite.acca.api.register.dao.AppExamOpenCityDao;
import com.thinkgem.jeesite.acca.api.register.entity.AppExamOpenCity;
import com.thinkgem.jeesite.acca.api.register.service.AppAccaRegisterService;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.service.AppAccaUserService;
import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.HttpUrlConnectionUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageRequest;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import com.thinkgem.jeesite.freetek.api.model.PageApi;
import com.thinkgem.jeesite.freetek.util.TextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


/**
 * APP 通用接口 Service
 * @author Ivan
 * @version 2016-07-25
 */
@Service
@Transactional(readOnly = true)
public class AppArticleService  extends BaseService{

	@Autowired
	private AppArticleCategoryDao appArticleCategoryDao;
	
	@Autowired
	private AppArticleDao appArticleDao;
	
	@Autowired
	private AppTeacherDao appTeacherDao;
	
	@Autowired
	private AppUserLearningPlanDao appUserLearningPlanDao;
	
	@Autowired
	private AppArticleCollectDao appArticleCollectDao;
	
	@Autowired
	private AppArticlePraiseDao appArticlePraiseDao;
	
	@Autowired
	private AppArticleCommentDao appArticleCommentDao;
	
	@Autowired
	private AppAwardDao appAwardDao;
	
	@Autowired
	private AppActivityLogDao appActivityLogDao;
	
	@Autowired
	private AppAccaUserService appAccaUserService;
	@Autowired
	AppExamOpenCityDao examOpenCityDao;

	public BasePageResponse<AppArticleCategory> getArticleCategoryList(AppArticleCategory entity) {

		List<AppArticleCategory> list = appArticleCategoryDao.findAllList(entity);
		logger.info("appArticleCategoryList:"+list);
		
		return new BasePageResponse<AppArticleCategory>(list);
	}

	public BasePageResponse<AppArticle> getArticleList(GetArticleListReq req) {
		
		AppArticle article = new AppArticle();
		article.setType(req.getType());
		article.setPageApi(req.getPage());
		
		List<AppArticle> list = appArticleDao.getListByLearningAndTearcher(article);
		logger.info("getListByLearningAndTearcher list:"+list);
		
		return new BasePageResponse<AppArticle>(list);
	}


	public BasePageResponse<AppArticle> getArticleListByCategory(GetArticleListByCategoryReq req) {
		
		AppArticle article = new AppArticle();
		article.setArticleCategoryId(req.getArticleCategoryId());
		article.setPageApi(req.getPage());
		
		List<AppArticle> list = appArticleDao.getListByArticleCategoryId(article);
		logger.info("getArticleListByCategory list:"+list);
		
		return new BasePageResponse<AppArticle>(list);
	}
	
	public BasePageResponse<AppArticle> getArticleListByTitleAndCategory(GetArticleListByTitleAndCategoryReq req) {
		if (StringUtils.isEmpty(req.getTitle())) {
			return new BasePageResponse<>(RespConstants.GLOBAL_SUCCESS);
		}
		AppArticle article = new AppArticle();
		article.setArticleCategoryId(req.getArticleCategoryId());
		article.setTitle(req.getTitle());
		article.setPageApi(req.getPage());
		
		List<AppArticle> list = appArticleDao.getListByTitleAndCategoryId(article);
		logger.info("getListByTitleAndCategoryId list:"+list);
		
		return new BasePageResponse<AppArticle>(list);
	}


	@Transactional(readOnly = false)
	public String detail(Long accaUserId,AppArticle article,Model model) {
		logger.info("accaUserId:"+accaUserId);
		
		AppArticle entity = appArticleDao.get(article);
		logger.info("article:"+entity);
		if(entity==null){
			return "error/404.jsp";
		}		
		entity.setViewNum(entity.getViewNum()+1);		
		logger.info("viewNum:"+entity.getViewNum());		
		appArticleDao.updateViewNum(entity);
		
		boolean collectFlag = false;//是否收藏
		if(accaUserId!=0L && accaUserId!=-1L){
			AppArticleCollect collect = appArticleCollectDao.getByUserIdAndAticleId(accaUserId, article.getArticleId());
			if(collect!=null){
				collectFlag = true;
			}
		}
		
		boolean praiseFlag = false;//是否点赞
		if(accaUserId!=0L && accaUserId!=-1L){
			AppArticlePraise praise = appArticlePraiseDao.getByUserIdAndArticleId(accaUserId, article.getArticleId());
			logger.info("AppArticlePraise:"+praise);
			if(praise!=null){
				praiseFlag = true;
			}
		}
		
		
		long commentNum = appArticleCommentDao.getCommentNumByArticleId(article.getArticleId());
		entity.setCommentNum(commentNum);
		
		model.addAttribute("article",entity);
		model.addAttribute("accaUserId",accaUserId);
		
		if(entity.getArticleId()==66){
			AppAccaUser accaUser = appAccaUserService.get(accaUserId.toString());
			model.addAttribute("accauser",accaUser);
			AppExamOpenCity examOpenCity = new AppExamOpenCity();
			//默认为8中博诚通分部8
			examOpenCity.setExamType(ApiConstant.EXAM_TYPE_ZHONGCHENG);
			List<AppExamOpenCity> citylist = examOpenCityDao.findList(examOpenCity);
			model.addAllAttributes(citylist);
			return "api/article/sa";// "api/article/studentAuthor";
		}
		
		model.addAttribute("collectFlag",collectFlag);
		model.addAttribute("praiseFlag",praiseFlag);
		
		if(entity.getType()==Constants.ArticleType.course){			
			if(entity.getCourseType()==1){
				return "api/article/articlecourse";
			}else if(entity.getCourseType()==2){
				return "api/article/articlecourse";
			}else{
				return "api/article/articlecoursevideo";
			}
			//return "api/article/date";
		}else if(entity.getType()==Constants.ArticleType.tearcher){
			return "api/article/articleteacher";
			//return "api/article/date";
		}else if(entity.getType()==Constants.ArticleType.learning){
			return "api/article/articlelearning";
		}else if(entity.getType()==Constants.ArticleType.h5){
			return "api/article/h5";
		}else {
			return "api/article/article";
		}		
	}
	

	public GetTeacherInfoResp getTeacherInfo(GetTeacherInfoReq req) {
		
		Long teacherId = req.getTeacherId();
		
		AppTeacher teacher = appTeacherDao.get(new AppTeacher(teacherId));
		logger.info("getTeacherInfo teacher:"+teacher);
		if(teacher==null){
			return new GetTeacherInfoResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		
		
		AppArticle article = new AppArticle();
		article.setType(Constants.ArticleType.tearcher);
		article.setTeacherId(teacherId);
		article.setPageApi(req.getPage());
		
		List<AppArticle> list = appArticleDao.getListByLearningAndTearcher(article);
		logger.info("getTeacherInfo getListByLearningAndTearcher:"+list);
		
		return new GetTeacherInfoResp(teacher, list);
	}


	public BasePageResponse<AppArticle> getHomeArticleList(BasePageRequest req) {
		Long accaUserId = req.getAppUserId();
		List<Long> examCourseIdList = new ArrayList<Long>();
		if(accaUserId==null || accaUserId==0){
			//表示未登录用户，应该获取前导分类的文章
			examCourseIdList.add(-1L);
		}else{
			//根据用户当前正在学习的计划，获取指定的examCourseId
			List<AppUserLearningPlan> list = appUserLearningPlanDao.getLearningPlanListByNow(accaUserId);
			logger.info("accaUserId:{},  ..List<AppUserLearningPlan>:{}",accaUserId,list);
			if(list==null || list.isEmpty()){
				examCourseIdList.add(-1L);
			}else{
				for(AppUserLearningPlan index:list){
					examCourseIdList.add(index.getCourseId());
				}
				
				
			}
		}
		//为了暂时的模拟数据
		//examCourseIdList.add(1L);
		//examCourseIdList.add(2L);
		//examCourseIdList.add(3L);
		
		logger.info("examCourseIdList:{}"+examCourseIdList);
		
		List<AppArticle> list = appArticleDao.getListByExamCourseList(examCourseIdList, req.getPage());
		//logger.info("getHomeArticleList list:"+list);
		
		return new BasePageResponse<AppArticle>(list);
		
	}


	/**
	 * 
	 * @param articleId
	 * @param accaUserId
	 * @param praiseType 1:取消点赞（当前处于点赞状态），2点赞（当前处于未点赞状态）
	 * @return
	 */
	@Transactional(readOnly = false)
	public BaseObjResponse<String> praise(Long articleId, Long accaUserId,Integer praiseType) {
		if(articleId==null || articleId==0L){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(accaUserId==null || accaUserId==0L || accaUserId==-1L){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(praiseType==null || praiseType==0){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(praiseType!=1 && praiseType!=2){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		AppArticlePraise praise = appArticlePraiseDao.getByUserIdAndArticleId(accaUserId,articleId);
		if(praiseType==1){
			//取消点赞
			if(praise!=null){
				appArticlePraiseDao.deleteByAccaUserIdAndArticleId(accaUserId, articleId);
			}
		}else{
			//点赞
			if(praise==null){
				praise = new AppArticlePraise();
				praise.setAccaUserId(accaUserId);
				praise.setArticleId(articleId);
				praise.setCreateDate(new Date());
				appArticlePraiseDao.insert(praise);
			}	
		}
		Long praiseNum = appArticlePraiseDao.getPraiseCountByArticleId(articleId);
		String praiseNumStr = TextUtils.formatViewNum(praiseNum);
		
		return new BaseObjResponse<String>(praiseNumStr);
	}

	/**
	 * 
	 * @param articleId
	 * @param accaUserId
	 * @param collectType 1:取消收藏（当前处于收藏状态），2收藏（当前处于未收藏状态）
	 * @return
	 */
	@Transactional(readOnly = false)
	public BaseResponse collect(Long articleId, Long accaUserId,Integer collectType) {
		if(articleId==null || articleId==0L){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(accaUserId==null || accaUserId==0L || accaUserId==-1L){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(collectType==null || collectType==0){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(collectType!=1 && collectType!=2){
			return new BaseObjResponse<String>(RespConstants.GLOBAL_PARAM_ERROR);
		}
		
		AppArticleCollect collect = appArticleCollectDao.getByUserIdAndAticleId(accaUserId, articleId);
		if(collectType==1){
			//取消收藏
			if(collect!=null){
				List<Long> list = new ArrayList<Long>();
				list.add(articleId);
				appArticleCollectDao.deleteArticleCollectBatch(list, accaUserId);
			}
		}else{
			//收藏
			if(collect==null){
				collect = new AppArticleCollect();
				collect.setAccaUserId(accaUserId);
				collect.setArticleId(articleId);
				appArticleCollectDao.insert(collect);
			}
		}
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}


	public BasePageResponse<AppArticleComment> getCommentList(Long articleId, PageApi page) {
		
		logger.info("articleId:{},   ,page:{}",articleId,page);
		if(articleId==null || articleId==0L){
			return new BasePageResponse<AppArticleComment>(RespConstants.GLOBAL_PARAM_ERROR);
		}

		
		List<AppArticleComment> list = appArticleCommentDao.getListByArticleId(articleId,page);
		logger.info("AppArticleCommentList:"+list);
		
		
		return new BasePageResponse<AppArticleComment>(list);
	}


	@Transactional(readOnly = false)
	public SubmitCommentResp submitComment(AppArticleComment req) {
		Long accaUserId = req.getAccaUserId();
		String content = req.getContent();
		Long articleId = req.getArticleId();
		Long parentId = req.getParentId();
		if(accaUserId==null || accaUserId<=0L){
			return new SubmitCommentResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(articleId==null || articleId<=0L){
			return new SubmitCommentResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(StringUtils.isEmpty(content) || StringUtils.isEmpty(content.trim())){
			return new SubmitCommentResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		if(StringUtils.isEmpty(content.trim())){
			return new SubmitCommentResp(RespConstants.GLOBAL_PARAM_ERROR,"评论不能只输入空格！");
		}
		content = content.replace("&nbsp;", " ");		
		if(content.length()>300){
			return new SubmitCommentResp(RespConstants.GLOBAL_PARAM_ERROR,"评论不允许超过300字！");
		}
		logger.info("content:"+content);
		req.setCreateDate(new Date());
		appArticleCommentDao.insert(req);
		logger.info("{}",req.getArticleCommentId());
		
		AppArticleComment comment = appArticleCommentDao.getByArticleCommentId(req.getArticleCommentId());
		
		long commentNum = appArticleCommentDao.getCommentNumByArticleId(articleId);
		
		String commentNumStr = TextUtils.formatViewNum(commentNum);
		
		return new SubmitCommentResp(comment,commentNumStr);
	}
	

	@Transactional(readOnly = false)
	public SubmitAwardResp submitAward(Long activityId,String phone) {
		// TODO Auto-generated method stub		
		AppAccaUser accaUser = appAccaUserService.getAccaUserByPhone(phone);
		if(accaUser==null){
			return new SubmitAwardResp(RespConstants.USER_NONEXIST);
		}
		if(getUserAwardNum(activityId,phone)==0L){
			return new SubmitAwardResp(RespConstants.GLOBAL_OTHER,"您今天的抽奖机会已经用尽，分享本活动获得更多抽奖机会");
		}
		Lottery lt=BigWheelDrawUtil.generateAward();		
		AppAward aa=new AppAward();	
		aa.setContent(lt.getName());		
		if(lt.getId()==1 ){ /*&& (appAwardDao.getCountByContent("CMA中文全科U+网课套餐") >= 1 || appAwardDao.getCountTodayByContent("CMA中文全科U+网课套餐") >= 1)){*/
			lt= new Lottery(7L,2000,"2017年4月CMA P1冲刺串讲", 2000,2L,1L,"实物奖品","8a22ecb55b1ec7e9015b3be9e0bd03a6");
		}else if(lt.getId()==2 && (appAwardDao.getCountByContent("单科中文CMA U+网课套餐") >= 4 || appAwardDao.getCountTodayByContent("单科中文CMA U+网课套餐") >= 1)){
			lt= new Lottery(7L,2000,"2017年4月CMA P1冲刺串讲", 2000,2L,1L,"实物奖品","8a22ecb55b1ec7e9015b3be9e0bd03a6");
		}else if(lt.getId()==3 && (appAwardDao.getCountByContent("IPad mini 4") >= 1 || appAwardDao.getCountTodayByContent("IPad mini 4") >= 1)){
			lt= new Lottery(7L,2000,"2017年4月CMA P1冲刺串讲", 2000,2L,1L,"实物奖品","8a22ecb55b1ec7e9015b3be9e0bd03a6");
		}else if(lt.getId()==4 && (appAwardDao.getCountByContent("一步通关课程代金券 价值2500元") >= 30 || appAwardDao.getCountTodayByContent("一步通关课程代金券 价值2500元") >= 3)){
			lt= new Lottery(7L,2000,"2017年4月CMA P1冲刺串讲", 2000,2L,1L,"实物奖品","8a22ecb55b1ec7e9015b3be9e0bd03a6");
		}else if(lt.getId()==5 && (appAwardDao.getCountByContent("CMA U+网课代金券 价值2000元") >= 500 || appAwardDao.getCountTodayByContent("CMA U+网课代金券 价值2000元") >= 50)){
			lt= new Lottery(7L,2000,"2017年4月CMA P1冲刺串讲", 2000,2L,1L,"实物奖品","8a22ecb55b1ec7e9015b3be9e0bd03a6");
		}else if(lt.getId()==5 && (appAwardDao.getCountByContent("单科中文CMA串讲课 价值1900元") >= 300 || appAwardDao.getCountTodayByContent("单科中文CMA串讲课 价值1900元") >= 30)){
			lt= new Lottery(7L,2000,"2017年4月CMA P1冲刺串讲", 2000,2L,1L,"实物奖品","8a22ecb55b1ec7e9015b3be9e0bd03a6");
		}
		
		
		aa.setAccaUserId(accaUser.getAccaUserId());
		aa.setActivityId(lt.getActivityId());
		aa.setAwardCategoryId(lt.getAwardCategoryId());
		aa.setAwardCategoryName(lt.getAwardCategoryName());
		aa.setAwardStatus(1);
		aa.setCardRuleId(lt.getCardRuleId());
		aa.setContent(lt.getName());
		aa.setCreateDate(new Date());
		aa.setUser(accaUser);
		appAwardDao.insert(aa);	
		if(!"".equals(aa.getCardRuleId())){
			this.setAwardToCAicui(phone,aa.getCardRuleId());
		}
		return new SubmitAwardResp(aa);
	}
	
	public BasePageResponse<AppAward> getAwardList(Long activeId) {		
		List<AppAward> list = appAwardDao.getLAwardList(activeId);	
		System.out.println(list);
		return new BasePageResponse<AppAward>(list);		
	}
	
	public BasePageResponse<AppAward> getAwardListByuser( Long activeId , String phone ) {		
		List<AppAward> list = appAwardDao.getLAwardListByPhone(activeId,phone);		
		return new BasePageResponse<AppAward>(list);		
	}
	
	public String setAwardToCAicui(String phone, String string) {
		String t= ZBGUtils.getToken();
		String regActiveID = "0";
		String regActiveType = "4";
		String nickName = "ACCAHelper"+phone;
		String password = phone.substring(phone.length()-6,phone.length());
		String email = phone.substring(phone.length()-6,phone.length())+"@ACCAHelper.com";
		String name = "小助手"+phone.substring(phone.length()-6,phone.length());
		String gender = "1";
		String areaPath = "-";
		String address = "-";
		String mobile = phone;
		String identityType = "2";
		System.out.println(addCaicuiUser(t,regActiveID,regActiveType,nickName,password,email,
				name,gender,areaPath,address,mobile,identityType));
		return AddCoupon(t,phone,string);
	}


//	public static String getToken(){
//		String url="http://api.caicui.com/api/zbids/app/gettoken/v1.0/";
//		String requestMethod="POST";
//		Map<String, String> params= new HashMap<String, String>();
//		params.put("appType","accahelperserver");
//		params.put("appId","accahelper");
//		params.put("appKey","33AF7273FB74052AB2B03CCFC7E97D93");
//		JSONObject t =(JSONObject) JSONObject.parse(HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params));
//		JSONObject v = (JSONObject) t.get("data");
//		String s=(String) v.get("token");
//		return s;
//	}
	
//	public static String getCaicuiUser(String token,String code){
//		String url="http://api.caicui.com/api/zbids/member/detail/v1.0";
//		String requestMethod="GET";
//		Map<String, String> params= new HashMap<String, String>();
//		params.put("code",code);
//		params.put("type","2");
//		params.put("token",token);
//		String u= HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
//		return u;
//	}
	
	public static String addCaicuiUser(String token,String regActiveID,String regActiveType,String nickName,String password,String email,
								String name,String gender,String areaPath,String address,String mobile,String identityType){
		String url="http://api.zbgedu.com/api/zbids/ib/regmember/v1.0";
		String requestMethod="POST";
		Map<String, String> params= new HashMap<String, String>();
		params.put("token",token);
		params.put("regActiveID",regActiveID);
		params.put("regActiveType",regActiveType);
		params.put("nickName",nickName);
		params.put("password",password);
		params.put("email",email);
		params.put("name",name);
		params.put("gender",gender);
		params.put("areaPath",areaPath);
		params.put("address",address);
		params.put("mobile",mobile);
		params.put("identityType",identityType);		
		String u= HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
		return u;
	}
	
	public static String getUserCoupon(String phone){
		String url="http://api.zbgedu.com/api/business/coupon/usercoupon";
		String requestMethod="GET";
		Map<String, String> params= new HashMap<String, String>();
		params.put("code",phone);
		params.put("type","2");	
		String u= HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
		return u;
	}
	
	public static String AddCoupon(String token,String phone,String ruleId){
		String url="http://api.zbgedu.com/api/business/coupon/create/v1.0";
		String requestMethod="GET";
		Map<String, String> params= new HashMap<String, String>();
		params.put("phone",phone);
		params.put("ruleId",ruleId);
		params.put("token",token);		
		String u= HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
		return u;
	}
		
	
	public static void main(String args[]){		
		Lottery lt=BigWheelDrawUtil.generateAward();		
		System.out.print(lt.getName());
		
	}


	public Long getUserAwardNum(Long activityId,String phone) {
		// TODO Auto-generated method stub
		//Long n =  appAwardDao.getUserAwardNum(phone);
		//
		//Long activityId=1L;
		Long used = appAwardDao.getCountTodayByPhone(activityId,phone);
		System.out.println("used:"+used);		
		Long shareed = appActivityLogDao.getCountTodayByPhone(phone,activityId);
		System.out.println("shareed:"+shareed);
		if(shareed>2){
			shareed=2L;
		}
		System.out.println("shareed:"+shareed);
		if(used>=3)
		{
			return 0L;
		}
		System.out.println("end:"+(1+shareed-used));
		return 1+shareed-used;
	}	
	
	public Long getAddtionNum(String phone,String activeId){
		Long n =  appAwardDao.getUserAddtionNum(phone,activeId);
		return n;
	}

	@Transactional(readOnly = false)
	public String addActiveLog(Long activeId, String phone) {
		AppAccaUser accaUser = appAccaUserService.getAccaUserByPhone(phone);
		if(accaUser==null){
			return "{\"respCode\":0,\"respDesc\":\"用户未注册！\"}";
		}	
		AppActivityLog aa=new AppActivityLog();	
		aa.setAccaUserId(accaUser.getAccaUserId());
		aa.setActivityId(activeId);
		aa.setCreateDate(new Date());
		appActivityLogDao.insert(aa);
		return "{\"respCode\":1,\"respDesc\":\"保存成功！\"}";
	}
	
	@Transactional(readOnly = false)
	public String submitAuthen(AppAccaUser req) {
		if(appAccaUserService.submitAuthen(req) == 1){
			return "审核提交成功，请等待机考老师审核！";
		}else{
			return "认证审核提交失败，请检查提交选项真实性！";
		}
	}
	
	
	
}