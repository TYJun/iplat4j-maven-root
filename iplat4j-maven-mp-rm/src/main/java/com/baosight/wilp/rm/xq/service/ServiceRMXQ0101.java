package com.baosight.wilp.rm.xq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.*;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.domain.RmRequirePlanDetail;
import com.baosight.wilp.rm.lj.service.RmRequirePlanService;
import com.baosight.wilp.rm.pz.domain.RmConfig;
import com.baosight.wilp.rm.pz.domain.RmConfigConstant;
import com.baosight.wilp.utils.UUID;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 年度需求计划子页面Service
 * @ClassName: ServiceRMXQ0101
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2022年09月13日 10:37
 *
 * 1.页面加载
 * 2.保存年度需求计划
 */
public class ServiceRMXQ0101 extends ServiceBase {

    /**提交标记,Y=是, N=否**/
    private static final String SUBMIT_FLAG = "Y";

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
        //编辑，数据回显
        if (!RmConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(RmConstant.OPERATE_NAME))) {
            //获取需求计划
            RmRequirePlan plan = new RmRequirePlan();
            plan.fromMap(inInfo.getBlock(RmConstant.QUERY_BLOCK).getRow(0));
            List<RmRequirePlan> list = requirePlanService.queryRequirePlanList(plan);
            inInfo.setRows(RmConstant.QUERY_BLOCK, list);
            //获取需求计划明细
            List<RmRequirePlanDetail> detailList = requirePlanService.queryRequirePlanDetailList(plan.getId());
            inInfo.setRows(RmConstant.RESULT_BLOCK, detailList);
        } else {
            Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "deptNum", RmUtils.toString(deptMap.get("deptNum")));
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "deptName", RmUtils.toString(deptMap.get("deptName")));
            //复制新增
            String id = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id");
            if(StringUtils.isNotBlank(id)) {
                List<RmRequirePlanDetail> detailList = requirePlanService.queryRequirePlanDetailList(id);
                inInfo.setRows(RmConstant.RESULT_BLOCK, detailList);
            }
        }
        //像request域中添加参数
        UserSession.setOutRequestProperty(RmConstant.OPERATE_NAME, inInfo.getString(RmConstant.OPERATE_NAME));
        return inInfo;
    }

    /**
     * 保存需求计划
     * <p>
     *    1.获取参数
     *    2.参数校验
     *    3.判断是新增还是编辑
     *      3.1 新增，保存需求计划及需求计划明细
     *      3.2 编辑，更新需求计划明细,删除原先需求计划明细，保存新的需求计划明细
     * </p>
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        /**1.获取参数**/
        RmRequirePlan plan = new RmRequirePlan();
        plan.fromMap(inInfo.getBlock(RmConstant.QUERY_BLOCK).getRow(0));
        List<RmRequirePlanDetail> detailList = RmUtils.toList(inInfo.get("detailList"), RmRequirePlanDetail.class);
        //明细过滤,过滤没有数量或数量为0的
        detailList = detailList.stream().filter(detail -> detail.getNum() !=null && detail.getNum() > 0).collect(Collectors.toList());
        /**2.参数校验**/
        EiInfo validateInfo = ValidatorUtils.validateEntity(plan);
        if(validateInfo.getStatus() == -1) {
            return validateInfo;
        }
        if(CollectionUtils.isEmpty(detailList)) {
            return ValidatorUtils.errorInfo("需求计划明细不能为空或数量不能全部为0");
        }
        /**3.判断是新增还是编辑**/
        if(RmConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(RmConstant.OPERATE_NAME))) {
            /**3.1 新增,保存需求计划及需求计划明细**/
            assignPlan(plan, detailList, inInfo.getString("submitFlag"));
            requirePlanService.insertRequirePlan(plan);
        } else {
            /**3.2 编辑，更新需求计划明细,删除原先需求计划明细，保存新的需求计划明细**/
            assignDetail(plan, detailList);
            if(RmUtils.toBoolean(inInfo.getString("submitFlag"))) {
                //如果不需要审批，则状态直接为审批通过
                plan.setStatusCode(needApproval(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())) ?
                        RmConstant.REQUIRE_STATUS_UN_APPROVAL : RmConstant.REQUIRE_STATUS_PASS);
            } else {
                plan.setStatusCode(RmConstant.REQUIRE_STATUS_NEW);
            }
            plan.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.require.status", plan.getStatusCode()));
            plan.setRecRevisor(UserSession.getLoginName());
            requirePlanService.updateRequirePlan(plan);
            requirePlanService.deleteRequirePlanDetail(plan.getId());
        }
        requirePlanService.insertRequirePlanDetail(detailList);
        return inInfo;
    }

    /**
     * 需求计划赋值
     * @Title: assignPlan
     * @param plan plan : 需求计划对象
     * @param detailList detailList : 需求计划明细集合
     * @param submitFlag submitFlag : 是否提交标记(Y=是, N=否)
     * @return void
     * @throws
     **/
    private void assignPlan(RmRequirePlan plan, List<RmRequirePlanDetail> detailList, String submitFlag) {
        plan.setId(UUID.randomUUID().toString());
        //获取需求计划类型
        if(StringUtils.isBlank(plan.getPlanType())) {
            plan.setPlanType(RmConstant.PLAN_TYPE_YEAR);
            plan.setPlanTypeName(CommonUtils.getDataCodeItemName("wilp.rm.require.planType", RmConstant.PLAN_TYPE_YEAR));
        }
        //获取状态名称,判断是否直接提交
        if(SUBMIT_FLAG.equals(submitFlag)) {
            plan.setStatusCode(needApproval(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName())) ?
                    RmConstant.REQUIRE_STATUS_UN_APPROVAL : RmConstant.REQUIRE_STATUS_PASS);
        } else {
            plan.setStatusCode(RmConstant.REQUIRE_STATUS_NEW);
        }
        plan.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.require.status", plan.getStatusCode()));
        //生成需求计划单号
        if(RmConstant.PLAN_TYPE_YEAR.equals(plan.getPlanType())) {
            plan.setPlanNo(SerialNoUtils.generateSerialNo("rm_year_require", "RLY", "yyyy", 8));
        } else if (RmConstant.PLAN_TYPE_MONTH.equals(plan.getPlanType())) {
            plan.setPlanNo(SerialNoUtils.generateSerialNo("rm_month_require", "RLM", "yyyyMM", 6));
        } else {
            plan.setPlanNo(SerialNoUtils.generateSerialNo("rm_temp_require", "RLT", DateUtils.DATE8_PATTERN));
        }
        if(StringUtils.isBlank(plan.getRecCreator())) {
            plan.setRecCreator(UserSession.getLoginName());
            plan.setRecCreatorName(UserSession.getLoginCName());
        }
        plan.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        assignDetail(plan, detailList);
    }

    /**
     * 需求计划明细处理
     * @Title: assignDetail
     * @param plan plan : 需求计划对象
     * @param detailList detailList : 需求计划明细集合
     * @return void
     * @throws
     **/
    private void assignDetail(RmRequirePlan plan, List<RmRequirePlanDetail> detailList) {
        //获取总量和总金额
        detailList.forEach(detail -> {
            detail.setId(UUID.randomUUID().toString());
            detail.setPlanId(plan.getId());
            plan.setPlanNum(plan.getPlanNum() + detail.getNum());
            plan.setPlanCost(plan.getPlanCost().add(detail.getCost()));
        });
    }

    /**
     * 判断需求计划科室审批是否生效
     * @Title: needApproval
     * @param dataGroupCode dataGroupCode : 院区
     * @return boolean
     * @throws
     **/
    private boolean needApproval(String dataGroupCode) {
        String configRadioValue = RmConfigCache.getConfigRadioValue(dataGroupCode, RmConfigCache.RM_CONFIG_REQ_APPROVAL);
        return RmConstant.CONFIG_YES.equals(configRadioValue);
    }
}
