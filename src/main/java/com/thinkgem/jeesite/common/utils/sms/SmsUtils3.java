package com.thinkgem.jeesite.common.utils.sms;

import com.bcloud.msg.http.HttpSender;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.HashMap;

public class SmsUtils3 {
	
	private static Logger logger = Logger.getLogger(SmsUtils3.class);
	
	private static String restIp = "sandboxapp.cloopen.com";
	private static String restPort="8883";
	private static String accountSid="aaf98f8948f34b1b0148f3ba5b0c0095";
	private static String accountToken="564d78c80d3742df825dd0921524d8a8";
	private static String appId="8a48b5514979e09101497a34904b0057";
	private static String templateId="8468";

	
//	public static HashMap<String, Object> sendSmsVcode(String phone, String templateId, String[] params) {
	public static HashMap<String, Object> sendSmsVcode(String phone, String[] params) {
	HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(restIp, restPort);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(accountSid, accountToken);// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(appId);// 初始化应用ID
		result = restAPI.sendTemplateSMS(phone, templateId, params);
		
		logger.info("send sms vcode , phone:" +  phone + ", templateId:" + templateId + ", params:" + params);
		logger.info("send sms vcode , result: " +  result);
		
		return result;
		/*
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))) {
			@SuppressWarnings("unchecked")
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");//正常返回输出data包体信息（map）
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			return true;
		}else{
			//异常返回输出错误码和错误信息
			statusCode = (String) result.get("statusCode");
			statusMsg = (String) result.get("statusMsg");
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			logger.warn("send sms vcode , error:" +  "错误码=" + result.get("statusCode") +" 错误信息= "+result.get("	"));
			return false;
		}
		*/
	}

	/**
	 * 短信接口
	 * @param phone
	 * @param msg
     * @return
     */
	public static HashMap<String, Object> sendSms2Vcode(String phone, String msg) {
		String url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
		//String account = "nanjing888";// 账号
		//String pswd = "Tch10251025";// 密码
		String account = "VIP_zbxs";// 账号
		String pswd = "Tch123456";// 密码
		//String mobile = "13856050245";// 手机号码，多个号码使用","分割
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = "19733";// 扩展码
		String returnString="";
		try {
			returnString = HttpSender.batchSend(url, account, pswd, phone, msg, needstatus, "",extno);
			logger.info("returnString:"+returnString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(returnString)) {
			String[] ary = returnString.split(",");//调用API方法按照逗号分隔字符串
			String statusCode =ary[1];
			logger.info("statusCode:"+statusCode);
			if (statusCode.startsWith("0")) {
				statusCode="000000";
				map.put("statusCode", statusCode);
				map.put("statusMsg", "发送成功");
			}else{
				map.put("statusCode", statusCode);
				map.put("statusMsg", "发送失败");
			}
		}
		logger.info("map:"+map);
		return map;
	}
	
	public static HashMap<String, Object> sendSms2Vcode(String phone, String[] params) {
		
		String url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
//		String account = "nanjing888";// 账号
//		String pswd = "Tch10251025";// 密码
		String account = "VIP_zbxs";// 账号
		String pswd = "Tch123456";// 密码
		//String mobile = "13856050245";// 手机号码，多个号码使用","分割
		String msg = "您的验证码为#1#，验证码的有效期为#2#分钟。";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = "19733";// 扩展码

		String returnString="";
		//String vcode = RandomStringUtils.randomNumeric(4);
		logger.info("params:"+params[0]+","+params[1]);
		String result="";
		result=msg.replace("#1#", params[0]);
		result=result.replace("#2#", params[1]);
		logger.info("result:"+result);
		try {
			returnString = HttpSender.batchSend(url, account, pswd, phone, result, needstatus, "",extno);
//			System.out.println(returnString);
			logger.info("returnString:"+returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(returnString)) {
			
			String[] ary = returnString.split(",");//调用API方法按照逗号分隔字符串
			String statusCode =ary[1];
			logger.info("statusCode:"+statusCode);
			if (statusCode.startsWith("0")) {
				statusCode="000000";
				map.put("statusCode", statusCode);
				map.put("statusMsg", "发送成功");
			}else{
				map.put("statusCode", statusCode);
				map.put("statusMsg", "发送失败");
			}
		}
		logger.info("map:"+map);
		return map;
	}
	
	
	public static boolean checkEmailFormat(String email){
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if(StringUtils.isEmpty(email) || !email.trim().matches(regex)){
			return false;
		}
		return true;
	}
	public static boolean checkPhoneFormat(String phone){
		if(StringUtils.isEmpty(phone) || !(phone.matches("\\d{11}"))){
			System.out.println("phone error");
			return false;
		}
		return true;
	}
	//检查真实qq号
	public static boolean checkQqFormat(String qq){
		if(StringUtils.isEmpty(qq) || !(qq.matches("^[1-9][0-9]{4,11}$"))){
			System.out.println("qq error");
			return false;
		}
		return true;
	}	
	


	public String getRestIp() {
		return restIp;
	}


	@SuppressWarnings("static-access")
	public void setRestIp(String restIp) {
		this.restIp = restIp;
	}


	public String getRestPort() {
		return restPort;
	}


	@SuppressWarnings("static-access")
	public void setRestPort(String restPort) {
		this.restPort = restPort;
	}


	public String getAccountSid() {
		return accountSid;
	}


	@SuppressWarnings("static-access")
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}


	public String getAccountToken() {
		return accountToken;
	}


	@SuppressWarnings("static-access")
	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}


	public String getAppId() {
		return appId;
	}


	@SuppressWarnings("static-access")
	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getTemplateId() {
		return templateId;
	}


	@SuppressWarnings("static-access")
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}
