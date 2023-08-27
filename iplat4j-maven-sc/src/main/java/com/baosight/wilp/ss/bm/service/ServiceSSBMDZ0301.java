package com.baosight.wilp.ss.bm.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.SSBMDZ03;
import com.baosight.wilp.ss.bm.domain.ScPatientAddress;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class ServiceSSBMDZ0301 extends ServiceBase {
    @Override
    public EiInfo insert(EiInfo inInfo) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String groupNum = inInfo.getAttr().get("groupNum").toString();
        String groupName = inInfo.getAttr().get("groupName").toString();
        String deptName = inInfo.getAttr().get("deptName").toString();
        String deptNum = inInfo.getAttr().get("deptNum").toString();

        SSBMDZ03 ssbmdz03 = new SSBMDZ03();
        ssbmdz03.setId(id);
        ssbmdz03.setDeptName(deptName);
        ssbmdz03.setDeptNum(deptNum);
        ssbmdz03.setGroupNum(groupNum);
        ssbmdz03.setGroupName(groupName);
        dao.insert("SSBMDZ03.insertGroup", ssbmdz03);
        inInfo.setMsg("添加成功");
        return inInfo;
    }
}
