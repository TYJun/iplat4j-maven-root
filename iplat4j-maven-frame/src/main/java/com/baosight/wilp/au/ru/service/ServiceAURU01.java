package com.baosight.wilp.au.ru.service;

import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色与用户.
 * <p>
 * 界面初始化, 查询功能，树结构查询方法，删除功能.
 * </p>
 *
 * @Title ServiceAURU01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceAURU01 extends ServiceBase {
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：
	 * 返回入参EiInfo
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询功能
	 * 作者：hcd
	 * 入参：EiInfo（工号 "workNo",姓名 "name",科室名称 "deptName",角色ID "roleId"）
	 * 出参：EiInfo（grid（主键 id,工号 workNo,姓名 name,性别 gender, 所属科室 deptName,联系电话
	 * contactTel,员工类型 staffType,服务人员 isService, 是否停用 isStatus, 所属院区 dataGroupName））
	 * 处理：
	 * 1.从入参读入参数"workNo", "name", "deptName", "roleId" 存入map
	 * 2.调用query()方法从数据库查询出符合入参条件的结果集list
	 * 3.将list结果封装在EiInfo中的result域
	 * 4.返回EiInfo
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.从入参读入参数"workNo", "name", "deptName", "roleId" 存入map
		 */
		String[] param = { "workNo", "name", "deptName", "roleId" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		map.put("projectSchema", projectSchema);

		// 初始化列表不显示数据
		if (map.get("roleId") == null || "".equals(map.get("roleId").toString())) {
			return inInfo;
		}

		/**
		 * 2.调用query()方法从数据库查询出符合入参条件的结果集list
		 */
		List<Map<String, Object>> list = dao.query("AURU01.queryRolePersonList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("AURU01.queryRolePersonListCount", map);

		/**
		 *  3.将list结果封装在EiInfo中的result域
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return inInfo;
		}

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
		List<Map<String, String>> list = dao.query("AURU01.queryRoleTree", map);

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
		EiColumn eiColumn = new EiColumn("label");
		eiColumn.setDescName("角色ID");
		eiColumn.setNullable(false);
		eiColumn.setPrimaryKey(true);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("roleNum");
		eiColumn.setDescName("角色编号");
		eiColumn.setNullable(false);
		eiColumn.setPrimaryKey(true);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("roleName");
		eiColumn.setDescName("角色名称");
		eiColumn.setNullable(false);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("pId"); // 作为kendo.data.Model 不能出现id，parent列
		eiColumn.setDescName("父级ID");
		eiColumn.setNullable(true);
		eiMetadata.addMeta(eiColumn);
		return eiMetadata;

	}

	/**
	 * 删除功能
	 * 作者：hcd
	 * 入参：EiInfo（待删除角色id list）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取待删除人员id list和角色id roleId
	 * 2.调用delete方法删除角色与用户关系
	 * 3.返回删除结果
	 * 
	 */
	public EiInfo delete(EiInfo inInfo) {

		/**
		 * 1.从入参中读取待删除人员id list和角色id roleId
		 */
		List<String> list = (List<String>) inInfo.get("list");
		String roleId = inInfo.getString("roleId");

		/**
		 * 2.调用delete方法删除角色与用户关系
		 */
		for (String perId : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("perId", perId);
			map.put("roleId", roleId);
			map.put("projectSchema", projectSchema);
			dao.delete("AURU01.delete", map);
		}

		/**
		 *  3.返回删除结果
		 */
		inInfo.setMsg("删除成功");
		return inInfo;
	}

}
