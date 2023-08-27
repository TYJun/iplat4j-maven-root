package com.baosight.wilp.fa.bs.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.fa.utils.FixedAssetsEum;
import com.baosight.wilp.fa.utils.OneSelfUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
 * 固定资产报损管理逻辑类.
 * 固定资产报损初始化方法.
 * 固定资产报损录入方法.
 * 固定资产报损删除方法.
 * 固定资产报损确认方法.
 * @author zhaowei
 * @date 2022/8/26 14:52
 */
public class ServiceFABS0101 extends ServiceBase{

	/**
	 * 固定资产报损初始化方法.
	 * 1.获取前端传递的操作类型.
	 * 1.1.录入分支.
	 * 1.2.编辑分支.
	 * @author zhaowei
	 * @date 2022/6/5 12:42
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.获取前端传递的操作类型
		String type = info.getString("type");
		switch (type) {
			// 1.1.录入分支
			case "enter":
				info.set("info-0-confirmFlag", "录入");
				break;
			// 1.2.编辑分支
			case "edit":
				List<Map<String,Object>> queryFaReimburseInfoList = dao.query("FABS01.queryFaReimburseInfo", info.getAttr());
				info.set("info-0-faReimburseId", queryFaReimburseInfoList.get(0).get("faReimburseId"));
				info.set("info-0-archiveFlag", queryFaReimburseInfoList.get(0).get("archiveFlag"));
				info.set("info-0-reimburseNo", queryFaReimburseInfoList.get(0).get("reimburseNo"));
				info.set("info-0-confirmFlag", queryFaReimburseInfoList.get(0).get("confirmFlagMean"));
				info.set("info-0-goodsId", queryFaReimburseInfoList.get(0).get("goodsId"));
				info.set("info-0-goodsNum", queryFaReimburseInfoList.get(0).get("goodsNum"));
				info.set("info-0-goodsNum_textField", queryFaReimburseInfoList.get(0).get("goodsNum"));
				info.set("info-0-goodsName", queryFaReimburseInfoList.get(0).get("goodsName"));
				info.set("info-0-model", queryFaReimburseInfoList.get(0).get("model"));
				info.set("info-0-manufacturer", queryFaReimburseInfoList.get(0).get("manufacturer"));
				info.set("info-0-buyCost", queryFaReimburseInfoList.get(0).get("buyCost"));
				info.set("info-0-useYears", queryFaReimburseInfoList.get(0).get("useYears"));
				info.set("info-0-buyDate", queryFaReimburseInfoList.get(0).get("buyDate"));
				info.set("info-0-useDate", queryFaReimburseInfoList.get(0).get("useDate"));
				info.set("info-0-deptName", queryFaReimburseInfoList.get(0).get("deptName"));
				info.set("info-0-installLocation", queryFaReimburseInfoList.get(0).get("installLocation"));
				info.set("info-0-reimburseDate", queryFaReimburseInfoList.get(0).get("reimburseDate"));
				info.set("info-0-finishTime", queryFaReimburseInfoList.get(0).get("finishTime"));
				info.set("info-0-reimburseReason", queryFaReimburseInfoList.get(0).get("reimburseReason"));
				info.set("info-0-remark", queryFaReimburseInfoList.get(0).get("remark"));
				break;
		}
		return info;
	}


	/**
	 * 固定资产报损录入方法.
	 * 1.获取操作类型并判断操作分支.
	 * 1.1.录入分支.
	 * 1.2.编辑分支.
	 * @author zhaowei
	 * @date 2022/6/5 12:43
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo saveFaReimburseInfo(EiInfo info){
		// 1.获取操作类型并判断操作分支
		String type = info.getString("type");
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		Map<String, Object> params = info.getRow("info", 0);
		params.put("faInfoId", params.get("goodsId"));
		switch (type){
			// 1.1.录入分支
			case "enter":
				params.put("confirmFlag", "0");
				params.put("archiveFlag", 1);
				params.put("faReimburseId", UUID.randomUUID().toString().replace("-",""));
				params.put("reimburseNo", OneSelfUtils.publicCreateCode(FixedAssetsEum.BS.getAcronym()));
				params.put("recCreator", staffByUserId.get("workNo"));
				params.put("recCreateName", staffByUserId.get("name"));
				params.put("recCreateTime", DateUtils.curDateTimeStr19());
				dao.insert("FABS01.saveFaReimburseInfo", params);
				dao.update("FADB01.updateFaInfoLock", params);
				break;
			// 1.2.编辑分支
			case "edit":
				params.put("recRevisor", staffByUserId.get("workNo"));
				params.put("recReviseName", staffByUserId.get("name"));
				params.put("recReviseTime", DateUtils.curDateTimeStr19());
				dao.update("FABS01.updateFaReimburseInfo", params);
				break;
		}
		return info;
	}

	/**
	 * 固定资产报损删除方法.
	 * 1.删除固定资产报损信息并更新固定资产使用状态
	 * @author zhaowei
	 * @date 2022/6/7 13:44
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo removeFaReimburseInfo(EiInfo info){
		// 1.删除固定资产报损信息并更新固定资产使用状态
		dao.delete("FABS01.removeFaReimburseInfo",info.getAttr());
		dao.update("FADA01.updateFaInfoUnlock", info.getAttr());
		return info;
	}
	
	/**
	 * 固定资产报损确认方法.
	 * 1.修改固定资产报损信息并更新固定资产使用状态
	 * @author zhaowei
	 * @date 2022/6/7 13:44
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo confirmFaReimburseInfo(EiInfo info){
		// 1.修改固定资产报损信息并更新固定资产使用状态
		dao.update("FABS01.confirmFaReimburseInfo",info.getAttr());
		dao.update("FABS01.updateFaInfoUnlock", info.getAttr());
		return info;
	}
}

