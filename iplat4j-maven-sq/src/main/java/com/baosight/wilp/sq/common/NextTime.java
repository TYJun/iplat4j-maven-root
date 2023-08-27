package com.baosight.wilp.sq.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 获取档期那日期后一天的时间
 * 
 * @Title: NextTime.java
 * @ClassName: NextTime
 * @Package：com.baosight.wilp.sq.common
 * @author: ha'ha'ha
 * @date: 2021年9月10日 上午10:40:29
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class NextTime {

    
    public static String getTheNextDate(String date){
        //201711191600
        if (date!=null&&date.length()>=8) {
            date=date.substring(0, 8);
        }else {
            return "";
        }
        
        String year=date.substring(0,4);
        String month=date.substring(4,6);
        String day1=date.substring(6,8);
        //System.out.println(year+"   "+month+"   "+day1);
        String dateStr=year+"-"+month+"-"+day1;
        Date myDate=null;
        Calendar calendar=Calendar.getInstance();
        try {
            myDate=new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(myDate);
        int day=calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day+1);
        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        //System.out.println(dayAfter);
//        String [] dateArray=dayAfter.split("-");
//        String dayAfter1="";
//        for (int i = 0; i < dateArray.length; i++) {
//            dayAfter1+=dateArray[i];
//        }
        return dayAfter;
    }
}
