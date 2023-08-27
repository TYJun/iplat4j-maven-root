package com.baosight.wilp.common.hc;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 危化品模块时间相关工具类
 * 
 * @Title: TimeUtil.java
 * @ClassName: TimeUtil
 * @Package：com.baosight.wilp.common.hc
 * @author: zhangjiaqian
 * @date: 2021年7月13日 下午5:32:04
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class TimeUtil {
    
    
    
    /**
     * 比较日期大小
     *
     * @Title: time 
     * @param beginDate
     * @param endDate
     * @return 
     * @return: Boolean
     */
    public static Boolean time(String beginDate,String endDate) {
        SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2  =new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        Date parse2 = null;
        try {
            parse = sdf.parse(beginDate);
            parse2 = sdf2.parse(endDate);
        } catch (Exception e) {
        }
        boolean flag = parse.getTime() > parse2.getTime();
        return flag;
    }
}
