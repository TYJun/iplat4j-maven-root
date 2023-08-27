package com.baosight.wilp.cs.fs.service;

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
 * 保洁完工子页面Service.
 * 一、页面加载.
 * 二、工单的保洁完工功能.
 * 
 * @Title: ServiceCSFS0101.java
 * @ClassName: ServiceCSFS0101
 * @Package：com.baosight.wilp.cs.fs.service
 * @author: fangzekai
 * @date: 2021年11月25日 下午8:35:11
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSFS0101 extends ServiceBase {
	
	/**
	 * 一、页面加载.
     *
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
        // 获取前端传来的组合字符串，对字符串进行拆分成taskId 工单ID 和 itemCode 事项编码.
        String taskId = "";
        String itemCode = "";
        String str = (String)inInfo.getAttr().get("taskIdAnditemCode");
        taskId = str.split(",")[0];
        itemCode = str.split(",")[1];
        inInfo.set("taskId",taskId);
        inInfo.set("itemCode",itemCode);
        // 调用本地服务CSRE01.queryUnFinishTaskInfo 查询未完工工单详情加载页面。
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "queryUnFinishTaskInfo");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }
	
	/**
	 * 二、工单的保洁完工功能.
     *  1、获取inInfo中的参数并对参数进行处理.
     *  2、以map为参数执行CSRE01.queryGDStatus 获取当前工单的状态.
     *     判断当前工单的状态是否符合完工流程（状态码为02）.
     *     若符合完工02流程，
     *     执行CSRE01.updateCSGDMX更改保洁工单明细表的工单子保洁事项状态为完工工单状态.
     *     执行CSRE01.unFinishItemCount 查询当前工单下还有多少事项未完工.
     *     判断工单下的所有保洁事项是否都已经完工，若都已完工，
     *     则执行CSRE01.updateCSGD更改保洁主表的工单状态为完工工单状态.
     *     调用本地服务CSRE01.insertCSGDLC插入完工流程于保洁工单流程表中.
	 *
	 * @Title: finishMethod 
	 * @param inInfo
	 * @return 
	 * @return: EiInfo
	 */
	public EiInfo finishMethod(EiInfo inInfo) {
        /*
         *  1、获取inInfo中的参数并对参数进行处理.
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
        String itemCode = inInfo.getString("itemCode"); // 事项编码
        String statusCode = inInfo.getString("statusCode"); // 工单状态
        map.put("taskId", taskId);
        map.put("itemCode", itemCode);
        map.put("statusCode", statusCode);
        // 将主表的完工工单人与完工时间赋入。
        map.put("finishUserNo", loginName);
        map.put("finishUserName", userInfo == null ? "" : (String)userInfo.get("name"));
        map.put("finishTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        /*
         * 2、以map为参数执行CSRE01.queryGDStatus 获取当前工单的状态.
         *    判断当前工单的状态是否符合完工流程（状态码为02）.
         *    若符合完工02流程，
         *    执行CSRE01.updateCSGDMX更改保洁工单明细表的工单子保洁事项状态为完工工单状态.
         *    执行CSRE01.unFinishItemCount 查询当前工单下还有多少事项未完工.
         *    判断工单下的所有保洁事项是否都已经完工，若都已完工，
         *    则执行CSRE01.updateCSGD更改保洁主表的工单状态为完工工单状态.
         *    调用本地服务CSRE01.insertCSGDLC插入完工流程于保洁工单流程表中.
         */
        String status = "";
        // 执行CSRE01.queryGDStatus判断当前的工单状态.
        List result = dao.query("CSRE01.queryGDStatus", map);
        // 判空
        if (result != null && result.size() > 0) {
            status = (String)result.get(0);
        }
        // 判断当前工单的状态是否符合完工流程（状态码为02）.
        if (status.equals(CsConstant.STATUS_UN_FINISH)) {
            // 执行CSRE01.updateCSGDMX更改保洁工单明细表的工单子保洁事项状态为完工工单状态。
            dao.update("CSRE01.updateCSGDMX", map);
            // 执行CSRE01.unFinishItemCount 查询当前工单下还有多少事项未完工.
            int unFinishItemCount = super.count("CSRE01.unFinishItemCount",map);
            // 判断未完工的事项是否为0，为0则执行整单完工操作.
            if (unFinishItemCount == 0){
                // 执行CSRE01.updateCSGD更改保洁主表的工单状态为完工工单状态。
                dao.update("CSRE01.updateCSGD", map);
                // 调用本地服务CSRE01.insertCSGDLC插入完工流程于保洁工单流程表中。
                inInfo.set(EiConstant.serviceName, "CSRE01");
                inInfo.set(EiConstant.methodName, "insertCSGDLC");
                outInfo = XLocalManager.call(inInfo);
            }
            outInfo.addMsg("操作成功");
            outInfo.setMsgKey("200");
        }else {
            outInfo.addMsg("操作失败");
            outInfo.setMsgKey("201");
        }
        return outInfo;
	}
	
}
