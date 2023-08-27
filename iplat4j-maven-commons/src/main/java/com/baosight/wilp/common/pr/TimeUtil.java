package com.baosight.wilp.common.pr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 时间转换工具类
 * 
 * @Title: TimeUtil.java
 * @ClassName: TimeUtil
 * @Package：com.baosight.zdyyaq.common
 * @author: ha'ha'ha
 * @date: 2021年5月2日 下午6:07:40
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class TimeUtil {

    
    
    public static String timeYMD(String param) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date parse = null;
        try {
            parse = df.parse(param);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
             e.printStackTrace();
        }
        String format = df.format(parse);
        return format;
    }
    
    
    
    /**
     * 
     * 查询历史时间方法
     *
     * @Title: historyTime 
     * @param param 
     * @return: void
     */
    public static Map historyTime(String param) {
        //获取当前时间
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endDate = df.format(ca.getTime());    
        Map map = new HashMap();
        if(null != param) {
            Integer valueOf = Integer.valueOf(param);
            int par = valueOf.intValue();
            if(30 == par) {
                ca.add(Calendar.MONTH, -1);
                String beginDate = df.format(ca.getTime());
                map.put("beginDate", beginDate);
                map.put("endDate", endDate);
                return map;
            }
            ca.add(Calendar.DATE, -par);
            String beginDate = df.format(ca.getTime());
            map.put("beginDate", beginDate);
            map.put("endDate", endDate);
        }
        return map;
    }
     
    
    
    
    
    
    
    
}
