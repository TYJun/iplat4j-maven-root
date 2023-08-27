package com.baosight.wilp.cs.re.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.cs.common.CSUtils;
import com.baosight.wilp.cs.re.domain.CsConstant;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保洁编辑子页面Service.
 * 一、页面加载.
 * 二、工单编辑更新接口.
 * 
 * @Title: ServiceCSRE0301.java
 * @ClassName: ServiceCSRE0301
 * @Package：com.baosight.wilp.cs.re.service
 * @author: fangzekai
 * @date: 2021年11月24日 上午11:30:45
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSRE0301 extends ServiceBase {
	
	/**
	 * 一、页面加载.
	 * @Title: initLoad
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
        //调用本地服务CSRE01.queryTaskInfo加载页面。
        inInfo.set(EiConstant.serviceName, "CSRE01");
        inInfo.set(EiConstant.methodName, "queryTaskInfo");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
    }
	
	/**
	 *  二、工单编辑更新接口.
     *  1、获取当前用户信息.
     *  2、以map为参数执行CSRE01.queryGDStatus在保洁工单主表查看当前工单的状态.
     *     判断当前工单的状态是否符合01待确认状态，符合则允许执行编辑.
     *     调用本地服务CSRE01.updateCSGD将工单信息更新到保洁工单主表.
     *     以工单ID执行CSRE01.deleteCSGDMX语句将修改前的保洁事项从保洁明细表中删除.
     *     调用本地服务CSRE01.insertCSGDMX 将修改后的工单的保洁事项插入保洁明细表.
     *
	 * Title: update
	 * @param inInfo
	 * @return
	 * @see ServiceBase#update(EiInfo)
	 */
	public EiInfo update(EiInfo inInfo) {
        /*
         * 1、获取当前用户信息.
         */
	    String loginName=StringUtils.isBlank((String)inInfo.get("workNo"))?
          UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = CSUtils.getUserInfo(loginName);
        Map<String, String> map = new HashMap<>();
        //参数处理
        String taskId = inInfo.getString("taskId"); // 工单ID
        map.put("taskId", taskId);
        inInfo.set("recRevisor", loginName); // 修改人
        EiInfo outInfo = new EiInfo();
        /*
         *  2、以map为参数执行CSRE01.queryGDStatus在保洁工单主表查看当前工单的状态.
         *     判断当前工单的状态是否符合01待确认状态，符合则允许执行编辑.
         *     调用本地服务CSRE01.updateCSGD将工单信息更新到保洁工单主表.
         *     以工单ID执行CSRE01.deleteCSGDMX语句将修改前的保洁事项从保洁明细表中删除.
         *     调用本地服务CSRE01.insertCSGDMX 将修改后的工单的保洁事项插入保洁明细表.
         */
        String status = "";
        // 执行CSRE01.queryGDStatus在保洁工单主表查看当前工单的状态.
        List result = dao.query("CSRE01.queryGDStatus", map);
        // 若不为空则用status字符串接值.
        if (result != null && result.size() > 0) {
            status = (String)result.get(0);
        }
        // 判断该状态是否为01未确认工单状态.
        if (status.equals(CsConstant.STATUS_UN_CONFIRM)) {
            // 将工单信息更新到保洁工单主表
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "updateCSGD");
            outInfo = XLocalManager.call(inInfo);
            // 将修改前的保洁事项从保洁明细表中删除。
            dao.insert("CSRE01.deleteCSGDMX", taskId); //删除工单原先的事项
            // 将修改后的工单的保洁事项插入保洁明细表
            inInfo.set(EiConstant.serviceName, "CSRE01");
            inInfo.set(EiConstant.methodName, "insertCSGDMX");
            outInfo = XLocalManager.call(inInfo);
         /*
          * 3. 返回操作结果。
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
