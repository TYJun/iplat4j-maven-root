package com.baosight.wilp.rm.lj.service;

import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.domain.RmRequirePlanDetail;

import java.util.List;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 科室需求计划逻辑Service接口
 * @ClassName: RmRequirePlanService
 * @Package com.baosight.wilp.rm.lj.service
 * @date: 2022年08月31日 18:15
 * <p>
 * 1.获取需求计划集合
 * 2.根据需求计划ID获取需求计划明细集合
 * 3.根据需求计划ID获取指定需求计划
 * 4.根据需求计划单号获取指定需求计划
 * 5.新增需求计划
 * 6.编辑需求计划
 * 7.批量新增需求计划明细
 * 8.根据ID删除需求计划
 * 9.根据需求计划ID删除需求计划明细
 * 10.更新需求计划明细
 * 11.判断指定需求计划的明细是否都已汇总
 */
public interface RmRequirePlanService {

    /**
     * 获取需求计划集合
     *
     * @param require require : 需求计划对象
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmRequirePlan>
     * @throws
     * @Title: queryRequirePlanList
     **/
    List<RmRequirePlan> queryRequirePlanList(RmRequirePlan require);

    /**
     * 根据需求计划ID获取需求计划明细集合
     *
     * @param requirePlanId requirePlanId : 需求计划ID
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmRequirePlan>
     * @throws
     * @Title: queryRequirePlanDetailList
     **/
    List<RmRequirePlanDetail> queryRequirePlanDetailList(String requirePlanId);

    /**
     * 获取指定需求计划
     *
     * @param id id : 需求计划ID
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     * @Title: queryRequirePlan
     **/
    RmRequirePlan queryRequirePlan(String id);

    /**
     * 根据需求计划单号获取指定需求计划
     *
     * @param planNo planNo
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     * @Title: queryRequirePlanByPlanNo
     **/
    RmRequirePlan queryRequirePlanByPlanNo(String planNo);

    /**
     * 新增需求计划
     *
     * @param require require : 需求计划对象
     * @return void
     * @throws
     * @Title: insertRequirePlan
     **/
    void insertRequirePlan(RmRequirePlan require);

    /**
     * 编辑需求计划
     *
     * @param require require : 需求计划对象
     * @return void
     * @throws
     * @Title: updateRequirePlan
     **/
    void updateRequirePlan(RmRequirePlan require);

    /**
     * 批量新增需求计划明细
     *
     * @param detailList detailList : 需求计划明细集合
     * @return void
     * @throws
     * @Title: insertRequirePlanDetail
     **/
    void insertRequirePlanDetail(List<RmRequirePlanDetail> detailList);

    /**
     * 根据ID删除需求计划
     *
     * @param id id : 需求计划ID
     * @return void
     * @throws
     * @Title: deleteRequirePlan
     **/
    void deleteRequirePlan(String id);

    /**
     * 根据需求计划ID删除需求计划明细
     *
     * @param requirePlanId requirePlanId : 需求计划ID
     * @return void
     * @throws
     * @Title: deleteRequirePlanDetail
     **/
    void deleteRequirePlanDetail(String requirePlanId);

    /**
     * 更新需求计划明细
     *
     * @param detail detail
     * @return int
     * @throws
     * @Title: updateRequirePlanDetail
     **/
    int updateRequirePlanDetail(RmRequirePlanDetail detail);

    /**
     * 判断指定需求计划的明细是否都已汇总
     *
     * @param planId planId
     * @return boolean
     * @throws
     * @Title: hasAllCollect
     **/
    boolean hasAllCollect(String planId);
}
