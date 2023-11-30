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
import com.baosight.wilp.rm.xq.common.RmPushMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 年度需求计划管理页面Service
 * @ClassName: ServiceRMXQ01
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2022年09月09日 17:13
 *
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
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //添加科室查询条件
        Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "manageDeptNum",deptMap.get("deptNum"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "manageDeptName",deptMap.get("deptName"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreatorName",UserSession.getLoginCName());
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 20);
        inInfo.set("workNo", UserSession.getLoginName());
        inInfo.set("name", UserSession.getLoginCName());
        return query(inInfo);
    }

    /**
     * 页面数据查询(年度需求计划)
     * @Title: query
     * @param inInfo inInfo
     *    planNo: 需求计划单号
     *    planTime: 年度
     *    statusCode: 状态
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        //添加需求计划类型查询条件
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "planType", RmConstant.PLAN_TYPE_YEAR);
        return super.query(inInfo, "RMLJ01.query", new RmRequirePlan());
    }


    /**
     * 删除需求计划
     * @Title: delete
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlan(id);
        if(plan == null || !plan.getStatusCode().equals(RmConstant.REQUIRE_STATUS_NEW)) {
            return ValidatorUtils.errorInfo("需求计划已被删除或无法删除");
        }
        //删除
        requirePlanService.deleteRequirePlan(id);
        requirePlanService.deleteRequirePlanDetail(id);
        return inInfo;
    }

    /**
     * 需求计划提交
     * @Title: submit
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo submit(EiInfo inInfo) throws Exception {
        String id = inInfo.getString("id");
        //参数校验
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlan(id);
        if(plan == null || !(plan.getStatusCode().equals(RmConstant.REQUIRE_STATUS_NEW)
                || plan.getStatusCode().equals(RmConstant.REQUIRE_STATUS_REJECT))) {
            return ValidatorUtils.errorInfo("需求计划不存在或已提交");
        }
        //提交
        RmRequirePlan requirePlan = RmRequirePlan.getStatusInstant(id, RmConstant.REQUIRE_STATUS_UN_APPROVAL);
        requirePlan.setSignature("/si/showSign/" + inInfo.getString("signature"));
        requirePlanService.updateRequirePlan(requirePlan);
        //判断是否需要审批，否,自动审批
        if(RmConfigConstant.REQUIRE_APPROVAL_NO.equals(RmConfigCache.getConfigRadioValue(plan.getDataGroupCode(),
                RmConfigConstant.REQUIRE_APPROVAL))) {
            RmUtils.invoke("RMXQ0401", "pass", Arrays.asList("planId"), id);
        }
        String magerdeptNum = plan.getManageDeptNum();
        String nid = plan.getId();
        String name = plan.getRecCreatorName();
        String node = plan.getStatusName();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String timer = formatter.format(date);
        List<Map<String, String>> list =  dao.query("RMLJ04.queryXqPop",magerdeptNum);
        if (!list.isEmpty()){
            for(int i = 0; i < list.size();i++){
                String userid = list.get(i).get("userId");
                RmPushMsg.pushWxMsg(userid,name,node,timer,nid);
            }
        }

        return inInfo;
    }

    /**
     * 需求计划撤回
     * @Title: withdraw
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo withdraw(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlan(id);
        if(plan == null || !validateWithdrawStatus(plan.getStatusCode(), plan.getDataGroupCode())) {
            return ValidatorUtils.errorInfo("需求计划不存在或无法撤回");
        }
        //撤回
        requirePlanService.updateRequirePlan(RmRequirePlan.getStatusInstant(id, RmConstant.REQUIRE_STATUS_NEW));
        return inInfo;
    }

    /**
     * 校验需求单是否可以撤回
     * @Title: validateWithdrawStatus
     * @param statusCode statusCode
     * @param dataGroupCode dataGroupCode
     * @return boolean
     * @throws
     **/
    private boolean validateWithdrawStatus(String statusCode, String dataGroupCode) {
        if(RmConfigConstant.REQUIRE_APPROVAL_NO.equals(RmConfigCache.getConfigRadioValue(dataGroupCode,
                RmConfigConstant.REQUIRE_APPROVAL))) {
            //需求计划无需审批
            return RmConstant.REQUIRE_STATUS_PASS.equals(statusCode);
        } else {
            return RmConstant.REQUIRE_STATUS_UN_APPROVAL.equals(statusCode);
        }
    }
}
