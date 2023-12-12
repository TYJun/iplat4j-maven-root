package com.baosight.wilp.ac.fw.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.wilp.mc.fw.util.SendingMsgUtil;
import com.baosight.xservices.xs.authentication.SecurityBridgeFactory;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 对外服务接口.
 * <p>
 * 查询员工列表, 通过员工ID或者员工工号查询员工详情, 通过科室ID或者科室编码查询科室下员工, 通过科室ID或者科室编码查询科室下员工数量
 * 查询科室列表, 通过科室ID或者科室编码查询科室详情, 通过员工ID或者员工工号查询所属科室详情, 通过地点获取科室信息, 通过科室查询地点信息...
 * </p>
 *
 * @Title ServiceACFW01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
@SuppressWarnings("unchecked")
public class ServiceACFW01 extends ServiceBase {

	/**
	 * 1.查询员工列表
	 * 作者：hcd
	 * 入参：EiInfo（分页使用的offset，limit；人员ID userId，人员工号workNo，用户名userName 科室ID
	 * deptId，科室编号 deptNum， 科室名称 deptName ）
	 * 出参：EiInfo（满足入参条件的员工列表） 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断是否是带分页查询
	 * 3.根据是否带分页，使用不同方法从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryStaffList(EiInfo inInfo) {
		/**
		 * 1.从入参中获取参数存入map中
		 */
		String offset = inInfo.get("offset") == null ? "" : inInfo.getString("offset");
		String limit = inInfo.get("limit") == null ? "" : inInfo.getString("limit");
		String userId = inInfo.get("userId") == null ? "" : inInfo.getString("userId");
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");
		String workNo1 = inInfo.get("workNo1") == null ? "" : inInfo.getString("workNo1");
		String userName = inInfo.get("userName") == null ? "" : inInfo.getString("userName");
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId");
		String deptNum = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");
		String deptName = inInfo.get("deptName") == null ? "" : inInfo.getString("deptName");
		String datagroupCode = inInfo.get("datagroupCode") == null ? "" : inInfo.getString("datagroupCode");

		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("workNo", workNo);
		map.put("workNo1", workNo1);
		map.put("userName", userName);
		map.put("deptId", deptId);
		map.put("deptNum", deptNum);
		map.put("deptName", deptName);
		map.put("datagroupCode", datagroupCode);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		/**
		 * 2.判断是否是带分页查询
		 */
		// 当offset和limit任意为空，视为不分页查询
		if ("".equals(offset) || "".equals(limit)) {
			list = dao.query("ACFW01.queryStaffList", map, 0, -999999);
			inInfo.set("result", list);
		} else {
			list = dao.query("ACFW01.queryStaffList", map, Integer.parseInt(offset), Integer.parseInt(limit));
			int count = dao.count("ACFW01.queryStaffCount", map);
			// 返回
			if (!CollectionUtils.isEmpty(list)) {
				/**
				 * 3.根据是否带分页，使用不同方法从数据库中查询出相关数据存入EiInfo result 域
				 */
				inInfo = PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
			}
		}
		/**
		 * 4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 2.通过员工ID或者员工工号查询员工详情
	 * 作者：hcd
	 * 入参：EiInfo（员工ID userId，员工工号 workNo）
	 * 出参：EiInfo（满足入参条件的员工详情）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断userId，workNo是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryStaffInfo(EiInfo inInfo) {
		/**
		 * 1.从入参中获取参数存入map中
		 */
		String userId = inInfo.get("userId") == null ? "" : inInfo.getString("userId");
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");

		/**
		 *  2.判断userId，workNo是否全为空
		 */
		if ("".equals(userId) && "".equals(workNo)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {
			/**
			 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			String projectSchema = PrUtils.getConfigure();

			Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("workNo1", workNo);
			map.put("projectSchema", projectSchema);

			List<Map<String, Object>> list = dao.query("ACFW01.queryStaffList", map);
			if (!CollectionUtils.isEmpty(list)) {
				inInfo.set("result", list.get(0));
			}
		}

		/**
		 *  4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 3.通过科室ID或者科室编码查询科室下员工
	 * 作者：hcd
	 * 入参：EiInfo（科室ID deptId，科室编号 deptNum）
	 * 出参：EiInfo（满足入参条件的科室详情）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断deptId，deptNum是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryStaffByDept(EiInfo inInfo) {

		/**
		 *  1.从入参中获取参数存入map中
		 */
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId");
		String deptNum = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");

		/**
		 * 2.判断deptId，deptNum是否全为空
		 */
		if ("".equals(deptId) && "".equals(deptNum)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {

			/**
			 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			String projectSchema = PrUtils.getConfigure();

			Map<String, String> map = new HashMap<>();
			map.put("deptId", deptId);
			map.put("deptNum", deptNum);
			map.put("projectSchema", projectSchema);

			List<Map<String, Object>> list = dao.query("ACFW01.queryStaffList", map);
			inInfo.set("result", list);
		}

		/**
		 * 4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 4.通过科室ID或者科室编码查询科室下员工数量
	 * 作者：hcd
	 * 入参：EiInfo（科室ID deptId，科室编号 deptNum）
	 * 出参：EiInfo（满足入参条件的科室详情和）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断deptId，deptNum是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo count 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryStaffCountByDept(EiInfo inInfo) {

		/**
		 * 1.从入参中获取参数存入map中
		 */
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId");
		String deptNum = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");
		/**
		 * 2.判断deptId，deptNum是否全为空
		 */
		if ("".equals(deptId) && "".equals(deptNum)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {
			/**
			 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo count 域
			 */
			String projectSchema = PrUtils.getConfigure();

			Map<String, String> map = new HashMap<>();
			map.put("deptId", deptId);
			map.put("deptNum", deptNum);
			map.put("projectSchema", projectSchema);

			int count = super.count("ACFW01.queryStaffCount", map);

			Map<String, Object> map1 = new HashMap<>();
			map1.put("count", count);
			inInfo.set("result", map1);
		}
		/**
		 *  4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 5.查询科室列表
	 * 作者：hcd
	 * 入参：EiInfo（分页使用的offset，limit；科室ID deptId，科室编号deptNum，科室名称deptName）
	 * 出参：EiInfo（满足入参条件的科室列表）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断是否是带分页查询
	 * 3.根据是否带分页，使用不同方法从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryDeptList(EiInfo inInfo) {
		/**
		 *  1.从入参中获取参数存入map中
		 */
		String offset = inInfo.get("offset") == null ? "" : inInfo.getString("offset");
		String limit = inInfo.get("limit") == null ? "" : inInfo.getString("limit");
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId");
		String deptNum = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");
		String deptName = inInfo.get("deptName") == null ? "" : inInfo.getString("deptName");
		String datagroupCode = inInfo.get("datagroupCode") == null ? "" : inInfo.getString("datagroupCode");

		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("deptId", deptId);
		map.put("deptNum", deptNum);
		map.put("deptName", deptName);
		map.put("datagroupCode", datagroupCode);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/**
		 * 2.判断是否是带分页查询
		 */
		// 当offset和limit任意为空，视为不分页查询
		if ("".equals(offset) || "".equals(limit)) {
			list = dao.query("ACFW01.queryDeptList", map);
			inInfo.set("result", list);
		} else {
			/**
			 * 3.根据是否带分页，使用不同方法从数据库中查询出相关数据存入EiInfo result 域
			 */
			list = dao.query("ACFW01.queryDeptList", map, Integer.parseInt(offset), Integer.parseInt(limit));
			int count = dao.count("ACFW01.queryDeptCount", map);
			// 返回
			if (list != null && list.size() > 0) {
				inInfo = PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
			}
		}
		/**
		 *  4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 6.通过科室ID或者科室编码查询科室详情
	 * 作者：hcd
	 * 入参：EiInfo（科室ID deptId，科室编号 deptNum）
	 * 出参：EiInfo（满足入参条件的科室详情）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断deptId，deptNum是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryDeptInfo(EiInfo inInfo) {
		/**
		 * 1.从入参中获取参数存入map中
		 */
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId");
		String deptNum = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");
		/**
		 * 2.判断deptId，deptNum是否全为空
		 */
		if ("".equals(deptId) && "".equals(deptNum)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {
			/**
			 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			String projectSchema = PrUtils.getConfigure();

			Map<String, String> map = new HashMap<>();
			map.put("deptId", deptId);
			map.put("deptNum", deptNum);
			map.put("projectSchema", projectSchema);

			List<Map<String, Object>> list = dao.query("ACFW01.queryDeptList", map);

			if (!CollectionUtils.isEmpty(list)) {
				inInfo.set("result", list.get(0));
			}
		}
		/**
		 * 4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 7.通过员工ID或者员工工号查询所属科室详情
	 * 作者：hcd
	 * 入参：EiInfo（员工ID userId，员工工号 workNo）
	 * 出参：EiInfo（满足入参条件的科室详情）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断userId，workNo是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryDeptInfoByUser(EiInfo inInfo) {
		/**
		 * 1.从入参中获取参数存入map中
		 */
		String userId = inInfo.get("userId") == null ? "" : inInfo.getString("userId");
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");
		/**
		 * 2.判断userId，workNo是否全为空
		 */
		if ("".equals(userId) && "".equals(workNo)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {
			/**
			 *  3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			String projectSchema = PrUtils.getConfigure();
			Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("workNo", workNo);
			map.put("projectSchema", projectSchema);

			List<Map<String, Object>> list = dao.query("ACFW01.queryDeptList", map);
			if (!CollectionUtils.isEmpty(list)) {
				inInfo.set("result", list.get(0));
			}
		}
		/**
		 *  4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 8.通过地点获取科室信息
	 * 作者：hcd
	 * 入参：EiInfo（地点ID spotId，地点编号 spotNum，地点名称spotName）
	 * 出参：EiInfo（满足入参条件的科室详情）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断spotId，spotNum，spotName是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryDeptInfoBySpot(EiInfo inInfo) {
		/**
		 * 1.从入参中获取参数存入map中
		 */
		String spotId = inInfo.get("spotId") == null ? "" : inInfo.getString("spotId");
		String spotNum = inInfo.get("spotNum") == null ? "" : inInfo.getString("spotNum");
		String spotName = inInfo.get("spotName") == null ? "" : inInfo.getString("spotName");
		/**
		 * 2.判断spotId，spotNum，spotName是否全为空
		 */
		if ("".equals(spotId) && "".equals(spotNum) && "".equals(spotName)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {
			/**
			 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			String projectSchema = PrUtils.getConfigure();
			Map<String, String> map = new HashMap<>();
			map.put("spotId", spotId);
			map.put("spotNum", spotNum);
			map.put("spotName", spotName);
			map.put("projectSchema", projectSchema);

			List<Map<String, Object>> list = dao.query("ACFW01.queryDeptList", map);
			if (!CollectionUtils.isEmpty(list)) {
				inInfo.set("result", list.get(0));
			}
		}
		/**
		 * 4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 9. 通过科室查询地点信息
	 * 作者：hcd
	 * 入参：EiInfo（科室ID deptId，科室编号deptNum）
	 * 出参：EiInfo（满足入参条件的地点详情）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.从数据库中查询出相关数据存入EiInfo result域
	 * 3.返回 EiInfo
	 */
	public EiInfo querySpotByDept(EiInfo inInfo) {
		/**
		 *  1.从入参中获取参数存入map中
		 */
		String offset = inInfo.get("offset") == null ? "" : inInfo.getString("offset");
		String limit = inInfo.get("limit") == null ? "" : inInfo.getString("limit");
		String deptId = inInfo.get("deptId") == null ? "" : inInfo.getString("deptId");
		String deptNum = inInfo.get("deptNum") == null ? "" : inInfo.getString("deptNum");
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");
		String spotNum = inInfo.get("spotNum") == null ? "" : inInfo.getString("spotNum");
		String spotName = inInfo.get("spotName") == null ? "" : inInfo.getString("spotName");

		String projectSchema = PrUtils.getConfigure();
		Map<String, String> map = new HashMap<>();
		map.put("deptId", deptId);
		map.put("deptNum", deptNum);
		map.put("id", id);
		map.put("spotNum", spotNum);
		map.put("spotName", spotName);
		map.put("projectSchema", projectSchema);

		/**
		 *  2.从数据库中查询出相关数据存入EiInfo result域
		 */
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 当offset和limit任意为空，视为不分页查询
		if ("".equals(offset) || "".equals(limit)) {
			list = dao.query("ACFW01.querySpotByDept", map, 0, -999999);
			inInfo.set("result", list);
		} else {
			list = dao.query("ACFW01.querySpotByDept", map, Integer.parseInt(offset), Integer.parseInt(limit));
			int count = dao.count("ACFW01.querySpotByDeptCount", map);
			// 返回
			if (!CollectionUtils.isEmpty(list)) {
				/**
				 * 3.根据是否带分页，使用不同方法从数据库中查询出相关数据存入EiInfo result 域
				 */
				inInfo = PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
			}
		}


		/**
		 * 3.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 10.通过电话获取科室和地点信息
	 * 作者：hcd
	 * 入参：EiInfo（电话号码 telNum）
	 * 出参：EiInfo（满足入参条件的科室和地点信息）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断telNum是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo querySpotByTel(EiInfo inInfo) {
		/**
		 * 1.从入参中获取参数存入map中
		 */
		String telNum = inInfo.get("telNum") == null ? "" : inInfo.getString("telNum");
		String datagroupCode = inInfo.get("datagroupCode") == null ? "" : inInfo.getString("datagroupCode");
		/**
		 * 2.判断telNum是否全为空
		 */
		if ("".equals(telNum)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能为空!");
		} else {
			/**
			 *  3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			String projectSchema = PrUtils.getConfigure();
			Map<String, String> map = new HashMap<>();
			map.put("telNum", telNum);
			map.put("datagroupCode", datagroupCode);
			map.put("projectSchema", projectSchema);
			List<Map<String, Object>> list = dao.query("ACFW01.querySpotByTel", map);
			inInfo.set("result", list);
		}

		/**
		 * 4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 11.通过地点获取电话信息
	 * 作者：hcd
	 * 入参：EiInfo（地点ID spotId，spotNum 地点编号，spotName地点名称）
	 * 出参：EiInfo（满足入参条件的电话信息）
	 * 处理：
	 * 1.从入参中获取参数存入map中
	 * 2.判断spotId，spotNum，spotName是否全为空
	 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
	 * 4.返回 EiInfo
	 */
	public EiInfo queryTelBySpot(EiInfo inInfo) {

		/**
		 *  1.从入参中获取参数存入map中
		 */
		String spotId = inInfo.get("spotId") == null ? "" : inInfo.getString("spotId");
		String spotNum = inInfo.get("spotNum") == null ? "" : inInfo.getString("spotNum");
		String spotName = inInfo.get("spotName") == null ? "" : inInfo.getString("spotName");

		/**
		 * 2.判断spotId，spotNum，spotName是否全为空
		 */
		if ("".equals(spotId) && "".equals(spotNum) && "".equals(spotName)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("参数不能全部为空!");
		} else {
			/**
			 * 3.如果不全为空则从数据库中查询出相关数据存入EiInfo result 域
			 */
			String projectSchema = PrUtils.getConfigure();
			Map<String, String> map = new HashMap<>();
			map.put("spotId", spotId);
			map.put("spotNum", spotNum);
			map.put("spotName", spotName);
			map.put("projectSchema", projectSchema);

			List<Map<String, Object>> list = dao.query("ACFW01.querySpotByTel", map);
			inInfo.set("result", list);
		}
		/**
		 * 4.返回 EiInfo
		 */
		return inInfo;
	}

	/**
	 * 12.获取所有服务科室
	 * 作者：hcd
	 * 入参：EiInfo（）
	 * 出参：EiInfo（类型为服务科室的科室列表）
	 * 处理：
	 * 1.设置查询条件map
	 * 2.调用query()方法查出符合条件的科室列表
	 * 3.将科室list封装在EiInfo 的result域并返回EiInfo
	 */
	public EiInfo queryServiceDept(EiInfo inInfo) {
		/**
		 * 1.设置查询条件map
		 */
		String datagroupCode = inInfo.get("datagroupCode") == null ? "" : inInfo.getString("datagroupCode");
		String projectSchema = PrUtils.getConfigure();
		Map<String, String> map = new HashMap<>();
		map.put("type", "1");
		map.put("datagroupCode", datagroupCode);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.调用query()方法查出符合条件的科室列表
		 */
		List<Map<String, Object>> list = dao.query("ACFW01.queryDeptList", map);

		/**
		 * 3.将科室list封装在EiInfo 的result域并返回EiInfo
		 */
		inInfo.set("result", list);
		return inInfo;
	}

	/**
	 * 13.获取所有楼
	 * 作者：hcd
	 * 入参：EiInfo（）
	 * 出参：EiInfo（所有楼list）
	 * 处理：
	 * 1.设置查询条件map
	 * 2.调用query()方法查出所有楼
	 * 3.将楼list封装在EiInfo 的result域并返回EiInfo
	 */
	public EiInfo getBuilding(EiInfo inInfo) {
		/**
		 * 1.设置查询条件map
		 */
		String building = inInfo.get("building") == null ? "" : inInfo.getString("building");
		String datagroupCode = inInfo.get("datagroupCode") == null ? "" : inInfo.getString("datagroupCode");
		String projectSchema = PrUtils.getConfigure();
		Map<String, String> map = new HashMap<>();
		map.put("building", building);
		map.put("datagroupCode", datagroupCode);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.调用query()方法查出所有楼
		 */
		List<Map<String, Object>> list = dao.query("ACFW01.getBuilding", map);

		/**
		 * 3.将楼list封装在EiInfo 的result域并返回EiInfo
		 */
		inInfo.set("result", list);
		return inInfo;
	}

	/**
	 * 14.获取层
	 * 作者：hcd
	 * 入参：EiInfo（building，fioor）
	 * 出参：EiInfo（所有层list）
	 * 处理：
	 * 1.设置查询条件map
	 * 2.判断入参楼building是否为空
	 * 3.调用query()方法查出符合条件的层列表
	 * 4.将层list封装在EiInfo 的result域并返回EiInfo
	 */
	public EiInfo getFloor(EiInfo inInfo) {
		/**
		 * 1.设置查询条件map
		 */
		String building = inInfo.get("building") == null ? "" : inInfo.getString("building");
		String fioor = inInfo.get("fioor") == null ? "" : inInfo.getString("fioor");
		/**
		 * 2.判断入参楼building是否为空
		 */
		if ("".equals(building)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("必填参数参数不能为空!");
		} else {
			/**
			 * 3.调用query()方法查出符合条件的层列表
			 */
			String projectSchema = PrUtils.getConfigure();
			Map<String, String> map = new HashMap<>();
			map.put("building", building);
			map.put("fioor", fioor);
			map.put("projectSchema", projectSchema);
			List<Map<String, Object>> list = dao.query("ACFW01.getFloor", map);
			inInfo.set("result", list);
		}
		return inInfo;
	}

	/**
	 * 15.获取房间
	 * 作者：hcd
	 * 入参：EiInfo（building，fioor，room）
	 * 出参：EiInfo（所有房间list）
	 * 处理：
	 * 1.设置查询条件map
	 * 2.调用query()方法查出符合条件的房间列表
	 * 3.将房间list封装在EiInfo 的result域并返回EiInfo
	 */
	public EiInfo getRoom(EiInfo inInfo) {

		/**
		 * 1.设置查询条件map
		 */
		String building = inInfo.get("building") == null ? "" : inInfo.getString("building");
		String floor = inInfo.get("floor") == null ? "" : inInfo.getString("floor");
		String room = inInfo.get("room") == null ? "" : inInfo.getString("room");

		if ("".equals(building) || "".equals(floor)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("必填参数参数不能为空!");
		} else {
			/**
			 * 2.调用query()方法查出符合条件的房间列表
			 */
			String projectSchema = PrUtils.getConfigure();
			Map<String, String> map = new HashMap<>();
			map.put("building", building);
			map.put("floor", floor);
			map.put("room", room);
			map.put("projectSchema", projectSchema);

			List<Map<String, Object>> list = dao.query("ACFW01.getRoom", map);
			inInfo.set("result", list);
		}
		/**
		 *  3.将房间list封装在EiInfo 的result域并返回EiInfo
		 */
		return inInfo;
	}

	/**
	 * 更新CID
	 * 作者：hcd
	 * 入参：EiInfo(workNo 工号，cid 个推cid)
	 * 出参：EiInfo（操作结果）
	 * 操作：
	 * 1.从EiInfo中获取工号和cid
	 * 2.验证工号和cid是否为空
	 * 3.若不为空，则调用dao.update()方法更新结果
	 * 4.返回结果
	 */
	public EiInfo updateCid(EiInfo inInfo) {
		/**
		 * 1.从EiInfo中获取工号和cid
		 */
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");
		String cid = inInfo.get("cid") == null ? "" : inInfo.getString("cid");
		/**
		 * 2.验证工号和cid是否为空
		 */
		if ("".equals(workNo) || "".equals(cid)) {
			inInfo.setStatus(-1);
			inInfo.setMsg("必填参数参数不能为空!");
		} else {
			/**
			 * 3.若不为空，则调用dao.update()方法更新结果
			 */
			String projectSchema = PrUtils.getConfigure();
			Map<String, String> map = new HashMap<>();
			map.put("workNo", workNo);
			map.put("cid", cid);
			map.put("projectSchema", projectSchema);

			dao.update("ACFW01.updateCid", map);
		}
		/**
		 * 4.返回结果
		 */
		return inInfo;
	}

	/**
	 * 16.查询物资列表
	 * 作者：hcd
	 * 入参：EiInfo（分页使用的offset，limit；物资ID userId，物资编码 matCode，物资名称 matName 物资分类id matClassId
	 * 		物资分类编码 matClassCode  物资分类名称 matClassName
	 * 出参：EiInfo（满足入参条件的物资列表）
	 * 处理：
	 * 1.从EiInfo中读入数据
	 * 2.判断是否需要分页
	 * 3.执行查询方法
	 * 4.返回查询结果
	 */
	public EiInfo queryMaterialList(EiInfo inInfo) {

		/**
		 * 1.从EiInfo中读入数据
		 */
		String offset = inInfo.get("offset") == null ? "" : inInfo.getString("offset");
		String limit = inInfo.get("limit") == null ? "" : inInfo.getString("limit");

		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");
		String matCode = inInfo.get("matCode") == null ? "" : inInfo.getString("matCode");
		String matName = inInfo.get("matName") == null ? "" : inInfo.getString("matName");
		String matSpec = inInfo.get("matSpec") == null ? "" : inInfo.getString("matSpec");
		String matClassId = inInfo.get("matClassId") == null ? "" : inInfo.getString("matClassId");
		String matClassCode = inInfo.get("matClassCode") == null ? "" : inInfo.getString("matClassCode");
		String matClassName = inInfo.get("matClassName") == null ? "" : inInfo.getString("matClassName");
		String matTypeCode = inInfo.get("matTypeCode") == null ? "" : inInfo.getString("matTypeCode");
		String  orderBy = inInfo.get("orderBy") == null ? "" : inInfo.getString("orderBy");
		//数据隔离
		String excludeGoods = inInfo.get("excludeGoods") == null ? "" : inInfo.getString("excludeGoods");
		String projectSchema = PrUtils.getConfigure();

		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("matCode", matCode);
		map.put("matName", matName);
		map.put("matClassId", matClassId);
		map.put("matClassCode", matClassCode);
		map.put("matClassName", matClassName);
		map.put("matSpec", matSpec);
		map.put("excludeGoods",excludeGoods);
		if(matTypeCode!=null&&matTypeCode.indexOf(",")>0)
		{
		    map.put("matTypeCodeArray", matTypeCode.split(","));
		}
		else
		{
		    map.put("matTypeCode", matTypeCode);
		}
		map.put("projectSchema", projectSchema);
		map.put("orderBy", orderBy);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		/**
		 * 2.判断是否需要分页
		 */
		// 当offset和limit任意为空，视为不分页查询
		if ("".equals(offset) || "".equals(limit)) {
			list = dao.query("ACFW01.queryMaterialList", map, 0, -999999);
			inInfo.set("result", list);
		} else {
			/**
			 * 3.执行查询方法
			 */
			list = dao.query("ACFW01.queryMaterialList", map, Integer.parseInt(offset), Integer.parseInt(limit));
			int count = dao.count("ACFW01.queryMaterialListCount", map);
			// 返回
			if (!CollectionUtils.isEmpty(list)) {
				inInfo = PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
			}
		}
		/**
		 * 4.返回查询结果
		 */
		return inInfo;
	}

	/**
	 * 17.查询物资分类树
	 * 作者：hcd
	 * 入参：EiInfo（主键 id，双亲id parentId，物资分类编码matClassCode  物资分类名称 matClassName
	 * 出参：EiInfo（满足入参条件的物资分类列表）
	 * 处理：
	 * 1.从EiInfo中获取参数
	 * 2.调用dao.query()方法查询符合条件的物资分类，将结果封装在EiInfo中的result域中
	 * 3.返回结果
	 */
	public EiInfo queryMaterialTree(EiInfo inInfo) {

		String projectSchema = PrUtils.getConfigure();

		/**
		 * 1.从EiInfo中获取参数
		 */
		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");
		String parentId = inInfo.get("parentId") == null ? "" : inInfo.getString("parentId");
		String matClassCode = inInfo.get("matClassCode") == null ? "" : inInfo.getString("matClassCode");
		String matClassName = inInfo.get("matClassName") == null ? "" : inInfo.getString("matClassName");

		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("parentId", parentId);
		map.put("matClassCode", matClassCode);
		map.put("matClassName", matClassName);
		map.put("projectSchema", projectSchema);

		/**
		 *  2.调用dao.query()方法查询符合条件的物资分类，将结果封装在EiInfo中的result域中
		 */
		List<Map<String, Object>> list = dao.query("ACFW01.getMaterialClass", map, 0, -999999);
		inInfo.set("result", list);

		/**
		 * 3.返回结果
		 */
		return inInfo;
	}

	/**
	 * 18.获取供应商
	 * 作者：hcd
	 * 入参：EiInfo(分页使用的offset和limit，供应商主键id，供应商编码 supplier_code  供应商名称supplier_name  供应商类型 supplier_type)
	 * 出参：EiInfo（供应商列表）
	 * 处理：
	 * 1.从EiInfo中读取入参
	 * 2.判断是否需要分页
	 * 4.从数据库中查询满足条件的供应商集合
	 * 5.返回结果
	 */
	public EiInfo getSupplier(EiInfo inInfo) {
		/**
		 *  1.从EiInfo中读取入参
		 */
		String offset = inInfo.get("offset") == null ? "" : inInfo.getString("offset");
		String limit = inInfo.get("limit") == null ? "" : inInfo.getString("limit");


		String id = inInfo.get("id") == null ? "" : inInfo.getString("id");
		String supplierCode = inInfo.get("supplierCode") == null ? "" : inInfo.getString("supplierCode");
		String supplierName = inInfo.get("supplierName") == null ? "" : inInfo.getString("supplierName");
		String supplierType = inInfo.get("supplierType") == null ? "" : inInfo.getString("supplierType");

		String projectSchema = PrUtils.getConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("supplierCode", supplierCode);
		map.put("supplierName", supplierName);
		map.put("supplierType", supplierType);
		map.put("projectSchema", projectSchema);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/**
		 *  2.判断是否需要分页
		 */
		// 当offset和limit任意为空，视为不分页查询
		if ("".equals(offset) || "".equals(limit)) {
			list = dao.query("ACFW01.getSupplierList", map, 0, -999999);
			inInfo.set("result", list);
		} else {
			/**
			 *  4.从数据库中查询满足条件的供应商集合
			 */
			list = dao.query("ACFW01.getSupplierList", map, Integer.parseInt(offset), Integer.parseInt(limit));
			int count = dao.count("ACFW01.getSupplierCount", map);
			// 返回
			if (!CollectionUtils.isEmpty(list)) {
				inInfo = PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
			}
		}
		/**
		 * 5.返回结果
		 */
		return inInfo;
	}

	/**
	 * 19.获取多院区设置
	 * 作者：hcd
	 * 入参：EiInfo()
	 * 出参：EiInfo（）
	 * 处理：
	 * 1.
	 * 2.
	 */
	public EiInfo getHospitalSetting(EiInfo inInfo) {
		/**
		 *  1.从EiInfo中读取入参
		 */

		String platSchema = PrUtils.getIplatConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("platSchema", platSchema);

		List<Map<String, Object>> list = dao.query("ACFW01.getHospitalSetting", map);

		inInfo.set("hospitalAreasNumber", list.get(0).get("FVALUE").toString());

		return inInfo;
	}

	/**
	 * 20.获取Edcc03
	 * 作者：hcd
	 * 入参：EiInfo()
	 * 出参：EiInfo（）
	 * 处理：
	 * 1.
	 * 2.
	 */
	public EiInfo getEdcc03(EiInfo inInfo) {
		/**
		 *  1.从EiInfo中读取入参
		 */

		String fkey = inInfo.getString("fkey");
		if (fkey == null) {
			fkey = "";
		}

		String platSchema = PrUtils.getIplatConfigure();

		Map<String, String> map = new HashMap<>();
		map.put("fkey", fkey);
		map.put("platSchema", platSchema);

		List<Map<String, Object>> list = dao.query("ACFW01.getEdcc03", map);

		inInfo.set("result", list);

		return inInfo;
	}


	/**
	 * 修改密码服务
	 *
	 * @param inInfo
	 * @return
	 */
	public EiInfo updatePassword(EiInfo inInfo) {
		String workNo = inInfo.get("workNo") == null ? "" : inInfo.getString("workNo");
		String password = inInfo.get("password") == null ? "" : inInfo.getString("password");
//        String encodedPassword = SecurityEncode.encoderByDES(password.replace(" ", ""), "miyao");
		String matchedPassword = SecurityBridgeFactory.getSecurityPasswordEncrypt().encode(password);
		String platSchema = PrUtils.getIplatConfigure();
		String recRevisor = UserSession.getUser().getUsername(); /* 修改人 */
		String recReviseTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); /* 修改时间 */
		Map<String, String> map = new HashMap<>();
		map.put("workNo", workNo);
		map.put("password", matchedPassword);
		map.put("recRevisor", recRevisor);
		map.put("recReviseTime", recReviseTime);
		map.put("platSchema", platSchema);

		dao.update("ACFW01.updateUserPassword", map);

		inInfo.setMsg("修改成功");
		inInfo.setStatus(0);
		/**
		 * 4.返回查询结果
		 */
		return inInfo;

	}

	public EiInfo sendMsgService(EiInfo inInfo) {
		/**
		 *  1.从EiInfo中读取入参
		 */

		String mobile = inInfo.get("mobile") == null ? "" : inInfo.getString("mobile");
		String msg = inInfo.get("msg") == null ? "" : inInfo.getString("msg");
		String module = inInfo.get("module") == null ? "" : inInfo.getString("module");
		SendingMsgUtil.sendingMsg(mobile, msg, module);
		inInfo.setMsg("短信发送成功");
		inInfo.setStatus(0);
		return inInfo;
	}
}
