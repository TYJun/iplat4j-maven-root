package com.baosight.wilp.pm.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.pm.domain.Tpm01;
import com.baosight.wilp.pm.domain.TpmVia;
import com.baosight.wilp.pm.utils.PMUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.*;
/**
 * 
 * 工程项目：初始化查询、工程项目数据回显、查询督办信息、保存项目督办信息、是否发送短信
 * <p>1.初始化查询 initLoad
 * <p>2.工程项目数据回显 projectInit
 * <p>3.查询督办信息 queryVia
 * <p>4.保存项目督办信息 viaProject
 * <p>5.是否发送短信 isSend
 * 
 * @Title: ServicePM0301.java
 * @ClassName: ServicePM0301
 * @Package：com.baosight.wilp.pm.service
 * @author: zhaow
 * @date: 2021年8月28日 下午3:40:13
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServicePM0301 extends ServiceBase {
	
	/**记录一个项目的督办人***/
	public static Map<String,String> viaSmsMap = new HashMap<>();

	/**
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
		inInfo.addBlock(block);
		return inInfo;
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
	public EiInfo queryVia(EiInfo inInfo){
		//获取参数
		Map<String, Object> map = PMUtils.buildParamter(inInfo, "inqu_status", "resultB");
		//查询数据
		List<TpmVia> list = dao.query("PM0301.queryViaList",map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		//获取总数
		int count = dao.count("PM0301.queryViaCount", map);
		//数据返回
		return PMUtils.BuildOutEiInfo(inInfo, "resultB", new TpmVia().eiMetadata, list, count);
	}
	
	/**
	 * @Title: viaProject
	 * @Description: 保存项目督办信息
	 * @param:  @param inInfo
	 * @param:  @return
	 * @return: EiInfo  
	 * @throws
	 */
	public EiInfo viaProject(EiInfo inInfo){
		boolean smsFlag = true;
		//获取表单参数
		EiBlock block = inInfo.getBlock("inqu_status");
		Map<String,Object> param = block.getRow(0);
		//获取tab/grid参数
		Object viaList = inInfo.get("viaList");
		//获取当前登录用户信息
        Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		//保存项目
		Tpm01 PM01 = new Tpm01();
		PM01.fromMap(param);
		PM01.setRecRevisor((String)staffByUserId.get("name"));
		PM01.setRecReviseTime(DateUtils.curDateTimeStr19());
		PM01.setProjectTypeName((String) param.get("projectTypeNum_textField"));
		PM01.setContorName((String)param.get("contorId_textField"));
		PM01.setUndertakeDeptName((String) param.get("undertakeDeptNum_textField"));
		PM01.setProjectObjectConsName((String) param.get("projectObjectCons_textField"));
		PM01.setSupplierName((String) param.get("supplierNum_textField"));
		dao.update("PM01.update",PM01);
		//处理下viaSmsMap,为后面发送是否发送短信做准备
//		if(viaSmsMap.isEmpty()){
//			List<Map<String,String>> queryList =  (List<Map<String, String>>) viaList;
//			String str = "";
//			for (int i = 0; i < queryList.size(); i++) {
//				str += queryList.get(i).get("superviseMaker") + ",";
//			}
//			String finalStr = str;
//			queryList.forEach(map -> viaSmsMap.put(PM01.getId(), finalStr.substring(0, finalStr.length()-1)));
//		}
		//保存督办信息
		List<Map<String,Object>> list = new ArrayList<>();
		if(viaList != null){
			list = (List<Map<String, Object>>) viaList;
		}
		//删除旧的执行人信息
		dao.delete("PM0301.deleteVia", PM01.getId());
		//新增
		for (Map<String, Object> map : list) {
			TpmVia via = new TpmVia();
			via.fromMap(map);
			via.setId(UUID.randomUUID().toString());
			via.setProjectPk(PM01.getId());
			via.setStartTime(PMUtils.timeZoneExchange(via.getStartTime()));
			via.setEndTime(PMUtils.timeZoneExchange(via.getEndTime()));
			via.setDataGroupCode(PMUtils.getDataGroup());
			via.setRecCreator(UserSession.getUser().getUsername());
			via.setRecCreateTime(DateUtils.curDateTimeStr19());
			dao.insert("PM0301.insertVia",via);
		}
		//发送短信
		//if(isSend(PM01.getId(), list)){ SmsCallUtils.smsCall("projectVia", PM01.getId()); }
		//发送短信
		if (true) {
			EiInfo eiInfo = new EiInfo();
			//设置参数
			eiInfo.set("configType", "projectVia");
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

	//是否发送短信
	private boolean isSend(String projectId, List<Map<String,Object>> list){
		if(list == null || list.size() == 0){
			return false;
		}
		//获取督办人
		StringBuilder sb = new StringBuilder();
		list.forEach(map -> sb.append(map.get("superviseMaker")).append(","));
		String workNos = sb.deleteCharAt(sb.length()-1).toString();
		//1.判断是否需要发送短信(只要督办人发生变化，就发送短信)
		if(viaSmsMap.containsKey(projectId) && viaSmsMap.get(projectId).equals(workNos)){
			return false;
		} else {
			viaSmsMap.put(projectId, workNos);
			return true;
		}
		//2.只发送一次,不管督办人是否发生变化
		/*if(viaSmsMap.containsKey(projectId)){
			return false;
		} else {
			viaSmsMap.put(projectId, workNos);
			return true;
		}*/
	}

}
