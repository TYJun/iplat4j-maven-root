package com.baosight.wilp.pr.ap.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.wilp.pr.ap.domain.ResultData;


/**
 * 
 * 登录接口 -- 暂不使用
 * 
 * @Title: AppLoginService.java
 * @ClassName: AppLoginService
 * @Package：com.baosight.wilp.pr.ap.service;
 * @author: zhangjiaqian
 * @date: 2021年2月19日 下午3:37:14
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class AppLoginService {

    
    public ResultData login(HttpServletRequest request, HttpServletResponse response){
        EiInfo eiInfo = new EiInfo();
        ResultData result = new ResultData();
        eiInfo.set(EiConstant.serviceId,"S_XS_08");
        String userName=request.getParameter("userName");
        String passWord=request.getParameter("passWord");
        eiInfo.set("loginName",userName);
        eiInfo.set("password",passWord);
        EiInfo outInfo = XServiceManager.call(eiInfo);
        int status=outInfo.getStatus();
        String msg=outInfo.getMsg();
        if(status!=1) {
            result.setRespCode("199");
            result.setRespMsg("账号或密码错误，请重新登录");
            result.setParam("null");
            return result;
        }
        EiInfo info = new EiInfo();
        info.set("name", userName);
        info.set(EiConstant.serviceName, "PRAP01");
        info.set(EiConstant.methodName, "login");
        EiInfo infoLogin =XLocalManager.call(info);
        Map<String, String> map=(Map<String, String>)infoLogin.get("map");
        result.setRespCode("200");
        result.setRespMsg("登录成功");
        result.setParam(map);
        return result;
    }
}
