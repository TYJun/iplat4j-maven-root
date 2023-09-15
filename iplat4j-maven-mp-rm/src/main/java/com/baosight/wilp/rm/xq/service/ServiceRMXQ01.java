package com.baosight.wilp.rm.xq.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConfigCache;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.service.RmRequirePlanService;
import com.baosight.wilp.rm.pz.domain.RmConfigConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 年度需求计划管理页面Service
 * @ClassName: ServiceRMXQ01
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2022年09月09日 17:13
 * <p>
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除需求计划
 * 4.需求计划提交
 * 5.需求计划撤回
 */
public class ServiceRMXQ01 extends ServiceBase {

    @Autowired
    private RmRequirePlanService requirePlanService;

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
     * 页面数据查询(年度需求计划)
     *
     * @param inInfo inInfo
     *               planNo: 需求计划单号
     *               planTime: 年度
     *               statusCode: 状态
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: query
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        //添加需求计划类型查询条件
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "planType", RmConstant.PLAN_TYPE_YEAR);
        return super.query(inInfo, "RMLJ01.query", new RmRequirePlan());
    }


    /**
     * 删除需求计划
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: delete
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlan(id);
        if (plan == null || !plan.getStatusCode().equals(RmConstant.REQUIRE_STATUS_NEW)) {
            return ValidatorUtils.errorInfo("需求计划已被删除或无法删除");
        }
        //删除
        requirePlanService.deleteRequirePlan(id);
        requirePlanService.deleteRequirePlanDetail(id);
        return inInfo;
    }

    /**
     * 需求计划提交
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: submit
     **/
    public EiInfo submit(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlan(id);
        if (plan == null || !(plan.getStatusCode().equals(RmConstant.REQUIRE_STATUS_NEW)
                || plan.getStatusCode().equals(RmConstant.REQUIRE_STATUS_REJECT))) {
            return ValidatorUtils.errorInfo("需求计划不存在或已提交");
        }
        //提交
        requirePlanService.updateRequirePlan(RmRequirePlan.getStatusInstant(id, RmConstant.REQUIRE_STATUS_UN_APPROVAL));
        //判断是否需要审批，否,自动审批
        if (RmConfigConstant.REQUIRE_APPROVAL_NO.equals(RmConfigCache.getConfigRadioValue(plan.getDataGroupCode(),
                RmConfigConstant.REQUIRE_APPROVAL))) {
            RmUtils.invoke("RMXQ0401", "pass", Arrays.asList("planId"), id);
        }
        return inInfo;
    }

    /**
     * 需求计划撤回
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: withdraw
     **/
    public EiInfo withdraw(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlan(id);
        if (plan == null || !validateWithdrawStatus(plan.getStatusCode(), plan.getDataGroupCode())) {
            return ValidatorUtils.errorInfo("需求计划不存在或无法撤回");
        }
        //撤回
        requirePlanService.updateRequirePlan(RmRequirePlan.getStatusInstant(id, RmConstant.REQUIRE_STATUS_NEW));
        return inInfo;
    }

    /**
     * 校验需求单是否可以撤回
     *
     * @param statusCode    statusCode
     * @param dataGroupCode dataGroupCode
     * @return boolean
     * @throws
     * @Title: validateWithdrawStatus
     **/
    private boolean validateWithdrawStatus(String statusCode, String dataGroupCode) {
        if (RmConfigConstant.REQUIRE_APPROVAL_NO.equals(RmConfigCache.getConfigRadioValue(dataGroupCode,
                RmConfigConstant.REQUIRE_APPROVAL))) {
            //需求计划无需审批
            return RmConstant.REQUIRE_STATUS_PASS.equals(statusCode);
        } else {
            return RmConstant.REQUIRE_STATUS_UN_APPROVAL.equals(statusCode);
        }
    }
}
