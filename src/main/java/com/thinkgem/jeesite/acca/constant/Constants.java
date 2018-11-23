package com.thinkgem.jeesite.acca.constant;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.freetek.api.constant.PublicConstants;


@Component
public class Constants extends PublicConstants {
	
	public static final String STR_DATETIMEFORMAT = "yyyy-MM-dd HH:mm";
	public static final SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat(STR_DATETIMEFORMAT);
	
	public static final Map<Integer, String> EXAM_TYPE_NAMES = new HashMap<Integer, String>();
	
	public static final Map<Integer, String> ORDER_STATUS_TYPES = new LinkedHashMap<Integer, String>();
	
	static {
		EXAM_TYPE_NAMES.put(ExamType.self, "自有机考");
		EXAM_TYPE_NAMES.put(ExamType.officialAll, "官方机考和笔考");

		ORDER_STATUS_TYPES.put(OrderStatus.uncheckd, "待审核");
		ORDER_STATUS_TYPES.put(OrderStatus.unpay, "待支付");
		ORDER_STATUS_TYPES.put(OrderStatus.checkSuccess, "审核通过");
		ORDER_STATUS_TYPES.put(OrderStatus.checkSupplement,"补传信息");
		ORDER_STATUS_TYPES.put(OrderStatus.checkFail, "审核未通过");
		ORDER_STATUS_TYPES.put(OrderStatus.cancel, "已取消");
		ORDER_STATUS_TYPES.put(OrderStatus.delete, "已删除");
		//ORDER_STATUS_TYPES.put(OrderStatus.change, "已延期");
	}
	
	
	public static class AccaUserType{
		public static int general = 1;//普通用户
		public static int learning = 2;//学习达人
		public static int tearcher = 3;//名师
	}
	
	public static class AccaUserStatus{
		public static int normal = 1;//正常
		public static int frozen = 2;//冻结
	}
	
	
	public static class ArticleType{
		public static int normal = 1;//有资有料html文章
		public static int course = 2;//公开课文章
		public static int learning = 3;//学习达人
		public static int tearcher = 4;//名师指导
		public static int h5 = 5;//h5		
	}
	
	public static class ArticleCourseLiveStatus{
		public static int unstart = 1;
		public static int living = 2;
		public static int closed = 3;
	}
	
	public static class ExamType{
		public static int self=1;//自有考试
		public static int officialM=2;//官方机考
		public static int officialPen=4;//官方笔考
		public static int officialAll = 6;//官方机考和笔考
	}

	public enum CouponStatus {
		NEW("NEW"), USING("USING"),CONFIRM("CONFIRM"),USED("USED");
		private String status;
		CouponStatus(String c) {
			this.status = c;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}
	
	public static class OrderStatus{
		public static int unpay=10;//待支付
		public static int uncheckd=20;//待审核
		public static int checkSuccess=30;//审核通过
		public static int checkSupplement=35;//补传信息
		public static int checkFail=40;//审核失败
		public static int cancel=50;//已取消
		public static int delete=60;//以删除
		//public static int change=70;//调整延期
	}

	public static class InviteStatus {
		public static String inviting = "0";
		public static String success = "1";
		public static String failure = "2";

	}
	
	public static class DeviceAuthStatus{
		public static int unauth=1;//未授权
		public static int authed=2;//已授权
	}
	
	public static class ExamFlag {
		public static int now=1;//当前考试
		public static int old=0;//历史考试
	}
}
