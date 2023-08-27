package com.baosight.wilp.sq.common;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 
 * 提取小代码工具类
 * 
 * @Title: TyepCode.java
 * @ClassName: TyepCode
 * @Package：com.baosight.wilp.sq.common
 * @author: zhangjiaqian
 * @date: 2021年7月28日 下午6:19:24
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class TyepCode {

    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @SuppressWarnings("all")
    public static List<Map<String,String>> dealUseDay(String groupTypeCode) throws Exception{
        EiInfo eiInfo = new EiInfo();
        String serviceId = "S_ED_02";
        eiInfo.set(EiConstant.serviceId,serviceId);
        eiInfo.set("codeset",groupTypeCode);
        //服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        List<Map<String,String>> listValue = (List<Map<String, String>>) outInfo.get("list");
        return listValue;
    }
}
