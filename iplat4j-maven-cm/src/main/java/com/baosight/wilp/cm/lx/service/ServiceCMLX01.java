package com.baosight.wilp.cm.lx.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
/**
 * 该页面为合同类型定义
 * 主要包含对合同类型的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、合同查询、合同删除
 * <p>1.初始化查询 initLoad
 * <p>2.合同查看 query
 * <p>3.合同删除 delete
 * @Title: ServiceCMLX01.java
 * @ClassName: ServiceCMLX01
 * @Package：com.baosight.wilp.cm.lx.service
 * @author: zhaow
 * @date: 2021年8月30日 下午3:20:37
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMLX01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同类型定义界面
     * 通过合同类型名称条件查询
     * 回显合同类型编码 、合同类型名称、收付方向、备注
     * @param EiInfo
     * contTypeName 合同类型名称
     * @return EiInfo
     * contTypeNum 合同类型编码 
     * contTypeName 合同类型名称
     * payType 收付方向
     * remark 备注
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}

	/**
	 * @Title: query
     * @Description: 查看
	 * <p>1.设置数组并赋值
	 * <p>2.调用封装方法
	 * <p>3.调用查询方法，并设置分页条件
	 * <p>4.如果查询不为空
	 * <p>5.调用封装方法返回参数
     * 合同类型定义界面
     * 通过合同类型名称条件查询
     * 回显合同类型编码 、合同类型名称、收付方向、备注
     * @param EiInfo
     * contTypeName 合同类型名称
     * @return EiInfo
     * contTypeNum 合同类型编码 
     * contTypeName 合同类型名称
     * payType 收付方向
     * remark 备注
	 */
	public EiInfo query(EiInfo inInfo) {
	    // 1.设置数组并赋值
		String[] parameter = {"contTypeName"};
		// 将数组转化为集合
		List<String> plist = Arrays.asList(parameter);
		// 2.调用封装方法
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 3.调用查询方法，并设置分页条件
		List<Map<String, ?>> result = dao.query("CMLX01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 设置信息总数
		int count = dao.count("CMLX01.query", map);
		// 4.如果查询不为空
		if(result != null && result.size() > 0){
		    // 5.调用封装方法返回参数
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else {
		    // 返回参数
			return inInfo; 
		}
	}

	/**
     * @Title: delete
     * @Description: 删除
     * 合同类型定义界面
     * 通过合同类型主键
     * 将符合条件的信息删除
     * @param EiInfo
     * id 合同类型主键
     * @return: EiInfo
     */
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		return super.delete(inInfo, "CMLX01.delete");
	}

}
