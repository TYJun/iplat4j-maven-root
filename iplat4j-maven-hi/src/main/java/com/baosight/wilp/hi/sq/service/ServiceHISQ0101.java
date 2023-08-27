package com.baosight.wilp.hi.sq.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.hi.common.domain.HiApply;
import com.baosight.wilp.hi.common.domain.HiFile;
import com.baosight.wilp.hi.common.util.*;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该页面为医院标识申请管理
 * <p>1.初始化查询 initLoad
 * <p>2.保存弹窗信息 saveApplicationInformation
 * <p>3.接口改造所属科室 queryIconCostNum
 * <p>4.接口改造合同管理员 queryAdmin
 * <p>5.查询项目附件信息 queryFile
 * <p>6.获取申请单号 createApplyNo
 * <p>7.保存附件 saveFile
 * <p>8.TabGrid查询方法 queryTabGrid
 * <p>9.查询科室地点querySpot
 * <p>10.查询单号 querySerialNo
 * <p>11.更新单号 updateSerialNo
 *
 * @Title: ServiceHISQ0101.java
 * @ClassName: ServiceHISQ0101
 * @Package：com.baosight.wilp.hi.sq.service
 * @author: liangyongfei
 * @date: 2022年8月21日 下午2:23:38
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceHISQ0101 extends ServiceBase {
	/**
	 * @param inInfo id 主键
	 *               type 操作类型
	 * @return EiInfo
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * <p>1.获取参数</>
	 * <p>2.如果参数存在</>
	 * <p>3.调用查询方法</>
	 * <p>4.数据返回</>
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		// 1.获取参数(id,type)
		String id = inInfo.getAttr().get("id").toString();
		String type = inInfo.getAttr().get("type").toString();
		//2、判断id是否为空
		if (StringUtils.isNotBlank(id)) {
			//2.1、不为空时，判断是否为编辑或查看
			if ("edit".equals(type) || "see".equals(type)) {

				//是。获取id
				Map<String, String> pMap = new HashMap<>(4);
				pMap.put("id", inInfo.getString("id"));
				//通过id，查询医院标识申请基础信息(标识名称、标识中文内容、标识英文内容、标识分类、安装地点、数量、安装地点说明、安装日期、申请科室、管理员、申请理由、备注)
				List<HiApply> identifiesInformation = dao.query("HISQ01.query", pMap);
				//通过id，查询医院标识申请附件
				Map<String, String> pMap1 = new HashMap<>(4);
				pMap1.put("id", inInfo.getString("id"));
				List<HiFile> fileDate = dao.query("HIBZ01.queryId", pMap1);
				HiApply hiApply = identifiesInformation.get(0);
				inInfo.addBlock(String.valueOf(hiApply));
				EiBlock block = new EiBlock("resultD");
				block.setRows(fileDate);
				inInfo.setBlock(block);
			}
		}
		//1.获取当前登录用户信息
		Map<String, Object> userInfo = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		if(userInfo !=null){
			inInfo.set("inqu_status-0-applyDeptName", userInfo.get("deptNum"));
			inInfo.set("inqu_status-0-applyDeptName_textField", userInfo.get("deptName"));
		}
		  //返回
		return  inInfo;

	}

	/**
	 * @param inInfo id 主键
	 *               iconName 标识名称
	 *               iconZhContent 标识中文内容
	 *               iconEnContent 标识英文内容
	 *               classifyName 标识分类
	 *               spotName 安装地点
	 *               iconAmount 数量
	 *               spotDesc 安装地点说明
	 *               installDate 安装日期
	 *               applyReason 申请理由
	 *               managerName 管理员
	 *               applyDeptName 申请科室
	 *               remark 备注
	 *
	 * @return EiInfo
	 * @Title: saveApplicationInformation
	 * @Description: 保存弹窗信息
	 * <p>1.获取表单参数
	 * <p>2.获取tab/grid参数
	 * <p>3.保存项目
	 * <p>4.获取当前登录用户信息
	 * <p>5.如果参数为空
	 * <p>6.保存项目
	 * <p>7.更新项目
	 * <p>8.保存附件
	 */
	public EiInfo saveApplicationInformation(EiInfo inInfo){
		// 1.获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		// 2.获取tab/grid参数
		Object fileObj = inInfo.getAttr().get("file");
		List<Map<String, Object>> deleteFile = (List<Map<String, Object>>) inInfo.getAttr().get("deleteFile");
		/*
		* 1、获取参数
		* iconName：标识名称
		* iconZhContent：标识中文内容
		* iconEnContent：标识英文内容
		* classifyId：标识分类
		* fixedPlace：安装地点
		* spotDesc：安装地点说明
		* applyReason：申请理由
		* */
		String iconName = (String)param.get("iconName");
		String iconZhContent = (String) param.get("iconZhContent");
		String iconEnContent = (String) param.get("iconEnContent");
		String  classifyId = (String) param.get("classifyId");
		String 	fixedPlace = (String) param.get("spotName");
		String 	fixedPlace1 = (String) inInfo.getAttr().get("spotName");
		String 	spotDesc = (String) param.get("spotDesc");
		String 	applyReason = (String) param.get("applyReason");
		String errorMsy = "";
		/*
		* 1、判断如果标识名称为空时，提示标识名称不能为空
		* 2、判断如果标识中文内容为空时，提示标识中文内容不能为空
		* 3、判断如果标识英文内容为空时，提示标识英文内容不能为空
		* 4、判断如果标识分类内容为空时，提示标识分类不能为空
		* 5、判断如果安装地点为空时，提示安装地点不能为空
		* 6、判断如果安装地点说明为空时，提示安装地点说明不能为空
		* 7、判断如果申请理由为空时，提示申请理由不能为空
		* */
		if(StringUtils.isBlank(iconName)){
			errorMsy = errorMsy + "标识名称不能为空\r\n";
		}
//		if(StringUtils.isBlank(iconZhContent)){
//			errorMsy = errorMsy + "标识中文内容不能为空\r\n";
//		}
//		if(StringUtils.isBlank(iconEnContent)){
//			errorMsy = errorMsy + "标识英文内容不能为空\r\n";
//		}
		if(StringUtils.isBlank(classifyId)){
			errorMsy = errorMsy + "标识分类不能为空\r\n";
		}
		if(StringUtils.isBlank(fixedPlace)&& StringUtils.isBlank(fixedPlace1) ){
			errorMsy = errorMsy + "安装地方不能为空\r\n";
		}
		if(StringUtils.isBlank(spotDesc)){
			errorMsy = errorMsy + "安装地点说明不能为空\r\n";
		}
		if(StringUtils.isBlank(applyReason)){
			errorMsy = errorMsy + "申请理由不能为空\r\n";
		}
		if(errorMsy.length()>0){
			inInfo.setMsg(errorMsy);
			return inInfo;
		}
		// 3.保存项目
		HiApply apply = new HiApply();
		apply.fromMap(param);
		apply.setManagerName((String) param.get("managerName_textField"));
		apply.setApplyDeptNum((String) param.get("applyDeptName"));
		apply.setApplyDeptName((String) param.get("applyDeptName_textField"));
		apply.setClassifyName((String) param.get("classifyId_textField"));
		//4.获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 5.如果参数(id)为空
		if (StringUtils.isBlank(apply.getId())) {
			apply.setId(UUID.randomUUID().toString());
			apply.setApplyNo(HIUtils.generationSerialNo("hospital_hi_sq", "HISQ", "1"));
			apply.setRecCreator((String) staffByUserId.get("name"));
			apply.setRecCreateTime(DateUtils.curDateTimeStr19());
			apply.setBuilding((String) inInfo.getAttr().get("building"));
			apply.setFloor((String) inInfo.getAttr().get("floor"));
			apply.setSpotName((String) inInfo.getAttr().get("spotName"));
			apply.setSpotCode((String) inInfo.getAttr().get("spotNum"));
			apply.setStatus("01");
			apply.setStatusName("新增");
			// 6.保存项目
			dao.insert("HISQ01.insert", apply);
		} else {
			apply.setApplyDeptNum((String)staffByUserId.get("deptNum"));
			apply.setRecRevisor((String) staffByUserId.get("name"));
			apply.setRecReviseTime(DateUtils.curDateTimeStr19());
			apply.setBuilding((String) param.get("building"));
			apply.setFloor((String) param.get("floor"));
			apply.setSpotName((String) param.get("spotName"));
			apply.setSpotCode((String) param.get("spotCode"));
//			apply.setStatus("01");
			apply.setStatusName("新增");
			// 7.更新项目
			dao.update("HISQ01.update", apply);
		}
		//8.保存附件
		saveFile.saveFile(fileObj, apply.getId(), deleteFile, apply.getStatus());

		return inInfo;

	}


	/**
	 * @param info deptNum 科室编码
	 *             deptName 科室名称
	 * @return EiInfo
	 * contDeptNum 科室编码
	 * contDeptName 科室名称
	 * @Title: queryIconContCostNum
	 * @Description: 接口改造所属科室
	 * <p>1.调用分页接口构建map
	 * <p>2.调用远程服务获取改造科室接口
	 * <p>3.如果存在科室信息
	 * <p>4.科室信息存储在list
	 * <p>5.循环
	 * <p>6.如果list为空
	 * <p>7.返回封装的方法：构建返回结果EiInfo
	 * 调用档案中心
	 * 查询科室
	 */
	public EiInfo queryIconCostNum(EiInfo info) {
		// 1.调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "iconDept");
		map.put("deptNum", map.get("iconDeptNum"));
		map.put("deptName", map.get("iconDeptName"));
		// 实例化List
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化总数为0
		int count = 0;
		// 2.调用远程服务获取改造科室接口
		EiInfo outInfo = BaseDockingUtils.getDeptAllPage(map, "iconDept");
		// 3.如果存在科室信息
		if (outInfo.getBlock("iconDept") != null) {
			// 4.科室信息存储在list
			list = outInfo.getBlock("iconDept").getRows();
			// 5.循环
			list.forEach(map2 -> {
				map2.put("iconDeptNum", map2.get("deptNum"));
				map2.put("iconDeptName", map2.get("deptName"));
			});
			// 6.如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 科室信息总数存在count
			count = (int) outInfo.getBlock("iconDept").getAttr().get("count");
			// 7.返回封装的方法：构建返回结果EiInfo
			return CommonUtils.BuildOutEiInfo(info, "iconDept", PMUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}

	/**
	 * @param info userName 用户名
	 * @return EiInfo
	 * work 工号
	 * name 用户名
	 * @Title: queryAdmin
	 * @Description: 接口改造合同管理员
	 * <p>1.调用分页接口构建map
	 * <p>2.获取blockId
	 * <p>3.如果blockId相等
	 * <p>4.获取block中的数据的集合
	 * <p>5.获取集合中的数据
	 * <p>6.调用改造人员接口并返回
	 * <p>7.如果存在人员信息
	 * <p>8.获取人员信息
	 * <p>9.如果list为空
	 * <p>10.返回封装的方法：构建返回结果EiInfo
	 * <p>11.调用改造人员接口并返回
	 * <p>12.调用改造人员接口并返回
	 * <p>13.如果存在人员信息
	 * <p>14.获取人员信息
	 * <p>15.返回封装的方法：构建返回结果EiInfo
	 * 调用档案中心
	 * 查询人员
	 */
	public EiInfo queryAdmin(EiInfo info) {
		// 1.调用分页接口构建map
		Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "iconAdmin");
		map.put("userName", map.get("name"));
		// 2.获取blockId
		String blockId = info.getBlockIds();
		// 3.如果blockId相等
		if (blockId.equals("inqu_status,person")) {
			// 实例化info
			EiInfo outinfo = new EiInfo();
			// 实例化block
			EiBlock block = new EiBlock("person");
			// 4.获取block中的数据的集合
			List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
			// 5.获取集合中的数据
			String name = (String) listMap.get(0).get("name");
			// 设置userName
			map.put("userName", name);
			// 实例化List
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 初始化查询总数为0
			int count = 0;
			// 6.调用改造人员接口并返回
			EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
			// 7.如果存在人员信息
			if (outInfo.getBlock("person") != null) {
				// 8.获取人员信息
				list = outInfo.getBlock("person").getRows();
				// 9.如果list为空
				if (list.isEmpty()) {
					// 返回封装的方法：构建返回结果EiInfo
					return info;
				}
				// 获取人员信息总数
				count = (int) outInfo.getBlock("person").getAttr().get("count");
				// 10.返回封装的方法：构建返回结果EiInfo
				return CommonUtils.BuildOutEiInfo(info, "person", PMUtils.createBlockMeta(list.get(0)), list, count);
			} else {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
		}
		// 11.调用改造人员接口并返回
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化查询总数为0
		int count = 0;
		// 12.调用改造人员接口并返回
		EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "iconAdmin");
		// 13.如果存在人员信息
		if (outInfo.getBlock("iconAdmin") != null) {
			// 14.获取人员信息
			list = outInfo.getBlock("iconAdmin").getRows();
			// 如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 获取人员信息总数
			count = (int) outInfo.getBlock("iconAdmin").getAttr().get("count");
			// 15.返回封装的方法：构建返回结果EiInfo
			return CommonUtils.BuildOutEiInfo(info, "iconAdmin", PMUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}


	/**
	 * 楼补全
	 *
	 * <p>1.处理参数</br>
	 *  2.获取维修配置项：是否与基础对接</br>
	 *  3.判断是否与基础对接。是，调用微服务接口S_AC_FW_13，获取楼信息</br>
	 *  4.否，查询表中相关信息</p>
	 *
	 * @Title: selectSpotBuildingName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		building ： 楼
	 * @param:  @return
	 * @return: EiInfo
	 * 		building ： 楼
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo selectSpotBuildingName(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>(4);
//		List<HashMap<String,Object>> query = dao.query("querySpot");
//		query.size()
		map.put("dataGroupCode", inInfo.getString("dataGroupCode"));
		map.put("building", inInfo.getString("building"));
		//调用兼容封装方法，获取楼信息
		return MtObsoleteUtils.selectSpotBuildingName(map,inInfo,dao);

	}

	/**
	 * 层补全
	 *
	 * <p>1.处理参数</br>
	 *  2.获取维修配置项：是否与基础对接</br>
	 *  3.判断是否与基础对接。是，调用微服务接口S_AC_FW_14，获取层信息</br>
	 *  4.否，查询表中相关信息</p>
	 *
	 * @Title: selectSpotFloorName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		building ： 层
	 * 		floor ： 层
	 * @param:  @return
	 * @return: EiInfo
	 * 		floor ： 层
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo selectSpotFloorName(EiInfo inInfo) {
		//参数处理
		Map<String, Object> map = new HashMap<>(4);
		map.put("dataGroupCode", inInfo.getString("dataGroupCode"));

		if(inInfo.get("building") == null) {
			map.put("building", inInfo.getMsg());
		}else {
			map.put("building", inInfo.getString("building"));
		}
		map.put("floor", inInfo.getString("floor"));
		return MtObsoleteUtils.selectSpotFloorName(map, inInfo, dao);

	}

	/**
	 * 地点补全
	 *
	 * <p>1.处理参数</br>
	 *  2.获取维修配置项：是否与基础对接</br>
	 *  3.判断是否与基础对接。是，调用微服务接口S_AC_FW_15，获取地点信息</br>
	 *  4.否，查询表中相关信息</p>
	 *
	 * @Title: selectSpotRoomName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		building ： 层
	 * 		floor ： 层
	 * 		spotName ： 地点名称
	 * @param:  @return
	 * @return: EiInfo
	 * 		reqSpotName ： 房间
	 * 		spotNum ： 地点编码
	 * 		spotName ： 地点名称
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo selectSpotRoomName(EiInfo inInfo) {
		Map<String, Object> map = new HashMap<>(8 );
		map.put("dataGroupCode", inInfo.getString("dataGroupCode"));

		if(inInfo.get("building") == null || inInfo.get("floor") == null) {
			map.put("floor", inInfo.getMsg());
			map.put("building", inInfo.getName());
		}else {
			map.put("building", inInfo.getString("building"));
			map.put("floor", inInfo.getString("floor"));
		}
		map.put("spotName", inInfo.getString("spotName"));
		map.put("spotNum", inInfo.getString("spotNum"));
		return MtObsoleteUtils.selectSpotRoomName(map, inInfo, dao);

	}



	/**
	 * @param inInfo querySql 查询sql
	 *               countSql 统计sql
	 *               resultBlock blockId
	 *               blockMeta EiBlockMeta
	 * @Title: queryTabGrid
	 * @Description: TabGrid查询方法
	 * @return: EiInfo
	 */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock,
								EiBlockMeta blockMeta) {
		// 调用封装方法构造map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		// 查询数据
		List<?> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
		// 获取总数
		int count = dao.count(countSql, map);
		// 数据返回
		return CommonUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}








	/**
	 * @Title: querySpot
	 * @Description: 接口改造(地点)2021-08-04
	 * @param info
	 * spotName : 科室地点
	 * @return info
	 * spotName : 科室地点
	 */
	public EiInfo querySpot(EiInfo info) {
		// 构建参数map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "spot");
		// 调用远程接口返回集合
		List<Map<String, Object>> spotList = BaseDockingUtils.getSpotByDeptId("296");
		spotList.forEach(pMap -> pMap.put("spotId", pMap.get("id")));
		//将数据进行逻辑分页
		EiInfo spot = CommonUtils.BuildOutEiInfoWithLogicalPage(spotList, map, "spot");
		spot.setBlockInfoValue("dept", "showCount", "true");
		return spot;
	}


	/**
	 * 查询单号
	 * @Title: querySerialNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		type : 单号在model_number表中的标识符
	 * 	<p>1、根据type（module）,查询信息</>
	 * 	<p>2、如果信息为空的话，赋给“”</>
	 * 	<p>3、如果不为空的话，type_num(类型编码)赋给serialNo</>
	 * @param:  @return
	 * @return: EiInfo
	 * 		list<String> list : 单号集合
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo querySerialNo (EiInfo inInfo) {
		//1、根据type,查询type_num(类型编码)
		List<String> list = dao.query("HISQ01.querySerialNo", inInfo.getString("type"));
		//2、判断是否为空
		if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(list.get(0))) {
			//3、空的话，赋给“”
			inInfo.set("serialNo", "");
		} else {
			//3、不为空，type_num(类型编码)赋给serialNo
			inInfo.set("serialNo", list.get(0));
		}
		return inInfo;
	}


	/**
	 * 更新单号
	 * @Title: updateSerialNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		taskNo： 任务单号
	 * 		type ： 单号在model_number表中的标识符
	 * 		updateTime ： 更新时间
	 * 	<p>1、获取参数（type,serialNo）</>
	 * 	<p>2、赋值</>
	 * 	<p>3、类型为插入时，插入数据</>
	 * 	<p>4、类型为更新时，更新数据</>
	 * @param:  @return
	 * @return: EiInfo  ： 无
	 * @throws
	 */
	public EiInfo updateSerialNo(EiInfo inInfo) {
		//1、获取参数（type,serialNo）
		String type = HIUtils.toString(inInfo.get("type"));
		String serialNo = HIUtils.toString(inInfo.get("serialNo"));
		//2、赋值
		Map<String, String> map = new HashMap<>();
		map.put("serialNo", serialNo);
		map.put("type", type);
		map.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		//3、操作为插入时
		if("insert".equals(inInfo.getString("op"))){
			//3.1、将map(serialNo、type、updateTime)插入到cu_model_number表
			dao.insert("HISQ01.insertSerialNo", map);
		} else {
			//操作为更新时，将map(serialNo、type、updateTime)更新到cu_model_number表
			dao.update("HISQ01.updateSerialNo", map);
		}
		return inInfo;
	}


}
