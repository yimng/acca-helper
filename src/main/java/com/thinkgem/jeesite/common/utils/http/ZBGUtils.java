package com.thinkgem.jeesite.common.utils.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.HttpUrlConnectionUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ZBGUtils {
    private static String domain = "https://apidev.zbgedu.com";
//    private static String domain = "http://10.10.20.16:6005";
//private static String domain = "http://localhost:6005";

    public static String getToken() {
        String url = domain + "/api/edu/zbids/app/gettoken/";
        String requestMethod = "POST";
        Map<String, String> params = new HashMap<String, String>();
        params.put("appType", "accahelperserver");
        params.put("appId", "accahelper");
        params.put("appKey", "33AF7273FB74052AB2B03CCFC7E97D93");
        String s = "";
        try {
            String text = HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
            JSONObject t = (JSONObject) JSONObject.parse(text);
            JSONObject v = (JSONObject) t.get("data");
            s = (String) v.get("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String getCaicuiUser(String token, String code) {
        String url=domain + "/api/edu/zbids/member/detail/";
        String requestMethod = "GET";
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);//type=2 传手机
        params.put("type", "2");
        params.put("token", token);
        String s = "";
        try {
            String json = HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
            //System.out.println(json);
            JSONObject t = (JSONObject) JSONObject.parse(json);
            if ("success".equals((String) t.get("state"))) {
                JSONObject v = (JSONObject) t.get("data");
                //System.out.println("caicuiuser"+v);
                s = (String) v.get("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean loginzbg(String token, String phone, String password) {
        String url = domain + "/api/edu/zbids/member/login";
        String reqeustMethod = "POST";
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("account", phone);
        params.put("password", password);
        boolean result = false;
        try {
            String json = HttpUrlConnectionUtil.httpRequestToString(url, reqeustMethod, params);
            JSONObject t = ((JSONObject) JSONObject.parse(json));
            if ("success".equals((String) t.get("state"))) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String registerZBG(String token, String phone, String password) {
        String url = domain + "/api/edu/zbids/member/fastreg";
        String requestMethod = "POST";
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("phone", phone);
        if (StringUtils.isNotEmpty(password)) {
            params.put("password", password);
        }
        String memberId = "";
        try {
            String s = HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
            JSONObject o = (JSONObject) JSONObject.parse(s);
            if ("success".equals(o.get("state"))) {
                JSONObject v = (JSONObject) o.get("data");
                memberId = (String) v.get("memberId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberId;
    }

    public static String getStudentClass(String token, String phone) {
        String url = domain + "/api/business/order/memberOrderList";
        String requestMethod = "POST";
        String userid = getCaicuiUser(token, phone);
        if (userid == null || "".equals(userid)) {
            return "未能根据用户手机号找到对应财萃网用户，可能该用户还未注册财萃网，也可能该用户财萃网所留手机号与当前号码不一致。";
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("memberId", userid);
        params.put("token", token);
        try {
            String json = HttpUrlConnectionUtil.httpRequestToString(url, requestMethod, params);
            //System.out.println("财萃订单"+json);
            //System.out.println("============================================================");
            JSONObject t = (JSONObject) JSONObject.parse(json);
            String s = "没有对应财萃网订单，该生可能为面授标准课学员或还未在财萃网开课。";
            if ("success".equals((String) t.get("state"))) {
                JSONArray co = (JSONArray) t.get("data");
                Iterator it = co.iterator();
                StringBuffer sb = new StringBuffer();
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
                s = sb.toString();
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取财萃订单错误";
        }

    }
}
