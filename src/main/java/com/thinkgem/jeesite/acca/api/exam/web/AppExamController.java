/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.acca.api.exam.web;

import com.thinkgem.jeesite.acca.api.exam.entity.AppExam;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamPlace;
import com.thinkgem.jeesite.acca.api.exam.entity.AppExamSelfCityTiny;
import com.thinkgem.jeesite.acca.api.exam.entity.AppOfficialExamSignup;
import com.thinkgem.jeesite.acca.api.exam.service.AppExamService;
import com.thinkgem.jeesite.acca.api.model.request.*;
import com.thinkgem.jeesite.acca.api.model.response.GetSelfExamCartGroupByPlaceResp;
import com.thinkgem.jeesite.acca.api.model.response.GetSelfExamCartResp;
import com.thinkgem.jeesite.acca.api.model.response.GetSelfOfficialExamNameResp;
import com.thinkgem.jeesite.acca.api.model.response.SubmitExamRegisterResp;
import com.thinkgem.jeesite.acca.api.register.entity.AppExamOpenCity;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.freetek.api.constant.RespConstants;
import com.thinkgem.jeesite.freetek.api.model.BaseObjResponse;
import com.thinkgem.jeesite.freetek.api.model.BasePageResponse;
import com.thinkgem.jeesite.freetek.api.model.BaseRequest;
import com.thinkgem.jeesite.freetek.api.model.BaseResponse;
import gui.ava.html.image.generator.HtmlImageGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AppExamController
 * @author Ivan
 * @version 2016-08-09
 */
@Api(value = "${apiPath}/exam", description = "exam考试模块")
@Controller
@RequestMapping(value = "${apiPath}/exam")
public class AppExamController extends BaseController {
	
	@Value("${upload.file.base.dir}")
	private String file_path = "";
	
	@Value("${upload.file.base.url}")
	private String file_url = "";

	
	@Autowired
	private AppExamService appExamService;
	
	@ApiOperation(value = "获取自有考试和官方考试名称", notes = "获取自有考试和官方考试名称")
	@RequestMapping(value = "getSelfOfficialExamName.do" ,method=RequestMethod.POST)
	public @ResponseBody GetSelfOfficialExamNameResp getSelfOfficialExamName(@RequestBody BaseRequest req) {		
		return appExamService.getSelfOfficialExamName(req);
	}
	
	
	@ApiOperation(value = "获取F1-F4考试城市列表", notes = "获取F1-F4考试城市列表")
	@RequestMapping(value = "getSelfExamCity.do" ,method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppExamOpenCity> getSelfExamCity(@RequestBody BaseRequest req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new BasePageResponse<AppExamOpenCity>(respCode);
		}
		
		return appExamService.getSelfExamCity(req);
	}
	
	@ApiOperation(value = "F1-F4按照城市和最近的月份获取F1-F4考试列表", notes = "按照城市和最近的月份获取F1-F4考试列表")
	@RequestMapping(value = "getSelfExamList.do" ,method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppExam> getSelfExamList(@RequestBody GetSelfExamListReq req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new BasePageResponse<AppExam>(respCode);
		}
		
		return appExamService.getSelfExamList(req.getExamCityId(),req.getTimeType(),req.getAppUser());
	}
	
	@ApiOperation(value = "F1-F4按照城市和时间范围获取F1-F4考试列表", notes = "按照城市和时间范围获取F1-F4考试列表")
	@RequestMapping(value = "getSelfExamListByCityAndTimeSpan.do" ,method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppExam> getSelfExamListByCityAndTimeSpan(@RequestBody GetSelfExamListByCityAndTimeSpanReq req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new BasePageResponse<AppExam>(respCode);
		}
		
		return appExamService.getSelfExamListByCityAndTimeSpan(req.getExamCityId(),req.getStartTime(),req.getEndTime(),req.getAppUser());
	}
	
	@ApiOperation(value = "F1-F4按照考点id展示考试详情", notes = "F1-F4按照考点id展示考试详情")
	@RequestMapping(value = "getSelfExamByExamPlaceId.do" ,method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<AppExamPlace> getSelfExamByExamPlaceId(@RequestBody GetSelfExamByExamPlaceIdReq req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new BaseObjResponse<AppExamPlace>(respCode);
		}
		
		return appExamService.getSelfExamByExamPlaceId(req.getExamPlaceId(),req.getAppUser());
	}	
	
	@ApiOperation(value = "F1-F4考点列表(城市)", notes = "F1-F4考点列表(城市)")
	@RequestMapping(value = "getSelfExamPlaceList.do" ,method=RequestMethod.POST)
	public @ResponseBody BasePageResponse<AppExamSelfCityTiny> getSelfExamPlaceList(@RequestBody BaseRequest req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new BasePageResponse<AppExamSelfCityTiny>(respCode);
		}
		
		return appExamService.getSelfExamPlaceList(req.getAppUser());
	}
	
	
	@ApiOperation(value = "F1-F4考试：已经添加购物车中的考试列表", notes = "已经添加购物车中的考试列表")
	@RequestMapping(value = "getSelfExamCart.do" ,method=RequestMethod.POST)
	public @ResponseBody GetSelfExamCartResp getSelfExamCart(@RequestBody BaseRequest req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new GetSelfExamCartResp(respCode);
		}
		
		return appExamService.getSelfExamCart(req.getAppUser());
	}

	@ApiOperation(value = "F1-F4考试：已经添加购物车中的按考点分组的考试列表", notes = "已经添加购物车中的按考点分组的考试列表")
	@RequestMapping(value = "getSelfExamCartGroupByPlace.do" ,method=RequestMethod.POST)
	public @ResponseBody GetSelfExamCartGroupByPlaceResp getSelfExamCartGroupByPlaceId(@RequestBody BaseRequest req) {

		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new GetSelfExamCartGroupByPlaceResp(respCode);
		}

		return appExamService.getSelfExamCartByPlace(req.getAppUser());
	}
	
	@ApiOperation(value = "F1-F4考试：报名/添加考试到购物车中", notes = "F1-F4考试：报名/添加考试到购物车中")
	@RequestMapping(value = "addSelfExamCart.do" ,method=RequestMethod.POST)
	public @ResponseBody GetSelfExamCartResp addSelfExamCart(@RequestBody AddSelfExamCartReq req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new GetSelfExamCartResp(respCode);
		}
		
		List<AppOfficialExamSignup> list = appExamService.findSignups(req.getExamId(), req.getAppUserId());
		if(list != null && list.size() != 0){
			return new GetSelfExamCartResp(RespConstants.EXAM_SELF_CART_EXISTCOURSE);
		}
		
		return appExamService.addSelfExamCart(req);
	}
	
	@ApiOperation(value = "F1-F4考试：从物车中删除某一个考试", notes = "F1-F4考试：从物车中删除某一个考试")
	@RequestMapping(value = "delSelfExamCart.do" ,method=RequestMethod.POST)
	public @ResponseBody GetSelfExamCartResp delSelfExamCart(@RequestBody DelSelfExamCartReq req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new GetSelfExamCartResp(respCode);
		}
		
		return appExamService.delSelfExamCart(req.getSelfExamCartId(),req.getAppUser());
	}
	
	
	@ApiOperation(value = "F1-F4考试：确认报名生成支付订单", notes = "F1-F4考试：确认报名生成支付订单")
	@RequestMapping(value = "submitExamRegister.do" ,method=RequestMethod.POST)
	public @ResponseBody SubmitExamRegisterResp submitExamRegister(@RequestBody SubmitExamRegisterReq req) {
		
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new SubmitExamRegisterResp(respCode);
		}
		
		return appExamService.submitExamRegister(req);
	}
	
	public final Map<Long,String> global = new HashMap<Long,String>();
	
	@ApiOperation(value = "获取准考证h5页面", notes = "获取准考证h5页面")
	//@RequestMapping(value = "ordertest.do" ,method=RequestMethod.POST)
	@RequestMapping(value ="examticket",method=RequestMethod.GET)
	public String examticket(Model model,Long orderId,Long accaUserId) {
		/*long a1 = 1;
		AppExamCourseSelf a = new AppExamCourseSelf();
		a.setCourse("Id 99919191");
		a.setCourseName("2016年01月02日 13:00-15:00");
		a.setExamCourseId(a1);
		model.addAttribute("user",a);
		*/
		return appExamService.examticket(model,orderId,accaUserId);
		
		
	}
	
	@RequestMapping(value ="examTicketImageUpload.do",method=RequestMethod.POST)
	public @ResponseBody BaseResponse examTicketImageUpload(@RequestBody ExamTicketImageUploadModel req) {
		
		logger.info("req:"+req);
		global.put(req.getAccaUserId(), req.getHtmlSrc());
		logger.info("map:"+global);
		
		logger.info("global:"+global.get(req.getAccaUserId()));
		
		return new BaseResponse(RespConstants.GLOBAL_SUCCESS);
	}
	@ApiOperation(value = "获取准考证图片url接口", notes = "获取准考证图片url接口")
	@RequestMapping(value ="getExamTicketImage.do",method=RequestMethod.POST)
	public @ResponseBody BaseObjResponse<String> genimage(@RequestBody BaseRequest req) {
		logger.info("req:"+req);
		int respCode = req.isCorrectParams();
		if(respCode!=RespConstants.GLOBAL_SUCCESS){
			return new BaseObjResponse<String>(respCode);
		}
		
		String htmlSrc = global.get(req.getAppUserId());
		
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		//imageGenerator.setSize(arg0);
		imageGenerator.loadHtml(htmlSrc) ;
		imageGenerator.getBufferedImage();
		String cardImageName="card_"+req.getAppUserId()+".png";
		String cardImagePath = file_path+"card"+File.separator;
		File file = new File(cardImagePath);
		if (!file.exists()) {
			file.mkdir();
		}
		String imageUrl = file_url+"card/"+cardImageName;
		imageGenerator.saveAsImage(cardImagePath+cardImageName);
		//imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
		
		logger.info("global:"+global.get(req.getAppUserId()));
		logger.info("imageUrl:"+imageUrl);
		//Map<String,String> map = new HashMap<String,String>();
		//Object obj = imageUrl;
		return new BaseObjResponse<String>(imageUrl);
	}
	

	public static void main(String args[]){
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadUrl("http://www.caicui.com/");
		Dimension ds = new Dimension(800,1200);
		//System.out.println(imageGenerator.getSize());
		//imageGenerator.loadHtml("<div class='div2' style='padding:50 100;margin:50 50;'><br><h3  class='title' align='center'><font size='16' color='#000'>ACCA机考准考证</font></h3><table border='1' cellpadding='5' cellspacing='0' style='margin-bottom:10px;' class='mytable mytable2'><tbody><tr><td>ACCA student ID:<img src='http://app.china-ife.com/upload/image/1476261530089920068.png' width=150 height=180 ></td><td>88888888/password</td></tr><tr><td>Date of Birth:<br />DD/MM/YY:</td><td>23/03/78</td></tr><tr><td>Gender:</td><td>Male</td></tr><tr><td>Chinese Name:<br />(E.g. 张红)</td><td>张华</td></tr><tr><td>Given Name:<br />(E.g Hong)</td><td>Hua</td></tr><tr><td>Surname:<br />(E.g Zhang)</td><td>Zhang</td></tr><tr><td>机考科目：<br />F2(ACCA:F2)</td><td>考试时间：2016-12-27 13:00-15:00<br/>考试地点：北京市海淀区北三环西路甲30号双天大厦308室</td></tr><tr><td>ID/Passport No. <br />身份证/护照:</td><td>110105197803231174 </td></tr><tr><td>Company/School <br />单位名:</td><td>首都经济贸易大学</td></tr><tr><td>Tel/Mobile <br />电话:</td><td>13910880907</td></tr><tr><td>Email <br />邮箱:</td><td>openclick@qq.com</td></tr><tr><td>Signature <br />签名:</td><td></td></tr></tbody></table><p>注：以上表格内信息必须如实填写，否则会影响您的报考是否成功。 </p><p> 注意事项：</p><p> 1：考试费用一旦缴付，如因考生自身原因缺考，作弃权处理，不须考虑退款事宜；</p><p>  2：具体考试时间安排请以邮件通知为准；</p><p> 3：考试须在考试开始前 30分钟到达考点，由监考老师对考生进行核查考生本人身份证、准考证；</p><p> 4：考生在考试开始前 15分钟经过监考老师批准方可进入考场。逾期不得再进入考场； </p><p>5：中博诚通机考中心保留因不可抗力因素（如网络问题、停电等）调整机考时间或取消考试的权利。</p><table  width=150 height=180 cellspacing='5' cellpadding='5' border='2' align='center'><tr><td><img src='http://app.china-ife.com/upload/image/1476261530089920068.png' width=150 height=180 ></td></tr></table></div>");////				+ "<h3 class='title' align='center'><img src='http://app.china-ife.com/upload/image/1475154084136439722.jpg' width='110' height='80'></h3>"
//				+ "<table style='margin-bottom: 10px; width: 426px;' class='mytable mytable2' cellspacing='0' cellpadding='5' border='1'><tbody>"
//				+ "<tr><td rowspan='6' style='white-space: nowrap; font-size: 12px;' align='center'>"
//				+ "<img src='http://app.china-ife.com/upload/image/1475154084136439722.jpg' width='110' height='80'>"
//				+ "</td><td style='white-space: nowrap; font-size: 12px;'>ACCA student ID:<img src='http://app.china-ife.com/upload/image/1475154084136439722.jpg' width='110' height='80'></td><td style='white-space: nowrap; font-size: 12px;'>88888888/password</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Date of Birth:<br>DD/MM/YY:</td><td style='white-space: nowrap; font-size: 12px;'>23/03/78</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Gender:</td><td style='white-space: nowrap; font-size: 12px;'>Male </td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Chinese Name:<br>(E.g. 张红)</td><td style='white-space: nowrap; font-size: 12px;'>张华</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Given Name:<br>(E.g Hong)</td><td style='white-space: nowrap; font-size: 12px;'>Hua</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Surname:<br>(E.g Zhang)</td><td style='white-space: nowrap; font-size: 12px;'>Zhang</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>机考科目：<br>F2(ACCA:F2)</td><td colspan='2' style='white-space: nowrap; font-size: 12px;'>考试时间：2016-12-27 13:00-15:00<br>考试地点：北京市海淀区北三环西路甲30号双天大厦308室</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>ID/Passport No. <br>身份证/护照:</td><td colspan='2' style='white-space: nowrap; font-size: 12px;'>110105197803231174 </td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Company/School <br>单位名:</td><td colspan='2' style='white-space: nowrap; font-size: 12px;'>首都经济贸易大学</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Tel/Mobile <br>电话:</td><td colspan='2' style='white-space: nowrap; font-size: 12px;'>13910880907</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Email <br>邮箱:</td><td colspan='2' style='white-space: nowrap; font-size: 12px;'>openclick@qq.com</td></tr><tr><td style='white-space: nowrap; font-size: 12px;'>Signature <br>签名:</td><td colspan='2' style='white-space: nowrap; font-size: 12px;'></td></tr></tbody></table><div>");
		imageGenerator.saveAsImage("hello-world.png");;
		imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
	}
	
/*   public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException, AWTException {  
    	//此方法仅适用于JdK1.6及以上版本本  
		Desktop.getDesktop().browse(new URL("file:///C:/Users/ly/WebstormProjects/untitled/src/order.html").toURI());  
		Robot robot = new Robot();  
		robot.delay(3000);  
		Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());  
		int width = (int) d.getWidth();  
		int height = (int) d.getHeight();  
		//最大化浏览器  
		robot.keyRelease(KeyEvent.VK_F11);  
		robot.delay(2000);  
		Image image = robot.createScreenCapture(new Rectangle(0, 0, width,height));  
		BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
		Graphics g = bi.createGraphics();  
		g.drawImage(image, 0, 0, width, height, null);  
		//保存图片  
		ImageIO.write(bi, "jpg", new File("C:\\src\\google.jpg"));  
    } */ 
	
	
	
}