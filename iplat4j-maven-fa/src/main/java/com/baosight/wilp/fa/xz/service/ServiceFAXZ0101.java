package com.baosight.wilp.fa.xz.service;

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
 * 固定资产详情接口.
 * 1.固定资产闲置详情初始化接口.
 * 2.固定资产闲置录入接口.
 * 3.固定资产闲置删除接口.
 * 4.固定资产闲置确认接口.
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年05月31日 10:09
 */
public class ServiceFAXZ0101 extends ServiceBase {
	/**
	 * 固定资产闲置详情初始化接口.
	 * 1.获取前台传递的操作类型.
	 * 2.判断操作类型进入不同的分支.
	 * 2.1.录入分支.
	 * 2.2.编辑分支.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/31 10:09
	 * @version 5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 1.获取前台传递的操作类型
		 */
		String type = info.getString("type");
		/*
		 * 2.判断操作类型进入不同的分支
		 */
		switch (type) {
			/*
			 * 2.1.录入分支
			 * 返回录入状态返回前台
			 */
			case "enter":
				info.set("info-0-statusCode", "录入");
				break;
			/*
			 * 2.2.编辑分支
			 * 通过数据库操作查询固定资产闲置信息
			 * 封装数据返回前台
			 */
			case "edit":
				List<Map<String, Object>> queryFaIdleInfo = dao.query("FAXZ01.query", info.getAttr());
				info.set("info-0-faIdleId", queryFaIdleInfo.get(0).get("faIdleId"));
				info.set("info-0-archiveFlag", queryFaIdleInfo.get(0).get("archiveFlag"));
				info.set("info-0-idleNo", queryFaIdleInfo.get(0).get("idleNo"));
				info.set("info-0-statusCode", queryFaIdleInfo.get(0).get("statusCodeMean"));
				info.set("info-0-goodsId", queryFaIdleInfo.get(0).get("goodsId"));
				info.set("info-0-goodsNum", queryFaIdleInfo.get(0).get("goodsNum"));
				info.set("info-0-goodsNum_textField", queryFaIdleInfo.get(0).get("goodsName"));
				info.set("info-0-goodsName", queryFaIdleInfo.get(0).get("goodsName"));
				info.set("info-0-model", queryFaIdleInfo.get(0).get("model"));
				info.set("info-0-manufacturer", queryFaIdleInfo.get(0).get("manufacturer"));
				info.set("info-0-buyCost", queryFaIdleInfo.get(0).get("buyCost"));
				info.set("info-0-useYears", queryFaIdleInfo.get(0).get("useYears"));
				info.set("info-0-deptName", queryFaIdleInfo.get(0).get("deptName"));
				info.set("info-0-installLocation", queryFaIdleInfo.get(0).get("installLocation"));
				info.set("info-0-idleDate", queryFaIdleInfo.get(0).get("idleDate"));
				info.set("info-0-idleDirection", queryFaIdleInfo.get(0).get("idleDirection"));
				info.set("info-0-idleReason", queryFaIdleInfo.get(0).get("idleReason"));
				info.set("info-0-remark", queryFaIdleInfo.get(0).get("remark"));
				break;
		}
		return info;
	}

	/**
	 * 固定资产闲置录入接口.
	 * 1.获取前台传递的操作类型及数据.
	 * 2.通过前台获取的操作类型进入不同分支.
	 * 2.1.录入分支.
	 * 2.2.编辑分支.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/2 16:26
	 * @version 5.0.0
	 */
	public EiInfo saveFaIdleInfo(EiInfo info) {
		/*
		 * 1.获取前台传递的操作类型及数据
		 */
		String type = info.getString("type");
		Map<String, Object> params = info.getRow("info", 0);
		params.put("faInfoId", params.get("goodsId"));
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(com.baosight.xservices.xs.util.UserSession.getUser().getUsername());
		/*
		 * 2.通过前台获取的操作类型进入不同分支
		 */
		switch (type) {
			/*
			 * 2.1.录入分支
			 * 重新组装前端参数形成新的集合
			 * 插入固定资产闲置信息并将固定资产信息进行锁定
			 */
			case "enter":
				params.put("statusCode", "0");
				params.put("archiveFlag", 1);
				params.put("id", UUID.randomUUID().toString().replace("-", ""));
				params.put("idleNo", OneSelfUtils.publicCreateCode(FixedAssetsEum.XZ.getAcronym()));
				params.put("recCreator", staffByUserId.get("workNo"));
				params.put("recCreateName", staffByUserId.get("name"));
				params.put("recCreateTime", DateUtils.curDateTimeStr19());
				params.put("applyDeptNum", staffByUserId.get("deptNum"));
				params.put("applyDeptName", staffByUserId.get("deptName"));
				dao.insert("FAXZ01.saveFaIdleInfo", params);
				dao.update("FADB01.updateFaInfoLock", params);
				break;
			/*
			 * 2.2.编辑分支
			 * 重新组装前端参数形成新的集合
			 * 更新固定资产闲置信息
			 */
			case "edit":
				params.put("recRevisor", staffByUserId.get("workNo"));
				params.put("recReviseName", staffByUserId.get("name"));
				params.put("recReviseTime", DateUtils.curDateTimeStr19());
				params.put("applyDeptNum", staffByUserId.get("deptNum"));
				params.put("applyDeptName", staffByUserId.get("deptName"));
				dao.update("FAXZ01.updateFaIdleInfo", params);
				break;
		}
		return info;
	}

	/**
	 * 固定资产闲置删除接口.
	 * 1.移除固定资产闲置信息并将固定资产信息进行解锁.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/6 20:02
	 * @version 5.0.0
	 */
	public EiInfo removeFaIdleInfo(EiInfo info) {
		/*
		 * 1.移除固定资产闲置信息并将固定资产信息进行解锁
		 */
		dao.delete("FAXZ01.removeFaIdleInfo", info.getAttr());
		dao.update("FADA01.updateFaInfoUnlock", info.getAttr());
		return info;
	}

	/**
	 * 固定资产闲置确认接口.
	 * 1.审批固定资产闲置信息并将固定资产信息进行解锁.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/6 20:27
	 * @version 5.0.0
	 */
	public EiInfo confirmFaIdleInfo(EiInfo info) {
		/*
		 * 1.审批固定资产闲置信息并将固定资产信息进行解锁
		 */
		dao.update("FAXZ01.confirmFaIdleInfo", info.getAttr());
		dao.update("FAXZ01.updateFaInfoUnlock", info.getAttr());
		return info;
	}

}
