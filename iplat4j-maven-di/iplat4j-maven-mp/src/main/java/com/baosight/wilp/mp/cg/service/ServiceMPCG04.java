package com.baosight.wilp.mp.cg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;

import java.util.Map;

/**
 *  采购计划审批页面Service
 * <p>页面加载</p>
 * <p>查询采购计划</p>
 *
 * All rights Reserved, Designed By www.bonawise.com
 * @Title:  ServiceMPCG04.java
 * @ClassName:  ServiceMPCG04
 * @Package com.baosight.wilp.mp.cg.service
 * @Description: TODO
 * @author lyf
 * @date:   2022年10月17日 上午10:36:16
 * @version V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 *
 */
public class ServiceMPCG04 extends ServiceBase {
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo initLoad(EiInfo info){
		//获取当前用户的用户组(角色)
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
		String userGroups = MpUtils.getUserGroups(UserSession.getLoginName());
		if(userGroups.contains(MpConstant.DEAN_ROLE)) {
			info.setCell(MpConstant.QUERY_BLOCK, 0, "statusCode", MpConstant.PROCUREMENT_STATUS_THREE);
		}
		if(userGroups.contains(MpConstant.LEADER_ROLE) && userGroups.contains(MpConstant.DEPT_ROLE)) {
			info.setCell(MpConstant.QUERY_BLOCK, 0, "roleAuth", "deptAndLeader");
			info.setCell(MpConstant.QUERY_BLOCK, 0, "curDept", deptMap.get("deptNum"));
			info.setCell(MpConstant.QUERY_BLOCK, 0, "depts", MpUtils.getBusinessDept(MpUtils.toString(deptMap.get("deptNum")), true));
		} else if(userGroups.contains(MpConstant.LEADER_ROLE) && !userGroups.contains(MpConstant.DEPT_ROLE)) {
			info.setCell(MpConstant.QUERY_BLOCK, 0,"roleAuth", "leader");
			info.setCell(MpConstant.QUERY_BLOCK, 0, "depts", MpUtils.getBusinessDept(MpUtils.toString(deptMap.get("deptNum")), true));
			info.setCell(MpConstant.QUERY_BLOCK, 0, "statusCode", MpConstant.PROCUREMENT_STATUS_SECOND);
		} else if(userGroups.contains(MpConstant.DEPT_ROLE)) {
			info.setCell(MpConstant.QUERY_BLOCK, 0,"roleAuth", "dept");
			info.setCell(MpConstant.QUERY_BLOCK, 0, "curDept", deptMap.get("deptNum"));
			info.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
			info.setCell(MpConstant.QUERY_BLOCK, 0, "statusCode", MpConstant.PROCUREMENT_STATUS_UN_APPROVAL);
		}
		// 设置info中的id
		MpUtils.initQueryTime(info,"recCreateTimeS","recCreateTimeE");
		UserSession.setOutRequestProperty("roleAuth", info.getCellStr(MpConstant.QUERY_BLOCK, 0,"roleAuth"));
		return this.query(info);
	}

	/**
	 *
	 * <p>Title: query</p>
	 * <p>Description: </p>
	 * @param info
	 * @return
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
	public EiInfo query(EiInfo info){
		return super.query(info, "MPCG01.query", new MpPurchasePlan());
	}



	/**
	 * 查询采购计划明细
	 * @Title: queryDetail
	 * @param inInfo inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo queryDetail(EiInfo inInfo) {
		return super.query(inInfo, "MPCG0401.queryDetail", new MpPurchasePlanDetail(), false, null,null,null, "detail");
	}
}
