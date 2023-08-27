package com.baosight.wilp.pm.pg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.util.CreateNoUtil;
import com.baosight.wilp.util.DateUtilsTools;
import com.baosight.wilp.util.EasyIplat4jUtil;
import com.baosight.wilp.util.ProjectEnum;
import com.baosight.wilp.utils.DatagroupUtil;
import com.baosight.xservices.xs.util.UserSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicePMPG02 extends ServiceBase {
	// 初始化查询
	public EiInfo initLoad(EiInfo info) {
		return this.queryPmTypeMsg(info);
	}

	/**
	 * 查询项目类型配置信息
	 * 入参：
	 * typeCode：项目类型编码
	 * typeName：项目类型名称
	 * 出参：
	 * typeId：工程项目类型Id
	 * typeCode：工程项目类型编码
	 * typeName：工程项目类型名称
	 * typeRemark：工程项目类型备注
	 * stageCode：绑定工程项目阶段编码
	 * stageName：绑定工程项目阶段名称
	 * createNo：创建人工号
	 * createName：创建人名称
	 * createTime：创建时间
	 * updateNo：修改人工号
	 * updateName：修改人名称
	 * updateTime：修改时间
	 */
	public EiInfo queryPmTypeMsg(EiInfo info) {
		/**
		 * 1.获取账套信息、登录信息
		 */
		// 获取账套信息
		DatagroupUtil.getDatagroupCode();
		// 获取用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String loginName = (String) staffByUserId.get("name");
		/**
		 * 2.获取入参参数
		 */
		// 获取前端查询参数
		String[] queryCondition = {"typeCode", "typeName"};
		/**
		 * 3.将获取的信息存储进map
		 */
		//获取分页信息
		Map<String, Object> map = EasyIplat4jUtil.queryConditionToMap(info, "result", Arrays.asList(queryCondition));
		// 调用sql查询方法获取信息
		List list = dao.query("PMPG02.queryPmTypeMsg", map, Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
		//
		Integer count = dao.count("PMPG02.queryPmTypeMsg", map);
		// 将查询信息封装返回
		return EasyIplat4jUtil.BuildOutEiInfo(info, null, null, list, count);
	}

	/**
	 * 新增项目阶段配置信息
	 * 入参：
	 * typeCode：
	 * typeName：
	 * <p>
	 * 出参：
	 * 无
	 */
	public EiInfo insertPmTypeMsg(EiInfo info) {
		// 1.获取账套信息、登录信息
		// 获取账套信息
		DatagroupUtil.getDatagroupCode();
		// 获取用户信息
		String createNo = UserSession.getUser().getUsername();
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String createName = (String) staffByUserId.get("name");
		// 获取入参参数与分页信息
		Map<String, Object> map = new HashMap<>();
		String id = CreateNoUtil.CreateUUID32();
		String typeCode = CreateNoUtil.CreateStageNo(ProjectEnum.typeCode);
		String typeName = (String) info.get("typeName");
		String typeRemark = (String) info.get("typeRemark");
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) info.get("resultList");
		Map<String, Object> resultMap = EasyIplat4jUtil.listToMap(resultList);
		String createTime = DateUtilsTools.getCurrentTime24();
		String stageCode = EasyIplat4jUtil.listToString((List<String>) resultMap.get("stageCode"));
		String stageName = EasyIplat4jUtil.listToString((List<String>) resultMap.get("stageName"));
		String stageRemark = EasyIplat4jUtil.listToString((List<String>) resultMap.get("stageRemark"));
		// 将获取的信息存储进map
		map.put("id", id);
		map.put("typeCode", typeCode);
		map.put("typeName", typeName);
		map.put("typeRemark", typeRemark);
		map.put("stageCode", stageCode);
		map.put("stageName", stageName);
		map.put("stageRemark", stageRemark);
		map.put("createNo", createNo);
		map.put("createName", createName);
		map.put("createTime", createTime);
		// 调用sql查询方法获取信息
		dao.insert("PMPG02.insertPmTypeMsg", map);
		// 将查询信息封装返回
		return info;
	}

	/**
	 * 编辑项目类型配置信息
	 * 入参：
	 * id：项目阶段id
	 * typeName：类型名称
	 * typeRemark：类型备注
	 * 出参：
	 * 无
	 */
	public EiInfo editPmTypeMsg(EiInfo info) {
		// 获取入参参数与分页信息
		String updateNo = UserSession.getUser().getUsername();
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String updateName = (String) staffByUserId.get("name");
		//
		Map<String, Object> map = new HashMap<>();
		String id = (String) info.get("id");

		String typeName = (String) info.get("typeName");
		String typeRemark = (String) info.get("typeRemark");
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) info.get("resultList");
		Map<String, Object> resultMap = EasyIplat4jUtil.listToMap(resultList);
		String stageCode = EasyIplat4jUtil.listToString((List<String>) resultMap.get("stageCode"));
		String stageName = EasyIplat4jUtil.listToString((List<String>) resultMap.get("stageName"));
		String stageRemark = EasyIplat4jUtil.listToString((List<String>) resultMap.get("stageRemark"));
		String updateTime = DateUtilsTools.getCurrentTime24();
		// 将获取的信息存储进map
		map.put("id", id);
		map.put("typeName", typeName);
		map.put("typeRemark", typeRemark);
		map.put("stageCode", stageCode);
		map.put("stageName", stageName);
		map.put("stageRemark", stageRemark);
		map.put("updateTime", updateTime);
		map.put("updateNo", updateNo);
		map.put("updateName", updateName);
		// 调用sql查询方法获取信息
		dao.update("PMPG02.editPmTypeMsg", map);
		// 将查询信息封装返回
		return info;
	}

	// 删除
	public EiInfo deletePmTypeMsg(EiInfo info) {
		// 将获取的信息存储进map
		Map<String, Object> map = new HashMap<>();
		String id = (String) info.get("id");
		// 调用sql查询方法获取信息
		map.put("id", id);
		// 将查询信息封装返回
		dao.delete("PMPG02.deletePmTypeMsg", map);
		return info;
	}
}
