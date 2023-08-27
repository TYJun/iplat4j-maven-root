package com.baosight.wilp.vi.utils;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public  class ViUtils {
    // 默认不开启
    public static Boolean getDataCode(String codeset) {
        Boolean flag = false;
        List<Map<String,String>> list = new ArrayList<>();
        EiInfo eiInfo = new EiInfo();
        eiInfo.set(EiConstant.serviceId, "S_ED_02");
        eiInfo.set("codeset",codeset);
        //服务接口调用
        EiInfo outInfo = XServiceManager.call(eiInfo);
        Object object = outInfo.get("list");
        if(object !=null){
            list = (List<Map<String, String>>) object;
        }
        if (CollectionUtils.isNotEmpty(list)){
            String value = list.get(0).get("value");
            flag = Boolean.parseBoolean(value);
        }
        return flag;
    }
}
