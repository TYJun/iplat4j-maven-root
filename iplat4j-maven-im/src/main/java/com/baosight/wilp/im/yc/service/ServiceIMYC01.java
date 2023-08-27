package com.baosight.wilp.im.yc.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.yc.domain.IMYC01;


/**
 * 
 * 巡检异常管理：初始化查询、异常查询
 * <p>1.初始化查询 initLoad
 * <p>2.异常查询 query
 * @Title: ServiceDIYC01.java
 * @ClassName: ServiceDIYC01
 * @Package：com.baosight.wilp.di.yc.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午2:25:00
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceIMYC01 extends ServiceBase {
    
    /**
     * 初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * @param inInfo
     * @return
     * itemID  主键
     * exceptionStatus  异常状态
     * exceptionResult  异常处理结果
     * taskCode  任务单号
     * jitemName  任务名称
     * jobContent  作业说明
     * errorContent 异常故障描述
     * finishMan  完工操作人
     * finishTime  完成时间
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
    @Override
	public EiInfo initLoad(EiInfo inInfo) {
	    EiInfo outInfo = super.query(inInfo, "IMYC01.queryException", new IMYC01());
        return outInfo;
	}
	
	
	/**
	 * 异常查询
	 * &lt;p&gt;Title: query&lt;/p&gt;  
	 * &lt;p&gt;Description: &lt;/p&gt;
	 * @param inInfo
	 * taskCode 任务单号
	 * jitemName  任务名称
	 * startTime  计划开始日期
	 * endTime  计划截至日期
	 * @return
	 * itemID  主键
     * exceptionStatus  异常状态
     * exceptionResult  异常处理结果
     * taskCode  任务单号
     * jitemName  任务名称
     * jobContent  作业说明
     * errorContent 异常故障描述
     * finishMan  完工操作人
     * finishTime  完成时间
	 * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
    @Override
	public EiInfo query(EiInfo inInfo) {
	    EiInfo outInfo = super.query(inInfo, "IMYC01.queryException", new IMYC01());
		return outInfo;
	}
}
