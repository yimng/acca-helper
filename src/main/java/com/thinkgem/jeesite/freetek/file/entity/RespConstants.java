package com.thinkgem.jeesite.freetek.file.entity;

import java.util.HashMap;
import java.util.Map;

public class RespConstants {

	public static final int GLOBAL_SUCCESS=0;
	public static final int GLOBAL_OTHER=999999;//其他错误
	public static final int GLOBAL_PARAM_ERROR=1;//参数错误
	public static final int GLOBAL_INNER_ERROR=2;//服务端内部错误
	
	//家长端
	//关联宝宝
	public static final int BABY_ERROR=100010;//宝宝信息错误
	
	public static final int VERIFY_FAIL=100007;//审核失败
	public static final int PRE_VERIFY=100006;//未审核
	public static final int CODE_CD=100005;//验证码cd
	public static final int CODE_SEND_FAILURE=100004;//验证码发送失败
	public static final int CODE_ERROR=100003;//验证码错误
	public static final int NEW_USER=100001;//新用户
	//老师端
	public static final int NON_USER=110001;//不是用户
	
	public static final int NON_BABY=200001;//宝宝不存在
	public static final int BABY_FAIL=200002;//不是宝宝的关联用户
	public static final int BABY_NO=200003;//没有关联宝宝
	public static final int BABY_ALREADY=200004;//宝宝已经关联
	
	
	public static final int UPDATE_PHONE_FAIL=500001;//修改失败
	public static final int PHONE_ALREADY=500002;//号码已被使用
	
	public static final int NON_CLASSES=510000;//不在此班级
	
	public static final int PRAISE_FAIL=400001;//点赞失败
	public static final int PRAISE_ALREADY=400002;//您已经赞过此相册
	public static final int DELETE_ALBUM_FAIL=400003;//删除相册失败
	public static final int DELETE_PRAISE_FAIL=400004;//取消点赞失败
	public static final int COMMENT_FAIL=400005;//评论失败
	public static final int DELETE_COMMENT_FAIL=400006;//删除评论失败
	
	public static final Map<Integer, String> respMap = new HashMap<Integer, String>();
	
	static{
		respMap.put(GLOBAL_SUCCESS, "成功");
		respMap.put(GLOBAL_OTHER, "其他错误");
		respMap.put(GLOBAL_PARAM_ERROR, "参数错误");
		respMap.put(GLOBAL_INNER_ERROR, "服务端内部错误");
		
		
		respMap.put(CODE_CD, "验证码cd");
		respMap.put(CODE_SEND_FAILURE, "验证码发送失败");
		respMap.put(CODE_ERROR, "验证码错误");
		respMap.put(NEW_USER, "新用户");
		respMap.put(PRE_VERIFY, "未审核");
		respMap.put(VERIFY_FAIL, "审核失败");
		respMap.put(BABY_ERROR, "宝宝信息错误");
		
		respMap.put(NON_USER, "不是用户");
		
		respMap.put(NON_BABY, "宝宝不存在");
		respMap.put(BABY_FAIL, "不是宝宝的关联用户");
		respMap.put(BABY_NO, "没有关联宝宝");
		respMap.put(PHONE_ALREADY, "宝宝已经关联");
		
		respMap.put(UPDATE_PHONE_FAIL, "修改失败");
		respMap.put(PHONE_ALREADY, "号码已被使用");
		
		respMap.put(NON_CLASSES, "不在此班级");
		
		respMap.put(PRAISE_FAIL, "点赞失败");
		respMap.put(PRAISE_ALREADY, "您已经赞过此相册");
		respMap.put(DELETE_ALBUM_FAIL, "删除相册失败");
		respMap.put(DELETE_PRAISE_FAIL, "取消点赞失败");
		respMap.put(COMMENT_FAIL, "评论失败");
		respMap.put(DELETE_COMMENT_FAIL, "删除评论失败");
		
	}
	
}
