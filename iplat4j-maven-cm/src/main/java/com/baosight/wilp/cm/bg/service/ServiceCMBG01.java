package com.baosight.wilp.cm.bg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.common.util.CommonUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该页面为合同变更管理
 * 主要包含对合同的变更、冻结、恢复、终止、查看功能
 * 合同模块：初始化查询、合同模块数据查询、合同模块冻结、合同冻结恢复、合同终止
 * <p>1.初始化查询 initLoad
 * <p>2.合同模块数据查询 query
 * <p>3.合同模块冻结 freezr
 * <p>4.合同冻结恢复 fecover
 * <p>5.合同终止 stop
 * @Title: ServiceCMBG01.java
 * @ClassName: ServiceCMBG01
 * @Package：com.baosight.wilp.cm.bg.service
 * @author: zhaow
 * @date: 2021年8月30日 上午9:41:58
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMBG01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同类型定义界面
     * 通过合同类型名称条件查询
     * 回显合同类型编码 、合同类型名称、收付方向、备注
     * @param inInfo
     * contTypeName 合同类型名称
     * @return EiInfo
     * contTypeNum 合同类型编码 
     * contTypeName 合同类型名称
     * payType 收付方向
     * remark 备注
     */
    @Override
	public EiInfo initLoad(EiInfo inInfo) {
        // 调用初始化方法
		return this.query(inInfo);
	}

	/**
     * @Title: query
     * @Description: 合同模块数据查询
	 * <p>1.创建数组并赋值
	 * <p>2.数组转list
	 * <p>3.调用接口创建map
	 * <p>4.调用查询方法
	 * <p>5.获取查询信息总数
	 * <p>6.如果获取查询信息不为空
     * 合同变更界面
     * 通过合同号、合同名称、合同类型、签订日期起、签订日期止条件查询
     * 回显合同号、合同名称、合同类型、签订日期、合同生效日期、合同终止日期、币种、预算、质保期、合同状态、合同管理员、制单人、制单时间、审批人
     * @param inInfo
     * contTypeName 合同类型名称
     * @return EiInfo
     * contTypeNum 合同类型编码 
     * contTypeName 合同类型名称
     * payType 收付方向
     * remark 备注
     */
    @Override
	public EiInfo query(EiInfo inInfo) {
//        //调用初始化方法
//		EiInfo eiInfo = query(inInfo, "CMBG01.query", new ContractBill());
//		// 返回数据
//		return eiInfo;
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		// 1.创建数组并赋值
		String[] parameter = {"contNo","contName","contTypeNum","contSignTime","contSignTime1"};
		// 2.数组转list
		List<String> plist = Arrays.asList(parameter);
		// 3.调用接口创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
//		map.put("contDeptNum",staffByUserId.get("deptNum"));
		// 4.调用查询方法
		List<Map<String, ?>> result = dao.query("CMBG01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 5.获取查询信息总数
		int count = dao.count("CMBG01.query", map);
		// 6.如果获取查询信息不为空
		if(result != null && result.size() > 0){
			// 返回封装方法
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else {
			// 返回参数
			return inInfo;
		}
	}

	
    /**
     * @Title: freezr
     * @Description: 合同模块冻结
	 * <p>1.获取参数
	 * <p>2.实例化map
	 * <p>3.map赋值
	 * <p>4.调用更新方法
	 * <p>5.返回数据
     * 合同变更界面
     * 通过合同主键
     * 将符合条件的合同模块冻结
     * @param EiInfo
     * id 合同主键
     * @return: EiInfo
     */
	public EiInfo freezr(EiInfo inInfo) {
	    // 1.获取参数
		String id = inInfo.getAttr().get("id").toString();
		// 2.实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 3.map赋值
		map.put("id",id);
		// 4.调用更新方法
		dao.update("CMBG01.freezr",map);
		// 5.返回数据
		return inInfo;

	}
	
	/**
     * @Title: fecover
     * @Description: 合同冻结恢复
	 * <p>1.获取参数
	 * <p>2.实例化map
	 * <p>3.map赋值
	 * <p>4.调用更新方法
	 * <p>5.返回参数
     * 合同变更界面
     * 通过合同主键
     * 将符合条件的合同冻结恢复
     * @param EiInfo
     * id 合同主键
     * @return: EiInfo
     */
	public EiInfo fecover(EiInfo inInfo) {
	    // 1.获取参数
		String id = inInfo.getAttr().get("id").toString();
		// 2.实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 3.map赋值
		map.put("id",id);
		// 4.调用更新方法
		dao.update("CMBG01.fecover",map);
		// 5.返回参数
		return inInfo;
	}
	
	/**
     * @Title: stop
     * @Description: 合同终止
	 * <p>1.获取参数
	 * <p>2.实例化map
	 * <p>3.map赋值
	 * <p>4.调用更新方法
	 * <p>5.返回参数
     * 合同变更界面
     * 通过合同主键
     * 将符合条件的信息终止
     * @param EiInfo
     * id 合同主键
     * @return: EiInfo
     */
	public EiInfo stop(EiInfo inInfo) {
	    // 1.获取参数
		String id = inInfo.getAttr().get("id").toString();
		// 2.实例化map
		HashMap<String,String> map = new HashMap<String,String>();
		// 3.map赋值
		map.put("id",id);
		// 4.调用更新方法
		dao.update("CMBG01.stop",map);
		// 5.返回参数
		return inInfo;
	}
}
