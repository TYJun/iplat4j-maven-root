package com.baosight.wilp.df.rh.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.wilp.df.common.util.DFUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 该页面为设备润滑管理
 * 主要包含对设备润滑信息的页面查询、删除、设备查询、保洁单位查询、负责人查询
 * <p>1.初始化查询 initLoad
 * <p>2.保洁数据查询 query
 * <p>3.保洁数据删除 delete
 * <p>4.设备查询 queryProject
 * <p>5.保洁单位查询 queryDept
 * <p>6.负责人查询 queryPerson
 * @Title: ServiceDFBJ01.java
 * @ClassName: ServiceDFBJ01
 * @Package：com.baosight.wilp.df.bj.service
 * @author: liangyongfei
 * @date: 2022年6月27日 下午1:29:57
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDFRH01 extends ServiceBase {
	/**
	 * @Title: initLoad
	 * @Description: 初始化查询
	 * 设备润滑管理界面
	 * 通过润滑单号、设备名称、安装地点、润滑日期起、润滑日期止条件查询
	 * 回显润滑单号、设备名称、安装地点、负责单位、负责人、润滑日期、作业说明
	 * @param inInfo
	 * lubricateNo 润滑单号
	 * machineName 设备名称
	 * fixedPlace 安装地点
	 * lubricateDateS 保洁日期起
	 * lubricateDateE 保洁日期止
	 * @return EiInfo
	 * id 主键
	 * lubricateNo 润滑单号
	 * machineName 设备名称
	 * fixedPlace 安装地点
	 * lubricateDeptName 负责单位
	 * lubricateManageName 负责人
	 * lubricateDate 润滑日期
	 * remark 作业说明
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}

	/**
	 * @Title: query
	 * @Description: 数据查询
	 * 通过润滑单号、设备名称、安装地点、润滑日期起、润滑日期止条件查询
	 * 回显润滑单号、设备名称、安装地点、负责单位、负责人、润滑日期、作业说明
	 * @param inInfo
	 * lubricateNo 润滑单号
	 * machineName 设备名称
	 * fixedPlace 安装地点
	 * lubricateDateS 保洁日期起
	 * lubricateDateE 保洁日期止
	 * @return EiInfo
	 * id 主键
	 * lubricateNo 润滑单号
	 * machineName 设备名称
	 * fixedPlace 安装地点
	 * lubricateDeptName 负责单位
	 * lubricateManageName 负责人
	 * lubricateDate 润滑日期
	 * remark 作业说明
	 */
	 //回显方法
	@Override
	public EiInfo query(EiInfo inInfo) {
	    // 创建数组并赋值
		String[] parameter = {"lubricateNo","machineName","fixedPlace","lubricateDateS","lubricateDateE"};
		// 数组转list
		List<String> plist = Arrays.asList(parameter);
		// 调用接口创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 调用查询方法
		List<Map<String, ?>> result = dao.query("DFRH01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 获取查询信息总数
		int count = dao.count("DFRH01.query", map);
		// 如果获取查询信息不为空
		if(result != null && result.size() > 0){
		    // 返回封装方法
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else { 
		    // 返回参数
			return inInfo; 
		}
	}
	
	/**
     * @Title: delete
     * @Description: 删除
     * 设备润滑管理界面
     * 通过设备润滑主键
     * 将符合条件的信息删除
     * @param inInfo
     * id 润滑主键
     * @return: EiInfo
     */
	@Override
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		return super.delete(inInfo, "DFRH01.delete");
	}

	/**
	 * @Title: queryProject
	 * @Description: 调设备档案设备信息
	 * @param inInfo
	 * @return info
	 * machineCode : 设备编码
	 * machineName : 设备名称
	 *
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
			if(list.isEmpty()){
				return inInfo;
			}else {
				return CommonUtils.BuildOutEiInfo(inInfo, "dept", DFUtils.createBlockMeta(list.get(0)), list, count);
			}
		}catch (Exception e){
		}
		return inInfo;
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
		Map<String, Object> map = CommonUtils.buildParamter(info, "inqu_status", "clean");
		// 调用远程服务获取改造科室接口
		map.put("datagroupCode",BaseDockingUtils.getUserGroupByWorkNo(null));
		EiInfo clean=BaseDockingUtils.getDeptAllPage(map, "clean");
		clean.setBlockInfoValue("clean", "showCount", "true");
		return  clean;
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


}
