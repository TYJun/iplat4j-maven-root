package com.baosight.wilp.fa.qr.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年12月14日 11:25
 */
public class ServiceFAQR0101 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        List<Map<String, String>> mapList = dao.query("FAQR01.queryFaConfirmVOInfo", new HashMap<String, Object>() {{
            put("id", inInfo.getString("faConfirmId"));
        }});
        if (CollectionUtils.isNotEmpty(mapList)) {
            String matTypeName = (String) mapList.get(0).get("matTypeName");
            // 通过资产类别进行查询
            List<Map<String, String>> FaTypeInfo = dao.query("FAQR01.queryFaTypeInfoByMatTypeName", matTypeName);
            String manufacturerNatyCode = StringUtils.isNotEmpty(mapList.get(0).get("manufacturerNatyCode")) ? mapList.get(0).get("manufacturerNatyCode") : "156";
            mapList.get(0).put("manufacturerNatyCode", manufacturerNatyCode);
            inInfo.setRows("info", mapList);
            inInfo.set("surpNum", mapList.get(0).get("surpNum"));
            inInfo.set("surpName", mapList.get(0).get("surpName"));
            inInfo.set("deptNum", mapList.get(0).get("deptNum"));
            inInfo.set("deptName", mapList.get(0).get("deptName"));
            if (CollectionUtils.isNotEmpty(FaTypeInfo)) {
                inInfo.setCell("info", 0, "parentId", FaTypeInfo.get(0).get("parentId"));
                inInfo.setCell("info", 0, "goodsTypeCode", FaTypeInfo.get(0).get("goodsTypeCode"));
                inInfo.setCell("info", 0, "goodsTypeName", FaTypeInfo.get(0).get("goodsTypeName"));
                inInfo.setCell("info", 0, "useYears", FaTypeInfo.get(0).get("useYears"));
                inInfo.setCell("info", 0, "goodsClassifyCode", FaTypeInfo.get(0).get("goodsClassifyCode"));
                inInfo.setCell("info", 0, "goodsClassifyName", FaTypeInfo.get(0).get("goodsClassifyName"));
            }
        }
        return inInfo;
    }

    /**
     * 资产末级确认模糊查询
     *
     * @param inInfo
     * @return
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        // 1.设置分页条件
        Map<String, Object> pageMap = new HashMap<>(16);
        Integer offset = 0;
        Integer limit = 50;
        EiBlock eiBlock = (EiBlock) inInfo.getBlocks().get("result");
        if (eiBlock != null) {
            pageMap = eiBlock.getAttr();
            if (pageMap.containsKey("offset") && pageMap.containsKey("limit")){
                offset = (Integer) pageMap.get("offset");
                limit = (Integer) pageMap.get("limit");
            }
        }
        Map<String, Object> paramsMap = new HashMap<>(4);
        // 2.在初始化查询时，不存在inqu_status
        if (inInfo.getBlock("inqu_status") != null) {
            // 3.点击查询时获取集合，并且去除空字符
            paramsMap = inInfo.getBlock("inqu_status").getRow(0);
        }
        // 4.查询结果并返回
        Map<String, String> map = inInfo.getRow("info", 0);
        // 获取对应资产节点
        String parentId = map.get("parentId");
        List<String> treeMenuList = dao.query("FALB01.treeMenu", new HashMap<String, String>() {{
            put("parentId", parentId);
        }});
        treeMenuList.add(parentId);
        paramsMap.put("treeMenuList", treeMenuList);
        List<Map<String, Object>> resultList = dao.query("FALB01.queryAessettypeInfo", paramsMap, offset, limit);
        int count = dao.count("FALB01.queryAessettypeInfo", paramsMap);
        pageMap.put("count", count);
        if (CollectionUtils.isNotEmpty(resultList)) {
            inInfo.setRows("result", resultList);
        }
        inInfo.setAttr(pageMap);
        return inInfo;
    }
}
