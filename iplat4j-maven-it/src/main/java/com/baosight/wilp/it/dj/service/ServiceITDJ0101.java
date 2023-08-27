package com.baosight.wilp.it.dj.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.it.utils.CreateNum;
import com.baosight.wilp.it.utils.IndependentTaskEnum;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 第一段
 * 独立任务登记新增、编辑、作废接口.
 * 1.独立任务登记新增、编辑、作废初始化方法.
 * 2.独立任务获取人员信息方法.
 * 3.独立任务获取电话信息方法.
 * 4.独立任务获取科室信息方法.
 * 5.独立任务获取楼信息方法.
 * 6.独立任务获取层信息方法.
 * 7.独立任务获取地点信息方法.
 * 8.保存独立任务登记信息.
 * 第二段
 * 独立任务登记新增、编辑、作废接口.
 * 独立任务公共接口对接(获取人员信息、获取电话信息、获取科室信息、获取楼信息、获取层信息、获取地点信息).
 * 独立任务保存登记接口.
 * 第三段
 * @author zhaowei
 * @date 2022年07月28日 17:02
 * @version V1.0
 */
public class ServiceITDJ0101 extends ServiceBase {
	/**
	 * 第一段
	 * 独立任务登记新增、编辑、作废初始化方法.
	 * 第二段
	 * 1.通过任务单号，获取对应独立任务信息.
	 * 2.获取前端操作类型，根据操作类型进入不同的分支.
	 * 2.1.登记分支
	 * 2.2.编辑分支
	 * 2.3.提交分支
	 * 2.4.作废分支
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:06
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 1.通过任务单号，获取对应独立任务信息
		 */
		if("all".equals(CreateNum.getDataGroupCode(null))){
			if (CreateNum.getDataGroupCodeAll(null)!=null){
				info.set("dataGroupCode", CreateNum.getDataGroupCodeAll(null));
				info.set("dataGroupCodeAll", StringUtils.join(CreateNum.getDataGroupCodeAll(null).toArray(),","));
			}
		}else {
			List<String> dataGroupCode = new ArrayList<>();
			dataGroupCode.add(CreateNum.getDataGroupCode(null));
			info.set("dataGroupCode", dataGroupCode);
		}
		List<Map<String,Object>> queryItTaskInfoList = dao.query("ITDJ01.queryItTaskInfo", info.getAttr());
		/*
		 * 2.获取前端操作类型
		 * 根据操作类型进入不同的分支
		 */
		String type = info.getString("type");
		switch (type){
			/*
			 * 2.1.登记分支
			 * 返回不紧急
			 */
			case "enter":
				info.set("impFlag","N");
				break;
			/*
			 * 2.2.编辑分支
			 * 如果查询结果不为空
			 * 返回独立任务查询结果
			 * 返回编辑状态
			 */
			case "edit":
				if (CollectionUtils.isNotEmpty(queryItTaskInfoList)){
					info.setAttr(queryItTaskInfoList.get(0));
					info.set("type","edit");
				}
				break;
			/*
			 * 2.3.提交分支
			 * 如果查询结果不为空
			 * 返回独立任务查询结果
			 * 返回提交状态
			 */
			case "submit":
				if (CollectionUtils.isNotEmpty(queryItTaskInfoList)){
					info.setAttr(queryItTaskInfoList.get(0));
					info.set("type","submit");
				}
				break;
			/*
			 * 2.4.作废分支
			 * 如果查询结果不为空
			 * 返回独立任务查询结果
			 * 返回作废状态
			 */
			case "cancellation":
				if (CollectionUtils.isNotEmpty(queryItTaskInfoList)){
					info.setAttr(queryItTaskInfoList.get(0));
					info.set("type","cancellation");
				}
				break;
		}
		return info;
	}

	/**
	 * 第一段
	 * 独立任务获取人员信息方法.
	 * 第二段
	 * 1.获取入参封装成Map集合，同时手动传入账套、员工工号、员工姓名.
	 * 2.调用公共类中的人员信息方法.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:05
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo queryPersonnelList(EiInfo inInfo) {
		/*
		 * 1.获取入参封装成Map集合，同时手动传入账套、员工工号、员工姓名
		 */
		Map<String, Object> map = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList(new String[]{"dataGroupCode","reqStaffName","reqStaffId"}));
		/*
		 * 2.调用公共类中的人员信息方法
		 * 并构建Block进行返回
		 * 判断账套是否为全部
		 */
		List<Map<String, Object>> list = new ArrayList<>();
		if ("all".equals(CreateNum.getDataGroupCode(null))){
			if (CreateNum.getDataGroupCodeAll(null)!=null){
				list = CreateNum.queryPersonnelListByDataGroupCodeAll(map);
			}else {
				list = CreateNum.queryPersonnelListByDataGroupCode(map);
			}
		}else {
			map.put("dataGroupCode", CreateNum.getDataGroupCode(null));
			map.put("datagroupCode", CreateNum.getDataGroupCode(null));
			list = CreateNum.queryPersonnelListByDataGroupCode(map);
		}
		return CommonUtils.BuildOutEiInfo("person", list);
	}

	/**
	 * 第一段
	 * 独立任务获取电话信息方法.
	 * 第二段
	 * 1.获取入参封装成Map集合，同时手动传入账套、电话号码.
	 * 2.如果电话不为空，标记位改为false.
	 * 2.1.请求电话参数为空，则电话参数设置为1.
	 * 2.2.请求电话参数不为空，则电话参数设置为请求电话参数.
	 * 3.调用微服务S_AC_FW_10通过电话获取科室和地点信息.
	 * 4.telNum不为空时，过滤科室和地点信息，返回指定电话对应的科室和地点信息.
	 * 5.将获取到的电话信息保存到Block块中.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:05
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo selectOfficePhone(EiInfo inInfo) {
		/*
		 * 1.获取入参封装成Map集合，同时手动传入账套、电话号码
		 */
		Map<String, Object> pMap = new HashMap<>(16);
		pMap.put("reqTelNum", inInfo.getString("reqTelNum"));
		pMap.put("telNum", inInfo.getString("telNum"));
		/*
		 * 2.如果电话不为空，标记位改为false
		 * 如果电话参数全为空
		 * 2.1.请求电话参数为空，则电话参数设置为1
		 * 2.2.请求电话参数不为空，则电话参数设置为请求电话参数
		 */
		boolean tempFlag = true;
		if(pMap.get("telNum") !=null && StringUtils.isNotBlank(pMap.get("telNum").toString())){
			tempFlag = false;
		} else {
			if(pMap.get("reqTelNum") == null || StringUtils.isBlank(pMap.get("reqTelNum").toString())){
				pMap.put("telNum", "1");
			} else {
				pMap.put("telNum", pMap.get("reqTelNum"));
			}
		}
		/*
		 * 3.调用微服务S_AC_FW_10通过电话获取科室和地点信息
		 */
		List<Map<String, Object>> phoneList = new ArrayList<>();
		if ("all".equals(CreateNum.getDataGroupCode(null))){
			if (CreateNum.getDataGroupCodeAll(null)!=null){
				phoneList = CreateNum.getSpotAndDeptByPhoneByDataGroupCodeAll(pMap);
			}else {
				phoneList = CreateNum.getSpotAndDeptByPhoneByDataGroupCode(pMap);
			}
		}else {
			pMap.put("dataGroupCode",CreateNum.getDataGroupCode(null));
			pMap.put("datagroupCode",CreateNum.getDataGroupCode(null));
			phoneList = CreateNum.getSpotAndDeptByPhoneByDataGroupCode(pMap);
		}
		/*
		 * 4.telNum不为空时，过滤科室和地点信息，返回指定电话对应的科室和地点信息
		 */
		if(!tempFlag){
			phoneList = phoneList.stream().filter(map -> pMap.get("telNum").equals(map.get("telNum")))
					.collect(Collectors.toList());
		}
		/*
		 * 5.将获取到的电话信息保存到Block块中
		 */
		return CommonUtils.BuildOutEiInfo("phone",phoneList);
	}

	/**
	 * 第一段
	 * 独立任务获取科室信息方法.
	 * 第二段
	 * 1.获取入参封装成Map集合，同时手动传入账套、需求科室、服务科室.
	 * 2.调用微服务接口S_AC_FW_05查询科室信息.
	 * 3.将获取到的科室信息保存到Block块中.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:05
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo queryDeptList (EiInfo inInfo) {
		/*
		 * 1.获取入参封装成Map集合，同时手动传入账套、需求科室、服务科室
		 */
		Map<String, Object> paramMap = CommonUtils.buildParamterNoPage(inInfo, Arrays.asList("reqDeptName", "serveDeptName", "dataGroupCode"));
		if (paramMap.get("reqDeptName") != null){
			paramMap.put("deptName", paramMap.get("reqDeptName"));
		}
		if (paramMap.get("serveDeptName") != null){
			paramMap.put("deptName", paramMap.get("serveDeptName"));
		}
		/*
		 * 2.调用微服务接口S_AC_FW_05查询科室信息
		 */
		List<Map<String, Object>> deptList = new ArrayList<>();
		if ("all".equals(CreateNum.getDataGroupCode(null))){
			if (CreateNum.getDataGroupCodeAll(null)!=null){
				deptList = CreateNum.getDeptAllNoPageByDataGroupCodeAll(paramMap);
			}else {
				deptList = CreateNum.getDeptAllNoPageByDataGroupCode(paramMap);
			}
		}else {
			paramMap.put("dataGroupCode",CreateNum.getDataGroupCode(null));
			paramMap.put("datagroupCode",CreateNum.getDataGroupCode(null));
			deptList = CreateNum.getDeptAllNoPageByDataGroupCode(paramMap);
		}
		/*
		 * 3.将获取到的科室信息保存到Block块中
		 */
		return CommonUtils.BuildOutEiInfo("dept", deptList);
	}

	/**
	 * 第一段
	 * 独立任务获取楼信息方法.
	 * 第二段
	 * 1.获取入参封装成Map集合，同时手动传入账套、楼.
	 * 2.调用微服务接口S_AC_FW_13查询楼信息.
	 * 3.将获取到的楼信息保存到Block块中.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:05
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo selectSpotBuildingName(EiInfo inInfo) {
		/*
		 * 1.获取入参封装成Map集合，同时手动传入账套、楼
		 */
		Map<String, Object> map = new HashMap<>(16);
		map.put("dataGroupCode",CreateNum.getDataGroupCode(null));
		map.put("datagroupCode",CreateNum.getDataGroupCode(null));
		map.put("building", inInfo.getString("building"));
		String building = map.get("building") == null ? "" : map.get("building").toString();
		/*
		 * 2.调用微服务接口S_AC_FW_13查询楼信息
		 */
		List<Map<String, Object>> list = BaseDockingUtils.getBuilding(building);
		/*
		 * 3.将获取到的楼信息保存到Block块中
		 */
		return CommonUtils.BuildOutEiInfo("building", list);
	}

	/**
	 * 第一段
	 * 独立任务获取层信息方法.
	 * 第二段
	 * 1.获取入参封装成Map集合，同时手动传入账套、楼、层.
	 * 2.调用微服务接口S_AC_FW_14查询层信息.
	 * 3.将获取到的层信息保存到Block块中.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:05
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo selectSpotFloorName(EiInfo inInfo) {
		/*
		 * 1.获取入参封装成Map集合，同时手动传入账套、楼、层
		 */
		Map<String, Object> map = new HashMap<>(16);
		map.put("dataGroupCode",CreateNum.getDataGroupCode(null));
		map.put("datagroupCode",CreateNum.getDataGroupCode(null));
		if(inInfo.get("building") == null) {
			map.put("building", inInfo.getMsg());
		}else {
			map.put("building", inInfo.getString("building"));
		}
		map.put("floor", inInfo.getString("floor"));
		String building = map.get("building") == null ? "" : map.get("building").toString();
		String floor = map.get("floor") == null ? "" : map.get("floor").toString();
		/*
		 * 2.调用微服务接口S_AC_FW_14查询层信息
		 */
		List<Map<String, Object>> list = BaseDockingUtils.getFloor(building, floor);
		/*
		 * 3.将获取到的层信息保存到Block块中
		 */
		return CommonUtils.BuildOutEiInfo("floor", list);
	}

	/**
	 * 第一段
	 * 独立任务获取地点信息方法.
	 * 第二段
	 * 1.获取入参封装成Map集合，同时手动传入账套、楼、层、地点.
	 * 2.调用微服务接口S_AC_FW_15查询房间信息.
	 * 3.将获取到的地点信息保存到Block块中.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:05
	 * @param inInfo 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo selectSpotRoomName(EiInfo inInfo) {
		/*
		 * 1.获取入参封装成Map集合，同时手动传入账套、楼、层、地点
		 */
		Map<String, Object> map = new HashMap<>(16);
		map.put("dataGroupCode",CreateNum.getDataGroupCode(null));
		map.put("datagroupCode",CreateNum.getDataGroupCode(null));
		if(inInfo.get("building") == null || inInfo.get("floor") == null) {
			map.put("floor", inInfo.getMsg());
			map.put("building", inInfo.getName());
		}else {
			map.put("building", inInfo.getString("building"));
			map.put("floor", inInfo.getString("floor"));
		}
		map.put("spotName", inInfo.getString("reqSpotName"));
		String building = map.get("building") == null ? "" : map.get("building").toString();
		String floor = map.get("floor") == null ? "" : map.get("floor").toString();
		String room = map.get("spotName") == null ? "" : map.get("spotName").toString();
		/*
		 * 2.调用微服务接口S_AC_FW_15查询房间信息
		 * 并进行参数进行转换
		 */
		List<Map<String, Object>> list = BaseDockingUtils.getRoom(building, floor, room);
		for (Map<String, Object> pMap : list) {
			pMap.put("reqSpotName", pMap.get("room"));
			pMap.put("spotNum", pMap.get("spot_num"));
			pMap.put("spotName", pMap.get("spot_name"));
		}
		/*
		 * 3.将获取到的地点信息保存到Block块中
		 */
		return CommonUtils.BuildOutEiInfo("room", list);
	}

	/**
	 * 第一段
	 * 保存独立任务登记信息.
	 * 第二段
	 * 1.获取用户组信息.
	 * 2.获取前端操作类型.
	 * 2.1.保存分支.
	 * 2.2.编辑分支.
	 * 2.3.提交分支.
	 * 2.4.作废分支.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 17:05
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo saveIndependentTask(EiInfo info){
		/*
		 * 1.获取用户组信息
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		/*
		 * 2.获取前端操作类型
		 * 根据操作类型进入不同的分支
		 */
		String type = info.getString("type");
		switch (type){
			/*
			 * 2.1.保存分支
			 * 封装参数
			 * 调用数据库插入操作
			 */
			case "enter":
				if("all".equals(CreateNum.getDataGroupCode(null))){
					if (CreateNum.getDataGroupCodeAll(null)!=null){
						info.set("dataGroupCode", StringUtils.join(CreateNum.getDataGroupCodeAll(null).toArray(),","));
					}
				}else {
					info.set("dataGroupCode", CreateNum.getDataGroupCode(null));
				}
				info.set("itTaskId", UUID.randomUUID().toString().replace("-",""));
				info.set("taskNo", CreateNum.CreateNumByType(IndependentTaskEnum.DJ.getAbbreviation()));
				info.set("status", "00");
				info.set("archiveFlag", 1);
				info.set("recCreator",staffByUserId.get("name"));
				info.set("recCreateTime", DateUtils.curDateTimeStr19());
				dao.insert("ITDJ01.saveIndependentTask",info.getAttr());
				break;
			/*
			 * 2.2.编辑分支
			 * 封装参数
			 * 调用数据库更新操作
			 */
			case "edit":
				info.set("recRevisor",staffByUserId.get("name"));
				info.set("recReviseTime", DateUtils.curDateTimeStr19());
				dao.update("ITDJ01.updateIndependentTask",info.getAttr());
				break;
			/*
			 * 2.3.提交分支
			 * 封装参数
			 * 调用数据库更新操作
			 */
			case "submit":
				info.set("status", "01");
				dao.update("ITDJ01.submit", info.getAttr());
				break;
			/*
			 * 2.4.作废分支
			 * 封装参数
			 * 调用数据库更新操作
			 */
			case "cancellation":
				info.set("status","10");
				info.set("invalidManNo",staffByUserId.get("workNo"));
				info.set("invalidManName",staffByUserId.get("name"));
				info.set("invalidTime", DateUtils.curDateTimeStr19());
				dao.update("ITDJ01.cancellation",info.getAttr());
				break;
		}
		return info;
	}
}