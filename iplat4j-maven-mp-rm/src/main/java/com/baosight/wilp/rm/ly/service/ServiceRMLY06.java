package com.baosight.wilp.rm.ly.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;

import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 科室领用成本报表页面Service
 * @ClassName: ServiceRMLY06
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2023年04月13日 12:49
 */
public class ServiceRMLY06 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
       /* Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.set("deptNum", deptMap.get("deptNum"));
        inInfo.set("deptName", deptMap.get("deptName"));*/
        inInfo.set("workNo", UserSession.getLoginName());
        inInfo.set("frameSchema", PlatApplicationContext.getProperty("projectSchema"));
        return inInfo;
    }
}
