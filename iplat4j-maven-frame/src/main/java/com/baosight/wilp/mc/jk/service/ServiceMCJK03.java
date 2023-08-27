package com.baosight.wilp.mc.jk.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;



public class ServiceMCJK03  extends ServiceBase {
    public EiInfo queryAccompanyNo(EiInfo inInfo) {
        //获取前端工单号
        String accompanyNo = (String)inInfo.getAttr().get("accompanyNo");
        EiInfo outInfo = new EiInfo();
        //判断是否为空
        if(StringUtils.isBlank(accompanyNo)) {
            //存储一个字符串
            outInfo.setMsg("参数不完整");
            //存储一个整数
            outInfo.setStatus(-1);
            return outInfo;
        }
        // dao.query查询写法，获得的值存放到map集合中
        List<Map<String,String>> list = dao.query("MCJK03.query",accompanyNo);
        HashMap<String, Map<String, String>> paramMap = new HashMap<String, Map<String, String>>();
        paramMap.put("dept",list.get(0));
        List<Map<String,String>> list2 = dao.query("MCJK03.query2",paramMap);
        if(CollectionUtils.isEmpty(list)) {
            outInfo.setMsg("未查到数据");
            outInfo.setStatus(-1);
            return outInfo;
        }
        //存储map集合
        outInfo.setAttr(list2.get(0));
        return outInfo;
    }
}
