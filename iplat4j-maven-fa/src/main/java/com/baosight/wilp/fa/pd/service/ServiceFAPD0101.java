package com.baosight.wilp.fa.pd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 固定资产盘点详情接口.
 * 1.固定资产盘点初始化方法.
 * 2.固定资产盘点查询方法.
 * 3.保存固定资产盘点信息.
 * 4.移除固定资产盘点信息.
 * 5.确认固定资产盘点信息.
 * 6.查询固定资产盘点详情.
 *
 * @author zhaowei
 * @version v5.0.0
 * @date 2022年05月31日 14:19
 */
public class ServiceFAPD0101 extends ServiceBase {
	/**
	 * 固定资产盘点初始化方法.
	 * 1.获取操作类型.
	 * 1.1.录入分支.
	 * 1.2.编辑分支.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/9 11:05
	 * @version v5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.获取操作类型
		String type = info.getString("type");
		switch (type) {
			// 1.1.录入分支
			case "enter":
				break;
			// 1.2.编辑分支
			case "edit":
				List<Map<String, Object>> queryFaInventoryInfoList = dao.query("FAPD01.queryFaInventoryInfo", info.getAttr());
				info.set("info-0-faInventoryId", queryFaInventoryInfoList.get(0).get("faInventoryId"));
				info.set("info-0-inventoryNo", queryFaInventoryInfoList.get(0).get("inventoryNo"));
				info.set("info-0-checkDeptNum", queryFaInventoryInfoList.get(0).get("inventoryDeptNum"));
				info.set("info-0-checkDeptName", queryFaInventoryInfoList.get(0).get("inventoryDeptName"));
				info.set("deptName", queryFaInventoryInfoList.get(0).get("inventoryDeptName"));
//				info.set("info-0-inventoryDeptNum", queryFaInventoryInfoList.get(0).get("inventoryDeptNum"));
//				info.set("info-0-inventoryDeptNum_textField", queryFaInventoryInfoList.get(0).get("inventoryDeptName"));
				info.set("info-0-newBuild", queryFaInventoryInfoList.get(0).get("build"));
				info.set("info-0-newFloor", queryFaInventoryInfoList.get(0).get("floor"));
				info.set("info-0-newGoodsLocationNum", queryFaInventoryInfoList.get(0).get("inventorySpotNum"));
				info.set("info-0-newGoodsLocation", queryFaInventoryInfoList.get(0).get("inventorySpotName"));
				info.set("newBuild", queryFaInventoryInfoList.get(0).get("build"));
				info.set("newFloor", queryFaInventoryInfoList.get(0).get("floor"));
				info.set("newGoodsLocationNum", queryFaInventoryInfoList.get(0).get("inventorySpotNum"));
				info.set("newGoodsLocation", queryFaInventoryInfoList.get(0).get("inventorySpotName"));
				info.set("installLocation", queryFaInventoryInfoList.get(0).get("inventorySpotName"));
				info.set("info-0-remark", queryFaInventoryInfoList.get(0).get("remark"));
				info.set("info-0-archiveFlag", queryFaInventoryInfoList.get(0).get("archiveFlag"));
				break;
		}
		return info;
	}

	/**
	 * 固定资产盘点查询方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/9 11:21
	 * @version v5.0.0
	 */
	@Override
	public EiInfo query(EiInfo info) {
		return info;
	}

	/**
	 * 保存固定资产盘点信息.
	 * 1.获取操作类型并封装参数.
	 * 2.判断固定资产是否处于盘库状态.
	 * 3.根据操作类型进行判断.
	 * 3.1.录入分支.
	 * 3.2.编辑分支.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/9 13:57
	 * @version v5.0.0
	 */
	public EiInfo saveFaInventoryInfo(EiInfo info) {
		// 1.获取操作类型并封装参数.
		String type = info.getString("type");
		Map<String, Object> params = info.getRow("info", 0);
		// EFPopupInput获取参数显示值方法
//		params.put("inventoryDeptName",params.get("inventoryDeptNum_textField"));
		params.put("inventoryDeptNum", params.get("checkDeptNum"));
		params.put("inventoryDeptName", params.get("checkDeptName"));
		// 用于校验科室是否存在盘点单
		params.put("inventorySpotNum", params.get("confirmLocationNum"));
		params.put("newGoodsLocation", params.get("confirmLocationNum_textField"));
		params.put("newGoodsLocationNum", params.get("confirmLocationNum"));
		params.put("dealFlag", 0);
		params.put("subNo", OneSelfUtils.publicCreateCode(FixedAssetsEum.PDZX.getAcronym()));
		// 2.判断固定资产是否处于盘库状态
		if (OneSelfUtils.existsLock(params)) {
			info.setStatus(-1);
			info.setMsg("该科室正在进行盘库，生成盘点单请等盘库结束！");
			return info;
		}
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 3.根据操作类型进行判断
		switch (type) {
			// 3.1.录入分支
			case "enter":
				params.put("faInventoryId", UUID.randomUUID().toString().replace("-", ""));
				params.put("inventoryNo", OneSelfUtils.publicCreateCode(FixedAssetsEum.PD.getAcronym()));
				params.put("recCreator", staffByUserId.get("workNo"));
				params.put("recCreateName", staffByUserId.get("name"));
				params.put("recCreateTime", DateUtils.curDateTimeStr19());
				params.put("dataGroupCode", DatagroupUtil.getDatagroupCode());
				params.put("statusCode", 0);
				params.put("archiveFlag", 1);
				params.put("singleSystemMen", staffByUserId.get("workNo"));
				params.put("singleSystemName", staffByUserId.get("name"));
				params.put("singleSystenDate", DateUtils.curDateTimeStr19());
				dao.insert("FAPD01.saveFaInventoryInfo", params);
				break;
			// 3.2.编辑分支
			case "edit":
				params.put("recRevisor", staffByUserId.get("workNo"));
				params.put("recReviseName", staffByUserId.get("name"));
				params.put("recReviseTime", DateUtils.curDateTimeStr19());
				dao.update("FAPD01.updateFaInventoryInfo", params);
				dao.delete("FAPD01.removeFaInventoryDetailInfo", params);
				break;
		}
		// 保存固定资产盘点信息
		dao.insert("FAPD01.saveFaInventoryDetailInfo", params);
		dao.update("FAPD01.updateFaInventoryBeforeInvenNum", params);
		return info;
	}

	/*
	 * 移除固定资产盘点信息
	 * @author zhaowei
	 * @date 2022/8/25 19:14
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v5.0.0
	 */
	public EiInfo removeFaInventoryInfo(EiInfo info) {
		dao.delete("FAPD01.removeFaInventoryDetailInfoList", info.getAttr());
		dao.delete("FAPD01.removeFaInventoryInfo", info.getAttr());
		return info;
	}

	/*
	 * 确认固定资产盘点信息.
	 * @author zhaowei
	 * @date 2022/8/25 19:14
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v5.0.0
	 */
	public EiInfo confirmFaInventoryInfo(EiInfo info) {
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		info.set("checkMen", staffByUserId.get("workNo"));
		info.set("checkMenName", staffByUserId.get("name"));
		info.set("checkDate", DateUtils.curDateTimeStr19());
		List<String> faInventoryNo = (List<String>) info.getAttr().get("faInventoryNo");
		if (faInventoryNo.size() > 0) {
			// 更新盘点单状态
			dao.update("FAPD01.confirmFaInventoryInfo", info.getAttr());
			dao.insert("FAPD01.insertFaInventoryInfoFinal", info.getAttr());
			for (String s : faInventoryNo) {
				// 更新盘点单数量
				dao.update("FAPD01.updateFaInventoryAfterInvenNum", new HashMap<String, String>() {{
					put("faInventoryNo", s);
				}});
				// 更新资产信息
//				dao.update("FAPD01.updateFaInfo",new HashMap<String,String>(){{
//					put("faInventoryNo", s);
//				}});
			}
		}
		return info;
	}

	/**
	 * 查询固定资产盘点详情.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/13 16:35
	 */
	public EiInfo queryInventoryDetailTabInfo(EiInfo info) {
		List<Map<String, Object>> queryInventoryDetailFinalTabInfoList = dao.query("FAPD01.queryInventoryDetailFinalTabInfo", info.getAttr());
		List<Map<String, Object>> queryInventoryDetailTabInfoList = dao.query("FAPD01.queryInventoryDetailTabInfo", info.getAttr());
		if (CollectionUtils.isNotEmpty(queryInventoryDetailFinalTabInfoList)) {
			info.setRows("resultInventoryDetail", queryInventoryDetailFinalTabInfoList);
		} else {
			info.setRows("resultInventoryDetail", queryInventoryDetailTabInfoList);
		}
		return info;
	}
}
