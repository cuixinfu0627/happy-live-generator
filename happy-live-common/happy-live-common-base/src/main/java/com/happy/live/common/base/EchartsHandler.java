package com.happy.live.common.base;


import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 类名:EchartsHandler <tb>
 * 描述: 图表数据处理类  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2018/9/20 19:16 <tb>
 */
public class EchartsHandler {
    static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public static Map<String, Object> completionDate(List<Map<String, Object>> lineChartData, Integer dateType) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new LinkedHashMap<>();
        String[] lineDate = null;
        Integer[] lineCount = null;
        if (lineChartData.size() > 0) {
            for (Map<String, Object> m : lineChartData) {
                map.put(m.get("NAME") + "", m.get("COUNT"));
            }
            switch (dateType) {
                //当日
                case 1: {
                    lineDate = new String[24];
                    lineCount = new Integer[24];
                    for (int i = 0; i < lineDate.length; i++) {
                        for (String n : map.keySet()) {
                            if (Integer.valueOf(n).equals(i)) {
                                lineDate[i] = n;
                                lineCount[i] = Integer.valueOf(map.get(n).toString());
                                break;
                            } else {
                                lineDate[i] = Integer.valueOf(i).toString();
                                lineCount[i] = 0;
                            }
                        }
                    }
                    if (lineChartData.size() == 0) {
                        for (int i = 0; i < lineDate.length; i++) {
                        	String tempStr = Integer.valueOf(i).toString();
                            lineDate[i] = tempStr;
                            lineCount[i] = 0;
                        }
                    }
                    break;
                }
                //当月
                case 2: {
                    int daysOfMonth = DateHandler.getDaysOfMonth(new Date());
                    lineDate = new String[daysOfMonth];
                    lineCount = new Integer[daysOfMonth];
                    for (int i = 0; i < lineDate.length; i++) {
                        for (String y : map.keySet()) {
                            //获取当月第一天
                            String day = DateHandler.getForDate();
                            LocalDate with = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i);
                            //日期格式转换
                            LocalDate localDate = LocalDate.parse(y, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (Period.between(with, localDate).getDays() == 0) {
                                lineDate[i] = y.substring(5);
                                lineCount[i] = Integer.valueOf(map.get(y).toString());
                                break;
                            } else {
                                lineDate[i] = with.toString().substring(5);
                                lineCount[i] = 0;
                            }
                        }
                    }
                    if (lineChartData.size() == 0) {
                        for (int i = 0; i < lineDate.length; i++) {
                            lineDate[i] = Integer.valueOf(i).toString();
                            lineCount[i] = 0;
                        }
                    }
                    break;
                }
                //当年
                case 3: {
                    SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM");
                    lineDate = new String[12];
                    lineCount = new Integer[12];
                    for (int i = 0; i < lineDate.length; i++)
                        for (String n : map.keySet()) {
                            //获取第一个月
                            //添加月份
                            Date date = DateUtils.addMonths(DateHandler.getBeginDayOfYear(), i);
                            DateUtils.addMonths(date, i);
                            String month = sfm.format(date);
                            if (month.equals(n)) {
                                lineDate[i] = n;
                                lineCount[i] = Integer.valueOf(map.get(n).toString());
                                break;
                            } else {
                                lineDate[i] = month;
                                lineCount[i] = 0;
                            }
                        }
                    if (lineChartData.size() == 0) {
                        for (int i = 0; i < lineDate.length; i++) {
                            lineDate[i] = Integer.valueOf(i).toString();
                            lineCount[i] = 0;
                        }
                    }
                    break;
                }
                case 4:{
                    //前七天
                    lineDate = new String[8];
                    lineCount = new Integer[8];
                    for (int i = 0; i < lineDate.length; i++) {
                        for (String y : map.keySet()) {
                            //获取当月第一天
                            String endTime = DateHandler.getNowStrDate();
                            String day = DateHandler.getNextDay(endTime,"-7");
                            LocalDate with = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i);
                            //日期格式转换
                            LocalDate localDate = LocalDate.parse(y, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            if (Period.between(with, localDate).getDays() == 0) {
                                lineDate[i] = y.substring(5);
                                lineCount[i] = Integer.valueOf(map.get(y).toString());
                                break;
                            } else {
                                lineDate[i] = with.toString().substring(5);
                                lineCount[i] = 0;
                            }
                        }
                    }
                    if (lineChartData.size() == 0) {
                        for (int i = 0; i < lineDate.length; i++) {
                            lineDate[i] = Integer.valueOf(i).toString();
                            lineCount[i] = 0;
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }else switch (dateType) {
            case 1: {
                String lineDate2[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
                Integer lineCount2[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                lineDate = lineDate2;
                lineCount = lineCount2;
            }
            break;
            case 2: {
                int daysOfMonth = DateHandler.getDaysOfMonth(new Date());
                lineDate = new String[daysOfMonth];
                lineCount = new Integer[daysOfMonth];
                for (int i = 0; i < daysOfMonth; i++) {
                    String day = DateHandler.getForDate();
                    LocalDate with = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i);
                    lineDate[i] = with.toString().substring(5);
                    lineCount[i] = 0;
                }
            }
            break;
            case 3: {
                SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM");
                lineDate = new String[12];
                lineCount = new Integer[12];
                for (int i = 0; i <= 11; i++){
                	Date date = DateUtils.addMonths(DateHandler.getBeginDayOfYear(), i);
                    DateUtils.addMonths(date, i);
                    String month = sfm.format(date);
                    lineDate[i] = month;
                    lineCount[i] = 0;
                }
                break;
            }
            case 4:{
                //前七天
                lineDate = new String[8];
                lineCount = new Integer[8];
                for (int i = 0; i < lineDate.length; i++) {
                    String endTime = DateHandler.getNowStrDate();
                    String day = DateHandler.getNextDay(endTime,"-7");
                    LocalDate with = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i);
                    lineDate[i] = with.toString().substring(5);
                    lineCount[i] = 0;
                }
                break;
            }
            default:
                break;
        }
        result.put("lineDate", lineDate);
        result.put("lineCount", lineCount);
        return result;
    }

    /**
     * 返回两个数组存入map
     * @param lineChartData
     * @param startTime
     * @param endTime
     */
    public static Map<String, Object> completionDate(List<Map<String, Object>> lineChartData,String startTime,String endTime){
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new LinkedHashMap<>();
        int monthDiff = 0 ;
        try {
            monthDiff = differentDays(fmt.parse(startTime), fmt.parse(endTime));
        }catch (Exception e){
            e.printStackTrace();
        }
        String[]  lineDate = new String[monthDiff+1];
        Integer[] lineCount = new Integer[monthDiff+1];
        if(lineChartData != null && !lineChartData.isEmpty()){
            for (Map<String, Object> m : lineChartData) {
                map.put(m.get("NAME") + "", m.get("COUNT"));
            }
            //比较时间大小：
            for (int i = 0,length = lineDate.length; i < length; i++) {
                for (String y : map.keySet()) {
                    String day = DateHandler.getNextDay(startTime,i+"");
                    LocalDate with = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i);
                    //日期格式转换
                    LocalDate localDate = LocalDate.parse(y, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if (Period.between(with, localDate).getDays() == 0) {
                        lineDate[i] = y.substring(5);
                        lineCount[i] = Integer.valueOf(map.get(y).toString());
                    } else {
                        lineDate[i] = with.toString().substring(5);
                        lineCount[i] = 0;
                    }
                }
            }
        }else{
            for (int i = 0,length = lineDate.length; i < length; i++){
                String day = DateHandler.getNextDay(startTime,"+"+(i));
                LocalDate with = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i);
                lineDate[i] = with.toString().substring(5);
                lineCount[i] = 0;
            }
        }
        result.put("lineChartsDate", lineDate);
        result.put("lineChartsCount", lineCount);
        return result;
    }

    /**返回，map key时间，value为值
     * @param lineChartData
     * @param startTime
     * @param endTime
     */
    public static Map<String, Object> getMapDate(List<Map<String, Object>> lineChartData,String startTime,String endTime,String key,String value){
        Map<String, Object> result = new HashMap<>();
        int monthDiff = 0 ;
        try {
            monthDiff = differentDays(fmt.parse(startTime), fmt.parse(endTime));
        }catch (Exception e){
            e.printStackTrace();
        }
        if(lineChartData != null && !lineChartData.isEmpty()){
            for (Map<String, Object> m : lineChartData) {
                result.put(m.get(key) + "", m.get(value));
            }
        }else{
            for (int i = 0,length = monthDiff; i <= length; i++){
                //String day = DateHandler.getNextDay(startTime,"+"+(i));
                LocalDate with = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(i);
                result.put(with.toString(),0);
            }
        }
        return result;
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     */
    public static int differentDays(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2){   //同一年
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++){
                if(i%4==0 && i%100!=0 || i%400==0){    //闰年

                    timeDistance += 366;
                } else {    //不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        }else { //不同年
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    public static void main(String[] args) throws ParseException {
    	String startDate = "2018-09-02";
    	String endDate = "2018-09-05";
        int monthDiff = differentDays(fmt.parse(startDate), fmt.parse(endDate));
        System.out.println(monthDiff);
    }
}
