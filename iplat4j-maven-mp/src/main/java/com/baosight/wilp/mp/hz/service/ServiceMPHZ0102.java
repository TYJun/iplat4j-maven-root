package com.baosight.wilp.mp.hz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.common.SerialNoUtils;
import com.baosight.wilp.mp.common.ValidatorUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;
import com.baosight.wilp.mp.lj.domain.MpRequireCollect;
import com.baosight.wilp.mp.lj.domain.MpRequireCollectDetail;
import com.baosight.wilp.mp.lj.service.MpProcurementPlanService;
import com.baosight.wilp.mp.lj.service.MpRequireCollectService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 生成采购计划子页面Service
 * @ClassName: ServiceMPHZ0102
 * @Package com.baosight.wilp.mp.hz.service
 * @date: 2022年10月18日 17:02
 *
 * 1.页面加载
 * 2.生成采购计划
 */
public class ServiceMPHZ0102 extends ServiceBase {

    @Autowired
    private MpRequireCollectService collectService;

    @Autowired
    private MpProcurementPlanService purchasePlanService;

    /**
     * 页面加载
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        String id = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "id");
        //主单据处理
        MpRequireCollect collect = collectService.queryRequireCollect(id);
        inInfo.setRows(MpConstant.QUERY_BLOCK, new ArrayList(){{ add(collect); }});
        inInfo.setCell(MpConstant.QUERY_BLOCK, 0, "collectDate", collect.getRecCreateTimeStr().substring(0,10));
        //汇总明细表格赋值
        List<MpRequireCollectDetail> details = collectService.queryDetailList(id);
        inInfo.setRows(MpConstant.RESULT_BLOCK, details);
        return inInfo;
    }

    /**
     * 生成采购计划
     * @Title: genPurchasePlan
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    public EiInfo genPurchasePlan(EiInfo inInfo) {
        //参数处理
        Map<String, String> row = inInfo.getRow(MpConstant.QUERY_BLOCK, 0);
        List<MpPurchasePlanDetail> details = MpUtils.toList(inInfo.get("list"), MpPurchasePlanDetail.class);
        //参数校验
        details = details.stream().filter(detail -> detail.getNum() !=null && detail.getNum() > 0).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(details)) {
            return ValidatorUtils.errorInfo("采购计划明细不能为空或数量不能全部为0");
        }
        //构建采购计划对象和完善采购计划明细
        MpPurchasePlan plan = buildPurchase(details, row);
        //保存采购计划及明细
        purchasePlanService.insert(plan);
        purchasePlanService.insertDetail(plan.getDetails());
        //修改需求计划汇总单状态
        collectService.update(MpRequireCollect.getStatusInstance(row.get("id"), "10"));
        return inInfo;
    }

    /**
     * 构建采购计划对象及完善采购计划明细
     * @Title: buildPurchase
     * @param details details
     * @param row row
     * @return com.baosight.wilp.mp.lj.domain.MpPurchasePlan
     * @throws
     **/
    private MpPurchasePlan buildPurchase(List<MpPurchasePlanDetail> details, Map<String, String> row) {
        //构建采购计划主单据
        MpPurchasePlan plan = new MpPurchasePlan();
        plan.setId(UUID.randomUUID().toString());
        plan.setResourceId(row.get("id"));
        plan.setPurchaseNo(SerialNoUtils.generateNumberSerialNo("purchase_mp_cg", "MPCG", 4));
        plan.setStatusCode(MpConstant.PROCUREMENT_STATUS_NEW);
        plan.setStatusName(CommonUtils.getDataCodeItemName("wilp.mp.purchase.planStatus", plan.getStatusCode()));
        plan.setDeptNum(row.get("deptNum"));
        plan.setDeptName(row.get("deptName"));
        plan.setRecCreator(UserSession.getLoginName());
        plan.setRecCreatorName(UserSession.getLoginCName());
        plan.setRecCreateTime(new Date());
        plan.setDataGroupCode(BaseDockingUtils.getUserGroupByWorkNo(UserSession.getLoginName()));
        //明细处理
        details.forEach(detail -> {
            detail.setId(UUID.randomUUID().toString());
            detail.setPurchaseId(plan.getId());
            plan.setPurchaseNum(MpUtils.doubleAdd(plan.getPurchaseNum(), detail.getNum()));
            Double cost = MpUtils.doubleMult(detail.getNum(), detail.getPrice());
            plan.setPurchaseCost(plan.getPurchaseCost().add(new BigDecimal(cost)));
        });
        plan.setDetails(details);
        return plan;
    }

}
