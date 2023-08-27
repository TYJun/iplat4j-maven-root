package com.baosight.wilp.dk.jz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.dk.common.util.DeviceEiUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保养基准管理：保养基准页面的初始化方法、查询保养基准的信息、基准删除、基准启用、基准禁用
 * <p>1.基准初始化查询  initLoad
 * <p>2.基准查询   query
 * <p>3.基准删除   deleteScheme
 * <p>4.基准启用   startScheme
 * <p>5.基准禁用   stopScheme
 * 
 * @Title: ServiceDKJZ01.java
 * @ClassName: ServiceDKJZ01
 * @Package：com.baosight.wilp.dk.jz.service
 * @author: LENOVO
 * @date: 2021年9月14日 下午2:33:27
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDKJZ01 extends ServiceBase{

	/**
	 * 保养基准页面的初始化方法
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}
	
	/**
	 * 查询保养基准的信息
	 * <p>1.获取参数
     * <p>2.将参数封装并获取分页，执行查询
     * <p>3.判断是否与设备包关联
     * <p>4.将返回的保养基准list和count添加到EiiNFO并返回客户端
     * @param inInfo
     * schemeID    基准id
     * schemeCode  基准编码
     * schemeName  基准名称
     * schemeDept  单位部门
     * machineCode 设备编号
     * machineName 设备名称
     * status      状态
     * @return
     * schemeID    主键
     * schemeCode  基准代码
     * schemeName  基准名称
     * machineCode 设备编码
     * machineName 设备名称
     * fixedPlace  地点
     * status      状态
     * statusName  状态名称
     * createTime  创建时间
     * createMan   创建人
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#query(com.baosight.iplat4j.core.ei.EiInfo)
	 */
   @Override
   public EiInfo query(EiInfo inInfo) {
	 //1.将传递参数分装map
	   String[] param = {"schemeID", "schemeCode", "schemeName", "schemeDept", "machineCode", "machineName", "status"};
       Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
     //2.使用map参数查询获取保养基准list和count
       List<Map<String, String>> list = dao.query("DKJZ01.queryScheme", map);
       
       //3.判断是否与设备包关联
       Map param2 = new HashMap();
       //4.list不为空查询设备包
       if(null != list || list.size()>0) {
           for (int i = 0; i < list.size(); i++) {
               Map<String, String> map2 = list.get(i);
               //5.获取设备包id
               String packetID = map2.get("packetID");
               //6.获取设备id
               String machineID = map2.get("machineID");
               param2.put("packetID", packetID);
               param2.put("machineID", machineID);
               //7.设备包id不为空查询设备
               if(null != packetID) {
                   //8.获取设备包信息并赋值
                   List<Map<String,String>> query = dao.query("DKJZ01.queryPacket", param2);
                   //9.for循环给设备添加地址设备编码信息
                   for (int j = 0; j < query.size(); j++) {
                       Map<String, String> map3 = query.get(j);
                       String deviceId = map3.get("device_id");
                       //10.判断设备id是否等于设备id
                       if(machineID.equals(deviceId)) {
                           //11.赋值地址
                           map2.put("spotName", map3.get("fixed_place"));
                           //12.赋值设别编码
                           map2.put("machineCode", map3.get("machine_code"));
                           map2.put("machineName", map3.get("machine_name"));
                       }
                   }
               }
           }
       }
       int count = dao.count("DKJZ01.countScheme", map);
     //13.将返回的保养基准list和count添加到EiiNFO并返回客户端
       return DeviceEiUtil.buildResult(inInfo, list, count, "result");
   }
   
   /**
    * 删除基准
    * @Title: deleteScheme
    * <p>1.删除基准时判断该基准是否生成任务,生成任务则不能删除
    * <p>2.删除基准
    * <p>3.循环删除执行周期、基准项目、执行人
    * <p>4.返回删除成功
    * @param:  @param inInfo
    * id  基准id
    * @return: EiInfo  
    * 删除成功否则失败并执行回滚操作
    * @throws
    */
   public EiInfo deleteScheme(EiInfo inInfo) {
	//1.获取选中行的id并分装到LIST里
   	List<String> ids = (List<String>) inInfo.get("list");
   	//2.获取基准是否已生成任务
   	int num=dao.count("DKJZ01.isTask", ids);
   	//3.大于0则标识该基准已生成任务
   	if(num>0) {
   		inInfo.setMsg("不能删除已生成任务的基准");
   		return inInfo;
   	}
   	//4.通过LIST里的基准id删除基准
   	dao.delete("DKJZ01.deleteScheme", ids);
   	for (String id : ids) {
			dao.delete("DKJZ01.deleteCycle", id);//删除执行周期
			dao.delete("DKJZ01.deleteItem", id);//删除基准项目
			dao.delete("DKJZ01.deleteExman", id);//删除执行人
		}
  //6.返回删除成功
   	inInfo.setMsg("删除成功");
   	return inInfo;
   }
   
   /**
    * 基准启用
    * <p>循环将基准修改为启用状态
    * @Title: openInfo 
    * @param inInfo
    * list  选中基准的id
    * @return
    * 启用成功，失败则返回失败执行回滚
    * @return: EiInfo
    */
   public EiInfo startScheme(EiInfo inInfo) {
   	List<String> list = (List<String>) inInfo.get("list");
  //1.循环将基准修改为启用状态
   	list.forEach(schemeID -> {
   		Map<String, Object> param = new HashMap<>();
   		//2.赋值状态为1
   		param.put("status", 1);
   		//3.赋值基准id
   		param.put("schemeID", schemeID);
   		dao.update("DKJZ01.updateScheme", param);
   	});
   	EiInfo outInfo = new EiInfo();
   	//4.启用成功
   	outInfo.setMsg("启用成功");
   	return outInfo;
   }
   
   /**
    * 基准禁用
    * <p>循环将基准修改为禁用状态
    * @Title: openInfo 
    * @param inInfo
    * list  选中基准的id
    * @return
    * 禁用成功，失败则返回失败执行回滚
    * @return: EiInfo
    */
   public EiInfo stopScheme(EiInfo inInfo) {
   	List<String> list = (List<String>) inInfo.get("list");
     //1.通过LIST里的基准id循环修改该基准的状态为禁用状态
   	list.forEach(schemeID -> {
   		Map<String, Object> param = new HashMap<>();
   		//2.赋值状态为-1
   		param.put("status", -1);
   		//3.赋值基准id
   		param.put("schemeID", schemeID);
   		dao.update("DKJZ01.updateScheme", param);
   	});
   	EiInfo outInfo = new EiInfo();
     //4.返回禁用成功
   	outInfo.setMsg("禁用成功");
   	return outInfo;
   }

	/**
	 * PC端查询保养项目分类
	 * @Title: queryTree
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * 		node ： 选中节点的id
	 * @param:  @return
	 * @return: EiInfo
	 * 		label : 节点id（分类id）,
	text : 	节点名称（分类名称）
	pId : 父节点id(上级分类id)
	leaf : 是否存在子节点，1=不存在子节点 、 2=存在子节点
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public EiInfo queryTree(EiInfo inInfo) {
		//1 获取参数
		String node = inInfo.getCellStr(EiConstant.queryBlock, 0, "node");

		Map<String, String> queryMap = new HashMap<>(16);
		if("$".equals(node)){
			queryMap.put("parentId", "root");
		} else {
			queryMap.put("parentId", node);
		}
		queryMap.put("dataGroupCode", inInfo.getString("dataGroupCode"));
		//2.查询数据
		List rows = dao.query("DKKP01.queryProjectTypeTree", queryMap);
		//3 增加节点 block 块
		EiInfo outInfo = new EiInfo();
		EiBlock outBlock = outInfo.addBlock(node);
		outBlock.addRows(rows);
		return outInfo;
	}


}
