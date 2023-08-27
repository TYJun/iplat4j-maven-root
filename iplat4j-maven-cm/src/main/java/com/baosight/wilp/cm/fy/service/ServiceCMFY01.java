package com.baosight.wilp.cm.fy.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * 该页面为合同费用定义
 * 主要包含对合同费用的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、保存弹窗信息、查询供应商
 * <p>1.初始化查询 initLoad
 * <p>2.数据回显和加载 query
 * <p>3.删除 delete
 * 
 * @Title: ServiceCMFY01.java
 * @ClassName: ServiceCMFY01
 * @Package：com.baosight.wilp.cm.fy.service
 * @author: zhaow
 * @date: 2021年8月30日 下午2:43:28
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMFY01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同费用定义界面
     * 通过合同费用名称条件查询
     * 回显合同费用编码 、合同费用名称、备注
     * @param EiInfo
     * contCostName 合同费用名称
     * @return EiInfo
     * id 主键
     * contCostNum 合同费用编码
     * contCostName 合同费用名称
     * remark 备注
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}

	/**
     * @Title: query
     * @Description: 数据回显和加载
	 * <p>1.调用封装方法创建map
	 * <p>2.调用查询方法
	 * <p>3.如果查询结果不为空
	 * <p>4.返回封装方法
     * 合同费用定义界面
     * 通过合同费用名称条件查询
     * 回显合同费用编码 、合同费用名称、备注
     * @param EiInfo
     * contCostName 合同费用名称
     * @return EiInfo
     * id 主键
     * contCostNum 合同费用编码
     * contCostName 合同费用名称
     * remark 备注
     */
	public EiInfo query(EiInfo inInfo) {
	    // 创建数组并赋值
		String[] parameter = {"contCostName"};
		// 将数组转化为集合
		List<String> plist = Arrays.asList(parameter);
		// 1.调用封装方法创建map
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 2.调用查询方法
		List<Map<String, ?>> result = dao.query("CMFY01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 查询方法的总数
		int count = dao.count("CMFY01.query", map);
		// 3.如果查询结果不为空
		if(result != null && result.size() > 0){
			// 4.返回封装方法
		    return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else { 
		    // 返回结果
			return inInfo; 
		}
	}

	/**
     * @Title: delete
     * @Description: 删除
     * 合同费用定义界面
     * 通过合同费用主键
     * 将符合条件的信息删除
     * @param EiInfo
     * id 合同费用主键
     * @return: EiInfo
     */
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		return super.delete(inInfo, "CMFY01.delete");
	}
}
