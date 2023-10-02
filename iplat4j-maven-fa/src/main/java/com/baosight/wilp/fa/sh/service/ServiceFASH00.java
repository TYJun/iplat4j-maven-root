package com.baosight.wilp.fa.sh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.*;

public class ServiceFASH00 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        Integer offset = 0;
        Integer limit = 100;
        HashMap<String, Object> map = new HashMap<>();
        Map<String, Object> attrSize = new HashMap<>();
        EiBlock block = inInfo.getBlock("inqu_status");
        String faInfoIdList = (String) inInfo.getAttr().get("faInfoIdList");
        //选择页面显示条数
        EiBlock resultNumber = inInfo.getBlock("resultA");
        if (resultNumber != null) {
            attrSize = resultNumber.getAttr();
        }
        if (attrSize.containsKey("offset") && attrSize.containsKey("limit")) {
            offset = (Integer) attrSize.get("offset");
            limit = (Integer) attrSize.get("limit");
        }
        //查询条件
        if (block != null) {
            Map inquStatus = block.getRow(0);
            map.put("goodsNum", inquStatus.get("goodsNum"));
            map.put("goodsName", inquStatus.get("goodsName"));
            map.put("surpName", inquStatus.get("surpName"));
            map.put("remark", inquStatus.get("remark"));
            map.put("buyCostS", inquStatus.get("buyCostS"));
            map.put("buyCostE", inquStatus.get("buyCostE"));
            map.put("netAssetValueS", inquStatus.get("netAssetValueS"));
            map.put("netAssetValueE", inquStatus.get("netAssetValueE"));
            map.put("buyDateS", inquStatus.get("buyDateS"));
            map.put("buyDateE", inquStatus.get("buyDateE"));
            map.put("useDateS", inquStatus.get("useDateS"));
            map.put("useDateE", inquStatus.get("useDateE"));
            map.put("deptName", inquStatus.get("deptName"));
            map.put("goodsClassifyCode", inquStatus.get("goodsClassifyCode"));
            map.put("goodsTypeCode", inquStatus.get("goodsTypeCode"));
            map.put("fundingSourceNum", inquStatus.get("fundingSourceNum"));
            faInfoIdList = (String) inquStatus.get("faInfoIdList");
        }
        //筛选已选物资
        if (faInfoIdList != null) {
            String[] split = faInfoIdList.split(",");
            ArrayList<String> rowsList = new ArrayList<>(Arrays.asList(split));
            map.put("rowsList", rowsList);
        }
        map.put("statusCode", "040");
        List<Map<String, String>> list = dao.query("FASH01.query", map, offset, limit);
        int count = dao.count("FASH01.query", map);
        attrSize.put("count", count);
        EiBlock resultA = new EiBlock("resultA");
        resultA.setRows(list);
        resultA.setAttr(attrSize);
        inInfo.setBlock(resultA);
        return inInfo;
    }

    public EiInfo updateFaInfoLock(EiInfo info) {
        String lockFlag = info.getString("lockFlag");
        String type = info.getString("type");
        String discussId = info.getString("discussId");
        Object o = info.get("faInfoList");
        if (o != null) {
            List faInfoList = (List) o;
            dao.update("FASH01.updateFaInfoLock", new HashMap<String, Object>() {{
                put("faInfoList", faInfoList);
                put("lockFlag", lockFlag);
            }});
            switch (lockFlag) {
                case "0":
                    // 移除资产
                    dao.delete("FASH01.deleteFaDiscussDetail", new HashMap<String, Object>() {{
                        put("faIdList", faInfoList);
                        put("discussId", discussId);
                    }});
                    if ("wasted".equals(type)) {
                        dao.update("FASH01.removeFaInfoWasting", new HashMap<String, Object>() {{
                            put("faIdList", faInfoList);
                        }});
                    }
                    break;
                case "1":
                    // 新增资产
                    dao.insert("FASH01.insertFaDiscussDetail", new HashMap<String, Object>() {{
                        put("faIdList", faInfoList);
                        put("discussId", discussId);
                    }});
                    break;
            }
        }
        return info;
    }
}
