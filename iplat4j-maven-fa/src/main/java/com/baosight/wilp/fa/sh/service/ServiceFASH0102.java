package com.baosight.wilp.fa.sh.service;

import com.baosight.iplat4j.core.data.ibatis.dao.SqlMapDao;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ServiceFASH0102 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiBlock block = inInfo.getBlock("inqu_status");
        EiBlock info = inInfo.getBlock("info");
        EiBlock result = inInfo.getBlock("result");
        Map<String, Object> map = new HashMap<String, Object>();
        if (block != null) {
            map = block.getRow(0);
        }
        String discussId = inInfo.getString("discussId");
        if (info != null) {
            discussId = (String) info.getRow(0).get("discussId");
        }
        String discussName = inInfo.getString("discussName");
        String discussDate = inInfo.getString("discussDate");
        String type = inInfo.getString("type");
        map.put("discussId", discussId);
        int count = dao.count("FASH01.faDiscussDetail", map);
        SqlMapDao sqlMapDao = (SqlMapDao) this.dao;
        sqlMapDao.setMaxQueryCount(count);
        List list = sqlMapDao.query("FASH01.faDiscussDetail", map);
        inInfo.setCell("info", 0, "discussName", discussName);
        inInfo.setCell("info", 0, "discussDate", discussDate);
        inInfo.setCell("info", 0, "discussId", discussId);
        inInfo.setCell("info", 0, "type", type);
        inInfo.setRows("result", list);
        if (result != null) {
            result.setAttr(new HashMap(){{
                put("limit",count);
            }});
        }
        return inInfo;
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        return inInfo;
    }

    public EiInfo updateFaInfoWasting(EiInfo info) {
        String discussId = info.getString("discussId");
        Object o = info.get("faIdList");
        if (o != null) {
            List faIdList = (List) o;
            // 修改上会单状态
            dao.update("FASH01.updateFaDiscussStatus", new HashMap<String, Object>() {{
                put("discussId", discussId);
                put("discussStatus", "10");
            }});
            // 修改资产状态
            dao.update("FASH01.updateFaInfoWasting", new HashMap<String, Object>() {{
                put("discussId", discussId);
                put("faIdList", faIdList);
            }});
        }
        return info;
    }

    public EiInfo updateFaInfoWasted(EiInfo info) {
        String discussId = info.getString("discussId");
        Object o = info.get("faIdList");
        if (o != null) {
            List faIdList = (List) o;
            // 修改上会单状态
            dao.update("FASH01.updateFaDiscussStatus", new HashMap<String, Object>() {{
                put("discussId", discussId);
                put("discussStatus", "99");
            }});
            // 修改资产状态
            dao.update("FASH01.updateFaInfoWasted", new HashMap<String, Object>() {{
                put("discussId", discussId);
                put("faIdList", faIdList);
            }});
        }
        return info;
    }
}
