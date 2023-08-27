package com.baosight.wilp.rm.ly.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用申请页面Service
 * @ClassName: ServiceRMLY01
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月15日 14:58
 * <p>
 * 1.页面加载
 * 2.页面数据查询
 * 3.批量审批通过
 * 4.批量审批驳回
 */
public class ServiceRMLY03 extends ServiceBase {

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
        RmUtils.initQueryTime(inInfo, "beginTime", "endTime");
        //添加成本科室查询条件
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "costDeptNum", deptMap.get("deptNum"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "costDeptName", deptMap.get("deptName"));
        return query(inInfo);
    }

    /**
     * 页面数据查询
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: query
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        //添加状态查询条件: 待审批
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "statusCode", RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE);
        return super.query(inInfo, "RMLJ02.query", new RmClaim());
    }

    /**
     * 查询领用明细
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryDetail
     **/
    public EiInfo queryDetail(EiInfo inInfo) {
        return super.query(inInfo, "RMLJ02.queryDetail", new RmClaimDetail(), false, null, null, "detail", "detail");
    }


    /**
     * 批量审批通过
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: batchPass
     **/
    public EiInfo batchPass(EiInfo inInfo) {
        //参数处理
        List<String> ids = RmUtils.toList(inInfo.get("ids"), String.class);
        if (CollectionUtils.isEmpty(ids)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //遍历，审批
        EiInfo outInfo = null;
        for (String id : ids) {
            outInfo = RmUtils.invoke("RMLY0301", "pass", Arrays.asList("claimId"), id);
        }
        return outInfo;
    }

    /**
     * 批量审批驳回
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: batchReject
     **/
    public EiInfo batchReject(EiInfo inInfo) {
        //参数处理
        List<String> ids = RmUtils.toList(inInfo.get("ids"), String.class);
        if (CollectionUtils.isEmpty(ids)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //遍历，审批
        EiInfo outInfo = null;
        for (String id : ids) {
            outInfo = RmUtils.invoke("RMLY0301", "reject", Arrays.asList("claimId", "rejectReason"),
                    id, inInfo.getString("rejectReason"));
        }
        return outInfo;
    }
}
