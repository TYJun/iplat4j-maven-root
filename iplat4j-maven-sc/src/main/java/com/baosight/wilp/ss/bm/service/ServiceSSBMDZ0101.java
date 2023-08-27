package com.baosight.wilp.ss.bm.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.ScAddress;

import java.util.UUID;


public class ServiceSSBMDZ0101 extends ServiceBase {
    @Override
    public EiInfo insert(EiInfo inInfo) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String building = inInfo.getAttr().get("building").toString();
        String floor = inInfo.getAttr().get("floor").toString();
        String address = inInfo.getAttr().get("address").toString();
        ScAddress scAddress = new ScAddress();
        scAddress.setId(id);
        scAddress.setAddress(address);
        scAddress.setBuilding(building);
        scAddress.setFloor(floor);
        dao.insert("SSBMDZ01.insert", scAddress);
        inInfo.setMsg("添加成功");
        return inInfo;
    }
}
