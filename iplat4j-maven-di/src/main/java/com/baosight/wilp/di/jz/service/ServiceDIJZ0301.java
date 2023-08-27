package com.baosight.wilp.di.jz.service;


import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.di.jz.domain.DIJZ03;

import java.util.UUID;


public class ServiceDIJZ0301 extends ServiceBase {
    @Override
    public EiInfo insert(EiInfo inInfo) {
        String id = UUID.randomUUID().toString().replace("-", "");
        String configureNum = inInfo.getAttr().get("configureNum").toString();
        String configureName = inInfo.getAttr().get("configureName").toString();
        String configureTime = inInfo.getAttr().get("configureTime").toString();


        DIJZ03 dijz03 = new DIJZ03();
        dijz03.setId(id);
        dijz03.setConfigureNum(configureNum);
        dijz03.setConfigureName(configureName);
        dijz03.setConfigureTime(configureTime);
        dao.insert("DIJZ03.insertGroup", dijz03);
        inInfo.setMsg("添加成功");
        return inInfo;
    }
}
