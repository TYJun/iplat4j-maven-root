package com.baosight.wilp.df.da.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 设备档案：初始化查询、设备档案查看
 * <p>1.初始化查询 initLoad
 * <p>2.设备档案查看 detail
 * 
 * @Title: ServiceDFDA0102.java
 * @ClassName: ServiceDFDA0102
 * @Package：com.baosight.wilp.df.da.service
 * @author: zhaow
 * @date: 2021年8月10日 上午10:48:30
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History://历史修改记录
 * <author>// 修改人
 * <time> // 修改时间
 * <version>//版本
 * <desc>   // 描述修改内容
 */
public class ServiceDFDA0102 extends ServiceBase{
    
    /**
     * 
     * @Title: initLoad
     * @Description: 页面初始化 
     * @param info
     * id : 设备档案id
     * @return info
     * id : 设备档案id
     * machineCode : 设备编码
     * machineName : 设备名称
     * models : 规格型号
     * machineTypeId : 设备分类编号
     * status : 设备状态
     * needScan : 是否扫二维码
     * buyTime : 购买日期
     * fixedTime : 安装日期
     * useTime : 使用日期
     * fixedPlace :安装地点
     * building : 楼
     * floor : 层
     * spotId : 安装地点id
     * spotCode : 安装地点编码
     * spotName : 安装地点
     * warrantyDate : 质保到期日
     * makerBrand : 品牌
     * supplierName : 供应商
     * supplierId : 供应商id
     * manufacturerName : 生产单位
     * maintUnit : 保养单位
     * lastMaintainTime : 上次保养日期
     * maintainRound : 保养周期
     * managerManId : 管理员id
     * managerMan : 管理员
     * managerDepartId : 管理科室id
     * useDeaprtId : 使用科室id
     * matNum : 物资编码
     * matName : 物资名称
     * outFactoryNo : 出厂编号
     * useLimit : 折旧年限
     * useFor : 用途
     * machineFolderId : 档案盒号
     * buyMode : 采购方式
     * assetPrice : 资产价格
     * memo : 备注
     * managerDepartName : 管理科室
     * useDeaprtName : 使用科室
     */
	@Override
	public EiInfo initLoad(EiInfo info) {
	    // 获取info中的id
		String id = (String)info.getAttr().get("id");
		// 设置info中的id
		info.set("id",id);
		// 调用设备档案查看方法
        return this.detail(info);
	}
	/**
	 * 
	 * @Title: edit
	 * @Description: 设备档案查看
	 * @param info
	 * id : 设备档案id
	 * @return info
	 * id : 设备档案id
     * machineCode : 设备编码
     * machineName : 设备名称
     * models : 规格型号
     * machineTypeId : 设备分类编号
     * status : 设备状态
     * needScan : 是否扫二维码
     * buyTime : 购买日期
     * fixedTime : 安装日期
     * useTime : 使用日期
     * fixedPlace :安装地点
     * building : 楼
     * floor : 层
     * spotId : 安装地点id
     * spotCode : 安装地点编码
     * spotName : 安装地点
     * warrantyDate : 质保到期日
     * makerBrand : 品牌
     * supplierName : 供应商
     * supplierId : 供应商id
     * manufacturerName : 生产单位
     * maintUnit : 保养单位
     * lastMaintainTime : 上次保养日期
     * maintainRound : 保养周期
     * managerManId : 管理员id
     * managerMan : 管理员
     * managerDepartId : 管理科室id
     * useDeaprtId : 使用科室id
     * matNum : 物资编码
     * matName : 物资名称
     * outFactoryNo : 出厂编号
     * useLimit : 折旧年限
     * useFor : 用途
     * machineFolderId : 档案盒号
     * buyMode : 采购方式
     * assetPrice : 资产价格
     * memo : 备注
     * managerDepartName : 管理科室
     * useDeaprtName : 使用科室
	 */
	public EiInfo detail(EiInfo info) {
	    // 获取info的id
		String id = (String)info.get("id");
		// 查询设备档案信息
		List<Map<String, String>> list = dao.query("DFDA01.queryId",id);
        // 如果集合为空
		if(CollectionUtils.isEmpty(list)) {
            // 返回
		    return info;
        }
		// 设置info的attr
		info.setAttr(list.get(0));
		// 获取设备分类id
		String moduleId = list.get(0).get("machineTypeId");
		// 实例化map
		Map<String, String> map = new HashMap<>();
        // map赋值moduleId
		map.put("moduleId", moduleId);
        // 查询参数值
		List<Map<String,String>> list2 = dao.query("DFFL10.query",map);
        // info添加结果
		info.addRows("result", list2);
		// 返回
		return info;
	}
}
