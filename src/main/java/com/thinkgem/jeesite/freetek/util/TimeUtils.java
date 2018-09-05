package com.thinkgem.jeesite.freetek.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static SimpleDateFormat sdfSimple = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
	
	public static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 字符串转成日期
	 * @param date
	 * @return
	 */
	public static Date StrToDate(String s, SimpleDateFormat dateFormat){
		try {
			return dateFormat.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 字符串转成日期
	 * @param date
	 * @return
	 */
	public static Date StrToDate1(String s){
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String DateToStr(Date date, SimpleDateFormat dateFormat){
		String format;
		synchronized (dateFormat) {
			format = dateFormat.format(date);
		}
		return format;
	}
	
	/**
	 * 日期转成字符串
	 * @param date
	 * @return
	 */
	public static String DateToStrSimple(Date date){
		String format;
		synchronized (sdfSimple) {
			format = sdfSimple.format(date);
		}
		return format;
	}
	
	/**
	 * 日期转成字符串
	 * @param date
	 * @return
	 */
	public static String DateToStr1(Date date){
		String format;
		synchronized (sdf) {
			format = sdf.format(date);
		}
		return format;
	}

	/**
	 * 时间跳转到周一的同一时分秒
	 * @param date
	 * @return
	 */
	public static Date getWeekStartDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	
	/**
	 * 时间跳转到本月一号的同一时分秒
	 * @param date
	 * @return
	 */
	public static Date getMonthStartDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 * 时间跳转到周六的同一时分秒
	 * @param date
	 * @return
	 */
	public static Date getWeekStaDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return cal.getTime();
	}
	
	/**
	 * 时间跳转到传入日期的零点
	 * @param date
	 * @return
	 */
	public static Date getStartTime(Date date){ 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);   
        return new Date(cal.getTimeInMillis());  
    }
	
	/**
	 * 时间跳转到i天前或者i天后的同一时分秒
	 * @param i
	 * @return
	 */
	public static Date getNextDay(int i){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, i);
		return cal.getTime();
	}
	
	/**
	 * 时间跳转到i天前或者i天后的同一时分秒
	 * @param i
	 * @return
	 */
	public static Date getNextDay(Date date, int i){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, i);
		return cal.getTime();
	}
	
	/**
	 * 获得下周一的同一时分秒
	 * @param date
	 * @return
	 */
	public static Date getNextStartDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return cal.getTime();
	}
	
	public static Date getWeekDay(Date date, int i){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(i == 7){
			cal.add(Calendar.WEEK_OF_MONTH, 1);
			cal.set(Calendar.DAY_OF_WEEK, 1);
		} else {
			i = i+1;
			cal.set(Calendar.DAY_OF_WEEK, i);
		}
		
		return cal.getTime();
	}
	
	public static boolean isEqualsDate(Date date1, Date date2){
		if(date1 == null && date2 == null){
			return true;
		} else if(date1 != null && date2 != null){
			return sdfSimple.format(date1).equals(sdfSimple.format(date2));
		} else {
			return false;
		}
	}
	
	public static int compare(Date date1, Date date2){
		if(date1.getTime()>date2.getTime()){
			return 0;
		} else if(date1.getTime() == date2.getTime()) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public static boolean isSW(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.AM_PM) == 0){
			return true; 
		} else {
			return false;
		}
		
	}
	
	public static Date beforeMinute(Date date, int m){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, m);
		return cal.getTime();
	}

	/**
	 * 获取指定时间的那天 23:59:59.999 的时间
	 * @param date
	 * @return
	 */
	public static Date dayEnd(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 获取指定时间的那天 00:00:00.000 的时间
	 * @param date
	 * @return
	 */
	public static Date dayStart(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(getWeekDay(new Date(), 1));
		System.out.println(getNextDay(7));
		System.out.println(getNextStartDate(getNextDay(7)));
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartTime(new Date()));
		System.out.println(cal.get(Calendar.AM_PM));
		System.out.println(beforeMinute(new Date(), -30));
		System.out.println(getMonthStartDate(getNextDay(-2)));
		System.out.println("获取当天的最后时间:" + dayEnd(new Date()));
		System.out.println("获取当天的开始时间:" + dayStart(new Date()));
		try {
			System.out.println(dateFormat3.parse("2016-01"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		/*System.out.println(getWeekStartDate(new Date()));
		System.out.println(getStartTime(new Date()));
		System.out.println(getStartTime(getWeekStartDate(new Date())));
		System.out.println(getStartTime(getWeekStaDate(new Date())));
		System.out.println(getWeekStaDate(new Date()));
		System.out.println(getNextDay(13));
		System.out.println(getNextStartDate(new Date()));*/
		}
	}
}
