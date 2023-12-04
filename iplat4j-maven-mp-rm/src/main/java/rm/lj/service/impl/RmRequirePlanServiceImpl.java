package rm.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.rm.lj.domain.RmRequirePlan;
import com.baosight.wilp.rm.lj.domain.RmRequirePlanDetail;
import com.baosight.wilp.rm.lj.service.RmRequirePlanService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 科室需求计划逻辑Service实现类
 * @ClassName: RmRequirePlanServiceImpl
 * @Package com.baosight.wilp.rm.lj.service.impl.impl
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
 * 10.更新需求计划明细状态
 * 11.判断指定需求计划的明细是否都已汇总
 * 12.需求计划明细分配
 * 13.需求计划明细分配退回
 */
@Service
public class RmRequirePlanServiceImpl implements RmRequirePlanService {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 获取需求计划集合
     * @Title: queryRequirePlanList
     * @param require require : 需求计划对象
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmRequirePlan>
     * @throws
     **/
    @Override
    public List<RmRequirePlan> queryRequirePlanList(RmRequirePlan require) {
        if(require == null) {
            return dao.query("RMLJ01.query", new HashMap<>(0));
        }
        return dao.query("RMLJ01.query", require.toMap());
    }

    /**
     * 根据需求计划ID获取需求计划明细集合
     * @Title: queryRequirePlanDetailList
     * @param requirePlanId requirePlanId : 需求计划ID
     * @return java.util.List<com.baosight.wilp.rm.lj.domain.RmRequirePlan>
     * @throws
     **/
    @Override
    public List<RmRequirePlanDetail> queryRequirePlanDetailList(String requirePlanId) {
        return dao.query("RMLJ01.queryDetail", new HashMap(2) {{
            put("planId", requirePlanId);
        }});
    }

    /**
     * 获取指定需求计划
     * @Title: queryRequirePlan
     * @param id id : 需求计划ID
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     **/
    @Override
    public RmRequirePlan queryRequirePlan(String id) {
        List<RmRequirePlan> list = dao.query("RMLJ01.query", new HashMap(2) {{put("id", id);}});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 根据需求计划单号获取指定需求计划
     * @Title: queryRequirePlanByPlanNo
     * @param planNo planNo
     * @return com.baosight.wilp.rm.lj.domain.RmRequirePlan
     * @throws
     **/
    @Override
    public RmRequirePlan queryRequirePlanByPlanNo(String planNo) {
        List<RmRequirePlan> list = dao.query("RMLJ01.query", new HashMap(2) {{
            put("planNoEQ", planNo);
        }});
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 新增需求计划
     * @Title: insertRequirePlan
     * @param require require : 需求计划对象
     * @return void
     * @throws
     **/
    @Override
    public void insertRequirePlan(RmRequirePlan require) {
        require.setRecCreateTime(new Date());
        dao.insert("RMLJ01.insert", require);
    }

    /**
     * 编辑需求计划
     * @Title: updateRequirePlan
     * @param require require : 需求计划对象
     * @return void
     * @throws
     **/
    @Override
    public void updateRequirePlan(RmRequirePlan require) {
        require.setRecReviseTime(new Date());
        dao.update("RMLJ01.update", require);
    }

    /**
     * 批量新增需求计划明细
     * @Title: insertRequirePlanDetail
     * @param detailList detailList : 需求计划明细集合
     * @return void
     * @throws
     **/
    @Override
    public void insertRequirePlanDetail(List<RmRequirePlanDetail> detailList) {
        dao.insert("RMLJ01.insertDetail", detailList);
    }

    /**
     * 根据ID删除需求计划
     * @Title: deleteRequirePlan
     * @param id id : 需求计划ID
     * @return void
     * @throws
     **/
    @Override
    public void deleteRequirePlan(String id) {
        dao.delete("RMLJ01.delete", id);
    }

    /**
     * 根据需求计划ID删除需求计划明细
     * @Title: deleteRequirePlanDetail
     * @param requirePlanId requirePlanId : 需求计划ID
     * @return void
     * @throws
     **/
    @Override
    public void deleteRequirePlanDetail(String requirePlanId) {
        dao.delete("RMLJ01.deleteDetail", requirePlanId);
    }

    /**
     * 更新需求计划明细状态
     * @Title: updateRequirePlanDetailStatus
     * @param detail detail
     * @return int
     * @throws
     **/
    @Override
    public int updateRequirePlanDetailStatus(RmRequirePlanDetail detail) {
        return dao.update("RMLJ01.updateDetailStatus", detail);
    }

    /**
     * 判断指定需求计划的明细是否都已汇总
     * @Title: hasAllCollect
     * @param planId planId
     * @return boolean
     * @throws
     **/
    @Override
    public boolean hasAllCollect(String planId) {
        int count = dao.count("RMLJ01.hasAllCollect", planId);
        return count == 0;
    }

    /**
     * 更新需求计划状态(部分汇总或未汇总)
     * @Title: updateRequirePlanUnFinish
     * @param planIds planIds
     * @return int
     * @throws
     **/
    @Override
    public int updateRequirePlanUnFinish(String planIds) {
        return dao.update("RMLJ01.updateRequirePlanUnFinish", planIds);
    }

    /**
     * 更新需求计划状态(部分汇总或已汇总)
     * @Title: updateRequirePlanFinish
     * @param planIds planIds
     * @return int
     * @throws
     **/
    @Override
    public int updateRequirePlanFinish(String planIds) {
        return dao.update("RMLJ01.updateRequirePlanFinish", planIds);
    }

    /**
     * 需求计划明细分配
     * @Title: insertRequirePlanAllot
     * @param detailList detailList 需求计划明细集合
     * @return void
     * @throws
     **/
    @Override
    public void insertRequirePlanAllot(List<RmRequirePlanDetail> detailList) {
        //无法使用replace into
        List<String> ids = detailList.stream().map(d -> d.getId()).collect(Collectors.toList());
        dao.delete("RMLJ01.deleteRequirePlanAllot",ids);
        dao.insert("RMLJ01.insertRequirePlanAllot", detailList);
    }

    /**
     * 需求计划明细退回
     * @Title: updateRequirePlanAllot
     * @param backDetail backDetail 需求计划明细
     * @return void
     * @throws
     **/
    @Override
    public void updateRequirePlanAllot(RmRequirePlanDetail backDetail) {
        dao.update("RMLJ01.updateRequirePlanAllot", backDetail);
    }
}
