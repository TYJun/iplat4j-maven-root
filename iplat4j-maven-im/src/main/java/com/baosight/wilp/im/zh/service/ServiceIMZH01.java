package com.baosight.wilp.im.zh.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.common.util.DeviceEiUtil;
import com.baosight.wilp.im.jz.service.ServiceImTimeTask;

/**
 * 
 * 巡检综合查询：初始化查询、任务查询
 * <>1.初始化查询 initLoad
 * <>2.任务查询 query
 * @Title: ServiceDIZH01.java
 * @ClassName: ServiceDIZH01
 * @Package：com.baosight.wilp.di.zh.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午2:42:32
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class ServiceIMZH01 extends ServiceBase {

    /**
     * 初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>此方法就是调用下面任务查询方法query
     * @param info
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo initLoad(EiInfo info) {
        return query(info);
    }
    
    /**
     * 任务查询
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>封装查询参数
     * @param info
     * taskCode  任务编号
     * taskName  任务名称
     * status    状态
     * machineName  设备名称
     * startTimeS   开始时间
     * startTimeE   结束时间
     * @return
     * taskID    主键
     * schemeID  基准的ID号
     * status    状态
     * taskCode  任务单号
     * schemeName  任务名称
     * exceptionFlag  异常状态
     * machineName  设备名称
     * machineCode  设备代码
     * jobContent   作业说明
     * deptName     责任单位部门 
     * name          责任人
     * finishName   完工操作人
     * createTime   计划开始时间
     * finishTime   完成时间
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
     */
    public EiInfo query(EiInfo info) {
        ServiceImTimeTask s = new ServiceImTimeTask();
        s.createImTask();
        
        
        Map<String, String> map=new HashMap<String, String>();
        String[] param = {"taskCode","taskName","status","machineName","startTimeS","startTimeE","objectName"};
        Map<String, Object> buildParam = DeviceEiUtil.buildParam(info, Arrays.asList(param), "result");
        //封装查询参数
        String statS = (String)buildParam.get("startTimeS");
        String statE = (String)buildParam.get("startTimeE");
        if(null != statS && null != statE) {
            if(!"".equals(statS) && !"".equals(statE)) {
                buildParam.put("startTimeS", statS + " 00:00:00");
                buildParam.put("startTimeE", statE + " 23:59:59");
            }
        }
        List<Map<String,String>> list=dao.query("IMZH01.query",buildParam);
        int count = dao.count("IMZH01.count",buildParam);
        return DeviceEiUtil.buildResult(info, list, count, "result");
    }
}
