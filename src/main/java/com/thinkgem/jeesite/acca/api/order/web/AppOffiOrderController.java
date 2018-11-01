package com.thinkgem.jeesite.acca.api.order.web;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialOrder;
import com.thinkgem.jeesite.acca.api.exam.service.AppOfficialOrderService;
import com.thinkgem.jeesite.acca.api.exam.util.ExamConstant;
import com.thinkgem.jeesite.acca.api.model.request.*;
import com.thinkgem.jeesite.acca.api.model.response.GetOfficialOrderInfoResp;
import com.thinkgem.jeesite.acca.api.order.entity.SmallOrder;
import com.thinkgem.jeesite.acca.api.order.service.AppOffiOrderService;
import com.thinkgem.jeesite.acca.api.plan.service.AppUserLearningPlanService;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.coupon.entity.Coupon;
import com.thinkgem.jeesite.acca.web.user.entity.SmallCoupon;
import com.thinkgem.jeesite.acca.web.user.service.UserCouponService;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 考试订单controller
 * @author Michael
 * @version 2016-08-10
 */
@Api(value = "${apiPath}/order", description = "考试订单模块")
@Controller
@RequestMapping(value = "${apiPath}/order")
public class AppOffiOrderController extends BaseController {

	@Autowired
	private AppOffiOrderService appOffiOrderService;
	@Autowired
	private AppOfficialOrderService orderService;
	@Autowired
	private AppUserLearningPlanService learningPlanService;
	@Autowired
	private UserCouponService userCouponService;
	
	@ApiOperation(value = "获取考试订单列表", notes = "获取考试订单列表")
	@RequestMapping(value = "getOrderListByStatis", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<SmallOrder> getOrderListByStatis(@RequestBody GetOrderListByStatisReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<SmallOrder>(respFlag);
		}
		AppOfficialOrder aoo = new AppOfficialOrder(req);
		aoo.getSqlMap().put("idStr", String.valueOf(Constants.OrderStatus.delete));
		return new BasePageResponse<SmallOrder>(appOffiOrderService.getOrderListByStatis(aoo));
	}
	
	@ApiOperation(value = "按照月份获取F5-P9订单信息", notes = "按照月份获取F5-P9订单信息")
	@RequestMapping(value = "getOrderByMonth", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<SmallOrder> getOrderByMonth(@RequestBody GetOrderByMonthReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<SmallOrder>(respFlag);
		}
		AppOfficialOrder order = new AppOfficialOrder(req);
		order.setExamType(Constants.ExamType.officialAll);
		order.getSqlMap().put("idStr", String.valueOf(Constants.OrderStatus.delete)+","+Constants.OrderStatus.checkFail+","+Constants.OrderStatus.cancel);
		return new BasePageResponse<SmallOrder>(appOffiOrderService.getOrderListByStatis(order));
	}
	
	@ApiOperation(value = "订单详情页面", notes = "订单详情页面")
	@RequestMapping(value = "getOfficialOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public GetOfficialOrderInfoResp getOfficialOrderInfo(@RequestBody GetOfficialOrderInfoReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new GetOfficialOrderInfoResp(respFlag);
		}
		return appOffiOrderService.getOfficialOrderInfo(req);
		//return new GetOfficialOrderInfoResp(appOffiOrderService.getOfficialOrderInfo(new AppOfficialOrder(req)));
	}
	
	@ApiOperation(value = "取消订单", notes = "取消订单")
	@RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
	@ResponseBody 
	public BaseResponse cancelOrder(@RequestBody CancelOrderReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BaseResponse(respFlag);
		}
		AppOfficialOrder order = orderService.get(new AppOfficialOrder(req.getOrderId()));
		if(order.getOrderStatus() == null || order.getOrderStatus() != ExamConstant.SIGNUP_TYPE_WAIT_PAY){
			return new BaseResponse(RespConstants.GLOBAL_OTHER);
		} else {
			orderService.updAll(new AppOfficialOrder(req.getOrderId(), ExamConstant.SIGNUP_TYPE_WAIT_CANCEL));
			return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
		}
	}
	
	@ApiOperation(value = "删除订单", notes = "删除订单")
	@RequestMapping(value = "deleteOrder", method = RequestMethod.POST)
	@ResponseBody 
	public BaseResponse deleteOrder(@RequestBody CancelOrderReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BaseResponse(respFlag);
		}
		AppOfficialOrder order = orderService.get(new AppOfficialOrder(req.getOrderId()));
		if(order.getOrderStatus() == null || order.getOrderStatus() != Constants.OrderStatus.cancel){
			return new BaseResponse(RespConstants.GLOBAL_OTHER);
		} else {
			orderService.updAll(new AppOfficialOrder(req.getOrderId(), Constants.OrderStatus.delete));
			return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
		}
	}
	
	@ApiOperation(value = "保存支付凭证,F1-F4考试，F5-P7考试公用此接口", notes = "保存支付凭证,F1-F4考试，F5-P7考试公用此接口")
	@RequestMapping(value = "saveOrderPay.do" ,method=RequestMethod.POST)
	public @ResponseBody BaseResponse saveOrderPay(@RequestBody SaveOrderPayReq req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new BaseResponse(respCode);
		}
		
		return orderService.saveOrderPay(req.getOrderId(),req.getOrderPayImgId(), req.getPayerAccountName(), req.getAppUser());
	}

	@ApiOperation(value = "待支付状态,保存学习规划", notes = "待支付状态,保存学习规划")
	@RequestMapping(value = "saveLearningPlan.do" ,method=RequestMethod.POST)
	public @ResponseBody BaseResponse saveLearningPlan(@RequestBody SaveLearningPlanReq req) {
		int respCode = req.isCorrectParams();
		if(respCode != RespConstants.GLOBAL_SUCCESS){
			return new BaseResponse(respCode);
		}
		return learningPlanService.saveLearningPlan(req);
	}

	@ApiOperation(value = "根据用户id获得代金券", notes = "根据用户id获得代金券")
	@RequestMapping(value = "getCouponList.do" ,method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<SmallCoupon> getCouponList(@RequestBody BaseRequest req) {
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<SmallCoupon>(respFlag);
		}
		List<SmallCoupon> couponListByUserId = userCouponService.getCouponListByUserId(req.getAppUserId());
		return new BasePageResponse<>(couponListByUserId);
	}
	
}
