package com.baosight.wilp.cm.xy.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * 该页面为付款协议定义
 * 主要包含对付款协议的新增、删除、编辑、查看功能
 * 合同模块：初始化查询、查询、删除
 * <p>1.初始化查询 initLoad
 * <p>2.查询 query
 * <p>3.删除 delete
 * @Title: ServiceCMXY01.java
 * @ClassName: ServiceCMXY01
 * @Package：com.baosight.wilp.cm.xy.service
 * @author: zhaow
 * @date: 2021年8月30日 下午4:28:41
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录 
 * <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceCMXY01 extends ServiceBase {
    /**
     * @Title: initLoad
     * @Description: 调用查询方法
     * 付款协议定义界面
     * 通过付款协议条件查询
     * 回显付款协议编码 、付款协议名称、备注
     * @param :EiInfo
     * payTypeName 合同协议名称
     * @return EiInfo
     * id 主键
     * payTypeNum 合同协议编码
     * payTypeName 合同协议名称
     * remark 备注
     */
	public EiInfo initLoad(EiInfo inInfo) {
	    // 调用查询方法
		return this.query(inInfo);
	}

	/**
     * @Title: query
     * @Description: 数据回显和加载
     * 付款协议定义界面
     * 通过付款协议条件查询
     * 回显付款协议编码 、付款协议名称、备注
     * @param :EiInfo
     * payTypeName 合同协议名称
     * @return EiInfo
     * id 主键
     * payTypeNum 合同协议编码
     * payTypeName 合同协议名称
     * remark 备注
     */
	public EiInfo query(EiInfo inInfo) {
	    // 设置数组并赋值
		String[] parameter = {"payTypeName"};
		// 将数组转化为集合
		List<String> plist = Arrays.asList(parameter);
		// 调用封装方法
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", plist);
		// 调用查询方法
		List<Map<String, ?>> result = dao.query("CMXY01.query", map,Integer.parseInt(map.get("offset").toString()),Integer.parseInt(map.get("limit").toString()));
		// 设置信息总数
		int count = dao.count("CMXY01.count", map);
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
     * 付款协议定义界面
     * 通过付款协议主键
     * 将符合条件的信息删除
     * @param :EiInfo
     * id 付款协议主键
     * @return: EiInfo
     */
	public EiInfo delete(EiInfo inInfo) {
	    // 调用删除方法
		Map map = (Map)inInfo.getBlock("result").getRows().get(0);
		String payTypeNum = (String) map.get("payTypeNum");
		dao.delete("CMXY0101.delete", payTypeNum);
		return super.delete(inInfo,"CMXY01.delete");
	}
}
