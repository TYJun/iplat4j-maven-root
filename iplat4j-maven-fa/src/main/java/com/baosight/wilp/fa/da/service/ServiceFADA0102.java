package com.baosight.wilp.fa.da.service;

import com.baosight.bpm.util.StringUtil;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.fa.bg.domain.FaModificationBatchDetailVO;
import com.baosight.wilp.fa.utils.OneSelfUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 固定资产档案详情方法.
 * 固定资产初始化方法.
 * 固定资产折旧履历方法.
 * 固定资产变更履历方法.
 * 固定资产拆分履历方法.
 * 固定资产调拨履历方法.
 * 固定资产闲置履历方法.
 * 固定资产报损履历方法.
 * 固定资产报废履历方法.
 *
 * @author zhaowei
 * @version v5.0.0
 * @date 2022年06月08日 15:54
 */
public class ServiceFADA0102 extends ServiceBase {

	/**
	 * 固定资产初始化方法.
	 * 1.查询固定资产信息并返回.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/8 17:35
	 * @version v5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.查询固定资产信息并返回
		List<Map<String, Object>> faInfoInfoList = dao.query("FADA01.query", info.getAttr());
		Map<String, Object> map = faInfoInfoList.get(0);
		info.setAttr(map);
		return info;
	}

	/**
	 * 固定资产折旧履历方法.
	 * 1.查询固定资产折旧履历信息并返回.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/7/18 8:34
	 * @version v5.0.0
	 */
	public EiInfo queryDepreciationTabInfo(EiInfo info) {
		// 1.查询固定资产折旧履历信息并返回
		List<Map<String, Object>> queryDepreciationTabInfoList = dao.query("FADA01.queryDepreciationTabInfo", info.getAttr());
		info.setRows("resultDepreciation", queryDepreciationTabInfoList);
		return info;
	}

	/**
	 * 固定资产变更履历方法.
	 * 1.查询固定资产变更履历信息并返回.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/7/18 8:34
	 * @version v5.0.0
	 */
	public EiInfo queryModificationTabInfo(EiInfo info) {
		// 1.查询固定资产变更履历信息并返回.
//		List query = dao.query("FADA01.queryModificationTabInfo", info.getAttr());
		info.setCell("inqu_status", 0, "faInfoId", info.getString("faInfoId"));
		List<Map<String, String>> faModificationRecordVOList = dao.query("FABG01.queryFaModificationRecordByFaInfoId", info.getString("faInfoId"));
		info.setRows("resultModification", faModificationRecordVOList);
		return info;
	}

	/**
	 * 固定资产拆分履历方法.
	 * 1.查询固定资产拆分履历信息并返回.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/7/22 17:58
	 * @version v5.0.0
	 */
	public EiInfo querySplitTabInfo(EiInfo info) {
		// 1.查询固定资产拆分履历信息并返回.
		List<Map<String, String>> result = dao.query("FACF01.querySplitByValue", info.getString("faInfoId"));
		info.setRows("resultSplit", result);
		return info;
	}

	/**
	 * 固定资产调拨履历方法.
	 * 1.查询固定资产调拨履历信息并返回.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/8 17:36
	 * @version v5.0.0
	 */
	public EiInfo queryTransferTabInfo(EiInfo info) {
		// 1.查询固定资产调拨履历信息并返回
		List<Map<String, Object>> queryTransferTabInfoList = dao.query("FADA01.queryTransferTabInfo", info.getAttr());
		info.setRows("resultTransfer", queryTransferTabInfoList);
		return info;
	}

	/**
	 * 固定资产闲置履历方法.
	 * 1.查询固定资产闲置履历信息并返回.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/8 17:36
	 * @version v5.0.0
	 */
	public EiInfo queryIdleTabInfo(EiInfo info) {
		// 1.查询固定资产闲置履历信息并返回
//		List<Map<String, Object>> queryIdleTabInfoList = dao.query("FADA01.queryIdleTabInfo", info.getAttr());
//		info.setRows("resultIdle", queryIdleTabInfoList);
		return info;
	}

	/**
	 * 固定资产报损履历方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/8 17:37
	 * @version v5.0.0
	 */
	public EiInfo queryReimburseTabInfo(EiInfo info) {
//		List<Map<String, Object>> queryReimburseTabInfoList = dao.query("FADA01.queryReimburseTabInfo", info.getAttr());
//		info.setRows("resultReimburse", queryReimburseTabInfoList);
		return info;
	}

	/**
	 * 固定资产报废履历方法
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/8 17:38
	 */
	public EiInfo queryScrapTabInfo(EiInfo info) {
		List<Map<String, Object>> queryScrapTabInfoList = dao.query("FADA01.queryScrapTabInfo", info.getAttr());
		info.setRows("resultScrap", queryScrapTabInfoList);
		return info;
	}

	/**
	 * 入库微服务查询
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/22 10:08
	 */
	public EiInfo queryPutInTabInfo(EiInfo info) {
		EiInfo eiInfo = new EiInfo();
		String enterBillNo = info.getString("enterBillNo");
		String matNum = info.getString("matNum");
		if (StringUtil.isNotEmpty(enterBillNo) && StringUtil.isNotEmpty(matNum)) {
			eiInfo.set("enterBillNo", enterBillNo);
			eiInfo.set("matNum", matNum);
			//EiInfo outInfo = OneSelfUtils.invoke(eiInfo,"SIJK04","queryEnter");
			EiInfo outInfo = OneSelfUtils.invoke(eiInfo, "S_SI_FA_01");
			if (outInfo != null) {
				String id = outInfo.getString("id");
				String enterTypeName = outInfo.getString("enterTypeName");
				String enterPerson = outInfo.getString("enterPerson");
				String enterTime = outInfo.getString("enterTime");
				String wareHouseName = outInfo.getString("wareHouseName");
				outInfo.setRows("resultPutIn", new ArrayList<Map>() {{
					Map<String, String> map = new HashMap<>();
					map.put("id", id);
					map.put("enterBillNo", enterBillNo);
					map.put("enterTypeName", enterTypeName);
					map.put("enterPerson", enterPerson);
					map.put("enterTime", enterTime);
					map.put("wareHouseName", wareHouseName);
					add(map);
				}});
				return outInfo;
			}
		}
		return info;
	}

	/**
	 * 出库微服务查询
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/22 10:09
	 */
	public EiInfo queryPutOutTabInfo(EiInfo info) {
		EiInfo eiInfo = new EiInfo();
		String outBillNo = info.getString("outBillNo");
		String matNum = info.getString("matNum");
		if (StringUtil.isNotEmpty(outBillNo) && StringUtil.isNotEmpty(matNum)) {
			eiInfo.set("outBillNo", outBillNo);
			eiInfo.set("matNum", matNum);
//			EiInfo outInfo = OneSelfUtils.invoke(eiInfo,"SIJK04","queryOut");
			EiInfo outInfo = OneSelfUtils.invoke(eiInfo, "S_SI_FA_02");
			if (outInfo != null) {
				String id = outInfo.getString("id");
				String outTypeName = outInfo.getString("outTypeName");
				String outPerson = outInfo.getString("outPerson");
				String outTime = outInfo.getString("outTime");
				String userDeptName = outInfo.getString("userDeptName");
				outInfo.setRows("resultPutOut", new ArrayList<Map>() {{
					Map<String, String> map = new HashMap<>();
					map.put("id", id);
					map.put("outBillNo", outBillNo);
					map.put("outTypeName", outTypeName);
					map.put("outPerson", outPerson);
					map.put("outTime", outTime);
					map.put("userDeptName", userDeptName);
					add(map);
				}});
				return outInfo;
			}
		}
		return info;
	}
}
