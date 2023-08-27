package com.baosight.wilp.pm.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.pm.domain.Tpm01;
/**
 * 
 * 工程项目：初始化查询、工程项目数据查询、工程项目数据删除
 * <p>1.初始化查询 initLoad
 * <p>2.工程项目数据查询 query
 * <p>3.工程项目数据删除 delete
 * 
 * @Title: ServicePM01.java
 * @ClassName: ServicePM01
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月27日 上午9:37:59
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM01 extends ServiceBase {
    
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return
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
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo inInfo) {
		// 调用查询方法
		return super.query(inInfo,"PM01.query",new Tpm01());
	}

	/**
	 * 
	 * @Title: delete
     * @Description: 工程项目数据删除
     * @param inInfo
     * @param inInfo
	 * @see ServiceBase#delete(EiInfo)
	 */
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		EiInfo eiInfo = super.delete(inInfo,"PM01.delete");
		//删除项目阶段信息
		dao.delete("PM0103.deleteStage", inInfo.getCellStr("result", 0, "id"));
		//删除执行人信息
		dao.delete("PM0103.deleteStaff", inInfo.getCellStr("result", 0, "id"));
		//删除知会人信息
		dao.delete("PM0103.deleteKnow", inInfo.getCellStr("result", 0, "id"));
		//删除旧的附件信息
		dao.delete("PM0103.deleteFile", inInfo.getCellStr("result", 0, "id"));
		return eiInfo;
	}

	/**
	 * @Title: cancell
	 * @Description: 工程项目作废
	 * @param info
	 */
	public EiInfo startPR(EiInfo info){
		String projectNo = (String) info.get("projectNo");
		// 调用作废方法
		dao.update("PM01.startPR",projectNo);
		return info;
	}

	/**
	 * @Title: cancell
	 * @Description: 工程项目作废
	 * @param info
	 */
	public EiInfo cancell(EiInfo info){
		String projectNo = (String) info.get("projectNo");
		// 调用作废方法
		dao.update("PM01.cancell",projectNo);
		return info;
	}
}
