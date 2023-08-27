package com.baosight.wilp.cs.pj.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.cs.common.CSUtils;
import com.baosight.wilp.cs.re.domain.CsConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单评价子页面评价页面Service.
 * 一、页面加载.
 * 二、实现工单评价功能.
 * 
 * @Title: ServiceCSPJ0101.java
 * @ClassName: ServiceCSPJ0101
 * @Package：com.baosight.wilp.cs.pj.service
 * @author: fangzekai
 * @date: 2021年11月26日 上午10:14:53
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSPJ0101 extends ServiceBase {

    /**
     * 一、页面加载.
     *
     * @Title: initLoad
     * @param inInfo
     * @return
     * @see ServiceBase#initLoad(EiInfo)
     */
    public EiInfo initLoad(EiInfo inInfo) {
        // 调用本地服务CSRE01.queryTaskInfo加载页面。
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "queryTaskInfo");
        EiInfo outInfo = XLocalManager.call(inInfo);
        return outInfo;
    }

    /**
     * 二、实现工单评价功能.
     *  1、对inInfo中的参数进行处理，将工号ID，工单状态、评价信息等置于map中，用于作为参数进行增改操作.
     *  2、执行CSRE01.queryGDStatus查询当前工单状态.
     *     判断当前工单的状态是否符合评价流程（即待评价03状态）.
     *     若符合，执行CSRE01.updateCSGD将评价的内容与状态信息更改至保洁工单主表.
     *     执行CSRE01.updateCSGDMX将评价的内容与状态信息更改至保洁工单明细表中.
     *     最后调用本地服务CSRE01.insertCSGDLC插入评价流程于保洁工单流程表中，即(工单状态结束).
     *  3、设置操作信息并返回.
     * 
     * @Title: evaluateMethod
     * @param inInfo
     * @return
     * @return: EiInfo
     */
    public EiInfo evaluateMethod(EiInfo inInfo) {
        /*
         * 1、对inInfo中的参数进行处理，将工号ID，工单状态、评价信息等置于map中，用于作为参数进行增改操作.
         */
        EiInfo outInfo = new EiInfo();
        Map<String, String> map = new HashMap<>();
        // 参数处理
        String loginName = StringUtils.isBlank((String)inInfo.get("workNo")) ? UserSession.getUser().getUsername()
            : (String)inInfo.get("workNo");
        Map<String, Object> userInfo = CSUtils.getUserInfo(loginName);
        
        // 操作人
        inInfo.set("operationUserNo", loginName);
        inInfo.set("operationUserName", userInfo == null ? "" : userInfo.get("name"));
        
        String taskId = inInfo.getString("taskId"); // 工单ID
        String statusCode = inInfo.getString("statusCode"); // 工单状态
        String evalGrade = inInfo.getString("evalGrade"); // 评价等级
        String evalOpinion = inInfo.getString("evalOpinion"); // 评价意见
        String evalStatus = inInfo.getString("evalStatus"); // 评价状态，未启用，预留后期.
        map.put("taskId", taskId);
        map.put("statusCode", statusCode);
        
        // 将主表的评价信息赋入。
        map.put("evalGrade", evalGrade);
        map.put("evalOpinion", evalOpinion);
        map.put("evalUserNo", loginName);
        map.put("evalUserName", userInfo == null ? "" : (String)userInfo.get("name"));
        map.put("evalTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("evalStatus", evalStatus);
        /*
         * 2、执行CSRE01.queryGDStatus查询当前工单状态.
         *   判断当前工单的状态是否符合评价流程（即待评价03状态）.
         *   若符合，执行CSRE01.updateCSGD将评价的内容与状态信息更改至保洁工单主表.
         *   执行CSRE01.updateCSGDMX将评价的内容与状态信息更改至保洁工单明细表中.
         *   最后调用本地服务CSRE01.insertCSGDLC插入评价流程于保洁工单流程表中，即(工单状态结束).
         */
        String status = "";
        List result = dao.query("CSRE01.queryGDStatus", map);
        if (result != null && result.size() > 0) {
            status = (String)result.get(0);
        }
        // 判断当前工单的状态是否符合评价流程（即待评价03状态）
        if (status.equals(CsConstant.STATUS_EVALUATE)) {
            // 将评价的内容与状态信息更改至保洁工单主表。
            dao.update("CSRE01.updateCSGD", map);
            // 将评价的内容与状态信息更改至保洁工单明细表中。
            dao.update("CSRE01.updateCSGDMX", map);
            // 调用本地服务CSRE01.insertCSGDLC插入评价流程于保洁工单流程表中，即(工单状态结束)
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "insertCSGDLC");
            outInfo = XLocalManager.call(inInfo);
            /*
             * 3、设置操作信息并返回.
             */
            outInfo.addMsg("操作成功");
            outInfo.setMsgKey("200");
        }else {
            outInfo.addMsg("操作失败");
            outInfo.setMsgKey("201");
        }
        return outInfo;
        
    }

}
