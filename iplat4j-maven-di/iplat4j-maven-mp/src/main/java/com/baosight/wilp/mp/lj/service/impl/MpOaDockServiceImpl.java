package com.baosight.wilp.mp.lj.service.impl;

import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.service.MpOaDockService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: OA对接Service实现
 * @ClassName: MpOaDockServiceImpl
 * @Package com.baosight.wilp.mp.lj.service.impl
 * @date: 2023年06月28日 16:52
 */
@Service
public class MpOaDockServiceImpl implements MpOaDockService {

    private static final String processState_running = "Running";
    private static final String processState_return = "Return";
    private static final String processState_finish = "Finish";


    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    /**
     * 查询待审批的需求计划
     * @Title: queryUnApprovalRequirePlan
     * @param deptNum deptNum 需求科室编码
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    @Override
    public List<Map<String, Object>> queryUnApprovalRequirePlan(String deptNum) {
        return dao.query("MPLJ09.queryApprovalRequire", new HashMap(4){{
            put("statusCode", "10");
            put("deptNum", deptNum);
        }});
    }

    /**
     * 根据集成平台系统科室ID获取科室信息
     * @Title: queryDeptByHrpId
     * @param deptId deptId 集成平台科室ID
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    @Override
    public Map<String, String> queryDeptByHrpId(String deptId) {
        Map<String, Object> dept = BaseDockingUtils.getDeptByDeptId(deptId);
        return new HashMap(4) {{
            put("deptId", deptId);
            put("deptNum", MpUtils.toString(dept.get("deptNum")));
            put("deptName", MpUtils.toString(dept.get("deptName")));
            put("parentId", MpUtils.toString(dept.get("parentId")));
            put("parentName", MpUtils.toString(dept.get("parentDeptName")));
        }};
    }

    /**
     * 查询已审批的需求计划
     * @Title: queryApprovedRequire
     * @param map map
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    @Override
    public List<Map<String, Object>> queryApprovedRequire(Map<String, Object> map) {
        String userCode = MpUtils.toString(map.get("userCode"));
        String processState = MpUtils.toString(map.get("processState"));
        //获取用户组
        String userGroups = MpUtils.getUserGroups(userCode);
        //没有需求科室审批用户角,或流程状态为：Running（流转）的直接返回。
        if(!userGroups.contains(MpConstant.REQUIRE_ROLE) || processState_running.equals(processState)) {
            return null;
        }
        List<Map<String, Object>> list = dao.query("MPLJ09.queryApprovalRequire", map);
        return list;
    }

    /**
     * 查询已审批的采购计划
     * @Title: queryApprovedPurchase
     * @param map map
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     **/
    @Override
    public List<Map<String, Object>> queryApprovedPurchase(Map<String, Object> map) {
        String userCode = MpUtils.toString(map.get("userCode"));
        String processState = MpUtils.toString(map.get("processState"));
        //获取用户组
        String userGroups = MpUtils.getUserGroups(userCode);
        if(userGroups.contains(MpConstant.DEAN_ROLE) && processState_running.equals(processState)) {
            return null;
        }
        List list = dao.query("MPLJ09.queryApprovedPurchase", map);

        return list;
    }

    /**
     * 根据需求计划ID获取需求计划单号
     * @Title: queryRequireNoById
     * @param id id
     * @return java.lang.String
     * @throws
     **/
    @Override
    public String queryRequireNoById(String id) {
        List<String> list = dao.query("MPLJ09.queryRequireNoById", id);
        return CollectionUtils.isEmpty(list) ? "" : list.get(0);
    }
}
