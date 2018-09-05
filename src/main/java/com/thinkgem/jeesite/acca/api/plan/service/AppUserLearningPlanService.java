package com.thinkgem.jeesite.acca.api.plan.service;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.acca.api.article.entity.Lottery;
import com.thinkgem.jeesite.acca.api.article.service.BigWheelDrawUtil;
import com.thinkgem.jeesite.acca.api.home.dao.AppTipsDao;
import com.thinkgem.jeesite.acca.api.home.entity.AppTips;
import com.thinkgem.jeesite.acca.api.model.request.*;
import com.thinkgem.jeesite.acca.api.model.response.GetLearningPlanResultResp;
import com.thinkgem.jeesite.acca.api.model.response.LearningPlanInfo;
import com.thinkgem.jeesite.acca.api.model.response.LearningTipsResp;
import com.thinkgem.jeesite.acca.api.plan.dao.AppAccaEnglishWordDao;
import com.thinkgem.jeesite.acca.api.plan.dao.AppExamCourseDao;
import com.thinkgem.jeesite.acca.api.plan.dao.AppUserLearningPlanDao;
import com.thinkgem.jeesite.acca.api.plan.entity.AppAccaEnglishWord;
import com.thinkgem.jeesite.acca.api.plan.entity.AppExamCourse;
import com.thinkgem.jeesite.acca.api.plan.entity.AppUserLearningPlan;
import com.thinkgem.jeesite.acca.api.plan.entity.PlanTips;
import com.thinkgem.jeesite.acca.api.plan.utils.LearningPlanComparator;
import com.thinkgem.jeesite.acca.api.user.dao.AppAccaUserDao;
import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.exam.dao.WebExamSignupDao;
import com.thinkgem.jeesite.acca.web.exam.dao.WebOrderDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebOrder;
import com.thinkgem.jeesite.acca.web.exam.entity.WebSignup;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 学习计划Service
 * @author Young
 * @version 2016-08-10
 */
@Service
@Transactional(readOnly = true)
public class AppUserLearningPlanService extends CrudService<AppUserLearningPlanDao, AppUserLearningPlan> {

	@Autowired
	private AppAccaEnglishWordDao appAccaEnglishWordDao;
	@Autowired
	private AppExamCourseDao appExamCourseDao;
	@Autowired
	private WebExamSignupDao examSignupDao;
	@Autowired
	private AppTipsDao tipsDao;
	@Autowired
	private WebOrderDao orderDao;
	@Autowired
	private WebMsgSysService msgSysService;
	@Autowired
	private AppAccaUserDao userDao;
	/**
	 * 获取ACCA财经词汇列表
	 * @param req
	 * @return
     */
	public BasePageResponse<AppAccaEnglishWord> getAccaEnglishWordList(BaseRequest req) {
		List<AppAccaEnglishWord> englishWordList = appAccaEnglishWordDao.findList(new AppAccaEnglishWord());
		logger.info("用户{}获取ACCA财经词汇列表{}成功!", req.getAppUserId(), englishWordList);
		return new BasePageResponse<AppAccaEnglishWord>(englishWordList);
	}
	
	public String getAccaWordList() {
		// TODO Auto-generated method stub
		AppAccaEnglishWord awd = new AppAccaEnglishWord();
		awd.setCertificate("ACCA");
		List<AppAccaEnglishWord> englishWordList = appAccaEnglishWordDao.findList(awd);
		JSONObject json = new JSONObject();
		String[] alphatableb =  
		{  
		               "A", "B", "C", "D", "E", "F", "G", "H", "I",  
		               "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"  
		};  
		ArrayList wordlist=new ArrayList();  
		for(String a:alphatableb){
			ArrayList words=new ArrayList();  
			for(AppAccaEnglishWord aed:englishWordList){//为了排序都返回大写字母  
				if(a.equals(aed.getName().substring(0,1).toUpperCase())){  
					JSONObject word = new JSONObject();
					word.put("word", aed.getName());
					word.put("cword", aed.getChineseName());
					words.add(word);
				}
			}
			if(words.size()>0){
				JSONObject cap = new JSONObject();
				cap.put("cap", a);
				cap.put("words", words);
				wordlist.add(cap);
			}
		}
		json.put("wordslist", wordlist);
		return json.toJSONString();
	}
	
	public String getAccaWordDetail(String w) {
		// TODO Auto-generated method stub
		AppAccaEnglishWord wd = appAccaEnglishWordDao.getByName(w);
		JSONObject word= new JSONObject();		
		if(wd!=null){
			word.put("word",wd.getName());
			word.put("word_mp3",wd.getNameurl());
			word.put("word_ch", wd.getChineseName());
			word.put("symbol", wd.getYinbiao());
			word.put("symbol_mp3", wd.getYinbiaourl());
			word.put("gloze",wd.getEndescription());
			word.put("gloze_cn",wd.getDescription());
			word.put("gloze_mp3", wd.getEndescriptionurl());
			word.put("memory",wd.getJiyifangfa());
			word.put("memory_mp3",wd.getJiyifangfaurl());
		}
		return word.toJSONString();
	}
	
	
	/**
	 * 获取单词的详细信息
	 * @param id
	 * @return
     */
	public AppAccaEnglishWord getWordDesc(String id) {
		return appAccaEnglishWordDao.get(id);
	}

	/**
	 * 查询用户的报考信息,进行规划,再按照考试的信息对规划进行修改
	 * 提交学习计划,进行规划
	 * 1.先查询规划列表,看是否已经有过规划了
	 * 2.有规划,先删除以前的规划,再重新按规则创建;没有则直接创建
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BasePageResponse<AppUserLearningPlan> submitLearningPlan(SubmitLearningPlanReq req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppUserLearningPlan>(req.isCorrectParams());
		}
		Long accaUserId = req.getAppUserId();
		//获取用户的时间在现在之后的,并且审核通过的报考信息
		WebSignup condition = new WebSignup();
		condition.setAccaUserId(accaUserId);
		condition.setExamSignupStatus(Constants.OrderStatus.checkSuccess);
		List<WebSignup> signups = examSignupDao.findMyList(condition);
		//先根据登录的用户id获取学习计划列表
		List<AppUserLearningPlan> learningPlanList = this.getPlanList(req);
		//判断学习计划列表是否为空,如果不为空,则删除之前的列表
		if (learningPlanList != null && learningPlanList.size() > 0){
			for (AppUserLearningPlan plan:learningPlanList){
				dao.delete(plan);
			}
			logger.info("用户{}删除原有的学习计划列表{}成功!", accaUserId, learningPlanList);
		}

		//TODO:获取是否有被删除的学习规划,用户下面是否发送优惠码的判断.如果没有被删除的,说明是第一次规划,推送优惠码
		/*AppUserLearningPlan delCondition = new AppUserLearningPlan();
		delCondition.setAccaUserId(accaUserId);
		Long delCount = dao.getDelPlanCount(delCondition);*/

		learningPlanList = this.generateLearningPlaningList(req);
		logger.info("用户{}规划学习计划列表{}成功!", accaUserId, learningPlanList);

		//TODO:推送给规划完成的用户优惠码,按时间的活动,到时间后会停用.活动已于10.16到期,活动停用
		/*if (delCount == 0){
			this.planActivity(accaUserId);
		}*/


		//判断考试列表是否为空,如果不为空,则对规划进行修改
		if (signups != null && signups.size() > 0){
			for (WebSignup signup:signups){
				this.updateLearningPlanWhenExam(accaUserId,signup,signup.getExamSignupStatus());
			}
		}
		return new BasePageResponse<AppUserLearningPlan>(learningPlanList);
	}

	/**
	 * 根据用户登录信息获取学习计划
	 * @param req
	 * @return
     */
	public BasePageResponse<AppUserLearningPlan> getLearningPlanList(BaseRequest req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppUserLearningPlan>(req.isCorrectParams());
		}
		AppUserLearningPlan condition = new AppUserLearningPlan();
		condition.setAccaUserId(req.getAppUserId());
		//根据登录的用户id获取学习计划列表
		List<AppUserLearningPlan> learningPlanList = dao.getPlanListOrderById(condition);
		return new BasePageResponse<AppUserLearningPlan>(learningPlanList);
	}

	/**
	 * 删除学习规划
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BaseResponse deleteLearningPlan(BaseRequest req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppUserLearningPlan>(req.isCorrectParams());
		}
		Long accaUserId = req.getAppUserId();
		//根据登录的用户id获取学习计划列表
		List<AppUserLearningPlan> learningPlanList = this.getPlanList(req);
		//判断学习计划列表是否为空,如果不为空,则删除之前的列表
		if (learningPlanList != null && learningPlanList.size() > 0){
			for (AppUserLearningPlan plan:learningPlanList){
				dao.delete(plan);
				//同时,删除提醒
				String courseName = plan.getCourseName();
				AppTips tips = new AppTips();
				tips.setTipType(ApiConstant.TIP_TYPE_PLAN);
				tips.setAccaUserId(accaUserId);
				tips.setCourse(courseName);
				tipsDao.delete(tips);
			}
			logger.info("用户{}删除原有的学习计划列表{}成功!", accaUserId, learningPlanList);
		}
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	/**
	 * 修改学习规划
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BaseObjResponse<AppUserLearningPlan> updateLearningPlan(UpdateLearningPlanReq req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new BaseObjResponse<AppUserLearningPlan>(req.isCorrectParams());
		}
		AppUserLearningPlan plan = dao.get(req.getLearningPlanId());
		Date startDate = new Date();
		Date endDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDateStr = req.getStartDate();
		String endDateStr = req.getEndDate();
		try {
			startDate = format.parse(startDateStr);
			endDate = format.parse(req.getEndDate());
			//判断开始时间和结束时间大小
			int daysBetween = DateTimeUtils.daysBetween(startDate, endDate);
			//判断开始时间与当前时间大小
			int days = DateTimeUtils.daysBetween(new Date(), startDate);
			//当开始时间大于结束时间或者开始时间小于当前时间时,提示错误
			if (daysBetween < 0 || days < 0){
				return new BaseObjResponse<AppUserLearningPlan>(RespConstants.GLOBAL_PARAM_ERROR,"传入的时间不合法!");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//获取开始和结束时间
		String firstDayOfStartMonth = DateTimeUtils.getFirstDayOfMonth(Integer.parseInt(startDateStr.substring(0,4)), Integer.parseInt(startDateStr.substring(5,7)));
		String lastDayOfEndMonth = DateTimeUtils.getLastDayOfMonth(Integer.parseInt(endDateStr.substring(0,4)), Integer.parseInt(endDateStr.substring(5,7)));
		Date cStart = new Date();
		Date dEnd = new Date();
		try {
			cStart = format.parse(firstDayOfStartMonth);
			dEnd = format.parse(lastDayOfEndMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AppUserLearningPlan condition = new AppUserLearningPlan();
		condition.setStartDate(cStart);
		condition.setEndDate(dEnd);
		condition.setAccaUserId(req.getAppUserId());
		//先根据登录的用户id获取学习计划列表
		List<AppUserLearningPlan> learningPlanList = dao.getPlanListBetweenDays(condition);
		//获取学习规划的map
		Map<String, List<AppUserLearningPlan>> planMap = this.convertPlanListToMap(learningPlanList);
		for (String key : planMap.keySet()) {
			List<AppUserLearningPlan> planList = planMap.get(key);
			if (planList.size() >= 4){
				return new BaseObjResponse<AppUserLearningPlan>(RespConstants.GLOBAL_PARAM_ERROR,"传入的时间不合法,超过四科了!");
			}
		}

		plan.setStartDate(startDate);
		plan.setEndDate(endDate);
		dao.update(plan);
		logger.info("用户{}修改学习计划{}成功!", req.getAppUserId(), plan);
		//获取修改后得学习规划
		AppUserLearningPlan learningPlan = dao.get(req.getLearningPlanId());
		logger.info("用户{}获取修改后的学习计划{}成功!", req.getAppUserId(), plan);
		//同时,修改首页提醒
		Date dayEnd = this.dayEnd(learningPlan.getEndDate());
		this.saveOrUpdatePlanTips(learningPlan.getAccaUserId(),learningPlan.getCourseName(),dayEnd);
		logger.info("用户{}修改学习规划后,修改首页提醒成功!", req.getAppUserId());
		return new BaseObjResponse<AppUserLearningPlan>(learningPlan);
	}

	/**
	 * 首页获取学习规划提醒
	 * 以前规则:
	 * 1.获取考试报考信息,如果最近有两科或两科以上报考信息,则显示前两条;
	 * 2.如果,只有一科报考信息,则查找学习规划,并且排除掉这个报考的科目,
	 * 	 如果学习规划的条数大于等于1,则取一条符合条件的信息;
	 * 3.如果没有报考信息,则查找最近的学习规划,没有就不展示.
	 * 现在直接都从提醒表中获取,取离现在最近的两条
	 */
	public LearningTipsResp getLearningTips(BaseRequest req){
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new LearningTipsResp(req.isCorrectParams());
		}
		List<Map<String,String>> result = new ArrayList<Map<String, String>>();
		//根据登录用户的信息,获取他的提醒信息,并取前两条
		Long userId = req.getAppUserId();
		AppTips tips = new AppTips();
		tips.setAccaUserId(userId);
		Page<AppTips> page = new Page<AppTips>();
		page.setPageSize(2);
		tips.setPage(page);
		List<AppTips> tipsList = tipsDao.findList(tips);
		if (tipsList != null && tipsList.size() > 0){
			//遍历提醒列表
			for (AppTips tip:tipsList){
				//获取考试时间的日期和时间
				Date tipTime = tip.getTipTime();
				long day = DateTimeUtils.daysBetween(new Date(),tipTime);
				String tipDay = DateTimeUtils.convertDate2String(tipTime, "yyyy-MM-dd");
				//判断提醒的类型
				Integer tipType = tip.getTipType();
				String tipContent = tip.getTipContent();
				Map<String,String> map = new HashMap<String, String>();
				map.put("time",tipDay);
				map.put("timeTips","剩余" + day + "天");
				map.put("tips",tipContent);
				if (tipType == ApiConstant.TIP_TYPE_EXAM){
					map.put("tipsType","2");
				} else if (tipType == ApiConstant.TIP_TYPE_PLAN){
					map.put("tipsType","1");
				} else {
					map.put("tipsType","3");
				}
				result.add(map);
			}
		}
		LearningTipsResp resp = new LearningTipsResp(result);
		//获取规划列表
		List<AppUserLearningPlan> planList = this.getPlanList(req);
		if (planList != null && planList.size() > 0){
			resp.setHasPlan(1);
			//获取开始年份
			AppUserLearningPlan firstPlan = planList.get(0);
			Date startDate = firstPlan.getStartDate();
			String startYear = DateTimeUtils.convertDate2String(startDate, "yyyy");
			resp.setStartYear(Integer.parseInt(startYear));
			//获取结束年份
			AppUserLearningPlan lastPlan = planList.get(planList.size() - 1);
			Date endDate = lastPlan.getEndDate();
			String endYear = DateTimeUtils.convertDate2String(endDate, "yyyy");
			resp.setEndYear(Integer.parseInt(endYear));
		} else {
			resp.setHasPlan(0);
		}
		return resp;
	}

	/**
	 * 获取考试科目列表,根据科目排序
	 * @param req
	 * @return
     */
	public BasePageResponse<AppExamCourse> getExamPlanList(BaseRequest req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppExamCourse>(req.isCorrectParams());
		}
		List<AppExamCourse> list = appExamCourseDao.findList(new AppExamCourse());
		logger.info("用户{}获取考试科目列表{}成功!", req.getAppUserId(), list);
		return new BasePageResponse<AppExamCourse>(list);
	}

	/**
	 * 获取学习页面的信息,并进行数据组装
	 * @param req
	 * @return
	 */
	public GetLearningPlanResultResp getLeaningPlanResult(BaseRequest req) {
		if (req.isCorrectParams() != RespConstants.GLOBAL_SUCCESS) {
			return new GetLearningPlanResultResp(req.isCorrectParams());
		}

		//获取该用户的学习计划列表
		List<AppUserLearningPlan> planList= this.getPlanList(req);
		//获取学习需要使用的年数
		List<Integer> useYear = this.getUseYear(planList);

		//先将学习规划列表转为按年月排列的map
		Map<String,List<AppUserLearningPlan>> resultMap = this.convertPlanListToMap(planList);

		List<LearningPlanInfo> resultList = new ArrayList<LearningPlanInfo>();
		//对使用的年进行遍历
		for (Integer year:useYear){
			LearningPlanInfo learningPlanInfo = new LearningPlanInfo();
			//对年的月份进行遍历
			Map<String, List<AppUserLearningPlan>> monthMap = new LinkedHashMap<String, List<AppUserLearningPlan>>();
			Map<String,Integer> tipsMap = new LinkedHashMap<String, Integer>();
			for (int m = 1;m <= 12;m++){
				List<AppUserLearningPlan> monthPlanList = resultMap.get(year + "-" + m);
				if (monthPlanList != null){
					monthMap.put("M" + m,monthPlanList);
				} else {
					monthMap.put("M" + m,new ArrayList<AppUserLearningPlan>());
				}
				String monthStr;
				if (m < 10) {
					monthStr = year + "-0" + m;
				} else {
					monthStr = year + "-" + m;
				}
				Long count = tipsDao.findMyListByMonth(req.getAppUserId(), monthStr);
				if (count > 0){
					tipsMap.put("M" + m,1);
				} else {
					tipsMap.put("M" + m,0);
				}
			}
			learningPlanInfo.setTipsInfo(tipsMap);

			learningPlanInfo.setYear(year.toString());
			learningPlanInfo.setContent(monthMap);
			resultList.add(learningPlanInfo);
		}

		return new GetLearningPlanResultResp(resultList);
	}

	/**
	 * 添加日历提醒
	 * @param req
	 * @return
	 */
	@Transactional(readOnly = false)
	public BaseResponse addCalendarTips(AddCalendarTipsReq req) {
		AppTips tips = new AppTips();
		Integer tipSet = req.getTipSet();
		String tipTimeStr = req.getTipTime();
		int year = Integer.parseInt(tipTimeStr.substring(0, 4));
		int month = Integer.parseInt(tipTimeStr.substring(5, 7));
		int day = Integer.parseInt(tipTimeStr.substring(8, 10));
		//转换提醒时间
		Date tipTime = DateTimeUtils.convertString2Date(tipTimeStr,"yyyy-MM-dd hh:mm");
		tips.setAccaUserId(req.getAppUserId());
		tips.setTipTime(tipTime);
		tips.setTipSet(tipSet);
		tips.setTipContent(req.getTipContent());
		tips.setPushContent("同学您好，您于" + year + "年" + month + "月" + day + "日设置了提醒，提醒内容为：" + req.getTipContent());
		//设置标题,用于消息推送
		tips.setTipTitle("日历提醒");
		tips.setIsSys(ApiConstant.TIP_NOT_SYS);
		tips.setTipType(ApiConstant.TIP_TYPE_PERSON);
		//判断提醒时间的类型,设置推送的时间
		Date pushTime ;
		if (tipSet == ApiConstant.TIP_BEFORE_DAY){
			pushTime = TimeUtils.getNextDay(tipTime,-1);
		} else {
			pushTime = TimeUtils.getNextDay(tipTime,-7);
		}
		tips.setPushTime(pushTime);
		tipsDao.insert(tips);
		logger.info("用户{}添加日历提醒成功!", req.getAppUserId());
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	/**
	 * 删除个人日历提醒
	 * @param req
	 * @return
	 */
	@Transactional(readOnly = false)
	public BaseResponse delCalendarTips(DelCalendarTipsReq req) {
		AppTips tips = new AppTips();
		tips.setId(String.valueOf(req.getId()));
		tipsDao.delete(tips);
		logger.info("用户{}删除日历提醒成功!", req.getAppUserId());
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	/**
	 * 获取日历提醒
	 * @param req
	 * @return
     */
	public BaseObjResponse<List<PlanTips>> getCalendarTips(GetCalendarTipsReq req) {
		Long appUserId = req.getAppUserId();
		String tipMonth = req.getTipMonth();
		//获取月份的提醒列表
		List<AppTips> allTips = tipsDao.findByMonth(appUserId,tipMonth);
		//对列表进行处理,设置成天的列表,先获取当月有多少天
		int year = Integer.parseInt(tipMonth.substring(0, 4));
		int month = Integer.parseInt(tipMonth.substring(5, 7));
		int days = DateTimeUtils.days(year, month);
		//对天进行遍历
		List<PlanTips> planTipsList = new ArrayList<PlanTips>();
		for (int d = 1;d <= days;d++){
			PlanTips tips = new PlanTips();
			List<AppTips> tipsList = new ArrayList<AppTips>();
			int flag = 0;
			if (allTips != null && allTips.size() > 0){
				for (AppTips tip:allTips){
					Date tipTime = tip.getTipTime();
					String tipDay = DateTimeUtils.convertDate2String(tipTime, "yyyy-MM-dd");
					String dayStr = tipMonth;
					if (d < 10) {
						dayStr += "-0" + d;
					} else {
						dayStr += "-" + d;
					}
					if (tipDay.equals(dayStr)){
						tipsList.add(tip);
						flag = 1;
					}
				}
			}
			tips.setTipsList(tipsList);
			if (flag == 1) {
				tips.setHasTips(1);
			} else {
				tips.setHasTips(0);
			}
			planTipsList.add(tips);
		}
		logger.info("用户{}获取日历提醒列表成功!", req.getAppUserId());
		return new BaseObjResponse<List<PlanTips>>(planTipsList);
	}

	/**
	 * 修改个人日历提醒
	 * @param req
	 * @return
	 */
	@Transactional(readOnly = false)
	public BaseObjResponse<AppTips> updateCalendarTips(UpdateCalendarTipsReq req) {
		AppTips condition = new AppTips();
		condition.setId(String.valueOf(req.getId()));
		//通过id获取需要修改的日历提醒
		AppTips tips = tipsDao.get(condition);
		if (tips == null) {
			return new BaseObjResponse<AppTips>(RespConstants.GLOBAL_PARAM_ERROR,"该提醒不存在!");
		}
		if (tips.getTipType() == ApiConstant.TIP_IS_SYS) {
			return new BaseObjResponse<AppTips>(RespConstants.GLOBAL_PARAM_ERROR,"系统提醒,不允许修改!");
		}
		//转换提醒时间
		Date tipTime = DateTimeUtils.convertString2Date(req.getTipTime(),"yyyy-MM-dd hh:mm");
		Integer tipSet = req.getTipSet();
		tips.setTipTime(tipTime);
		tips.setTipSet(tipSet);
		tips.setTipContent(req.getTipContent());
		tips.setPushContent(req.getTipContent());
		//判断提醒时间的类型,设置推送的时间
		Date pushTime ;
		if (tipSet == ApiConstant.TIP_BEFORE_DAY){
			pushTime = TimeUtils.getNextDay(tipTime,-1);
		} else {
			pushTime = TimeUtils.getNextDay(tipTime,-7);
		}
		tips.setPushTime(pushTime);
		tipsDao.update(tips);
		logger.info("用户{}修改日历提醒成功!", req.getAppUserId());
		tips = tipsDao.get(condition);
		return new BaseObjResponse<AppTips>(tips);
	}

	/**
	 * 报考管理-保存学习规划
	 * @param req
	 * @return
     */
	@Transactional(readOnly = false)
	public BaseResponse saveLearningPlan(SaveLearningPlanReq req) {
		Long appUserId = req.getAppUserId();
		Long orderId = req.getOrderId();
		//先通过订单id获取订单列表
		WebOrder order = new WebOrder();
		order.setAccaUserId(appUserId);
		order.setOrderId(orderId);
		WebOrder webOrder = orderDao.get(order);
		Integer orderStatus = webOrder.getOrderStatus();
		//判断订单状态是否为待支付的
		if (orderStatus != Constants.OrderStatus.unpay){
			return new BaseResponse(RespConstants.GLOBAL_PARAM_ERROR,"状态不正确!");
		}
		AppUserLearningPlan condition = new AppUserLearningPlan();
		condition.setAccaUserId(appUserId);

		//首先获取规划的列表,看是否有进行规划
		List<AppUserLearningPlan> allList = dao.findList(condition);
		if (allList == null || allList.size() == 0) {
			logger.error("用户{}未进行学习规划,不对其规划做修改!", appUserId);
			return new BaseResponse(RespConstants.NO_LEARNING_PLAN);
		}
		//获取考试列表
		List<WebSignup> signups = webOrder.getSignups();
		if (signups != null && signups.size() > 0){
			for (WebSignup signup:signups){
				updateLearningPlanWhenExam(appUserId,signup,orderStatus);
			}
		}
		logger.info("用户{}报考后,修改学习规划成功!", appUserId);
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}

	/**
	 * 报考通过后,修改学习规划
	 * 如果报考时间是在规划结束之后,则不修改;
	 * 如果报考时间在规划时间内,则将结束时间改为报考时间;
	 * 如果报考时间在规划时间之前,则判断当月学习的科目是否小于四,如果不是则一直往前推
	 * @param accaUserId
	 * @param signup
     */
	@Transactional(readOnly = false)
	public void updateLearningPlanWhenExam(Long accaUserId, WebSignup signup,Integer orderStatus){
		Long examCourseId = signup.getExamCourseId();
		Date examDate = signup.getExamStartTime();
		AppUserLearningPlan condition = new AppUserLearningPlan();
		condition.setAccaUserId(accaUserId);

		//首先获取规划的列表,看是否有进行规划
		List<AppUserLearningPlan> allList = dao.findList(condition);
		if (allList == null || allList.size() == 0) {
			logger.error("用户{}未进行学习规划,不对其规划做修改!", accaUserId);
			return;
		}
		//通过登录信息和选择的课程,获取到规划的信息进行修改
		condition.setCourseId(examCourseId);
		//获取到规划的信息
		AppUserLearningPlan plan = dao.getByCondition(condition);
		if (plan == null) {
			logger.error("用户{}未对科目{}进行学习规划,不对其规划做修改!", accaUserId, examCourseId);
			return;
		}
		Date startDate = plan.getStartDate();
		Date endDate = plan.getEndDate();
		//判断报考的时间与该科目的规划时间的关系
		int startDays = DateTimeUtils.daysBetween(examDate, startDate);
		int endDays = DateTimeUtils.daysBetween(examDate, endDate);
		if (endDays >= 0 && startDays <= 0){
			//如果报考时间介于规划的时间之间,则直接修改报考的时间为规划结束的时间
			plan.setEndDate(examDate);
		} else if (startDays > 0){
			plan = this.updatePlanTime(accaUserId,examCourseId, plan, examDate, examDate);
		}
		dao.update(plan);
		logger.info("用户{}报考完修改规划成功!", accaUserId);
		//判断考试的状态,如果为审核通过或者手动添加的为待支付状态的,则新增考试提醒
		if (orderStatus == Constants.OrderStatus.checkSuccess || orderStatus == Constants.OrderStatus.unpay)
		//给报考信息添加首页提醒.先删除对应科目的规划提醒,再新增考试提醒
		{
			this.saveSignupTips(signup,accaUserId);
		}
	}

	/**
	 * 遍历修改学习规划时间
	 * @param accaUserId 查询条件
	 * @param plan 返回的对象
	 * @param examDate 规划的时间
     * @return
     */
	private AppUserLearningPlan updatePlanTime(Long accaUserId,Long examCourseId,AppUserLearningPlan plan,Date examDate,Date endDate){
		//如果报考的时间在该科目的规划时间之前,则获取考试的月份是否能再添加计划,除自己的id外,小于4科
		String planMonthStr = DateTimeUtils.convertDate2String(endDate, "yyyy-MM");
		int count = dao.findMonthList(accaUserId,examCourseId,planMonthStr);
		if (count >= 4){
			//获取上个月
			Date lastDate = DateTimeUtils.getLastDate(endDate);
			plan = updatePlanTime(accaUserId,examCourseId, plan,examDate, lastDate);
		} else {
			plan.setStartDate(DateTimeUtils.convertString2Date(planMonthStr + "-01","yyyy-MM-dd"));
			//获取当月的最后一天
			int year = Integer.parseInt(planMonthStr.substring(0, 4));
			int month = Integer.parseInt(planMonthStr.substring(5, 7));
			String lastDayOfMonth = DateTimeUtils.getLastDayOfMonth(year, month);
			String examStr = DateTimeUtils.convertDate2String(examDate, "yyyy-MM");
			Date lastDay = DateTimeUtils.convertString2Date(lastDayOfMonth,"yyyy-MM-dd");
			if (examStr.equals(planMonthStr)){
				lastDay = examDate;
			}
			plan.setEndDate(lastDay);
		}
		return plan;
	}

	/**
	 * 生成学习规划安排
	 * 现在一年的计划科目数,身份都不考虑
	 * @return
	 */
	private List<AppUserLearningPlan> generateLearningPlaningList(SubmitLearningPlanReq req) {
		//获取规划的数据
		String startDateStr = req.getStartDate();
		Integer planNum = req.getPlanNum();
		List<Long> donotExamList = req.getDonotExamList();
		Integer schoolType = req.getSchoolType();
		Integer englishLevel = req.getEnglishLevel();
		Long accUserId = req.getAppUserId();
		Integer identity = req.getIdentity();

		//先计算可并行学习的科目数量
		int learningNum = this.calculateLearningNum(schoolType,englishLevel);

		//解析时间
		String[] timeArr = StringUtils.split(startDateStr,"-");
		int year = Integer.parseInt(timeArr[0]);
		int month = Integer.parseInt(timeArr[1]);

		//获取所有考试科目列表
		List<AppExamCourse> allExamCourseList = appExamCourseDao.findList(new AppExamCourse());
		//需要规划的考试科目列表
		List<AppExamCourse> planningCourseList = new ArrayList<AppExamCourse>();
		//从所有考试科目列表中剔除已考或者免考的科目
		for (int i = 0;i < allExamCourseList.size();i++){
			AppExamCourse appExamCourse = allExamCourseList.get(i);
			Long examCourseId = appExamCourse.getExamCourseId();
			if (!donotExamList.contains(examCourseId)) {
				planningCourseList.add(appExamCourse);
			}
		}
		int planningCourseNum = planningCourseList.size();
		//进行学习计划安排
		if (planningCourseNum > 0){
			int flag = 0;
			int endMonth = 1;
			//对需规划的考试科目id列表进行遍历
			for (AppExamCourse course:planningCourseList){
				Long examCourseId = course.getExamCourseId();
				String courseName = course.getCourse();
				//一个月安排的科目数,进行标记计算
				if (flag == learningNum){
					month = endMonth + 1;
					flag = 0;
				}
				//将数据保存
				AppUserLearningPlan plan = new AppUserLearningPlan();
				plan.setAccaUserId(accUserId);
				plan.setCourseId(examCourseId);
				plan.setCourseName(courseName);
				//获取当前的日期
				Date currentDate = DateTimeUtils.getCurrentDate();
				String currentStr = DateTimeUtils.convertDate2String(currentDate);
				int startDay = 1;
				//判断规划的开始日期是否是本月,如果是本月,则规划的开始日期为当天开始
				if (Integer.parseInt(currentStr.substring(0,4)) == year && Integer.parseInt(currentStr.substring(5,7)) == month){
					startDay = Integer.parseInt(currentStr.substring(8,10));
				}
				String startStr = year + "-" + month + "-" + startDay;
				//学习时间至少跨三个月,所以,结束月份要比现在的月份>=2
				endMonth = month + 2;
				//判断安排科目学习结束的月份是否为3的倍数
				while (endMonth % 3 != 0){
					endMonth ++;
				}

				Date startDate = DateTimeUtils.convertString2Date(startStr,"yyyy-MM-dd");
				String lastDayOfMonth = DateTimeUtils.getLastDayOfMonth(year, endMonth);
				Date endDate = DateTimeUtils.convertString2Date(lastDayOfMonth,"yyyy-MM-dd");
				plan.setStartDate(startDate);
				plan.setEndDate(endDate);
				dao.insert(plan);
				//并将该条信息保存到首页提醒中
				Date dayEnd = this.dayEnd(endDate);
				this.saveOrUpdatePlanTips(accUserId,courseName,dayEnd);
				//对标记位加1
				flag ++;
			}
		}
		//查询生成的列表,并返回
		return this.getPlanList(req);
	}

	/**
	 * 获取学习使用的年数
	 * @param planList
	 * @return
     */
	private List<Integer> getUseYear(List<AppUserLearningPlan> planList){
		List<Integer> tempYearList = new ArrayList<Integer>();
		//获取学习计划的年数
		if (planList != null && planList.size() > 0){
			//对规划列表进行遍历
			for (int i = 0;i < planList.size();i++) {
				AppUserLearningPlan learningPlan = planList.get(i);
				//获取开始和结束的年份
				String[] startDate = this.getDateArr(learningPlan.getStartDate());
				int startDateYear = Integer.parseInt(startDate[0]);
				tempYearList.add(startDateYear);
				String[] endDate = this.getDateArr(learningPlan.getEndDate());
				int endDateYear = Integer.parseInt(endDate[0]);
				tempYearList.add(endDateYear);
			}
		}
		//对list去重
		List<Integer> yearList= new ArrayList<Integer>();
		for(Integer i:tempYearList){
			if(!yearList.contains(i)){
				yearList.add(i);
			}
		}
		return yearList;
	}

	/**
	 * 根据登录信息获取学习规划
	 * @param req
	 * @return
     */
	private List<AppUserLearningPlan> getPlanList(BaseRequest req){
		AppUserLearningPlan condition = new AppUserLearningPlan();
		condition.setAccaUserId(req.getAppUserId());
		List<AppUserLearningPlan> learningPlanList = dao.findList(condition);
		logger.info("用户{}获取学习计划列表{}成功!", req.getAppUserId(), learningPlanList);
		return learningPlanList;
	}

	/**
	 * 拆分日期
	 * @param date
	 * @return
     */
	private String[] getDateArr(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(date);
		return dateStr.split("-");
	}

	/**
	 * 将学习规划列表转为按年月排列的map
	 * @param planList
	 * @return
     */
	private Map<String,List<AppUserLearningPlan>> convertPlanListToMap(List<AppUserLearningPlan> planList){
		Map<String,List<AppUserLearningPlan>> resultMap = new LinkedHashMap<String, List<AppUserLearningPlan>>();
		if (planList != null && planList.size() > 0){
			//对规划列表进行遍历
			for (int i = 0;i < planList.size();i++){
				AppUserLearningPlan learningPlan = planList.get(i);
				//获取开始和结束的日期
				String[] startDate = this.getDateArr(learningPlan.getStartDate());
				int startDateYear = Integer.parseInt(startDate[0]);
				int startDateMonth = Integer.parseInt(startDate[1]);
				String[] endDate = this.getDateArr(learningPlan.getEndDate());
				int endDateYear = Integer.parseInt(endDate[0]);
				int endDateMonth = Integer.parseInt(endDate[1]);

				//判断时间是否是在同一年
				if (startDateYear == endDateYear){
					for (int j = startDateMonth;j <= endDateMonth;j++){
						List<AppUserLearningPlan> list = resultMap.get(startDateYear + "-" + j);
						if (list == null){
							list = new ArrayList<AppUserLearningPlan>();
						}
						list.add(learningPlan);
						LearningPlanComparator comparator = new LearningPlanComparator();
						Collections.sort(list , comparator);
						resultMap.put(startDateYear + "-" + j, list);
					}
				} else if (startDateYear == endDateYear -1){
					//不是在同一年
					for (int j = startDateMonth;j <= 12;j++){
						List<AppUserLearningPlan> list = resultMap.get(startDateYear + "-" + j);
						if (list == null){
							list = new ArrayList<AppUserLearningPlan>();
						}
						list.add(learningPlan);
						LearningPlanComparator comparator = new LearningPlanComparator();
						Collections.sort(list , comparator);
						resultMap.put(startDateYear + "-" + j, list);
					}
					for (int j = 1;j <= endDateMonth;j++){
						List<AppUserLearningPlan> list = resultMap.get(endDateYear + "-" + j);
						if (list == null){
							list = new ArrayList<AppUserLearningPlan>();
						}
						list.add(learningPlan);
						LearningPlanComparator comparator = new LearningPlanComparator();
						Collections.sort(list , comparator);
						resultMap.put(endDateYear + "-" + j, list);
					}
				}
			}
		}
		return resultMap;
	}

	/**
	 * 计算可并行学习的科目数
	 * @param schoolType 所在学校类型,985为1;211为2;一本为3;二本为4;三本为5;大专为6;高中毕业为7
	 * @param englishLevel 英语水平,值为高考90分以上1;英语3级为2;英语4级为3;英语6级为4;英语专8为5;雅思4.5-5.5为6;雅思6.0以上为7;托福70-90为8;托福90以上为9
     * @return
     */
	private int calculateLearningNum(Integer schoolType, Integer englishLevel) {
		//当学校类型为985,211,一本中的一个,并且英语水平为4级,6级,专8,雅思6.0以上,托福90以上中的一种的情况一个月才可以并行学两科,其他情况都只能学一科
		if ((schoolType == 1 || schoolType == 2 || schoolType == 3) && (englishLevel == 3 || englishLevel== 4 || englishLevel == 5 || englishLevel == 7 || englishLevel == 9)){
			return 2;
		}
		return 1;
	}

	/**
	 * 保存或更新规划提醒
	 * @param accUserId
	 * @param courseName
	 * @param endDate
     */
	private void saveOrUpdatePlanTips(Long accUserId,String courseName,Date endDate){
		//并将该条信息保存到首页提醒中
		AppTips tips = new AppTips();
		tips.setAccaUserId(accUserId);
		tips.setCourse(courseName);
		tips.setTipType(ApiConstant.TIP_TYPE_PLAN);
		//先查询是否有这条提醒,如果有,则更新,如果没有,则新增
		AppTips oldTips = tipsDao.get(tips);
		if (oldTips != null) {
			tips = oldTips;
		}
		//因为首页提醒,个人提醒和报考提醒是精确到小时的,而查询按时间升序的,所以,规划取当天的最后时刻
		tips.setTipTime(endDate);
		tips.setPushTime(TimeUtils.getNextDay(endDate,-1));
		tips.setTipContent(courseName + "科目学习结束");
		tips.setTipTitle(courseName + "科目学习结束");
		tips.setPushContent("同学您好，明天是您" + courseName + "学习结束的时间。");
		//除个人自定义提醒外,都是系统提醒
		tips.setIsSys(ApiConstant.TIP_IS_SYS);
		tips.setTipSet(1);
		//如果有,则更新,如果没有,则新增
		if (oldTips != null){
			tipsDao.update(tips);
		} else {
			tipsDao.insert(tips);
		}
	}

	/**
	 * 保存考试提醒
	 * 先删除对应科目的规划提醒,再新增考试提醒
	 * @param signup
	 * @param accaUserId
     */
	private void saveSignupTips(WebSignup signup,Long accaUserId){
		String courseName = signup.getCourse();
		Date examDate = signup.getExamStartTime();
		//给报考信息添加首页提醒.
		AppTips tips = new AppTips();
		tips.setAccaUserId(accaUserId);
		tips.setCourse(courseName);
		tips.setTipType(ApiConstant.TIP_TYPE_PLAN);
		tipsDao.delete(tips);
		//新增考试提醒
		tips.setTipSet(1);
		tips.setTipType(ApiConstant.TIP_TYPE_EXAM);
		tips.setTipTime(examDate);
		//先查询这条数据是否存在,如果存在,则先删除
		AppTips oldTip = tipsDao.get(tips);
		if (oldTip != null) {
			tipsDao.delete(oldTip);
		}
		//获取时间范围
		String timeScope = signup.getTimeScope();
		timeScope = timeScope.substring(11,timeScope.length());
		tips.setTipContent(timeScope + "参加" + courseName + "考试");
		tips.setPushTime(TimeUtils.getNextDay(examDate,-1));
		String examStartStr = DateTimeUtils.convertDate2String(examDate);
		int year = Integer.parseInt(examStartStr.substring(0, 4));
		int month = Integer.parseInt(examStartStr.substring(5, 7));
		int day = Integer.parseInt(examStartStr.substring(8, 10));
		String examPlaceName = signup.getExamPlaceName();
		String pushContent = "同学您好，您预约的" + year + "年" + month + "月" + day + "日" + courseName + "考试" +
				"将于明日" + timeScope + "在" + examPlaceName + "机考中心进行，" +
				"请提前0.5-1小时到达指定机考中心，祝考试顺利。";
		tips.setPushContent(pushContent);
		tips.setTipTitle("考试提醒");
		tips.setIsSys(ApiConstant.TIP_IS_SYS);
		tipsDao.insert(tips);
		logger.info("用户{}报考完修改规划后添加提醒成功!", accaUserId);
	}

	public Date dayEnd(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	 public Map sort(List list){  
         Map map=new HashMap();  
         ArrayList arraylist=new ArrayList();  
         String[] alphatableb =  
             {  
                "A", "B", "C", "D", "E", "F", "G", "H", "I",  
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"  
               };  
             for(String a:alphatableb){  
                 for(int i=0;i<list.size();i++){//为了排序都返回大写字母  
                     if(a.equals(list.get(i).toString().substring(0,1).toUpperCase())){  
                         arraylist.add(list.get(i).toString());  
                     }  
                 }  
                 map.put(a,arraylist);  
                 arraylist=new ArrayList();  
         }  
         return map;  
     }  

	public static void main(String args[]){		
		
		//Map<String, Object> json = "{'name':'reiz'}";
		ArrayList list=new ArrayList();  
        list.add("adisen");  
        list.add("bulsi");  
        list.add("Kobe");  
        list.add("buding");  
        list.add("dufu");  
        list.add("yuanfang");  
        list.add("adisen2");  
        list.add("bulsi2");  
        list.add("Kobe2");  
        list.add("buding2");  
        list.add("dufu2");  
        list.add("yuanfang2");  
        
        
        
        Map map=new HashMap();  
        ArrayList arraylist=new ArrayList();  
        String[] alphatableb =  
            {  
               "A", "B", "C", "D", "E", "F", "G", "H", "I",  
               "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"  
              };  
        JSONObject json = new JSONObject();
        ArrayList wordlist=new ArrayList();  
		for(String a:alphatableb){			
			ArrayList words=new ArrayList();  
			for(int i=0;i<list.size();i++){//为了排序都返回大写字母  
				if(a.equals(list.get(i).toString().substring(0,1).toUpperCase())){  
					JSONObject word = new JSONObject();
					word.put("word", list.get(i));
					words.add(word);					
				}  
			}
			
			//System.out.println(words.toString());
			if(words.size()>0){
				JSONObject cap = new JSONObject();
				cap.put("cap", a);
				cap.put("words", words);
				wordlist.add(cap);
			}
		}
		json.put("wordslist", wordlist);
        System.out.println(json.toJSONString());  
/*        System.out.println(map.get("A"));  
        System.out.println(map.get("B"));  
        System.out.println(map.get("C"));  
        System.out.println(map.get("D"));  
        System.out.println(map.get("Y"));*/  
		
	}

	/**
	 * TODO:完成学习规划活动,送优惠码推送代码,活动已于10.16到期,停用代码
	 */
	/*private void planActivity(Long accaUserId){
		logger.error("开始发送信息");
		//获取用户信息
		AppAccaUser appAccaUser = userDao.get(String.valueOf(accaUserId));
		String phone = appAccaUser.getPhone();
		String title = "已成功完成任务";
		String content = "同学您好，您已经成功完成了我们的任务，现在可以领取我们为您准备的精品课程了! 领取地址 http://www.china-ife.com/S?UserKey=lucky2016";
		msgSysService.savePushToPersonal(title,content,accaUserId);
		String msg = "同学您好，您已经成功完成了我们的任务，现在可以领取我们为您准备的精品课程了! 点击领取 http://www.china-ife.com/S?UserKey=lucky2016";
		//发送提醒短信
		SmsUtils.sendSms2Vcode(phone,msg);
		logger.error("发送信息完成");
	}*/
}