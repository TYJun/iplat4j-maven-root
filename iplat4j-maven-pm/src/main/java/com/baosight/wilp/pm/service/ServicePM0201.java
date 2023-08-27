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

import java.io.File;
import java.util.*;
/**
 * 
 * 工程项目：初始化查询、工程项目数据回显、查询执行人、查询知会人、TabGrid查询方法、查询项目附件信息、查询项目甲方材料、查询项目乙方材料、项目执行、保存执行人、保存知会人、保存项目附件信息、保存项目材料、是否发送短信
 * <p>1.初始化查询 initLoad
 * <p>2.工程项目数据回显 projectInit
 * <p>3.查询执行人 queryStaff
 * <p>4.查询知会人 queryKnow
 * <p>5.TabGrid查询方法 queryTabGrid
 * <p>6.查询项目附件信息 queryFile
 * <p>7.查询项目甲方材料 queryJStuff
 * <p>8.查询项目乙方材料 queryYStuff
 * <p>9.项目执行 excuteProject
 * <p>10.保存执行人 saveStaff
 * <p>11.保存知会人 saveKnow
 * <p>12.保存项目附件信息 saveFile
 * <p>13.保存项目材料 saveStuff
 * <p>14.是否发送短信 isSend
 * 
 * @Title: ServicePM0201.java
 * @ClassName: ServicePM0201
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午2:59:18
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0201 extends ServiceBase {
	
	/**记录一个项目的执行人***/
	public static Map<String,String> staffSmsMap = new HashMap<>();

	/**
     * 
     * @Title: initLoad
     * @Description: 初始化查询
     * @param inInfo
     * @return inInfo
     * @see ServiceBase#initLoad(EiInfo)
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用初始化方法
		return super.initLoad(inInfo,new Tpm01());
	}
	
	/**
	 * 数据回显
	 * @Title: projectInit
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo projectInit(EiInfo inInfo) {
		//获取参数
		String id = String.valueOf(inInfo.get("id"));
		String type = String.valueOf(inInfo.get("type"));
		//构建Block
		EiBlock block = new EiBlock("inqu_status");
		block.addBlockMeta(new Tpm01().eiMetadata);
		// 实例化map
		Map<String,Object> param = new HashMap<>();
		// map赋值
		param.put("id", id);
		//获取数据
		List<Tpm01> list = dao.query("PM01.query", param);
		//数据返回
		block.addRows(list);
		//特殊数据处理
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
		inInfo.addBlock(block);
		return inInfo;
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
		return queryTabGrid(inInfo, "PM0103.queryStageList", "PM0103.queryStageCount", "resultF", new TpmStage().eiMetadata);
	}

	/**
	 * 查询执行人
	 * @Title: queryStaff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryStaff(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0103.queryStaffList","PM0103.queryStaffCount","resultA",new TpmStaff().eiMetadata);
	}
	
	/**
	 * 查询知会人
	 * @Title: queryKnow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryKnow(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0103.queryKnowList","PM0103.queryKnowCount","resultB",new TpmKnow().eiMetadata);
	}
	
	/**
	 * 查询项目附件信息
	 * @Title: queryFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryFile(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0103.queryFileList","PM0103.queryFileCount","resultC",new TpmAtt().eiMetadata);
	}
	
	/**
     * TabGrid查询方法
     * @Title: queryTabGrid
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql,String countSql, String resultBlock, EiBlockMeta blockMeta){
		// 调用封装方法创建map
	    Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		//查询数据
		List<TpmStaff> list = dao.query(querySql, map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		//获取总数
		int count = dao.count(countSql, map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}
	
	/**
	 * 查询项目材料
	 * @Title: queryStuff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryJStuff(EiInfo inInfo){
		//构建参数对象
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", "resultD");
		// 设置参数
		map.put("supType", "1");
		//数据查询
		List<TpmStuff> list = dao.query("PM0201.queryStuffList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		// 获取查询项目材料数量
		int count = dao.count("PM0201.queryStuffCount", map);
		//返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultD", new TpmStuff().eiMetadata, list, count);
	}
	
	/**
	   *   查询项目材料
     * @Title: queryStuff
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	public EiInfo queryYStuff(EiInfo inInfo){
		//构建参数对象
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", "resultE");
		// 设置参数
		map.put("supType", "2");
		//数据查询
		List<TpmStuff> list = dao.query("PM0201.queryStuffList", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		// 获取查询项目材料数量
		int count = dao.count("PM0201.queryStuffCount", map);
		//返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultE", new TpmStuff().eiMetadata, list, count);
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
		return queryTabGrid(inInfo, "PM0103.queryDeleteFile", "PM0103.queryDeleteFileCount", "resultG", new TpmAtt().eiMetadata);
	}

	/**
	 * 项目执行
	 * @Title: excuteProject
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo excuteProject(EiInfo inInfo) {
		//获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String,Object> param = block.getRow(0);
		//获取tab/grid参数
		Object stageObj = inInfo.get("stageList");
		Object staffObj = inInfo.get("staffList");
		Object knowObj = inInfo.get("knowList");
		Object fileObj = inInfo.get("fileList");
		Object jStuffObj = inInfo.get("jStuffList");
		Object yStuffObj = inInfo.get("yStuffList");
		List<Map<String, Object>> deleteFile = (List<Map<String, Object>>) inInfo.get("deleteFile");
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//用户名
		String name = (String)staffByUserId.get("name");
		// 实例化实体
		Tpm01 PM01 = new Tpm01();
		// 实体参数处理
		PM01.fromMap(param);
		PM01.setProjectStatus("02");
		PM01.setRecRevisor(name);
		PM01.setRecReviseTime(DateUtils.curDateTimeStr19());
		PM01.setProjectTypeName((String) param.get("projectTypeNum_textField"));
		PM01.setContorName((String)param.get("contorId_textField"));
        PM01.setUndertakeDeptName((String)param.get("undertakeDeptNum_textField"));
        PM01.setProjectObjectConsName((String)param.get("projectObjectCons_textField"));
        PM01.setSupplierName((String)param.get("supplierNum_textField"));
        // 保存项目
		dao.update("PM01.update",PM01);
		//处理下staffSmsMap,为后面发送是否发送短信做准备
		if(staffSmsMap.isEmpty()){
			List<Map<String,String>> queryList = dao.query("PM0201.querySendStaff", null);
			queryList.forEach(map -> staffSmsMap.put(map.get("projectPk"), map.get("workNos")));
		}
		//保存Tab/Grid数据
		saveStage(stageObj, PM01.getId(), name);
		saveStaff(staffObj,PM01.getId());
		saveKnow(knowObj,PM01.getId());
		saveFile(fileObj,PM01.getId(),deleteFile);
		saveStuff(jStuffObj,PM01.getId(), "1");
		saveStuff(yStuffObj,PM01.getId(), "2");
		inInfo.setMsg("执行成功");
		//发送短信
        /*if(isSend(PM01.getId(), staffObj)){
        	SmsCallUtils.smsCall("projectExec", PM01.getId());
        }*/
		//发送短信
//		if (isSend(PM01.getId(), staffObj)) {
		if (false) {
			EiInfo eiInfo = new EiInfo();
			//设置参数
			eiInfo.set("configType", "projectExec");
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
     * @Title: excuteProject
     * @Description: 保存执行人
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	private void saveStaff(Object staffObj, String id) {
	    // 实例化list
		List<Map<String,Object>> list = new ArrayList<>();
		// 入参参数不为空
		if(staffObj != null){
			// 入参参数转换类型
		    list = (List<Map<String, Object>>) staffObj;
		}
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//删除旧的执行人信息
		dao.delete("PM0103.deleteStaff", id);
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			TpmStaff staff = new TpmStaff();
			// 实体参数转换
			staff.fromMap(map);
			staff.setId(UUID.randomUUID().toString());
			staff.setProjectPk(id);
			staff.setDataGroupCode(PMUtils.getDataGroup());
			staff.setRecCreator((String)staffByUserId.get("name"));
			staff.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0103.insertStaff",staff);
		}
	}
		
	/**
     * @Title: saveKnow
     * @Description: 保存知会人
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	private void saveKnow(Object knowObj, String id) {
	    // 实例化list
		List<Map<String,Object>> list = new ArrayList<>();
		// 如果入参参数不为空
		if(knowObj != null){
		    // 入参参数类型转换
			list = (List<Map<String, Object>>) knowObj;
		}
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//删除旧的执行人信息
		dao.delete("PM0103.deleteKnow", id);
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			TpmKnow know = new TpmKnow();
			// 实体参数转换
			know.fromMap(map);
			know.setId(UUID.randomUUID().toString());
			know.setProjectPk(id);
			know.setDataGroupCode(PMUtils.getDataGroup());
			know.setRecCreator((String)staffByUserId.get("name"));
			know.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0103.insertKnow",know);
		}
	}
		
	/**
     * @Title: saveFile
     * @Description: 保存项目附件信息
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	private void saveFile(Object fileObj, String id,List<Map<String, Object>> deleteFile) {
		String path = System.getProperty("user.dir");
	    // 实例化list
		List<Map<String,Object>> list = new ArrayList<>();
		// 如果入参参数不为空
		if(fileObj != null){
			// 入参参数类型转换
		    list = (List<Map<String, Object>>) fileObj;
		}
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		List<String> filePath = new ArrayList<>();
		if (!deleteFile.isEmpty()) {
			//循环修改删除文件状态
			for (Map<String, Object> map : deleteFile) {
				map.put("projectPk", id);
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
			// 实体参数转换
			att.fromMap(map);
			att.setId(UUID.randomUUID().toString());
			att.setProjectPk(id);
			att.setArchiveFlag("1");
			att.setStage((String) map.get("stage"));
			att.setDataGroupCode(PMUtils.getDataGroup());
			att.setRecCreator((String)staffByUserId.get("name"));
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0103.insertFile",att);
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
     * @Title: saveStuff
     * @Description: 保存项目材料
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	private void saveStuff(Object stuffObj, String id, String supType) {
	    // 实例化list
		List<Map<String,Object>> list = new ArrayList<>();
		// 如果入参参数不为空
		if(stuffObj != null){
		    // 参数类型转换
			list = (List<Map<String, Object>>) stuffObj;
		}
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 实例化map
		Map<String,Object> param  = new HashMap<>();
		// map赋值
		param.put("id",id);
		param.put("supType",supType);
		//删除旧的项目材料
		dao.delete("PM0201.deleteStuff", param);
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			TpmStuff stuff = new TpmStuff();
			// 实体参数转换
			stuff.fromMap(map);
			stuff.setId(UUID.randomUUID().toString());
			stuff.setProjectPk(id);
			stuff.setSupType(supType);
			stuff.setDataGroupCode(PMUtils.getDataGroup());
			stuff.setRecCreator((String)staffByUserId.get("name"));
			stuff.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0201.insertStuff",stuff);
		}
	}
	
	/**
     * @Title: isSend
     * @Description: 判断是否发送短信
     * @param:  @param inInfo
     * @param:  @return
     * @return: EiInfo  
     * @throws
     */
	private boolean isSend(String projectId, Object staffObj){
	    // 入参为空
		if(staffObj == null){
			return false;
		}
		// 入参类型转换
		List<Map<String,Object>> list = (List<Map<String, Object>>) staffObj;
		if(list.size() == 0){
			return false;
		}
		//获取执行人
		StringBuilder sb = new StringBuilder();
		list.forEach(map -> sb.append(map.get("execStaffId")).append(","));
		String workNos = sb.deleteCharAt(sb.length()-1).toString();
		//1.判断是否需要发送短信(只要执行人发生变化，就发送短信)
		if(staffSmsMap.containsKey(projectId) && staffSmsMap.get(projectId).equals(workNos)){
			return false;
		} else {
			staffSmsMap.put(projectId, workNos);
			return true;
		}
		//2.只发送一次,不管执行人是否发生变化
		/*if(staffSmsMap.containsKey(projectId)){
			return false;
		} else {
			staffSmsMap.put(projectId, workNos);
			return true;
		}*/
	}

}
