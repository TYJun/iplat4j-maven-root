/**
 *@Name ServiceDHRM01.java
 *@Description TODO
 *@Date 2021年5月2日 下午7:19:55
 *@Version 1.0
 **/

package com.baosight.wilp.dm.hi.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;


/**
 * TODO(页面加载查询-DMHI01)
 * 
 *     1.初始化页面加载调用query方法
 *     2.初始化页面加载
 * 
 * @Title: ServiceDMHI01.java
 * @ClassName: ServiceDMHI01
 * @Package：com.baosight.wilp.dm.hi.service
 * @author: 辉
 * @date: 2021年11月24日 下午3:01:05
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMHI01 extends ServiceBase{
	
    /**
     * TODO(初始化页面加载调用query方法)
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return query(inInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    return query(inInfo);
	}
	
	 /**
     * TODO(初始化页面加载)
     * @title query
     * @param resquest 请求入参 {ifReview-是否通过审核}
     * @return outInfo
     */
    @Override
    public EiInfo query(EiInfo inInfo) {
        //传入数据

    	inInfo.set("ifReview", inInfo.get("ifReview"));
    	//调用类DMHM01
    	inInfo.set(EiConstant.serviceName, "DMHM01");
    	//调用类DMHM01中方法queryHiList
     	inInfo.set(EiConstant.methodName, "queryHiList");
        EiInfo outInfo =XLocalManager.call(inInfo);
        return outInfo;
        
    }
    
   
}

