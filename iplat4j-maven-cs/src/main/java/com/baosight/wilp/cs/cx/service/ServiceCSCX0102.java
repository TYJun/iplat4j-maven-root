package com.baosight.wilp.cs.cx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单打印Service
 * 一、页面加载.
 * 二、工单打印数据查询.
 * 三、更改工单的打印状态.
 * 
 * @Title: ServiceCSCX0102.java
 * @ClassName: ServiceCSCX0102
 * @Package：com.baosight.wilp.cs.cx.service
 * @author: fangzekai
 * @date: 2021年11月29日 下午3:02:20
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCSCX0102 extends ServiceBase {

    /**
     *  一、页面加载.
     *
     * @Title: initLoad
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 二、工单打印数据查询.
     *
     * @Title: queryTaskForReport
     * @param inInfo inInfo
     *      taskNo：工单号
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      taskNo : 工单号
     *      currentTime ： 当前时间
     *      reqStaffId ： 来电人Id
     *      reqStaffName ：来电人名称
     *      reqTelNum ：来电电话
     *      reqDeptName ： 来电科室
     *      reqSpotName ：保洁地点
     *      comments ：工单备注
     *      evalGrade ： 工单评价等级
     *      evalOpinion : 工单评价建议
     * @throws
     **/
    public EiInfo queryTaskForReport(EiInfo inInfo) {
        // 获取参数
        String taskNo = inInfo.getString("taskNo");
        // 执行CSRE01.queryTaskForReport 查询工单打印数据.
        List<Map<String, Object>> list = dao.query("CSRE01.queryTaskForReport", taskNo);
        Map<String, Object> map = null;
        // 判空
        if(list != null && list.size() > 0){
            map = list.get(0);
        } else {
            map = new HashMap<>(16);
        }
        // 返回数据
        inInfo.set("parameters", map);
        return inInfo;
    }

    /**
     * 三、更改工单的打印状态.
     * 1、获取前端所有勾选并点击打印的工单号.
     * 2、对该工单列表进行一个批量更新打印状态.
     * 3、操作成功返回.
     *
     * @Title: changePrintStatusCode
     * @Param EiInfo
     * @return: EiInfo
     */
    public EiInfo changePrintStatusCode(EiInfo inInfo) {
        EiInfo outInfo = new EiInfo();
        /*
         * 1、获取前端所有勾选并点击打印的工单号.
         */
        List taskNoAll = (List)inInfo.get("taskNoAll");
        /*
         * 2、对该工单列表进行一个批量更新打印状态.
         */
        dao.update("CSRE01.updateCSGDPrint", taskNoAll);
        /*
         * 3、操作成功返回.
         */
        outInfo.set("taskNoAll", taskNoAll);
        return outInfo;
    }
}
