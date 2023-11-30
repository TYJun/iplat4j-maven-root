package com.baosight.wilp.rm.ly.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmApproval;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmApprovalHistoryService;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用仓库审批子页面Service
 * @ClassName: ServiceRMLY0202
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月16日 16:39
 *
 * 1.页面加载
 * 2.审批通过
 * 3.审批驳回
 */
public class ServiceRMLY0202 extends ServiceBase {

    @Autowired
    private RmClaimService claimService;

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
        //获取领用申请
        RmClaim claim = new RmClaim();
        claim.fromMap(inInfo.getBlock(RmConstant.QUERY_BLOCK).getRow(0));
        inInfo.setRows(RmConstant.QUERY_BLOCK, claimService.queryClaimList(claim, null, null));
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimId", claim.getId());
        return inInfo;
    }

    /**
     * 页面表格数据查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        EiInfo outInfo = super.query(inInfo, "RMLJ02.queryDetail", new RmClaimDetail());
        //处理库存量、处理预约量
        RmUtils.assignNum(outInfo.getBlock(RmConstant.RESULT_BLOCK).getRows(), claimService);
        return outInfo;
    }

    /**
     * 审批通过
     * @Title: pass
     * @param inInfo inInfo
     *      claimId : 领用申请ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo pass(EiInfo inInfo) {
        return approval(inInfo, RmConstant.CLAIM_STATUS_UN_OUT);
    }

    /**
     * 审批驳回
     * @Title: reject
     * @param inInfo inInfo
     *     claimId : 领用申请ID
     *     rejectReason : 驳回原因
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo reject(EiInfo inInfo) {
        return approval(inInfo, RmConstant.CLAIM_STATUS_STOCK_REJECT);
    }

    /**
     * 领用申请科室审批
     * @Title: approval
     * @param inInfo inInfo
     * @param approvalResult approvalResult
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    private EiInfo approval(EiInfo inInfo, String approvalResult) {
        String claimId = inInfo.getString("claimId");
        //参数校验
        if(StringUtils.isBlank(claimId)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmClaim claim = claimService.queryClaimById(claimId);
        if(claim == null || !claim.getStatusCode().equals(RmConstant.CLAIM_STATUS_UN_STOCK_APPROVE)) {
            return ValidatorUtils.errorInfo("领用已审批或无法审批");
        }
        //更新领用申请状态
        RmClaim instant = RmClaim.getStatusInstance(claimId, approvalResult);
        claimService.update(instant);
        //保存审批履历
        RmApproval approval = RmApproval.getInstance(claimId, approvalResult, instant.getStatusName(),
                RmConstant.CLAIM_STATUS_STOCK_REJECT.equals(approvalResult) ? inInfo.getString("rejectReason") : null);
        approvalHistoryService.approval(approval);
        return inInfo;
    }

    /**
     * 领用明细单挑驳回
     * @Title: rejectDetail
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo rejectDetail(EiInfo inInfo) {
        //获取参数
        String claimId = inInfo.getString("claimId");
        List<RmClaimDetail> detailList = RmUtils.toList(inInfo.get("list"), RmClaimDetail.class);
        //参数校验
        if(CollectionUtils.isEmpty(detailList)) {
            return ValidatorUtils.errorInfo("驳回物资明细不能为空");
        }
        //获取领用单和领用明细
        List<String> matNums = detailList.stream().map(d -> d.getMatNum()).collect(Collectors.toList());
        RmClaim claim = claimService.queryClaimById(claimId);
        List<RmClaimDetail> rejectList = claimService.queryPartClaimDetailList(claimId, matNums);
        if(rejectList.isEmpty()) { return inInfo; }
        //数据校验
        if(rejectList.stream().anyMatch(d -> d.getNum().compareTo(d.getOutNum()) <= 0)){
            return ValidatorUtils.errorInfo("已全部出库的物资无法驳回");
        }
        //修改领用明细状态
        claimService.rejectDetail(claimId, detailList);
        //构建并保存新的驳回领用单
        buildNewClaim(claim, rejectList, detailList);
        claimService.insert(claim);
        claimService.insertDetail(rejectList);
        //修改领用单状态
        updateClaimStatus(claimId);
        return inInfo;
    }

    /**
     * 构建新的领用单
     * @Title: buildNewClaim
     * @param claim claim 领用主单据
     * @param rejectList rejectList 领用明细
     * @return void
     * @throws
     **/
    private void buildNewClaim(RmClaim claim, List<RmClaimDetail> rejectList, List<RmClaimDetail> detailList) {
        claim.setId(UUID.randomUUID().toString());
        claim.setClaimNo(claimService.getRejectClaimNo(claim.getClaimNo()));
        claim.setStatusCode(RmConstant.CLAIM_STATUS_STOCK_REJECT);
        claim.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.claim.status", claim.getStatusCode()));
        claim.setOutNum(0d);
        claim.setClaimNum(0d);
        for (int i = 0; i < rejectList.size(); i++) {
            RmClaimDetail detail = rejectList.get(i);
            RmClaimDetail cDetail = detailList.stream().filter(d -> d.getMatNum().equals(detail.getMatNum())).findFirst().orElse(null);
            detail.setId(UUID.randomUUID().toString());
            detail.setClaimId(claim.getId());
            detail.setNum(RmUtils.doubleSub(detail.getNum(), detail.getOutNum()));
            detail.calClaimAmount(detail.getPrice(), detail.getNum());
            detail.setOutNum(0d);
            detail.setSortNo(i);
            if (cDetail != null) {
                detail.setReturnReason(cDetail.getReturnReason());
            }
            claim.setClaimNum(claim.getClaimNum() + detail.getNum());
        }
    }

    /**
     * 更新领用单状态
     * @Title: updateClaimStatus
     * @param claimId claimId
     * @return void
     * @throws
     **/
    private void updateClaimStatus(String claimId) {
        List<RmClaimDetail> detailList = claimService.queryClaimDetailList(claimId);
        boolean match = detailList.stream().anyMatch(d -> "未驳回".equals(d.getStatus()));
        if(match) {
            boolean b = detailList.stream().filter(d -> "未驳回".equals(d.getStatus()))
                    .anyMatch(d -> d.getNum().compareTo(d.getOutNum()) > 0);
            if(!b) {
                claimService.update(RmClaim.getStatusInstance(claimId, RmConstant.CLAIM_STATUS_STOCK_CONFRIM));
            }
        } else {
            claimService.update(RmClaim.getStatusInstance(claimId, RmConstant.CLAIM_STATUS_OVER));
        }

    }
}
