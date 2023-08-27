package com.baosight.wilp.di.zh.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单打印Service
 * All rights Reserved, Designed By www.bonawise.com
 * @author fangjian
 * @version V5.0.2
 * @Title: ServiceDIZH0102.java
 * @ClassName: ServiceDIZH0102
 * @Package com.baosight.wilp.di.zh.service
 * @Description: TODO
 * @date: 2021年11月12日 13:44
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceDIZH0102 extends ServiceBase {

    /**
     * 页面加载
     * @Title: initLoad
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     * @return com.baosight.iplat4j.core.ei.EiInfo
     * @throws
     **/
    @Override
    public EiInfo initLoad(EiInfo inInfo) {
        return inInfo;
    }

    /**
     * 查询打印工单参数
     * @Title: queryTaskForReport
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param inInfo inInfo
     *      taskCode：工单号
     * @return com.baosight.iplat4j.core.ei.EiInfo
     *      taskID ： 任务ID
     *      taskNo ：任务单号
     *      taskName ：任务名称
     *      createTime ： 开始执行时间
     *      departName ：负责科室
     *      principal ：负责人
     *      excutors ： 巡检人员
     * @throws
     **/
    public EiInfo queryTaskForReport(EiInfo inInfo) {
        //获取参数
        String taskCode = inInfo.getString("taskCode");
        //数据查询
        List<Map<String, Object>> list = dao.query("DIZH01.queryTaskForReport", taskCode);
        Map<String, Object> map = null;
        if(list != null && list.size() > 0){
            map = list.get(0);
        } else {
            map = new HashMap<>(16);
        }
        //返回数据
        inInfo.set("parameters", map);
        return inInfo;
    }
}
