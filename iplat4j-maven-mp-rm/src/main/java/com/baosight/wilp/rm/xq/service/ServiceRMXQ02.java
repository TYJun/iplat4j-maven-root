package com.baosight.wilp.rm.xq.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;

import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 月度需求计划管理页面Service
 * @ClassName: ServiceRMXQ02
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2022年09月09日 17:13
 * <p>
 * 1.页面加载
 * 2.页面数据查询
 */
public class ServiceRMXQ02 extends ServiceBase {

    /**
     * 页面加载
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: initLoad
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //添加科室查询条件
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 20);
        return query(inInfo);
    }

    /**
     * 页面数据查询(月度需求计划)
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: query
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        //添加需求计划类型查询条件
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "planType", RmConstant.PLAN_TYPE_MONTH);
        return super.query(inInfo, "RMLJ01.query", new RmRequirePlan());
    }

}
