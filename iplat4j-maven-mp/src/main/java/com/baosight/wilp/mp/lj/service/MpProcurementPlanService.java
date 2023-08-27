package com.baosight.wilp.mp.lj.service;


import com.baosight.wilp.mp.lj.domain.MpContractDetail;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;

import java.util.List;

/**
 * @author lyf
 * @version V5.0.0
 * @Description: 采购计划审批
 * @ClassName: MpProcurementPlanService
 * @Package com.baosight.wilp.rm.lj.service
 * @date: 2022年08月31日 18:15
 *
 * 1.获取指定采购计划
 * 2.更新采购计划
 * 3.新增采购计划
 * 4.批量新增采购计划明细
 * 5.减少采购计划明细中的生成合同数量
 * 6.减少采购计划明细中的生成合同数量
 * 7.需改采购计划状态与采购计划明细
 * 8.判断采购计划是否足够
 * 9.根据采购计划ID查询采购计划明细
 */
public interface MpProcurementPlanService {

    /**
     * 获取指定采购计划
     * @Title: queryRequirePlan
     * @param id id : 需求计划ID
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     **/
    MpPurchasePlan  queryProcurementPlan (String id);

    /**
     * 编辑采购计划
     * @Title: updateRequirePlan
     * @param require require : 需求计划对象
     * @return void
     * @throws
     **/

    void updateProcurementPlan(MpPurchasePlan require);

    /**
     * 新增采购计划
     * @Title: insert
     * @param plan plan : 采购计划对象
     * @return void
     * @throws
     **/
    void insert(MpPurchasePlan plan);

    /**
     * 批量新增采购计划明细
     * @Title: insertDetail
     * @param list list : 采购计划明细集合
     * @return void
     * @throws
     **/
    void insertDetail(List<MpPurchasePlanDetail> list);

    /**
     * 减少采购计划明细中的生成合同数量
     * @Title: reduceContNum
     * @param contractDetail contractDetail : 合同明细对象
     * @return int
     * @throws
     **/
    int reduceContNum(MpContractDetail contractDetail);

    /**
     * 增加采购计划明细中的生成合同数量
     * @Title: addContNum
     * @param contractDetail contractDetail : 合同明细对象
     * @return int
     * @throws
     **/
    int addContNum(MpContractDetail contractDetail);

    /**
     * 需改采购计划状态与采购计划明细
     * @param details details : 合同明细集合
     * @param type type : 已生成合同数量操作类型
     * @return int
     * @throws
     **/
    int updateContNum(List<MpContractDetail> details, String type);

   /**
    * 判断采购计划是否足够
    * @Title: hasEnoughNum
    * @param detail detail : 合同明细对象
    * @return boolean
    * @throws
    **/
    boolean hasEnoughNum(MpContractDetail detail);

    /**
     * 根据采购计划ID查询采购计划明细
     * @Title: queryDetailList
     * @param purchaseId purchaseId : 采购计划ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail>
     * @throws
     **/
    List<MpPurchasePlanDetail> queryDetailList(String purchaseId);
}
