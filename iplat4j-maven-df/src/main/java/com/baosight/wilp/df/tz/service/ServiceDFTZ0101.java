package com.baosight.wilp.df.tz.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.domain.DfConstant;
import com.baosight.wilp.df.common.util.DFUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 * 设备台账：初始化查询、查询巡检基准、查询巡检实绩
 * <p>1.初始化查询 initLoad
 * <p>2.查询巡检基准 queryTableSchemeInspection
 * <p>3.查询巡检实绩 queryTableTaskInspection
 * 
 * @Title: ServiceDFTZ0101.java
 * @ClassName: ServiceDFTZ0101
 * @Package：com.baosight.wilp.df.tz.service
 * @author: zhaow
 * @date: 2021年8月10日 下午9:57:19
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFTZ0101 extends ServiceBase{
	/**
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * @param info
	 * id : 设备id
	 * @return info
	 * machineTypeId : 设备类型
     * machineCode : 设备编码
     * machineName : 设备名称
     * status : 设备状态
     * makerBrand : 品牌
     * models : 规格型号
     * fixedTime : 安装日期
     * fixedPlace : 安装地点
     * lastMaintainTime : 上次保养日期 
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // map设置machineId
        String machineId = info.getString("machineId");
		// 获取设备基础信息
		List<Map<String, String>> list = dao.query("DFTZ0101.initDeviceForm", machineId);
		info.setAttr(list.get(0));
        // 返回
        return info;
	}
	
	/**
	 * @Title: queryDiScheme
	 * @Description: 查询巡检基准
	 * @param info
	 * id : 设备id
	 * @return info
	 * schemeCode : 计划代码
	 * schemeName : 计划名称
	 * jobContent : 作业说明
	 * statusName : 状态
	 * createTime : 创建时间
	 * createMan : 创建人
	 * modifyTime : 修改时间
	 * modifyMan : 修改人
	 */
	public EiInfo queryDiScheme(EiInfo info) {
		try{
			//存在巡检模块
			if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DI)){
				//参数处理
				Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "diScheme");
				map.put("machineId",info.getString("machineId"));
				//数据查询
				List<Map<String, Object>> list = dao.query("DFTZ0101.queryDiSchemeList", map);
				int count = dao.count("DFTZ0101.queryDiSchemeCount", map);
				return CommonUtils.BuildOutEiInfo(info,"diScheme",null,list,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * @Title: queryDiTask
	 * @Description: 查询巡检任务
	 * @param info
	 * id : 设备id
	 * @return info
	 * STATUS : 状态
	 * taskCode : 任务单号
	 * schemeName : 任务名称
	 * machineCode : 设备编码
	 * machineName : 设备名称
	 * jobContent : 作业说明
	 * departName : 责任单位科室
	 * managerName : 责任人
	 * createTime : 创建时间
	 * finishTime : 完成时间
	 */
	public EiInfo queryDiTask(EiInfo info) {
		try{
			//存在巡检模块
			if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DI)){
				//参数处理
				Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "diTask");
				map.put("machineId",info.getString("machineId"));
				//数据查询
				List<Map<String, Object>> list = dao.query("DFTZ0101.queryDiTaskList", map);
				int count = dao.count("DFTZ0101.queryDiTaskCount", map);
				return CommonUtils.BuildOutEiInfo(info,"diTask",null,list,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
    }

	/**
	 * @Title: queryDkScheme
	 * @Description: 查询保养基准
	 * @param info
	 * id : 设备id
	 * @return info
	 * schemeCode : 计划代码
	 * schemeName : 计划名称
	 * jobContent : 作业说明
	 * statusName : 状态
	 * createTime : 创建时间
	 * createMan : 创建人
	 * modifyTime : 修改时间
	 * modifyMan : 修改人
	 */
	public EiInfo queryDkScheme(EiInfo info) {
		try{
			//存在巡检模块
			if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
				//参数处理
				Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dkScheme");
				map.put("machineId",info.getString("machineId"));
				//数据查询
				List<Map<String, Object>> list = dao.query("DFTZ0101.queryDkSchemeList", map);
				int count = dao.count("DFTZ0101.queryDkSchemeCount", map);
				return CommonUtils.BuildOutEiInfo(info,"dkScheme",null,list,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * @Title: queryDkTask
	 * @Description: 查询保养任务
	 * @param info
	 * id : 设备id
	 * @return info
	 * STATUS : 状态
	 * taskCode : 任务单号
	 * schemeName : 任务名称
	 * machineCode : 设备编码
	 * machineName : 设备名称
	 * jobContent : 作业说明
	 * departName : 责任单位科室
	 * managerName : 责任人
	 * createTime : 创建时间
	 * finishTime : 完成时间
	 */
	public EiInfo queryDkTask(EiInfo info) {
		try{
			//存在保养模块
			if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
				//参数处理
				Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "dkTask");
				map.put("machineId",info.getString("machineId"));
				//数据查询
				List<Map<String, Object>> list = dao.query("DFTZ0101.queryDkTaskList", map);
				int count = dao.count("DFTZ0101.queryDkTaskCount", map);
				return CommonUtils.BuildOutEiInfo(info,"dkTask",null,list,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * @Title: queryMtTask
	 * @Description: 查询维修任务
	 * @param info info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/
	public EiInfo queryMtTask(EiInfo info) {
		try{
			//存在维修模块
			if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DK)){
				//参数处理
				Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "mtTask");
				map.put("machineCode",info.getString("machineCode"));
				//数据查询
				List<Map<String, Object>> list = dao.query("DFTZ0101.queryMtTaskList", map);
				int count = dao.count("DFTZ0101.queryMtTaskCount", map);
				return CommonUtils.BuildOutEiInfo(info,"mtTask",null,list,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}


	/**
	 * @Title: queryBjTask
	 * @Description: 查询保洁信息
	 * @param info info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/

	public EiInfo queryBjTask(EiInfo info) {
		try{
			if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DF)) {
				Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "bjTask");
				map.put("machineCode", info.getString("machineCode"));
				List<Map<String, Object>> list = dao.query("DFTZ0101.queryBjTaskList", map);
				int count = dao.count("DFTZ0101.queryBjTaskCount", map);
				return CommonUtils.BuildOutEiInfo(info,"bjTask", null,list,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
         return info;
	}

	/**
	 * @Title: queryRhTask
	 * @Description: 查询润滑信息
	 * @param info info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @throws
	 **/

	public EiInfo queryRhTask(EiInfo info) {
		try{
			if(DFUtils.isExistModule(DfConstant.MODULE_CODE_DF)){
				//参数处理
				Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "rhTask");
				map.put("machineCode",info.getString("machineCode"));
				//数据查询
				List<Map<String, Object>> list = dao.query("DFTZ0101.queryRhTaskList", map);
				int count = dao.count("DFTZ0101.queryRhTaskCount", map);
				return CommonUtils.BuildOutEiInfo(info,"rhTask",null,list,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
}
