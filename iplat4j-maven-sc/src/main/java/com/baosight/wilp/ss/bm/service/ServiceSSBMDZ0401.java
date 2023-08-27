package com.baosight.wilp.ss.bm.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.ss.bm.domain.ScAddr;
import com.baosight.wilp.ss.bm.domain.ScAddress;

import java.util.UUID;


public class ServiceSSBMDZ0401 extends ServiceBase {
    @Override
    public EiInfo insert(EiInfo inInfo) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String building = inInfo.getAttr().get("building").toString();
        String menuName = inInfo.getAttr().get("menuName").toString();
//        String address = inInfo.getAttr().get("address").toString();
        ScAddr scAddress = new ScAddr();
        scAddress.setId(id);
//        scAddress.setAddress(address);
        scAddress.setBuilding(building);
        scAddress.setMenuName(menuName);
        dao.insert("SSBMDZ04.insert", scAddress);
        inInfo.setMsg("添加成功");
        return inInfo;
    }
}
