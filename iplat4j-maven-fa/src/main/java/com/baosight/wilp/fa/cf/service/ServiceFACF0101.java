package com.baosight.wilp.fa.cf.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 固定资产拆分管理详情类
 * 固定资产拆分管理详情初始化发方法
 * 固定资产拆分管理详情查询方法
 * 固定资产按数量拆分保存方法
 * @author zhaowei
 * @date 2022年07月13日 9:52
 */
public class ServiceFACF0101 extends ServiceBase {
	/**
	 * 固定资产拆分管理详情初始化发方法
	 * 1.查询固定资产拆分管理信息
	 * 2.查询固定资产拆分管理按数量拆分详情信息
	 * @author zhaowei
	 * @date 2022/8/25 18:59
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.查询固定资产拆分管理信息
		List<Map<String, Object>> faInfoInfoList = dao.query("FADA01.query", info.getAttr());
		info.setAttr(faInfoInfoList.get(0));
		// 2.查询固定资产拆分管理按数量拆分详情信息
		List<Map<String, Object>> querySplitTabList = dao.query("FACF01.querySplitTab", info.getAttr());
		info.addRows("resultSplitByNumber", querySplitTabList);
		return info;
	}

	/**
	 * 固定资产拆分管理详情查询方法.
	 * @author zhaowei
	 * @date 2022/8/26 13:31
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 固定资产按数量拆分保存方法.
	 * 1.在主表中填写拆分理由
	 * 2.在主表中新增拆分信息
	 * 3.在拆分表中保存
	 * @author zhaowei
	 * @date 2022/8/26 13:31
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo saveSplitByNumberInfo(EiInfo info) {
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		info.set("recCreator", staffByUserId.get("workNo"));
		info.set("recCreateName", staffByUserId.get("name"));
		info.set("recCreateTime", DateUtils.curDateTimeStr19());
		info.set("splitWay", "Number");
		// 查看是否已经被拆分
		List splitInfoList = dao.query("FADA01.querySplitInfo", info.getAttr());
		if (CollectionUtils.isNotEmpty(splitInfoList)) {
			info.setStatus(-1);
			info.setMsg("固定资产已被拆分！");
			return info;
		}
		// 在主表中填写拆分理由
		dao.update("FADA01.updateSplitReason", info.getAttr());
		// 在主表中新增拆分信息
		dao.insert("FADA01.saveSplitInfo", info.getAttr());
		// 在拆分表中保存
		dao.insert("FACF01.saveSplitByNumberInfo", info.getAttr());
		return info;
	}
}
