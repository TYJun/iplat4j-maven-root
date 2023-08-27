package com.baosight.wilp.im.jz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.im.common.util.DeviceEiUtil;
import com.baosight.wilp.im.common.util.DeviceUtil;

/**
 * 
 * 巡检基准管理：基准初始化查询、基准查询、基准删除、基准启用、基准禁用
 * <p>1.基准初始化查询  initLoad
 * <p>2.基准查询   query
 * <p>3.基准删除   deleteScheme
 * <p>4.基准启用   startScheme
 * <p>5.基准禁用   stopScheme
 * 
 * @Title: ServiceDIJZ01.java
 * @ClassName: ServiceDIJZ01
 * @Package：com.baosight.wilp.di.jz.service
 * @author: LENOVO
 * @date: 2021年8月10日 下午3:34:12
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
@SuppressWarnings("all")
public class ServiceIMJZ01 extends ServiceBase{

    /**
     * 基准初始化查询
     * &lt;p&gt;Title: initLoad&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>该方法调用下面query方法
     * @param inInfo
     * 无参
     * @return
     * @see com.baosight.iplat4j.core.service.impl.ServiceBase#initLoad(com.baosight.iplat4j.core.ei.EiInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {

		return this.query(inInfo);  
	}
	
	/**
     * 基准查询
     * &lt;p&gt;Title: query&lt;/p&gt;  
     * &lt;p&gt;Description: &lt;/p&gt;
     * <p>1.获取参数
     * <p>2.将参数封装并获取分页，执行查询
     * <p>3.判断是否与设备包关联
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
    public EiInfo query(EiInfo inInfo) {
        String[] param = { "schemeCode", "schemeName", "schemeDept", "objId", "objName", "status" };
        //将参数封装并获取分页，执行查询
        Map<String, Object> map = DeviceEiUtil.buildParam(inInfo, Arrays.asList(param), "result");
        List<Map<String, String>> list = dao.query("IMJZ01.queryScheme", map);
        
        //判断是否与设备包关联
        Map param2 = new HashMap();
        if(null != list || list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map2 = list.get(i);
                String packetID = map2.get("packetID");
                String machineID = map2.get("machineID");
                param2.put("packetID", packetID);
                param2.put("machineID", machineID);
                if(null != packetID) {
                    List<Map<String,String>> query = dao.query("IMJZ01.queryPacket", param2);
                    for (int j = 0; j < query.size(); j++) {
                        Map<String, String> map3 = query.get(j);
                        String deviceId = map3.get("device_id");
                        if(machineID.equals(deviceId)) {
                            map2.put("spotName", map3.get("fixed_place"));
                            map2.put("machineCode", map3.get("machine_code"));
                            map2.put("machineName", map3.get("machine_name"));
                        }
                    }
                }
            }
        }
        int count = dao.count("IMJZ01.countScheme", map);
        return DeviceEiUtil.buildResult(inInfo, list, count, "result");
    }
    
    /**
     * 删除基准
     * @Title: deleteScheme
     * @Description: 
     * <p>1.删除基准时判断该基准是否生成任务,生成任务则不能删除
     * <p>2.删除基准
     * <p>3.循环删除执行周期、基准项目、执行人
     * @param:  @param inInfo
     * id  基准id
     * @return: EiInfo  
     * 删除成功否则失败并执行回滚操作
     * @throws
     */
    public EiInfo deleteScheme(EiInfo inInfo) {
    	List<String> ids = (List<String>) inInfo.get("list");
    	//删除基准时判断该基准是否生成任务,生成任务则不能删除
    	List<Map<String,String>> isTask=dao.query("IMJZ01.isTask", ids);
    	if(!isTask.isEmpty()) {
    	    inInfo.setMsg("该基准已生成任务,不能删除");
            return inInfo;
    	}
    	//删除基准
    	dao.delete("IMJZ01.deleteScheme", ids);
    	//循环删除执行周期、基准项目、执行人
    	for (String id : ids) {
			dao.delete("IMJZ01.deleteCycle", id);//删除执行周期
			dao.delete("IMJZ01.deleteItem", id);//删除基准项目
			dao.delete("IMJZ01.deleteExman", id);//删除执行人
		}
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
    	//循环将基准修改为启用状态
    	list.forEach(schemeID -> {
    		Map<String, Object> param = new HashMap<>();
    		param.put("status", 1);
    		param.put("schemeID", schemeID);
    		dao.update("IMJZ01.updateScheme", param);
    	});
    	EiInfo outInfo = new EiInfo();
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
    	//循环将基准修改为禁用状态
    	list.forEach(schemeID -> {
    		Map<String, Object> param = new HashMap<>();
    		param.put("status", -1);
    		param.put("schemeID", schemeID);
    		dao.update("IMJZ01.updateScheme", param);
    	});
    	EiInfo outInfo = new EiInfo();
    	outInfo.setMsg("禁用成功");
    	return outInfo;
    }
	
}
