package com.caicui.smsChuanglan.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.caicui.smsChuanglan.response.SmsSendResponse;

/**
 * com.bitzh.message.cl
 * Created by yukewi on 2015/9/22 15:41.
 */
public final class ChuangLanResultHelper {
    public static final String MSGID = "msgid";
    public static final String RESPONSE_STATUS = "responseStatus";
    public static final String RESPONSE_STATUS_MESSAGE = "responseStatusMessage";
    public static final String RESPONSE_TIME = "responseTime";
    private static final ChuangLanResultHelper instance = new ChuangLanResultHelper();
    private static Map <String, String> CHUANG_LAN_RESULT_STATUS_MAP;

    {
        CHUANG_LAN_RESULT_STATUS_MAP = statusMap();
    }

    private ChuangLanResultHelper() {
    }

    public Map <String, String> splitResponse(String response) {
        Map <String, String> map = new HashMap <>();
        String responseTime = null;
        String responseStatus = null;

        response = StringUtils.trim(response);

        String[] splitResults = StringUtils.split(response, ",");

        if ((splitResults != null) && (splitResults.length == 2)) {
            responseTime = splitResults[0];
            responseStatus = splitResults[1];
            map.put(RESPONSE_TIME, responseTime);
            map.put(RESPONSE_STATUS, responseStatus);
            map.put(RESPONSE_STATUS_MESSAGE, CHUANG_LAN_RESULT_STATUS_MAP.get(responseStatus));
        }

        return map;
    }

    private synchronized Map <String, String> statusMap() {
        Map <String, String> map = new LinkedHashMap <>();

        map.put("0", "提交成功");
        map.put("101", "无此用户");
        map.put("102", "密码错");
        map.put("103", "提交过快（提交速度超过流速限制）");
        map.put("104", "系统忙（因平台侧原因，暂时无法处理提交的短信）");
        map.put("105", "敏感短信（短信内容包含敏感词）");
        map.put("106", "消息长度错（>536或<=0）");
        map.put("107", "包含错误的手机号码");
        map.put("108", "手机号码个数错（群发>50000或<=0;单发>200或<=0）");
        map.put("109", "无发送额度（该用户可用短信数已使用完）");
        map.put("110", "不在发送时间内");
        map.put("111", "超出该账户当月发送额度限制");
        map.put("112", "无此产品，用户没有订购该产品");
        map.put("113", "extno格式错（非数字或者长度不对）");
        map.put("114", "可用参数组个数错误（小于最小设定值或者大于1000）;变量参数组大于20个");
        map.put("115", "自动审核驳回");
        map.put("116", "签名不合法，未带签名（用户必须带签名的前提下）");
        map.put("117", "IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
        map.put("118", "用户没有相应的发送权限");
        map.put("119", "用户已过期");


        /**
         * 新加入错误编码
         * 2017年11月29日 14:20:06
         */
        map.put("120", "违反防盗用策略(日发送限制)");
        map.put("123", "发送类型错误");
        map.put("124", "白模板匹配错误");
        map.put("125", "匹配驳回模板，提交失败");
        map.put("127", "定时发送时间格式错误");
        map.put("128", "内容编码失败");
        map.put("129", "JSON格式错误");
        map.put("130", "请求参数错误（缺少必填参数）");


        return map;
    }

    /**
     * 格式:
     * <p>
     * {"time":"20171129142333","msgId":"17112914233328275","errorMsg":"","code":"0"}
     *
     * @param json
     * @return
     */
    public Map <String, String> translateJson(String json) {
        SmsSendResponse response = JsonUtil.toObject(json, SmsSendResponse.class);


        /**
         * 响应时间
         */
        String time = response.getTime();
        /**
         * 消息id
         */
        String msgId = response.getMsgId();
        /**
         * 状态码（详细参考提交响应状态码）
         */
        String code = response.getCode();

        /**
         * 状态码说明（成功返回空）
         */
        String errorMsg = response.getErrorMsg();
        if (StringUtils.isEmpty(response.getErrorMsg()) && StringUtils.isNotEmpty(code)) {
            errorMsg = CHUANG_LAN_RESULT_STATUS_MAP.get(code);
        }

        Map <String, String> map = new HashMap <>();
        map.put(MSGID, msgId);
        map.put(RESPONSE_TIME, time);
        map.put(RESPONSE_STATUS, code);
        map.put(RESPONSE_STATUS_MESSAGE, errorMsg);
        return map;
    }

    /**
     * 20110725160412,0
     * 1234567890100
     * <p/>
     * 响应时间为20110725160412，响应状态为0 表明那个成功提交到服务器；1234567890100为返回的msgid，这个工状态报告匹配时使用。
     * <p/>
     * 20110725160412,101
     * 本示例表明用户提交失败，返回错误码为101，服务器端并未返回msgid。
     *
     * @param result
     * @return
     */
    public Map <String, String> translate(String result) {
        Map <String, String> map = new HashMap <>();
        String response = null;
        String msgid = null;

        result = StringUtils.trim(result);
        result = StringUtils.replace(result, "\r\n", "\n");

        String[] splitResults = StringUtils.split(result, "\n");

        if ((splitResults != null) && (splitResults.length == 2)) {

            // SUCCESS REQUEST
            response = splitResults[0];
            msgid = splitResults[1];
            map.put(MSGID, msgid);
        } else if ((splitResults != null) && (splitResults.length == 1)) {

            // ERROR REQUEST
            response = splitResults[0];
        }

        map.putAll(splitResponse(response));

        return map;
    }

    public static ChuangLanResultHelper getInstance() {
        return instance;
    }
}



