package com.baosight.wilp.mp.lj.service;

import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.2
 * @Description: TODO
 * @ClassName: MpOaDockService
 * @Package com.baosight.wilp.mp.lj.service
 * @date: 2023年06月28日 16:52
 */
public interface MpOaDockService {

    /**
     * 查询待审批的需求计划
     * @Title: queryUnApprovalRequirePlan
     * @param deptNum deptNum 需求科室编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    List<Map<String, Object>> queryUnApprovalRequirePlan(String deptNum);

    /**
     * 根据集成平台系统科室ID获取科室信息
     * @Title: queryDeptByHrpId
     * @param deptId deptId 集成平台科室ID
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    public Map<String, String> queryDeptByHrpId(String deptId);

    /**
     * 查询已审批的需求计划
     * @Title: queryApprovedRequire
     * @param map map
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    List<Map<String, Object>> queryApprovedRequire(Map<String, Object> map);

    /**
     * 查询已审批的采购计划
     * @Title: queryApprovedPurchase
     * @param map map
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    List<Map<String, Object>> queryApprovedPurchase(Map<String, Object> map);

    /**
     * 根据需求计划ID获取需求计划单号
     * @Title: queryRequireNoById
     * @param id id
     * @return java.lang.String
     * @throws
     **/
    String queryRequireNoById(String id);
}
