package com.baosight.wilp.ac.gm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.PrUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 物资档案配置页面
 *  作者：TYJ
 */
public class ServiceACGMPZ0101 extends ServiceBase {

    final String projectSchema = PrUtils.getConfigure();
    final String platSchema = PrUtils.getIplatConfigure();
    /**
     * 页面初始化方法
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
        HashMap<String, Object> map = new HashMap<>();
        map.put("projectSchema",projectSchema);
        map.put("platSchema",platSchema);
        map.put("GroupCname",inInfo.get("GroupCname"));
        map.put("matClassId",inInfo.get("matClassId"));
        dao.query("ACGMPZ01.query",map);
        return inInfo;

    }


    public EiInfo save(EiInfo inInfo) {
        HashMap<String,Object> map =  new HashMap<>();
        //获取人员权限分组信息
        String groupEname = (String) inInfo.getAttr().get("groupEname");
        String groupCname = (String) inInfo.getAttr().get("groupCname");
        //获取物资类别信息
        String matClassCode = (String) inInfo.getAttr().get("matClassCode");
        String matClassName = (String) inInfo.getAttr().get("matClassName");

        //封装
        map.put("projectSchema",projectSchema);
        map.put("platSchema",platSchema);
        map.put("groupEname",groupEname);
        map.put("groupCname",groupCname);
        map.put("matClassName",matClassName);
        map.put("matClassCode",matClassCode);
        map.put("id", UUID.randomUUID().toString().substring(0,32));

        dao.insert("ACGMPZ01.insert",map);

        return inInfo;
    }
    /*
     * 查询物资类别编码
     */
    public EiInfo queryMaterialCategory(EiInfo info){
        EiInfo outInfo = new EiInfo();
        HashMap<String, Object> map = new HashMap<>();
        map.put("projectSchema",projectSchema);
        map.put("platSchema",platSchema);
        List<Map<String,Object>> query = dao.query("ACGMPZ01.queryAllMaterialCategory", map);
        outInfo.setRows("materialCategory",query);
        return outInfo;
    }
    /*
     * 查询人员权限分组
     */
    public EiInfo queryPermissionGroup(EiInfo info){
        EiInfo outInfo = new EiInfo();
        HashMap<String, Object> map = new HashMap<>();
        map.put("projectSchema",projectSchema);
        map.put("platSchema",platSchema);
        List<Map<String,Object>> query = dao.query("ACGMPZ01.queryAllPermissionGroup", map);
        outInfo.setRows("Permission",query);


        return outInfo;
    }


}
