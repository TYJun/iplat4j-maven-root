package com.baosight.wilp.pm.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.domain.*;
import com.baosight.wilp.pm.utils.PMUtils;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

/**
 * 工程项目：初始化查询、人员查询（接口改造）、供应商查询（接口改造）、工程项目数据回显、保存工程项目、获取合同号、保存执行人、保存知会人、保存项目附件信息、查询执行人、查询知会人、查询项目附件信息、TabGrid查询方法
 * <p>1.初始化查询 initLoad
 * <p>2.人员查询（接口改造） queryPerson
 * <p>3.供应商查询（接口改造） querySupplier
 * <p>4.工程项目数据回显 projectInit
 * <p>5.保存工程项目 saveProject
 * <p>6.获取合同号 createProjectNo
 * <p>7.保存执行人 saveStaff
 * <p>8.保存知会人 saveKnow
 * <p>9.保存项目附件信息 saveFile
 * <p>10.查询执行人 queryStaff
 * <p>11.查询知会人 queryKnow
 * <p>12.查询项目附件信息 queryFile
 * <p>13.TabGrid查询方法 queryTabGrid
 *
 * @Title: ServicePM0103.java
 * @ClassName: ServicePM0103
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月27日 上午9:53:23
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0103 extends ServiceBase {

	/**
	 * @param inInfo
	 * @return inInfo
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		// 调用初始化方法
		return inInfo;
	}

	/**
	 * 2021-08-26：人员查询（接口改造）
	 *
	 * @throws
	 * @Title: queryPerson
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: inInfo
	 * @return: EiInfo
	 */
	public EiInfo queryPerson(EiInfo info) {
		// 调用分页接口构建map
		Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "person");
		// 获取blockId
		String blockId = info.getBlockIds();
		// 如果blockId相等
		if (blockId.equals("inqu_status,person")) {
			// 实例化info
			EiInfo outinfo = new EiInfo();
			// 实例化block
			EiBlock block = new EiBlock("person");
			// 获取block中的数据的集合
			List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
			// 获取集合中的数据
			String name = (String) listMap.get(0).get("name");
			// 设置userName
			map.put("userName", name);
			// 实例化List
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 初始化查询总数为0
			int count = 0;
			// 调用改造人员接口并返回
			EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
			// 如果存在人员信息
			if (outInfo.getBlock("person") != null) {
				// 获取人员信息
				list = outInfo.getBlock("person").getRows();
				// 如果list为空
				if (list.isEmpty()) {
					// 返回封装的方法：构建返回结果EiInfo
					return info;
				}
				// 获取人员信息总数
				count = (int) outInfo.getBlock("person").getAttr().get("count");
				// 返回封装的方法：构建返回结果EiInfo
				return PMUtils.BuildOutEiInfo(info, "person", PMUtils.createBlockMeta(list.get(0)), list, count);
			} else {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
		}
		// 调用改造人员接口并返回
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化查询总数为0
		int count = 0;
		// 调用改造人员接口并返回
		EiInfo outInfo = BaseDockingUtils.getStaffAllPage(map, "person");
		// 如果存在人员信息
		if (outInfo.getBlock("person") != null) {
			// 获取人员信息
			list = outInfo.getBlock("person").getRows();
			// 如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 获取人员信息总数
			count = (int) outInfo.getBlock("person").getAttr().get("count");
			// 返回封装的方法：构建返回结果EiInfo
			return PMUtils.BuildOutEiInfo(info, "person", PMUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}

	/**
	 * 2021-08-26：供应商查询（接口改造）
	 *
	 * @throws
	 * @Title: querySupplier
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo querySupplier(EiInfo info) {
		// 调用分页接口构建map
		Map<String, Object> map = PMUtils.buildParamter(info, "inqu_status", "supplier");
		// 获取blockId
		String blockId = info.getBlockIds();
		// 如果blockId相等
		if (blockId.equals("inqu_status,supplier")) {
			// 实例化info
			EiInfo outinfo = new EiInfo();
			// 实例化block
			EiBlock block = new EiBlock("supplier");
			// 获取block中的数据的集合
			List<Map<String, Object>> listMap = info.getBlock("inqu_status").getRows();
			// 获取集合中的数据
			String name = (String) listMap.get(0).get("supplierName");
			// 设置supplierName
			map.put("supplierName", name);
			// 实例化list
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 初始化查询总数为0
			int count = 0;
			// 调用改造供应商接口并返回
			EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
			// 如果供应商信息
			if (outInfo.getBlock("supplier") != null) {
				// 获取供应商信息
				list = outInfo.getBlock("supplier").getRows();
				// 如果list为空
				if (list.isEmpty()) {
					// 返回封装的方法：构建返回结果EiInfo
					return info;
				}
				// 遍历供应商信息
				for (Map<String, Object> map2 : list) {
					// 将key：supplierCode改为key：supplierNum
					map2.put("supplierNum", map2.get("supplierCode"));
				}
				// 获取供应商信息总数
				count = (int) outInfo.getBlock("supplier").getAttr().get("count");
				// 返回封装的方法：构建返回结果EiInfo
				return PMUtils.BuildOutEiInfo(info, "supplier", PMUtils.createBlockMeta(list.get(0)), list, count);
			} else {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
		}
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 初始化查询总数为0
		int count = 0;
		// 调用改造供应商接口并返回
		EiInfo outInfo = BaseDockingUtils.getSupplierAllPage(map, "supplier");
		// 如果供应商信息
		if (outInfo.getBlock("supplier") != null) {
			// 获取供应商信息
			list = outInfo.getBlock("supplier").getRows();
			// 如果list为空
			if (list.isEmpty()) {
				// 返回封装的方法：构建返回结果EiInfo
				return info;
			}
			// 遍历供应商信息
			for (Map<String, Object> map2 : list) {
				// 将key：supplierCode改为key：supplierNum
				map2.put("supplierNum", map2.get("supplierCode"));
			}
			// 获取供应商信息总数
			count = (int) outInfo.getBlock("supplier").getAttr().get("count");
			// 返回封装的方法：构建返回结果EiInfo
			return PMUtils.BuildOutEiInfo(info, "supplier", PMUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			// 返回封装的方法：构建返回结果EiInfo
			return info;
		}
	}

	/**
	 * 工程项目数据回显
	 *
	 * @throws
	 * @Title: projectInit
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo projectInit(EiInfo inInfo) {
		// 获取参数
		String id = String.valueOf(inInfo.get("id"));
		String type = String.valueOf(inInfo.get("type"));
		// 构建Block
		EiBlock block = new EiBlock("inqu_status");
		// Block中添加eiMetadata
		block.addBlockMeta(new Tpm01().eiMetadata);
		// 获取参数不为空
		if (StringUtils.isNotBlank(id)) {
			// 实例化map
			Map<String, Object> param = new HashMap<>();
			// 给map赋值
			param.put("id", id);
			// 获取数据
			List<Tpm01> list = dao.query("PM01.query", param);
			// 数据返回
			block.addRows(list);
			// 特殊数据处理
			EiColumn column1 = new EiColumn("contorId_textField");
			EiColumn column2 = new EiColumn("projectObjectCons_textField");
			EiColumn column3 = new EiColumn("undertakeDeptNum_textField");
			EiColumn column4 = new EiColumn("supplierNum_textField");
			EiColumn column5 = new EiColumn("projectTypeNum_textField");
			block.addMeta(column1);
			block.addMeta(column2);
			block.addMeta(column3);
			block.addMeta(column4);
			block.addMeta(column5);
			block.setCell(0, "contorId_textField", list.get(0).getProjectPrincipal());
			block.setCell(0, "projectObjectCons_textField", list.get(0).getProjectObjectConsName());
			block.setCell(0, "undertakeDeptNum_textField", list.get(0).getUndertakeDeptName());
			block.setCell(0, "supplierNum_textField", list.get(0).getSupplierName());
			block.setCell(0, "projectTypeNum_textField", list.get(0).getProjectTypeName());
			block.setCell(0, "type", type);
		} else {
			// 特殊数据处理
			block.setCell(0, "projectObjectDeptNum", inInfo.get("deptNum"));
			block.setCell(0, "projectObjectDeptName", inInfo.get("deptName"));
			block.setCell(0, "projectStatus", "01");
			block.setCell(0, "type", type);
		}
		// 数据返回
		inInfo.addBlock(block);
		return inInfo;
	}

	/**
	 * 保存工程项目
	 *
	 * @throws
	 * @Title: saveProject
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo saveProject(EiInfo inInfo) {
		//获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String, Object> param = block.getRow(0);
		//获取tab/grid参数
		Object stageObj = inInfo.get("stageList");
		Object staffObj = inInfo.get("staffList");
		Object knowObj = inInfo.get("knowList");
		Object fileObj = inInfo.get("fileList");
		List<Map<String, Object>> deleteFile = (List<Map<String, Object>>) inInfo.get("deleteFile");
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//当前登录人
		String name = (String) staffByUserId.get("name");
		// 短信标记位，初始为否
		boolean smsFlag = false;
		// 实例化
		Tpm01 PM01 = new Tpm01();
		// 将数据保存到实体中
		PM01.fromMap(param);
		// 如果实体不存在
		if (StringUtils.isBlank(PM01.getId())) {
			// 给实体赋值
			PM01.setId(UUID.randomUUID().toString());
			PM01.setProjectNo(createProjectNo());
			PM01.setProjectTypeName((String) param.get("projectTypeNum_textField"));
			PM01.setContorName((String) param.get("contorId_textField"));
			PM01.setUndertakeDeptName((String) param.get("undertakeDeptNum_textField"));
			PM01.setProjectObjectConsName((String) param.get("projectObjectCons_textField"));
			PM01.setSupplierName((String) param.get("supplierNum_textField"));
			PM01.setRecCreator(name);
			PM01.setRecCreateTime(DateUtils.curDateTimeStr19());
			PM01.setDataGroupCode(PMUtils.getDataGroup());
			// 保存工程项目
			dao.insert("PM01.insert", PM01);
			smsFlag = true;
		} else {
			// 给实体赋值
			PM01.setRecRevisor(name);
			PM01.setRecReviseTime(DateUtils.curDateTimeStr19());
			PM01.setProjectTypeName((String) param.get("projectTypeNum_textField"));
			PM01.setContorName((String) param.get("contorId_textField"));
			PM01.setUndertakeDeptName((String) param.get("undertakeDeptNum_textField"));
			PM01.setProjectObjectConsName((String) param.get("projectObjectCons_textField"));
			PM01.setSupplierName((String) param.get("supplierNum_textField"));
			dao.insert("PM01.update", PM01);
		}
		//保存执行人员
		saveStage(stageObj, PM01.getId(), name);
		saveStaff(staffObj, PM01.getId(), name);
		saveKnow(knowObj, PM01.getId(), name);
		saveFile(fileObj, PM01.getId(), deleteFile);
		//发送短信
		List<Map<String, Object>> knowList = new ArrayList<>();
		List<String> workNoList = new ArrayList<>();
		if (knowObj != null) {
			// 入参类型转换
			knowList = (List<Map<String, Object>>) knowObj;
		}
		// 增强循环
		for (int i = 0; i < knowList.size(); i++) {
			workNoList.add((String) knowList.get(i).get("workNo"));
		}
		if (false) {
			EiInfo eiInfo = new EiInfo();
			//设置参数
			eiInfo.set("configType", "projectNew");
			eiInfo.set("projectId", PM01.getId());
			//设置服务名
			eiInfo.set(EiConstant.serviceName, "PMSms");
			//设置方法名
			eiInfo.set(EiConstant.methodName, "sendMessage");
			//服务调用
			EiInfo outInfo = XLocalManager.call(eiInfo);
			if (outInfo.getStatus() < 0) {
				throw new PlatException(outInfo.getMsg());
			}
		}
		return inInfo;
	}

	/**
	 * 获取合同号
	 *
	 * @return
	 * @Title: createProjectNo
	 * @return: String
	 */
	private String createProjectNo() {
		// 加锁
		synchronized (dao) {
			// 日期转换
			String date = DateUtils.curDateStr8();
			// 查询合同号
			List<String> list = dao.query("PM0103.queryProjectNo", date);
			// 如果参数为空
			if (list == null || list.size() == 0 || list.get(0) == null) {
				// 返回合同号
				return date + "0001";
			} else {
				// 获得最大合同号
				String maxNo = list.get(0);
				// 返回合同号
				return (Long.parseLong(maxNo) + 1L) + "";
			}
		}

	}

	/**
	 * 保存项目阶段
	 *
	 * @param stageObj
	 * @param id
	 * @param name
	 * @Title: saveStage
	 * @return: void
	 */
	private void saveStage(Object stageObj, String id, String name) {
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (stageObj != null) {
			// 进行类型转换
			list = (List<Map<String, Object>>) stageObj;
		}
		//删除旧的执行人信息
		dao.delete("PM0103.deleteStage", id);
		// 增强循环
		for (Map<String, Object> map : list) {
			// 实例化实体
			TpmStage stage = new TpmStage();
			// 将数据保存到实体中
			stage.fromMap(map);
			// 给实体赋值
			stage.setId(UUID.randomUUID().toString());
			stage.setProjectPk(id);
			stage.setPlanDate((String) map.get("planDate"));
			stage.setdataGroupCode(PMUtils.getDataGroup());
			stage.setRecCreator(name);
			stage.setRecCreateTime(DateUtils.curDateTimeStr19());
			stage.setFlag("0");
			// 新增
			dao.insert("PM0103.insertStage", stage);
		}
	}

	/**
	 * 保存执行人
	 *
	 * @param staffObj
	 * @param id
	 * @param name
	 * @Title: saveStaff
	 * @return: void
	 */
	private void saveStaff(Object staffObj, String id, String name) {
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (staffObj != null) {
			// 进行类型转换
			list = (List<Map<String, Object>>) staffObj;
		}
		//删除旧的执行人信息
		dao.delete("PM0103.deleteStaff", id);
		// 增强循环
		for (Map<String, Object> map : list) {
			// 实例化实体
			TpmStaff staff = new TpmStaff();
			// 将数据保存到实体中
			staff.fromMap(map);
			// 给实体赋值
			staff.setId(UUID.randomUUID().toString());
			staff.setProjectPk(id);
			staff.setDataGroupCode(PMUtils.getDataGroup());
			staff.setRecCreator(name);
			staff.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0103.insertStaff", staff);
		}
	}

	/**
	 * 保存知会人
	 *
	 * @param knowObj
	 * @param id
	 * @param name
	 * @Title: saveKnow
	 * @return: void
	 */
	private void saveKnow(Object knowObj, String id, String name) {
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (knowObj != null) {
			// 入参类型转换
			list = (List<Map<String, Object>>) knowObj;
		}
		//删除旧的执行人信息
		dao.delete("PM0103.deleteKnow", id);
		// 增强循环
		for (Map<String, Object> map : list) {
			// 实例化实体
			TpmKnow know = new TpmKnow();
			// 将数据保存到实体中
			know.fromMap(map);
			// 给实体赋值
			know.setId(UUID.randomUUID().toString());
			know.setProjectPk(id);
			know.setDataGroupCode(PMUtils.getDataGroup());
			know.setRecCreator(name);
			know.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0103.insertKnow", know);
		}
	}

	/**
	 * 保存项目附件信息
	 *
	 * @param fileObj
	 * @param id
	 * @param deleteFile
	 * @Title: saveFile
	 * @return: void
	 */
	private void saveFile(Object fileObj, String id, List<Map<String, Object>> deleteFile) {
		String path = System.getProperty("user.dir");
		// 实例化list
		List<Map<String, Object>> list = new ArrayList<>();
		// 如果入参不为空
		if (fileObj != null) {
			// 入参类型转换
			list = (List<Map<String, Object>>) fileObj;
		}
		//判断要删除的文件是否为空
		List<String> filePath = new ArrayList<>();
		if (!deleteFile.isEmpty()) {
			//循环修改删除文件状态
			for (Map<String, Object> map : deleteFile) {
				map.put("projectPk", id);
				Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
				map.put("recRevisor", (String) staffByUserId.get("name"));
				map.put("recReviseTime", DateUtils.curDateTimeStr19());
				dao.update("PM0103.updateFile", map);
				filePath.add(path + File.separator + map.get("attachPath"));
			}
		}
		//删除旧的附件信息
		dao.delete("PM0103.deleteFile", id);
		// 增强循环
		for (Map<String, Object> map : list) {
			// 实例化实体
			TpmAtt att = new TpmAtt();
			// 将数据保存到实体
			att.fromMap(map);
			// 给实体赋值
			att.setId(UUID.randomUUID().toString());
			att.setProjectPk(id);
			att.setDataGroupCode(PMUtils.getDataGroup());
			Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
			att.setRecCreator((String) staffByUserId.get("name"));
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			att.setArchiveFlag("1");
			// 新增
			dao.insert("PM0103.insertFile", att);
		}
		//物理删除文件
		if (!filePath.isEmpty()) {
			//循环删除文件
			for (String fileP : filePath) {
				//获取文件
				File file = new File(fileP);
				//判断是否存在
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}

	/**
	 * 查询项目阶段
	 *
	 * @throws
	 * @Title: queryStaff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo queryStage(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "PM0103.queryStageList", "PM0103.queryStageCount", "resultA", new TpmStage().eiMetadata);
	}

	/**
	 * 查询执行人
	 *
	 * @throws
	 * @Title: queryStaff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo queryStaff(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "PM0103.queryStaffList", "PM0103.queryStaffCount", "resultB", new TpmStaff().eiMetadata);
	}

	/**
	 * 查询知会人
	 *
	 * @throws
	 * @Title: queryKnow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo queryKnow(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "PM0103.queryKnowList", "PM0103.queryKnowCount", "resultC", new TpmKnow().eiMetadata);
	}

	/**
	 * 查询项目附件信息
	 *
	 * @throws
	 * @Title: queryFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param inInfo
	 * @param: @return
	 * @return: EiInfo
	 */
	public EiInfo queryFile(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "PM0103.queryFileList", "PM0103.queryFileCount", "resultD", new TpmAtt().eiMetadata);
	}

	/**
	 * @param inInfo attachId 附件ID
	 *               datagroupCode 账套
	 * @Title: queryDeleteFile
	 * @Description: 查询删除的项目附件信息
	 * @return: EiInfo
	 * id 主键
	 * contPk 项目主键
	 * attachId 附件ID
	 * attachName 附件名称
	 * attachPath 附件路径
	 * attachSize 附件大小
	 * attachDesc 附件说明
	 * archiveFlag 归档标记
	 * datagroupCode 账套
	 * recCreator 记录创建责任者
	 * recCreateTime 记录创建时间
	 * recRevisor 记录修改责任者
	 * recReviseTime 记录修改时间
	 */
	public EiInfo queryDeleteFile(EiInfo inInfo) {
		// 调用封装方法
		return queryTabGrid(inInfo, "PM0103.queryDeleteFile", "PM0103.queryDeleteFileCount", "resultF", new TpmAtt().eiMetadata);
	}

	/**
	 * TabGrid查询方法
	 *
	 * @param inInfo
	 * @param querySql
	 * @param countSql
	 * @param resultBlock
	 * @param blockMeta
	 * @return
	 * @Title: queryTabGrid
	 * @return: EiInfo
	 */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql, String countSql, String resultBlock, EiBlockMeta blockMeta) {
		// 调用分页接口构建map
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		//查询数据
		List<TpmStaff> list = dao.query(querySql, map, (Integer) map.get("offset"), (Integer) map.get("limit"));
		//获取总数
		int count = dao.count(countSql, map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}
}
