package com.baosight.wilp.ci.sq.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.common.util.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 物资选择页面
 * <p>1.初始化查询 initLoad
 * <p>2.页面查询 query
 *
 * @Title: ServiceCISQ010101.java
 * @ClassName: ServiceCISQ010101
 * @Package：com.baosight.wilp.md.sq.service
 * @author: gcc
 * @date: 2022年3月5日 下午1:20:19
 * @version: V1.0
 * @Copyright: 2022 www.bonawise.com Inc. All rights reserved.
 */
public class ServiceCISQ010101 extends ServiceBase {
	
	/**
	 * 页面加载
	 * <p>Title: initLoad</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * @return   
	 * @see ServiceBase#initLoad(EiInfo)
	 */
	@Override
    public EiInfo initLoad(EiInfo inInfo) {
        return query(inInfo);
    }
	
	/**
	 * 页面查询
	 * <p>Title: query</p>   
	 * <p>Description: </p>   
	 * @param inInfo
	 * 		matName:物资名称
	 * 		matTypeNum:物资分类编码
	 * @return inInfo
	 * @see ServiceBase#query(EiInfo)
	 */
	@Override
    public EiInfo query(EiInfo inInfo) {
		String[] param = {"matName",""};
		//将取参数封装包含分页
		Map<String, Object> map = CommonUtils.buildParamter(inInfo, "result", Arrays.asList(param));
		List<Map<String, String>> list = dao.query("CISQ01.queryMat", map);
		int count = dao.count("CISQ01.countMat", map);
		return CommonUtils.BuildOutEiInfo(inInfo, null, null, list, count);
    }

}
