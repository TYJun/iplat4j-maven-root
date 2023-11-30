package com.baosight.wilp.rm.ly.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.rm.common.RmConfigCache;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import com.baosight.wilp.rm.pz.domain.RmConfigConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用申请页面Service
 * @ClassName: ServiceRMLY01
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月15日 14:58
 *
 * 1.页面加载
 * 2.页面数据查询
 * 3.删除
 * 4.提交
 * 5.撤回
 * 6.签收
 * 7.结束领用单
 */
public class ServiceRMLY01 extends ServiceBase {

    @Autowired
    private RmClaimService claimService;

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
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptNum",deptMap.get("deptNum"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "deptName",deptMap.get("deptName"));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
        inInfo.set("workNo", UserSession.getLoginName());inInfo.set("name", UserSession.getLoginCName());
        RmUtils.initQueryTime(inInfo, "beginTime", "endTime");
        inInfo.addBlock(RmConstant.RESULT_BLOCK).set(EiConstant.limitStr, 20);
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
        return super.query(inInfo, "RMLJ02.query", new RmClaim());
    }

    /**
     * 删除
     * @Title: delete
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //数据校验
        if(StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo(inInfo, "参数不能为空");
        }
        RmClaim claim = claimService.queryClaimById(id);
        if(claim == null || !RmConstant.CLAIM_STATUS_NEW.equals(claim.getStatusCode())) {
            return ValidatorUtils.errorInfo(inInfo, "领用申请不存在或无法删除");
        }
        claimService.delete(id);
        claimService.deleteDetail(id);
        return inInfo;
    }

    /**
     * 提交
     * @Title: submit
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo submit(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //数据校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmClaim claim = claimService.queryClaimById(id);
        if(claim == null ||!(RmConstant.CLAIM_STATUS_NEW.equals(claim.getStatusCode())
                || RmConstant.CLAIM_STATUS_DEPT_REJECT.equals(claim.getStatusCode()))) {
            return ValidatorUtils.errorInfo(inInfo, "领用申请不存在或已提交");
        }
        //提交
        claimService.update(RmClaim.getStatusInstance(id, RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE));
        //判断是否需要科室审批，否,自动审批
        if(RmConfigConstant.CLAIM_APPROVAL_NO.equals(RmConfigCache.getConfigRadioValue(claim.getDataGroupCode(),
                RmConfigConstant.CLAIM_APPROVAL))) {
            RmUtils.invoke("RMLY0301", "pass", Arrays.asList("claimId"), id);
        }
        return inInfo;
    }

    /**
     * 撤回
     * @Title: withdraw
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo withdraw(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //数据校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmClaim claim = claimService.queryClaimById(id);
        if(claim == null || !validateWithdrawStatus(claim.getStatusCode(), claim.getDataGroupCode())) {
            return ValidatorUtils.errorInfo(inInfo, "领用申请不存在或无法撤回");
        }
        claimService.update(RmClaim.getStatusInstance(id, RmConstant.CLAIM_STATUS_NEW));
        return inInfo;
    }

    /**
     * 校验领用单是否可以撤回
     * @Title: validateWithdrawStatus
     * @param statusCode statusCode
     * @param dataGroupCode dataGroupCode
     * @return boolean
     * @throws
     **/
    private boolean validateWithdrawStatus(String statusCode, String dataGroupCode) {
        //是否开启领用申请仓库审批
        String stockApproval = RmConfigCache.getConfigRadioValue(dataGroupCode, RmConfigCache.RM_CONFIG_CLAIM_STOCK_APPROVAL);
        //是否开启领用申请科室审批
        String deptApproval = RmConfigCache.getConfigRadioValue(dataGroupCode, RmConfigCache.RM_CONFIG_CLAIM_DEPT_APPROVAL);
        if(RmUtils.toBoolean(deptApproval)) {
            //是,只能撤回待科室审批的领用申请单
            return RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE.equals(statusCode);
        } else {
            if(RmUtils.toBoolean(stockApproval)) {
                return RmConstant.CLAIM_STATUS_UN_STOCK_APPROVE.equals(statusCode);
            } else {
                return RmConstant.CLAIM_STATUS_UN_OUT.equals(statusCode);
            }
        }
    }

    /**
     * 签收(废弃)
     * @Title: signAccept
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo signAccept(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //数据校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmClaim claim = claimService.queryClaimById(id);
        if(claim == null || !RmConstant.CLAIM_STATUS_UN_SIGN.equals(claim.getStatusCode())) {
            return ValidatorUtils.errorInfo(inInfo, "领用申请不存在或无法签收");
        }

        RmClaim instance = RmClaim.getStatusInstance(id, RmConstant.CLAIM_STATUS_SIGNED);
        /*String signature = "/rm/showSign/" + inInfo.getString("signature");
        instance.setSignature(signature);*/
        claimService.update(instance);
        return inInfo;
    }

    /**
     * 结束领用单
     * @Title: over
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo over(EiInfo inInfo) {
        String id = inInfo.getString("id");
        //参数校验
        if (StringUtils.isBlank(id)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        //判断当前人是否能结束领用单
       /* RmClaim claim = claimService.queryClaimById(id);
        String workNo = UserSession.getLoginName();
        if(RmUtils.containRole(workNo, "MPCK001") && !workNo.equals(claim.getRecCreator())) {
            return ValidatorUtils.errorInfo("您无权限结束非您创建的单据");
        }*/
        claimService.update(RmClaim.getStatusInstance(id, RmConstant.CLAIM_STATUS_OVER));
        return inInfo;
    }
}
