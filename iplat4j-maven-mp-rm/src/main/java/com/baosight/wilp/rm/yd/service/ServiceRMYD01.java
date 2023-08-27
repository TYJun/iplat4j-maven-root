package com.baosight.wilp.rm.yd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmApproval;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.domain.RmRequirePlanDetail;
import com.baosight.wilp.rm.lj.service.RmApprovalHistoryService;
import com.baosight.wilp.rm.lj.service.RmRequirePlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求计划APP接口Service
 * @ClassName: ServiceRMYD01
 * @Package com.baosight.wilp.rm.yd.service
 * @date: 2022年09月21日 14:58
 *
 * 1.待审批需求计划列表
 * 2.需求计划明细
 * 3.根据需求计划单号获取指定需求计划
 * 4.需求计划审批通过
 * 5.需求计划审批驳回
 * 6.需求计划批量审批通过
 * 7.需求计划审批批量驳回
 */
public class ServiceRMYD01 extends ServiceBase {

    @Autowired
    private RmRequirePlanService requirePlanService;

    @Autowired
    private RmApprovalHistoryService approvalHistoryService;

    /**
     * 科室领导
     **/
    public static final String ROLE = "MP_DEPT_LEADER";

    /**
     * 待审批需求计划列表
     * @Title: queryUnApproveList
     * @param inInfo inInfo
     *      role: 角色编码字符串
     *      curDept: 当前科室编码
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryUnApproveList(EiInfo inInfo) {
        //参数处理
        String role = inInfo.getString("role");
        String deptNum = inInfo.getString("curDept");
        if(StringUtils.isBlank(deptNum)) {
            return ValidatorUtils.errorInfo("科室参数不能为空");
        }
        //角色校验,判断用户的角色是否满足。不满足，返回空;满足,查询数据
        if(!ROLE.equals(role)) {
            inInfo.set("list", new ArrayList<>());
        } else {
            List<RmRequirePlan> list = dao.query("RMLJ01.query", new HashMap(4) {{
                put("deptNum", deptNum);
                put("statusCode", RmConstant.REQUIRE_STATUS_UN_APPROVAL);
            }});
            inInfo.set("list", list);
        }
        return inInfo;
    }

    /**
     * 获取需求计划明细
     * @Title: queryDetailList
     * @param inInfo inInfo
     *      planId: 需求计划ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetailList(EiInfo inInfo) {
        String planId = inInfo.getString("planId");
        if(StringUtils.isBlank(planId)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        List<RmRequirePlanDetail> details = requirePlanService.queryRequirePlanDetailList(planId);
        inInfo.set("list", details);
        return inInfo;
    }

    /**
     * 根据需求计划单号获取指定需求计划
     * @Title: queryRequirePlan
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryRequirePlan(EiInfo inInfo) {
        String planNo = inInfo.getString("planNo");
        if(StringUtils.isBlank(planNo)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlanByPlanNo(planNo);
        inInfo.set("requirePlan", plan);
        return inInfo;
    }

    /**
     * 需求计划审批通过
     * @Title: pass
     * @param inInfo inInfo
     *      planId : 需求计划ID
     *      workNo: 当前登录人工号
     *      name : 当前登录人姓名
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pass(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMXQ0401", "pass");
    }

    /**
     * 需求计划审批驳回
     * @Title: reject
     * @param inInfo inInfo
     *      planId : 需求计划ID
     *      rejectReason : 驳回原因
     *      workNo: 当前登录人工号
     *      name : 当前登录人姓名
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo reject(EiInfo inInfo) {
        String reason = inInfo.getString("rejectReason");
        if(StringUtils.isBlank(reason)) {
            return ValidatorUtils.errorInfo("驳回原因不能为空");
        }
        return RmUtils.invoke(inInfo, "RMXQ0401", "reject");
    }

    /**
     * 批量审批通过
     * @Title: batchPass
     * @param inInfo inInfo
     *      ids: 需求计划ID集合
     *      workNo: 当前登录人工号
     *      name : 当前登录人姓名
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo batchPass(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMXQ04", "batchPass");
    }

    /**
     * 批量审批驳回
     * @Title: batchReject
     * @param inInfo inInfo
     *      ids: 需求计划ID集合
     *      workNo: 当前登录人工号
     *      name : 当前登录人姓名
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo batchReject(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMXQ04", "batchReject");
    }

    /**
     * 获取审批履历
     * @Title: queryApproval
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryApproval(EiInfo inInfo) {
        String relateId = inInfo.getString("relateId");
        if(StringUtils.isBlank(relateId)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        List<RmApproval> mpApprovals = approvalHistoryService.queryApproval(relateId);
        inInfo.set("list", mpApprovals);
        return inInfo;
    }

}
