package com.thinkgem.jeesite.freetek.util.jpush;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

public class JpushUtil {
	
	private static Logger logger = Logger.getLogger(JpushUtil.class);

	//正式环境数据
	public final static String DEFAULT_SECRET = "8dc29d063f77a404c8bf0016";
	public final static String DEFAULT_APPKEY = "65a24951cb7c338f0c6a705b";

	//测试环境数据
//	public final static String DEFAULT_SECRET = "5520a47f31535b2ffb86c6c8";
//	public final static String DEFAULT_APPKEY = "bc0579a7b88b8abe2088320e";
	
	public static boolean pushAll(JpushEntity je){
		return sendNotification_All_WithAlias_And_Extras(DEFAULT_SECRET, DEFAULT_APPKEY, je);
	}
	
	public static boolean pushAll(String secret, String appKey, JpushEntity je){
		return sendNotification_All_WithAlias_And_Extras(secret, appKey, je);
	}
	
	public static boolean pushByTag(JpushEntity je){
		return sendNotification_All_WithAlias_And_Extras2(DEFAULT_SECRET, DEFAULT_APPKEY, je);
	}
	
	public static boolean pushByTag(String secret, String appKey, JpushEntity je){
		return sendNotification_All_WithAlias_And_Extras2(secret, appKey, je);
	}
	
	/**
	 * 发送通知
	 * @param title
	 * @param alert
	 * @param extra
	 * @param alias
	 * @return
	 */
	public static boolean sendNotification_All_WithAlias_And_Extras(String appSecret, String appKey, JpushEntity je) {
		
		boolean flag = true;
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(5);
		config.setConnectionTimeout(10 * 1000); // 10 seconds
		config.setTimeToLive(60 * 60 * 24 * 10); // 10 day
		PushPayload ppla = PayloadBuild.buildPushObject_android_and_ios_withAlias_and_extras2(je);
		JPushClient jpushCilent = new JPushClient(appSecret, appKey, null, config);
		try {
			
			PushResult result = jpushCilent.sendPush(ppla);
			logger.info("result" + result);
			
		} catch (APIConnectionException e) {
			
			logger.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
			flag = false;
			
		}
		return flag;
	}
	
	/**
	 * 发送通知
	 * @param title
	 * @param alert
	 * @param extra
	 * @param alias
	 * @return
	 */
	public static boolean sendNotification_All_WithAlias_And_Extras2(String appSecret, String appKey, JpushEntity je) {
		
		boolean flag = true;
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(5);
		config.setConnectionTimeout(10 * 1000); // 10 seconds
		config.setTimeToLive(60 * 60 * 24 * 10); // 10 day
		PushPayload ppla = PayloadBuild.buildPushObject_android_and_ios_withAlias_and_extras(je);
		JPushClient jpushCilent = new JPushClient(appSecret, appKey, null, config);
		try {
			
			PushResult result = jpushCilent.sendPush(ppla);
			logger.info("result" + result);
			
		} catch (APIConnectionException e) {
			
			logger.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
			flag = false;
			
		}
		return flag;
	}
	
	public static void main(String[] args) {
		String title = "考勤异常";
		String msgContent = "【宝宝姓名】【时间】【入院/出院】";
		String contentType= "123";
		Map<String,String> extra= new HashMap<String,String>();
		extra.put("contentType", contentType);
		extra.put("babyId", "1");
		extra.put("babyName", "权龙龙");
		extra.put("babyRelationName", "爸爸");
	
		
		if (!pushAll("c4859e34584b1794ff477fd9","7b08576f2fcdba540d40836f",new JpushEntity(title, msgContent, extra, null))) {
			logger.error("信息推送失败："+title+"----"+msgContent);
		}else{
			System.out.println("成功");
		}
	}

}
