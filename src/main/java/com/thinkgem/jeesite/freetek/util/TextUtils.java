package com.thinkgem.jeesite.freetek.util;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;


/**
 * 字符串工具类，常用字符操作放在此处，如email格式比较，phone格式比较等
 * @author lihaifeng
 *
 */

public class TextUtils {
	public static boolean checkEmailFormat(String email){
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if(StringUtils.isEmpty(email) || !email.trim().matches(regex)){
			return false;
		}
		return true;
	}
	public static boolean checkPhoneFormat(String phone){
		if(StringUtils.isEmpty(phone) || !(phone.matches("\\d{11}"))){
			return false;
		}
		return true;
	}
	//检查真实qq号
	public static boolean checkQqFormat(String qq){
		if(StringUtils.isEmpty(qq) || !(qq.matches("^[1-9][0-9]{4,11}$"))){
			return false;
		}
		return true;
	}

	
	public static String getNbitRandomNum(int n){
		int maxNum = (int) Math.pow(10, n);
		Random rand = new Random();
		int num = rand.nextInt(maxNum);
		String str = String.valueOf((maxNum+num)).substring(1);
		return str;
	}
	
	public static String getRandomString(int length){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		StringBuffer sbuf = new StringBuffer();
		for(int i=0;i<length;i++){
			int num = rand.nextInt(str.length());
			sbuf.append(str.charAt(num));
		}
		return sbuf.toString();
	}
	

	/**
	 * 生成支付单编号(两位随机 + 从2000-01-01 00:00:00 到现在的秒数+微秒+会员ID%1000)，该值会传给第三方支付接口
	 * 长度 =2位 + 10位 + 3位 + 3位  = 18位
	 * 1000个会员同一微秒提订单，重复机率为1/100
	 * @return string
	 */
	public static String getPaySn(int memberId){
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(10+new Random().nextInt(90))
			.append(addZero(String.valueOf(new Date().getTime()-946656000000L), 13))
			.append(addZero(String.valueOf(memberId%1000),3));
		
		return sbuf.toString();
	}
	

	
	/**
	 * 给字符串在前面补零
	 * str：目标字符串
	 * len:补零后的长度
	 * 如参数("aaa",5),输出"00aaa"
	 */
	public static String addZero(String str,int len){
		if(str.length()>=len){
			return str;
		}
		int indexLen = len-str.length();
		String strTemp = "";
		for(int i=0;i<indexLen;i++){
			strTemp+="0";
		}
		return strTemp+str;
	}
	
	/**
	 * 根据富文本内容和需要截取的预览文字长度，截取其预览文本
	 * @param content
	 * @param len
	 * @return
	 */
	public static String getContentPreview(String content,int len){
		if(content==null || "".equals(content)){
			return "";
		}
		
		String contentPreview = HtmlUtil.delHTMLTag(content);
		if(contentPreview==null || "".equals(contentPreview)){
			return "";
		}
		if(contentPreview.length()>=len){
			return contentPreview.substring(0,len);
		}else{
			return contentPreview;
		}
		
	}
	
	/**
	 * 类似于微博中显示点赞数，评论数，浏览量等，用于格式化数字显示
	 * 根据传入的浏览量数字，格式化成需要的字符串类型，如传入“100000”则返回“10万+”
	 * @param viewNum
	 * @return
	 */
	public static String formatViewNum(Long viewNum){
		if(viewNum==null){
			return "0";
		}
		if(viewNum<10000){
			return  ""+viewNum;
		}else if(viewNum<100000){
			double temp = ((double)viewNum)*0.0001;
			String str = String.format("%.1f", temp)+"万";
			return str;
		}else{
			return "10万+";
		}
	}
	
}
