package rm.lj.service;

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
 *
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
     * @Title: queryRequirePlanList
     * @param require require : 需求计划对象
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmRequirePlan>
     * @throws
     **/
    List<RmRequirePlan>  queryRequirePlanList(RmRequirePlan require);

    /**
     * 根据需求计划ID获取需求计划明细集合
     * @Title: queryRequirePlanDetailList
     * @param requirePlanId requirePlanId : 需求计划ID
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmRequirePlan>
     * @throws
     **/
    List<RmRequirePlanDetail>  queryRequirePlanDetailList(String requirePlanId);

    /**
     * 获取指定需求计划
     * @Title: queryRequirePlan
     * @param id id : 需求计划ID
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     **/
    RmRequirePlan queryRequirePlan(String id);

    /**
     * 根据需求计划单号获取指定需求计划
     * @Title: queryRequirePlanByPlanNo
     * @param planNo planNo
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     **/
    RmRequirePlan queryRequirePlanByPlanNo(String planNo);

    /**
     * 新增需求计划
     * @Title: insertRequirePlan
     * @param require require : 需求计划对象
     * @return void
     * @throws
     **/
    void insertRequirePlan(RmRequirePlan require);

    /**
     * 编辑需求计划
     * @Title: updateRequirePlan
     * @param require require : 需求计划对象
     * @return void
     * @throws
     **/
    void updateRequirePlan(RmRequirePlan require);

    /**
     * 批量新增需求计划明细
     * @Title: insertRequirePlanDetail
     * @param detailList detailList : 需求计划明细集合
     * @return void
     * @throws
     **/
    void insertRequirePlanDetail(List<RmRequirePlanDetail> detailList);

    /**
     * 根据ID删除需求计划
     * @Title: deleteRequirePlan
     * @param id id : 需求计划ID
     * @return void
     * @throws
     **/
    void deleteRequirePlan(String id);

    /**
     * 根据需求计划ID删除需求计划明细
     * @Title: deleteRequirePlanDetail
     * @param requirePlanId requirePlanId : 需求计划ID
     * @return void
     * @throws
     **/
    void deleteRequirePlanDetail(String requirePlanId);

    /**
     * 更新需求计划明细
     * @Title: updateRequirePlanDetailStatus
     * @param detail detail
     * @return int
     * @throws
     **/
    int updateRequirePlanDetailStatus(RmRequirePlanDetail detail);

    /**
     * 判断指定需求计划的明细是否都已汇总
     * @Title: hasAllCollect
     * @param planId planId
     * @return boolean
     * @throws
     **/
    boolean hasAllCollect(String planId);

    /**
     * 更新需求计划状态(部分汇总或未汇总)
     * @Title: updateRequirePlanUnFinish
     * @param join join
     * @return void
     * @throws
     **/
    int updateRequirePlanUnFinish(String join);

    /**
     * 更新需求计划状态(部分汇总或已汇总)
     * @Title: updateRequirePlanFinish
     * @param join join
     * @return int
     * @throws
     **/
    int updateRequirePlanFinish(String join);

    /**
     * 需求计划明细分配
     * @Title: insertRequirePlanAllot
     * @param detailList detailList 需求计划明细集合
     * @return void
     * @throws
     **/
    void insertRequirePlanAllot(List<RmRequirePlanDetail> detailList);

    /**
     * 需求计划明细退回
     * @Title: updateRequirePlanAllot
     * @param backDetail backDetail 需求计划明细
     * @return void
     * @throws
     **/
    void updateRequirePlanAllot(RmRequirePlanDetail backDetail);
}
