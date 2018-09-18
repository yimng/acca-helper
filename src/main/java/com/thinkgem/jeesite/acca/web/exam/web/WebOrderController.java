package com.thinkgem.jeesite.acca.web.exam.web;

import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.exam.entity.SmallCourse;
import com.thinkgem.jeesite.acca.web.exam.entity.SmallVersion;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExam;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamPlace;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSignup;
//import com.thinkgem.jeesite.acca.web.exam.entity.WebExamSignup;
import com.thinkgem.jeesite.acca.web.exam.entity.WebOrder;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamPlaceService;
import com.thinkgem.jeesite.acca.web.exam.service.WebOrderService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamSignupService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamService;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamCourseService;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.sms.SmsUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping(value = "${adminPath}/web/order")
public class WebOrderController extends BaseController {

	@Autowired
	private WebOrderService orderService;
	@Autowired
	private WebExamService examService;
	@Autowired
	private WebExamPlaceService examPlaceService;
	@Autowired
	private WebExamSignupService wesService;
	@Autowired
	private WebExamCourseService wecService;
	@Autowired
	private WebMsgSysService wms;
	
	@RequiresPermissions("web:order:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebOrder webOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(!Constants.ORDER_STATUS_TYPES.keySet().contains(webOrder.getOrderStatus())){
			webOrder.setOrderStatus(Constants.OrderStatus.uncheckd);
		}
		//获取登录用户的信息
		User user = UserUtils.getUser();
		String userId = user.getId();
		//先通过用户id查询是否有绑定考点
		WebExamPlace place = new WebExamPlace();
		place.setSysUserId(userId);
		//查询考点列表
		List<WebExamPlace> placesList = examPlaceService.findList(place);
		//如果考点列表不为空,说明该用户绑定有考点,则需要通过考点信息获取相关的订单信息
		if (placesList != null && placesList.size() > 0){
			String places = "";
			for (WebExamPlace p:placesList){
				places += p.getExamPlaceId() + ",";
			}
			places = places.substring(0,places.length() - 1);
			webOrder.getSqlMap().put("places",places);
		}
		Page<WebOrder> page = orderService.findPage(new Page<WebOrder>(request, response), webOrder); 
		List<WebOrder> list = page.getList();
		if(list == null || list.size() == 0){
			model.addAttribute("page", page);
		} else {
			for(WebOrder order : list){	
				WebOrder ww = orderService.get(order);
				if(ww!= null){
					order.setSignups(ww.getSignups());
				}
			}
			
			/*//获得页内所有订单id字符串
			StringBuilder sb = new StringBuilder();
			for(WebOrder order : list){
				sb.append(order.getOrderId() + ",");
			}			
			WebOrder wo = new WebOrder();
			wo.getSqlMap().put("idStr", sb.substring(0, sb.length()-1));
			//获得所有对应订单的订单的科目
			List<WebOrder> orders = orderService.findOrders(wo);			
			for(WebOrder order : list){
				HashSet<String> hs = new HashSet<String>();
				for(WebOrder order2 : orders){
					if(order.getOrderId().equals(order2.getOrderId())){
						hs.add(order2.getCourse());
					}
				}
				StringBuilder sb2 = new StringBuilder();
				for(Iterator<String> i = hs.iterator();i.hasNext();){
					String s = i.next();
					if(s != null){
						sb2.append(s+"/");
					}
				}
				order.setCourseNames(sb2.length() != 0 ? sb2.substring(0, sb2.length()-1) : null);
			}*/
			
			
			model.addAttribute("page", page);
		}
		return "web/exam/order/orderList";
	}
	

	@RequiresPermissions("web:order:view")
	@RequestMapping(value = "detail")
	public String detail(WebOrder webOrder, Model model){
		//System.out.println("webOrder"+webOrder);
		WebOrder wo=orderService.get(webOrder);			
		//System.out.println("wo:"+wo);
		if(wo.getOrderStatus().equals(wo.getCheckSuccess())){
			WebExam webExam = new WebExam();
			User user = UserUtils.getUser();
			String userId = user.getId();
			//先通过用户id查询是否有绑定考点
			WebExamPlace place = new WebExamPlace();
			place.setSysUserId(userId);
			//查询考点id列表
			List<WebExamPlace> placesList;
			//当为自有考试的时候,去查询考点信息
			if(webExam.getExamType() == null){
				webExam.setExamType(Constants.ExamType.self);
			}
			placesList = examPlaceService.findList(place);
			//如果id列表不为空
			if (placesList != null && placesList.size() > 0){
				webExam.getSqlMap().put("sysUserId",userId);
			}
			if(webExam.getExamFlag() == null){
				webExam.setExamFlag(Constants.ExamFlag.now);
			}
			//获取考点列表
			List<WebExamPlace> places = examService.selectPlaceByType(webExam);
			//model.addAttribute("places", places);
			if (places != null && places.size() > 0) {
                webExam.setPlaces(places);
            }
			//获取考试列表	
			List<WebExam> we1 = examService.findList(webExam);
			System.out.println(we1.toString());
			Map<Long, String> mwe =new  TreeMap<Long, String>();
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			DateFormat format2 = new SimpleDateFormat("HH:mm");
			for (WebExam w:we1){
				  mwe.put(w.getExamId(), w.getExamCityName() + " " 
						  + w.getExamPlaceName() 
						  +" [  "+format1.format(w.getExamStartTime())+" - " 
						  +format2.format(w.getExamEndTime())
						  + " ] 考位：" 
						  + w.getUsedSeats() 
						  +"/"+w.getTotalSeats()+"");
			}
			System.out.println(mwe.toString());
			model.addAttribute("exams", mwe);
		}
		model.addAttribute("webOrder", wo);
		return "web/exam/order/detail";
	}
	
	@RequiresPermissions("web:order:edit")
	@RequestMapping(value = "refuse")
	public String refuse(WebOrder order, Model model){
		model.addAttribute("order", order);
		return "web/exam/order/refuse";
	}
	
	@RequiresPermissions("web:order:edit")
	@RequestMapping(value = "audit")
	public String audit(WebOrder webOrder, Integer flag, Model model, RedirectAttributes redirectAttributes){
		WebOrder order = orderService.findOne(webOrder);
		System.out.println("webOder: "+ webOrder);
		if(order.getOrderStatus() == null || order.getOrderStatus() != Constants.OrderStatus.uncheckd){
			addMessage(redirectAttributes, "操作失败！订单状态已被改变");
		} else {
			if(flag == null || flag == 0){
				webOrder.setOrderStatus(Constants.OrderStatus.checkFail);				
			} else if (flag == 1){
				webOrder.setOrderStatus(Constants.OrderStatus.checkSuccess);				
			} else if (flag == 2) {
				webOrder.setOrderStatus(Constants.OrderStatus.checkSupplement);
			}
			orderService.updOrderStatus2(webOrder);
			addMessage(redirectAttributes, "操作成功！");
		}
		return "redirect:" + adminPath + "/web/order";
	}
	
	
	
	/**
	 * 修改或延期考试
	 * @param examSignupId,targetexamId,courseId,versionStr
	 * @return
     */
	@RequiresPermissions("web:order:edit")
	@RequestMapping(value = "/changeExam")
	@ResponseBody
	public String changeExam(String examSignupId,String targetexamId,Long courseId,String versionStr,Model model) {
		
//		examSignupId:val,
//		examId:examId,
//		courseId:courseId,
//		versionStr:versionStr
		//修改考试席位数
		System.out.println("=================================================================================================================");
		WebExam targetwe = new WebExam();
		WebExamSignup wes=wesService.get(examSignupId);		
		String oldexamId = wes.getExamId().toString();
		if(!oldexamId.equals(targetexamId)){
			WebExam oldwe = examService.getById(oldexamId);
			targetwe = examService.getById(targetexamId);
			Integer us = targetwe.getUsedSeats(); 
			if(us == null){
				us = 0;
			}
			if(targetwe.getTotalSeats() - us < 1){
				return "操作失败，目标考试已无可选考位！";
			} else {
				targetwe.setTotalSeats(targetwe.getTotalSeats() - 1);
			}
			examService.updateSeat(targetwe);
			oldwe.setTotalSeats(oldwe.getTotalSeats() + 1);			
			examService.updateSeat(oldwe);			
		}else{
			targetwe = examService.get(oldexamId);
		}
		//修改报考信息	
		if(versionStr==null){
			versionStr=courseId + ",null,null";
		}
		String[] array = versionStr.split(",");
		WebExamPlace wep = examPlaceService.get(targetwe.getExamPlaceId().toString());
		System.out.println("【获得目标考试考点信息 wep：】 "+wep.toString());
		WebExamCourse wec= wecService.get(array[0]);
		Date n=new Date();
		wes.setExamCourseId(courseId);	
		//wes.setUpdateBy(user);
		wes.setUpdateDate(n);
		wes.setExamId(targetwe.getExamId());
		wes.setExamCourseId(wec.getExamCourseId());
		wes.setExamCourse(wec.getCourse());
		wes.setExamVersionJson("{\"examVersionId\":"+array[1]+",\"examVersionName\":\""+array[2]+"\"}");		
		wes.setExamCourseName(wec.getCourseName());		
		wes.setExamSignupTime(n);
		wes.setPrice(wec.getPrice());
		wes.setExamStartTime(targetwe.getExamStartTime());
		wes.setExamEndTime(targetwe.getExamEndTime());
		wes.setExamDetailAddress(wep.getExamDetailAddress());
		wes.setExamCityId(targetwe.getExamCityId());
		wes.setExamCityName(targetwe.getExamCityName());
		wes.setExamPlaceName(targetwe.getExamPlaceName());
		wes.setExamPlaceSn(wep.getExamPlaceSn());
		wes.setExamPlaceImageId(wep.getExamPlaceImageId());
		wes.setExamPlaceContantName(wep.getExamPlaceContantName());
		wes.setExamPlaceContantPhone(wep.getExamPlaceContantPhone());
		wes.setEnglishName(wec.getEnglishName());
		wes.setEnglishShortName(wec.getEnglishShortName());
		wes.setExamPlaceId(wep.getExamPlaceId());		
		wesService.changeExam(wes);
		WebOrder order = orderService.get(wes.getOrderId().toString());
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		StringBuffer checkReason = new StringBuffer();		
		if(order != null){
			//DateFormat format2 = new SimpleDateFormat("HH:mm");
			User user = UserUtils.getUser();
			checkReason.append(order.getCheckReason());		
			checkReason.append("<br>").append(user.getName()).append(" 于 [")
			.append(format1.format(n)).append("] 延期或修改了考试信息。考试修改为【")
			.append(wes.getExamPlaceName()).append(format1.format(wes.getExamStartTime())).append("】");
			order.setCheckReason(checkReason.toString());
			orderService.updOrderStatus2(order);
		}
		String phone = wes.getRegisterPhone(); 
		String title = "考试订单修改/延期";
		String content = wes.getRegisterName().toString() + "您的机考报名订单已经修改为：【"+wes.getExamPlaceName() + format1.format(wes.getExamStartTime())+"】详情请进入小助手-考点儿-考试管理内查看,并在已报名订单内选择保存准考证。";;
		String msg = content;
		//发送提醒短信
		wms.savePushToPersonal(title,content,wes.getAccaUserId());
		SmsUtils.sendSms2Vcode(phone,msg);
		//System.out.println("【修改后order：】 "+order.toString());
		System.out.println("=================================================================================================================");
		//addMessage(model, "修改考试信息完成。");
		return "修改考试信息完成。";
	}
}
