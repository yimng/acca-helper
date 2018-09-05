package com.thinkgem.jeesite.freetek.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间、日期处理公共类
 * 
 * @author zhangl
 * @date 2012-8-4
 * 
 */

public class DateTimeUtils {
	/**
	 * 默认的日期格
	 */
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat sFullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final String DATE_SEPARATOR = "-/";

	/** 作日期分析之*/
	static StringTokenizer sToken;

	/**
	 * 取得当前日期
	 * 
	 * @return Date 当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 返回当前日期对应的默认格式的字符
	 * 
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_DATE_FORMAT);
	}

	/**
	 * 返回当前日期对应的指定格式的字符
	 * 
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate(String dateFormat) {
		return convertDate2String(getCurrentDate(), dateFormat);
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date
	 *            - 要转换的日期
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !"".equals(dateFormat)) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		return sdf.format(date);
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date
	 *            - 要转换的日期
	 * @param dateFormat
	 *            - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date) {
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 将字符串转换成日
	 * 
	 * @param stringDate
	 *            - 要转换的字符串格式的日期
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate) {
		return convertString2Date(stringDate, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 将字符串转换成日
	 * 
	 * @param stringDate
	 *            - 要转换的字符串格式的日期
	 * @param dateFormat
	 *            - 要转换的字符串对应的日期格式
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate, String dateFormat) {
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !"".equals(dateFormat)) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		try {
			return sdf.parse(stringDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return new Date(System.currentTimeMillis());
		}
	}

	/**
	 * 将一种格式的日期字符串转换成默认格式的日期字符串
	 * 
	 * @param oldDate
	 *            - 要格式化的日期字符串
	 * @param oldFormat
	 *            - 要格式化的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate, String oldFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat),
				DEFAULT_DATE_FORMAT);
	}

	/**
	 * 将一种格式的日期字符串转换成另一种格式的日期字符
	 * 
	 * @param oldDate
	 *            - 要格式化的日期字符串
	 * @param oldFormat
	 *            - 要格式化的日期的格式
	 * @param newFormat
	 *            - 格式化后的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate,
			String oldFormat, String newFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat),
				newFormat);
	}

	/**
	 * 根据年份和月份判断该月有几天
	 * 
	 * @param year
	 *            - 年份
	 * @param month
	 *            - 月份
	 * @return int
	 */
	public static int days(int year, int month) {
		int total = 30;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			total = 31;
			break;
		case 2:
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                total = 29;
            } else {
                total = 28;
            }
			break;
		default:
			break;
		}
		return total;
	}

	private static final String datePattern1 = "\\d{4}\\d{2}\\d{2}";

	private static final String datePattern3 = "^((((1[6-9]|[2-9]\\d)\\d{2})(0?[13578]|1[02])(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})(0?[13456789]|1[012])(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})0?2(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0?2-29))$";

	/**
	 * 判断是否是有效的日期 1.位数82.年份大于1900 3.格式yyyyMMdd
	 * */
	public static boolean isValidDate(String sDate) {
		if ((sDate != null)) {
			if (sDate.trim().length() != 8) {
                return false;
            }
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern3);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	public static Timestamp getTimestamp(String strDate) {
		return Timestamp.valueOf(strDate);
	}

	/**
	 * 得到当前日期的前后日+为后 -为前
	 * 
	 * @param day_i
	 * @return
	 */
	public static final String getBefDateString(String currentDate, int day_i, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(currentDate);
			Calendar day = Calendar.getInstance();
			day.setTime(date);
			day.add(Calendar.DATE, day_i);
			return sdf.format(day.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static long getRuntime(long begintime) {
		return ((System.currentTimeMillis() - begintime)) / 1000;
	}


	/**
	 * 取当前日前后 offset天的日期
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getDate(int offset) {
		Calendar cal = Calendar.getInstance();// 使用默认时区和语境获得一个日历
		// cal.add(Calendar.DAY_OF_MONTH, -1);// 取当前日期的前一
		cal.add(Calendar.DAY_OF_MONTH, offset);// 取当前日期的后一
		String sdate = convertDate2String(cal.getTime(),DEFAULT_DATE_FORMAT);
		return convertString2Date(sdate);
	}
	
//	public static Date getToday() {
//		Date today = new Date();
//		return new SimpleDateFormat("yyyy-MM-dd").parse(today.toString());
//		return today;
//	}

	/**
	 * 取当前小时数 24
	 * 
	 * @return
	 */
	public static int getCurrentHour24() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取当前系统分钟数
	 * 
	 * @return
	 */
	public static int getCurrentMin() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	

	public static boolean isInPeriod(Date start, Date end, Date time) {
		if (start == null || end == null) {
            return true;
        }
		if (start.after(time)) {
            return false;
        }
		if (time.after(end)) {
            return false;
        }
		return true;
	}
	

	/** 
     * 计算两个日期之间相差的天数 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int daysBetween(Date date1,Date date2)  
    {  
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			date1 = dateFormat.parse(dateFormat.format(date1));
			date2 = dateFormat.parse(dateFormat.format(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);  
          
       return Integer.parseInt(String.valueOf(between_days));         
    }  
	
    
    public static int hoursBetween(Date date1,Date date2)
    {  
        long time1 = date1.getTime();             
        long time2 = date2.getTime();
        long between_hours=(time2-time1)/(1000*3600);
          
       return Integer.parseInt(String.valueOf(between_hours));      
    }  
    
    
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

	/**
	 * 获取当月的第一天
	 * @param year
	 * @param month
     * @return
     */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
	}

	/**
	 * 获取当月的最后一天
	 * @param year
	 * @param month
     * @return
     */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 获取上个月
	 * @param date
	 * @return
     */
	public static Date getLastDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public static void main(String[] args) {
		//获取当前的日期
		Date currentDate = DateTimeUtils.getCurrentDate();
		String currentStr = DateTimeUtils.convertDate2String(currentDate);
		System.out.println(currentStr);
		System.out.println(Integer.parseInt(currentStr.substring(0,4)));
		System.out.println(Integer.parseInt(currentStr.substring(5,7)));
		System.out.println(Integer.parseInt(currentStr.substring(8,10)));
		int i = 4;
		while (i % 3 != 0){
			i ++ ;
			//System.out.println(i);
		}
		//System.out.println(i);
		Date date = convertString2Date("2016-09-23","yyyy-MM-dd");
		int daysBetween = daysBetween(new Date(), date);
		System.out.println(daysBetween);
		String lastDayOfMonth = getLastDayOfMonth(2016, 2);
		//System.out.println(lastDayOfMonth);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date now = new Date();
		System.out.println(sdf.format(now));
		System.out.println(sdf.format(getLastDate(now)));
	}

	/**
	 * 用户检查两个时间段，是否存在相交可能
	 * @param startTime1
	 * @param endTime1
	 * @param startTime2
	 * @param endTime2
	 * @return
	 */
	public static boolean checkTwoTimeIntersect(Date startDate1,Date endDate1,Date startDate2,Date endDate2){
		long startTime1 = startDate1.getTime();
		long startTime2 = startDate2.getTime();
		long endTime1 = endDate1.getTime();
		long endTime2 = endDate2.getTime();
		
		if(startTime2>startTime1 && startTime2<endTime1){
			return true;
		}
		if(startTime1>startTime2 && startTime1<endTime2){
			return true;
		}
		
		if(startTime2<startTime1 && endTime2>endTime1){
			return true;
		}
		
		if(startTime1<startTime2 && endTime1>endTime2){
			return true;
		}
		
		
		return false;
	}
	
}
