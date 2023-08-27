package com.baosight.wilp.mp.yd.service;

import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpQyWxUtils;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpApproval;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;
import com.baosight.wilp.mp.lj.service.MpOaDockService;
import com.baosight.wilp.mp.lj.service.MpProcurementApprovalHistoryService;
import com.baosight.wilp.mp.lj.service.MpProcurementPlanService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 采购计划APP接口Service
 * @ClassName: ServiceMPYD01
 * @Package com.baosight.wilp.mp.yd.service
 * @date: 2022年10月17日 18:20
 *
 * 1.获取待科室审批采购计划
 * 2.获取指定采购计划
 * 3.获取采购计划明细
 * 4.采购计划审批通过
 * 5.采购计划审批驳回
 * 6.获取审批履历
 */
public class ServiceMPYD01 extends ServiceBase {

    @Autowired
    private MpProcurementApprovalHistoryService approvalHistoryService;

    @Autowired
    private MpProcurementPlanService planService;

    @Autowired
    private MpOaDockService mpOaDockService;

    /**
     * 获取待科室审批采购计划
     * @Title: queryDeptUnApproveList
     * @param inInfo inInfo
     *      role: 角色编码字符串
     *      curDept: 当前科室编码
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDeptUnApproveList(EiInfo inInfo) {
        //参数处理
        String role = inInfo.getString("role");
        String deptNum = inInfo.getString("curDept");
        if(StringUtils.isBlank(role)) {
            return ValidatorUtils.errorInfo("角色编码不能为空");
        }
        if(StringUtils.isBlank(deptNum)) {
            return ValidatorUtils.errorInfo("科室参数不能为空");
        }

        //角色校验,判断用户的角色是否满足。不满足，返回空;满足,查询数据
        HashMap<String, String> pMap = new HashMap<>(8);
        if(role.contains(MpConstant.DEAN_ROLE)) {
            pMap.put("statusCode", MpConstant.PROCUREMENT_STATUS_THREE);
        } else if(role.contains(MpConstant.LEADER_ROLE) && role.contains(MpConstant.DEPT_ROLE)) {
            pMap.put("roleAuth", "deptAndLeader");
            pMap.put("curDept", deptNum);
            pMap.put("depts", MpUtils.getBusinessDept(deptNum, true));
        } else if(role.contains(MpConstant.LEADER_ROLE) && !role.contains(MpConstant.DEPT_ROLE)) {
            pMap.put("roleAuth", "leader");
            pMap.put("depts", MpUtils.getBusinessDept(deptNum, true));
        } else if(role.contains(MpConstant.DEPT_ROLE)) {
            pMap.put("roleAuth", "dept");
            pMap.put("curDept", deptNum);
        } else {
            pMap.put("deptNum", "no auth");
        }

        //数据查询
        List<Map> list = dao.query("MPCG01.query", pMap);
        inInfo.set("list", list);
        return inInfo;
    }

    /**
     * 获取指定采购计划
     * @Title: queryPurchasePlan
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryPurchasePlan(EiInfo inInfo) {
        String purchaseNo = inInfo.getString("purchaseNo");
        if(StringUtils.isBlank(purchaseNo)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        List<MpPurchasePlan> list = dao.query("MPLJ01.query", new HashMap(4) {{
            put("purchaseNoEQ", purchaseNo);
        }});
        inInfo.set("purchasePlan", CollectionUtils.isEmpty(list) ? null : list.get(0));
        return inInfo;
    }

    /**
     * 获取采购计划明细
     * @Title: queryDetailList
     * @param inInfo inInfo
     *      planId: 采购计划ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo queryDetailList(EiInfo inInfo) {
        String planId = inInfo.getString("planId");
        if(StringUtils.isBlank(planId)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        List<MpPurchasePlanDetail> list = dao.query("MPLJ01.queryDetail", new HashMap(2) {{
            put("purchaseId", planId);
        }});
        inInfo.set("list", list);
        return inInfo;
    }

    /**
     * 采购计划审批通过
     * @Title: pass
     * @param inInfo inInfo
     *      planIds : 采购计划ID集合
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pass(EiInfo inInfo) {
        return MpUtils.invoke(inInfo, "MPCG0401","pass");
    }

    /**
     * 采购计划审批驳回
     * @Title: reject
     * @param inInfo inInfo
     *      planIds : 采购计划ID集合
     *      rejectReason : 驳回原因
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo reject(EiInfo inInfo) {
        return MpUtils.invoke(inInfo, "MPCG0401","reject");
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
        List<MpApproval> mpApprovals = approvalHistoryService.queryApproval(relateId);
        inInfo.set("list", mpApprovals);
        return inInfo;
    }

    /**
     * 详情页面跳转接口
     * @Title: jumpDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo jumpDetail(EiInfo inInfo) {
        String serial = inInfo.getString("serial");
        String nid = inInfo.getString("nid");
        String code = inInfo.getString("code");
        if(NumberUtils.toint(serial, 0) >= 100) {
            inInfo.set("jumpType", "require");
            inInfo.set("planId", mpOaDockService.queryRequireNoById(nid));
        } else {
            inInfo.set("jumpType", "purchase");
            MpPurchasePlan plan = planService.queryProcurementPlan(nid);
            inInfo.set("planId", plan.getPurchaseNo());
        }
        //获取企业微信userId
        String userId = MpQyWxUtils.getUserId(code);
        inInfo.set("userId", userId);
        return inInfo;
    }
}
