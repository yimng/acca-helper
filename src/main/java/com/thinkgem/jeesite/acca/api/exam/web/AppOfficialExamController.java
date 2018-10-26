package com.thinkgem.jeesite.acca.api.exam.web;

import java.util.List;

import com.thinkgem.jeesite.acca.api.model.request.SeasonOffPlaceReq;
import com.thinkgem.jeesite.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExam;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamCourse;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamMonth;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamPlace;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialOrder;
import com.thinkgem.jeesite.acca.api.exam.entity.Appointment;
import com.thinkgem.jeesite.acca.api.exam.entity.SmallCourseInfo;
import com.thinkgem.jeesite.acca.api.exam.entity.SmallPlace;
import com.thinkgem.jeesite.acca.api.exam.service.AppOfficialExamCourseService;
import com.thinkgem.jeesite.acca.api.exam.service.AppOfficialExamPlaceService;
import com.thinkgem.jeesite.acca.api.exam.service.AppOfficialExamService;
import com.thinkgem.jeesite.acca.api.exam.service.AppOfficialOrderService;
import com.thinkgem.jeesite.acca.api.exam.util.ExamConstant;
import com.thinkgem.jeesite.acca.api.model.request.AppOfficialOrderReq;
import com.thinkgem.jeesite.acca.api.model.request.ExamStartTimeReq;
import com.thinkgem.jeesite.acca.api.model.request.OffPlaceReq;
import com.thinkgem.jeesite.acca.api.model.response.OrderInfoResponse;
import com.thinkgem.jeesite.acca.api.order.entity.SmallOrder;
import com.thinkgem.jeesite.acca.api.order.service.AppOffiOrderService;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;

/**
 * 官方考试Controller
 * @author Michael
 *
 */

@Api(value = "${apiPath}/exam", description = "exam考试模块")
@Controller
@RequestMapping(value = "${apiPath}/exam")
public class AppOfficialExamController {
	
	@Autowired
	private AppOfficialExamService appOfficialExamService;
	
	@Autowired
	private AppOfficialExamCourseService appOfficialExamCourseService;
	
	@Autowired
	private AppOfficialExamPlaceService appOfficialExamPlaceService;
	
	@Autowired
	private AppOfficialOrderService appOfficialOrderService;
	
	@Autowired
	private AppOffiOrderService appOffiOrderService;
	
	@ApiOperation(value = "获取考试月份", notes = "获取考试月份")
	@RequestMapping(value = "getExamMonth.do", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<AppOfficialExamMonth> getExamMonth(@RequestBody BaseRequest req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppOfficialExamMonth>(respFlag);
		}
		return new BasePageResponse<AppOfficialExamMonth>(appOfficialExamService.getExamMonth(new AppOfficialExam()));
	}
	
	@ApiOperation(value = "获取官方考试笔试科目", notes = "获取官方考试笔试科目")
	@RequestMapping(value = "getOfficialExamPenCourseList.do", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<AppOfficialExamCourse> getOfficialExamPenCourseList(@RequestBody ExamStartTimeReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppOfficialExamCourse>(respFlag);
		}
		AppOfficialExamCourse course = new AppOfficialExamCourse(req);
		course.setExamType(ExamConstant.OFFI_EXAM_WRI);
		return new BasePageResponse<AppOfficialExamCourse>(appOfficialExamCourseService.getOfficialExamCourseList(course));
	}

	@ApiOperation(value = "获取官方考试机试科目", notes = "获取官方考试机试科目")
	@RequestMapping(value = "getOfficialExamCourseList.do", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<AppOfficialExamCourse> getOfficialExamCourseList(@RequestBody ExamStartTimeReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<AppOfficialExamCourse>(respFlag);
		}
		AppOfficialExamCourse course = new AppOfficialExamCourse(req);
		course.setExamType(ExamConstant.OFFI_EXAM_MAC);
		return new BasePageResponse<AppOfficialExamCourse>(appOfficialExamCourseService.getOfficialExamCourseList(course));
	}

	@ApiOperation(value = "获取官方考试笔记/机试科目细节", notes = "获取官方考试笔记/机试科目细节")
	@RequestMapping(value = "getOfficialExamCourseDetailList.do", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<AppOfficialExamCourse> getOfficialExamCourseDetailList(@RequestBody ExamStartTimeReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<>(respFlag);
		}
		AppOfficialExamCourse course = new AppOfficialExamCourse(req);
		return new BasePageResponse<>(appOfficialExamCourseService.getOfficialExamCourseDetailList(course));
	}
	
	@ApiOperation(value = "根据所选科目对应的考试获取考试地点列表 ", notes = "根据所选科目对应的考试获取考试地点列表")
	@RequestMapping(value = "getOfficialExamPlaceList.do", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<SmallPlace> getOfficialExamPlaceList(@RequestBody OffPlaceReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<SmallPlace>(respFlag);
		}
		AppOfficialExamPlace place = new AppOfficialExamPlace();
		if(req.getExamCourseIds()!=null && req.getExamCourseIds().length!=0 ){
			StringBuilder idStr = new StringBuilder();
			int i = 0;
			for(Long examCourseId : req.getExamCourseIds()){
				if(i == req.getExamCourseIds().length - 1){
					idStr.append(examCourseId);
				} else {
					idStr.append(examCourseId+",");
				}
				i++;
			}
			place.getSqlMap().put("idStr", idStr.toString());
			place.getSqlMap().put("idNum", String.valueOf(req.getExamCourseIds().length));
		} else {
			place.getSqlMap().put("idStr", "");
			place.getSqlMap().put("idNum", "");
		}
		place.setExamStartTimeStr(req.getExamStartTimeStr());
		place.setExamType(req.getExamType());
		return new BasePageResponse<SmallPlace>(appOfficialExamPlaceService.getExamPlace(place));
	}

	@ApiOperation(value = "根据所选考试季获取考试地点列表 ", notes = "根据所选考试季获取考试地点列表")
	@RequestMapping(value = "getOfficialExamPlaceListBySeason.do", method = RequestMethod.POST)
	@ResponseBody
	public BasePageResponse<SmallPlace> getOfficialExamPlaceListBySeason(@RequestBody SeasonOffPlaceReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new BasePageResponse<SmallPlace>(respFlag);
		}
		AppOfficialExamPlace place = new AppOfficialExamPlace();


		place.setExamStartTimeStr(req.getExamStartTimeStr());
		place.setExamCityId(req.getCityId());
		return new BasePageResponse<SmallPlace>(appOfficialExamPlaceService.getExamPlaceBySeason(place));
	}
	
	@ApiOperation(value = "预约报考，生成订单 ", notes = "预约报考，生成订单")
	@RequestMapping(value = "submitOfficialExamRegiseter.do", method = RequestMethod.POST)
	@ResponseBody
	public OrderInfoResponse submitOfficialExamRegiseter(@RequestBody AppOfficialOrderReq req){
		int respFlag = req.isCorrectParams();
		if (respFlag != RespConstants.GLOBAL_SUCCESS) {
			return new OrderInfoResponse(respFlag);
		} else {
			Appointment macInfo = req.getMacAppInfo();
			Appointment penInfo = req.getPenAppInfo();
			if(macInfo != null && penInfo != null){
				for(SmallCourseInfo info : macInfo.getCourseInfos()){
					if(penInfo.getCourseInfos().contains(info)){
						return new OrderInfoResponse(RespConstants.GLOBAL_PARAM_ERROR);
					}
				}
			}
		}
		AppOfficialOrder appOfficialOrder = new AppOfficialOrder();
		appOfficialOrder.setExamTimeStr(req.getExamTimeStr());
		appOfficialOrder.setAppUserId(req.getAppUserId());
		AppOfficialOrder ord = new AppOfficialOrder();
		ord.setAccaUserId(req.getAccaUserId());
		ord.setExamTimeStr(req.getExamTimeStr());
		ord.setExamType(Constants.ExamType.officialAll);
		ord.getSqlMap().put("idStr", String.valueOf(Constants.OrderStatus.delete)+","+Constants.OrderStatus.checkFail+","+Constants.OrderStatus.cancel);
		List<SmallOrder> order = appOffiOrderService.getOrderListByStatis(ord);
		//AppOfficialOrder order = null;
		if(order != null && order.size() > 0){
			return new OrderInfoResponse(RespConstants.GLOBAL_OTHER);
		} else {
			AppOfficialOrder order1 = new AppOfficialOrder(req);
			order1.setExamType(ExamConstant.OFFI_EXAM_MAC + ExamConstant.OFFI_EXAM_WRI);
			return appOfficialOrderService.saveOrder(order1, req);
		}
		
	}
	
}
