package com.thinkgem.jeesite.acca.web.exam.service;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCart;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSignup;
import com.thinkgem.jeesite.acca.api.plan.service.AppUserLearningPlanService;
import com.thinkgem.jeesite.acca.api.user.dao.AppAccaUserDao;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.coupon.service.CouponService;
import com.thinkgem.jeesite.acca.web.exam.dao.WebOrderDao;
import com.thinkgem.jeesite.acca.web.exam.entity.WebOrder;
import com.thinkgem.jeesite.acca.web.exam.entity.WebSignup;
import com.thinkgem.jeesite.acca.web.exam.utils.SignupComparator;
import com.thinkgem.jeesite.acca.web.register.dao.WebAccaRegisterDao;
import com.thinkgem.jeesite.acca.web.user.dao.WebAccaUserDao;
import com.thinkgem.jeesite.acca.web.user.entity.UserCoupon;
import com.thinkgem.jeesite.acca.web.user.service.UserCouponService;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.sms.SmsUtils;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WebOrderService extends CrudService<WebOrderDao, WebOrder> {
	
	@Autowired
	private WebOrderDao webOrderDao;
	@Autowired
	private WebMsgSysService msgSysService;
	@Autowired
	private AppUserLearningPlanService learningPlanService;
	@Autowired
	private UserCouponService userCouponService;
	@Autowired
    private CouponService couponService;
	
	public List<WebOrder> findOrders(WebOrder order){
		return dao.findOrders(order);
	}
	
	public WebOrder findOne(WebOrder order){
		return dao.findOne(order);
	}
	
	
	/**
	 * 修改报考订单状态
	 * @param order
     */
	@Transactional(readOnly = false)
	public void updOrderStatus(WebOrder order){
		List<WebOrder> orders = dao.findOrders(order);
		dao.updOrderStatus(order);
		if(orders != null && orders.size() != 0){
			order.setOrders(orders);
			dao.updSignupOrderStatus(order);
		}
	}
	
	/**
	 * 修改报考订单状态，审核通过或者需要补传信息或者不通过
	 * @param order
     */
	@Transactional(readOnly = false)
	public void updOrderStatus2(WebOrder order){
		WebOrder wo = webOrderDao.get(order);
		wo.setOrderStatus(order.getOrderStatus());
		User user = UserUtils.getUser();
		/*if(wo.getOrderStatus() == Constants.OrderStatus.checkSuccess){
			AppAccaUser entity = new AppAccaUser();
			entity.setAccaUserId(wo.getAccaUserId());
			entity.setRegisterName(wo.getRegisterName());
			entity.setRegisterCardNumber(wo.getRegisterCardNumber());
			entity.setRegisterEmail(wo.getRegisterEmail());
			entity.setRegisterPhone(wo.getRegisterPhone());
			entity.setRegisterWhiteColorImgId(wo.getRegisterWhiteColorImgId());
			entity.setCardType(wo.getCardType());
			entity.setAccaRegisterName(wo.getAccaRegisterName());
			entity.setAccaRegisterPassword(wo.getAccaRegisterPassword());
			appAccaUserDao.updateRegister(entity);
		}*/
		Date d = new Date();
		wo.setCheckPersonId(user.getId());
		wo.setCheckPersonName(user.getLoginName());
		wo.setCheckTime(d);
		wo.setCheckReason(order.getCheckReason());
		wo.setUpdateDate(d);
		dao.updOrderStatus(wo);
		dao.updSignupStatus(wo);
		/*if(orders != null && orders.size() != 0){
			order.setOrders(orders);
			dao.updSignupOrderStatus(order);
		}*/
		if (wo.getOrderStatus() == Constants.OrderStatus.checkFail){
			//审核不通过,推送消息
			msgSysService.savePushToPersonal("考试报名未通过审核","抱歉，您的报考申请未通过审核，快去查看原因并重新提交报考申请吧。", wo.getAccaUserId());
			SmsUtils.sendSms2Vcode(wo.getPhone(),"您好，您的考试报名申请未通过审核，快去小助手【考点】首页，右上【考试管理】查看原因并重新提交报考申请吧。");
		} else if (wo.getOrderStatus() == Constants.OrderStatus.checkSupplement) {
			//需补传信息,推送消息
			msgSysService.savePushToPersonal("考试报名需要补传信息","抱歉，您的报考申请需要补传信息，快去查看原因并重新提交报考申请吧。", wo.getAccaUserId());
			SmsUtils.sendSms2Vcode(wo.getPhone(),"您好，您的考试报名申请需要补传信息，请于4小时内去小助手【考点】首页，右上【考试管理】查看原因并重新提交报考申请吧。");
		} else if (wo.getOrderStatus() == Constants.OrderStatus.checkSuccess){
            //publishCoupon(wo);
            String courseNames = "";
			//获取报考的信息
			List<WebSignup> signups = wo.getSignups();
			Long accaUserId = wo.getAccaUserId();
			if (signups != null && signups.size() > 0){
				//先排序
				SignupComparator comparator = new SignupComparator();
				Collections.sort(signups , comparator);
				for (WebSignup signup:signups){
					courseNames += signup.getCourse() + ",";
					learningPlanService.updateLearningPlanWhenExam(accaUserId,signup,order.getOrderStatus());
				}
			}
			courseNames = courseNames.substring(0,courseNames.length() - 1);
			//根据考试类型,推送考试提醒
			if (wo.getExamType() == Constants.ExamType.self){
				//自有考试,F1到F4
				msgSysService.savePushToPersonal("考试报名成功","同学您好，您预约的" + courseNames + "考试已经成功审核通过,【考点】首页右上角【考试管理】查看考试详情，并可打印纸质版准考证信息，请安排好时间按时参加考试。", wo.getAccaUserId());
				SmsUtils.sendSms2Vcode(wo.getPhone(),"同学您好，" + courseNames + "考试报名已经成功审核通过,【考点】首页右上角【考试管理】查看考试详情，查看保存准考证信息，请安排好时间按时参加考试。");
			} else if (wo.getExamType() == Constants.ExamType.officialAll){
				//官方考试
				msgSysService.savePushToPersonal("考试报名成功","同学您好，您预约的" + courseNames + "考试已经成功审核通过，记得好好复习并做好充分的考前准备哦。", wo.getAccaUserId());
				SmsUtils.sendSms2Vcode(wo.getPhone(),"同学您好，" + courseNames + "考试报名已经成功审核通过，【考点】首页右上角【考试管理】查看考试详情，请安排好时间按时参加考试。");
			}
		}
		int examType = wo.getExamType();
		List<WebOrder> orders = dao.findOrders(wo);
		if(wo.getOrderStatus() == Constants.OrderStatus.checkFail && (examType&1)==1){//审核失败考位+1
			for(WebOrder index:orders){
				webOrderDao.subUsedSeats(index.getExamId());
			}
		}
		
	}

    /**
	 * 修改已经审核通过订单，延期或调整科目
	 * @param oldorder,newolder
     */
	@Transactional(readOnly = false)
	public void updOrderStatus(WebOrder oldorder,WebOrder neworder){
//		WebOrder wo = webOrderDao.get(oldorder);
//		wo.setOrderStatus(oldorder.getOrderStatus());
//		User user = UserUtils.getUser();
//		Date d = new Date();
//        //订单基本信息复制
//		neworder.setAccaRegisterName(wo.getAccaRegisterName());
//		neworder.setAccaRegisterPassword(wo.getAccaRegisterPassword());
//		neworder.setAccaUserId(wo.getAccaUserId());
//		neworder.setAmount(wo.getAmount());
//		neworder.setCardType(wo.getCardType());
//		neworder.setCheckFail(wo.getCheckFail());
//		neworder.setCheckSuccess(wo.getCheckSuccess());		
//		//neworder.setExamTimeStr(wo.getExamTimeStr());
//		neworder.setExamType(wo.getExamType());
//		neworder.setExamTypeName(wo.getExamTypeName());		
//		neworder.setFile(wo.getFile());
//		neworder.setImage(wo.getImage());
//		neworder.setImg1(wo.getImg1());
//		neworder.setImg2(wo.getImg2());
//
//		neworder.setOrderPayImgId(wo.getOrderPayImgId());
//		
//		neworder.setPhone(wo.getPhone());
//		neworder.setRegisterCardNumber(wo.getRegisterCardNumber());
//		neworder.setRegisterEmail(wo.getRegisterEmail());
//		neworder.setRegisterName(wo.getRegisterName());
//		neworder.setRegisterPhone(wo.getRegisterPhone());
//		neworder.setOrg(wo.getOrg());
//		//neworder.setOrderId(wo.getOrderId());
//		//neworder.setOrderMaps(wo.getOrderMaps());
//		//neworder.setOrders(wo.getOrders());
//		//neworder.setOperaName(wo.getOperaName());
//		
//
//		neworder.setRegisterWhiteColorImgId(wo.getRegisterWhiteColorImgId());
//		neworder.setSignups(wo.getSignups());
//		neworder.setUncheck(wo.getUncheck());
//		//需改变字段赋值
//
//		neworder.setCheckReason("学生考试延期或科目调整");
//		neworder.setExamSignupTime(d);
//		neworder.setCheckTime(d);		
//		neworder.setCreateDate(d);
//		neworder.setUpdateDate(d);
//		neworder.setCheckPersonId(user.getId());
//		neworder.setCheckPersonName(user.getLoginName());
//		
//		
//		
//		neworder.setExpireDate(wo.getExpireDate());
//		neworder.setCourse(wo.getCourse());
//		neworder.setCourseNames(wo.getCourseNames());
//		neworder.setExamId(wo.getExamId());
//		neworder.setExamSignupId(wo.getExamSignupId());
//		neworder.setOrderStatus(wo.getOrderStatus());
//		neworder.setOrderStatusName(wo.getOrderStatusName());
//
//		wo.setUpdateDate(d);
//		dao.insert(neworder);
//		
//		List<AppExamSignup> signupList = new ArrayList<AppExamSignup>();		
//		for(AppExamSelfCart index:list){
//			AppExamSignup entity = new AppExamSignup();
//			entity.setCreateDate(new Date());
//			entity.setUpdateDate(new Date());
//			entity.setExamType(Constants.ExamType.self);
//			entity.setExamId(index.getExamId());
//			entity.setExamCourseId(index.getExamCourseId());
//			entity.setExamVersionJson(index.getExamVersionJson());
//			entity.setAccaUserId(oldorder.getAccaUserId());
//			entity.setRegisterName(req.getRegisterName());
//			entity.setRegisterCardNumber(req.getRegisterCardNumber());
//			entity.setCardType(req.getCardType());
//			entity.setRegisterPhone(req.getRegisterPhone());
//			entity.setRegisterEmail(req.getRegisterEmail());
//			entity.setRegisterWhiteColorImgId(req.getRegisterWhiteColorImgId());
//			entity.setAccaRegisterName(req.getAccaRegisterName());
//			entity.setAccaRegisterPassword(req.getAccaRegisterPassword());
//			entity.setExamCourse(index.getCourse());
//			entity.setExamCourseName(index.getCourseName());
//			entity.setExamSignupTime(new Date());
//			entity.setExamSignupStatus(Constants.OrderStatus.unpay);
//			entity.setPrice(index.getPrice());
//			entity.setOrderId(order.getOrderId());
//			entity.setOrg(req.getOrg());
//			
//			entity.setExamCityId(index.getExamCityId());
//			entity.setExamCityName(index.getExamCityName());
//			entity.setExamStartTime(index.getExamStartTime());
//			entity.setExamEndTime(index.getExamEndTime());
//			entity.setExamSignupEndtime(index.getExamSignupEndtime());
//			entity.setExamPlaceId(index.getExamPlaceId());
//			entity.setExamPlaceName(index.getExamPlaceName());
//			entity.setExamDetailAddress(index.getExamDetailAddress());
//			entity.setExamPlaceSn(index.getExamPlaceSn());
//			entity.setExamPlaceImageId(index.getExamPlaceImageId());
//			entity.setExamPlaceContantName(index.getExamPlaceContantName());
//			entity.setExamPlaceContantPhone(index.getExamPlaceContantPhone());
//			
//			entity.setEnglishName(index.getEnglishName());
//			entity.setEnglishShortName(index.getEnglishShortName());
//						
//			signupList.add(entity);
//		}
//		logger.info("signupList:"+signupList);
//		
//		appExamSignupDao.insertBatch(signupList);
//		
//		
//		
//		//循环修改考试座位数量
//		for(AppExamSelfCart index:list){
//			appExamDao.updateUsedSeats(index.getExamId());
//		}
//		dao.updSignupStatus(neworder);
//		
//		/*if(wo.getOrderStatus() == Constants.OrderStatus.checkSuccess){
//			AppAccaUser entity = new AppAccaUser();
//			entity.setAccaUserId(wo.getAccaUserId());
//			entity.setRegisterName(wo.getRegisterName());
//			entity.setRegisterCardNumber(wo.getRegisterCardNumber());
//			entity.setRegisterEmail(wo.getRegisterEmail());
//			entity.setRegisterPhone(wo.getRegisterPhone());
//			entity.setRegisterWhiteColorImgId(wo.getRegisterWhiteColorImgId());
//			entity.setCardType(wo.getCardType());
//			entity.setAccaRegisterName(wo.getAccaRegisterName());
//			entity.setAccaRegisterPassword(wo.getAccaRegisterPassword());
//			appAccaUserDao.updateRegister(entity);
//		}*/
//
//
//		/*if(orders != null && orders.size() != 0){
//			order.setOrders(orders);
//			dao.updSignupOrderStatus(order);
//		}*/
//		if (wo.getOrderStatus() == Constants.OrderStatus.checkFail){
//			//审核不通过,推送消息
//			msgSysService.savePushToPersonal("考试报名未通过审核","抱歉，您的报考申请未通过审核，快去查看原因并重新提交报考申请吧。", wo.getAccaUserId());
//		} else if (wo.getOrderStatus() == Constants.OrderStatus.checkSuccess){
//			String courseNames = "";
//			//获取报考的信息
//			List<WebSignup> signups = wo.getSignups();
//			Long accaUserId = wo.getAccaUserId();
//			if (signups != null && signups.size() > 0){
//				//先排序
//				SignupComparator comparator = new SignupComparator();
//				Collections.sort(signups , comparator);
//				for (WebSignup signup:signups){
//					courseNames += signup.getCourse() + ",";
//					learningPlanService.updateLearningPlanWhenExam(accaUserId,signup,order.getOrderStatus());
//				}
//			}
//			courseNames = courseNames.substring(0,courseNames.length() - 1);
//			//根据考试类型,推送考试提醒
//			if (wo.getExamType() == Constants.ExamType.self){
//				//自有考试,F1到F4
//				msgSysService.savePushToPersonal("考试报名成功","同学您好，您预约的" + courseNames + "考试已经成功审核通过,【考点】首页右上角【考试管理】查看考试详情，并可打印纸质版准考证信息，请安排好时间按时参加考试。", wo.getAccaUserId());
//			} else if (wo.getExamType() == Constants.ExamType.officialAll){
//				//官方考试
//				msgSysService.savePushToPersonal("考试报名成功","同学您好，您预约的" + courseNames + "考试已经成功审核通过，记得好好复习并做好充分的考前准备哦。", wo.getAccaUserId());
//			}
//		}
//		int examType = wo.getExamType();
//		List<WebOrder> orders = dao.findOrders(wo);
//		if(wo.getOrderStatus() == Constants.OrderStatus.checkFail && (examType&1)==1){//审核失败考位+1
//			for(WebOrder index:orders){
//				webOrderDao.subUsedSeats(index.getExamId());
//			}
//		}
		
	}
	
	@Transactional(readOnly = false)
	public void updExpireOrders(List<WebOrder> list){
		logger.info("updExpireOrders start!!!!!!!");
		for(WebOrder wo : list){
			//超过四个小时未支付,添加消息推送提醒
			Long userId = wo.getAccaUserId();
            String title = "考试席位已自动取消";
            String content = "同学您好，您预约的考试席位由于4小时内未收到您的支付凭证提交信息，预约考试席位被自动取消，如需预约考试请重新提交。";
			if (wo.getOrderStatus() == Constants.OrderStatus.checkSupplement) {
			    content = "同学您好，您预约的考试席位由于4小时内未收到您的补传信息，预约考试席位被自动取消，如需预约考试请重新提交。";
            }
			msgSysService.savePushToPersonal(title,content,userId);
			//取消订单,修改相关表的状态
			List<WebOrder> orders = dao.findOrders(wo);
			wo.setOrderStatus(Constants.OrderStatus.cancel);
			dao.updOrderStatus(wo);
			dao.updSignupStatus(wo);
			
			int examType = wo.getExamType();
			if( (examType & 1)==1){
				if(orders!=null && !orders.isEmpty()){
					for(WebOrder index:orders){
						Long examId = index.getExamId();
						dao.subUsedSeats(examId);
					}
				}
			}
		}
		logger.info("updExpireOrders end!!!!!!!");
	}
	
	@Transactional(readOnly = false)
	public void updSignupStatus(WebOrder order){
		dao.updSignupStatus(order);
	}

	/**
	 * 订单四小时未支付取消前半小时内推送提醒
	 * @param orders
     */
	@Transactional(readOnly = false)
	public void orderCancelTips(List<WebOrder> orders) {
		logger.info("orderCancelTips start!!!!!!!");
		for(WebOrder order : orders){
			Long userId = order.getAccaUserId();
			String title = "支付提醒";
			//获取创建时间之后四小时的时间
			Date expireDate = TimeUtils.beforeMinute(order.getCreateDate(), 240);
			Date now = new Date();
			long between_minus = (expireDate.getTime() - now.getTime())/(1000*60);
			String content = "您有一笔待支付的订单，将于" + between_minus + "分钟后自动取消，请您尽快支付！";
			msgSysService.savePushToPersonal(title,content,userId);
		}
		logger.info("orderCancelTips end!!!!!!!");
	}

	public List<WebOrder> getExpireOrder(WebOrder order) {
	    return dao.findExpireOrder(order);

    }
	public static void main(String[] args) {
		Date expireDate = TimeUtils.beforeMinute(new Date(), 240);
		System.out.println(expireDate);
	}
}
