package com.baosight.wilp.fa.sh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ServiceFASH0101 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
        return inInfo;
    }

    public EiInfo insertFaDiscuss(EiInfo inInfo) {
        List<String> faIdList = new ArrayList<>();
        EiBlock info = inInfo.getBlock("info");
        Map<String, Object> row = info.getRow(0);
        String discussId = UUID.randomUUID().toString();
        String discussNo = OneSelfUtils.publicCreateCode("SH");
        row.put("discussId", discussId);
        row.put("discussNo", discussNo);
        row.put("discussStatus","00");
        row.put("discussDate", StringUtils.isNotEmpty((String) row.get("discussDate"))?row.get("discussDate"):null);
        if (inInfo.get("list") != null) {
            List<Map<String, Object>> list = (List) inInfo.get("list");
//            for (Map map : list) {
//                faIdList.add((String) map.get("faInfoId"));
//            }
            row.put("faIdList", list);
        }
        dao.insert("FASH01.insertFaDiscuss", row);
        dao.insert("FASH01.insertFaDiscussDetail", row);
        dao.update("FASH01.updateFaInfo", row);
        return inInfo;
    }
}
