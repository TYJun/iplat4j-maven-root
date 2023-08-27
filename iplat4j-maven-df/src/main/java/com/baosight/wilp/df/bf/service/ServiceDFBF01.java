package com.baosight.wilp.df.bf.service;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfConstant;
import com.baosight.wilp.df.common.util.DFUtils;
import org.apache.commons.collections.CollectionUtils;
import java.util.*;
/**
 * 
 * 设备报废管理：初始化查询、设备作废管理查询、删除设备报废信息、校验设备是否可以删除、判断巡检基准是否关联指定设备、判断保养基准是否关联指定设备、调用微服务人员、确定是否报废
 * <p>1.初始化查询 initLoad
 * <p>2.设备档案查询 query
 * <p>3.删除设备报废信息 deleteItem
 * <p>4.校验设备是否可以删除 isEnableDelete
 * <p>5.判断巡检基准是否关联指定设备 diRelationDevice
 * <p>6.判断保养基准是否关联指定设备 dkRelationDevice
 * <p>7.调用微服务人员 queryPerson
 * <p>8.确定是否报废 DetermineScrap
 * 
 * @Title: ServiceDFBF01.java
 * @ClassName: ServiceDFBF01
 * @Package：com.baosight.wilp.df.bf.service
 * @author: liangyongfei
 * @date: 2022年6月24日 上午11:48:52
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFBF01 extends ServiceBase{


	/**
	 * 
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @param info
	 * scrapNo : 报废单号
     * machineName : 设备名称
     * status : 状态
     * scrapWay : 报废方式
     * scrapDateS : 报废日期起
     * scrapDateE : 报废日期止
	 * @return info
	 * scrapNo : 报废单号
     * machineName : 设备名称
     * fixedPlace : 安装地点
     * status : 状态
     * scrapWay : 报废方式
     * scrapDateS : 报废日期
     * scrapDateE : 报废原因
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 调用查询方法
		return query(info);
	}

	/**
	 *
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @param info
	 * scrapNo : 报废单号
	 * machineName : 设备名称
	 * status : 状态
	 * scrapWay : 报废方式
	 * scrapDateS : 报废日期起
	 * scrapDateE : 报废日期止
	 * @return info
	 * scrapNo : 报废单号
	 * machineName : 设备名称
	 * fixedPlace : 安装地点
	 * status : 状态
	 * scrapWay : 报废方式
	 * scrapDateS : 报废日期
	 * scrapDateE : 报废原因
	 */
	@Override
	public EiInfo query(EiInfo info) {
	    // 定义数组
		String[] fieldList = {"scrapNo","machineName","status","scrapWay",
				"scrapDateS","warrantyDateE","scrapDateE"};
		// 调用分页方法构建map
		Map<String, Object> map = CommonUtils.buildParamter(info,"result", Arrays.asList(fieldList));
		// 查询返回list，参数为map
		List<Map<String, Object>> list = dao.query("DFBF01.query", map);
		// 查询返回数量，参数为map
		Integer count = dao.count("DFBF01.count", map);
		// list为空
		if(CollectionUtils.isEmpty(list)){
		    // 返回
		    return info;
		}
		// 调用分页接口
		return CommonUtils.BuildOutEiInfo(info, null, null, list, count);
		
	}
	
  
 
	
    /**
     * @Title: deleteItem 
     * @Description: 删除设备报废信息
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
		    dao.delete("DFBF01.deleteDevicemachine",list);
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
			int count = super.count("DFBF01.isDiRelationDevice", deviceId);
			return count > 0 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断保养基准是否关联指定设备
	 * @Title: dkRelationDevice
	 * @Description: 判断保养基准是否关联指定设备
	 * @param deviceId deviceId ： 设备ID
	 * @return boolean true=关联设备，false=没有关联设备
	 * @throws
	 **/
	private boolean dkRelationDevice (String deviceId){
		try{
			int count = super.count("DFBF01.isDkRelationDevice", deviceId);
			return count > 0 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}
	


	/**
	 * @Title: queryProject
	 * @Description: 调设备档案设备信息
	 * @param inInfo
	 * spotName : 科室地点
	 * @return info
	 * spotName : 科室地点
	 */
	public EiInfo queryProject(EiInfo inInfo) {
		// 调用分页接口构建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "inqu_status", "dept");
		List<Map<String,Object>> list=new ArrayList<>();
		int count =0;
		//获取所有设备信息
		try {
			list = dao.query("DFBF01.queryDeptAndWork", map);
			count = dao.count("DFBF01.queryDeptAndWorkCount", map);
			if(list.size() >0){
				return CommonUtils.BuildOutEiInfo(inInfo, "dept", DFUtils.createBlockMeta(list.get(0)), list, count);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
			return inInfo;
	}




	/**
	 * @Title:DetermineScrap
	 * @Description: 确定报废
	 * 设备报废管理界面
	 * 通过设备主键 id
	 * 将符合条件的信息确定
	 * 状态改为确定
	 * @param inInfo
	 * id 设备主键id
	 * @return: EiInfo
	 * status 设备报废状态
	 */
	public EiInfo DetermineScrap(EiInfo inInfo) {
		// 获取参数
		String id = (String)inInfo.get("id");
		// 实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 获取当前登录用户信息
		// 赋值map
		map.put("id",id);
		// 调用更新方法
		dao.update("DFBF01.examine",map);
		// 返回参数
		return inInfo;

	}
}
