package com.caicui.smsChuanglan.demo;

import java.io.UnsupportedEncodingException;

import com.caicui.smsChuanglan.request.SmsPullRequest;
import com.caicui.smsChuanglan.response.SmsPullResponse;
import com.caicui.smsChuanglan.util.ChuangLanSmsUtil;
import com.caicui.smsChuanglan.util.JsonUtil;


/**
 * @author tianyh
 * @Description:查询上行短信
 */
public class SmsPullDemo {

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "";
    // 用户平台API密码(非登录密码)
    public static String pswd = "";

    public static void main(String[] args) throws UnsupportedEncodingException {

        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsPullRequestUrl = "http://xxx/msg/pull/mo";
        //上行拉取条数
        String count = "1";

        SmsPullRequest smsPullRequest = new SmsPullRequest(account, pswd, count);

//		String requestJson = JSON.toJSONString(smsPullRequest);
        String requestJson = JsonUtil.toJson(smsPullRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsPullRequestUrl, requestJson);

        System.out.println("response after request result is : " + response);

        SmsPullResponse smsPullResponse = //JSON.parseObject(response, SmsPullResponse.class);
                JsonUtil.toObject(response, SmsPullResponse.class);
        System.out.println("response  toString is : " + smsPullResponse);
    }

}