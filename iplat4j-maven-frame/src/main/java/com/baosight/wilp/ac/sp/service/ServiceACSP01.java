
package com.baosight.wilp.ac.sp.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.ArchivesLog;
import com.baosight.wilp.common.util.DatagroupUtil;
import com.baosight.wilp.common.util.PrUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 地点电话档案信息管理.
 * <p>
 * 页面初始化方法, 默认查询方法, 查询地点, 查询电话, 新增地点,修改地点,更新地点状态,新增电话,修改电话, 更新电话状态, excel 导入地点,
 * excel 导入电话.
 * </p>
 *
 * @Title ServiceACSP01.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */
public class ServiceACSP01 extends ServiceBase {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final String projectSchema = PrUtils.getConfigure();

	/**
	 * 界面初始化
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：返回入参EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		return query(info);
	}

	/**
	 * 默认查询方法
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo
	 * 处理：调用querySpot()方法
	 */
	public EiInfo query(EiInfo info) {
		return querySpot(info);
	}

	/**
	 * 查询地点
	 * 作者:jzm
	 * 入参：EiInfo（当前部门id "curDeptId",地点名称
	 * "spotName",楼"building",层"floor",房间"room"）
	 * 出参：EiInfo（地点信息）
	 * 处理：
	 * 1.获取当前登陆人的院区，科室信息存入map中
	 * 2.从入参中读取"curDeptId","spotName","building","floor","room"存入map
	 * 3.调用query()方法从数据库中查询出满足入参条件的结果
	 * 4.将结果封装在EiInfo的resultSpot域中返回
	 */
	public EiInfo querySpot(EiInfo info) {
		/**
		 *  1.获取当前登陆人的院区，科室信息存入map中
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getUser().getUsername();
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserDepts");
		EiInfo xinfo = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> xlist = (ArrayList<HashMap<String, Object>>) xinfo.get("result");

		/**
		 * 2.从入参中读取"curDeptId","spotName","building","floor","room"存入map
		 */
		String[] param = { "curDeptId", "deptName", "spotName", "building", "floor", "room", "status", "telNum" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = PrUtils.changeToMap(info, "resultSpot", plist);


		/**
		 * 3.调用query()方法从数据库中查询出满足入参条件的结果
		 */
		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "1");
		map.put("list", xlist);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("ACSP01.querySpot", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("ACSP01.countSpot", map);


		/**
		 * 4.将结果封装在EiInfo的resultSpot域中返回
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(info, "resultSpot", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return info;
		}
	}

	/**
	 * 查询电话
	 * 作者：jzm
	 * 入参：EiInfo（当前科室id "curDeptId",当前地点id "curSpotId"） 出参：EiInfo（电话信息）
	 * 处理：
	 * 1.获取当前登陆人的院区，科室信息存入map中
	 * 2.从入参中读取"curDeptId","curSpotId"存入map
	 * 3.调用query()方法从数据库中查询出满足入参条件的结果
	 * 4.将结果封装在EiInfo的resultTele域中返回
	 */
	public EiInfo queryTele(EiInfo info) {
		/**
		 *  1.获取当前登陆人的院区，科室信息存入map中
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getUser().getUsername();
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserDepts");
		EiInfo xinfo = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> xlist = (ArrayList<HashMap<String, Object>>) xinfo.get("result");

		/**
		 * 2.从入参中读取"curDeptId","curSpotId"存入map
		 */
		String[] param = { "curDeptId", "curSpotId", "status1", "telNum" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = PrUtils.changeToMap(info, "resultTele", plist);

		// 判断如果telNum有值则单独查询telNum
		if (map.get("telNum") != null && !"".equals(map.get("telNum"))) {
			map.put("curDeptId", "");
			map.put("curSpotId", "");
		}
		/**
		 * 3.调用query()方法从数据库中查询出满足入参条件的结果
		 */
		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "1");
		map.put("list", xlist);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("ACSP01.queryTele", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("ACSP01.countTele", map);

		/**
		 *  4.将结果封装在EiInfo的resultTele域中返回
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(info, "resultTele", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return info;
		}
	}

	/**
	 * 新增地点
	 * 作者：jzm
	 * 入参：EiInfo（地点名称spotName，所属科室ID deptId，所属科室名称
	 * deptName，楼building，层floor，房间room）
	 * 出参：EiInfo(操作结果)
	 * 处理：
	 * 1.从入参中读取新增地点的信息
	 * 2.自动生成主键和按规则生成地点编号
	 * 3.读取当前时间存入recCreateTime，读取当前用户名存入recCreater
	 * 4.调用insert()方法执行数据库插入操作
	 * 5.返回操作结果
	 */
	@ArchivesLog(model = "AC", sign = "新增地点")
	public EiInfo insertSpot(EiInfo info) {
		/**
		 *  1.从入参中读取新增地点的信息
		 */
		Map map = info.getAttr(); // 新增地点的信息

		/**
		 *  2.自动生成主键和按规则生成地点编号
		 */
		String spotId = UUID.randomUUID().toString(); // 主键
		String spotNum = null;

		// 先查询出当前最新的 rec_create_time 取出该记录的spotNum
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("projectSchema", projectSchema);
		List<Map<String, String>> list = dao.query("ACSP01.queryLastSpotNum", map1);

		if (CollectionUtils.isEmpty(list)) { // 如果没有记录则生成一号SP00001
			spotNum = "SP00001";
		} else {
			spotNum = genSpotNum(list.get(0).get("spotNum")); // 生成地点编号
		}

		/**
		 * 3.读取当前时间存入recCreateTime，读取当前用户名存入recCreater
		 */
		String recCreateTime = sdf.format(new Date()); // 创建时间
		String recCreater = UserSession.getUser().getUsername();// 创建人


		/**
		 *  4.调用insert()方法执行数据库插入操作
		 */
		map.put("spotId", spotId);
		map.put("recCreateTime", recCreateTime);
		map.put("recCreater", recCreater);
		map.put("spotNum", spotNum);
		map.put("projectSchema", projectSchema);

		dao.insert("ACSP01.insertSpot", map);

		/**
		 * 5.返回操作结果
		 */
		info.setMsg("地点添加成功!");
		return info;
	}

	/**
	 * 修改地点
	 * 作者：jzm
	 * 入参：EiInfo（地点名称spotName，所属科室ID deptId，所属科室名称
	 * deptName，楼building，层floor，房间room）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取要修改地点的信息存入map中
	 * 2.获取当前时间存入recReviseTime；获取当前用户存入recRevisor
	 * 3.将修改时间recReviseTime和修改人recRevisor存入map中
	 * 4.调用update()方法执行数据库更新操作
	 * 5.返回操作结果
	 */
	@ArchivesLog(model = "AC", sign = "修改地点")
	public EiInfo updateSpot(EiInfo info) {
		/**
		 *  1.从入参中读取要修改地点的信息存入map中
		 */
		Map map = info.getAttr(); // 要修改地点的信息


		/**
		 * 2.获取当前时间存入recReviseTime；获取当前用户存入recRevisor
		 */
		String recReviseTime = sdf.format(new Date()); // 修改时间
		String recRevisor = UserSession.getUser().getUsername();// 修改人

		/**
		 * 3.将修改时间recReviseTime和修改人recRevisor存入map中
		 */
		map.put("recReviseTime", recReviseTime);
		map.put("recRevisor", recRevisor);
		map.put("projectSchema", projectSchema);


		/**
		 * 4.调用update()方法执行数据库更新操作
		 */
		dao.update("ACSP01.updateSpot", map);

		/**
		 *  5.返回操作结果
		 */
		info.setMsg("地点修改成功!");
		return info;
	}

	/**
	 * 删除地点 入参：EiInfo（待删除地点的id list） 出参：EiInfo(操作结果) 处理： 1.从入参中读取待删除地点的id list
	 * 2.判断该地点下有无电话信息，如果有则提示无法删除，结束此方法返回错误信息 3.如果可以删除，则执行delete()方法将相关数据从数据库中删除
	 * 4.返回操作结果
	 */
//	public EiInfo deleteSpot(EiInfo info) {
//		List<String> spotIds = (List<String>) info.get("list");
//
//		// 首先判断一下该地点下有无电话信息，如果有则提示无法删除，结束此方法
//		for (String spotId : spotIds) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("curSpotId", spotId);
//			int num = super.count("ACSP01.countTele", map);
//			if(num > 0) {
//				info.setMsg("删除失败,请先删除所选地点下的电话信息");
//				info.setStatus(-1);
//				return info;
//			}
//		}
//
//    	dao.delete("ACSP01.deleteSpot", spotIds);
//    	info.setMsg("删除成功");
//		return info;
//	}
//

	/**
	 * 更新地点状态
	 * 作者：jzm
	 * 入参EiInfo(地点id list)
	 * 出参EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取地点id list
	 * 2.判断当前更新操作流向根据具体流行执行
	 * 3.返回操作结果
	 * @param inInfo
	 * @return
	 */
	public EiInfo updateSpotStatus(EiInfo inInfo) {
		/**
		 * 1.从入参中读取地点id list
		 */
		List<String> list = (List<String>) inInfo.get("list");
		String status = inInfo.getString("status");
		Map<String, Object> map = new HashMap<>();

		map.put("list", list);
		map.put("status", status);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.判断当前更新操作流向根据具体流行执行
		 */
		// 如果状态是启用->停用
		if ("0".equals(map.get("status"))) {
			// 首先判断一下该地点下有无电话信息，如果有则提示无法停用，结束此方法
			for (String spotId : list) {
				Map<String, String> mp = new HashMap<>();
				mp.put("curSpotId", spotId);
				mp.put("projectSchema", projectSchema);
				int num = super.count("ACSP01.countUsingTele", mp);
				if (num > 0) {
					inInfo.setMsg("停用失败,请先停用所选地点下的电话信息");
					inInfo.setStatus(-1);
					return inInfo;
				}
			}

			dao.update("ACSP01.updateSpotStatus", map);
			inInfo.setStatus(0);
			inInfo.setMsg("操作成功");
		}
		// 否则状态是停用->启用
		else {
			dao.update("ACSP01.updateSpotStatus", map);
			inInfo.setStatus(0);
			inInfo.setMsg("操作成功");
		}

		/**
		 * 3.返回操作结果
		 */
		return inInfo;
	}

	/**
	 * 新增电话
	 * 作者：jzm
	 * 入参：EiInfo（电话号码telNum，所属科室ID deptId，所属地点ID spotId）
	 * 出参：EiInfo(操作结果)
	 * 处理：
	 * 1.从入参中读取新增电话的信息
	 * 2.读取当前时间存入recCreateTime，读取当前用户名存入recCreater
	 * 3.检查同一地点电话唯一性
	 * 4.调用insert()方法执行数据库插入操作
	 * 5.返回操作结果
	 */
	@ArchivesLog(model = "AC", sign = "新增电话")
	public EiInfo insertTele(EiInfo info) {
		/**
		 *  1.从入参中读取新增电话的信息
		 */
		Map map = info.getAttr(); // 新增电话的信息

		/**
		 * 2.读取当前时间存入recCreateTime，读取当前用户名存入recCreater
		 */
		String telId = UUID.randomUUID().toString(); // 主键
		String recCreateTime = sdf.format(new Date()); // 创建时间
		String recCreater = UserSession.getUser().getUsername();// 创建人

		map.put("telId", telId);
		map.put("recCreateTime", recCreateTime);
		map.put("recCreater", recCreater);
		map.put("projectSchema", projectSchema);

		/**
		 *  3.检查同一地点电话唯一性
		 */
		// 检查同地点下电话号码是否已经存在
		int count = super.count("ACSP01.teleIsExistInSpot", map);
		if (count != 0) {
			info.setMsg("电话号码已经存在");
			info.setStatus(-1);
			return info;
		}

		/**
		 *  4.调用insert()方法执行数据库插入操作
		 */
		dao.insert("ACSP01.insertTele", map);

		/**
		 * 5.返回操作结果
		 */
		info.setMsg("电话添加成功!");
		return info;
	}

	/**
	 * 修改电话
	 * 作者：jzm
	 * 入参：EiInfo（电话号码telNum，所属科室ID deptId，所属地点ID spotId）
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取要修改电话的信息存入map中
	 * 2.获取当前时间存入recReviseTime；获取当前用户存入recRevisor
	 * 3.将修改时间recReviseTime和修改人recRevisor存入map中
	 * 4.校验
	 * 5.调用update()方法执行数据库更新操作
	 * 6.返回操作结果
	 */
	@ArchivesLog(model = "AC", sign = "修改电话")
	public EiInfo updateTele(EiInfo info) {
		/**
		 * 1.从入参中读取要修改电话的信息存入map中
		 */
		Map map = info.getAttr(); // 要修改地点的信息

		/**
		 *  2.获取当前时间存入recReviseTime；获取当前用户存入recRevisor
		 */
		String recReviseTime = sdf.format(new Date()); // 修改时间
		String recRevisor = UserSession.getUser().getUsername();// 修改人

		/**
		 *  3.将修改时间recReviseTime和修改人recRevisor存入map中
		 */
		map.put("recReviseTime", recReviseTime);
		map.put("recRevisor", recRevisor);
		map.put("projectSchema", projectSchema);

		/**
		 *  4.校验
		 */
		// 修改电话 验证重复
		// old = 123
		// new = 123
		// 先查询出旧的号码
		List<Map<String, String>> list = (List<Map<String, String>>) dao.query("ACSP01.queryTeleBySpotIdAndId", map);
		Map<String, String> oldTele = list.get(0);
		String oldTeleNum = oldTele.get("telNum");
		String newTeleNum = map.get("telNum").toString();

		// 判断 旧号码和新号码是否一样
		if (!oldTeleNum.equals(newTeleNum)) {
			HashMap<String, String> map1 = new HashMap<>();
			map1.put("telNum", newTeleNum);
			map1.put("spotId", oldTele.get("spotId"));
			map1.put("projectSchema", projectSchema);
			int count = super.count("ACSP01.teleIsExistInSpot", map1);
			if (count != 0) {
				info.setMsg("电话号码已经存在");
				info.setStatus(-1);
				return info;
			}
		}
		/**
		 * 5.调用update()方法执行数据库更新操作
		 */
		dao.update("ACSP01.updateTele", map);

		/**
		 * 6.返回操作结果
		 */
		info.setMsg("电话修改成功!");
		return info;
	}

	/**
	 * 删除电话
	 * 作者：jzm
	 * 入参：EiInfo（待删除电话的id list）
	 * 出参：EiInfo(操作结果)
	 * 处理：
	 * 1.从入参中读取待删除电话的id list
	 * 2.调用delete()方法将相关数据从数据库中删除
	 * 3.返回操作结果
	 */
//	public EiInfo deleteTele(EiInfo info) {
//		List<String> ids = (List<String>) info.get("list");
//    	dao.delete("ACSP01.deleteTele", ids);
//    	info.setMsg("删除成功");
//		return info;
//	}

	/**
	 * 更新电话状态
	 * 入参：EiInfo(电话 id，和状态 status)
	 * 出参：EiInfo（操作结果）
	 * 处理：
	 * 1.从入参中读取tele id list和status
	 * 2.执行update操作
	 * 3.返回操作结果
	 * 作者：jzm
	 * @param inInfo
	 * @return
	 */
	public EiInfo updateTeleStatus(EiInfo inInfo) {
		/**
		 * 1.从入参中读取tele id list和status
		 */
		List<String> list = (List<String>) inInfo.get("list");
		String status = inInfo.getString("status");
		Map<String, Object> map = new HashMap<>();

		map.put("list", list);
		map.put("status", status);
		map.put("projectSchema", projectSchema);

		/**
		 * 2.执行update操作
		 */
		dao.update("ACSP01.updateTeleStatus", map);


		/**
		 *  3.返回操作结果
		 */
		inInfo.setStatus(0);
		inInfo.setMsg("操作成功");
		return inInfo;
	}

	/**
	 * 按照 SP00000 方式生成地点编号
	 * 作者：jzm
	 * 入参：lastSpotNum上一次最后生成的地点编号 出参：下一个地点编号
	 * 处理：取后五位转整型然后加一，转字符串并在前面拼接前缀返回。
	 */
	public static String genSpotNum(String lastSpotNum) {
		return "SP" + String.format("%05d", Integer.parseInt(lastSpotNum.substring(2)) + 1);
	}


	/**
	 * 从 excel 导入地点数据（ExcelController调用此方法）
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo（导入结果，如果有失败的记录，则会将失败的记录和失败原因返回）
	 * 处理：
	 * 1.校验：非空校验，长度校验，地点编号是否重复，所属科室存在校验
	 * 2.将正确的数据写入数据库
	 * 3.返回结果（如果存在错误数据，则一并返回）
	 */
	public EiInfo importSpotFromExcel(EiInfo eiInfo) {
		HashMap<String, String> checkMap = new HashMap<>();
		List<Map<String, String>> inList = (List<Map<String, String>>) eiInfo.get("list");
		List<Map<String, String>> okList = new ArrayList<>();
		List<Map<String, String>> errorList = new ArrayList<>();

		/**
		 * 1.校验：非空校验，长度校验，地点编号是否重复，所属科室存在校验
		 */
		for (Map<String, String> map : inList) {
			StringBuilder error = new StringBuilder();
			if (StringUtils.isEmpty(map.get("deptId"))) {
				error.append("科室编码为空  ");
			}
			if (StringUtils.isEmpty(map.get("spotNum"))) {
				error.append("地点编码为空  ");
			}
			if (StringUtils.isEmpty(map.get("spotName"))) {
				error.append("地点名称为空  ");
			}
			if (StringUtils.isEmpty(map.get("building"))) {
				error.append("楼为空  ");
			}
			if (StringUtils.isEmpty(map.get("floor"))) {
				error.append("层为空  ");
			}
			if (StringUtils.isEmpty(map.get("room"))) {
				error.append("房间为空  ");
			}

			// 判断字段长度是否超出
			if (map.get("deptId").length() > 50) {
				error.append("科室编码长度超过50  ");
			}
			if (map.get("spotNum").length() > 50) {
				error.append("地点编码长度超过50 ");
			}
			if (map.get("spotName").length() > 200) {
				error.append("地点名称长度超过200  ");
			}
			if (map.get("jpSpotName").length() > 50) {
				error.append("身份证号码长度超过32  ");
			}
			if (map.get("building").length() > 50) {
				error.append("楼长度超过50 ");
			}
			if (map.get("floor").length() > 50) {
				error.append("层长度超过50  ");
			}
			if (map.get("room").length() > 50) {
				error.append("房间长度超过50  ");
			}
			if (map.get("remark").length() > 500) {
				error.append("备注长度超过500  ");
			}

			// 验证地点编号是否重复
			HashMap<String, String> map1 = new HashMap<>();
			map1.put("projectSchema", projectSchema);
			map1.put("spotNum", map.get("spotNum"));
			int count1 = super.count("ACSP01.countSpotNum", map1);
			if (count1 != 0 || checkMap.get(map.get("spotNum")) != null) { // deptNum duplicate！
				error.append("地点编码重复  ");
			}

			// 验证所属科室是否存在（如果不存在则错）

			HashMap<String, String> map2 = new HashMap<>();
			map2.put("projectSchema", projectSchema);
			map2.put("deptNum", map.get("deptId"));
			int count2 = super.count("ACDE01.countDeptNum", map2);
			if (count2 == 0) {
				error.append("所属科室不存在  ");
			}

			checkMap.put(map.get("spotNum"), "1");
			// 判断是否存在错误
			if (error.length() == 0) {
				// 无错误，则生成必要信息 然后存入okList
				String id = UUID.randomUUID().toString();
				String recCreateTime = sdf.format(new Date());
				String recCreater = UserSession.getUser().getUsername();
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

		/**
		 * 2.将正确的数据写入数据库
		 */
		// 如果 okList 不为空 则插入数据库
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(okList)) {
			// 批量导入okList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", okList);
			map1.put("projectSchema", projectSchema);
			dao.insert("ACSP01.importSpotFromExcel", map1);
		}

		/**
		 * 3.返回结果（如果存在错误数据，则一并返回）
		 */
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
	 * 从 excel 导入电话数据（ExcelController调用此方法）
	 * 作者：jzm
	 * 入参：EiInfo
	 * 出参：EiInfo（导入结果，如果有失败的记录，则会将失败的记录和失败原因返回）
	 * 处理：
	 * 1.校验：非空校验，长度校验，电话是否重复，所属地点存在校验
	 * 2.将正确的数据写入数据库
	 * 3.返回结果（如果存在错误数据，则一并返回）
	 */
	public EiInfo importTeleFromExcel(EiInfo eiInfo) {
		List<Map<String, String>> inList = (List<Map<String, String>>) eiInfo.get("list");
		List<Map<String, String>> okList = new ArrayList<>();
		List<Map<String, String>> errorList = new ArrayList<>();

		// 存放 excel中的telNum
		HashSet<String> spotNumtelSet = new HashSet<>();

		/**
		 * 1.校验：非空校验，长度校验，电话是否重复，所属地点存在校验
		 */
		for (Map<String, String> map : inList) {
			StringBuilder error = new StringBuilder();
			if (StringUtils.isEmpty(map.get("deptId"))) {
				error.append("科室编码为空  ");
			}
			if (StringUtils.isEmpty(map.get("spotId"))) {
				error.append("地点编码为空  ");
			}
			if (StringUtils.isEmpty(map.get("telNum"))) {
				error.append("电话号码为空  ");
			}

			// 判断字段长度是否超出
			if (map.get("deptId").length() > 50) {
				error.append("科室编码长度超过50  ");
			}
			if (map.get("spotId").length() > 50) {
				error.append("地点编码长度超过50 ");
			}
			if (map.get("telNum").length() > 50) {
				error.append("电话号码长度超过50  ");
			}
			if (map.get("remark").length() > 500) {
				error.append("备注长度超过500  ");
			}

			// 验证所属地点是否存在（如果不存在则错）
			HashMap<String, String> map1 = new HashMap<>();
			map1.put("projectSchema", projectSchema);
			map1.put("spotNum", map.get("spotId"));
			int count1 = super.count("ACSP01.countSpotNum", map1);
			if (count1 == 0) {
				error.append("所属地点不存在  ");
			}

			// 验证所属科室是否存在（如果不存在则错）
			HashMap<String, String> map2 = new HashMap<>();
			map2.put("projectSchema", projectSchema);
			map2.put("deptNum", map.get("deptId"));
			int count2 = super.count("ACDE01.countDeptNum", map2);
			if (count2 == 0) {
				error.append("所属科室不存在  ");
			}

			// 验证电话号码是否重复
			HashMap<String, String> map3 = new HashMap<>();
			map3.put("projectSchema", projectSchema);
			map3.put("telNum", map.get("telNum"));
			map3.put("spotNum", map.get("spotId"));
			int count3 = super.count("ACSP01.isTeleInSpotExist", map3);
			if (count3 != 0 || spotNumtelSet.contains(map.get("spotId") + map.get("telNum"))) { // telNum duplicate！
				error.append("电话号码重复  ");
			}
			// 判断是否存在错误
			if (error.length() == 0) {
				// 无错误，则生成必要信息 然后存入okList
				String id = UUID.randomUUID().toString();
				String recCreateTime = sdf.format(new Date());
				String recCreater = com.baosight.iplat4j.core.web.threadlocal.UserSession.getLoginName();
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

			spotNumtelSet.add(map.get("spotId") + map.get("telNum"));

		}

		/**
		 *  2.将正确的数据写入数据库
		 */
		// 如果 okList 不为空 则插入数据库
		if (org.apache.commons.collections.CollectionUtils.isNotEmpty(okList)) {
			// 批量导入okList
			HashMap<String, Object> map1 = new HashMap<>();
			map1.put("list", okList);
			map1.put("projectSchema", projectSchema);
			dao.insert("ACSP01.importTeleFromExcel", map1);
		}

		/**
		 * 3.返回结果（如果存在错误数据，则一并返回）
		 */
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
	 * 查询所有地点信息（地点名称，地点编码。二维码导出使用）
	 * 入参：EiInfo(空)
	 * 出参：EiInfo(所有地点信息)
	 * 处理：
	 * 1.获取当前登陆人的院区，科室信息存入map中
	 * 2.从入参中读取"curDeptId","spotName","building","floor","room"存入map
	 * 3.调用query()方法从数据库中查询出满足入参条件的结果
	 * 4.将结果封装在EiInfo的result域中返回
	 * @param info
	 * @return
	 */
	public EiInfo queryAllSpot(EiInfo info){
		/**
		 *  1.获取当前登陆人的院区，科室信息存入map中
		 */
		String datagroupCode = DatagroupUtil.getDatagroupCode();
		String workNo = UserSession.getUser().getUsername();
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("datagroupCode", datagroupCode);
		eiInfo.set("workNo", workNo);
		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserDepts");
		EiInfo xinfo = XLocalManager.call(eiInfo);
		ArrayList<HashMap<String, Object>> xlist = (ArrayList<HashMap<String, Object>>) xinfo.get("result");

		/**
		 * 2.从入参中读取"curDeptId","spotName","building","floor","room"存入map
		 */
		String[] param = { "curDeptId", "deptName", "spotName", "building", "floor", "room", "status", "telNum" };
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = PrUtils.changeToMap(info, "resultSpot", plist);

		/**
		 * 3.调用query()方法从数据库中查询出满足入参条件的结果
		 */
		// 如果status是null 则为默认初始化查询 需要置status为 1（启用）
		map.putIfAbsent("status", "1");
		map.put("offset", 0);
		map.put("limit", -999999);
		map.put("list", xlist);
		map.put("projectSchema", projectSchema);
		List<Map<String, Object>> list = dao.query("ACSP01.querySpot", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("ACSP01.countSpot", map);

		/**
		 * 4.将结果封装在EiInfo的result域中返回
		 */
		if (!CollectionUtils.isEmpty(list)) {
			return PrUtils.BuildOutEiInfo(info, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			return info;
		}
	}


	/**
	 * 按编码集合查询属于该集合的地点信息（二维码部分导出使用）
	 * 入参：EiInfo(地点 id list)
	 * 出参：EiInfo（符合入参要求的地点信息）
	 * 处理：
	 * 1.从入参中读取地点 编号 list
	 * 2.从数据库中查询满足要求的地点信息
	 * 3.返回地点信息集合
	 * @param inInfo
	 * @return
	 */
	public EiInfo querySpotInfoByNum(EiInfo inInfo) {
		/**
		 * 1.从入参中读取地点 编号 list
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
		 *  2.从数据库中查询满足要求的地点信息
		 */
		List<Map<String, Object>> deptList = dao.query("ACSP01.querySpotInfoByNum", map,
				Integer.parseInt(map.get("offset").toString()), Integer.parseInt(map.get("limit").toString()));

		int count = super.count("ACSP01.querySpotInfoByNumCount", map);


		/**
		 * 3.返回地点信息集合
		 */
		if (!CollectionUtils.isEmpty(deptList)) {
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(deptList.get(0)), deptList, count);
		} else {
			return inInfo;
		}
	}
}
