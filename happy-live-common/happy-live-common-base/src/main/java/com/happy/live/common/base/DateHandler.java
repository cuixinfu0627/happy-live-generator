package com.happy.live.common.base;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.*;
import java.util.*;

/**
 * 类: DateHandler <br>  
 * 描述: 日期时间处理类 <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间:2017年11月22日 下午2:13:10 <br>
 */
public class DateHandler {

	private static Logger logs = LoggerFactory.getLogger(DateHandler.class);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static String FORMAT_GENERAL = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd
	 */
	public final static String FORMAT_GENERAL1 = "yyyy-MM-dd";
	/**
	 * MM/dd
	 */
	public final static String FORMAT_GENERAL2 = "MM/dd";
	/**
	 * HH:mm
	 */
	public final static String FORMAT_GENERAL3 = "HH:mm";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public final static String FORMAT_GENERAL4 = "yyyy-MM-dd HH:mm";
	
	/**
	 * MM-dd
	 */
	public final static String FORMAT_GENERAL5 = "MM-dd";
	
	/**
	 * 早上8点到下午16点，每隔半小时(一共是16个半小时)
	 */
	public final static int EIGHTH_TO_SIXTEEN = 16;
	
	/**
	 * 获取12小时制当前日期字符串
	 * 
	 * @return
	 */
	public static String getStrDate_12() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date currentTime = new Date();
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	/**
	 * 获取24小时制当前日期字符串
	 * 
	 * @return
	 */
	public static String getStrDate_24() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	/**
	 * 获取格式化当前时间、毫秒字符串
	 * 
	 * @return
	 */
	public static String getStrDateS() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SS");
		Date currentTime = new Date();
		String strDate = formatter.format(currentTime);
		return strDate;
	}

	/**
	 * 转换日期为字符串格式
	 * <p>
	 * 
	 * @param Date
	 * @return
	 */
	public static String DateToStr(Date Date) {
		if(Date != null){
			SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_GENERAL);
			String strDate = formatter.format(Date);
			return strDate;
		}
		return "";
	}

	/**
	 * 奖date类型的日期转换为指定格式
	 */
	public static Date DateToDate(Date Date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date d = new Date();
		String dd = formatter.format(d);
		Date ddd = null;
		try {
			ddd = formatter.parse(dd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ddd;
	}

	/**
	 * 转换日期为格式化字符串
	 * 
	 * @param Date
	 * @param format
	 * @return
	 */
	public static String DateToFormatStr(Date Date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String strDate = formatter.format(Date);
		return strDate;
	}

	/**
	 * 获取当前日期 格式为 yyyy-MM-dd
	 * 
	 * @return strDate
	 */
	public static String getNowStrDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * 获取当间时间字符串 yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getLongDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * 获取当间时间字符串 yyyyMMddHHmmssSS
	 * 
	 * @return
	 */
	public static String getLongDateS() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
		Date date = new Date();
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * 比较二个日期
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean Compare_Date(Date date1,
                                       Date date2) {
		return date1.equals(date2);
	}

	/**
	 * 将字符串类型的时间转化为Date型
	 * 
	 * @param strDate
	 * @param formatDate
	 * @return Date
	 * @throws ParseException
	 */
	public static Date str2Date(String strDate, String formatDate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		return sdf.parse(strDate);
	}

	/**
	 * 将字符串类型的时间转化为Date型，并将在此时间上进行增加或减少相应天
	 * 
	 * @param strDate
	 * @param formatDate
	 * @return Date
	 * @throws ParseException
	 */
	public static Date otherDate(String strDate, String formatDate, int num)
			throws ParseException {
		Calendar c = new GregorianCalendar();

		Date date = str2Date(strDate, formatDate);

		c.setTime(date);

		c.add(Calendar.DATE, num);

		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);

		return str2Date(sdf.format(c.getTime()), formatDate);
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60;
			if ((y - u) > 0){
				return y - u + "";
			}
			else {
				return "0";
			}
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0){
			return true;
		}
		else if ((year % 4) == 0) {
			if ((year % 100) == 0){
				return false;
			}else{
				return true;
			}
		} else{
			return false;
		}
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR)){
				return true;
			}
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR)){
				return true;
			}
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateHandler.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")){
			// 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		} 
		else if (num.equals("2")){
			// 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		} 
		else if (num.equals("3")){
			 // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		}
		else if (num.equals("4")){
			// 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		} 
		else if (num.equals("5")){
			// 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		} 
		else if (num.equals("6")){
			 // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		}
		else if (num.equals("0")){
			// 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		} 
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateHandler.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateHandler.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals("")){
			return 0;
		}
		if (date2 == null || date2.equals("")){
			return 0;
		}
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}
	/**获取两个时间相差的天数
	 * @param beforeTime
	 * @param nowTime
	 * @return
	 */
	public static Map<String,Object> compareTimeDiff(String beforeTime,String nowTime){
		Map<String,Object> map = new HashMap<String,Object>();
		long min = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date before = sdf.parse(beforeTime); 
			Date now = sdf.parse(nowTime);
			Calendar c1 = Calendar.getInstance();  
			Calendar c2 = Calendar.getInstance();  
			c1.setTime(before);  
			c2.setTime(now);  
			long l=now.getTime()-before.getTime();  
			long day=l/(24*60*60*1000);  
			long hour=(l/(60*60*1000)-day*24);  
			min=((l/(60*1000))-day*24*60-hour*60);  
			long s=(l/1000-day*24*60*60-hour*60*60-min*60); 
			//System.out.println("相差："+day+"天"+hour+"小时"+min+"分"+s+"秒");  
			map.put("day", day);
			map.put("hour", hour);
			map.put("min", min);
			map.put("s", s);
			return map;
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return map;
	}
	/**获取两个时间相差的天数
	 * @param beforeTime
	 * @param nowTime
	 */
	public static long compareTimeInterval(String beforeTime,String nowTime){
		long min = 0;
		long l = 0 ;
		long day = 0 ;
		long hour = 0 ;
		long s = 0 ;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date before = sdf.parse(beforeTime); 
			Date now = sdf.parse(nowTime);
			Calendar c1 = Calendar.getInstance();  
			Calendar c2 = Calendar.getInstance();  
			c1.setTime(before);  
			c2.setTime(now);  
			l=now.getTime()-before.getTime();  
			day=l/(24*60*60*1000);  
			hour=(l/(60*60*1000)-day*24);  
			s=(l/1000-day*24*60*60-hour*60*60-min*60); 
			System.out.println("相差："+day+"天"+hour+"小时"+min+"分"+s+"秒");  
			return (long) day;
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return day;
	}

	/**获取两个时间相差的天数
	 * @param beforeTime
	 * @param nowTime
	 */
	public static Map<String,Object> compareTimeYear(String beforeTime,String nowTime){
		Map<String,Object> result = new HashMap<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fromDate = sdf.parse(beforeTime);
			Date toDate = sdf.parse(nowTime);

			Calendar  from  =  Calendar.getInstance();
			from.setTime(fromDate);
			Calendar  to  =  Calendar.getInstance();
			to.setTime(toDate);

			int fromYear = from.get(Calendar.YEAR);
			int fromMonth = from.get(Calendar.MONTH);
			int fromDay = from.get(Calendar.DAY_OF_MONTH);

			int toYear = to.get(Calendar.YEAR);
			int toMonth = to.get(Calendar.MONTH);
			int toDay = to.get(Calendar.DAY_OF_MONTH);
			int year = toYear  -  fromYear;
			int month = toMonth  - fromMonth;
			int day = toDay  - fromDay;
			result.put("year",year);
			result.put("month",month);
			result.put("day",day);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几
		Date date = DateHandler.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateHandler.getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * 
	 * @param k
	 *            表示是取几位随机数，可以自己定
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * 
	 * 获得当前时间的后多少天
	 * 
	 * @param curr 
	 * @param count
	 * @return
	 */
	public static Date getNextDate(Date curr, int count) {
	    Calendar  calendar = Calendar.getInstance();
	    calendar.setTime(curr);
	    calendar.add(Calendar.DAY_OF_MONTH, count);  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date= str2Date(sdf.format(calendar.getTime()),"yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0){
			return "";
		}	
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	public static boolean RightDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		;
		if (date == null){
			return false;
		}
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/**
	 * 返回两个年份的差值，多少年保留一位小数 prram yyyy-MM-dd
	 */
	public static String timeToTime(String startdate, String enddate) {
		String date1[] = startdate.split("-");
		int year1 = 365;
		int month1 = 31;
		int day1 = Integer.parseInt(date1[2]);
		Long totalday1;
		String date2[] = enddate.split("-");
		int year2 = 365;
		int month2 = 31;
		int day2 = Integer.parseInt(date2[2]);
		Long totalday2;
		float time;
		if ("04".equals(date1[2]) || "06".equals(date1[2])
				|| "09".equals(date1[2]) || "11".equals(date1[2])) {

			month1 = 30;
		}
		if ("02".equals(date1[2])) {
			if (isLeapYear(startdate)) {
				month1 = 29;
			} else {
				month1 = 28;
			}
		}
		if ("04".equals(date2[2]) || "06".equals(date2[2])
				|| "09".equals(date2[2]) || "11".equals(date2[2])) {

			month2 = 30;
		}
		if ("02".equals(date2[2])) {
			if (isLeapYear(enddate)) {
				month2 = 29;
			} else {
				month2 = 28;
			}
		}
		totalday1 = Long.parseLong(date1[0]) * year1
				+ Integer.parseInt(date1[1]) * month1 + day1;
		totalday2 = Long.parseLong(date2[0]) * year2
				+ Integer.parseInt(date2[1]) * month2 + day2;
		time = (float) (totalday1 - totalday2) / 365;
		DecimalFormat df = new DecimalFormat("0.0");// 格式化小数，不足的补0
		return df.format(time);// 返回的是String类型的
	}
	/**   
     * @param startDay 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDay 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年   
     * @return   
     * 举例： 
     * compareDate("2009-09-12", null, 0);//比较天 
     * compareDate("2009-09-12", null, 1);//比较月 
     * compareDate("2009-09-12", null, 2);//比较年 
     */   
public static int compareDate(String startDay,String endDay,int stype){    
    int n = 0;    
    String[] u = {"天","月","年"};    
    String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";    
        
    endDay = endDay==null?getNowStrDate():endDay;    
        
    DateFormat df = new SimpleDateFormat(formatStyle);    
    Calendar c1 = Calendar.getInstance();    
    Calendar c2 = Calendar.getInstance();    
    try {    
        c1.setTime(df.parse(startDay));    
        c2.setTime(df.parse(endDay));    
    } catch (Exception e3) {    
        System.out.println("wrong occured");    
    }    
    //List list = new ArrayList();    
    while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果    
        //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来    
        n++;    
        if(stype==1){    
            c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1    
        }    
        else{    
            c1.add(Calendar.DATE, 1);           // 比较天数，日期+1    
        }    
    }    
    n = n-1;    
    if(stype==2){    
        n = (int)n/365;    
    }       
    System.out.println(startDay+" -- "+endDay+" 相差多少"+u[stype]+":"+n);          
    return n;    
}     
	/**
	 * 向前或者向后推几个月
	 * 
	 * @param strDate
	 * @param formatDate
	 * @return
	 */
	public static Date timeToBeforeOrAfter(String strDate, String formatDate,
			int num) {
		try {
			Calendar c = new GregorianCalendar();
			Date d = str2Date(strDate, "yyyy-MM-dd");
			c.setTime(d);
			c.add(Calendar.MONTH, num);
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			return str2Date(sdf.format(c.getTime()), formatDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 得到两日期相差几个月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMonthDiff(String startDate, String endDate)
			throws ParseException {
		int monthday;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate1 = fmt.parse(startDate);

		Calendar starCal = Calendar.getInstance();
		starCal.setTime(startDate1);

		int sYear = starCal.get(Calendar.YEAR);
		int sMonth = starCal.get(Calendar.MONTH);
		int sDay = starCal.get(Calendar.DAY_OF_MONTH);

		Date endDate1 = fmt.parse(endDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate1);
		int eYear = endCal.get(Calendar.YEAR);
		int eMonth = endCal.get(Calendar.MONTH);
		int eDay = endCal.get(Calendar.DAY_OF_MONTH);

		monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

		// 这里计算零头的情况，根据实际确定是否要加1 还是要减1
		if (sDay < eDay) {
			monthday = monthday + 1;
		}
		return monthday;
	}
	
	/** 
	 * 奖string类型的日期转换为指定格式
	 */
	public static Date stringToDate(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_GENERAL1);
		Date ddd = null;
		try {
			ddd = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ddd;
	}
	
	/**
	 * 奖date类型的日期转换为指定格式
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_GENERAL1);
		String ddd = null;
		ddd = formatter.format(date);
		return ddd;
	}
	
	public static String dateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String ddd = null;
		ddd = formatter.format(date);
		return ddd;
	}
	
	/**
	 * 将string类型的日期转换为指定格式：返回格式1的日期
	 * 错误将返回当前时间
	 */
	public static Date stringToDate1(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_GENERAL);
		Date ddd = new Date();
		try {
			ddd = formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ddd;
	}
	
	/**
	 * 
	 * 获得当前时间加上或者减去几天的时间的字符串
	 * @return
	 */
	public static String getAddDateStr(String dateStr, int addDate) {
	    Calendar  calendar = Calendar.getInstance();
	    calendar.setTime(stringToDate(dateStr));
	    calendar.add(Calendar.DATE, addDate);  
		return dateToString(calendar.getTime());
	}
	
	/**
	 * 
	 * 获得当前时间加上或者减去几天的时间的字符串
	 * 
	 * @param date
	 * @param addDate
	 * @return
	 */
	public static String getAddDateStr(Date date, int addDate) {
	    Calendar  calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, addDate);  
		return dateToString(calendar.getTime());
	}
	
	public static Date getAddDate(Date date, int addType, int addDate) {
	    Calendar  calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(addType, addDate);  
		return calendar.getTime();
	}
	
	public static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	public static Date parseDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(dateStr);
	}
	
	public static Date parseDate(Date date, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(formatter.format(date));
	}
	
	/**
	 * 定位到早上8点
	 */
	public static Date goToEighth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 定位到0点
	 */
	public static Date goToZero(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	
	/**
	 * @param d1
	 * @return 返回日期是星期几
	 */
	public static int getDateWeek(Date d1){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		if(i == 1){
			return 8;
		}
		return i;
	}
	/**
	 * 日期实体对象
	 */
	public static final class DateModel {
		public DateModel(int year, int month, int day, int week, long millisecond) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.week = week;
			this.millisecond = millisecond;
		}

		private int year;
		private int month;
		private int day;
		private int week;
		private long millisecond;

		public long getMillisecond() {
			return millisecond;
		}

		public void setMillisecond(long millisecond) {
			this.millisecond = millisecond;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getMonth() {
			if (month < 10) {
				return "0" + month;
			}
			return "" + month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public String getDay() {
			if (day < 10) {
				return "0" + day;
			}
			return "" + day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getWeek() {
			return week;
		}

		public void setWeek(int week) {
			this.week = week;
		}

		@Override
		public String toString() {
			return String.format("%s-%s-%s\t%s\t%s", year, month, day, week, millisecond);
		}
	}
	
	/**
	 * 获取日期实体 Calendar
	 */
	private static Calendar getCalendar(){
		Calendar claendar = Calendar.getInstance();
		claendar.setFirstDayOfWeek(Calendar.MONDAY);
		claendar.setTimeInMillis(claendar.getTimeInMillis());
		return claendar;
	}
	private static DateModel newInstansD(Calendar c){
		 return new DateModel(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.WEEK_OF_YEAR),c.getTimeInMillis());
	}
	/**
	 * 将TimeStamp 转换为形如 yyyyMMddHHmmssSSS
	 */
	public static String getMSecondFormatStr(Timestamp timeStamp) {
		long time = timeStamp.getTime();
		Date date = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sf.format(date);
	}

	/**
	 * 将TimeStamp 转换为形如 yyyyMMddHHmmssSSS
	 */
	public static long getMSecondFormatLong(Timestamp timeStamp) {
		return Long.parseLong(getMSecondFormatStr(timeStamp));
	}
	
	/**
	 * 将long型date转换为yyyyMMddHHmmssSSS
	 */
	public static long getLongFormatDate(long time){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date(time);
		return Long.parseLong(sf.format(date));
	}
	
	/**
	 * 根据时间戳获取"yyyy-MM-dd HH:mm:ss.SSS"格式的字符串
	 * @param longTime 时间戳
	 * @return
	 */
	public static String getFormatMSecondFromStamp(long longTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = new Date(longTime);
		return sdf.format(date);
	}
	
	/**
	 * 获得时间戳的天 yyyyMMdd
	 */
	public static int getDay(long time){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date(time);
		return Integer.parseInt(sf.format(date));
	}
	
	/**
	 * 获得时间戳的天 yyyy-MM-dd
	 */ 
	public static String getDay2(long time){
		Date date = new Date(time);
		return getDay2(date);
	}
	
	/**
	 * 获得时间戳的天 yyyy-MM-dd
	 */ 
	public static String getDay2(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}
	
	/**
	 * 获得当天日期
	 */
	public static int getDay(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(sf.format(new Date()));
	}
	
	/**
	 * 取得7天前的日期
	 */
	public static int get7DayBeforDate(long time) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 7);
		return Integer.parseInt(sf.format(c.getTime()));
	}
	
	/**
	 * 返回date日期当月最后一天的 23:59:59
	 * @param date
	 * @return
	 */
	public static long getEndPointOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1); //last day of that month
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTimeInMillis();
	}
	
	/**获取指定日期当天的截止时间的时间戳:23:59:59.999
	 * @param date
	 * @return
	 */
	public static long getEndPointOfDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 返回date日期当月的第一天的00:00:00时间
	 * @param date
	 * @return
	 */
	public static long getInitialPointOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 得到日期所在月的第一天  格式:20150209
	 */
	public static int getFirstDayOfMonth(Date date){
		return getDay(getInitialPointOfMonth(date));
	}
	
	/**
	 * 得到日期所在月的最后一天  格式:20150209
	 */
	public static int getLastDayOfMonth(Date date){
		return getDay(getEndPointOfMonth(date));
	}
	
	/**
	 * 根据pattern将int类型转换为型时间表示格式
	 * eg: int d=20150209 pattern='yyyyMMdd' resutrn value是 Date 时间：'2015-02-09 00:00:00' 
	 * @param date
	 * @param pattern
	 */
	public static Date convertIntToDate(int date, String pattern) throws ParseException{
		return new SimpleDateFormat(pattern).parse(String.valueOf(date));
	}
	
	/**
	 * 返回从今天开始的加了n天以后的日期的23:59:59，
	 * 如果是负数就是今天以前的日期，如n=-1就返回昨天23:59:59时间
	 * @param n
	 * @return
	 */
	public static long addNDaysFromTodayEndPoint(int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, n);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 返回从今天开始的加了n天以后的日期的00:00:00，
	 * 如果是负数就是今天以前的日期，如n=-1就返回昨天00:00:00时间
	 * @param n
	 * @return
	 */
	public static long addNDaysFromTodayInitialPoint(int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, n);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 返回从某天开始的加了n天以后的日期的00:00:00
	 * 如果是0 则是那天   如果是负数 则返回那天之前的00:00:00的时间
	 * @param time   时间戳
	 * @param n      距离的天数
	 * @return
	 */
	public static long addNDaysFromSomeDayInitialPoint(long time, int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(time));
		cal.add(Calendar.DAY_OF_MONTH, n);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 得到N天的天数（格式：20150209）
	 */
	public static int addNDays4Today2Day(int n){
		return getDay(addNDaysFromTodayInitialPoint(n));
	}
	
	/**
	 * 取得前day天的日期数组
	 */
	public static List<Integer> getDayBeforDateArr(long time, int day){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		List<Integer> arr = new ArrayList<Integer>();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 7);
		for(int i=1; i<=day; i++){
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
			arr.add(Integer.parseInt(sf.format(c.getTime())));
		}
		return arr;
	}
	
	/**
	 * 获取今天最后时间的时间戳       时间未精确到毫秒.建议使用getTodayLastTime 方法
	 * 结束时间yyyy-MM-dd 23:59:59
	 */
	@Deprecated
	public static long getToDayLastTime(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime().getTime();
	}

    /**
     * 获取今天最后时间的时间戳  时间未精确到毫秒.建议使用getTodayLastTime 方法
     * 结束时间yyyy-MM-dd 23:59:59
     */
	@Deprecated
    public static long getToDayLastTime() {
        return getToDayLastTime(System.currentTimeMillis());
    }
    
    /**
     * 获取今天最后时间的时间戳 ,精确到毫秒
     * 结束时间yyyy-MM-dd 23:59:59
     */
    public static long getTodayLastTime() {
        return getTodayLastTime(System.currentTimeMillis());
    }
    
    /**获取今天的开始时间(yyyy-MM-dd 00:00:00.000的时间戳)
     * @return 
     */
    public static long getTodayStartTime(){
    	return getDayStartTime(System.currentTimeMillis());
    }
    
    /**获取指定日的开始时间
     * @param time
     * @return
     */
    public static long getDayStartTime(long time){
    	 Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(time);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         cal.set(Calendar.MILLISECOND, 0);
         return cal.getTimeInMillis();
    }
    
    /**
     * 获取今天最后时间的时间戳       
     * 结束时间yyyy-MM-dd 23:59:59,精确到毫秒
     */
    public static long getTodayLastTime(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.SECOND, -1);
        return cal.getTime().getTime();
    }
	
	/**
	 *  取得时间中的特定field值,eg: 2015-03-08 22:01:02 取得 MM 为 03 
	 * @param dateTime eg: 12345678901234
	 * @param pattern eg: "yyyy" | "MM" | "dd" | "HH" | "mm" | 'ss'
	 */
	public static int getCalendarField(long dateTime, String pattern, int defVal){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(dateTime));
		if(pattern.equals("yyyy")){
			return cal.get(Calendar.YEAR);
		}
		else if(pattern.equals("MM")){
			return cal.get(Calendar.MONTH);
		}
		else if(pattern.equals("dd")){
			return cal.get(Calendar.DAY_OF_MONTH);
		}
		else if(pattern.equals("HH")){
			return cal.get(Calendar.HOUR_OF_DAY);
		}
		else if(pattern.equals("mm")){
			return cal.get(Calendar.MINUTE);
		}
		else if(pattern.equals("ss")){
			return cal.get(Calendar.SECOND);
		}
		else{
			return defVal;
		}
	}

	/**
	 * 得到yyyy-MM-dd格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear_Month_Day(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * 比较当前时间与给定时间的大小
	 * @param time
	 * @param minutes
	 * @return
	 * @throws Exception
	 */
	public static boolean compareDate(String time,int minutes){
		boolean b = false;
	    Date   nows=new   Date();  
	    Date date = parseDatetime(time);		
		long   minms=nows.getTime()-(date.getTime()+minutes*60*1000); 
		if (minms >= 0){
		   b=true;
		}			   
		 return b;
	}
	
	/**
	 * 将字符串转为yyyy-MM-dd HH:mm:ss格式的Date对象
	 * @param date
	 * @return
	 */
	public static Date parseDatetime(String date){
		SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date s = t.parse(date);
			return s;
		} catch (ParseException e) {
			logs.error("method(parseDatetime) param="+date+"  error \r\n"+ StringHandler.getExceptionStack(e));
			return null;
		} 
	}
	
	/**
	 * 得到yyyy-MM-dd HH:mm:ss格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 获取上一周时间
	 */
	public static long getLastWeek(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.WEEK_OF_YEAR, -1);
		return c.getTime().getTime();
	}
	
	/**
	 * 获取当前月份  yyyyMM格式
	 */
	public static int getDayOfMonth(){
		DateFormat format = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		return Integer.parseInt(format.format(date));
	}
	
	/**
	 * 获取指定时间的月份  yyyyMM格式
	 */
	public static int getDayOfMonth(long time){
		DateFormat format = new SimpleDateFormat("yyyyMM");
		Date date = new Date(time);
		return Integer.parseInt(format.format(date));
	}
	/**
	 * 获取指定时间的月份 yyyyMM格式  传1为下月  -1为上一月
	 * @return
	 */
	public static int getDayOfMonth(int n){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, n);
        return getDayOfMonth(cal.getTimeInMillis());
	}
	
	/**
	 * 获取本周的第一天
	 * @return
	 */
	public static int getThisWeekFirstDay(long time){
		Calendar claendar = Calendar.getInstance();
		claendar.setFirstDayOfWeek(Calendar.MONDAY);
		claendar.setTimeInMillis(time);
		claendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return getDay(claendar.getTimeInMillis());
	}
	
	/**
	 * 获取指定时间所在周的最后一天 23:59:59 的时间
	 * @return
	 */
	public static long getWeekLastDay(long time){
		Calendar claendar = Calendar.getInstance();
		claendar.setFirstDayOfWeek(Calendar.MONDAY);
		claendar.setTimeInMillis(time);
		claendar.set(Calendar.HOUR_OF_DAY, 23);
		claendar.set(Calendar.MINUTE, 59);
		claendar.set(Calendar.SECOND, 59);
		claendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		return claendar.getTimeInMillis();
	}
	
	/**
	 * 根据指定时间返回 年和周
	 * 
	 * @param time
	 *            返回数组year=int[0] week=year[1]
	 * @return
	 */
	public static int[] getYearAndWeek(long time) {
		if (time <= 0) {
			return new int[] { 0, 0 };
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(time));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(time));

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一星期的第一天
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		if (month == 12 && week == 1) {
			year++;
		}
		int[] yw = { year, week };
		return yw;
	}
	
	/**
	 * 获得当前时间距今天结束剩余多少秒
	 * 2015年11月13日 
	 * @return
	 * @throws ParseException
	 * Long
	 */
	public static int getSecond() throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sDate = df.parse(getDateString(new Date()));
		Date eDate = df.parse(DateHandler.getYear_Month_Day(new Date())+" 23:59:59:999");
		return (int)((eDate.getTime() - sDate.getTime()) / (1000));
	}
	/**
	 * 获取当日23:59:59:999 毫秒数
	 * 2015年11月13日 
	 * @return
	 * @throws ParseException
	 * Long
	 */
	public static Long getLongTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = 0;
		try {
			time = sdf.parse(DateHandler.getYear_Month_Day(new Date())+" 23:59:59:999").getTime();
			return time;
		} catch (ParseException e) {
			logs.error("method(getLongTime) error \r\n"+ StringHandler.getExceptionStack(e));
			return 0L;
		}
		
	}
	
	/**
	 * 从传入时间戳获取年
	 * 2015年11月16日 
	 * @author 徐舒飞
	 * @return
	 */
	public static int getYear(Date date) {
	    Calendar ca = Calendar.getInstance();
	    ca.setTime(date);
	    int year = ca.get(Calendar.YEAR);
	    return year;
	}
	
	/**
	 * 从传入时间戳获取月
	 * 2015年11月16日 
	 * @author 徐舒飞
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int month = ca.get(Calendar.MONTH) + 1;
		return month;
	}
	
	/**
	 * 从传入时间戳获取日
	 * 2015年11月16日 
	 * @author 徐舒飞
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * 获取当前时间的小时(24)
	 * @return
	 * @author john.(cuixinfu@51play.com)
	 * @date 2016年11月18日 上午11:15:15
	 */
	public static int gettodayHour(){
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 根据传入时间戳，获取当前时间的小时(24)
	 * @param transId  时间戳
	 * @return 当前小时数
	 */
	public static int getHourByTransId(long transId){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(transId));
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 功能描述：返回分
	 *
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		cal = Calendar.getInstance();
		cal.setTime(date);
	    return cal.get(Calendar.MINUTE);
	}
	
	/**
	 * 获取本周日期实体
	 * @param isAddYear true：进行跨年处理(建议使用true)
	 * @return
	 */
	public static DateModel getThisWeek(boolean isAddYear){
		if(isAddYear){
			DateModel now=newInstansD(catchYear(getCalendar()));
			return now;
		}else{
			DateModel now=newInstansD(getCalendar());
			return now;
		}
		
	}
	
	/**
	 * 获取上周日期实体
	 * @param isAddYear true：进行跨年处理(建议使用true)
	 * @return
	 */
	public static DateModel getLastWeek(boolean isAddYear){
		if(isAddYear){
			Calendar c=getCalendar();
			c.add(Calendar.WEEK_OF_YEAR, -1);
			catchYear(c);
			return newInstansD(c);
		}else{
			Calendar c=getCalendar();
			c.add(Calendar.WEEK_OF_YEAR, -1);
			return newInstansD(c);
		}
		
	}
	
	/**
	 * 跨年处理
	 * @param c
	 */
	private static Calendar catchYear(Calendar c){
		int month =c.get(Calendar.MONTH);
		if(month==Calendar.DECEMBER){
			//12月
			if(c.get(Calendar.WEEK_OF_YEAR)==1){
				c.add(Calendar.YEAR,1);
			}
		}
		return c;
	}
	/**
	 * 根据字符串获取毫秒日期
	 * 2016年1月7日 
	 * @param date
	 * @return
	 * @throws ParseException
	 * long
	 */
	public static long getTimeStamp(String date) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date longDate = df.parse(date);
		return longDate.getTime();
		
	}
	/**
	 * 获取当天   00:00:00毫秒日期
	 * 2016年1月7日 
	 * @deprecated 已过时，建议使用{@link DateHandler#getTodayStartTime()}
	 * @author jingwei
	 * @return
	 * @throws ParseException
	 * long
	 */
	public static long getTodayTimeStamp() throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date longDate = df.parse(getYear_Month_Day(new Date())+"  00:00:00");
		return longDate.getTime();
	}
	
	public static String getChDateString(String date){
		Date d = parseDatetime(date);
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		StringBuffer s = new StringBuffer();
		s.append(cal.get(Calendar.YEAR)).append("年").append(cal.get(Calendar.MONTH)+1).append("月").append(cal.get(Calendar.DAY_OF_MONTH)).append("日");
		return s.toString();
	}
	
	public static Date getDateByTimeStamp(long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.getTime();
	}

    public static String getStrDateByTimeStamp(long timeStamp){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strData = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        return strData;
    }
	
	/**
	 * 获取离这个月的某月，第一天和最后一天
	 * @param pattern 要返回的日期的格式，默认是yyyy-MM-dd
	 * @param interval 离这个月的前几个月，如-1上个月，1下个月
	 * @see SimpleDateFormat
	 * @return 某月，第一天和最后一天
	 * <p>
	 * 例子：例如这个月是2015年7月，getMonthFisrtLastDay(null,-1),返回['2015-06-01','2015-06-30']
	 * </p>
	 */
	public static String[] getMonthFisrtLastDay(String pattern, int interval) {
		if (StringUtils.isBlank(pattern))
			pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		//下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推
		// cal.set(Calendar.MONTH, 1-1);
		//调到上个月
		cal.add(Calendar.MONTH, interval);
		//得到一个月最最后一天日期(31/30/29/28)
		int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//按你的要求设置上月最后时间
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23, 59, 59);
		//按格式输出
		String lastMonthDay = sdf.format(cal.getTime());
		int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), minDay, 0, 0, 0);
		String firstMonthDay = sdf.format(cal.getTime());
		String[] arr = new String[2];
		arr[0] = firstMonthDay;
		arr[1] = lastMonthDay;
		return arr;
	}
	
	/**获取指定天数后的日期（格式：yyyy-MM-dd 00:00:00.000）
	 * @param days
	 * @return
	 */
	public static Date getDate(int days){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 为今天增加n天，n为负数，则得到今天之前的天
	 * @return yyyy-MM-dd格式的日期
	 */
    public static String addNdayforToday(int n){
    	long addDate = addNDaysFromTodayInitialPoint(n);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date(addDate);
    	return sf.format(date);
	}
    
    /**
	 * 返回从今天开始的加了n小时以后的日期的yyyy-MM-dd HH:mm:ss:SSS，
	 * @param n
	 * @return
	 */
	public static String addNHoursFromToday(int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, n);
		SimpleDateFormat sf = new SimpleDateFormat(FORMAT_GENERAL);
    	Date date = new Date(cal.getTimeInMillis());
    	return sf.format(date);
	}
	
    /**
	 * 返回给指定日期加了n小时以后的日期的yyyy-MM-dd HH:mm:ss:SSS，
	 * @param n
	 * @return
	 */
	public static String addNHoursFromDate(Date startDate,int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.HOUR_OF_DAY, n);
		SimpleDateFormat sf = new SimpleDateFormat(FORMAT_GENERAL);
    	Date date = new Date(cal.getTimeInMillis());
    	return sf.format(date);
	}
		
	/**
	 * 方法: getAllTheDayOfMonth <br>
	 * 描述: 获取指定日期月份的所有日期 <br>
	 * 作者: cuixinfu@51play.com<br>
	 * 时间: 2016年10月18日 下午7:01:20
	 * @param date	
	 * @return	[20161001, 20161002, 20161003...]
	 */
	public static List<Integer> getAllTheDayOfMonth(Date date) {
		List<Integer> list = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(getDay(cal.getTime().getTime()));
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	/**
	 * 方法: getDaysOfMonth <br>
	 * 描述: 获取指定日期月份的天数 <br>
	 * 作者: cuixinfu@51play.com<br>
	 * 时间: 2016年10月18日 下午7:01:20
	 * @param date	
	 * @return	天数
	 */
	public static int getDaysOfMonth(Date date)  
	{  
	    Calendar a = Calendar.getInstance();
	    a.setTime(date);
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    return a.get(Calendar.DATE);  
	}
	
	
	/**
	 * 获取当天是一周的第几天    
	 * @param date
	 * @return 返回1是周一  7是周日
	 */
	public static int getDayOfWeek(Date date) {
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.add(Calendar.DATE, -1);
		return a.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 获取添加N天的日期
	 * @param day
	 * @return
	 */
	public static long getDateAddNDays(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 月份加减返回月份
	 * @param date  201612
	 * @param n  1
	 * @return  201701
	 */
	public static int addMonth(int date,int n){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, date/100);
		//月份 0表示1月  11表示12月
		a.set(Calendar.MONTH, date%100-1);
		System.out.println(a.getTime());
		a.add(Calendar.MONTH, n);
		return getDayOfMonth(a.getTime().getTime());
	}
	
	/**
	 * 2个月份之间差几个月
	 * @param date1  201701
	 * @param date2  201612
	 * @return  1
	 */
	public static int monthInterval(int date1,int date2){
		int year1 =  date1/100;
		int year2 =  date2/100;
		int month1 = date1%100;
		int month2 = date2%100;
		return (year1 - year2 )*12+(month1 - month2);
	}
	
	/**
	 * 转换任意时间格式
	 * @param dateTime    将要准换的时间，如："2017-01-01 10:10:21"</br>
	 * @param originTime  原始的时间格式：如："yyyy-MM-dd HH:mm:ss"</br>
	 * @param targetTime  目标的时间格式：如："yyyy年MM月dd日  HH:mm:ss"</br>
	 * @return  "2017年01月01日  10:10:21"
	 */
	public static String formatTime(String dateTime,String originTime,String targetTime){
		SimpleDateFormat stf = new SimpleDateFormat(originTime);
		SimpleDateFormat stf2 = new SimpleDateFormat(targetTime);
		Date parseDate=null;
		try {
			parseDate = stf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stf2.format(parseDate);
	}
	/**
	 * 那天0点的时间戳
	 * @param day  20170522
	 * @return
	 */
	public static long getTime(int day){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date longDate = null;
		try {
			longDate = df.parse(""+day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return longDate.getTime();
	}
	
	/**
	 * 时间相减得到天数
	 * @param beginDate   20170522
	 * @param endDate   20170531
	 * @return
	 */
	public static long getDaySub(int beginDate,int endDate) {
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");    
        Date begin;
        Date end;
        try
        {
        	begin = format.parse(beginDate+"");
        	end= format.parse(endDate+"");    
            day=(end.getTime()-begin.getTime())/(24*60*60*1000);    
        } catch (ParseException e)
        {
            e.printStackTrace();
        }   
        return day;
    }
	
	/**
	 * 获取before前的日期（不包含今天）
	 * 
	 * @param time
	 * @param before
	 * @return yyyyMMdd
	 */
	public static int getDayBeforeDate(long time, int before) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - before);
		return Integer.parseInt(sf.format(c.getTime()));
	}
	
	/**
	 * 将long型date转换为HHmmss(时分秒)
	 */
	public static long getFormatDate(long time){
		SimpleDateFormat sf = new SimpleDateFormat("HHmmss");
		Date date = new Date(time);
		return Long.parseLong(sf.format(date));
	}
	
	/**
	 * 将字符串转换date
	 * @param time
	 * @param format
	 * @return
	 */
	public static Date getFormatDate(String time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// 解析时间 2016-01-05T15:09:54Z
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			logs.info("时间转换异常", e);
		}
		return date;
	}
	
	   /**
     * 函数功能描述:UTC时间转本地时间格式
     * @param utcTime UTC时间
     * @param utcTimePatten UTC时间格式
     * @param localTimePatten   本地时间格式
     * @return 本地时间格式的时间
     * eg:utc2Local("2017-06-14 09:37:50.788+08:00", "yyyy-MM-dd HH:mm:ss.SSSXXX", "yyyy-MM-dd HH:mm:ss.SSS")
     */
    public static String utc2Local(String utcTime, String utcTimePatten, String localTimePatten) {
        SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));//时区定义并进行时间获取
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return utcTime;
        }
        SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }
    //1、获取当月第一天
  	public static String getForDate(){
  		//规定返回日期格式
  		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
  		Calendar calendar=Calendar.getInstance();
  		Date theDate=calendar.getTime();
  		GregorianCalendar gcLast=(GregorianCalendar)Calendar.getInstance();
  		gcLast.setTime(theDate);
  		//设置为第一天
  		gcLast.set(Calendar.DAY_OF_MONTH, 1);
  		String day_first=sf.format(gcLast.getTime());
  		//打印本月第一天
  		return day_first;
  	}
  	//2、获取当月最后一天
  	@SuppressWarnings("static-access")
	public static String getForDatelast(){
  		//获取Calendar
  		Calendar calendar=Calendar.getInstance();
  		//设置日期为本月最大日期
  		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
  		//设置日期格式
  		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
  		String ss=sf.format(calendar.getTime());
  		return ss;
  	}
    //获取本年的开始时间
  	public static Date getBeginDayOfYear() {
  		Calendar cal = Calendar.getInstance();
  		cal.set(Calendar.YEAR, getNowYear());
  		// cal.set
  		cal.set(Calendar.MONTH, Calendar.JANUARY);
  		cal.set(Calendar.DATE, 1);
  		return getDayStartTime(cal.getTime());
  	}
  	//获取本年的结束时间
  	public static Date getEndDayOfYear() {
  		Calendar cal = Calendar.getInstance();
  		cal.set(Calendar.YEAR, getNowYear());
  		cal.set(Calendar.MONTH, Calendar.DECEMBER);
  		cal.set(Calendar.DATE, 31);
  		return getDayEndTime(cal.getTime());
  	}
  	//获取今年是哪一年
    public static Integer getNowYear() {
  		Date date = new Date();
  		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
  		gc.setTime(date);
  		return Integer.valueOf(gc.get(1));
  	}
   //获取某个日期的开始时间
   public static Timestamp getDayStartTime(Date d) {
	   Calendar calendar = Calendar.getInstance();
	   if(null != d) calendar.setTime(d);
	   calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
       calendar.set(Calendar.MILLISECOND, 0);
       return new Timestamp(calendar.getTimeInMillis());
   }
   //获取某个日期的结束时间
   public static Timestamp getDayEndTime(Date d) {
      Calendar calendar = Calendar.getInstance();
      if(null != d) calendar.setTime(d);
      calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
      calendar.set(Calendar.MILLISECOND, 999);
      return new Timestamp(calendar.getTimeInMillis());
   }
   /**
     * 此方法描述的是：   格式化日期
     * @param time
     * @return String
    */
    public static String formatStringTime(String time){
		long msgCreateTime = Long.parseLong(time) * 1000L;  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return format.format(new Date(msgCreateTime));
	}

	public static void main(String[] args) {
		//System.out.println(utc2Local("2017-09-12T09:10:16Z", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd HH:mm:ss"));
		//System.out.println(getLongDate());
		long interval = compareTimeInterval("2018-08-09 22:09:30", dateToStrLong(new Date()));
		System.out.println("interval ： " + interval);
		/*Map<String, Object> mapTime = compareTimeDiff("2018-08-08 22:09:30", dateToStrLong(new Date()));
		int day = Integer.valueOf(mapTime.get("day").toString());
		int hour = Integer.valueOf(mapTime.get("hour").toString());
		int min = Integer.valueOf(mapTime.get("min").toString());
		int s = Integer.valueOf(mapTime.get("s").toString());
		System.out.println("相差："+day+"天"+hour+"小时"+min+"分"+s+"秒");  */
		String addNHoursFromToday = addNHoursFromToday(24);
		System.out.println("addNHoursFromToday : = " + addNHoursFromToday) ;
		long timeStamp = 1061827200000L;
		getDays(timeStamp);
	}

	public static void getDays(long timeStamp){
		String dateByTimeStamp = getStrDateByTimeStamp(timeStamp);
		Map<String, Object> objectMap = compareTimeYear(dateByTimeStamp, dateToStrLong(new Date()));
		objectMap.forEach((key, value) -> {
            System.out.println(key + "：" + value);
        });
	}

	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 * @param date  日期
	 * @return  返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 * @param date  日期
	 * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
	 * @return  返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date, String pattern) {
		if(date != null){
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 字符串转换成日期
	 * @param strDate 日期字符串
	 * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (StringUtils.isBlank(strDate)){
			return null;
		}

		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return fmt.parseLocalDateTime(strDate).toDate();
	}

	/**
	 * 根据周数，获取开始日期、结束日期
	 * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
	 * @return  返回date[0]开始日期、date[1]结束日期
	 */
	public static Date[] getWeekStartAndEnd(int week) {
		DateTime dateTime = new DateTime();
		LocalDate date = new LocalDate(dateTime.plusWeeks(week));

		date = date.dayOfWeek().withMinimumValue();
		Date beginDate = date.toDate();
		Date endDate = date.plusDays(6).toDate();
		return new Date[]{beginDate, endDate};
	}

	/**
	 * 对日期的【秒】进行加/减
	 *
	 * @param date 日期
	 * @param seconds 秒数，负数为减
	 * @return 加/减几秒后的日期
	 */
	public static Date addDateSeconds(Date date, int seconds) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusSeconds(seconds).toDate();
	}

	/**
	 * 对日期的【分钟】进行加/减
	 *
	 * @param date 日期
	 * @param minutes 分钟数，负数为减
	 * @return 加/减几分钟后的日期
	 */
	public static Date addDateMinutes(Date date, int minutes) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMinutes(minutes).toDate();
	}

	/**
	 * 对日期的【小时】进行加/减
	 *
	 * @param date 日期
	 * @param hours 小时数，负数为减
	 * @return 加/减几小时后的日期
	 */
	public static Date addDateHours(Date date, int hours) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusHours(hours).toDate();
	}

	/**
	 * 对日期的【天】进行加/减
	 *
	 * @param date 日期
	 * @param days 天数，负数为减
	 * @return 加/减几天后的日期
	 */
	public static Date addDateDays(Date date, int days) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusDays(days).toDate();
	}

	/**
	 * 对日期的【周】进行加/减
	 *
	 * @param date 日期
	 * @param weeks 周数，负数为减
	 * @return 加/减几周后的日期
	 */
	public static Date addDateWeeks(Date date, int weeks) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusWeeks(weeks).toDate();
	}

	/**
	 * 对日期的【月】进行加/减
	 *
	 * @param date 日期
	 * @param months 月数，负数为减
	 * @return 加/减几月后的日期
	 */
	public static Date addDateMonths(Date date, int months) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusMonths(months).toDate();
	}

	/**
	 * 对日期的【年】进行加/减
	 *
	 * @param date 日期
	 * @param years 年数，负数为减
	 * @return 加/减几年后的日期
	 */
	public static Date addDateYears(Date date, int years) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusYears(years).toDate();
	}

}
