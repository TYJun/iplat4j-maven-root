package com.baosight.wilp.cm.tk.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;
/**
 * 该页面为合同条款定义
 * 主要包含对合同条款的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、查询、合同删除
 * <p>1.初始化查询 initLoad
 * <p>2.合同查询 query
 * <p>3.合同删除 delete
 * @Title: ServiceCMTK01.java
 * @ClassName: ServiceCMTK01
 * @Package：com.baosight.wilp.cm.tk.service
 * @author: zhaow
 * @date: 2021年8月30日 下午3:57:20
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMTK01 extends ServiceBase {

    /**
     * @Title: initLoad
     * @Description: 初始化查询
     * 合同条款定义界面
     * 通过合同条款名称条件查询
     * 回显合同条款编码 、合同条款名称、合同条款内容
     * @param EiInfo
     * contTypeName 合同条款名称
     * @return EiInfo
     * contTermNum 合同条款编码
     * contTermName 合同条款名称
     * contTermContent 合同条款内容
     * remark 备注
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}

	/**
     * @Title: query
     * @Description: 查询
     * 合同条款定义界面
     * 通过合同条款名称条件查询
     * 回显合同条款编码 、合同条款名称、合同条款内容
     * @param EiInfo
     * contTypeName 合同条款名称
     * @return EiInfo
     * contTermNum 合同条款编码
     * contTermName 合同条款名称
     * contTermContent 合同条款内容
     * remark 备注
     */
	public EiInfo query(EiInfo inInfo) {
	    // 设置数组并赋值
		String[] parameter = {"contTypeName"};
		// 将数组转化为集合
		List<String> plist = Arrays.asList(parameter);
		// 调用封装方法
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 调用查询方法，并设置分页条件
		List<Map<String, ?>> result = dao.query("CMTK01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 设置信息总数
		int count = dao.count("CMTK01.query", map);
		// 如果查询不为空
		if(result != null && result.size() > 0){
		    // 调用封装方法返回参数
			return CommonUtils.BuildOutEiInfo(inInfo, "result", CommonUtils.createBlockMeta(result.get(0)), result, count);
		} else { 
		    // 返回参数
			return inInfo; 
		}
	}

	/**
     * @Title: delete
     * @Description: 删除
     * 合同条款定义界面
     * 通过合同条款主键
     * 将符合条件的信息删除
     * @param EiInfo
     * id 合同类型主键
     * @return: EiInfo
     */
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		return super.delete(inInfo,"CMTK01.delete");
	}
	
	
}
