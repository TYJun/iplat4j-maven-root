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

public class ServicePMPG01 extends ServiceBase {
	// 初始化查询
	public EiInfo initLoad(EiInfo info) {
		// 调用查询项目阶段配置信息
		return this.queryPmStageMsg(info);
	}

	/**
	 * 查询项目阶段配置信息
	 * 入参：
	 * stageCode：阶段编码
	 * stageName：阶段名称
	 * 出参：
	 * stageCode：阶段编码
	 * stageName：阶段名称
	 * stageRemark：阶段备注
	 * createName：创建人
	 * createTime：创建时间
	 * updateName：修改人
	 * updateTime：修改时间
	 */
	public EiInfo queryPmStageMsg(EiInfo info) {
		/**
		 * 1.获取账套信息、登录信息
		 */
		// 获取账套信息
		DatagroupUtil.getDatagroupCode();
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String loginName = (String) staffByUserId.get("name");
		/**
		 * 2.获取入参参数与分页信息
		 */
		// 获取前端查询参数
		String[] queryCondition = {"stageCode", "stageName"};
		/**
		 * 3.将获取的信息存储进map
		 */
		//获取分页信息
		Map<String, Object> map = EasyIplat4jUtil.queryConditionToMap(info, "result", Arrays.asList(queryCondition));
		// 调用sql查询方法获取信息
		List list = dao.query("PMPG01.queryPmStageMsg", map, Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
		//
		Integer count = dao.count("PMPG01.queryPmStageMsg", map);
		// 将查询信息封装返回
		return EasyIplat4jUtil.BuildOutEiInfo(info, null, null, list, count);
	}

	/**
	 * 新增项目阶段配置信息
	 * 入参：
	 * stageName：阶段名称
	 * stageRemark:阶段备注
	 * 出参：
	 * 无
	 */
	public EiInfo insertPmStageMsg(EiInfo info) {
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
		String stageCode = CreateNoUtil.CreateStageNo(ProjectEnum.stageCode);
		String stageName = (String) info.get("stageName");
		String stageRemark = (String) info.get("stageRemark");
		String createTime = DateUtilsTools.getCurrentTime24();
		// 将获取的信息存储进map
		map.put("id", id);
		map.put("stageCode", stageCode);
		map.put("stageName", stageName);
		map.put("stageRemark", stageRemark);
		map.put("createTime", createTime);
		map.put("createNo", createNo);
		map.put("createName", createName);
		// 调用sql查询方法获取信息
		dao.insert("PMPG01.insertPmStageMsg", map);
		// 将查询信息封装返回
		return info;
	}

	/**
	 * 编辑项目阶段配置信息
	 * 入参：
	 * id：项目阶段id
	 * stageName：阶段名称
	 * stageRemark：阶段备注
	 * 出参：
	 * 无
	 */
	public EiInfo editPmStageMsg(EiInfo info) {
		// 获取入参参数与分页信息
		String updateNo = UserSession.getUser().getUsername();
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String updateName = (String) staffByUserId.get("name");
		//
		Map<String, Object> map = new HashMap<>();
		String id = (String) info.get("id");
		String stageName = (String) info.get("stageName");
		String stageRemark = (String) info.get("stageRemark");
		String updateTime = DateUtilsTools.getCurrentTime24();
		// 将获取的信息存储进map
		map.put("id", id);
		map.put("stageName", stageName);
		map.put("stageRemark", stageRemark);
		map.put("updateTime", updateTime);
		map.put("updateNo", updateNo);
		map.put("updateName", updateName);
		// 调用sql查询方法获取信息
		dao.update("PMPG01.editPmStageMsg", map);
		// 将查询信息封装返回
		return info;
	}

	/**
	 * 删除项目阶段配置信息
	 * 入参：
	 * List<String>
	 * id：项目阶段id
	 * 出参：
	 * 无
	 */
	public EiInfo deletePmStageMsg(EiInfo info) {
		// 将获取的信息存储进map
		Map<String, Object> map = new HashMap<>();
		String id = (String) info.get("id");
		// 调用sql查询方法获取信息
		map.put("id", id);
		// 将查询信息封装返回
		dao.delete("PMPG01.deletePmStageMsg", map);
		return info;
	}

}
