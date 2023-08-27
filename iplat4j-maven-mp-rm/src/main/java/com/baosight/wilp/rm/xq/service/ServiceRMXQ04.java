package com.baosight.wilp.rm.xq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.domain.RmRequirePlanDetail;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求计划审批管理页面Service
 * @ClassName: ServiceRMXQ02
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2022年09月09日 17:13
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.批量审批通过
 * 4.批量审批驳回
 */
public class ServiceRMXQ04 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }

    /**
     * 页面数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        //添加科室查询条件
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptName",deptMap.get("deptName"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptNum",deptMap.get("deptNum"));
        //添加状态查询条件: 待审批
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCode", RmConstant.REQUIRE_STATUS_UN_APPROVAL);
        return super.query(inInfo, "RMLJ01.query", new RmRequirePlan());
    }

    /**
     * 查询需求计划明细
     * @Title: queryDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        return super.query(inInfo, "RMLJ01.queryDetail", new RmRequirePlanDetail(), false, null,null,"detail", "detail");
    }

    /**
     * 批量审批通过
     * @Title: batchPass
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo batchPass(EiInfo inInfo) {
        //参数处理
        List<String> ids = RmUtils.toList(inInfo.get("ids"), String.class);
        if(CollectionUtils.isEmpty(ids)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //遍历，审批
        EiInfo outInfo = null;
        for (String id : ids) {
            outInfo = RmUtils.invoke("RMXQ0401", "pass", Arrays.asList("planId", "workNo", "name"),
                    id, inInfo.get("workNo"), inInfo.get("name"));
        }
        return outInfo;
    }

    /**
     * 批量审批驳回
     * @Title: batchReject
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo batchReject(EiInfo inInfo) {
        //参数处理
        List<String> ids = RmUtils.toList(inInfo.get("ids"), String.class);
        if(CollectionUtils.isEmpty(ids)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //遍历，审批
        EiInfo outInfo = null;
        for (String id : ids) {
            outInfo = RmUtils.invoke("RMXQ0401", "reject", Arrays.asList("planId", "rejectReason", "workNo", "name"),
                    id, inInfo.getString("rejectReason"), inInfo.get("workNo"), inInfo.get("name"));
        }
        return outInfo;
    }

}
