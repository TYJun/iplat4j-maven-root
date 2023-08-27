package com.baosight.wilp.hr.pb.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (这里用一句话描述这个类的作用)
 *
 * @Title:
 * @ClassName:
 * @Package：
 * @author: xiajunqing
 * @date:
 * @version: V1.0
 * @Copyright: www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class WeekDateUtils {


    /***
     * getWeekMap获取有序的周次日期列表
     * @param
     * */
    public static LinkedHashMap getWeekMap(String dateStr) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateStr);
        int maxIndex = getActualMaximum(date);
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
        LinkedHashMap<String,List<String>> lMap = new LinkedHashMap<String,List<String>>();
        for (int i = 0; i < maxIndex; i++) {
            Date weekFirst = getWeekFirstDay(i, date);
            Date weekLast = getWeekLastDay(i, date);
            String weekFristDay = formateDate(weekFirst);
            String weekLastDay = formateDate(weekLast);

            if(!dateFormat.substring(5,7).equals(weekFristDay.subSequence(5, 7))){
                weekFristDay = weekLastDay.substring(0,8) + "01";
            }
            if(!dateFormat.substring(5,7).equals(weekLastDay.subSequence(5, 7))){
                weekLastDay = weekFristDay.substring(0,8) + getMaxDayByYearMonth(weekFristDay);
            }
            //获取第几周的所有日期
            List<String> datelist = getBetweenDates(weekFirst, weekLast);
            lMap.put(i + "",datelist);
        }
        return lMap;
    }

    /**
     * getMonthDays获取某个月的所有日期及周次
     * 表格导出用，excel默认日期格式yyyy/MM/dd
     * @param
     * **/
    public static List<List<String>> getMonthDays(String dateStr) throws Exception{
        List<List<String>> dayslist = new ArrayList<List<String>>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = simpleDateFormat.parse(dateStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,1);
        int maxIndex = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        String[] dayStringOfWeek = {"周日","周一","周二","周三","周四","周五","周六"};
        for (int i = 0; i < maxIndex; i++) {
            int d = c.get(Calendar.DAY_OF_WEEK);
            List<String> list = new ArrayList<String>();
            
            list.add(simpleDateFormat.format(c.getTime()) +"("+dayStringOfWeek[d-1]+")");
            dayslist.add(list);
            c.add(Calendar.DAY_OF_MONTH,1);
        }
        return dayslist;
    }
    
    public static String getWeek(String dateStr,String formatStr) throws Exception{
        if(formatStr == null || formatStr.length() < 1 || " ".equals(formatStr)) {
            formatStr = "yyyy-MM-dd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        Date date = simpleDateFormat.parse(dateStr);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int d = c.get(Calendar.DAY_OF_WEEK);
        String[] dayStringOfWeek = {"周日","周一","周二","周三","周四","周五","周六"};
        return dayStringOfWeek[d-1];
    }
    
    public static void main(String[] args) throws Exception {
        String date1 = "2022/02/18";
        //List<List<String>> datelist =getMonthDays(date1);;
        /*List<List<String>> monthDays = WeekDateUtils.getMonthDays(date1);
        List<String> list = new ArrayList<String>();
        list.add("科室名称");
        monthDays.add(0, list);
        list = new ArrayList<String>();
        list.add("科室编码");
        monthDays.add(0, list);
        list = new ArrayList<String>();
        list.add("工号");
        monthDays.add(0, list);
        list = new ArrayList<String>();
        list.add("姓名");
        monthDays.add(0, list);
        System.out.println(monthDays);*/
        System.out.println(getWeek(date1,"yyyy/MM/dd"));
    }

    
    public static int getActualMaximum(Date date){
        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, year);
//        c.set(Calendar.MONTH, (month - 1));
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * @desc 获取传入日期所在月份的第几周的第一天
     * @param week
     * @param date
     * @return
     */
    public static Date getWeekFirstDay(Integer week, Date date){
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c.set(Calendar.YEAR, c1.get(Calendar.YEAR));
        c.set(Calendar.MONTH, c1.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, 1); // 设为每个月的第一天(1号)
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //每个月的第一天为星期几
        //星期日:1,星期一:2,星期二:3,星期三:4,星期四:5,星期五:6,星期六:7
        //转化为:星期一:1,星期二:2,星期三:3,星期四:4,星期五:5,星期六:6,星期日:7
        if (dayOfWeek != Calendar.SUNDAY) {
            dayOfWeek = dayOfWeek - 1;
        } else {
            dayOfWeek = 7;
        }
        c.add(Calendar.DAY_OF_MONTH, 1 - dayOfWeek); // 使其为每个月第一天所在周的星期一
        c.add(Calendar.DAY_OF_MONTH, week * 7);
        return c.getTime();
    }

    /**
     * @desc 获取传入日期所在月份的第几周的最后一天
     * @param week
     * @param date
     * @return
     */
    public static Date getWeekLastDay(Integer week,Date date){
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c.set(Calendar.YEAR, c1.get(Calendar.YEAR));
        c.set(Calendar.MONTH, c1.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, 1); // 设为每个月的第一天(1号)
        //星期日:1,星期一:2,星期二:3,星期三:4,星期四:5,星期五:6,星期六:7
        //转化为:星期一:1,星期二:2,星期三:3,星期四:4,星期五:5,星期六:6,星期日:7
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != Calendar.SUNDAY) {
            dayOfWeek = dayOfWeek - 1;
        } else {
            dayOfWeek = 7;
        }
        c.add(Calendar.DAY_OF_MONTH, 1 - dayOfWeek + 6); // 使其为每个月第一天所在周的星期日
        c.add(Calendar.DAY_OF_MONTH, week * 7);
        return c.getTime();
    }

    /**
     * @desc 格式化日期（yyyy-MM-dd）
     * @param date
     * @return
     */
    private static String formateDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获得某个月最大天数
     * @param date year 年份month 月份 (1-12)
     * @return 某个月最大天数
     */
    public static int getMaxDayByYearMonth(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(5,7));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * @desc 获取两个日期之间的所有日期
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getBetweenDates(Date begin, Date end) {
        List<String> result = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
        while(begin.getTime()<=end.getTime()){
            result.add(formateDate(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

    public static void getmaxWeek(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2022);
        c.set(Calendar.MONTH, (3 - 1));
        c.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println( c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月");
        System.out.println("天数：" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("Actual周数：" + c.getActualMaximum(Calendar.WEEK_OF_MONTH));
        System.out.println("Max周数：" + c.getMaximum(Calendar.WEEK_OF_MONTH));
    }
}
