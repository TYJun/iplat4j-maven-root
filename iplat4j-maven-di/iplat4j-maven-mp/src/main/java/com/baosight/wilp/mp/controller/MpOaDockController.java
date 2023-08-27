package com.baosight.wilp.mp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baosight.iplat4j.core.data.ibatis.dao.Dao;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.NumberUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.service.MpOaDockService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 物资采购OA对接接口
 * @ClassName: MpOaDockController
 * @Package com.baosight.wilp.mp.controller
 * @date: 2023年06月27日 16:58
 */
@Controller
@RequestMapping("/mp/oa")
public class MpOaDockController {

    /**注入dao**/
    private Dao dao = (Dao) PlatApplicationContext.getBean("dao");

    @Autowired
    private MpOaDockService mpOaDockService;

    /**
     * 查询待审批数据
     * @Title: queryUnApproval
     * @param map map
     *      userCode userCode 工号
     *      deptID deptID 集成平台科室ID
     * @return java.util.Map
     * @throws
     **/
    @PostMapping("/unApproval")
    @ResponseBody
    public Map queryUnApproval(@RequestBody Map<String, String> map) {
        List<Map<String, Object>> data = new ArrayList<>();
        String userCode = map.get("userCode");
        String deptID = map.get("deptID");
        if(StringUtils.isBlank(userCode) || StringUtils.isBlank(deptID)) {
            return error(0, "必填参数不能为空");
        }
        //获取科室ID对应的科室编码
        String deptNum = queryDeptNumById(deptID);
        //获取用户的用户组
        String userGroups = MpUtils.getUserGroups(userCode);
        /**1判断角色，查询角色对应的待审批数据**/
        //1.1是否具有需求计划审批角色权限，是，查询对应待审批的需求计划**/
        if(userGroups.contains(MpConstant.REQUIRE_ROLE)) {
            List<Map<String, Object>> requireList = mpOaDockService.queryUnApprovalRequirePlan(deptNum);
            requireToResult(data,requireList, 100);
        }
        //1.2角色校验,判断用户的角色是否满足。不满足，返回空;满足,查询采购计划数据
        HashMap<String, String> pMap = new HashMap<>(4);
        if(userGroups.contains(MpConstant.DEAN_ROLE)) {
            pMap.put("statusCode", MpConstant.PROCUREMENT_STATUS_THREE);
        } else if(userGroups.contains(MpConstant.LEADER_ROLE) && userGroups.contains(MpConstant.DEPT_ROLE)) {
            pMap.put("roleAuth", "deptAndLeader");
            pMap.put("curDept", deptNum);
            pMap.put("depts", MpUtils.getBusinessDept(deptNum, true));
        } else if(userGroups.contains(MpConstant.LEADER_ROLE) && !userGroups.contains(MpConstant.DEPT_ROLE)) {
            pMap.put("roleAuth", "leader");
            pMap.put("depts", MpUtils.getBusinessDept(deptNum, true));
        } else if(userGroups.contains(MpConstant.DEPT_ROLE)) {
            pMap.put("roleAuth", "dept");
            pMap.put("curDept", deptNum);
        } else {
            pMap.put("deptNum", "no auth");
        }
        //1.3采购计划数据查询
        List<Map<String, Object>> list = dao.query("MPCG01.query", pMap);
        purchaseToResult(data,list);

        return success("查询成功", JSON.toJSONString(data), data.size());
    }

    /**
     * 查询已审批数据
     * @Title: queryApproved
     * @param map map
     *     userCode 工号
     *     deptID 集成平台科室ID
     *     actFrom 开始时间
     *     actTo 结束时间
     *     starterDeptID 发起科室ID
     *     keyword 关键字，匹配流程名称与主要内容
     *     processState 流程状态，状态有：Running（流转）、Return（驳回）、Finish（完成）
     * @return java.util.Map
     * @throws
     **/
    @PostMapping("/approved")
    @ResponseBody
    public Map queryApproved(@RequestBody Map<String, Object> map) {
        List<Map<String, Object>> data = new ArrayList<>();
        if(!validateQueryParams(map)) {
            return error(0, "必填参数不能为空");
        }
        map.put("deptNum", queryDeptNumById(MpUtils.toString(map.get("deptID"))));
        map.put("starterDept", queryDeptNumById(MpUtils.toString(map.get("starterDeptID"))));
        //获取已审批的需求计划
        List<Map<String, Object>> requireList = mpOaDockService.queryApprovedRequire(map);
        requireToResult(data,requireList, 101);
        //获取已审批的采购计划
        List<Map<String, Object>> purchaseList = mpOaDockService.queryApprovedPurchase(map);
        purchaseToResult(data,purchaseList);

        return success("查询成功", JSON.toJSONString(data), data.size());
    }

    /**
     * 批量审批
     * @Title: batchApproval
     * @param map map
     * @return java.util.Map
     * @throws
     **/
    @PostMapping("/batchApproval")
    @ResponseBody
    public Map batchApproval(@RequestBody Map<String, Object> map) {
        //获取参数
         int successCount = 0;
        List<Map> serialList = MpUtils.toList(map.get("serialList"), Map.class);
        String opinion = MpUtils.toString(map.get("opinion"));
        String transactionId = MpUtils.toString(map.get("transactionID"));
        if(CollectionUtils.isEmpty(serialList) || StringUtils.isBlank(transactionId)) {
            return error(0, "必填参数不能为空");
        }
        
        //获取审批人信息
        Map<String, String> user = getApprovalUser(transactionId);

        //获取数据唯一标识
        List <String> planIds = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        serialList.forEach(serial -> {
            if(NumberUtils.toint(serial.get("processSerial")) >= 100) {
                ids.add(MpUtils.toString(serial.get("nodeID")));
            } else {
                planIds.add(MpUtils.toString(serial.get("nodeID")));
            }
        });
        EiInfo outInfo = null;
        //批量审批需求计划
        if (ids.size() > 0) {
            outInfo = MpUtils.invoke("RMXQ04", "batchPass", Arrays.asList("ids", "workNo", "name"),
                    ids, user.get("workNo"), user.get("name"));
            successCount = outInfo.getStatus() == -1 ? 0 : ids.size();
        }

        //批量审批采购计划
        if(planIds.size() > 0) {
            outInfo = MpUtils.invoke("MPCG0401","pass", Arrays.asList("planIds", "workNo", "name"),
                    planIds, user.get("workNo"), user.get("name"));
            successCount = successCount + (outInfo.getStatus() == -1 ? 0 : planIds.size());
        }
        int finalSuccessCount = successCount;
        return success("审批完成", JSON.toJSONString(new HashMap(2){{
            put("SuccCount", finalSuccessCount);put("FailCount", serialList.size() - finalSuccessCount);
        }}), 0);
    }

    /**
     * 根据集成平台科室ID获取科室编码
     * @Title: queryDeptNumById
     * @param deptID deptID 集成平台科室ID
     * @return java.lang.String
     * @throws
     **/
    private String queryDeptNumById(String deptID) {
        if(StringUtils.isBlank(deptID)) { return ""; }
        Map<String, String> dept = mpOaDockService.queryDeptByHrpId(deptID);
        return dept.get("deptNum");
    }

    /**
     * 将需求计划数据转换成接口要求返回类型数据
     * @Title: requireToResult
     * @param data data 返回数据集合
     * @param list list 需求计划集合
     * @return void
     * @throws
     **/
    private void requireToResult(List<Map<String, Object>> data, List<Map<String, Object>> list, int serial) {
        if(CollectionUtils.isEmpty(list)) { return; }
        for (Map<String, Object> map : list) {
            Map<String, Object> rMap = new HashMap<>(16);
            rMap.put("ProcessSerial", serial);
            rMap.put("FlowName", "科室采购物资需求审批");
            rMap.put("StarterName", map.get("recCreatorName"));
            rMap.put("DeptName", map.get("deptName"));
            rMap.put("NodeID", map.get("id"));
            rMap.put("NodeName", "主管科室审批");
            rMap.put("LastEditTime", map.get("recCreateTime"));
            rMap.put("MainContent", map.get("planDesc"));
            rMap.put("CanBatchAprove", true);
            rMap.put("IsDealInMobile", true);
            rMap.put("AppID", 70);
            data.add(rMap);
        }
    }

    /**
     * 将采购计划数据转换成接口要求返回类型数据
     * @Title: purchaseToResult
     * @param data data 返回数据集合
     * @param list list 采购计划集合
     * @return void
     * @throws
     **/
    private void purchaseToResult(List<Map<String, Object>> data, List<Map<String, Object>> list) {
        if(CollectionUtils.isEmpty(list)) { return; }
        for (Map<String, Object> map : list) {
            Map<String, Object> rMap = new HashMap<>(16);
            rMap.put("ProcessSerial", NumberUtils.toint(map.get("statusCode")));
            rMap.put("FlowName", "物资采购计划审批");
            rMap.put("StarterName", map.get("recCreatorName"));
            rMap.put("DeptName", map.get("deptName"));
            rMap.put("NodeID", map.get("id"));
            rMap.put("NodeName", map.get("statusName"));
            rMap.put("LastEditTime", map.get("recReviseTime"));
            rMap.put("MainContent", map.get("purchaseRemark"));
            rMap.put("CanBatchAprove", true);
            rMap.put("IsDealInMobile", true);
            rMap.put("AppID", 70);
            data.add(rMap);
        }
    }

    /**
     * 校验查询参数
     * @Title: validateQueryParams
     * @param map map
     * @return boolean
     * @throws
     **/
    private boolean validateQueryParams(Map<String, Object> map) {
        if(StringUtils.isBlank(MpUtils.toString(map.get("userCode")))) { return false;}
        if(StringUtils.isBlank(MpUtils.toString(map.get("deptID")))) { return false;}
        if(StringUtils.isBlank(MpUtils.toString(map.get("actFrom")))) { return false;}
        if(StringUtils.isBlank(MpUtils.toString(map.get("actTo")))) { return false;}
        return true;
    }

    /**
     * 根据医信签身份授权事务编号获取用户信息
     * @Title: getApprovalUser
     * @param transactionId transactionId 医信签身份授权事务编号
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @throws
     **/
    private Map<String, String> getApprovalUser(String transactionId) {
        EiInfo invoke = MpUtils.invoke("XQMS01", "checkScanStatus", Arrays.asList("transactionId"), transactionId);
        if(invoke.getStatus() != 0) {
            return new HashMap<>(0);
        }
        JSONObject result = JSON.parseObject(JSON.toJSONString(invoke.get("result")));
        JSONObject data = result.getJSONObject("data");
        return new HashMap<String, String>(2){{
            put("userId", data.getString("userId"));
            put("userName", data.getString("userName"));
        }};
    }

    /**
     * 接口成功返回
     * @Title: success
     * @param msg msg 返回描述
     * @param data data 返回数据(json字符串)
     * @param count count 返回Data中的记录数，没有记录则为0
     * @return java.util.Map
     * @throws
     **/
   private static Map success(String msg, String data, int count) {
       Map map = new HashMap(4);
       map.put("Code", 1);
       map.put("Msg", msg);
       map.put("Count", count);
       map.put("Data", data);
       return map;
   }

   /**
    * 接口失败返回
    * @Title: error
    * @param code code 响应码：0.失败，-1.异常。
    * @param msg msg 返回描述
    * @return java.util.Map
    * @throws
    **/
   private static Map error(int code, String msg) {
       Map map = new HashMap(4);
       map.put("Code", code);
       map.put("Msg", msg);
       map.put("Count", 0);
       map.put("Data", "");
       return map;
   }



}
