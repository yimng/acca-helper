/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.service;


import com.thinkgem.jeesite.acca.api.exam.dao.*;
import com.thinkgem.jeesite.acca.api.exam.entity.*;
import com.thinkgem.jeesite.acca.api.home.service.AppMsgPushLogService;
import com.thinkgem.jeesite.acca.api.model.request.AddSelfExamCartReq;
import com.thinkgem.jeesite.acca.api.model.request.SubmitExamRegisterReq;
import com.thinkgem.jeesite.acca.api.model.response.*;
import com.thinkgem.jeesite.acca.api.order.dao.AppOrderDao;
import com.thinkgem.jeesite.acca.api.order.entity.AppOrder;
import com.thinkgem.jeesite.acca.api.order.entity.SmallDetailOrder;
import com.thinkgem.jeesite.acca.api.order.service.AppOffiOrderService;
import com.thinkgem.jeesite.acca.api.register.dao.AppExamOpenCityDao;
import com.thinkgem.jeesite.acca.api.register.entity.AppExamOpenCity;
import com.thinkgem.jeesite.acca.api.user.dao.AppAccaUserDao;
import com.thinkgem.jeesite.acca.api.user.dao.AppConfExamTipsDao;
import com.thinkgem.jeesite.acca.api.user.entity.AppAccaUser;
import com.thinkgem.jeesite.acca.api.user.entity.AppConfExamTips;
import com.thinkgem.jeesite.acca.constant.ApiConstant;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.coupon.dao.CouponMapper;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.user.dao.UserCouponMapper;
import com.thinkgem.jeesite.acca.web.user.entity.UserCoupon;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * APP 通用接口 Service
 * @author Ivan
 * @version 2016-07-25
 */
@Service
@Transactional(readOnly = true)
public class AppExamService  extends BaseService{

	@Autowired
	private AppExamOpenCityDao appExamOpenCityDao;
	
	@Autowired
	private AppExamDao appExamDao;
	
	@Autowired
	private AppExamPlaceDao appExamPlaceDao;
	
	@Autowired
	private AppExamSelfCartDao appExamSelfCartDao;
	
	@Autowired
	private AppOrderDao appOrderDao;
	
	@Autowired
	private AppExamSignupDao appExamSignupDao;
	
	@Autowired
	private AppAccaUserDao appAccaUserDao;
	
	@Autowired
	private AppOfficialOrderService appOfficialOrderService;
	
	@Autowired
	private AppOffiOrderService appOffiOrderService;
	
	@Autowired
	private AppOfficialExamSignupDao examSignupDao;
	
	@Autowired
	private AppConfExamTipsDao appConfExamTipsDao;
	
	@Autowired
	private WebMsgSysService msgSysService;

	@Autowired
    private CouponMapper couponMapper;

	@Autowired
    private UserCouponMapper userCouponMapper;
	
	@Transactional(readOnly = false)
	public GetSelfOfficialExamNameResp getSelfOfficialExamName(BaseRequest req) {
		
		AppConfExamTips tips1 = appConfExamTipsDao.get(new AppConfExamTips("2"));		
		AppConfExamTips tips2 = appConfExamTipsDao.get(new AppConfExamTips("3"));
		
		//String selfExamName = "F1-F4考点儿及考试预约";
		//String officialExamName = "F5-P7考点儿及考试预约";
		String selfExamName = tips1.getOfficialExamProcess();
		String officialExamName = tips2.getOfficialExamProcess();		
		Long uid = req.getAppUserId();
		if(uid!=null){
			AppAccaUser appUser=appAccaUserDao.get(uid.toString());		
			try{
				if(appUser.getIszbg()!=3){
					msgSysService.savePushToPersonal(
							"学员认证提醒","同学您好：\n 中博&财萃学员在机考报名中享受： F1-F3 750元，F4 950元 的机考报名价格。\n"
									+ "时间有限，考位有限，莫错良机！",appUser.getAccaUserId());
				}else{
					System.out.println("==========================="+appUser.getNickname() + "学员已经认证===========================");
				}
			}catch(Exception e){
				System.out.println("===========================学员认证提醒错误===========================");
				e.printStackTrace();
			}
		}
		return new GetSelfOfficialExamNameResp(selfExamName, officialExamName);
	}
	

	public BasePageResponse<AppExamOpenCity> getSelfExamCity(BaseRequest req) {
		
		
		AppExamOpenCity city = new AppExamOpenCity();
		city.setExamType(ApiConstant.EXAM_TYPE_SELF);
		
		List<AppExamOpenCity> list = appExamOpenCityDao.findList(city);
		logger.info("AppExamOpenCity self list :"+list);
		
		Collections.sort(list);
		
		logger.info("AppExamOpenCity sort list :"+list);
		
		return new BasePageResponse<AppExamOpenCity>(list);
	}

	public BasePageResponse<AppExam> getSelfExamList(Integer examCityId,Integer timeType, AppAccaUser appUser) {
		logger.info("appUser:"+appUser);
		
		Date startTime = null;
		if(timeType!=-1){
			startTime = DateTimeUtils.getDate(30*timeType+1);
		}
				
		logger.info("startTime:"+startTime);
		
		List<AppExam> list = appExamDao.findSelfExamListByCityAndTime(examCityId, startTime);
		
		logger.info("getSelfExamList:"+list);
		
		
		return new BasePageResponse<AppExam>(list);
	}
	
	public BasePageResponse<AppExam> getSelfExamListByCityAndTimeSpan(Integer examCityId,Date startTime, Date endTime, AppAccaUser appUser) {
		logger.info("appUser:"+appUser);
		logger.info("startTime:"+startTime);
		logger.info("endTime:"+endTime);
		
		List<AppExam> list = appExamDao.findSelfExamListByCityAndTimeSpan(examCityId, startTime, endTime);
		
		logger.info("getSelfExamList:"+list);
		
		
		return new BasePageResponse<AppExam>(list);
	}

	public BaseObjResponse<AppExamPlace> getSelfExamByExamPlaceId(	Integer examPlaceId, AppAccaUser appUser) {		
		AppExamPlace examPlace =new AppExamPlace();
		
		if(appUser.getIszbg()==3){
			 examPlace = appExamPlaceDao.getSelfExamByExamPlaceId(examPlaceId);
		}else{
			 examPlace = appExamPlaceDao.getSelfExamByExamPlaceId(examPlaceId);
		}
		logger.info("examPlaceId:{}   ,examPlace:{}",examPlaceId,examPlace);
		if(examPlace==null){
			return new BaseObjResponse<AppExamPlace>(RespConstants.GLOBAL_PARAM_ERROR);
		}	
		
		List<AppExamSelfCart> list =  appExamSelfCartDao.getByUserId(appUser.getAccaUserId());
		logger.info("AppExamSelfCart list:"+list);		
		if(list==null || list.isEmpty()){
			return new BaseObjResponse<AppExamPlace>(examPlace);
		}	
		//采用循环，将列表中
		List<AppExam> examList = examPlace.getExamList();
		if(examList!=null && !examList.isEmpty()){
			for(AppExam index:examList){
				List<AppExamCourseSelf> courseList = index.getExamCourseList();
				if(courseList!=null && !courseList.isEmpty()){
					for(AppExamCourseSelf item:courseList){
						long examId = index.getExamId();
						long examCourseId = item.getExamCourseId();						
						for(AppExamSelfCart cart:list){
							if(examId==cart.getExamId() && examCourseId== cart.getExamCourseId()){
								item.setUserSignupStatus(1);
								break;
							}
						}
						
					}
				}
			}
		}		
		logger.info("examPlaceId:{}   ,examPlace:{}",examPlaceId,examPlace);
		return new BaseObjResponse<AppExamPlace>(examPlace);
	}

	public BasePageResponse<AppExamSelfCityTiny> getSelfExamPlaceList(AppAccaUser appUser) {		
		List<AppExamSelfCityTiny> list = appExamDao.getSelfExamPlaceList();
		logger.info("getSelfExamPlaceList list:"+list);		
		return new BasePageResponse<AppExamSelfCityTiny>(list);
	}

	public GetSelfExamCartResp getSelfExamCart(AppAccaUser appUser) {		
		List<AppExamSelfCart> list = appExamSelfCartDao.getByUserId(appUser.getAccaUserId());
		logger.info("getSelfExamCart list:"+list);
		double totalAmount = 0;
		if(list!=null && !list.isEmpty()){
			for(AppExamSelfCart index:list){
				if(appUser.getIszbg()==3){
					index.setPrice(index.getStudentPrice());
				}
				totalAmount+=index.getPrice();
			}
		}
		logger.info("totalAmount:"+totalAmount);		
		return new GetSelfExamCartResp(list,totalAmount);
	}
	public GetSelfExamCartGroupByPlaceResp getSelfExamCartByPlace(AppAccaUser appUser) {
		List<AppExamSelfCart> list = appExamSelfCartDao.getByUserId(appUser.getAccaUserId());
		logger.info("getSelfExamCart list:"+list);
		double totalAmount = 0;
		HashMap<Long, List<AppExamSelfCart>> map = new HashMap<>();

		// 根据examPlaceId分组
		if(list!=null && !list.isEmpty()){
			for(AppExamSelfCart index:list){
				Long examPlaceId = index.getExamPlaceId();
				if (!map.containsKey(examPlaceId)) {
					List<AppExamSelfCart> li = new ArrayList<>();
					li.add(index);
					map.put(examPlaceId, li);
				} else {
					map.get(examPlaceId).add(index);
				}
			}
		}
        List<SelfExamGoups> groups = new ArrayList<>();
		for (Map.Entry<Long, List<AppExamSelfCart>> entry : map.entrySet()) {
			Long placeId = entry.getKey();
			List<AppExamSelfCart> exams = entry.getValue();
			double totByPlace = 0;
			for (AppExamSelfCart cart : exams) {
				if(appUser.getIszbg()==3){
					cart.setPrice(cart.getStudentPrice());
				}
				totByPlace += cart.getPrice();
			}
			totalAmount += totByPlace;
			AppExamSelfCart appExamSelfCart = exams.get(0);
			SelfExamGoups group = new SelfExamGoups(placeId, appExamSelfCart.getExamDetailAddress(),
					appExamSelfCart.getExamPlaceName(), appExamSelfCart.getExamPlaceSn(), exams, totByPlace);
			groups.add(group);
		}
		logger.info("totalAmount:"+totalAmount);
		return new GetSelfExamCartGroupByPlaceResp(groups, totalAmount);
	}

	@Transactional(readOnly = false)
	public GetSelfExamCartResp addSelfExamCart(AddSelfExamCartReq req) {
		long examId = req.getExamId();
		long examPlaceId = req.getExamPlaceId();
		long examCourseId = req.getExamCourseId();
		long examVersionId = 0L;
		if(req.getExamVersionId()!=null){
			examVersionId=req.getExamVersionId();
		}
		
		
		AppExamVersion version = null;
		
		AppExam exam = appExamDao.getExamInfoByExamId(examId);
		logger.info("addSelfExamCart examId:{}  ,exam:{}",examId,exam);
		
		if(exam==null || exam.getExamPlace()==null){
			return new GetSelfExamCartResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		//判断是否自有考试
		int examType = exam.getExamType();
		if((examType & 1)!=1){
			return new GetSelfExamCartResp(RespConstants.EXAM_SELF_CART_NOT_SELF_EXAM);
		}
		
		//判断剩余座位是否足够
		logger.info("avalabileseats:"+exam.getAvailableSeats());
		if(exam.getAvailableSeats()<=0){
			return new GetSelfExamCartResp(RespConstants.EXAM_SELF_CART_NOAvailableSeats);
		}
		
		//此处需要进行考试的报名截止时间判断。
		/*if(exam.getExamSignupEndtime().getTime()>new Date().getTime()){
			return new GetSelfExamCartResp(RespConstants.GLOBAL_PARAM_ERROR);
		}*/
		
		if(examPlaceId!=exam.getExamPlace().getExamPlaceId()){
			return new GetSelfExamCartResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		//List<AppExamCourseSelf> examCourseList = exam.getExamCourseList();
		List<AppExamCourseSelf> examCourseList = appExamDao.getExamCourseListByExamId(examId);
		logger.info("examCourseList:{}",examCourseList);
		if(examCourseList==null || examCourseList.isEmpty()){
			return new GetSelfExamCartResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		
		//检查科目是否正确，检查版本是否正确
		boolean exist = false;//判断examCourseId是否在list中含有
		for(AppExamCourseSelf index:examCourseList){
			if(index.getExamCourseId()==examCourseId){
				List<AppExamVersion> versionList = index.getExamVersionList();
				if(versionList!=null && !versionList.isEmpty()){
					//存在版本list，则examVersionId不能为空
					if(examVersionId==0){
						break;
					}else{
						boolean existVersion = false;
						for(AppExamVersion item:versionList){
							if(item.getExamVersionId()==examVersionId){
								existVersion = true;
								version = item;
								break;
							}
						}
						if(existVersion){
							exist = true;
						}
						break;
					}
				}else{
					if( examVersionId==0 ){
						exist=true;
						break;
					}else{
						break;
					}
				}
			
			}
		}
		logger.info("exist:"+exist);
		if(!exist){
			return new GetSelfExamCartResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		AppAccaUser appUser = req.getAppUser();
		
		
		//检查购物车中是否已经存在相同的考试，检查购物车中是否存在同时间段的考试
		List<AppExamSelfCart> list = appExamSelfCartDao.getByUserId(appUser.getAccaUserId());
		if(list!=null && !list.isEmpty()){
			for(AppExamSelfCart index:list){
				logger.info("examId:{},index.getExamId(){}",examId,index.getExamId());
				if(examId==index.getExamId() && examPlaceId ==index.getExamPlaceId() ){
					return new GetSelfExamCartResp(RespConstants.EXAM_SELF_CART_EXISTCOURSE);
				}
				if(DateTimeUtils.checkTwoTimeIntersect(exam.getExamStartTime(), exam.getExamEndTime(), index.getExamStartTime(), index.getExamEndTime())){
					logger.info("checkTwoTimeIntersect true ,exam:{},indexCart:{}",exam,index);
					return new GetSelfExamCartResp(RespConstants.EXAM_SELF_CART_EXISTCOURSE); 
				}
			}
		}
//		System.out.println("===========================addSelfExamCart1===========================");
//		System.out.println(appUser.toString());
//		System.out.println("isZBG:" + appUser.getIszbg());
//		if(appUser.getIszbg()==3){
//			for(AppExamCourseSelf index:examCourseList){				
//				index.setPrice(index.getStudentPrice());	
//				
//			}
//		}
//		System.out.println("===========================addSelfExamCart1 end===========================");
		AppExamSelfCart cart  = new AppExamSelfCart();
		cart.setAccaUserId(appUser.getAccaUserId());
		cart.setExamCourseId(examCourseId);
		cart.setExamPlaceId(examPlaceId);
		cart.setExamId(examId);
		cart.setExamVersionJson(JsonMapper.getInstance().toJson(version));
		cart.setCreateDate(new Date());
		cart.setUpdateDate(new Date());
		appExamSelfCartDao.insert(cart);
		
		List<AppExamSelfCart> list2 = appExamSelfCartDao.getByUserId(appUser.getAccaUserId());
		logger.info("addSelfExamCart list2:"+list2);
		
		double totalAmount = 0;
		if(list2!=null && !list2.isEmpty()){
			for(AppExamSelfCart index:list2){
//				System.out.println("===========================addSelfExamCart2===========================");
//				System.out.println(appUser.toString());
//				System.out.println("isZBG:" + appUser.getIszbg());
				if(appUser.getIszbg()==3){
					index.setPrice(index.getStudentPrice());
					//System.out.println(index.toString());
				}					
				//System.out.println("===========================addSelfExamCart2 end===========================");
				totalAmount+=index.getPrice();
			}
		}
		logger.info("totalAmount:"+totalAmount);
		
		AppConfExamTips conf = appConfExamTipsDao.get(new AppConfExamTips("4"));
        String payConfTips =conf.getOfficialExamProcess();
//        System.out.println("===========================addSelfExamCart3===========================");
//        System.out.println(list2);
//        System.out.println(totalAmount);
//        System.out.println("===========================addSelfExamCart3 end===========================");
		return new GetSelfExamCartResp(list2,totalAmount,payConfTips);
	}

	@Transactional(readOnly = false)
	public GetSelfExamCartResp delSelfExamCart(long selfExamCartId,	AppAccaUser appUser) {
		List<AppExamSelfCart> list = appExamSelfCartDao.getByUserId(appUser.getAccaUserId());
		logger.info("delSelfExamCart list:"+list);
		
		if(list==null || list.isEmpty()){
			return new GetSelfExamCartResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		
		AppExamSelfCart cart = null;
		for(AppExamSelfCart index:list){
			long id = index.getSelfExamCartId();
			logger.info("id:{},...,selfExamCartId:{}",id,selfExamCartId);
			if(id==selfExamCartId){
				cart = index;
				break;
			}
		}
		logger.info("cart:{}",cart);
		if(cart==null){
			return new GetSelfExamCartResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		
		
		appExamSelfCartDao.delete(cart);
		
		List<AppExamSelfCart> list2 = appExamSelfCartDao.getByUserId(appUser.getAccaUserId());
		logger.info("addSelfExamCart list2:"+list2);
		
		double totalAmount = 0;
		if(list2!=null && !list2.isEmpty()){
			for(AppExamSelfCart index:list2){
				if(appUser.getIszbg()==3){
					index.setPrice(index.getStudentPrice());
				}
				totalAmount+=index.getPrice();
			}
		}
		logger.info("totalAmount:"+totalAmount);
		
		return new GetSelfExamCartResp(list2,totalAmount);
	}

	@Transactional(readOnly = false)
	public SubmitExamRegisterResp submitExamRegister(SubmitExamRegisterReq req) {
		AppAccaUser appUser = req.getAppUser();
		
		List<AppExamSelfCart> list = appExamSelfCartDao.getByUserIdAndPlaceId(appUser.getAccaUserId(), req.getExamPlaceId());
		logger.info("submitExamRegister list:"+list);
		
		if(list==null || list.isEmpty()){
			return new SubmitExamRegisterResp(RespConstants.GLOBAL_PARAM_ERROR);
		}
		
		boolean checkSeatsFlag = true;
		for(AppExamSelfCart index:list){
			int avalableSeats = index.getAvailableSeats();
			if(avalableSeats<=0){
				checkSeatsFlag =false;
				break;
			}
		}
		logger.info("checkSeatsFlag:{}",checkSeatsFlag);
		if(!checkSeatsFlag){
			return new SubmitExamRegisterResp(RespConstants.EXAM_SELF_CART_NOAvailableSeats);
		}
		
		
		AppOrder order = new AppOrder();
		
		order.setExamType(Constants.ExamType.self);
		order.setAccaUserId(appUser.getAccaUserId());
		order.setPhone(appUser.getPhone());
		order.setRegisterName(req.getRegisterName());
		order.setRegisterCardNumber(req.getRegisterCardNumber());
		order.setCardType(req.getCardType());
		order.setRegisterPhone(req.getRegisterPhone());
		order.setRegisterEmail(req.getRegisterEmail());
		order.setRegisterWhiteColorImgId(req.getRegisterWhiteColorImgId());
		order.setAccaRegisterName(req.getAccaRegisterName());
		order.setAccaRegisterPassword(req.getAccaRegisterPassword());
		order.setExamSignupTime(new Date());
		order.setOrderStatus(Constants.OrderStatus.unpay);
		order.setOrg(req.getOrg());
		//order.setAmount(0.0);//备注
		order.setCreateDate(new Date());
		order.setUpdateDate(new Date());
		double amount= 0;
		for(AppExamSelfCart index:list){
			if(appUser.getIszbg()==3){
				index.setPrice(index.getStudentPrice());
			}
			amount+=index.getPrice();
		}
		if (req.getCouponId() != null) {
            Coupon coupon = couponMapper.selectByPrimaryKey(req.getCouponId());
            if (coupon != null) {
                amount -= coupon.getPrice();
                coupon.setConsumed(coupon.getConsumed() + 1);
                couponMapper.updateByPrimaryKeySelective(coupon);
                //删除user对应的优惠券（已使用）
                Example example = new Example(UserCoupon.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("userId",appUser.getAccaUserId() );
                criteria.andEqualTo("couponId", req.getCouponId());
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setDelFlag("1");
                userCouponMapper.updateByExampleSelective(userCoupon, example);
            }
        }
		order.setAmount(amount);
		
		appOrderDao.insert(order);
		logger.info("order:"+order);
		
		List<AppExamSignup> signupList = new ArrayList<AppExamSignup>();
		
		for(AppExamSelfCart index:list){
			AppExamSignup entity = new AppExamSignup();
			entity.setCreateDate(new Date());
			entity.setUpdateDate(new Date());
			entity.setExamType(Constants.ExamType.self);
			entity.setExamId(index.getExamId());
			entity.setExamCourseId(index.getExamCourseId());
			entity.setExamVersionJson(index.getExamVersionJson());
			entity.setAccaUserId(appUser.getAccaUserId());
			entity.setRegisterName(req.getRegisterName());
			entity.setRegisterCardNumber(req.getRegisterCardNumber());
			entity.setCardType(req.getCardType());
			entity.setRegisterPhone(req.getRegisterPhone());
			entity.setRegisterEmail(req.getRegisterEmail());
			entity.setRegisterWhiteColorImgId(req.getRegisterWhiteColorImgId());
			entity.setAccaRegisterName(req.getAccaRegisterName());
			entity.setAccaRegisterPassword(req.getAccaRegisterPassword());
			entity.setExamCourse(index.getCourse());
			entity.setExamCourseName(index.getCourseName());
			entity.setExamSignupTime(new Date());
			entity.setExamSignupStatus(Constants.OrderStatus.unpay);
			entity.setPrice(index.getPrice());
			entity.setOrderId(order.getOrderId());
			entity.setOrg(req.getOrg());
				
			entity.setExamCityId(index.getExamCityId());
			entity.setExamCityName(index.getExamCityName());
			entity.setExamStartTime(index.getExamStartTime());
			entity.setExamEndTime(index.getExamEndTime());
			entity.setExamSignupEndtime(index.getExamSignupEndtime());
			entity.setExamPlaceId(index.getExamPlaceId());
			entity.setExamPlaceName(index.getExamPlaceName());
			entity.setExamDetailAddress(index.getExamDetailAddress());
			entity.setExamPlaceSn(index.getExamPlaceSn());
			entity.setExamPlaceImageId(index.getExamPlaceImageId());
			entity.setExamPlaceContantName(index.getExamPlaceContantName());
			entity.setExamPlaceContantPhone(index.getExamPlaceContantPhone());
			
			entity.setEnglishName(index.getEnglishName());
			entity.setEnglishShortName(index.getEnglishShortName());
			
			
			signupList.add(entity);
		}
		logger.info("signupList:"+signupList);
		
		appExamSignupDao.insertBatch(signupList);
		
		
		
		//循环修改考试座位数量
		for(AppExamSelfCart index:list){
			appExamDao.updateUsedSeats(index.getExamId());
		}
		
		//根据userId和placeId清空购物车
		appExamSelfCartDao.deleteByUserId(appUser.getAccaUserId(), req.getExamPlaceId());
		
		
		
		AppAccaUser entity = new AppAccaUser();
		entity.setAccaUserId(appUser.getAccaUserId());
		entity.setRegisterName(req.getRegisterName());
		entity.setRegisterCardNumber(req.getRegisterCardNumber());
		entity.setRegisterEmail(req.getRegisterEmail());
		entity.setRegisterPhone(req.getRegisterPhone());
		entity.setRegisterWhiteColorImgId(req.getRegisterWhiteColorImgId());
		entity.setCardType(req.getCardType());
		entity.setAccaRegisterName(req.getAccaRegisterName());
		entity.setAccaRegisterPassword(req.getAccaRegisterPassword());
		entity.setOrg(req.getOrg());
		
		appAccaUserDao.updateRegister(entity);
		
		
		AppConfExamTips conf = appConfExamTipsDao.get(new AppConfExamTips("5"));
        String payConfTips =conf.getOfficialExamProcess();
        String title = "报考订单生成确认";
		String content = "同学您好，您的报考订单已经生成，\n支付费用后即进入审核阶段。\n 支付时，一定要注意在汇款备注或附言中注明【汇款账号对应账户姓名】，\n 方便核对到款情况。\n \n 【 账号对应账户姓名！】\n \n 【 账号对应账户姓名！】\n \n 【 账号对应账户姓名！】\n \n 重要的事情说三遍 ：）\n 祝您考试顺利，一次通过！";
		msgSysService.savePushToPersonal(title,content,appUser.getAccaUserId());
		
		return new SubmitExamRegisterResp(signupList,order,payConfTips);
	}


	public String examticket(Model model, Long orderId, Long accaUserId) {

		//AppOfficialOrder order = appOfficialOrderService.get(new AppOfficialOrder(orderId));
		//logger.info("examticket orderId:{},order:{}",orderId,order);
		
		SmallDetailOrder order = appOffiOrderService.getOfficialOrderInfo(new AppOfficialOrder(orderId));
		logger.info("examticket orderId:{},order:{}",orderId,order);
		logger.info("orderId:"+orderId);
		
		String cardNumber= order.getRegisterCardNumber();
		Date birth = StringUtils.getBirthday(cardNumber);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); 
		String birthday = sdf.format(birth);
		String sex ="";
		if (StringUtils.isMale(cardNumber)) {
			sex="Male";
		}else{
			sex="Female";
		}
		
		String givenName ="";
		String surname ="";
		if (order.getRegisterName().length() >0) {
			List<String> names=StringUtils.getName(order.getRegisterName(),2);
			surname = names.get(0);
			givenName = names.get(1);
		}
		
		
		model.addAttribute("order", order);
		model.addAttribute("birthday", birthday);
		model.addAttribute("sex", sex);
		model.addAttribute("accaUserId", accaUserId);
		model.addAttribute("givenName", givenName);
		model.addAttribute("surname", surname);
		
		return "/api/order/order";
		
	}

	public List<AppOfficialExamSignup> findSignups(Long examId, Long accaUserId){
		AppExam ae = appExamDao.getExamInfoByExamId(examId);
		AppOfficialExamSignup signup = new AppOfficialExamSignup();
		signup.setAccaUserId(accaUserId);
		signup.setExamStartTime(ae.getExamStartTime());
		signup.setExamEndTime(ae.getExamEndTime());
		signup.getSqlMap().put("statusStr", String.valueOf(Constants.OrderStatus.delete)+","+Constants.OrderStatus.checkFail+","+Constants.OrderStatus.cancel);
		List<AppOfficialExamSignup> list = examSignupDao.findList(signup);
		return list;
	}

	
	
}