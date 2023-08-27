package com.baosight.wilp.sq.bz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/**
 * <p>初始化方法 initLoad
 * <p>问卷标准管理子页面查询 query
 * <p>问卷录入——保存题目详情+题目选项 save
 * <p>保存问卷中题目insertSqProjectInstance
 * <p>保存问卷中题目的每个选项 insertSqInstanceItem
 * <p>更新问卷中题目 updateSqProjectInstance
 * <p>删除问卷中题目的每个选项 deleteSqInstanceItem
 * @author zhaowei
 * @date 2022/4/12 13:43
 */
public class ServiceSQBZ010101 extends ServiceBase {
	/**
	 * 初始化方法
	 * @author zhaowei
	 * @date 2022/3/14 14:02
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 获取参数信息，并进行封装
		 */
		Map<String, Object> map = new HashMap<>();
		String type = info.getString("type");
		String projectId = info.getString("projectId");
		String instanceId = info.getString("instanceId");
		/*
		 * 对操作类型进行判断，add-新增操作，edit-修改操作
		 */
		if ("add".equals(type)) {
			info.set("projectId", projectId);
			info.set("instanceId", UUID.randomUUID().toString());
		} else if ("edit".equals(type)) {
			map.clear();
			map.put("projectId", projectId);
			map.put("instanceId", instanceId);
			info.set("projectId", projectId);
			info.set("instanceId", instanceId);
			// 通过题目ID找题目
			List<Map<String, Object>> instanceList = dao.query("SQBZ010101.queryInstanceByInstanceId", map);
			if (CollectionUtils.isNotEmpty(instanceList)) {
				Map<String, Object> instanceMap = instanceList.get(0);
				info.set("orderNo", instanceMap.get("orderNo"));
				info.set("inqu_status-0-instanceName", instanceMap.get("instanceName"));
				info.set("inqu_status-0-pointType", instanceMap.get("pointType"));
				info.set("inqu_status-0-point", instanceMap.get("point"));
				info.set("type", type);
			}
			// 通过题目ID找选项
			List<Map<String, Object>> itemList = dao.query("SQBZ010101.queryItemByInstanceId", map);
			if (CollectionUtils.isNotEmpty(itemList)) {
				info.setRows("result", itemList);
			}
		}
		return info;
	}

	/**
	 * 问卷标准管理子页面查询
	 * @author zhaowei
	 * @date 2022/4/12 13:43
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo query(EiInfo info) {
		return info;
	}

	/**
	 * 问卷录入——保存题目详情+题目选项
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/8 11:01
	 */
	public EiInfo save(EiInfo info) {
		/*
		 * 1.获取前端提交数据，对数据进行处理
		 */
		Map<String, Object> map = new HashMap<>();
		String projectId = info.getString("projectId");
		String instanceId = info.getString("instanceId");
		String instanceName = info.getString("instanceName");
		String pointType = info.getString("pointType");
		String pointName = info.getString("pointName");
		String point = StringUtils.isNotEmpty(info.getString("point")) ? info.getString("point") : "0";
		String orderNo = info.getString("orderNo");
		map.put("projectId", projectId);
		map.put("instanceId", instanceId);
		map.put("instanceName", instanceName);
		map.put("pointType", pointType);
		map.put("pointName", pointName);
		map.put("point", Integer.parseInt(point));
		map.put("orderNo", Integer.parseInt(orderNo));
		Object itemObject = info.get("item");
		// 新增或者编辑
		String type = info.getString("type");
		if ("add".equals(type)) {
			// 新增数据到题目表
			insertSqProjectInstance(map);
		} else if ("edit".equals(type)) {
			// 更新题目表数据
			updateSqProjectInstance(map);
			// 删除该题目下的所有选项
			deleteSqInstanceItem(map);
		}
		// 新增数据到选项表
		insertSqInstanceItem(itemObject);
		return info;
	}

	/**
	 * 保存问卷中题目
	 *
	 * @param map
	 * @author zhaowei
	 * @date 2022/3/8 17:46
	 */
	public void insertSqProjectInstance(Map<String, Object> map) {
		dao.insert("SQBZ010101.insertSqProjectInstance", map);
	}

	/**
	 * 保存问卷中题目的每个选项
	 *
	 * @param itemObject
	 * @author zhaowei
	 * @date 2022/3/8 11:09
	 */
	public void insertSqInstanceItem(Object itemObject) {
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) itemObject;
		if (CollectionUtils.isNotEmpty(itemList)) {
			for (Map<String, Object> itemMap : itemList) {
				itemMap.put("id", UUID.randomUUID().toString());
				dao.insert("SQBZ010101.insertSqInstanceItem", itemMap);
			}
		}
	}

	/**
	 * 更新问卷中题目
	 *
	 * @param map
	 * @author zhaowei
	 * @date 2022/3/9 16:30
	 */
	public void updateSqProjectInstance(Map<String, Object> map) {
		dao.update("SQBZ010101.updateSqProjectInstance", map);
	}

	/**
	 * 删除问卷中题目的每个选项
	 *
	 * @param map
	 * @author zhaowei
	 * @date 2022/3/9 16:31
	 */
	public void deleteSqInstanceItem(Map<String, Object> map) {
		dao.delete("SQBZ010101.deleteSqInstanceItem", map);
	}

}
