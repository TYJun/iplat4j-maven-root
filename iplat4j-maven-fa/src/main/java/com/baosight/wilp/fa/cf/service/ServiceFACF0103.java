package com.baosight.wilp.fa.cf.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.fa.cf.domain.FaSplitVO;
import com.baosight.wilp.fa.utils.OneSelfUtils;

import java.util.List;
import java.util.Map;

/**
 * 固定资产拆分管理详情类.
 * 固定资产拆分管理详情初始化方法.
 * 固定资产拆分管理详情查询方法.
 * 固定资产拆分管理审批方法.
 * @author zhaowei
 * @date 2022年07月13日 9:52
 */
public class ServiceFACF0103 extends ServiceBase {
	/**
	 * 固定资产拆分管理详情初始化方法.
	 * 1.查询固定资产拆分管理信息
	 * 2.查询固定资产拆分管理按价值拆分详情信息
	 * @author zhaowei
	 * @date 2022/8/26 14:52
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		info.setCell("inqu_status", 0, "faInfoId", info.getString("faInfoId"));
		EiInfo out = super.query(info, "FACF01.queryFaInfoDOInfo", new FaSplitVO(), false, null, null, "info", "info");
		List<Map<String,String>> result = dao.query("FACF01.querySplitByValue", info.getString("faInfoId"));
		out.setRows("resultSplitByValue",result);
		return out;
	}

	/**
	 * 固定资产拆分管理详情查询方法.
	 * @author zhaowei
	 * @date 2022/8/26 14:52
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 固定资产拆分管理审批方法
	 * 拆分表改变状态
	 * 主表改变状态
	 * 主表新数据改变状态
	 * @author zhaowei
	 * @date 2022/8/26 14:52
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo approval(EiInfo info){
		// 拆分表改变状态
		dao.update("FACF01.splitByNumberOfApproval",info.getAttr());
		// 主表改变状态
		dao.update("FADA01.splitByNumberOfApproval",info.getAttr());
		// 主表新数据改变状态
		dao.update("FADA01.splitByNumberOfApprovalNew",info.getAttr());
		return info;
	}
}
