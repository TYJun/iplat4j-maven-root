package com.baosight.wilp.mc.jk.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "DeptService", // 暴露服务名称
        targetNamespace = "http://service.jk.mc.wilp.baosight.com"// 命名空间,一般是接口的包名倒序
)
public interface DeptService {
    @WebMethod
//    void insertPersooner(@WebParam(name = "body")String body) throws DocumentException;
    public void insertDept(@WebParam(name="str",targetNamespace="http://service.jk.mc.wilp.baosight.com")String str);
}
