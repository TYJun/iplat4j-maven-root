package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.lj.domain.MpContractDetail;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;
import com.baosight.wilp.mp.lj.service.MpProcurementPlanService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author liangyongfei
 * @version V5.0.0
 * @Description: 采购计划审批实现类
 * @ClassName: MpProcurementPlanServiceImpl
 * @Package com.baosight.wilp.rm.lj.service.impl.impl
 * @date: 2022年08月31日 18:15
 *
 * 1.获取指定采购计划
 * 2.更新采购计划
 * 3.新增采购计划
 * 4.批量新增采购计划明细
 * 5.减少采购计划明细中的生成合同数量
 * 6.增加采购计划明细中的生成合同数量
 * 7.需改采购计划状态与采购计划明细
 * 8.判断采购计划是否足够
 * 9.根据采购计划ID查询采购计划明细
 */
@Service
public class MpProcurementPlanServiceImpl implements MpProcurementPlanService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**已生成合同数量操作类型 add=增加, reduce=减少**/
    private static final String TYPE = "add";

    /**
     * 获取指定采购计划
     * @Title: queryRequirePlan
     * @param id id : 需求计划ID
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     **/
    @Override
    public MpPurchasePlan queryProcurementPlan(String id) {
        List<MpPurchasePlan> list = dao.query("MPLJ01.query", new HashMap(2) {{put("id", id);}});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }


    /**
     * 编辑需求计划
     * @Title: updateRequirePlan
     * @param require require : 需求计划对象
     * @return void
     * @throws
     **/
    @Override
    public void updateProcurementPlan(MpPurchasePlan require) {
        require.setRecReviseTime(new Date());
        dao.update("MPLJ01.update", require);
    }

    /**
     * 新增采购计划
     * @Title: insert
     * @param plan plan
     * @return void
     * @throws
     **/
    @Override
    public void insert(MpPurchasePlan plan) {
        dao.insert("MPLJ01.insert", plan);
    }

    /**
     * 批量新增采购计划明细
     * @Title: insertDetail
     * @param list list
     * @return void
     * @throws
     **/
    @Override
    public void insertDetail(List<MpPurchasePlanDetail> list) {
        dao.insert("MPLJ01.insertDetail", list);
    }

    /**
     * 减少采购计划明细中的生成合同数量
     * @Title: reduceContNum
     * @param contractDetail contractDetail : 合同明细对象
     * @return int
     * @throws
     **/
    @Override
    public int reduceContNum(MpContractDetail contractDetail) {
        return dao.update("MPLJ01.reduceContedNum", contractDetail);
    }

    /**
     * 增加采购计划明细中的生成合同数量
     * @Title: addContNum
     * @param contractDetail contractDetail : 合同明细对象
     * @return int
     * @throws
     **/
    @Override
    public int addContNum(MpContractDetail contractDetail) {
        return dao.update("MPLJ01.addContedNum", contractDetail);
    }

    /**
     * 修改采购计划状态与采购计划明细
     * @Title: updateContNum
     * @param details details : 合同明细集合
     * @param type type : 已生成合同数量操作类型 add=增加, reduce=减少
     * @return int
     * @throws
     **/
    @Override
    public int updateContNum(List<MpContractDetail> details, String type) {
        String planId = details.get(0).getPurchasePlanId();
        //增加已生成合同数量
        if(TYPE.equals(type)) {
            details.forEach(detail -> addContNum(detail));
            updateProcurementPlan(MpPurchasePlan.getStatusInstant(planId,
                    allGenCont(planId) ? MpConstant.PROCUREMENT_STATUS_ALL_GENERATE : MpConstant.PROCUREMENT_STATUS_PART_GENERATE));
        } else {
            //减少已生成合同数量
            details.forEach(detail -> reduceContNum(detail));
            updateProcurementPlan(MpPurchasePlan.getStatusInstant(planId,
                    noneGenCont(planId) ? MpConstant.PROCUREMENT_STATUS_PASS : MpConstant.PROCUREMENT_STATUS_PART_GENERATE));
        }
        return 0;
    }

    /**
     * 采购计划是否全部生成合同
     * @Title: allGenCont
     * @param planId planId
     * @return boolean
     * @throws
     **/
    public boolean allGenCont(String planId) {
        int count = dao.count("MPLJ01.allGenCont", planId);
        return count == 0;
    }

    /**
     * 采购计划是否还未生成合同
     * @Title: noneGenCont
     * @param planId planId
     * @return boolean
     * @throws
     **/
    public boolean noneGenCont(String planId) {
        int count = dao.count("MPLJ01.noneGenCont", planId);
        return count == 0;
    }

   /**
    * 判断采购计划是否足够
    * @Title: hasEnoughNum
    * @param detail detail
    * @return boolean
    * @throws
    **/
    @Override
    public boolean hasEnoughNum(MpContractDetail detail) {
        int count = dao.count("MPLJ01.hasEnoughNum", detail);
        return count >= 0;
    }

    /**
     * 根据采购计划ID查询采购计划明细
     * @Title: queryDetailList
     * @param purchaseId purchaseId  : 采购计划ID
     * @return java.util.List<com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail>
     * @throws
     **/
    @Override
    public List<MpPurchasePlanDetail> queryDetailList(String purchaseId) {
        return dao.query("MPLJ01.queryDetail", new HashMap(2){{put("purchaseId", purchaseId);}});
    }

}
