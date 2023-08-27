package com.baosight.wilp.ac.de.service;

import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.mc.fw.util.SendingDingMsgUtil;
import com.baosight.wilp.mc.fw.util.SendingWXMsgUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 科室档案信息管理.
 * <p>
 * 页面初始化方法, 查询功能, 启用科室, 停用科室, 根据科室编码查询是否该科室有员工信息, 树结构查询方法，根据科室ID更新院区编码.
 * </p>
 *
 * @Title ServiceACDE01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACDE01 extends ServiceBase {
	
	//
//	private static String isMany = PlatApplicationContext.getProperty("isMany");
	
	private static String isDing = PlatApplicationContext.getProperty("isDing");
	private static String isWX = PlatApplicationContext.getProperty("isWX");
	
	//医院名称
	private static String hospitalCName = PlatApplicationContext.getProperty("hospitalCName");

	final String projectSchema = PrUtils.getConfigure();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 页面初始化方法
	 * 作者：hcd
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：调用query()方法
	 *
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return query(inInfo);
	}

	/**
	 * 查询功能
	 * 作者：hcd
	 * 入参：科室编号"deptNum", 科室名称"deptName", 双亲ID"parentId"
	 * 出参：EiInfo （查询结果）
	 * 处理：
	 * 1.调用DatagroupUtil.getDatagroupCode()方法查询出当前用户所在院区编号datagroupCode
	 * 2.将院区编号datagroupCode和工号workNo传入AUFW01.getUserDepts()得到该用户所属的科室
	 * 3.将科室信息和前端传入的科室编号"deptNum", 科室名称"deptName", 双亲ID"parentId"等信
 	 * 	息传入dao.query()方法，得到满足上述条件的科室结果集。
	 * 4.调用super.count()方法查询出结果集数
	 * 5.判断结果集是否为空，如果不为空则调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中
	 *
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {
		/**
		 * 1.调用DatagroupUtil.getDatagroupCode()方法查询出当前用户所在院区编号datagroupCode
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getLoginName();

		/**
		 *  2.将院区编号datagroupCode和工号workNo传入AUFW01.getUserDepts()得到该用户所属的科室
		 */
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserDepts");
		EiInfo info = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) info.get("result");

		/**
		 * 3.将科室信息和前端传入的科室编号"deptNum", 科室名称"deptName", 双亲ID"parentId"等信
		 * 	息传入dao.query()方法，得到满足上述条件的科室结果集。
		 */
		String[] param = { "deptNum", "deptName", "parentId", "status","dictType","depCategoryCode" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "启用");

		map.put("list", list);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> deptList = dao.query("ACDE01.queryDeptList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
		/**
		 * 4.调用super.count()方法查询出结果集数
		 */
		int count = super.count("ACDE01.queryDeptListCount", map);

		/**
		 * 5.判断结果集是否为空，如果不为空则调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中
		 */
		if (!CollectionUtils.isEmpty(deptList)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}

	}

	/**
	 * 科室删除功能，删除前判断科室是否绑定员工信息 入参：list(待删除的科室id的list集合)
	 * 出参：EiInfo(删除成功：设置inInfo状态为0，消息为删除成功；删除失败：设置inInfo状态为-1，消息为
	 * 所选科室已绑定员工，无法删除，请先删除该科室员工信息!!!) 处理： 1.从inInfo中获取待删除数据 id 的list集合
	 * 2.调用getTrueOrFalse()方法检查该科室下是否有员工。没有员工可以删除，有员工不可删除，返回删除失败 如果没有员工则删除list中的科室。
	 * 
	 */
//	public EiInfo delete(EiInfo inInfo) {
//
//		List<String> list = (List<String>)inInfo.get("list");
//
//		//验证科室下是否有员工。没有员工可以删除，有员工不可删除
//		if(getTrueOrFalse(list)) {
//			dao.delete("ACDE01.delete",list);
//			inInfo.setStatus(0);
//			inInfo.setMsg("删除成功");
//		}else {
//			inInfo.setStatus(-1);
//			inInfo.setMsg("所选科室已绑定员工，无法删除，请先删除该科室员工信息!!!");
//		}
//
//		return inInfo;
//	}

	/**
	 * 启用科室
	 * 作者：hcd
	 * 入参:EiInfo(待启用科室的id list 和 状态)
	 * 出参:EiInfo(操作结果)
	 * 处理：
	 * 1.从EiInfo中读取入参
	 * 2.在数据库中将符合id list 中的记录的状态status字段更新
	 * 3.返回操作结果
	 */
	@ArchivesLog(model = "AC", sign = "启用科室")
	public EiInfo startUsing(EiInfo eiInfo) {
		/**
		 * 1.从EiInfo中读取入参
		 */
		List<String> list = (List<String>) eiInfo.get("list");
		HashMap<String, Object> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("list", list);

		/**
		 * 2.在数据库中将符合id list 中的记录的状态status字段更新
		 */
		dao.update("ACDE01.startUsing", map);

		/**
		 * 3.返回操作结果
		 */
		eiInfo.setStatus(0);
		eiInfo.setMsg("启用成功");
		return eiInfo;
	}

	/**
	 * 停用科室
	 *作者：hcd
	 *入参：EiInfo（待停用科室的id list 和 状态 )）
	 *出参：EiInfo(操作结果)
	 *处理：
	 * 1.从EiInfo中读取入参 id list
	 * 2.验证科室下是否有员工。没有员工可以停用，有员工不可停用 并返回
	 * 3.验证该科室是否存在角色权限，如果存在则不能停用 并返回
	 * 4.如果通过2，3验证则执行数据库更新操作
	 * 5.返回成功结果
	 */
	@ArchivesLog(model = "AC", sign = "停用科室")
	public EiInfo stopUsing(EiInfo inInfo) {
		/**
		 * 1.从EiInfo中读取入参 id list
		 */
		List<String> list = (List<String>) inInfo.get("list");
		/**
		 *  2.验证科室下是否有员工。没有员工可以停用，有员工不可停用 并返回
		 */
		if (!getTrueOrFalse(list)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("所选科室已绑定员工，无法停用，请先停用该科室员工信息!");
			return inInfo;
		}
		/**
		 * 3.验证该科室是否存在角色权限，如果存在则不能停用 并返回
		 */
		else if (isExistRoleBind(list)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("所选科室已绑定角色，无法停用，请先解除角色绑定!");
			return inInfo;
		}

		/**
		 * 4.如果通过2，3验证则执行数据库更新操作
		 */
		HashMap<String, Object> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("list", list);
		dao.update("ACDE01.stopUsing", map);

		/**
		 * 5.返回成功结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("停用成功!");
		return inInfo;
	}

	/**
	 * 判断科室列表中的科室是否存在角色绑定关系
	 * 作者：hcd
	 * 入参 id list
	 * 出参: boolean
	 * 处理：
	 * 1.封装入参到map中
	 * 2.从数据库中查询该id 是否存在角色绑定
	 * 3.返回结果
	 */
	private boolean isExistRoleBind(List<String> list) {
		/**
		 * 1.封装入参到map中
		 */
		HashMap<String, Object> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("list", list);

		/**
		 * 2.从数据库中查询该id 是否存在角色绑定
		 */
		int count = super.count("ACDE01.isExistRoleBind", map);

		/**
		 *  3.返回结果
		 */
		return count > 0;
	}

	/**
	 * 根据科室编码查询是否该科室有员工信息 入参：list 集合 出参：Boolean （如果存在员工则返回false，反之true） 处理：
	 * 作者：hcd
	 * 1.调用dao.query()方法传入list集合，得到deptList
	 * 2.判断deptList集合是否为空如果空则返回true，如果不为空则返回false
	 */
	public Boolean getTrueOrFalse(List<String> list) {
		/**
		 * 1.调用dao.query()方法传入list集合，得到deptList
		 */
		HashMap<String, Object> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		map.put("list", list);
		List<Map<String, Object>> deptList = dao.query("ACDE01.getTrueOrFalse", map);

		/**
		 * 2.判断deptList集合是否为空如果空则返回true，如果不为空则返回false
		 */
		if (CollectionUtils.isNotEmpty(deptList)) {
			return false;
		}
		return true;
	}

	/**
	 * 树结构查询方法
	 * 作者：hcd
	 * 入参：node (节点id) 出参：EiInfo (以该node节点为双亲的孩子节点) 过程：
	 * 1.调用DatagroupUtil.getDatagroupCode()方法查询出当前用户所在院区编号datagroupCode
	 * 2.将院区编号datagroupCode和工号workNo传入AUFW01.getUserDepts()得到该用户所属的科室
	 * 3.将科室信息和前端传入的deptList等信息传入dao.query()方法，得到满足上述条件的树形结果集。
	 * 4.调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中的result域
	 */
	public EiInfo queryTree(EiInfo inInfo) {

		/**
		 * 1.调用DatagroupUtil.getDatagroupCode()方法查询出当前用户所在院区编号datagroupCode
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getLoginName();


		/**
		 * 2.将院区编号datagroupCode和工号workNo传入AUFW01.getUserDepts()得到该用户所属的科室
		 */
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getDepts");
		EiInfo info = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> deptList = (ArrayList<HashMap<String, Object>>) info.get("result");

		/**
		 * 3.将科室信息和前端传入的deptList等信息传入dao.query()方法，得到满足上述条件的树形结果集。
		 */
		Map<String, Object> map = inInfo.getRow("inqu_status", 0);
		map.put("deptList", deptList);
		map.put("projectSchema", projectSchema);
		List<Map<String, String>> list = dao.query("ACDE01.queryDeptTree", map);

		/**
		 *  4.调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中的result域
		 */
		PrUtils.BuildOutEiInfo(inInfo, "result", initMetaData(), list, list.size());
		String pEname = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");
		inInfo.getBlocks().put(pEname, inInfo.getBlock(EiConstant.resultBlock));
		inInfo.getBlocks().remove(EiConstant.resultBlock);
		return inInfo;
	}

	/**
	 * 从 excel 导入数据
	 * 作者：jzm
	 * 入参：EiInfo(list List<Map<String, String>> )
	 * 出参：EiInfo(导入结果，如果存在未导入记录，则返回该记录和原因)
	 * 处理：
	 * 1.验证字段非空
	 * 2.判断字段长度是否超出
	 * 3.验证科室编码是否重复
	 * 4.判断是否存在错误
	 * 5.返回操作结果
	 */
	public EiInfo importDeptFromExcel(EiInfo eiInfo) {
		HashMap<String, String> checkMap = new HashMap<>();
		List<Map<String, String>> inList = (List<Map<String, String>>) eiInfo.get("list");
		List<Map<String, String>> okList = new ArrayList<>();
		List<Map<String, String>> errorList = new ArrayList<>();

		for (Map<String, String> map : inList) {
			/**
			 * 1.验证字段非空
			 */
			StringBuilder error = new StringBuilder();
			if (StringUtils.isEmpty(map.get("deptNum"))) {
				error.append("科室编码为空  ");
			}
			if (StringUtils.isEmpty(map.get("deptName"))) {
				error.append("科室名称为空  ");
			}
			if (StringUtils.isEmpty(map.get("parentId"))) {
				error.append("上级科室为空  ");
			}
			if (StringUtils.isEmpty(map.get("type"))) {
				error.append("服务科室为空  ");
			}

			/**
			 * 2.判断字段长度是否超出
			 */
			if (map.get("deptNum").length() > 50) {
				error.append("科室编码长度超过50  ");
			}
			if (map.get("deptName").length() > 200) {
				error.append("科室名称长度超过50  ");
			}
			if (map.get("parentId").length() > 36) {
				error.append("上级科室长度超过36  ");
			}
			if (map.get("finaCode").length() > 36) {
				error.append("财务代码长度超过36  ");
			}
			if (map.get("erpCode").length() > 36) {
				error.append("HRP代码长度超过36  ");
			}
			if (map.get("deptDescribe").length() > 500) {
				error.append("科室描述长度超过500  ");
			}
			if (map.get("type").length() > 2) {
				error.append("服务科室长度超过2  ");
			}

			if (!("1".equals(map.get("type")) || "0".equals(map.get("type")))) {
				error.append("服务科室应填写 1 或 0  ");
			}

			/**
			 * 3.验证科室编码是否重复
			 */
			HashMap<String, String> map1 = new HashMap<>();
			map1.put("projectSchema", projectSchema);
			map1.put("deptNum", map.get("deptNum"));
			int count1 = super.count("ACDE01.countDeptNum", map1);
			if (count1 != 0 || checkMap.get(map.get("deptNum")) != null) { // deptNum duplicate！
				error.append("科室编码重复  ");
			}

			// 验证上级科室是否存在（如果不存在则错）
			// 如果parent是 root 则无需验证,如果不是root则验证是否存在
			if (!"root".equals(map.get("parentId"))) {
				HashMap<String, String> map2 = new HashMap<>();
				map2.put("projectSchema", projectSchema);
				map2.put("parentId", map.get("parentId"));
				int count2 = super.count("ACDE01.countDeptNum", map2);

				if (count2 == 0 && checkMap.get(map.get("parentId")) == null) {
					error.append("上级科室不存在  ");
				}
			}

			checkMap.put(map.get("deptNum"), "1");

			/**
			 * 4.判断是否存在错误
			 */
			if (error.length() == 0) {
				// 无错误，则生成必要信息 然后存入okList
				String id = UUID.randomUUID().toString();
				String recCreateTime = sdf.format(new Date());
				String recCreater = UserSession.getLoginName();
				map.put("id", id);
				map.put("status", "1"); // 默认启用
				map.put("recCreater", recCreater);
				map.put("recCreateTime", recCreateTime);
				// 存入okList
				okList.add(map);

			} else {
				// 有错误 添加错误字段 存入errorList
				map.put("error", error.toString());
				errorList.add(map);
			}

		}
		// 如果 okList 不为空 则插入数据库
		if (CollectionUtils.isNotEmpty(okList)) {
			// 批量导入okList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", okList);
			map1.put("projectSchema", projectSchema);

			dao.insert("ACDE01.importDeptFromExcel", map1);
			okList.forEach(e -> {
				String id = e.get("id");
				updateHosdNum(id);
			});
		}
		/**
		 * 5.返回操作结果
		 */
		// 返回
		EiInfo outInfo = new EiInfo();
		if (!errorList.isEmpty()) {
			outInfo.setStatus(1);
			HashMap<String, Object> map = new HashMap<>();
			map.put("list", errorList);
			outInfo.setAttr(map);
			return outInfo;
		}
		outInfo.setStatus(0);
		return outInfo;
	}

	/**
	 * 根据科室ID更新院区编码
	 * 作者：hcd
	 * 入参：inInfo（科室ID id）
	 * 出参：inInfo （执行结果）
	 * 处理：
	 * 1.从入参中获取id
	 * 2.执行dao.query()方法查询出该科室的孩子节点list
	 * 3.如果list非空，读出hospitalDistrict
	 * 4.将院区hospitalDistrict插入数据库中
	 */
	public void updateHosdNum(String id) {
		/**
		 * 1.从入参中获取id
		 */
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.执行dao.query()方法查询出该科室的孩子节点list
		 */
		List<Map<String, Object>> list = dao.query("ACDE0101.getChildrenList", map);

		/**
		 * 3.如果list非空，读出hospitalDistrict
		 */
		if (!CollectionUtils.isEmpty(list)) {
			String hospitalDistrict = list.get(0).get("hosdNum").toString();

			/**
			 * 4.将院区hospitalDistrict插入数据库中
			 */
			map.put("hospitalDistrict", hospitalDistrict);
			dao.update("ACDE0101.updateHosdNum", map);
		}
	}

	/**
	 * 树形使用
	 * 作者：hcd
	 * 入参：
	 * 出参：EiBlockMeta
	 * 过程：
	 * 1.封装科室ID列
	 * 2.封装科室编号列
	 * 3.封装科室名称列
	 * 4.父级ID列
	 */
	private EiBlockMeta initMetaData() {
		/**
		 * 1.封装科室ID列
		 */
		EiBlockMeta eiMetadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("label");
		eiColumn.setDescName("科室ID");
		eiColumn.setNullable(false);
		eiColumn.setPrimaryKey(true);
		eiMetadata.addMeta(eiColumn);

		/**
		 * 2.封装科室编号列
		 */
		eiColumn = new EiColumn("deptNum");
		eiColumn.setDescName("科室编号");
		eiColumn.setNullable(false);
		eiColumn.setPrimaryKey(true);
		eiMetadata.addMeta(eiColumn);

		/**
		 * 3.封装科室名称列
		 */
		eiColumn = new EiColumn("deptName");
		eiColumn.setDescName("科室名称");
		eiColumn.setNullable(false);
		eiMetadata.addMeta(eiColumn);

		/**
		 * 4.父级ID列
		 */
		eiColumn = new EiColumn("pId"); // 作为kendo.data.Model 不能出现id，parent列
		eiColumn.setDescName("父级ID");
		eiColumn.setNullable(true);
		eiMetadata.addMeta(eiColumn);

		return eiMetadata;
	}

	/**
	 *	查询所有子集
	 *	作者：hcd
	 * 	入参：EiInfo(节点 id)
	 * 	出参：EiInfo（以入参节点为双亲节点的所有子节点）
	 * 	处理：
	 * 	1.从入参读取 id
	 * 	2.从数据库中查询出所有的科室集合（启用的）
	 * 	3.清空menuList（防止该实例在客户端每次请求中不断累积增大）
	 * 	4.递归调用
	 * 	5.返回 所有子节点
	 */
	public EiInfo getDeptList(EiInfo inInfo) {

		/**
		 * 1.从入参读取 id
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");

		/**
		 * 	2.从数据库中查询出所有的科室集合（启用的）
		 */
		Map<String, String> map = new HashMap<>();
		map.put("projectSchema", projectSchema);
		List<Map<String, String>> list = dao.query("ACDE01.getDeptList", map);

		/**
		 * 3.清空menuList（防止该实例在客户端每次请求中不断累积增大）
		 */
		menuList.clear();

		/**
		 * 	4.递归调用
		 */
		getTree1(id, list);

		Map<String, String> map1 = new HashMap<>();
		map1.put("id", id);

		menuList.add(map1);
		inInfo.set("result", menuList);
		/**
		 * 5.返回 所有子节点
		 */
		return inInfo;
	}

	private List<Map<String, String>> menuList = new ArrayList<>();

	/**
	 * 递归方法1
	 * @param parentId
	 * @param list
	 */
	public void getTree1(String parentId, List<Map<String, String>> list) {

		List<Map<String, String>> list1 = getTree2(parentId, list);

		if (list1.size() > 0) {
			menuList.addAll(list1);
			for (int i = 0; i < list1.size(); i++) {
				getTree1(list1.get(i).get("id"), list);
			}
		}
	}

	/**
	 * 递归方法2
	 * @param parentId
	 * @param list
	 */
	public List<Map<String, String>> getTree2(String parentId, List<Map<String, String>> list) {

		List<Map<String, String>> newList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (parentId.equals(list.get(i).get("parentId"))) {
				newList.add(list.get(i));
			}
		}
		return newList;
	}

	/**
	 * 查询所有科室信息（二维码导出使用下载全部）
	 * 作者：jzm
	 * 入参：EiInfo(空)
	 * 出参：EiInfo （查询结果）
	 * 处理：
	 * 1.调用DatagroupUtil.getDatagroupCode()方法查询出当前用户所在院区编号datagroupCode
	 * 2.将院区编号datagroupCode和工号workNo传入AUFW01.getUserDepts()得到该用户所属的科室
	 * 3.将科室信息和前端传入的科室编号"deptNum", 科室名称"deptName", 双亲ID"parentId"等信
	 * 	息传入dao.query()方法，得到满足上述条件的科室结果集。
	 * 4.调用super.count()方法查询出结果集数
	 * 5.判断结果集是否为空，如果不为空则调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中
	 *
	 */
	public EiInfo queryAllDept(EiInfo inInfo) {
		/**
		 * 1.调用DatagroupUtil.getDatagroupCode()方法查询出当前用户所在院区编号datagroupCode
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getLoginName();
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);

		/**
		 * 2.将院区编号datagroupCode和工号workNo传入AUFW01.getUserDepts()得到该用户所属的科室
		 */
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserDepts");
		EiInfo info = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) info.get("result");

		/**
		 * 3.将科室信息和前端传入的科室编号"deptNum", 科室名称"deptName", 双亲ID"parentId"等信
		 * 	 * 	息传入dao.query()方法，得到满足上述条件的科室结果集。
		 */
		String[] param = { "deptNum", "deptName", "parentId", "status" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);

		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "启用");

		map.put("list", list);
		map.put("projectSchema", projectSchema);
		map.put("offset", 0);
		map.put("limit", -999999);

		List<Map<String, Object>> deptList = dao.query("ACDE01.queryDeptList", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		/**
		 *  4.调用super.count()方法查询出结果集数
		 */
		int count = super.count("ACDE01.queryDeptListCount", map);
		// 返回
		if (!CollectionUtils.isEmpty(deptList)) {
			/**
			 * 5.判断结果集是否为空，如果不为空则调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中
			 */
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}

	}


	/**
	 * 通过编码查询科室信息（二维码导出使用下载部分）
	 * 作者：jzm
	 * 入参：EiInfo(ids)
	 * 出参：EiInfo （查询结果）
	 * 处理：
	 * 1.从入参入读 list集合
	 * 2.调用dao.query()方法，得到满足上述条件的科室结果集。
	 * 3.调用super.count()方法查询出结果集数
	 * 4.判断结果集是否为空，如果不为空则调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中
	 *
	 */
	public EiInfo queryDeptInfoByNum(EiInfo inInfo) {

		/**
		 *  1.从入参入读 list集合
		 */
		List<String> list = (List<String>) inInfo.get("list");

		Map<String, Object> map = new HashMap<>();
		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "启用");

		map.put("list", list);
		map.put("projectSchema", projectSchema);
		map.put("offset", 0);
		map.put("limit", -999999);

		/**
		 *  2.调用dao.query()方法，得到满足上述条件的科室结果集。
		 */
		List<Map<String, Object>> deptList = dao.query("ACDE01.queryDeptInfoByNum", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));
		/**
		 * 3.调用super.count()方法查询出结果集数
		 */
		int count = super.count("ACDE01.queryDeptInfoByNumCount", map);
		// 返回
		if (!CollectionUtils.isEmpty(deptList)) {
			/**
			 * 4.判断结果集是否为空，如果不为空则调用PrUtils.BuildOutEiInfo()方法将查询结果封装在EiInfo中
			 */
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}
	}
	
	/**
	 * 同步钉钉科室信息
	 * 作者：hcd
	 * 入参：EiInfo()
	 * 出参：EiInfo()
	 * 处理：
	 * 1.获取token值
	 * 2.获取钉钉科室
	 * 3.处理科室List，将已存在的添加到oldList，将不存在的添加到newList
	 * 4.将改数据库所有科室改为停用状态
	 * 5.如果 oldList 不为空 则修改数据库
	 * 6.如果 newList 不为空 则插入数据库
	 * 7.返回操作结果
	 */
	public EiInfo synchronizationDingDept(EiInfo eiInfo) {
		List<Map<String, Object>> newList = new ArrayList<>();
		List<Map<String, Object>> oldList = new ArrayList<>();
		
		String recCreater = UserSession.getLoginName();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */
		
		/**
		 * 1.获取token值
		 */ 
		eiInfo.set("appCode", "AP00002");
		eiInfo.set(EiConstant.serviceName, "MCFW01");
		eiInfo.set(EiConstant.methodName, "getToken");
		EiInfo inInfo1 = XLocalManager.call(eiInfo);
		if (inInfo1.getStatus() < 0) {
			inInfo1.setStatus(-1);
			inInfo1.setMsg("token没有获取到!");
			return inInfo1;
		}
		
		String token = inInfo1.get("token") == null ? "" : inInfo1.getString("token");
		
		System.out.println("#########################token:"+token );

		/**
		 * 2.获取钉钉科室
		 */ 
		List<Map<String, Object>> inList = SendingDingMsgUtil.getDeptList("1",token);
		
		if("0".equals(isDing)) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", 1);
			map.put("name", hospitalCName);
			map.put("parentid", "root");
			inList.add(map);
		}
		
		/**
		 * 3.处理科室List，将已存在的添加到oldList，将不存在的添加到newList
		 */ 
		for (Map<String, Object> map : inList) {
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("id", map.get("id"));
			map1.put("source", "1");
			map1.put("projectSchema", projectSchema);
			
			List<Map<String, Object>> list = dao.query("ACDE01.queryDeptList", map1);
			
			if("1".equals(isDing)) {
				if("1".equals(map.get("parentid")+"")) {
					map.put("parentid", "root");
				}
			}
			
//			if("2".equals(isWX)) {
//				if("wx1".equals(map.get("parentid")+"")) {
//					map.put("parentid", "root");
//				}
//			}
			
			map.put("deptNum", map.get("id"));
			map.put("status", "1");
			map.put("source", "1");
			
			//将新的数据插入数据库，将旧的数据进行更新
			if(CollectionUtils.isNotEmpty(list)) {
				map.put("recRevisor", recCreater);
				map.put("recReviseTime", recCreateTime);
				oldList.add(map);
//				if(!"2".equals(isDing) &&  !"1".equals(map.get("parentid")+"")) {
//					oldList.add(map);
//				}
			}else {
				map.put("type", "0");
				map.put("recCreater", recCreater);
				map.put("recCreateTime", recCreateTime);
				newList.add(map);
//				if(!"2".equals(isDing) &&  !"1".equals(map.get("parentid")+"")) {
//					newList.add(map);
//				}
			}
		}
		
		/**
		 * 4.将改数据库所有科室改为停用状态
		 */ 
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("source", "1");
		map2.put("projectSchema", projectSchema);
		List<Map<String, Object>> deptList = dao.query("ACDE01.queryDeptList", map2);
		
		if(CollectionUtils.isNotEmpty(deptList)) {
			HashMap<String, Object> deptmap = new HashMap<>();
			deptmap.put("list", deptList);
			deptmap.put("projectSchema", projectSchema);
			dao.update("ACDE01.stopDepartment", deptmap);
		}
		
		/**
		 * 5.如果 oldList 不为空 则修改数据库
		 */ 
		if (CollectionUtils.isNotEmpty(oldList)) {
			// 批量修改oldList
			for (Map<String, Object> map : oldList) {
				HashMap<String, Object> map1 = new HashMap<>();
				map1.put("id", map.get("id")+"");
				map1.put("deptNum", map.get("deptNum")+"");
				map1.put("name", map.get("name")+"");
				map1.put("parentid", map.get("parentid")+"");
				map1.put("status", map.get("status")+"");
				map1.put("source", map.get("source")+"");
				map1.put("recRevisor", map.get("recRevisor")+"");
				map1.put("recReviseTime", map.get("recReviseTime")+"");
				map1.put("projectSchema", projectSchema);
				
				dao.update("ACDE01.updateDingDept", map1);
				
				String id = map.get("id")+"";
				updateHosdNum(id);
			}
		}
		
		/**
		 * 6.如果 newList 不为空 则插入数据库
		 */ 
		if (CollectionUtils.isNotEmpty(newList)) {
			// 批量新增newList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", newList);
			map1.put("projectSchema", projectSchema);

			dao.insert("ACDE01.insertDingDept", map1);
			newList.forEach(e -> {
				String id = e.get("id").toString();
				updateHosdNum(id);
			});
		}
		
		/**
		 * 7.返回操作结果
		 */
		// 返回
		EiInfo outInfo = new EiInfo();
		outInfo.setMsg("同步完成!");
		outInfo.setStatus(0);
		return outInfo;
	}
	

/**
	 * 同步企业微信科室信息
	 * 作者：hcd
	 * 入参：EiInfo()
	 * 出参：EiInfo()
	 * 处理：
	 * 1.获取token值
	 * 2.获取企业微信科室
	 * 3.处理科室List，将已存在的添加到oldList，将不存在的添加到newList
	 * 4.将改数据库所有科室改为停用状态
	 * 5.如果 oldList 不为空 则修改数据库
	 * 6.如果 newList 不为空 则插入数据库
	 * 7.返回操作结果
	 */
	public EiInfo synchronizationWXDept(EiInfo eiInfo) {
		List<Map<String, Object>> newList = new ArrayList<>();
		List<Map<String, Object>> oldList = new ArrayList<>();
		
		String recCreater = UserSession.getLoginName();
		String recCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); /* 创建时间 */
		
		/**
		 * 1.获取token值
		 */ 
		eiInfo.set("appCode", "AP00003");
		eiInfo.set(EiConstant.serviceName, "MCFW01");
		eiInfo.set(EiConstant.methodName, "getWXToken");
		EiInfo inInfo1 = XLocalManager.call(eiInfo);
		if (inInfo1.getStatus() < 0) {
			inInfo1.setStatus(-1);
			inInfo1.setMsg("token没有获取到!");
			return inInfo1;
		}
		
		String token = inInfo1.get("token") == null ? "" : inInfo1.getString("token");
		
		System.out.println("#########################token:"+token );

		/**
		 * 2.获取企业微信科室
		 */ 
		List<Map<String, Object>> inList = SendingWXMsgUtil.getDeptList(null,token);
		
		if("0".equals(isWX)) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", "a1");
			map.put("name", hospitalCName);
			map.put("parentid", "root");
			inList.add(map);
		}
		
		/**
		 * 3.处理科室List，将已存在的添加到oldList，将不存在的添加到newList
		 */ 
		for (Map<String, Object> map : inList) {
			
			if("1".equals(map.get("id")+"")) {
				map.put("id", "wx1");
			}
			
			if("1".equals(map.get("parentid")+"")) {
				map.put("parentid", "wx1");
			}
			
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("id", map.get("id"));
			map1.put("source", "2");
			map1.put("projectSchema", projectSchema);
			
			List<Map<String, Object>> list = dao.query("ACDE01.queryDeptList", map1);
			
			if("1".equals(isWX)) {
				if("0".equals(map.get("parentid")+"")) {
					map.put("parentid", "root");
				}
			}
//			if("2".equals(isWX)) {
//				if("wx1".equals(map.get("parentid")+"")) {
//					map.put("parentid", "root");
//				}
//			}
			
			map.put("deptNum", map.get("id"));
			map.put("status", "1");
			map.put("source", "2");
			
			
			//将新的数据插入数据库，将旧的数据进行更新
			if(CollectionUtils.isNotEmpty(list)) {
				map.put("recRevisor", recCreater);
				map.put("recReviseTime", recCreateTime);
				oldList.add(map);
//				if(!"2".equals(isWX) &&  !"wx1".equals(map.get("parentid")+"")) {
//					oldList.add(map);
//				}
			}else {
				map.put("type", "0");
				map.put("recCreater", recCreater);
				map.put("recCreateTime", recCreateTime);
				newList.add(map);
//				if(!"2".equals(isWX) &&  !"wx1".equals(map.get("parentid")+"")) {
//					newList.add(map);
//				}
			}
		}
		
		/**
		 * 4.将改数据库所有科室改为停用状态
		 */ 
		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("source", "2");
		map2.put("projectSchema", projectSchema);
		List<Map<String, Object>> deptList = dao.query("ACDE01.queryDeptList", map2);
		
		if(CollectionUtils.isNotEmpty(deptList)) {
			HashMap<String, Object> deptmap = new HashMap<>();
			deptmap.put("list", deptList);
			deptmap.put("projectSchema", projectSchema);
			dao.update("ACDE01.stopDepartment", deptmap);
		}
		
		/**
		 * 5.如果 oldList 不为空 则修改数据库
		 */ 
		if (CollectionUtils.isNotEmpty(oldList)) {
			// 批量修改oldList
			for (Map<String, Object> map : oldList) {
				HashMap<String, Object> map1 = new HashMap<>();
				map1.put("id", map.get("id")+"");
				map1.put("deptNum", map.get("deptNum")+"");
				map1.put("name", map.get("name")+"");
				map1.put("parentid", map.get("parentid")+"");
				map1.put("status", map.get("status")+"");
				map1.put("source", map.get("source")+"");
				map1.put("recRevisor", map.get("recRevisor")+"");
				map1.put("recReviseTime", map.get("recReviseTime")+"");
				map1.put("projectSchema", projectSchema);
				
				dao.update("ACDE01.updateDingDept", map1);
				
				String id = map.get("id")+"";
				updateHosdNum(id);
			}
		}
		
		/**
		 * 6.如果 newList 不为空 则插入数据库
		 */ 
		if (CollectionUtils.isNotEmpty(newList)) {
			// 批量新增newList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", newList);
			map1.put("projectSchema", projectSchema);

			dao.insert("ACDE01.insertDingDept", map1);
			newList.forEach(e -> {
				String id = e.get("id").toString();
				updateHosdNum(id);
			});
		}
		
		/**
		 * 7.返回操作结果
		 */
		// 返回
		EiInfo outInfo = new EiInfo();
		outInfo.setMsg("同步完成!");
		outInfo.setStatus(0);
		return outInfo;
	}
	
	
}
