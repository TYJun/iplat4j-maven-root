
package com.baosight.wilp.dm.au.service;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.wilp.common.util.MaintainUtil;
import com.baosight.wilp.common.util.PrUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * TODO(宿舍信息管理-DMAU01，对申请住宿的人员进行审批)
 *
 *     1.初始化界面，调用query方法，query查询结果
 *     2.查询操作：宿舍入住审核人员
 *     3.通过页面点击不用按钮(拒绝或者通过)调用DMHM01中updateStatus更新申请人的审批状态
 *     
 * @Title: ServiceDMAU01.java
 * @ClassName: ServiceDMAU01
 * @Package：com.baosight.wilp.dm.au.service
 * @author: 辉
 * @date: 2021年11月23日 下午1:23:15
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceDMAU01 extends ServiceBase{
	
    /**
     * TODO(初始化界面，调用query方法，query查询结果)
     * @title initLoad
     * @param resquest 请求入参 {}
     * @return query(inInfo)
     */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		inInfo.set("ifReview", "-2");
		/**1.获取数据：employeeId-工号，manName-姓名，departmentNo-部门科室，employmentNature-用工性质，ifReview-是否通过审核*/
		String[] param = {"employeeId", "manName", "departmentName", "employmentNature","ifReview"};
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		/**2.查询入住人员信息表*/
		List<Map<String, Object>> list = dao.query("dMAU01.query",map,Integer.parseInt((String)map.get("offset")), Integer.parseInt((String)map.get("limit")));
		//统计查询结果
		int count = super.count("dMAU01.count",map);
		/**3.筛选掉数据为空的数据*/
		if(list != null && list.size() > 0){
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			//返回
			return inInfo;
		}
	}

	/**
	 * TODO(查询操作：宿舍入住审核人员)
	 * 1.获取数据：employeeId-工号，manName-姓名，departmentNo-部门科室，employmentNature-用工性质，ifReview-是否通过审核
	 * 2.查询入住人员信息表
	 * 3.筛选掉数据为空的数据
	 * @title query
	 * @param resquest 请求入参 {employeeId-工号，manName-姓名，departmentNo-部门科室，employmentNature-用工性质，ifReview-是否通过审核}
	 * @return inInfo
	 */
    @SuppressWarnings("unchecked")
	@Override
	public EiInfo query(EiInfo inInfo) {
		inInfo.set("ifReview", "-2");
		/**1.获取数据：employeeId-工号，manName-姓名，departmentNo-部门科室，employmentNature-用工性质，ifReview-是否通过审核*/
		String[] param = {"employeeId", "manName", "departmentNo", "employmentNature","ifReview"};
		List<String> plist = Arrays.asList(param);
		Map<String, Object> map = MaintainUtil.changeToMap(inInfo, plist);
		/**2.查询入住人员信息表*/
		List<Map<String, Object>> list = dao.query("dMAU01.query",map,Integer.parseInt((String)map.get("offset")), Integer.parseInt((String)map.get("limit")));
		//统计查询结果
		int count = super.count("dMAU01.count",map);
		/**3.筛选掉数据为空的数据*/
		if(list != null && list.size() > 0){
			return PrUtils.BuildOutEiInfo(inInfo, "result", PrUtils.createBlockMeta(list.get(0)), list, count);
		} else {
			//返回
			return inInfo;
		}

	}
    /**
     * TODO(通过页面点击不用按钮(拒绝或者通过)调用DMHM01中updateStatus更新申请人的审批状态)
     * @title updateStatus
     * @param resquest 请求入参 {inInfo}
     * @return outInfo
     */
    // updateStatus
	public EiInfo updateStatus(EiInfo inInfo) {
	    //调用类DMHM01-宿舍信息管理 
        inInfo.set(EiConstant.serviceName, "DMHM01");
        //调用DMHM01中的updateStatus方法
    	inInfo.set(EiConstant.methodName, "updateStatus");
    	//call方法调用不主动设置事物方式
        EiInfo outInfo =XLocalManager.call(inInfo);
        //返回
        return outInfo;
	}
	
	

}

