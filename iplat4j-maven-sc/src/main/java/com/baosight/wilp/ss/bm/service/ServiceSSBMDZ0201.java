package com.baosight.wilp.ss.bm.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.ScPatientAddress;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class ServiceSSBMDZ0201 extends ServiceBase {
    @Override
    public EiInfo insert(EiInfo inInfo) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String building = inInfo.getAttr().get("building").toString();
        String floor = inInfo.getAttr().get("floor").toString();
        String deptName = inInfo.getAttr().get("deptName").toString();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("deptName", deptName);
        List<ScPatientAddress> list =dao.query("SSBMDZ02.queryAcDepartment", hashMap);
        ScPatientAddress scPatientAddress = new ScPatientAddress();
        scPatientAddress.setId(id);
        scPatientAddress.setDeptName(deptName);
        scPatientAddress.setDeptNum(list.get(0).getDeptNum());
        scPatientAddress.setDeptNumParent(list.get(0).getDeptNumParent());
        scPatientAddress.setDeptNameParent(list.get(0).getDeptNameParent());
        scPatientAddress.setTakeEffect("1");
        scPatientAddress.setBuilding(building);
        scPatientAddress.setFloor(floor);
        dao.insert("SSBMDZ02.insert", scPatientAddress);
        inInfo.setMsg("添加成功");
        return inInfo;
    }
}
