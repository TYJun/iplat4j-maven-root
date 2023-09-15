package com.baosight.wilp.hr.xx.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hr.xx.domain.HrMan;

import java.util.HashMap;
import java.util.Map;

/**
 *人员配置页面
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 *
 * @Title: ServiceHRXX02.java
 * @ClassName: ServiceHRXX02
 * @Package：com.baosight.wilp.hr.xx.service
 * @author: gcc
 * @date: 2022年4月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceHRXX02 extends ServiceBase {
	/**
	 * 初始化查询
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo initLoad(EiInfo info) {
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
		EiBlock eiBlock = new EiBlock("result");
		eiBlock.setAttr(new HashMap(){{
			put("showCount","true");
			put("offset","0");
			put("count","0");
			put("limit","50");
			put("orderBy","");
		}});
		info.setBlock(eiBlock);
		if (!"admin".equals(UserSession.getLoginName())) {
			info.setCell("inqu_status", 0, "managementDeptNum", deptMap.get("deptName"));
		}
		return this.query(info);
	}

	/**
	 * 页面查询
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * workNo : 工号
	 * name   : 姓名
	 * jobCode : 岗位
	 * company : 公司名称
	 * statusCode :状态
	 * deptNum   :所属科室
	 * @return
	 * @see ServiceBase#query(EiInfo)
	 */
	public EiInfo query(EiInfo info) {
		EiInfo outInfo = super.query(info,"HRXX01.query",new HrMan());
		return outInfo;
	}
}
