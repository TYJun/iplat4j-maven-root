package com.baosight.wilp.dk.zh.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 保养综合查询：初始化查询、任务查询
 * <>1.初始化查询 initLoad
 * <>2.任务查询 query
 * @Title: ServiceDKZH01.java
 * @ClassName: ServiceDKZH01
 * @Package：com.baosight.wilp.dk.zh.service
 * @author: LENOVO
 * @date: 2021年9月13日 下午2:42:32
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKZH01 extends ServiceBase{

    /**
     * 初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>此方法就是调用下面任务查询方法query
     * @param info
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
    public EiInfo initLoad(EiInfo info) {
        return query(info);
    }
	/**
     * 任务查询
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.将传递参数分装map
     * <p>2.使用map参数查询获取保养分类list和count
     * <p>3.将返回的保养分类list和count添加到EiiNFO并返回客户端
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
    @Override
    public EiInfo query(EiInfo info) {
        
        //测试方法
//        ServiceDKJZ02 dkjz02 = new ServiceDKJZ02();
//        dkjz02.createDeviceTask();
        
        
    	//1.将传递参数分装map
        Map<String, String> map=new HashMap<String, String>();
        String[] param = {"taskCode","taskName","status","machineName","startTimeS","startTimeE"};
        Map<String, Object> buildParam = DeviceEiUtil.buildParam(info, Arrays.asList(param), "result");
      //2.使用map参数查询获取保养分类list和count
        List<Map<String,String>> list=dao.query("DKZH01.query",buildParam);
        int count = dao.count("DKZH01.count",buildParam);
      //3.将返回的保养分类list和count添加到EiiNFO并返回客户端
        return DeviceEiUtil.buildResult(info, list, count, "result");
    }
	
}
