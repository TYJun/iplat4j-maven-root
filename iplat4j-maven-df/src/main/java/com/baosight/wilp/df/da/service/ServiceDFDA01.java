package com.baosight.wilp.df.da.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfConstant;
import com.baosight.wilp.df.common.util.DFUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 设备档案：初始化查询、设备档案查询、选择科室和人员、选择科室和人员、删除设备信息、调用微服务人员、调用微服务科室、调用微服务地点、分页
 * <p>1.初始化查询 initLoad
 * <p>2.设备档案查询 query
 * <p>3.选择科室和人员 queryDeptAndWork(弃用，改用微服务)
 * <p>4.选择科室和人员 queryDeptByworkNum(弃用，改用微服务)
 * <p>5.删除设备信息 deleteItem
 * <p>6.调用微服务人员 queryPerson
 * <p>7.调用微服务科室 queryDept
 * <p>8.调用微服务地点 querySpot
 * 
 * @Title: ServiceDFDA01.java
 * @ClassName: ServiceDFDA01
 * @Package：com.baosight.wilp.df.da.service
 * @author: zhaow
 * @date: 2021年8月10日 下午2:55:52
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFDA01 extends ServiceBase{

	/**
	 * 
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @param info
	 * machineCode : 设备编码
     * machineName : 设备名称
     * machineTypeId : 设备分类
     * models : 规格型号
     * needScan : 是否扫二维码
     * managerManId : 设备管理员
     * warrantyDateS : 失效日期起
     * warrantyDateE : 失效日期止
     * buyMode : 购置方式
     * useDeaprtName : 使用科室
	 * @return info
	 * machineCode : 设备编码
     * machineName : 设备名称
     * models : 规格型号
     * machineTypeId : 设备分类
     * status : 设备状态
     * needScan : 是否扫二维码
     * buyTime : 购买日期
     * fixedTime : 安装日期
     * useTime : 使用日期
     * spotNum : 地点编码
     * spotName : 地点名称
     * building : 楼
     * floor : 层
     * warrantyDate : 质保到期日
     * makerBrand : 品牌
     * supplierName : 供应商名称
     * supplierId : 供应商id
     * manufacturerName : 生产单位
     * maintUnit : 保养单位
     * lastMaintainTime : 上次保养日期
     * maintainRound : 保养周期
     * workNo : 人员工号
     * name : 人员姓名
     * deptNum : 科室编码
     * deptName : 科室名称
     * matNum : 物资编码
     * matName : 物资名称
     * outFactoryNo : 出厂编号
     * useLimit : 折旧年限
     * useFor : 用途
     * machineFolderId : 档案盒号
     * buyMode : 购置方式
     * assetPrice : 资产价格(元)
     * memo : 备注
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 调用查询方法
		return query(info);
	}
	
	/**
	 * 
	 * @Title: query
	 * @Description: 设备档案查询
	 * @param info
	 * machineCode : 设备编码
     * machineName : 设备名称
     * machineTypeId : 设备分类
     * models : 规格型号
     * needScan : 是否扫二维码
     * managerManId : 设备管理员
     * warrantyDateS : 失效日期起
     * warrantyDateE : 失效日期止
     * buyMode : 购置方式
     * useDeaprtName : 使用科室
	 * @return info
	 * machineCode : 设备编码
	 * machineName : 设备名称
	 * models : 规格型号
	 * machineTypeId : 设备分类
	 * status : 设备状态
	 * needScan : 是否扫二维码
	 * buyTime : 购买日期
	 * fixedTime : 安装日期
	 * useTime : 使用日期
	 * spotNum : 地点编码
	 * spotName : 地点名称
	 * building : 楼
	 * floor : 层
	 * warrantyDate : 质保到期日
	 * makerBrand : 品牌
	 * supplierName : 供应商名称
	 * supplierId : 供应商id
	 * manufacturerName : 生产单位
	 * maintUnit : 保养单位
	 * lastMaintainTime : 上次保养日期
	 * maintainRound : 保养周期
	 * workNo : 人员工号
	 * name : 人员姓名
	 * deptNum : 科室编码
	 * deptName : 科室名称
	 * matNum : 物资编码
	 * matName : 物资名称
	 * outFactoryNo : 出厂编号
	 * useLimit : 折旧年限
	 * useFor : 用途
	 * machineFolderId : 档案盒号
	 * buyMode : 购置方式
	 * assetPrice : 资产价格(元)
	 * memo : 备注
	 */
	@Override
	public EiInfo query(EiInfo info) {
	    // 定义数组
		String[] fieldList = {"machineCode","machineName","classifyName","models",
				"warrantyDateS","warrantyDateE","buyMode","useDeaprtName","managerDepartName"};
		// 调用分页方法构建map
		Map<String, Object> map = CommonUtils.buildParamter(info,"result", Arrays.asList(fieldList));
		// 查询返回list，参数为map
		List<Map<String, Object>> list = dao.query("DFDA01.query", map);
		// 查询返回数量，参数为map
		Integer count = dao.count("DFDA01.count", map);
		// list为空
		if(CollectionUtils.isEmpty(list)){
		    // 返回
		    return info;
		}
		// 调用分页接口
		return CommonUtils.BuildOutEiInfo(info, null, null, list, count);
		
	}
	
    /**
     * @Title: queryDeptAndWork 
     * @Description: 选择科室和人员(弃用，改用微服务)
     * @param inInfo
     * @return: inInfo
     */
	public EiInfo queryDeptAndWork(EiInfo inInfo) {
	    // 实例化map
    	Map<String, Object> map = new HashMap<String, Object>(8);
        // 调用接口构建map
    	map = CommonUtils.buildParamter(inInfo,"inqu_status", "result");
		// 数据查询
		List<Map<String, Object>> list = dao.query("DFDA01.queryDeptAndWork", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		if(list.isEmpty()) {
		    return inInfo;
		}
		// 查询数量
		int count = dao.count("DFDA01.queryDeptAndWorkCount", map);
		// 如果结果不为空
		if(CollectionUtils.isNotEmpty(list)){
		    // 调用分页接口
			return CommonUtils.BuildOutEiInfo(inInfo, "result", null, list, count);
		} else {
		    // 返回
			return inInfo;
		}
	}
    
    /**
     * @Title: queryDeptByworkNum 
     * @Description: 选择科室和人员(弃用，改用微服务)
     * @param inInfo
     * @return inInfo
     */
	public EiInfo queryDeptByworkNum(EiInfo inInfo) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo,"inqu_status", "result");
		// map设置workNum
		map.put("workNum", inInfo.get("workNum"));
		// 数据查询
		List<Map<String, Object>> list = dao.query("DFDA01.queryDeptAndWork", map, (Integer)map.get("offset"), (Integer)map.get("limit"));
		if(list.isEmpty()) {
            return inInfo;
        }
		// info设置值deptName
		inInfo.set("deptName", list.get(0).get("deptName"));
		// info设置值deptNum
		inInfo.set("deptNum", list.get(0).get("deptNum"));
		// 查询数据条数
		int count = dao.count("DFDA01.queryDeptAndWorkCount", map);
		// 如果list为空
		if(CollectionUtils.isNotEmpty(list)){
		    // 调用分页接口并返回
			return CommonUtils.BuildOutEiInfo(inInfo, "result", null, list, count);
		} else {
		    // 返回
			return inInfo; 
		}
	}
	
    /**
     * @Title: deleteItem 
     * @Description: 删除设备信息
     * @param info
     * @return: info
     */
	public EiInfo deleteItem(EiInfo info) {
	    // 获取list
		List<String> list = (List<String>)info.get("list");

		// 判断设备是否关联巡查基准或保养基准
		boolean isDelete = true;
		for(int i = 0; i < list.size(); i++) {
		    // 查询方法
			isDelete = isDelete  && isEnableDelete(list.get(i));
		}
		// 如果集合为空
		if(isDelete) {
		    // 删除
		    dao.delete("DFDA01.deleteDevicemachine",list);
		    // 设置返回信息
		    info.setMsg("删除成功");
		    // 返回
		    return info;
		}
		// 设置返回信息
		info.setMsg("删除失败,该设备存在基准");
		// 返回
		return info;
	}

	/**
	 * 校验设备是否可以删除
	 * @Title: isEnableDelete
	 * @Description: 校验设备是否可以删除
	 * @param deviceId deviceId ： 设备id
	 * @return boolean true=可以删除，false=不可以删除
	 * @throws
	 **/
	private boolean isEnableDelete(String deviceId) {
		boolean isDelete = true;
		//判断是否存在巡检、保养模块
		if(!DFUtils.isExistModule(DfConstant.MODULE_CODE_DI) && !DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
			return true;
		}
		//判断是否存在巡检模块,存在，查询是否关联
		if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DI)){
			//关联了设备时 isDelete = false
			isDelete = isDelete && !diRelationDevice(deviceId);
		}
		//判断是否存在保养模块,存在，查询是否关联
		if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
			//关联了设备时 isDelete = false
			isDelete = isDelete && !dkRelationDevice(deviceId);
		}
		return isDelete;
	}

	/**
	 * 判断巡检基准是否关联指定设备
	 * @Title: diRelationDevice
	 * @Description: 判断巡检基准是否关联指定设备
	 * @param deviceId deviceId ： 设备ID
	 * @return boolean true=关联设备，false=没有关联设备
	 * @throws
	 **/
	private boolean diRelationDevice (String deviceId){
		try{
			int count = super.count("DFDA01.isDiRelationDevice", deviceId);
			return count > 0 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断保养基准是否关联指定设备
	 * @Title: diRelationDevice
	 * @Description: 判断保养基准是否关联指定设备
	 * @param deviceId deviceId ： 设备ID
	 * @return boolean true=关联设备，false=没有关联设备
	 * @throws
	 **/
	private boolean dkRelationDevice (String deviceId){
		try{
			int count = super.count("DFDA01.isDkRelationDevice", deviceId);
			return count > 0 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
     * @Title: startRow 
     * @Description: 启用设备信息
     * @param info
     * @return: info
     */
    public EiInfo startRow(EiInfo info) {
        // 获取list
        List<String> list = (List<String>)info.get("list");
        // 启动
        dao.delete("DFDA01.startRow",list);
        // 设置返回信息
        info.setMsg("启用成功");
        // 返回
        return info;
    }
    
    /**
     * @Title: stopRow 
     * @Description: 停用设备信息
     * @param info
     * @return: info
     */
    public EiInfo stopRow(EiInfo info) {
        // 获取list
        List<String> list = (List<String>)info.get("list");
        // 停用
        dao.delete("DFDA01.stopRow",list);
        // 设置返回信息
        info.setMsg("停用成功");
        // 返回
        return info;
    }
	
	/**
	 * @Title: queryPerson 
	 * @Description: 接口改造(人员)2021-08-04
	 * @param info
	 * userName : 用户名
	 * @return info
	 * workNo : 工号
	 * userName : 用户名
	 */
	public EiInfo queryPerson(EiInfo info) {
	    // 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "person");
		//调用微服务接口
		map.put("datagroupCode",BaseDockingUtils.getUserGroupByWorkNo(null));
		EiInfo person=BaseDockingUtils.getStaffAllPage(map, "person");
		person.setBlockInfoValue("dept", "showCount", "true");
		return person;
	}
	
	/**
	 * @Title: queryDept 
	 * @Description: 接口改造(科室)2021-08-04
	 * @param info
	 * @return info
	 * deptNum : 科室编码
	 * deptName : 科室名称
	 */
	public EiInfo queryDept(EiInfo info) {
	    // 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dept");
		// 调用远程服务获取改造科室接口
		map.put("datagroupCode",BaseDockingUtils.getUserGroupByWorkNo(null));
		EiInfo dept=BaseDockingUtils.getDeptAllPage(map, "dept");
		dept.setBlockInfoValue("dept", "showCount", "true");
		return dept;
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
		List<Map<String, Object>> spotList = BaseDockingUtils.getSpotByDeptId("");
		spotList.forEach(pMap -> pMap.put("spotId", pMap.get("id")));
		//将数据进行逻辑分页
		EiInfo spot = CommonUtils.BuildOutEiInfoWithLogicalPage(spotList, map, "spot");
		spot.setBlockInfoValue("dept", "showCount", "true");
		return spot;
	}



}
