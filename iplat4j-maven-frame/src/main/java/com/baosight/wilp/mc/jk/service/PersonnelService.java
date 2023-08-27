package com.baosight.wilp.mc.jk.service;

import com.baosight.iplat4j.core.ei.EiInfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService(name = "PersonnelService", // 暴露服务名称
        targetNamespace = "http://service.jk.mc.wilp.baosight.com"// 命名空间,一般是接口的包名倒序
)

public class PersonnelService {
    public String insertPersooner(){
        System.out.println("111");
        return null;
    }
}
