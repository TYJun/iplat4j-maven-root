package com.baosight.wilp.fa.sh.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;

public class ServiceFASH00 extends ServiceBase {
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    @Override
    public EiInfo query(EiInfo inInfo) {
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
                        dao.update("FASH01.removeFaInfoWasting",new HashMap<String, Object>(){{
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
