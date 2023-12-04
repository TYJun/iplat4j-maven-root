package rm.ly.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.*;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用申请子页面Service
 * @ClassName: ServiceRMLY0101
 * @Package com.baosight.wilp.rm.ly.service
 * @date: 2022年09月15日 14:58
 *
 * 1.页面加载
 * 2.查询领用申请名称
 * 3.保存领用申请
 */
public class ServiceRMLY0101 extends ServiceBase {

    @Autowired
    private RmClaimService claimService;

    private static final String OPERATE_TYPE_EDIT = "edit";

    /**提交标记,Y=是, N=否**/
    private static final String SUBMIT_FLAG = "Y";

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        //编辑,数据回显
        if (!RmConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(RmConstant.OPERATE_NAME))) {
            //获取领用申请
            RmClaim claim = new RmClaim();
            claim.fromMap(inInfo.getBlock(RmConstant.QUERY_BLOCK).getRow(0));
            inInfo.setRows(RmConstant.QUERY_BLOCK, claimService.queryClaimList(claim, null, null));
            //获取领用明细
            if(OPERATE_TYPE_EDIT.equals(inInfo.getString(RmConstant.OPERATE_NAME))) {
                inInfo.setRows(RmConstant.RESULT_BLOCK, claimService.queryClaimDetailList(claim.getId()));
            } else {
                inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimId", claim.getId());
                EiInfo outInfo = query(inInfo);
                inInfo.setBlock(outInfo.getBlock(RmConstant.RESULT_BLOCK));
            }
        } else {
            Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "deptNum", RmUtils.toString(deptMap.get("deptNum")));
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "deptName", RmUtils.toString(deptMap.get("deptName")));
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "applyUserNo", UserSession.getLoginName());
            inInfo.setCell(RmConstant.QUERY_BLOCK,0, "applyUserName", UserSession.getLoginCName());
            //复制新增
            String id = inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id");
            if(StringUtils.isNotBlank(id)) {
                inInfo.setRows(RmConstant.RESULT_BLOCK, claimService.queryClaimDetailList(id));
            }
        }
        UserSession.setOutRequestProperty(RmConstant.OPERATE_NAME, inInfo.getString(RmConstant.OPERATE_NAME));
        return inInfo;
    }

    /**
     * 领用明细查询
     * @Title: query
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo query(EiInfo inInfo) {
        inInfo.setCell(RmConstant.QUERY_BLOCK, 0, "claimId", inInfo.getCellStr(RmConstant.QUERY_BLOCK, 0, "id"));
        return super.query(inInfo,"RMLJ02.queryDetail", new RmClaimDetail());
    }

    /**
     * 保存领用申请
     * <p>
     *    1.获取参数
     *    2.参数校验
     *    3.判断是新增还是编辑
     *      3.1 新增，保存领用申请及领用申请明细
     *      3.2 编辑，更新领用申请,删除原先领用申请明细，保存新的领用申请明细
     * </p>
     * @Title: save
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo save(EiInfo inInfo) {
        /**1.获取参数**/
        RmClaim claim = new RmClaim();
        claim.fromMap(inInfo.getBlock(RmConstant.QUERY_BLOCK).getRow(0));
        List<RmClaimDetail> detailList = RmUtils.toList(inInfo.get("detailList"), RmClaimDetail.class);
        //过滤数量为空或为0的
        detailList = detailList.stream().filter(detail -> detail.getNum() !=null && detail.getNum() > 0).collect(Collectors.toList());
        /**2.数据校验**/
        if(CollectionUtils.isEmpty(detailList)) {
            return ValidatorUtils.errorInfo("申请明细不能为空或数量不能全部为0");
        }
        /**2.1校验成本科室是否有未签收出库单**/
        String dataGroupCode = RmUtils.toString(inInfo.getString("dataGroupCode"), BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        if(RmUtils.toBoolean(RmConfigCache.getConfigRadioValue(dataGroupCode, RmConfigCache.RM_CONFIG_CLAIM_LIMIT))
                && hasUnChecked(claim.getCostDeptNum())) {
            String claimLimit = inInfo.getString("claimLimit");
            if(StringUtils.isBlank(claimLimit) || RmUtils.toBoolean(claimLimit)) {
                return ValidatorUtils.errorInfo(claim.getCostDeptName()+"还有未签收单据,请先签收再申领");
            }
        }

        /**2.2校验物资是否停用**/
        EiInfo isStopEiInfo = matIsStop(detailList);
        if(isStopEiInfo.getStatus() == -1) { return isStopEiInfo; }
        /**3.判断是新增还是编辑**/
        String submitFlag = inInfo.getString("submitFlag");
        if(RmConstant.OPERATE_TYPE_ADD.equals(inInfo.getString(RmConstant.OPERATE_NAME))) {
            /**3.1 新增,保存领用申请及申请明细**/
            assignClaim(claim, detailList, submitFlag);
            claim.setRecCreator(UserSession.getLoginName());
            claim.setRecCreatorName(UserSession.getLoginCName());
            claim.setDataGroupCode(dataGroupCode);
            claimService.insert(claim);
        } else {
            /**3.2 编辑，更新领用申请,删除原先领用申请明细，保存新的领用申请明细**/
            if(SUBMIT_FLAG.equals(submitFlag)) {
                claim.setStatusCode(RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE);
            } else {
                claim.setStatusCode(RmConstant.CLAIM_STATUS_NEW);
            }
            claim.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.claim.status", claim.getStatusCode()));
            assignClaimDetail(claim, detailList);
            claim.setRecRevisor(RmUtils.toString(inInfo.getString("recRevisor"), UserSession.getLoginName()));
            claimService.update(claim);
            claimService.deleteDetail(claim.getId());
        }
        claimService.insertDetail(detailList);
        /**4.检查是否需要科室审批,不需要，则自动审批**/
        String radioValue = RmConfigCache.getConfigRadioValue(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()),
                RmConfigCache.RM_CONFIG_CLAIM_DEPT_APPROVAL);
        if(!RmUtils.toBoolean(radioValue) && RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE.equals(claim.getStatusCode())) {
            //自动审批
            RmUtils.invoke("RMLY0301", "pass", Arrays.asList("claimId"), claim.getId());
        }
        inInfo.set("claimId", claim.getId());
        return inInfo;
    }

    /**
     * 领用单赋值
     * @Title: assignClaim
     * @param claim claim
     * @param detailList detailList
     * @param submitFlag submitFlag
     * @return void
     * @throws
     **/
    private void assignClaim(RmClaim claim, List<RmClaimDetail> detailList, String submitFlag) {
        claim.setId(UUID.randomUUID().toString());
        claim.setClaimNo(SerialNoUtils.generateNumberSerialNo("rm_claim","RMU"));
        if(StringUtils.isBlank(claim.getStatusCode())) {
            if(SUBMIT_FLAG.equals(submitFlag)) {
                claim.setStatusCode(RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE);
            } else {
                claim.setStatusCode(RmConstant.CLAIM_STATUS_NEW);
            }
            claim.setStatusName(CommonUtils.getDataCodeItemName("wilp.rm.claim.status", claim.getStatusCode()));
        }
        assignClaimDetail(claim, detailList);
    }

    /**
     * 领用单明细赋值
     * @Title: assignClaimDetail
     * @param claim claim
     * @param detailList detailList
     * @return void
     * @throws
     **/
    private void assignClaimDetail(RmClaim claim, List<RmClaimDetail> detailList) {
        for (int i = 0; i < detailList.size(); i++) {
            RmClaimDetail detail = detailList.get(i);
            detail.setId(UUID.randomUUID().toString());
            detail.setClaimId(claim.getId());
            detail.calClaimAmount(detail.getPrice(), detail.getNum());
            detail.setOutNum(0d);
            detail.setSortNo(i);
            detail.setReturnReason("");
            claim.setClaimNum(claim.getClaimNum() + detail.getNum());
        }
    }

    /**
     * 校验物资是否停用
     * @Title: matIsStop
     * @param detailList detailList
     * @return boolean
     * @throws
     **/
    private EiInfo matIsStop(List<RmClaimDetail> detailList) {
        List<String> errorMsg = new ArrayList<>();
        for (RmClaimDetail detail : detailList) {
            if(isMatStop(detail.getMatNum())) {
                errorMsg.add(detail.getMatName()+"("+detail.getMatNum()+")");
            }
        }
        EiInfo info = new EiInfo();
        if(CollectionUtils.isNotEmpty(errorMsg)) {
            info.setStatus(-1);
            info.setMsg(StringUtils.join(errorMsg, "、")+"已被停用，请选择其他相同物资或联系仓库人员");
        }
        return info;
    }

    /**
     * 判断物资是否被停用
     * @Title: isMatStop
     * @param matNum matNum
     * @return boolean
     * @throws
     **/
    private boolean isMatStop(String matNum) {
        EiInfo info = RmUtils.invoke("RMTY01", "isMatStop", Arrays.asList("matNum"), matNum);
        return !RmUtils.toBoolean(info.get("isNotStop"));
    }

    /**
     * 判断指定科室是否有待签收的单子
     * @Title: hasUnChecked
     * @param costDeptNum costDeptNum
     * @return boolean
     * @throws
     **/
    private boolean hasUnChecked(String costDeptNum) {
        EiInfo info = new EiInfo();
        info.setCell(EiConstant.queryBlock, 0, "costDeptNum", costDeptNum);
        EiInfo invoke = RmUtils.invoke(info, "SICK04", "query");
        return invoke.getStatus() > -1 && invoke.getRow(RmConstant.RESULT_BLOCK, 0) != null;
    }

    /**
     * 判断是否有未签收出库单
     * @Title: hasUnSignClaim
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo hasUnSignClaim(EiInfo inInfo) {
        EiInfo invoke = RmUtils.invoke(new EiInfo(), "SICK04", "query");
        if(invoke.getStatus() == -1 || invoke.getRow(RmConstant.RESULT_BLOCK, 0) == null) {
            inInfo.set("unSign", false);
        } else {inInfo.set("unSign", true); }
        return inInfo;
    }


}



