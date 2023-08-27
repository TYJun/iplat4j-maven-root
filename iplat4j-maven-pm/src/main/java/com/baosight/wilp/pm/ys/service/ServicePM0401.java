package com.baosight.wilp.pm.ys.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.domain.*;
import com.baosight.wilp.util.PMUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.*;
/**
 * 
 * 工程项目：初始化查询、工程项目数据回显、查询执行人、查询知会人、查询项目附件信息、查询督办信息、查询项目进程、查询项目资料信息、查询验收人信息、TabGrid查询方法、编辑保存
 * 保存执行人、保存知会人、保存项目附件信息、保存督办人信息、保存项目进程信息、保存项目提交资料信息、保存项目验收信息
 * <p>1.初始化查询 initLoad
 * <p>2.工程项目数据回显 projectInit
 * <p>3.查询执行人 queryStaff
 * <p>4.查询知会人 queryKnow
 * <p>5.查询项目附件信息 queryFile
 * <p>6.查询督办信息 queryVia
 * <p>7.查询项目进程 queryCed
 * <p>8.查询项目资料信息 queryZiliao
 * <p>9.查询验收人信息 queryAcceptStaff
 * <p>10.TabGrid查询方法 queryTabGrid
 * <p>11.编辑保存 saveProject
 * <p>12.保存执行人 saveStaff
 * <p>13.保存知会人 saveKnow
 * <p>14.保存项目附件信息 saveFile
 * <p>15.保存督办人信息 saveVia
 * <p>16.保存项目进程信息 saveCed
 * <p>17.保存项目提交资料信息 saveZiliao
 * <p>18.保存项目验收信息 saveAcceptStaff
 * 
 * @Title: ServicePM0401.java
 * @ClassName: ServicePM0401
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午3:47:29
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0401 extends ServiceBase {

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
		return super.initLoad(inInfo);
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
	@SuppressWarnings("unchecked")
	public EiInfo projectInit(EiInfo inInfo) {
		//获取参数
		String id = String.valueOf(inInfo.get("id"));
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
		block.addMeta(column1);block.addMeta(column2);block.addMeta(column3);block.addMeta(column4);
		block.setCell(0, "contorId_textField", list.get(0).getProjectPrincipal());
		block.setCell(0, "projectObjectCons_textField", list.get(0).getProjectObjectConsName());
		block.setCell(0, "undertakeDeptNum_textField", list.get(0).getUndertakeDeptName());
		block.setCell(0, "supplierNum_textField", list.get(0).getSupplierName());
			
		inInfo.addBlock(block);
		return inInfo;
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
	 * 查询督办信息
	 * @Title: queryVia
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryVia(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0301.queryViaList","PM0301.queryViaCount","resultD",new TpmVia().eiMetadata);
	}
	
	/**
	 * 查询项目进程
	 * @Title: queryCed
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryCed(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0401.queryCedList","PM0401.queryCedCount","resultE",new TpmCed().eiMetadata);
	}
	
	/**
	 * 查询项目资料信息
	 * @Title: queryZiliao
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryZiliao(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0401.queryZiliaoList","PM0401.queryZiliaoCount","resultF",new TpmZiliao().eiMetadata);
	}
	
	/**
	 * 查询验收人信息
	 * @Title: queryAcceptStaff
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo queryAcceptStaff(EiInfo inInfo) {
	    // 调用封装方法
		return queryTabGrid(inInfo,"PM0401.queryAcceptStaffList","PM0401.queryAcceptStaffCount","resultG",new TpmAcceptStaff().eiMetadata);
	}
	
	@SuppressWarnings("unchecked")
	private EiInfo queryTabGrid(EiInfo inInfo, String querySql,String countSql, String resultBlock, EiBlockMeta blockMeta){
		// 调用封装方法生成map
	    Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", resultBlock);
		//查询数据
		List<TpmStaff> list = dao.query(querySql, map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		//获取总数
		int count = dao.count(countSql, map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, resultBlock, blockMeta, list, count);
	}
	
	/**
	 * 编辑保存
	 * @Title: saveProject
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public EiInfo saveProject(EiInfo inInfo) {
		//获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String,Object> param = block.getRow(0);
		//获取tab/grid参数
		Object staffObj = inInfo.get("staffList");
		Object knowObj = inInfo.get("knowList");
		Object fileObj = inInfo.get("fileList");
		Object viaObj = inInfo.get("viaList");
		Object cedObj = inInfo.get("cedList");
		Object ziliaoObj = inInfo.get("ziliaoList");
		Object acceptStaffObj = inInfo.get("acceptStaffList");
		//获取当前登录用户信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//保存项目
		Tpm01 PM01 = new Tpm01();
		// 实体参数转换
		PM01.fromMap(param);
		PM01.setRecRevisor((String)staffByUserId.get("name"));
		PM01.setRecReviseTime(DateUtils.curDateTimeStr19());
		PM01.setContorName((String)param.get("contorId_textField"));
		PM01.setUndertakeDeptName((String)param.get("undertakeDeptNum_textField"));
		dao.update("PM01.update",PM01);
		//保存Tab/Grid数据
		saveStaff(staffObj,PM01.getId(),staffByUserId);
		saveKnow(knowObj,PM01.getId(),staffByUserId);
		saveFile(fileObj,PM01.getId(),staffByUserId);
		saveVia(viaObj,PM01.getId(),staffByUserId);
		saveCed(cedObj,PM01.getId(),staffByUserId);
		saveZiliao(ziliaoObj,PM01.getId(),staffByUserId);
		saveAcceptStaff(acceptStaffObj,PM01.getId(),staffByUserId);
		return inInfo;
	}
	
	//保存执行人
	@SuppressWarnings("unchecked")
	private void saveStaff(Object staffObj, String id,Map<String, Object> staffByUserId) {
	    // 实例化list
	    List<Map<String,Object>> list = new ArrayList<>();
	    // 入参参数不为空
	    if(staffObj != null){
	        // 入参参数转换类型
			list = (List<Map<String, Object>>) staffObj;
		}
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
		
	//保存知会人
	@SuppressWarnings("unchecked")
	private void saveKnow(Object knowObj, String id,Map<String, Object> staffByUserId) {
		// 实例化list
	    List<Map<String,Object>> list = new ArrayList<>();
		// 入参参数不为空
	    if(knowObj != null){
			// 入参参数转换类型
	        list = (List<Map<String, Object>>) knowObj;
		}
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
		
	//保存项目附件信息
	@SuppressWarnings("unchecked")
	private void saveFile(Object fileObj, String id,Map<String, Object> staffByUserId) {
	    // 实例化list
	    List<Map<String,Object>> list = new ArrayList<>();
		// 入参参数不为空
	    if(fileObj != null){
	        // 入参参数转换类型
			list = (List<Map<String, Object>>) fileObj;
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
			att.setDataGroupCode(PMUtils.getDataGroup());
			att.setRecCreator((String)staffByUserId.get("name"));
			att.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0103.insertFile",att);
		}
	}
	
	//保存督办人信息
	@SuppressWarnings("unchecked")
	private void saveVia(Object viaObj, String id,Map<String, Object> staffByUserId) {
	    // 实例化list
	    List<Map<String,Object>> list = new ArrayList<>();
	    // 入参参数不为空
	    if(viaObj != null){
	        // 入参参数转换类型
			list = (List<Map<String, Object>>) viaObj;
		}
		//删除旧的督办信息
		dao.delete("PM0301.deleteVia", id);
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			TpmVia via = new TpmVia();
			// 实体参数转换
			via.fromMap(map);
			via.setId(UUID.randomUUID().toString());
			via.setProjectPk(id);
			via.setStartTime(PMUtils.timeZoneExchange(via.getStartTime()));
			via.setEndTime(PMUtils.timeZoneExchange(via.getEndTime()));
			via.setDataGroupCode(PMUtils.getDataGroup());
			via.setRecCreator((String)staffByUserId.get("name"));
			via.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0301.insertVia",via);
		}
	}
	
	//保存项目进程信息
	@SuppressWarnings("unchecked")
	private void saveCed(Object cedObj, String id,Map<String, Object> staffByUserId) {
	    // 实例化list
	    List<Map<String,Object>> list = new ArrayList<>();
	    // 入参参数不为空
	    if(cedObj != null){
	        // 入参参数转换类型
			list = (List<Map<String, Object>>) cedObj;
		}
		//删除旧的进程信息
		dao.delete("PM0401.deleteCed", id);
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			TpmCed ced = new TpmCed();
			// 实体参数转换
			ced.fromMap(map);
			ced.setId(UUID.randomUUID().toString());
			ced.setProjectPk(id);
			ced.setStartTime(PMUtils.timeZoneExchange(ced.getStartTime()));
			ced.setEndTime(PMUtils.timeZoneExchange(ced.getEndTime()));
			ced.setDataGroupCode(PMUtils.getDataGroup());
			ced.setRecCreator((String)staffByUserId.get("name"));
			ced.setRecCreateTime(DateUtils.curDateTimeStr19());
			//新增
			dao.insert("PM0401.insertCed",ced);
		}
	}
	
	//保存项目提交资料信息
	@SuppressWarnings("unchecked")
	private void saveZiliao(Object ziliaoObj, String id,Map<String, Object> staffByUserId) {
		// 实例化list
	    List<Map<String,Object>> list = new ArrayList<>();
	    // 如果入参参数不为空
		if(ziliaoObj != null){
		    // 入参参数转换类型
			list = (List<Map<String, Object>>) ziliaoObj;
		}
		//删除旧的提交资料信息
		dao.delete("PM0401.deleteZiliao", id);
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			TpmZiliao ziliao = new TpmZiliao();
			// 实体参数转换
			ziliao.fromMap(map);
			ziliao.setId(UUID.randomUUID().toString());
			ziliao.setProjectPk(id);
			ziliao.setDataGroupCode(PMUtils.getDataGroup());
			ziliao.setRecCreator((String)staffByUserId.get("name"));
			ziliao.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0401.insertZiliao",ziliao);
		}
	}
	
	//保存项目验收信息
	@SuppressWarnings("unchecked")
	private void saveAcceptStaff(Object acceptStaffObj, String id,Map<String, Object> staffByUserId) {
		// 实例化list
	    List<Map<String,Object>> list = new ArrayList<>();
		// 如果入参参数不为空
	    if(acceptStaffObj != null){
	        // 入参参数类型转换
			list = (List<Map<String, Object>>) acceptStaffObj;
		}
		//删除旧的验收信息
		dao.delete("PM0401.deleteAcceptStaff", id);
		// 增强循环
		for (Map<String, Object> map : list) {
		    // 实例化实体
			TpmAcceptStaff acceptStaff = new TpmAcceptStaff();
			// 实体参数转换
			acceptStaff.fromMap(map);
			acceptStaff.setId(UUID.randomUUID().toString());
			acceptStaff.setProjectPk(id);
			acceptStaff.setDataGroupCode(PMUtils.getDataGroup());
			acceptStaff.setRecCreator((String)staffByUserId.get("name"));
			acceptStaff.setRecCreateTime(DateUtils.curDateTimeStr19());
			// 新增
			dao.insert("PM0401.insertAcceptStaff",acceptStaff);
		}
	}

}
