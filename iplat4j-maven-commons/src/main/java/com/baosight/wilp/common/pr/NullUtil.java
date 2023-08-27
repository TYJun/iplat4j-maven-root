package com.baosight.wilp.common.pr;


/**
 * 
 * 参数判空工具类
 * 
 * @Title: NullUtil.java
 * @ClassName: NullUtil
 * @Package：com.baosight.zdyyaq.common
 * @author: zhangjiaqian
 * @date: 2021年5月11日 下午3:18:43
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class NullUtil {

    
    
    /**
     * 
     * 判断参数是否为空
     *
     * @Title: orNull 
     * @param param
     * @return 
     * @return: int
     */
    public static int orNull(String... param) {
        int i = 0;
        for (String string : param) {
            if(null == string || string.length() == 0 || "[]".equals(string)) {
               i = -1;
            }
        }
        return i;
    }
}
