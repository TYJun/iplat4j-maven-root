package com.baosight.wilp.sq.bz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.sq.util.PMUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>满意度标准页面初始化方法 initLoad
 * <p>满意度标准子页面查询方法 query
 * <p>通过项目Id查询题目 queryInstanceByProjectId
 * <p>保存问卷 insertStandard
 * <p>保存问卷 insertStandard
 * <p>更新问卷 updateStandard
 * <p>删除项目 deleteProjectItems
 * <p>保存项目明细 insertProjectItems
 * <p>删除题目及选项 deleteInstanceAndItem
 * <p>删除项目+题目+选项 deleteProjectAndInstanceAndItem
 * <p>删除题目+选项 deleteInstanceAndItem
 * @author zhaowei
 * @date 2022年03月07日 10:53
 */
public class ServiceSQBZ0101 extends ServiceBase {
	/**
	 * 1、满意度标准页面初始化方法
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/7 10:54
	 */
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 获取前端参数
		 */
		String standardId;
		String id = info.getString("id");
		String type = info.getString("type");
		Map<String, Object> map = new HashMap<>();
		/*
		 * 对操作类型进行判断，add-新增操作，edit-修改操作
		 */
		if ("add".equals(type)) {
			// 创建uuid并回显
			standardId = UUID.randomUUID().toString();
			info.set("standardId", standardId);
			return info;
		} else if ("edit".equals(type)) {
			// 清空map防止数据污染
			map.clear();
			standardId = id;
			map.put("standardId", standardId);
			// 通过主题id查询项目信息，通过主题id查询题目信息
			List<Map<String, Object>> instance = dao.query("SQBZ0101.queryInstanceByStandardId", map);
			List<Map<String, Object>> project = dao.query("SQBZ0101.queryProjectByStandardId", map);
			// 不为空则将查到数据进行绑定返回
			if (CollectionUtils.isNotEmpty(instance)) {
				info.set("standardId", instance.get(0).get("standardId"));
				info.set("inqu_status-0-standardName", instance.get(0).get("standardName"));
				info.set("sqType", instance.get(0).get("assessTypeCode"));
				info.set("inqu_status-0-remark", instance.get(0).get("remark"));
			}
			// 不为空则将查到数据进行绑定返回
			if (CollectionUtils.isNotEmpty(project)) {
				info.setRows("resultProject", project);
				// 框架设置回显的最大条数，默认为10
				info.set("resultProject-limit", 1000);
			}
			return info;
		}
		return info;
	}

	/**
	 * 满意度标准子页面查询方法
	 * @author zhaowei
	 * @date 2022/4/12 13:39
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo query(EiInfo info) {
		return info;
	}

	/**
	 * 通过项目Id查询题目
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/10 14:11
	 */
	public EiInfo queryInstanceByProjectId(EiInfo info) {
		Map<String, Object> map = new HashMap<>();
		// 获取项目id
		String projectId = info.getString("pProjectId");
		map.put("projectId", projectId);
		// 通过项目id查询问题信息
		List list = dao.query("SQBZ0101.queryInstanceByProjectId", map);
		// 如果查询结果为空则返回为空，如果结果不为空则返回不为空
		if (CollectionUtils.isEmpty(list)) {
			info.setRows("resultProblem", null);
			return info;
		}
		info.setRows("resultProblem", list);
		// 框架设置回显的最大条数，默认为10
		info.set("resultProblem-limit", 1000);
		return info;
	}

	/**
	 * 保存问卷
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/11 13:49
	 */
	public EiInfo insertStandard(EiInfo info) {
		/*
		 * 获取参数信息，并进行封装
		 */
		Map<String, Object> map = new HashMap<>();
		String type = info.getString("type");
		String standardId = info.getString("standardId");
		String standardCode = PMUtils.createStandardCode();
		String standardName = info.getString("standardName");
		String sqType = info.getString("sqType");
		String sqTypeName = info.getString("sqTypeName");
		String remark = info.getString("remark");
		map.put("standardId", standardId);
		map.put("standardCode", standardCode);
		map.put("standardName", standardName);
		map.put("sqType", sqType);
		map.put("sqTypeName", sqTypeName);
		map.put("remark", remark);
		Object projectItems = info.get("ProjectItems");
		// 新增
		if ("add".equals(type)) {
			// 保存项目
			insertProjectItems(projectItems, map);
			// 保存问卷
			insertStandard(map);
		} else if ("edit".equals(type)) {
			// 更新项目,先删除，再增加
			deleteProjectItems(map);
			insertProjectItems(projectItems, map);
			// 更新问卷
			updateStandard(map);
		}
		return info;
	}

	/**
	 * 保存问卷
	 *
	 * @param map
	 * @author zhaowei
	 * @date 2022/3/11 14:38
	 */
	public void insertStandard(Map<String, Object> map) {
		dao.insert("SQBZ0101.insertStandard", map);
	}

	/**
	 * 更新问卷
	 *
	 * @param map
	 * @author zhaowei
	 * @date 2022/3/11 19:14
	 */
	public void updateStandard(Map<String, Object> map) {
		dao.update("SQBZ0101.updateStandard", map);
	}

	/**
	 * 删除项目
	 *
	 * @param map
	 * @author zhaowei
	 * @date 2022/3/11 19:14
	 */
	public void deleteProjectItems(Map<String, Object> map) {
		dao.delete("SQBZ0101.deleteProjectItems", map);
	}

	/**
	 * 保存项目明细
	 *
	 * @param projectItems
	 * @author zhaowei
	 * @date 2022/3/11 13:53
	 */
	public void insertProjectItems(Object projectItems, Map<String, Object> map) {
		// 将项目明细强转类型
		List<Map<String, Object>> projectItemsList = (List<Map<String, Object>>) projectItems;
		// 根据项目信息获取长度，方便后续倒序保存
		if (CollectionUtils.isNotEmpty(projectItemsList)) {
			for (Map<String, Object> projectItemsMap : projectItemsList) {
				projectItemsMap.put("standardId", map.get("standardId"));
				// 保存项目明细
				dao.insert("SQBZ0101.insertProjectItems", projectItemsMap);
			}
		}
	}

	/**
	 * 删除题目及选项
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/14 14:00
	 */
	public EiInfo deleteInstanceAndItem(EiInfo info) {
		Map<String, Object> map = new HashMap<>();
		// 获取题目ID
		String instanceId = info.getString("instanceId");
		map.put("instanceId", instanceId);
		// 调用删除题目及选项方法
		deleteInstanceAndItem(map);
		return info;
	}

	/**
	 * 删除项目+题目+选项
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/14 14:27
	 */
	public EiInfo deleteProjectAndInstanceAndItem(EiInfo info) {
		// 删除题目
		Map<String, Object> map = new HashMap<>();
		String projectId = info.getString("projectId");
		map.put("projectId", projectId);
		// 查询该项目下的所有题目
		List<Map<String, Object>> instanceList = dao.query("SQBZ0101.queryInstanceByProjectId", map);
		if (CollectionUtils.isNotEmpty(instanceList)){
			for (Map<String, Object> instanceMap : instanceList) {
				// 删除所有的题目+选项
				deleteInstanceAndItem(instanceMap);
			}
		}
		// 删除项目
		dao.delete("SQBZ0101.deleteProject", map);
		return info;
	}

	/**
	 * 删除题目+选项
	 * @author zhaowei
	 * @date 2022/4/12 13:42
	 * @param map
	 */
	public void deleteInstanceAndItem(Map<String, Object> map){
		// 删除该题目下的所有选项
		dao.delete("SQBZ010101.deleteSqInstanceItem", map);
		// 删除题目
		dao.delete("SQBZ010101.deleteSqInstance", map);
	}
}
