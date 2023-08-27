package com.baosight.wilp.dm.hs.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.dm.common.DMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 宿舍员工换宿子页面service
 * 一、页面加载.
 *
 * @Title: ServiceDMHS0101.java
 * @ClassName: ServiceDMHS0101
 * @Package：com.baosight.wilp.dm.qr.service
 * @author: fangzekai
 * @date: 2022年02月10日 下午6:27:22
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDMHS0101 extends ServiceBase {
    /**
     * 此方法用于页面初始化
     *
     * @Title: initLoad
     * @param: EiInfo inInfo
     * @return: EiInfo inInfo
     */
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 更新操作，更新人员的申请换宿的时间以及原因以及人员的状态
     * 1、获取当前用户信息.
     * 2、调用本地服务DMQR0101.updateDormRoomMan更新人员的申请换宿的时间以及原因以及人员的状态
     * 3、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
     * 4、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
     * 5、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
     * 6、返回操作结果.
     *
     * @Title: update
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo update(EiInfo inInfo) {
        /*
         * 1、获取当前用户信息.
         */
        // 获取当前登陆工号
        String loginName= StringUtils.isBlank((String)inInfo.get("workNo"))?
                UserSession.getUser().getUsername():(String)inInfo.get("workNo");
        Map<String, Object> userInfo = DMUtils.getUserInfo(loginName);
        inInfo.set("operator", loginName);
        String applyChangeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());      /* 换宿时间*/
        inInfo.set("applyChangeDate", applyChangeDate);
        /*
         * 2、调用本地服务DMQR0101.updateDormRoomMan更新人员的申请换宿的时间以及原因以及人员的状态
         */
        inInfo.set(EiConstant.serviceName, "DMQR0101");
        inInfo.set(EiConstant.methodName, "updateDormRoomMan");
        EiInfo outInfo = XLocalManager.call(inInfo);
        /*
         * 3、调用本地服务DMRZ01.updateStatusCode更新人员信息表状态.
         */
        inInfo.set("statusCode", "10");
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "updateStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 4、调用本地服务DMRZ01.updateLCStatusCode更新当前操作流程之前的状态为0,将之前存在的该状态不标注为当前状态.
         */
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "updateLCStatusCode");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 5、调用本地服务DMRZ01.insertLCInfo将申请流程插入宿舍操作流程历史表中.
         */
        // 将申请流程插入宿舍操作流程历史表中
        inInfo.set(EiConstant.serviceName, "DMRZ01");
        inInfo.set(EiConstant.methodName, "insertLCInfo");
        outInfo = XLocalManager.call(inInfo);
        /*
         * 6、返回操作结果.
         */
        outInfo.setMsgKey("200");
        return outInfo;
    }

}
