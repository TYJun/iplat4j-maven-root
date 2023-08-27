package com.baosight.wilp.si.cx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;

import java.util.Calendar;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: 出入库查询页面Service
 * @ClassName: ServiceSICX01
 * @Package com.baosight.wilp.si.cx.service
 * @date: 2022年12月23日 16:14
 *
 * 1.页面加载
 * 2.查询报表
 */
public class ServiceSICX01 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.set("deptNum", deptMap.get("deptNum"));
        inInfo.set("deptName", deptMap.get("deptName"));
        Calendar cal = Calendar.getInstance();
        inInfo.set("endTime", DateUtils.curDateStr10());
        cal.add(Calendar.MONTH, -1);
        inInfo.set("beginTime", DateUtils.toDateStr10(cal.getTime()));
        return inInfo;
    }

    /**
     * 查询报表列表
     * @Title: queryReportTree
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryReportTree(EiInfo inInfo) {

        return inInfo;
    }
}
