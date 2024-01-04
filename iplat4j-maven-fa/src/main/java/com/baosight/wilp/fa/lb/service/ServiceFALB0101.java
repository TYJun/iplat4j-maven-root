package com.baosight.wilp.fa.lb.service;

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
 * 固资类别管理录入逻辑类.
 * 1.固资类别管理录入初始化方法.
 * 2.固资类别录入保存方法.
 * 3.通过Excel表格保存固定资产分类.
 * 4.固资类别保存方法.
 * 5.固资类别编辑方法.
 * @author zhaowei
 * @date 2022年05月17日 18:23
 * @version v5.0.0
 */
public class ServiceFALB0101 extends ServiceBase {
	/**
	 * 固资类别管理录入初始化方法
	 * 1.获取操作类型.
	 * 1.1.新增操作回显选中根节点.
	 * 1.2.编辑操作回显选中节点的信息.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/23 15:36
	 * @version v5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.获取操作类型并进行逻辑判断
		String type = info.getString("type");
		switch (type){
			// 1.1.新增操作回显选中根节点
			case "enter":
				info.set("parentId", info.getString("typeId"));
				info.set("parentName", info.getString("typeName"));
				info.set("inqu_status-0-useYears", 1);
				break;
			// 1.2.编辑操作回显选中节点的信息
			case "edit":
				List<Map<String, Object>> aessettypeDetailInfoList = dao.query("FALB01.queryAessettypeDetailInfo", info.getAttr());
				if (CollectionUtils.isNotEmpty(aessettypeDetailInfoList)) {
					info.set("parentId", aessettypeDetailInfoList.get(0).get("parentId"));
					info.set("parentName", aessettypeDetailInfoList.get(0).get("parentName"));
					info.set("inqu_status-0-faTypeId", aessettypeDetailInfoList.get(0).get("faTypeId"));
					info.set("inqu_status-0-archiveFlag", aessettypeDetailInfoList.get(0).get("archiveFlag"));
					info.set("inqu_status-0-typeName", aessettypeDetailInfoList.get(0).get("typeName"));
					info.set("inqu_status-0-remark", aessettypeDetailInfoList.get(0).get("remark"));
					info.set("inqu_status-0-useYears", aessettypeDetailInfoList.get(0).get("useYears"));
					info.set("inqu_status-0-codeRule", aessettypeDetailInfoList.get(0).get("codeRule"));
					info.set("inqu_status-0-sortNo", aessettypeDetailInfoList.get(0).get("sortNo"));
				}
				break;
		}
		return info;
	}

	@Override
	public EiInfo query(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 固资类别录入保存方法
	 * 1.获取前端操作类型并处理前端传参数据.
	 * 2.判断根节点是否为root,通过根节点查询固定资产类别信息.
	 * 3.获取用户信息.
	 * 4.通过操作类型进入不同的操作分支.
	 * 4.1.保存分支，调用保存方法.
	 * 4.2.编辑分支，调用更新方法.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/23 10:05
	 * @version v5.0.0
	 */
	public EiInfo saveAessettypeInfo(EiInfo info) {
		// 1.获取前端操作类型并处理前端传参数据
		String type = info.getString("type");
		// 处理前端传参数据
		Map<String, Object> aessettypeInfoMap = info.getBlock("inqu_status").getRow(0);
		// 2.判断根节点是否为root
		if (aessettypeInfoMap.get("parentId").equals("root")) {
			aessettypeInfoMap.put("level", "1");
		} else {
			// 通过根节点查询固定资产类别信息
			List<Map<String, Object>> aessettypeInfoList = dao.query("FALB01.queryAessettypeInfoByParentId", aessettypeInfoMap);
			aessettypeInfoMap.putAll(aessettypeInfoList.get(0));
		}
		// 3.获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 4.通过操作类型进入不同的操作分支
		switch (type) {
			// 4.1.保存分支
			case "enter":
				aessettypeInfoMap.put("recCreator", staffByUserId.get("workNo"));
				aessettypeInfoMap.put("recCreateName", staffByUserId.get("name"));
				aessettypeInfoMap.put("recCreateTime", DateUtils.curDateTimeStr19());
				aessettypeInfoMap.put("id", UUID.randomUUID().toString());
				aessettypeInfoMap.put("typeCode", OneSelfUtils.privateCreateTypeCode((String) aessettypeInfoMap.get("codeRule"),(String) aessettypeInfoMap.get("parentId")));
				aessettypeInfoMap.put("dataGroupCode", DatagroupUtil.getDatagroupCode());
				aessettypeInfoMap.put("archiveFlag", 1);
				aessettypeInfoMap.put("isLeaf", "N");
				// 调用保存方法
				saveAessettypeInfo(aessettypeInfoMap);
				break;
			// 4.2.编辑分支
			case "edit":
				aessettypeInfoMap.put("recRevisor", staffByUserId.get("workNo"));
				aessettypeInfoMap.put("recReviseName", staffByUserId.get("name"));
				aessettypeInfoMap.put("recReviseTime", DateUtils.curDateTimeStr19());
				// 调用更新方法
				editAessettypeInfo(aessettypeInfoMap);
				break;
		}
		return info;
	}

	/**
	 * 通过Excel表格保存固定资产分类
	 * 1.获取excel表格中的数据并进行保存操作.
	 * @author zhaowei
	 * @date 2022/8/26 13:26
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v5.0.0
	 */
	public EiInfo saveAessettypeByExcel(EiInfo info){
		// 获取excel表格中的数据并进行保存操作
		List<Map<String,Object>> aessettypeList = info.getBlock("info").getRows();
		dao.insert("FALB01.saveAessettypeByExcel",aessettypeList);
		dao.update("FALB01.updateParentId",new HashMap<>());
		return info;
	}


	/**
	 * 固资类别保存方法
	 * @param aessettypeInfoMap
	 * @author zhaowei
	 * @date 2022/5/23 15:36
	 * @version v5.0.0
	 */
	public void saveAessettypeInfo(Map<String, Object> aessettypeInfoMap) {
		try {
			// 固定资产类别保存方法
			dao.insert("FALB01.saveAessettypeInfo", aessettypeInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 固资类别编辑方法
	 * @param aessettypeInfoMap
	 * @author zhaowei
	 * @date 2022/5/24 18:47
	 * @version v5.0.0
	 */
	public void editAessettypeInfo(Map<String, Object> aessettypeInfoMap) {
		try {
			dao.update("FALB01.editAessettypeInfo", aessettypeInfoMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}