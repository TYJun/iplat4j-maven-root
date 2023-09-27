package com.baosight.wilp.ac.gm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 物资档案配置页面
 */
public class ServiceACGMPZ01 extends ServiceBase {
    @Value("${projectSchema}")
    private String projectSchema;
    @Value("${platSchema}")
    private String platSchema;
    /**
     * 页面初始化方法
     * 作者：TYJ
     * 入参：EiInfo
     * 出参：EiInfo
     * 处理：调用query()方法
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 查询功能
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();

        HashMap<String, Object> map = new HashMap<>();
        map.put("projectSchema",projectSchema);
        map.put("platSchema",platSchema);
        map.put("GroupCname",inInfo.get("GroupCname"));
        map.put("GroupEname",inInfo.get("GroupEname"));
        map.put("matClassCode",inInfo.get("matClassCode"));
        List<Map<String,Object>> query = dao.query("ACGMPZ01.query", map);

        outInfo.setRows("result",query);

        return outInfo;
    }

    /**
     * 删除功能
     */
    public EiInfo delete(EiInfo inInfo) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("id",inInfo.getBlock("result").getRow(0).get("id"));
        map.put("projectSchema",projectSchema);
        dao.delete("ACGMPZ01.delete", map);

        inInfo.setMsg("删除成功");

        return inInfo;
    }


}
