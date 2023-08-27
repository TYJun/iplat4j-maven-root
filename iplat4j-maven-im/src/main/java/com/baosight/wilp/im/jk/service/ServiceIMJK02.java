package com.baosight.wilp.im.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.jk.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceIMJK02 extends ServiceBase {
    /**
     * 查询巡检记录表
     * @Title  queryUserRole
     * @author keke
     * @date 2022-10-2 13:57
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     */
    public EiInfo queryUserRole(EiInfo inInfo) {
        HashMap<String, Object> attr = (HashMap<String, Object>) inInfo.getAttr().get("paramObject");

        String createTime = StringUtil.toString(attr.get("createTime"));
        String spotId = StringUtil.toString(attr.get("spotId"));

        Map map = new HashMap<>();
        map.put("createTime",createTime);
        map.put("spotId",spotId);

        List<HashMap<String,String>> list = dao.query("IMJK01.queryInfo1",map);
        inInfo.set("obj", list);
        return inInfo;
    }
}
