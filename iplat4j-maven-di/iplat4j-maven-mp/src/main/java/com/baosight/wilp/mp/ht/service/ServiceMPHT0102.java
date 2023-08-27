package com.baosight.wilp.mp.ht.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.mp.common.MpConstant;
import com.baosight.wilp.mp.common.MpUtils;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlan;
import com.baosight.wilp.mp.lj.domain.MpPurchasePlanDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 采购合同管理选择采购计划子页面
 * <p>1.初始化查询 initLoad
 * <p>2.采购计划明细查询
 * 
 * @Title: ServiceMPHT0102.java
 * @ClassName: ServiceMPHT0102
 * @Package：com.baosight.wilp.mp.ht.service
 * @author: lyf
 * @date: 2022年10月19日 上午10:48:30
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 */
public class ServiceMPHT0102 extends ServiceBase{

    /**
     * 页面初始化
     * @Title: initLoad
     * @Description:
     * @param
     * @return info
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
		Map<String, Object> deptMap = BaseDockingUtils.getDeptByworkNo(UserSession.getLoginName());
		info.setCell(MpConstant.QUERY_BLOCK, 0, "deptNum", deptMap.get("deptNum"));
		info.setCell(MpConstant.QUERY_BLOCK, 0, "deptName", deptMap.get("deptName"));
		info.setCell(MpConstant.QUERY_BLOCK, 0, "recCreator", UserSession.getLoginName());
		info.setCell(MpConstant.QUERY_BLOCK, 0, "recCreatorName", UserSession.getLoginCName());
		info.setCell(MpConstant.QUERY_BLOCK, 0, "statusCodes", "30,40");
		// 设置info中的id
		MpUtils.initQueryTime(info,"recCreateTimeS","recCreateTimeE");
		return this.query(info);
	}

	/**
	 * 采购计划查询
	 * @Title: query
	 * @Description:
	 * @param
	 * @return info
	 */
	@Override
	public EiInfo query(EiInfo info) {
		info.setCell(MpConstant.QUERY_BLOCK, 0, "statusCodes",
				info.getCellStr(MpConstant.QUERY_BLOCK, 0, "statusCodes").split(","));
		return super.query(info, "MPLJ01.query", new MpPurchasePlan());
	}

	/**
	 * 采购计划明细查询
	 * @Title: queryDetail
	 * @Description:
	 * @param
	 * @return info
	 */
	public EiInfo  queryDetail (EiInfo inInfo) {
		String purchaseId = inInfo.getCellStr(MpConstant.QUERY_BLOCK, 0, "procurementId");
		List<MpPurchasePlanDetail> details = dao.query("MPLJ01.queryDetail", new HashMap(4) {{
			put("purchaseId", purchaseId);
			put("notShowOver", "Y");
		}});
		inInfo.setRows("detail", details);
		return inInfo;
	}

}
