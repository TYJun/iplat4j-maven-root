package com.baosight.wilp.pm.ys.service;

import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.pm.domain.Tpm01;
/**
 * 工程项目：初始化查询、工程项目数据回显、项目验收
 * <p>1.初始化查询 initLoad
 * <p>2.工程项目数据回显 query
 * <p>3.项目验收 acceptProject
 * 
 * @Title: ServicePM04.java
 * @ClassName: ServicePM04
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午3:44:44
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM04 extends ServiceBase {

    /**
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
     * @Title: query
     * @Description: 查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo query(EiInfo inInfo) {
	    // 调用查询方法
		return super.query(inInfo,"PM01.query",new Tpm01());
	}
	
	/**
	 * @Title: acceptProject
	 * @Description: 项目验收
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo acceptProject(EiInfo inInfo) {
	    // 获取参数
		String id = inInfo.get("id").toString();
		String projectStatus = inInfo.get("projectStatus").toString();
		// 实例化map
		Map<String,Object> map = new HashMap<String, Object>();
		// 设置map
		map.put("id", id);
		map.put("projectStatus", projectStatus);
		map.put("ysTime", DateUtils.curDateStr10());
		// 更新项目状态
		dao.update("PM02.updateProjectStatus", map);
		//发送短信
//		if("06".equals(projectStatus)){
//			SmsCallUtils.smsCall("projectAccept", id);
//		}
		inInfo.setMsg("操作成功");
		return inInfo;
	}

}
