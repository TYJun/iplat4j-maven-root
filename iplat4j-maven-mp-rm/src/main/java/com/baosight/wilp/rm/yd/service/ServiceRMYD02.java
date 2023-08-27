package com.baosight.wilp.rm.yd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.rm.common.RmConstant;
import com.baosight.wilp.rm.common.RmUtils;
import com.baosight.wilp.rm.common.ValidatorUtils;
import com.baosight.wilp.rm.lj.domain.RmClaim;
import com.baosight.wilp.rm.lj.domain.RmClaimDetail;
import com.baosight.wilp.rm.lj.service.RmClaimService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 领用管理APP接口Service
 * @ClassName: ServiceRMYD02
 * @Package com.baosight.wilp.rm.yd.service
 * @date: 2022年09月21日 14:58
 * <p>
 * 1.领用申请单列表(根据角色区分)
 * 2.根据领用单号获取指定领用单
 * 3.领用单详请
 * 4.领用申请审批通过
 * 5.领用申请审批驳回
 * 6.获取科室信息
 * 7.科室常用物资列表
 * 8.物资列表
 * 9.领用申请保存
 */
public class ServiceRMYD02 extends ServiceBase {

    /**
     * 科室领导
     **/
    public static final String ROLE = "MP002";

    /**
     * 仓库用户
     **/
    public static final String ROLE_STOCK = "MPCK002";

    @Autowired
    private RmClaimService claimService;

    /**
     * 领用申请单列表
     *
     * @param inInfo inInfo
     *               role: 角色编码字符串
     *               curDept: 当前科室编码
     *               statusCode: 状态编码(10=待审批)
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryUnApproveList
     **/
    public EiInfo queryClaimList(EiInfo inInfo) {
        String deptNum = inInfo.getString("curDept");
        String role = inInfo.getString("role");
        String statusCode = inInfo.getString("statusCode");
        if (StringUtils.isBlank(deptNum)) {
            return ValidatorUtils.errorInfo("科室参数不能为空");
        }
        //当角色不包含科室领导时,待审批工单返回空
        if (RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE.equals(statusCode) && !ROLE.equals(role)) {
            inInfo.set("list", new ArrayList<>());
        } else {
            List<RmClaim> list = dao.query("RMLJ02.query", new HashMap(4) {{
                //待审批领用单、成本归集科室领导审核
                put(RmConstant.CLAIM_STATUS_UN_DEPT_APPROVE.equals(statusCode) ? "costDeptNum" : "deptNum", deptNum);
                put("statusCode", statusCode);
            }});
            inInfo.set("list", list);
        }
        return inInfo;
    }

    /**
     * 获取领用单流程
     *
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryClaimFlow
     * claimNo: 领用单号
     **/
    public EiInfo queryClaimFlow(EiInfo inInfo) {
        String claimNo = inInfo.getString("claimNo");
        if (StringUtils.isBlank(claimNo)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        List<Map<String, String>> list = claimService.queryClaimFlow(claimNo);
        inInfo.set("claimFlow", list);
        return inInfo;
    }

    /**
     * 根据领用单号获取指定领用单
     *
     * @param inInfo inInfo
     *               claimNo: 领用单号
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryClaim
     **/
    public EiInfo queryClaim(EiInfo inInfo) {
        String claimNo = inInfo.getString("claimNo");
        if (StringUtils.isBlank(claimNo)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        RmClaim claim = claimService.queryClaimByClaimNo(claimNo);
        inInfo.set("claim", claim);
        return inInfo;
    }

    /**
     * 获取领用申请明细
     *
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryClaimDetail
     **/
    public EiInfo queryClaimDetail(EiInfo inInfo) {
        String claimId = inInfo.getString("claimId");
        if (StringUtils.isBlank(claimId)) {
            return ValidatorUtils.errorInfo("参数不能为空");
        }
        List<RmClaimDetail> details = claimService.queryClaimDetailList(claimId);
        inInfo.set("list", details);
        return inInfo;
    }

    /**
     * 领用申请审批通过
     *
     * @param inInfo inInfo
     *               claimId : 领用申请ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: pass
     **/
    public EiInfo pass(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMLY0301", "pass");
    }

    /**
     * 领用申请审批驳回
     *
     * @param inInfo inInfo
     *               claimId : 领用申请ID
     *               rejectReason : 驳回原因
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: reject
     **/
    public EiInfo reject(EiInfo inInfo) {
        String reason = inInfo.getString("rejectReason");
        if (StringUtils.isBlank(reason)) {
            return ValidatorUtils.errorInfo("驳回原因不能为空");
        }
        return RmUtils.invoke(inInfo, "RMLY0301", "reject");
    }

    /**
     * 获取科室信息
     *
     * @param inInfo inInfo
     *               workNo: 当前登陆人工号
     *               deptName: 科室名称
     *               dataGroupCode: 院区（账套）
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * deptId			:	科室ID
     * deptNum			:	科室编号
     * deptName		:	科室名称
     * workNo		    :	工号
     * name	        :	姓名
     * @throws
     * @Title: queryDept
     **/
    public EiInfo queryDept(EiInfo inInfo) {
        //调用微服务查询
        EiInfo invoke = RmUtils.invoke(inInfo, "RMTY01", "queryUserBusinessDept");
        inInfo.set("list", invoke.getBlock("userDept").getRows());
        return inInfo;
    }

    /**
     * 获取科室常用物资列表
     *
     * @param inInfo inInfo
     *               matTypeName: 物资分类名称
     *               matName: 物资名称
     *               matNum : 物资编码
     *               deptNum : 科室编码
     *               isClaim: 是否是领用
     *               page : 页码
     *               size : 每页数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryMatList
     **/
    public EiInfo queryDeptMatList(EiInfo inInfo) {
        //参数处理
        Map<String, Object> pMap = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("matTypeName", "matName", "matNum", "deptNum"));
        pMap.put("isClaim", "Y");
        //转换参数
        EiInfo info = new EiInfo();
        info.setRows(RmConstant.QUERY_BLOCK, new ArrayList() {{
            add(pMap);
        }});
        setBlockAttr(info, inInfo, "commonMat");

        //数据查询
        EiInfo invoke = RmUtils.invoke(info, "RMPZ0203", "query");
        inInfo.set("list", invoke.getBlock("commonMat").getRows());
        return inInfo;
    }

    /**
     * 获取物资列表
     *
     * @param inInfo inInfo
     *               matTypeName: 物资分类名称
     *               matName: 物资名称
     *               matNum : 物资编码
     *               isClaim: 是否是领用
     *               dataGroupCode : 院区（账套）
     *               page : 页码
     *               size : 每页数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: queryMatList
     **/
    public EiInfo queryMatList(EiInfo inInfo) {
        //参数处理
        Map<String, Object> pMap = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("matTypeName", "matName", "matNum", "dataGroupCode"));
        pMap.put("isClaim", "Y");
        //转换参数
        EiInfo info = new EiInfo();
        info.setRows(RmConstant.QUERY_BLOCK, new ArrayList() {{
            add(pMap);
        }});
        setBlockAttr(info, inInfo, "mat");

        //数据啊查询
        EiInfo invoke = RmUtils.invoke(info, "RMLY0103", "query");
        inInfo.set("list", invoke.getBlock("mat").getRows());
        return inInfo;
    }

    /**
     * 构建block的分页参数
     *
     * @param info    info : 服务调用EiInfo
     * @param inInfo  inInfo : 参数调用EiInfo
     * @param blockId blockId : block块的id
     * @return void
     * @throws
     * @Title: setBlockAttr
     **/
    private void setBlockAttr(EiInfo info, EiInfo inInfo, String blockId) {
        //分页参数处理
        EiBlock block = new EiBlock(blockId);
        int page = NumberUtils.toint(inInfo.getString("page"), 1);
        int size = NumberUtils.toint(inInfo.getString("size"), 1000);
        block.setAttr(new HashMap(8) {{
            put("offset", (page - 1) * size);
            put("limit", size);
            put("count", 0);
            put("orderBy", "");
            put("showCount", "false");
        }});
        info.addBlock(block);
    }

    /**
     * 保存(并提交)领用申请
     *
     * @param inInfo inInfo
     *               submitFlag: 提交标记, N=保存, Y=保存并提交
     *               deptNum: 当前登录科室编码
     *               deptName: 当前登录科室名称
     *               costDeptNum: 成本归集科室编码
     *               costDeptName: 成本归集科室名称
     *               remark: 备注
     *               dataGroupCode: 院区（账套）
     *               recCreator: 创建人工号
     *               recCreatorName: 创建人姓名
     *               recRevisor: 修改人工号
     *               detailList:
     *               matNum: 物资编码
     *               matName: 物资名称
     *               matTypeId: 物资分类ID
     *               matTypeName: 物资分类名称
     *               matSpec: 物资规格
     *               matModel: 物资型号
     *               unit: 计量单位
     *               price: 单价
     *               num: 领用数量
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: save
     **/
    public EiInfo save(EiInfo inInfo) {
        //参数处理
        List<String> paramNameList = Arrays.asList("submitFlag", "deptNum", "deptName", "costDeptNum", "costDeptName",
                "remark", "dataGroupCode", "recCreator", "recCreatorName", "recRevisor", "id");
        Map<String, Object> pMap = CommonUtils.buildParamterNoPage(inInfo, paramNameList);
        inInfo.setRows(RmConstant.QUERY_BLOCK, new ArrayList() {{
            add(pMap);
        }});
        //保存
        return RmUtils.invoke(inInfo, "RMLY0101", "save");
    }

    /**
     * 删除领用申请
     *
     * @param inInfo inInfo
     *               id: 领用申请ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: delete
     **/
    @Override
    public EiInfo delete(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMLY01", "delete");
    }

    /**
     * 提交领用申请
     *
     * @param inInfo inInfo
     *               id: 领用申请ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: submit
     **/
    public EiInfo submit(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMLY01", "submit");
    }

    /**
     * 撤回领用单
     *
     * @param inInfo inInfo
     *               id: 领用申请ID
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: withdraw
     **/
    public EiInfo withdraw(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMLY01", "withdraw");
    }

    /**
     * 验收确认
     *
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     * @Title: confirmClaim
     * id: 领用申请ID
     **/
    public EiInfo confirmClaim(EiInfo inInfo) {
        return RmUtils.invoke(inInfo, "RMLY01", "signAccept");
    }

}
