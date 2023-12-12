package com.baosight.wilp.df.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.df.jk.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDFJK02 extends ServiceBase {
    /**
     * 查询巡检记录表
     * @Title  queryDf
     * @author keke
     * @date 2022-10-2 13:57
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo queryDf(EiInfo inInfo) {
        HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

        String id = StringUtil.toString(attr.get("id"));

        Map map = new HashMap<>();
        map.put("id",id);

        List<HashMap<String,String>> list = dao.query("DFJK01.queryDf",map);
        inInfo.set("obj", list);
        return inInfo;
    }

    /**
     * 查询特种设备下所有图片
     * @Title  queryDfPic
     * @author keke
     * @date 2022-10-2 13:57
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo queryDfPic(EiInfo inInfo) {
        HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

        String id = StringUtil.toString(attr.get("id"));

        Map map = new HashMap<>();
        map.put("id",id);

        List<HashMap<String,String>> list = dao.query("DFJK01.queryDfPic",map);
        inInfo.set("obj", list);
        return inInfo;
    }
}
