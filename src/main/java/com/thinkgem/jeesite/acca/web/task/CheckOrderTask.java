package com.thinkgem.jeesite.acca.web.task;

import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.exam.entity.WebOrder;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamSelfCartService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamSignupService;
import com.thinkgem.jeesite.acca.web.exam.service.WebOrderService;
import com.thinkgem.jeesite.freetek.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 定时检查任务类
 * @author Michael
 *
 */
@Service 
@Lazy(false)
public class CheckOrderTask {
	
	@Autowired
	private WebOrderService orderService;
	@Autowired
	private WebExamSignupService signupService;
	
	@Autowired
	private WebExamSelfCartService webExamSelfCartService;

	/**
	 * 考试报考超过四个小时未支付,添加消息推送提醒
	 */
	@Scheduled(fixedDelay=300000)
	public void orderTaskMethod(){
		Date expireDate = TimeUtils.beforeMinute(new Date(), -240);
		WebOrder wo = new WebOrder();
		wo.setExpireDate(expireDate);
		List<WebOrder> orders = orderService.getExpireOrder(wo);
		orderService.updExpireOrders(orders);
	}

	/**
	 * 考试报考超过四个小时未支付,提前半小时添加消息推送提醒
	 */
	//@Scheduled(fixedDelay=1800000)
	public void orderCancelTips(){
		//查询三个半小时未支付的订单
		Date expireDate = TimeUtils.beforeMinute(new Date(), -210);
		WebOrder condition = new WebOrder();
		condition.setExpireDate(expireDate);
		condition.setOrderStatus(Constants.OrderStatus.unpay);
		List<WebOrder> orders = orderService.findList(condition);
		orderService.orderCancelTips(orders);
	}

	@Scheduled(fixedDelay=1200000)
	public void cancelExamSelfCart(){
		webExamSelfCartService.cancelExamSelfCart();
	}

	@Scheduled(fixedDelay=180000)
	public void cancelCoupon(){
		webExamSelfCartService.cancelCoupon();
	}

	/**
	 * 考试提醒,每天早上九点触发
	 */
	@Scheduled(cron = "0 0 9 * * ?")
	public void examTips(){
		signupService.updateSignupOrTips();
	}
}
