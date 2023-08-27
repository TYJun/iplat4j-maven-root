
package com.baosight.wilp.pm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pm.domain.Tpm01;
/**
 * 
 * 工程项目：初始化查询、工程项目数据查询
 * <p>1.初始化查询 initLoad
 * <p>2.工程项目数据查询 query
 * 
 * 
 * @Title: ServicePM0102.java
 * @ClassName: ServicePM0102
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月27日 上午9:48:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0102 extends ServiceBase {

    /**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用初始化方法
		return super.initLoad(inInfo,new Tpm01());
	}

	/**
	 * 
	 * @Title: query
     * @Description: 工程项目数据查询
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
	    // 调用查询方法
		return super.query(inInfo,"PM01.query",new Tpm01());
	}

}
