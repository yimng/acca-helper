/**
  * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.web.user.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.acca.constant.Constants;
import com.thinkgem.jeesite.acca.constant.WebConstant;
import com.thinkgem.jeesite.acca.web.content.service.WebMsgSysService;
import com.thinkgem.jeesite.acca.web.exam.entity.WebExamCourse;
import com.thinkgem.jeesite.acca.web.exam.service.WebExamCourseService;
import com.thinkgem.jeesite.acca.web.register.entity.WebAccaRegister;
import com.thinkgem.jeesite.acca.web.register.service.WebAccaRegisterService;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebCityUserRelation;
import com.thinkgem.jeesite.acca.web.sysconf.entity.WebExamOpenCity;
import com.thinkgem.jeesite.acca.web.sysconf.service.WebExamOpenCityService;
import com.thinkgem.jeesite.acca.web.user.entity.WebAccaUser;
import com.thinkgem.jeesite.acca.web.user.service.WebAccaUserService;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.HttpUrlConnectionUtil;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * accaUser模块Controller
 * @author Ivan
 * @version 2016-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/user/webAccaUser")
public class WebAccaUserController extends BaseController {

	@Autowired
	private WebAccaUserService webAccaUserService;
	
	@Autowired
	private WebAccaRegisterService webAccaRegisterService;
	
	@Autowired
	private WebExamCourseService webExamCourseService;
	
	@Autowired
	private WebExamOpenCityService cityService;
	
	@Autowired
	private WebMsgSysService msgSysService;
	
	@ModelAttribute
	public WebAccaUser get(@RequestParam(required=false) String id) {
		WebAccaUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = webAccaUserService.get(id);
		}
		if (entity == null){
			entity = new WebAccaUser();
		}
		return entity;
	}
	
	@RequiresPermissions("user:webAccaUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WebAccaUser webAccaUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		webAccaUser.setDate(new Date());
		logger.info("webAccaUser:"+webAccaUser);
		logger.info("type:"+webAccaUser.getType());
		logger.info("itype:"+webAccaUser.getIdentityType());
		WebExamCourse webExamCourse=new WebExamCourse();
		List<WebExamCourse> courseList = webExamCourseService.findList(webExamCourse);
		//logger.info("webAccaUser:"+webAccaUser);
		Page<WebAccaUser> page = webAccaUserService.findPage(new Page<WebAccaUser>(request, response), webAccaUser); 
		List<WebAccaUser> list = page.getList();
		logger.info("List:"+list);
		if(list != null && !list.isEmpty()){
			List<WebAccaUser> detailList = webAccaUserService.getDetailList(list);
			page.setList(detailList);
		}
		for (WebAccaUser a : page.getList()) {
			String course = "";
			if (a.getCourseList() != null && !a.getCourseList().isEmpty()) {
				
				for (WebExamCourse b : a.getCourseList()) {
					logger.info("mylist:"+b.getCourse());
					course = course+b.getCourse()+"/";
				}
				course = course.substring(0,course.length()-1);
			}
			
			a.setCourse(course);
		}
		
		
		logger.info("mylist:"+page.getList());
		
		model.addAttribute("courseList", courseList);
		model.addAttribute("page", page);
		
		return "/web/user/webAccaUserList";
	}
	
	@RequiresPermissions("user:webAccaUser:view")
	@RequestMapping(value ="author")
	public String author(WebAccaUser webAccaUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		webAccaUser.setDate(new Date());
		logger.info("webAccaUser:"+webAccaUser);
		logger.info("type:"+webAccaUser.getType());
		logger.info("itype:"+webAccaUser.getIdentityType());
		WebExamCourse webExamCourse=new WebExamCourse();
		String sysUserId = UserUtils.getUser().getId();
		//通过用户id,获取绑定的附近城市列表
//		WebExamOpenCity openCity = new WebExamOpenCity();
//		System.out.println("userid:"+sysUserId);
//		openCity.setSysUserId(sysUserId);
//		List<WebExamOpenCity> cityList = cityService.findList(openCity);
//		System.out.println(cityList);
//		if (cityList != null && cityList.size() > 0){
//			String citiesId = "";
//			for (WebExamOpenCity city:cityList){
//				citiesId += city.getCityId() + ",";
//			}
//			citiesId = citiesId.substring(0,citiesId.length() - 1);
//			webAccaUser.getSqlMap().put("citys",citiesId);
//			logger.info("citys:"+citiesId);
//		}
		List<WebCityUserRelation> cityList = cityService.findCityIdListByUserId(sysUserId);
		if (cityList != null && cityList.size() > 0){
			String citiesId = "";
			for (WebCityUserRelation city:cityList){
				citiesId += city.getCityId() + ",";
			}
			citiesId = citiesId.substring(0,citiesId.length() - 1);
			webAccaUser.getSqlMap().put("citys",citiesId);
			logger.info("citys:"+citiesId);
		}
		
		
		if(webAccaUser.getIszbg()==0){
			webAccaUser.setIszbg(1);
		}				
		logger.info("=====================================================================================");
//		List<WebExamCourse> courseList = webExamCourseService.findList(webExamCourse);
//		logger.info("webAccaUser:"+webAccaUser);
//		logger.info("webExamCourse"+webExamCourse);
		Page<WebAccaUser> page = webAccaUserService.findPage(new Page<WebAccaUser>(request, response), webAccaUser); 
		List<WebAccaUser> list = page.getList();
		logger.info("List:"+list);
		if(list != null && !list.isEmpty()){
			List<WebAccaUser> detailList = webAccaUserService.getDetailList(list);
			page.setList(detailList);
		}
		String token=getToken();
		for (WebAccaUser a : page.getList()) {
			String course = getStudentClass(token,a.getPhone());
			a.setCourse(course);
		}
		
		logger.info("mylist:"+page.getList());
//		model.addAttribute("courseList", courseList);
		model.addAttribute("page", page);
		
		return "/web/user/webAccaUserauther";
	}
	
	
	
	public static String getStudentClass(String token,String phone){
		String url="http://api.caicui.com/api/business/order/memberOrderList";
		String requestMethod="POST";
		String userid=getCaicuiUser(token,phone);
		if(userid==null|| "".equals(userid)){
			return "未能根据用户手机号找到对应财萃网用户，可能该用户还未注册财萃网，也可能该用户财萃网所留手机号与当前号码不一致。";
		}
		Map<String, String> params= new HashMap<String, String>();
		params.put("memberId",userid);
		params.put("token",token);
		try{
			String json = HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
			//System.out.println("财萃订单"+json);
			//System.out.println("============================================================");
			JSONObject t =(JSONObject) JSONObject.parse(json);
			String s="没有对应财萃网订单，该生可能为面授标准课学员或还未在财萃网开课。";
			if("success".equals((String) t.get("state"))){
				JSONArray co = (JSONArray) t.get("data");
				Iterator it = co.iterator();
				StringBuffer sb=new StringBuffer();        
				while (it.hasNext()) {
					JSONObject ob = (JSONObject) it.next();
		            sb.append(ob.get("createDate")).append("/").append(ob.get("price")).append(":").append("<br>");
		            JSONArray co1 = (JSONArray) ob.get("orderItem");
		            Iterator it1 = co1.iterator();
		            while (it1.hasNext()) {
		            	JSONObject ob1 = (JSONObject) it1.next();
		                sb.append("[").append(ob1.get("title")).append("]").append("<br>");
		            }
		            sb.append("<br>");	                
		        }
		        s=sb.toString();		        
			}
			return s;
		}catch(Exception e){
			e.printStackTrace();
			return "获取财萃订单错误";
		}
		
	}
	
	public static String getCaicuiUser(String token,String code){		
		String url="http://api.caicui.com/api/zbids/member/detail/v1.0";
		String requestMethod="GET";
		Map<String, String> params= new HashMap<String, String>();
		params.put("code",code);//type=2 传手机
		params.put("type","2");
		params.put("token",token);
		String s="";
		try{
			String json=HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
			//System.out.println(json);
			JSONObject t =(JSONObject) JSONObject.parse(json);		
			if("success".equals((String) t.get("state"))){
				JSONObject v = (JSONObject) t.get("data");
				//System.out.println("caicuiuser"+v);
				s=(String) v.get("id");
			}
		}catch(Exception e){			
			e.printStackTrace();			
		}
		return s;
	}
	
	public static String getToken(){
		String url="http://api.caicui.com/api/zbids/app/gettoken/v1.0/";
		String requestMethod="POST";
		Map<String, String> params= new HashMap<String, String>();
		params.put("appType","accahelperserver");
		params.put("appId","accahelper");
		params.put("appKey","33AF7273FB74052AB2B03CCFC7E97D93"); 
		JSONObject t =(JSONObject) JSONObject.parse(HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params));
		JSONObject v = (JSONObject) t.get("data");
		String s=(String) v.get("token");
		return s;
	}

	@RequiresPermissions("user:webAccaUser:view")
	@RequestMapping(value = "form")
	public String form(WebAccaUser webAccaUser, Model model) {
		model.addAttribute("webAccaUser", webAccaUser);
		return "/web/user/webAccaUserForm";
	}

	/**
	 * 修改用户的注册信息得跳转页面
	 * @param webAccaUser
	 * @param model
     * @return
     */
	@RequiresPermissions("user:webAccaUser:view")
	@RequestMapping(value = "forward")
	public String forward(WebAccaUser webAccaUser, Model model) {
		model.addAttribute("webAccaUser", webAccaUser);
		return "/web/user/editAccaUserForm";
	}
	
	@RequiresPermissions("user:webAccaUser:view")
	@RequestMapping(value = "detail")
	public String detail(WebAccaUser webAccaUser, Model model) {
		
		WebAccaRegister webAccaRegister =new WebAccaRegister();
		webAccaRegister.setAccaUserId(webAccaUser.getAccaUserId());
		
	    WebAccaRegister accaRegister = webAccaRegisterService.get(webAccaRegister);
	    
	    WebExamCourse webExamCourse=new WebExamCourse();
		webExamCourse.setAccaUserId(webAccaUser.getAccaUserId());
		List<WebExamCourse> courseList= webExamCourseService.findMyList(webExamCourse);
		if (courseList != null && !courseList.isEmpty()) {
			String course = "";
			for (WebExamCourse cou : courseList) {
				course = course+cou.getCourse()+"/";
			}
			course = course.substring(0,course.length()-1);
			webAccaUser.setCourse(course);
		}
		
		model.addAttribute("webAccaRegister", accaRegister);
		model.addAttribute("webAccaUser", webAccaUser);
		return "/web/user/webAccaUserDetail";
	}

	@RequiresPermissions("user:webAccaUser:view")
	@RequestMapping(value = "aurhorDetail")
	public String aurhorDetail(WebAccaUser webAccaUser, Model model) {
		
		WebAccaRegister webAccaRegister =new WebAccaRegister();
		webAccaRegister.setAccaUserId(webAccaUser.getAccaUserId());
		
	    WebAccaRegister accaRegister = webAccaRegisterService.get(webAccaRegister);
	    
	    WebExamCourse webExamCourse=new WebExamCourse();
		webExamCourse.setAccaUserId(webAccaUser.getAccaUserId());
		List<WebExamCourse> courseList= webExamCourseService.findMyList(webExamCourse);
		if (courseList != null && !courseList.isEmpty()) {
			String course = "";
			for (WebExamCourse cou : courseList) {
				course = course+cou.getCourse()+"/";
			}
			course = course.substring(0,course.length()-1);
			webAccaUser.setCourse(course);
		}
		
		model.addAttribute("webAccaRegister", accaRegister);
		model.addAttribute("webAccaUser", webAccaUser);
		return "/web/user/webAccaUserauthorDetail";
	}
	
	@RequiresPermissions("user:webAccaUser:edit")
	@RequestMapping(value = "save")
	public String save(WebAccaUser webAccaUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webAccaUser)){
			return form(webAccaUser, model);
		}
		logger.info("webAccaUser:"+webAccaUser);
		webAccaUserService.updateType(webAccaUser);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/user/webAccaUser/?repage";
	}

	/**
	 * 修改用户的注册信息
	 * @param webAccaUser
	 * @param model
	 * @param redirectAttributes
     * @return
     */
	@RequiresPermissions("user:webAccaUser:edit")
	@RequestMapping(value = "updateRegister")
	public String updateRegister(WebAccaUser webAccaUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, webAccaUser)){
			return form(webAccaUser, model);
		}
		String registerCardNumber = webAccaUser.getRegisterCardNumber();
		//获取以前的用户信息
		WebAccaUser oldUser = webAccaUserService.get(webAccaUser);
		//对名称,学员号和证件号进行数据校验
		if (StringUtils.isNotEmpty(oldUser.getAccaRegisterName()) && StringUtils.isNotEmpty(oldUser.getRegisterName()) && StringUtils.isNotEmpty(oldUser.getRegisterCardNumber())){
			//如果以前的不为空,则对现在的数据进行校验
			if (StringUtils.isEmpty(webAccaUser.getRegisterName()) || StringUtils.isEmpty(webAccaUser.getAccaRegisterName()) || StringUtils.isEmpty(registerCardNumber)){
				//如果以前的数据为空,说明以前是没有的,不让保存
				addMessage(redirectAttributes, "名称,学员号,证件号不能修改为空!");
			} else {
				//校验身份证号是否合法
				boolean flag = IdcardUtils.validateCard(registerCardNumber);
				if (!flag && webAccaUser.getCardType() == WebConstant.CARD_TYPE_IDCARD){
					//如果身份证不合法,则不让保存
					addMessage(redirectAttributes, "身份证号输入不合法!");
				} else {
					webAccaUserService.updateRegister(webAccaUser);
					addMessage(redirectAttributes, "保存成功");
				}
			}
		} else {
			//如果以前的数据为空,说明以前是没有的,不让保存
			addMessage(redirectAttributes, "此功能只用于修改手机号和注册证件号,不可添加!");
		}
		return "redirect:"+Global.getAdminPath()+"/user/webAccaUser/?repage";
	}
	
	@RequiresPermissions("user:webAccaUser:edit")
	@RequestMapping(value = "delete")
	public String delete(WebAccaUser webAccaUser, RedirectAttributes redirectAttributes) {
		webAccaUserService.delete(webAccaUser);
		addMessage(redirectAttributes, "删除accaUser模块成功");
		return "redirect:"+Global.getAdminPath()+"/user/webAccaUser/?repage";
	}
	
	@RequiresPermissions("user:webAccaUser:edit")
	@RequestMapping(value = "authorUser" ,method=RequestMethod.GET)	
    @ResponseBody
	public String authorUser(@RequestParam("id") Long id,@RequestParam("iszbg") int iszbg) {
			WebAccaUser wau=new WebAccaUser();
			wau.setAccaUserId(id);
			wau.setIszbg(iszbg);
			if(iszbg==3){
				msgSysService.savePushToPersonal("学员认证成功","您好，您的中博&财萃学员认证已经成功，报名机考时报考的订单会以学员价格核算。", id);
			}else{
				msgSysService.savePushToPersonal("学员认证失败","您好，您的中博&财萃学员认证未成功，请进入认证页面检查您所填报信息是否与报班时学员信息一致。", id);
			}
			webAccaUserService.updateIszbg(wau);
		return "success";
	}
	
	@RequiresPermissions("user:webAccaUser:edit")
	@RequestMapping(value = "freeze")
	public String freeze(WebAccaUser webAccaUser, RedirectAttributes redirectAttributes) {
		logger.info("status:"+webAccaUser.getUserStatus());
		if (webAccaUser.getUserStatus() == Constants.AccaUserStatus.normal) {
			webAccaUser.setUserStatus(Constants.AccaUserStatus.frozen);
			webAccaUserService.freeze(webAccaUser);
			addMessage(redirectAttributes, "冻结用户成功");	
		}else{
			webAccaUser.setUserStatus(Constants.AccaUserStatus.normal);
			webAccaUserService.freeze(webAccaUser);
			addMessage(redirectAttributes, "恢复用户成功");
		}
		return "redirect:"+Global.getAdminPath()+"/user/webAccaUser/?repage";
	}

	
	
	
	public static void main(String[] args) throws Exception {  
    	//此方法仅适用于JdK1.6及以上版本本  
		//System.out.println(getStudentClass(getToken(),"18187052980"));
		//String memberOrderLis="{\"data\":[{\"price\":\"43180.00\",\"status\":\"2\",\"orderType\":\"1\",\"orderItem\":[{\"title\":\"F1 Accountant in Business 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fbfd04a00b6\"},{\"title\":\"F2 Management Accounting 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fd3b0fc00b7\"},{\"title\":\"F3 Financial Accounting 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fd5468d00b8\"},{\"title\":\"F4 Corporate and Business Law 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fd6660400b9\"},{\"title\":\"F5 Performance Management 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fd7305100ba\"},{\"title\":\"2016 F6 Taxation 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fd7f56e00bb\"},{\"title\":\"F7 Financial Reporting 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fd8bfe000bc\"},{\"title\":\"F8 Audit and Assurance 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fd9817400bd\"},{\"title\":\"F9 Financial Management 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fdb512900be\"},{\"title\":\"P1 Governance Risk, and Ethic 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fdd1c4f00bf\"},{\"title\":\"P2 Corporate Reporting 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fde76cf00c0\"},{\"title\":\"P3 Business Analysis 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fdf6fb300c1\"},{\"title\":\"P4 Advanced Financial Management 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fe0d1f200c2\"},{\"title\":\"P5 Advanced Performance Management 基础 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fe25b2000c3\"},{\"title\":\"F1 Accountant in Business 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fed76b100c8\"},{\"title\":\"F2 Management Accounting 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fee504d00cc\"},{\"title\":\"F3 Financial Accounting 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550feef71600ce\"},{\"title\":\"F4 Corporate and Business Law 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550fefa3ab00d1\"},{\"title\":\"F5 Performance Management 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff0562100d3\"},{\"title\":\"2016 F6 Taxation 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff1124a00d5\"},{\"title\":\"F7 Financial Reporting 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff1b23400d8\"},{\"title\":\"F8 Audit and Assurance 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff2641200db\"},{\"title\":\"F9 Financial Management 串讲\",\"courseGroupId\":\"8a22ecb55507806b01550ff3504400dc\"},{\"title\":\"P1 Governance Risk, and Ethic 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff46a4e00e0\"},{\"title\":\"P2 Corporate Reporting 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff51f8600e2\"},{\"title\":\"P3 Business Analysis 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff6339700e7\"},{\"title\":\"P4 Advanced Financial Management 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff739b000ec\"},{\"title\":\"P5 Advanced Performance Management 串讲 ACCA\",\"courseGroupId\":\"8a22ecb55507806b01550ff831bf00ed\"},{\"title\":\"ACCA财务英语基础\",\"courseGroupId\":\"8a22ecb55507806b01552368f7e50139\"}],\"createDate\":\"2016-09-02 16:08:54\"}],\"state\":\"success\",\"msg\":\"\"}";
//		JSONArray jsonArray = new JSONArray(jsonStr);
//		  JSONObject jsonObj = jsonArray.getJSONObject(0);
//		  System.out.println(jsonObj);
//		   
//		  int a = jsonObj.getInt("a");
//		  String b = jsonObj.getString("b");
//		  JSONArray jsonArrayX = jsonObj.getJSONArray("x");
//		   
//		  System.out.println(a);
//		  System.out.println(b);
//		  System.out.println(jsonArrayX);
//		  System.out.println(jsonArrayX.getJSONObject(0).getString("inner"));
//		JSONObject t=(JSONObject) JSONObject.parse(memberOrderLis);
//		//String v = (String) t.get("data");
//		JSONArray co = (JSONArray) t.get("data");
//		Iterator it = co.iterator();
//		StringBuffer sb=new StringBuffer();        
//		while (it.hasNext()) {
//			JSONObject ob = (JSONObject) it.next();
//            sb.append(ob.get("createDate")).append("/").append(ob.get("price")).append(":").append("<br>");
//            JSONArray co1 = (JSONArray) ob.get("orderItem");
//            Iterator it1 = co1.iterator();
//            while (it1.hasNext()) {
//            	JSONObject ob1 = (JSONObject) it1.next();
//                sb.append("[").append(ob1.get("title")).append("]").append("<br>");
//            }
//            sb.append("<br>");	                
//        }
//		
//		List<CaicuiOrder> co= JSON.parseArray(v,CaicuiOrder.class);
//		Iterator<CaicuiOrder> it = co.iterator();
//		StringBuffer sb=new StringBuffer();        
//		while (it.hasNext()) {
//			CaicuiOrder ob = (CaicuiOrder) it.next();
//            sb.append(ob.getCreateDate()).append("/").append(ob.getPrice()).append(":").append("<br>");
//            List<OrderItem> ja1=JSON.parseArray("orderItem",OrderItem.class);
//            Iterator<OrderItem> it1 = ja1.iterator();
//            while (it1.hasNext()) {
//            	OrderItem ob1 = (OrderItem) it1.next();
//                sb.append("[").append(ob1.getTitle()).append("]");
//            }
//            sb.append("<br>");	                
//        }
		//String token=getToken();
		//	String course = getStudentClass(token,"13902832006");
		
		StringBuffer STR = new StringBuffer();		
		STR.append("a  bcdef g h e i"); 
		String s=STR.toString();
		for(int i=STR.length()-1;i>=0 ;i--){
			 System.out.println(i);
			char C=STR.charAt(i);
			if(C==' '){
				STR.replace(i, i+1, "%20");
			}
		}
        
		
		System.out.println(STR);
        System.out.println("-------------");
        System.out.println(s.replaceAll(" ", "%20"));
        
        
	}

}