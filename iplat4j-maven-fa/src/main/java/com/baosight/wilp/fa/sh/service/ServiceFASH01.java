package com.baosight.wilp.fa.sh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceFASH01 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo info) {
        return confirmedQuery(info);
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        return confirmedQuery(inInfo);
    }

    // 报废提交资产
    public EiInfo confirmedQuery(EiInfo info) {
        Integer offset = 0;
        Integer limit = 100;
        EiBlock resultA = info.getBlock("resultA");
        Map<String, Object> attr = new HashMap<>();
        if (resultA != null) {
            attr = resultA.getAttr();
        }
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
            map.put("orderBy",attr.get("orderBy"));
            String deptNameSplit = (String) map.get("deptName");
            if (StringUtils.isNotEmpty(deptNameSplit)) {
                String[] split = deptNameSplit.split(",");
                for (int i = 0; i < split.length; i++) {
                    split[i] = "fi.dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
                }
                String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
                info.setCell("inqu_status", 0, "deptNameSplit", param);
            }
        }
        if (attr.containsKey("offset") && attr.containsKey("limit")) {
            offset = (Integer) attr.get("offset");
            limit = (Integer) attr.get("limit");
        }
        map.put("statusCode", "040");
        List<Map<String, String>> list = dao.query("FASH01.query", map, offset, limit);
        int count = list.size();
        attr.put("count", count);
        EiBlock block = new EiBlock("resultA");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        // 1.获取参数,处理参数
        Map<String, Object> map1 = CommonUtils.buildParamter(info, "inqu_status", "dept");
        // 2.调用微服务接口S_AC_FW_05，获取科室信息
        map1.remove("limit");
        List<Map<String, String>> maps = dao.query("FADA01.queryDept", map1);
        info.addBlock("dept").addRows(maps);
        return info;
    }

    // 上会讨论资产
    public EiInfo discussQuery(EiInfo info) {
        Integer offset = 0;
        Integer limit = 100;
        Map attr = info.getBlock("resultB").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset") && attr.containsKey("limit")) {
            offset = (Integer) attr.get("offset");
            limit = (Integer) attr.get("limit");
        }
        map.put("discussStatus", "00");
        List<Map<String, String>> list = dao.query("FASH01.discussQuery", map, offset, limit);
        int count = dao.count("FASH01.discussQuery", map);
        attr.put("count", count);
        EiBlock block = new EiBlock("resultB");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        return info;
    }

    // 预报废资产
    public EiInfo wastingQuery(EiInfo info) {
        Integer offset = 0;
        Integer limit = 100;
        Map attr = info.getBlock("resultC").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset") && attr.containsKey("limit")) {
            offset = (Integer) attr.get("offset");
            limit = (Integer) attr.get("limit");
        }
        map.put("discussStatus", "10");
        List<Map<String, String>> list = dao.query("FASH01.discussQuery", map, offset, limit);
        int count = dao.count("FASH01.discussQuery", map);
        attr.put("count", count);
        EiBlock block = new EiBlock("resultC");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        return info;
    }

    // 已报废资产
    public EiInfo wastedQuery(EiInfo info) {
        Integer offset = 0;
        Integer limit = 100;
        Map attr = info.getBlock("resultD").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
            String deptNameSplit = (String) map.get("deptName");
            if (StringUtils.isNotEmpty(deptNameSplit)) {
                String[] split = deptNameSplit.split(",");
                for (int i = 0; i < split.length; i++) {
                    split[i] = "fi.dept_name LIKE concat ( '%', trim('" + split[i] + "'), '%' )";
                }
                String param = "(" + org.apache.commons.lang.StringUtils.join(split, " OR ") + ")";
                info.setCell("inqu_status", 0, "deptNameSplit", param);
            }
        }
        if (attr.containsKey("offset") && attr.containsKey("limit")) {
            offset = (Integer) attr.get("offset");
            limit = (Integer) attr.get("limit");
        }
        map.put("statusCode", "060");
        List<Map<String, String>> list = dao.query("FASH01.query", map, offset, limit);
        int count = dao.count("FASH01.query", map);
        attr.put("count", count);
        EiBlock block = new EiBlock("resultD");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        return info;
    }

    // 完结上会单
    public EiInfo FinsihQuery(EiInfo info) {
        Integer offset = 0;
        Integer limit = 100;
        Map attr = info.getBlock("resultE").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset") && attr.containsKey("limit")) {
            offset = (Integer) attr.get("offset");
            limit = (Integer) attr.get("limit");
        }
        map.put("discussStatus", "99");
        List<Map<String, String>> list = dao.query("FASH01.discussQuery", map, offset, limit);
        int count = dao.count("FASH01.discussQuery", map);
        attr.put("count", count);
        EiBlock block = new EiBlock("resultE");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        return info;
    }
}
