package com.baosight.wilp.au.rr.service;

import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 数据权限管理.
 * <p>
 * 界面初始化, 查询功能, 解除角色.
 * </p>
 *
 * @Title ServiceAUHD01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAURR01 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化 入参：EiInfo 出参：EiInfo 处理：返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}

	/**
	 * 查询功能 入参：EiInfo（工号 "workNo",姓名 "name",科室名称 "deptName",当前科室Id "curDeptId"）
	 * 出参：EiInfo（角色列表） 处理： 1.从入参中读取"workNo", "name", "deptName", "curDeptId"
	 * 2.调用query()方法查询满足条件的角色结果roleList 3.将查询结果roleList封装到EiInfo中的result区域
	 * 4.返回EiInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {

		String[] param = { "resourceEname", "resourceName", "type", "roleId" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);

		// 初始化列表不显示数据
		if (map.get("roleId") == null || "".equals(map.get("roleId").toString())) {
			return inInfo;
		}

		List<Map<String, Object>> roleList = dao.query("AURR01.queryResourceList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("AURR01.queryResourceListCount", map);
		// 返回
		if (!CollectionUtils.isEmpty(roleList)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(roleList.get(0)), roleList, count);
		} else {
			return inInfo;
		}
	}

	/**
	 * 解除角色 入参：EiInfo（科室deptId 角色id list） 出参：EiInfo（操作结果） 处理： 1.从入参获取科室id
	 * deptId和角色list 2.查询出上级机构deptList 3.如果存在上级机构则查询上级机构是否绑定角色
	 * 4.如果上级机构绑定角色则提示该角色存在父级权限,请先解除父级权限！并返回EiInfo
	 * 5.如果上级机构未绑定角色信息则调用delete()方法去数据库中删除对应的数据并返回删除结果
	 */
	public EiInfo relieveResource(EiInfo inInfo) {

		List<String> resourceList = (List<String>) inInfo.get("list");

		String roleId = inInfo.get("roleId") == null ? "" : inInfo.getString("roleId");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < resourceList.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("roleId", roleId);
			map.put("resourceId", resourceList.get(i));
			list.add(map);
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("projectSchema", projectSchema);
		
		dao.delete("AURR01.deleteRoleResource", map);

		inInfo.setMsg("解除成功");
		return inInfo;
	}
	
	/**
	 * 树结构查询方法
	 * 作者：hcd
	 * 入参：EiInfo (node 节点id)
	 * 出参：EiInfo (以该node节点为双亲的孩子节点)
	 * 过程：
	 * 1.从入参读取node节点信息存入map
	 * 2.将node节点信息传入dao.query()方法，得到满足上述条件的树形结果集。
	 * 3.调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中的result域
	 * 4.返回EiInfo
	 */
	public EiInfo queryTree(EiInfo inInfo) {
		/**
		 *  1.从入参读取node节点信息存入map
		 */
		Map<String, Object> map = inInfo.getRow("inqu_status", 0);
		map.put("projectSchema", projectSchema);

		/**
		 *  2.将node节点信息传入dao.query()方法，得到满足上述条件的树形结果集。
		 */
//		List<Map<String, String>> list = dao.query("AURU01.queryRoleTree", map);
		

		EiInfo eiInfo = new EiInfo();
		eiInfo.set(EiConstant.serviceId,"S_XS_01");
		
		if(!"roleRoot".equals(map.get("node").toString())) {
			
			eiInfo.set("groupEname","XXX");
		}
		
		EiInfo outInfo = XServiceManager.call(eiInfo);
		
		List<Map<String, String>> list = (List<Map<String, String>>) outInfo.get("result");

		/**
		 * 3.调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中的result域
		 */
		PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list, list.size());

		/**
		 * 4.返回EiInfo
		 */
		String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
		inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
		inInfo.getBlocks().remove(EiConstant.resultBlock);
		return inInfo;
	}

	/**
	 * 树形使用
	 * 作者：hcd
	 * 入参：void
	 * 出参：EiBlockMeta 过
	 * 程： 1.封装 角色ID 2.封装 角色编号 3.封装 角色名称 4.封装 父级ID
	 */
	private EiBlockMeta initMetaData() {
		EiBlockMeta eiMetadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("groupId");
		eiColumn.setDescName("角色ID");
		eiColumn.setNullable(false);
		eiColumn.setPrimaryKey(true);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("groupEname");
		eiColumn.setDescName("角色编号");
		eiColumn.setNullable(false);
		eiColumn.setPrimaryKey(true);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("groupCname");
		eiColumn.setDescName("角色名称");
		eiColumn.setNullable(false);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("groupType"); // 作为kendo.data.Model 不能出现id，parent列
		eiColumn.setDescName("父级ID");
		eiColumn.setNullable(true);
		eiMetadata.addMeta(eiColumn);
		return eiMetadata;

	}

}
