package com.caicui.smsChuanglan.sender;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caicui.smsChuanglan.request.SmsSendRequest;
import com.caicui.smsChuanglan.util.ChuangLanSmsUtil;
import com.caicui.smsChuanglan.util.JsonUtil;

/**
 * Description: SmsSender 短信发送器 <br>
 * <p>
 * vendor:chuanglan
 * <p>
 * <p>
 * All Rights Reserved.
 *
 * @author 于科为(yukewei@caicui.com)
 * @version V1.0  2017年11月29日 14:05:58 星期三
 */
public final class SmsSenderChuanglan {
    private static final SmsSenderChuanglan SMS_SENDER = new SmsSenderChuanglan();
    private static Logger LOGGER = LoggerFactory.getLogger(SmsSenderChuanglan.class);

    private SmsSenderChuanglan() {
    }


    public static void main(String[] args) {
        // 用户平台API账号(非登录账号,示例:N1234567)
        String account = "N0040330";
        // 用户平台API密码(非登录密码)
        String password = "J6Zz2SOiaUe3a9";
        String url = "http://smssh1.253.com/msg/send/json";
        // 短信内容
        String message = "你好,你的验证码是123456";
        //手机号码
        String mobile = "13691433195";
        SmsSenderChuanglan smsSender = getSmsSender();
        final String send = smsSender.send(url, account, password, mobile, message);
        System.out.println(send);
    }

    /**
     * 单用户发送
     *
     * @param url
     * @param account
     * @param password
     * @param mobile
     * @param message
     * @return
     */
    public String send(String url, String account, String password, String mobile, String message) {
        String report = "true"; // 是否需要状态报告，需要true，不需要false
        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, message, mobile, report);
        String requestJson = JsonUtil.toJson(smsSingleRequest);
        String response = ChuangLanSmsUtil.sendSmsByPost(url, requestJson);
        return response;
    }

    /**
     * 多用户发送
     *
     * @param url
     * @param account
     * @param password
     * @param mobile
     * @param message
     * @return
     */
    public String send(String url, String account, String password, String[] mobile, String message) {
        String mobile_ = StringUtils.join(mobile,",");
        String report = "true"; // 是否需要状态报告，需要true，不需要false
        SmsSendRequest smsSingleRequest = new SmsSendRequest(account, password, message, mobile_, report);
        String requestJson = JsonUtil.toJson(smsSingleRequest);
        String response = ChuangLanSmsUtil.sendSmsByPost(url, requestJson);
        return response;
    }


    public static SmsSenderChuanglan getSmsSender() {
        return SMS_SENDER;
    }
}



