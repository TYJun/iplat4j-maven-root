package com.baosight.wilp.fa.sh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceFASH01 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        return inInfo;
    }

    // 报废提交资产
    public EiInfo confirmedQuery(EiInfo info) {
        Integer offset = 0;
        Map attr = info.getBlock("resultA").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset")) {
            offset = (Integer) attr.get("offset");
        }
        map.put("statusCode", "040");
        List<Map<String, String>> list = dao.query("FASH01.query", map, offset, (Integer) attr.get("limit"));
        int count = dao.count("FASH01.query", map);
        attr.put("count", count);
        EiBlock block = new EiBlock("resultA");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        return info;
    }

    // 上会讨论资产
    public EiInfo discussQuery(EiInfo info) {
        Integer offset = 0;
        Map attr = info.getBlock("resultB").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset")) {
            offset = (Integer) attr.get("offset");
        }
        map.put("discussStatus", "00");
        List<Map<String, String>> list = dao.query("FASH01.discussQuery", map, offset, (Integer) attr.get("limit"));
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
        Map attr = info.getBlock("resultC").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset")) {
            offset = (Integer) attr.get("offset");
        }
        map.put("discussStatus", "10");
        List<Map<String, String>> list = dao.query("FASH01.discussQuery", map, offset, (Integer) attr.get("limit"));
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
        Map attr = info.getBlock("resultD").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset")) {
            offset = (Integer) attr.get("offset");
        }
        map.put("statusCode", "060");
        List<Map<String, String>> list = dao.query("FASH01.query", map, offset, (Integer) attr.get("limit"));
        int count = dao.count("FASH01.query", map);
        attr.put("count", count);
        EiBlock block = new EiBlock("resultD");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        return info;
    }

    // 完结上会单
    public EiInfo FinsihQuery(EiInfo info){
        Integer offset = 0;
        Map attr = info.getBlock("resultE").getAttr();
        Map<String, Object> map = new HashMap<>();
        EiBlock eiBlock = info.getBlock("inqu_status");
        if (eiBlock != null) {
            map = eiBlock.getRow(0);
        }
        if (attr.containsKey("offset")) {
            offset = (Integer) attr.get("offset");
        }
        map.put("discussStatus", "99");
        List<Map<String, String>> list = dao.query("FASH01.discussQuery", map, offset, (Integer) attr.get("limit"));
        int count = dao.count("FASH01.discussQuery", map);
        attr.put("count", count);
        EiBlock block = new EiBlock("resultE");
        block.setRows(list);
        block.setAttr(attr);
        info.setBlock(block);
        return info;
    }
}
