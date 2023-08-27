package com.baosight.wilp.ir.sy.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ir.sy.util.MaintainUtil;
import com.baosight.wilp.ir.sy.util.PrUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.baosight.wilp.ir.sy.service
 * @ClassName ServiceIRSY02
 * @Description 首页公告信息管理
 * @Author chunchen2
 * @Date 2022/1/10 14:11
 * @Version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 * @History 修改记录1
 * <author> chunchen2
 * <time> 2022/1/10 14:11
 * <version>  // 修改人
 * <desc> // 描述修改内容
 */
public class ServiceIRSY02 extends ServiceBase {

    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        inInfo.set("status", ""); // 初始化直接查询所有的记录
        return query(inInfo);
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        // 获取平台数据库的名称
        String platSchema = PlatApplicationContext.getProperty("platSchema");

        // pc页面的查询参数列表，用list包裹起来方便从EiInfo里面获取参数的值
        List<String> queryFieldLists = Arrays.asList("noticeTitle", "noticeContent", "status", "id");
        Map<String, Object> queryMaps=  MaintainUtil.changeToMap(inInfo, queryFieldLists);
        queryMaps.put("platSchema", platSchema);

        System.out.println(queryMaps);

        // 获取公告列表
        List<Map<String, Object>> noticesLists = dao.query("IRSY02.queryNoticeRecords", queryMaps, Integer.parseInt(
                queryMaps.get("offset").toString()), Integer.parseInt(queryMaps.get("limit").toString()));

        // 获取公告的总数
        int count = super.count("IRSY02.queryNoticeCount", queryMaps);

        if(noticesLists.size() > 0){
            return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(noticesLists.get(0)),
                    noticesLists, count);
        } else {
            return inInfo;
        }
    }

    /**
     * @Title queryNoticeRecordById
     * @Author chunchen2
     * @Description //根据id获取公告数据
     * @Date 18:54 2022/1/10
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryNoticeRecordById(EiInfo inInfo) {
        // 获取平台数据库的名称
        String platSchema = PlatApplicationContext.getProperty("platSchema");

        // pc页面的查询参数列表，用list包裹起来方便从EiInfo里面获取参数的值
        List<String> queryFieldLists = Arrays.asList("id");
        Map<String, Object> queryMaps=  MaintainUtil.changeToMap(inInfo, queryFieldLists);
        queryMaps.put("platSchema", platSchema);

        List<Map<String, Object>> noticesLists = dao.query("IRSY02.queryNoticeRecords", queryMaps);

        if(noticesLists.size() > 0) {
            Map<String, Object> noticeRecord = noticesLists.get(0);
            inInfo.setAttr(noticeRecord);
        }

        return inInfo;
    }

    /**
     * @Title delNoticeRecord
     * @Author chunchen2
     * @Description // 根据id列表批量删除公告信息
     * @Date 12:13 2022/1/11
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo deleteNoticeRecordById(EiInfo inInfo){
        String platSchema = PlatApplicationContext.getProperty("platSchema");

        List<String> queryFieldLists = Arrays.asList("idStr");
        Map<String, Object> delMaps=  MaintainUtil.changeToMap(inInfo, queryFieldLists);
        List<String> ids = Arrays.asList(String.valueOf(delMaps.get("idStr")).split(","));
        delMaps.put("ids", ids);
        delMaps.put("platSchema", platSchema);

        try {
            int deleteRet = dao.delete("IRSY02.deleteNoticeRecord", delMaps);
            if(deleteRet > 0){
                inInfo.setStatus(1);
                inInfo.setMsg("删除公告信息成功");
            } else {
                inInfo.setStatus(-2);
                inInfo.setMsg("删除公告信息失败");
            }
        } catch (Exception e){
            inInfo.setStatus(-1);
            inInfo.setMsg("更新公告信息失败：" + e.getCause());
        }


        return inInfo;
    }

    /**
     * @Title showNoticeRecord
     * @Author chunchen2
     * @Description // 展示一条最新启用的公告信息
     * @Date 16:56 2022/1/11
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo showNoticeRecord(EiInfo inInfo){
        String platSchema = PlatApplicationContext.getProperty("platSchema");

        Map<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("platSchema", platSchema);
        queryMaps.put("status", "1");

        List<Map<String, Object>> noticesLists = dao.query("IRSY02.queryNoticeRecords", queryMaps);

        if(noticesLists.size() > 0){// 只取最新的一条信息展示
            Map<String, Object> noticeRecord = noticesLists.get(0);
        }

        if(noticesLists.size() > 0){
            return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(noticesLists.get(0)),
                    noticesLists, 5);
        } else {
            return inInfo;
        }
    }

    /**
     * @Title batchDisableNotice
     * @Author chunchen2
     * @Description //根据公告id,批量停用公告信息
     * @Date 10:32 2022/1/12
     * @param inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo batchDisableNotice(EiInfo inInfo){
        String platSchema = PlatApplicationContext.getProperty("platSchema");

        List<String> idLists = Arrays.asList("idStr");
        Map<String, Object> disableMaps=  MaintainUtil.changeToMap(inInfo, idLists);
        List<String> ids = Arrays.asList(String.valueOf(disableMaps.get("idStr")).split(","));
        disableMaps.put("ids", ids);
        disableMaps.put("platSchema", platSchema);

        try {
            int updateRet = dao.update("IRSY02.batchUpdateNoticeRecord", disableMaps);
            if(updateRet > 0){
                inInfo.setStatus(1);
                inInfo.setMsg("批量禁用公告信息成功");
            } else {
                inInfo.setStatus(-2);
                inInfo.setMsg("批量禁用公告信息失败");
            }
        } catch (Exception e){
            inInfo.setStatus(-1);
            inInfo.setMsg("批量禁用公告信息失败：" + e.getCause());
        }

        return inInfo;
    }
}
