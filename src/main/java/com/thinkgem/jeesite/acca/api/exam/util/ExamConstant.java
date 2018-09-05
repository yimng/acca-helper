package com.thinkgem.jeesite.acca.api.exam.util;

import java.util.HashMap;
import java.util.Map;

public class ExamConstant {
	
	public final static int FREE_EXAM = 1;
	
	public final static int OFFI_EXAM_MAC = 2;
	
	public final static int OFFI_EXAM_WRI = 4;
	
	public final static int[] EXAM_TYPES = new int[]{FREE_EXAM, OFFI_EXAM_MAC, OFFI_EXAM_WRI};
	
	public final static Map<Integer, String> EXAM_TYPE_STRS = new HashMap<Integer, String>(); 
	
	public final static int SIGNUP_TYPE_WAIT_PAY = 10;//待支付
	
	public final static int SIGNUP_TYPE_WAIT_REV = 20;//待审核
	
	public final static int SIGNUP_TYPE_WAIT_CANCEL = 50;//已取消
	
	static{
		EXAM_TYPE_STRS.put(FREE_EXAM, "自由考试");
		EXAM_TYPE_STRS.put(OFFI_EXAM_MAC, "官方机考");
		EXAM_TYPE_STRS.put(OFFI_EXAM_WRI, "官方笔考");
	}

}
