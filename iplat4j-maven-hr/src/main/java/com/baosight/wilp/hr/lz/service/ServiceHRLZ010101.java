package com.baosight.wilp.hr.lz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.hr.common.HrUtils;
import com.baosight.wilp.hr.xx.domain.HrMan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *人员选择
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 *
 * @Title: ServiceHRLZ010101.java
 * @ClassName: ServiceHRLZ010101
 * @Package：com.baosight.wilp.hr.lz.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRLZ010101 extends ServiceBase {
    /**
     * 初始化查询
     * <p>Title: initLoad</p>
     * <p>Description: </p>
     * @param info
     * @return outInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo info){

		return this.query(info);
	}
	/**
	 * 初始化查询
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * flag  ：rz  查询入职且未登记预离职的人员
	 * @return outInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo info){
		// 设置flag值为rz，查询入职且未登记预离职的人员
		info.set("inqu_status-0-statusCode","02");
		if(!"DP".equals(info.getString("view"))){
			Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
			info.setCell("inqu_status", 0, "managementDeptNum", deptMap.get("deptName"));
		}
		EiInfo outInfo = super.query(info,"HRXX01.query",new HrMan());
		return outInfo;
	}
}
