package com.thinkgem.jeesite.freetek.api.constant;

import java.util.HashMap;
import java.util.Map;

public class RespConstants {

	public static final int GLOBAL_SUCCESS = 00000;
	public static final int GLOBAL_PARAM_ERROR= 00001;//参数错误
	public static final int GLOBAL_INNER_ERROR= 00002;//服务端内部错误
	public static final int GLOBAL_OTHER= 99999;//其他错误
	
	/**
	 * 用户状态
	 */
	public static final int USER_NONEXIST = 00101; //用户不存在
	public static final int USER_RREEZED = 00102; //账户被冻结
	public static final int USER_MUSTAPP = 00103; //必须为注册用户
	public static final int USER_EXIST = 00104;   //用户已存在
	// 用户正在被邀请
	public static final int USER_WAS_INVITING = 00105;
	// 优惠券已被抢完
	public static final int COUPON_NOT_AVAILABLE = 00106;
	/**
	 * 短信验证码
	 */
	public static final int SMS_VCODE_MOBILE_TYPE_ERROR = 10001;//手机号格式不正确
	public static final int SMS_VCODE_INCORRECT = 10002;//无效验证码
	public static final int SMS_VCODE_IN_SEND_GAP = 10003;//验证码发送间隔内，请稍后再试
	public static final int SMS_VCODE_SEND_FAIL = 10004;//验证码发送失败
	
	
	public static final int EXAM_SELF_CART_EXISTCOURSE = 20001;//自有考试：报名失败，你已经报考了同时间段类的其他考试！
	public static final int EXAM_SELF_CART_NOAvailableSeats = 20002;//自有考试：报名失败，无剩余可用座位！
	public static final int EXAM_SELF_CART_NOT_SELF_EXAM = 20003;//自有考试：报名失败，你选择的考试不是自有考试！
	public static final int EXAM_IDCARD_ERROR = 20004;//身份号不正确！
	public static final int EXAM_MOBILE_ERROR = 20005;//手机号不正确！
	public static final int EXAM_EMAIL_ERROR = 20006;//邮箱格式不正确！

	public static final int NO_LEARNING_PLAN = 30001;//没有学习规划

	public static final int ACCA_REGISTER_IMG = 40001;//代注册图片不能为空
	public static final int ACCA_REGISTER_STATUS = 40002;//代注册此状态不允许修改

	public static Map<Integer, String> respMap = new HashMap<Integer, String>();
	
	static{
		respMap.put(GLOBAL_SUCCESS, "成功");
		respMap.put(GLOBAL_PARAM_ERROR, "参数错误或token不正确");
		respMap.put(GLOBAL_INNER_ERROR, "服务端内部错误");
		respMap.put(GLOBAL_OTHER, "其他错误");
		
		respMap.put(USER_NONEXIST, "用户不存在");
		respMap.put(USER_RREEZED, "账号被冻结");
		respMap.put(USER_MUSTAPP, "本活动限ACCA小助手注册用户参加，请先下载ACCA小助手并注册用户后再参加本活动！");
		respMap.put(USER_EXIST, "用户已存在");
		respMap.put(USER_WAS_INVITING, "用户已被邀请");
		respMap.put(COUPON_NOT_AVAILABLE, "下手太慢优惠券已被抢完");
		
		respMap.put(SMS_VCODE_MOBILE_TYPE_ERROR, "手机号格式不正确");
		respMap.put(SMS_VCODE_INCORRECT, "无效验证码");
		respMap.put(SMS_VCODE_IN_SEND_GAP, "验证码发送间隔内，请稍后再试");
		respMap.put(SMS_VCODE_SEND_FAIL, "验证码发送失败");
		
		respMap.put(EXAM_SELF_CART_EXISTCOURSE, "已报过该时段的其他考试");
		respMap.put(EXAM_SELF_CART_NOAvailableSeats, "无剩余可用座位！");
		respMap.put(EXAM_SELF_CART_NOT_SELF_EXAM, "报名失败，你选择的考试不是自有考试！");
		respMap.put(EXAM_IDCARD_ERROR, "身份证号不正确！");
		respMap.put(EXAM_MOBILE_ERROR, "手机号不正确！");
		respMap.put(EXAM_EMAIL_ERROR,"邮箱格式不正确!");

		respMap.put(NO_LEARNING_PLAN, "没有学习规划!");
		respMap.put(ACCA_REGISTER_IMG,"请上传必需的图片!");
		respMap.put(ACCA_REGISTER_STATUS,"代注册此状态不运行修改!");
	}

	/**
	 * 课程名称赋颜色值
	 */
	public static Map<String,String> courseColorMap = new HashMap<String, String>();

	static {
		courseColorMap.put("F1","ffaa7f");
		courseColorMap.put("F2","ff7f7f");
		courseColorMap.put("F3","f8668a");
		courseColorMap.put("F4","ed80d9");
		courseColorMap.put("F5","9780ed");
		courseColorMap.put("F6","8ca5f8");
		courseColorMap.put("F7","9ec4f6");
		courseColorMap.put("F8","5bc3cc");
		courseColorMap.put("F9","71d6b1");
		courseColorMap.put("P1","87ec8c");
		courseColorMap.put("P2","f8cf7f");
		courseColorMap.put("P3","f8aa7f");
		courseColorMap.put("P4","f58585");
		courseColorMap.put("P5","fb6b8f");
		courseColorMap.put("P6","fb81d4");
		courseColorMap.put("P7","b282fb");
	}

	/**
	 * 课程id赋颜色值
	 */
	public static Map<Integer,String> courseIdColorMap = new HashMap<Integer, String>();

	static {
		courseIdColorMap.put(1,"ffaa7f");
		courseIdColorMap.put(2,"ff7f7f");
		courseIdColorMap.put(3,"f8668a");
		courseIdColorMap.put(4,"ed80d9");
		courseIdColorMap.put(5,"9780ed");
		courseIdColorMap.put(6,"8ca5f8");
		courseIdColorMap.put(7,"9ec4f6");
		courseIdColorMap.put(8,"5bc3cc");
		courseIdColorMap.put(9,"71d6b1");
		courseIdColorMap.put(10,"87ec8c");
		courseIdColorMap.put(11,"f8cf7f");
		courseIdColorMap.put(12,"f8aa7f");
		courseIdColorMap.put(13,"f58585");
		courseIdColorMap.put(14,"fb6b8f");
		courseIdColorMap.put(15,"fb81d4");
		courseIdColorMap.put(16,"b282fb");
	}
	
}
