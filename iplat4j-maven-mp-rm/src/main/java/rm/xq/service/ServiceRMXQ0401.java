package rm.xq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmApproval;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.service.RmApprovalHistoryService;
import com.baosight.wilp.rm.lj.service.RmRequirePlanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 需求计划审批子页面Service
 * @ClassName: ServiceRMXQ0401
 * @Package com.baosight.wilp.rm.xq.service
 * @date: 2022年09月13日 16:10
 *
 * 1.页面加载
 * 2.审批通过
 * 3.审批驳回
 */
public class ServiceRMXQ0401 extends ServiceBase {

    @Autowired
    private RmRequirePlanService requirePlanService;

    @Autowired
    private RmApprovalHistoryService approvalHistoryService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        EiInfo invoke = RmUtils.invoke(inInfo, "RMXQ0101", "initLoad");
        invoke.set("workNo", UserSession.getLoginName());
        invoke.set("name", UserSession.getLoginCName());
        return invoke;
    }

    /**
     * 审批通过
     * @Title: pass
     * @param inInfo inInfo
     *      planId : 需求计划ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pass(EiInfo inInfo) {
        return approval(inInfo, RmConstant.REQUIRE_STATUS_PASS);
    }

    /**
     * 审批驳回
     * @Title: reject
     * @param inInfo inInfo
     *     planId : 需求计划ID
     *     rejectReason : 驳回原因
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo reject(EiInfo inInfo) {
        return approval(inInfo, RmConstant.REQUIRE_STATUS_REJECT);
    }

    /**
     * 需求计划审批
     * @Title: approval
     * @param inInfo inInfo
     * @param approvalResult approvalResult
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    private EiInfo approval(EiInfo inInfo, String approvalResult) {
        String planId = inInfo.getString("planId");
        //参数校验
        if(StringUtils.isBlank(planId)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmRequirePlan plan = requirePlanService.queryRequirePlan(planId);
        if(plan == null || !plan.getStatusCode().equals(RmConstant.REQUIRE_STATUS_UN_APPROVAL)) {
            return ValidatorUtils.errorInfo("需求计划已审批或无法审批");
        }
        //更新需求计划状态
        RmRequirePlan instant = RmRequirePlan.getStatusInstant(planId, approvalResult);
        requirePlanService.updateRequirePlan(instant);
        //修改历史审批记录为过时
        approvalHistoryService.deprecated(planId);
        //保存审批履历
        RmApproval approval = RmApproval.getInstance(planId, approvalResult, instant.getStatusName(),
                RmConstant.REQUIRE_STATUS_REJECT.equals(approvalResult) ? inInfo.getString("rejectReason") : null);
        approval.setApprover(RmUtils.toString(approval.getApprover(), inInfo.getString("workNo")));
        approval.setApproverName(RmUtils.toString(approval.getApproverName(), inInfo.getString("name")));
        approval.setSignImg("/si/showSign/"+ inInfo.getString("signature"));
        approvalHistoryService.approval(approval);
        return inInfo;
    }

}
