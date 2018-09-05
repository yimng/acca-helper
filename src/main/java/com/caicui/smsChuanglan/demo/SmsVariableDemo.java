package com.caicui.smsChuanglan.demo;

import java.io.UnsupportedEncodingException;

import com.caicui.smsChuanglan.request.SmsVariableRequest;
import com.caicui.smsChuanglan.response.SmsVariableResponse;
import com.caicui.smsChuanglan.util.ChuangLanSmsUtil;
import com.caicui.smsChuanglan.util.JsonUtil;


/**
 * 
 * @author tianyh 
 * @Description:变量短信发送
 */
public class SmsVariableDemo {

	public static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)
	public static String account = "";
	// 用户平台API密码(非登录密码)
	public static String pswd = "";

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsVariableRequestUrl = "http://xxx/msg/variable/json";
		//短信内容
		String msg = "【253云通讯】尊敬的{$var},您好,您的验证码是{$var},{$var}分钟内有效";
		//参数组																
		String params = "187********,女士,123456,3;130********,先生,123456,3;";
		//状态报告
		String report= "true";
		
		SmsVariableRequest smsVariableRequest=new SmsVariableRequest(account, pswd, msg, params, report);
		
//        String requestJson = JSON.toJSONString(smsVariableRequest);
		String requestJson = JsonUtil.toJson(smsVariableRequest);

		System.out.println("before request string is: " + requestJson);
		
		String response = ChuangLanSmsUtil.sendSmsByPost(smsVariableRequestUrl, requestJson);
		
		System.out.println("response after request result is : " + response);
		
		SmsVariableResponse smsVariableResponse = // JSON.parseObject(response, SmsVariableResponse.class);
		JsonUtil.toObject(response, SmsVariableResponse.class);
		System.out.println("response  toString is : " + smsVariableResponse);
		
	}

}
