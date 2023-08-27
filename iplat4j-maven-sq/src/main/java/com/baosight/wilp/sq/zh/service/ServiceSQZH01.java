package com.baosight.wilp.sq.zh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 综合查询逻辑处理，页面初始化方法，查询方法
 * <p>页面初始化方法initload
 * <p>查询方法query
 *
 * @Title: ServiceSQZH01.java
 * @ClassName: ServiceSQZH01
 * @Package：com.baosight.wilp.sq.zh.service
 * @author: zhangjiaqian
 * @date: 2021年8月4日 上午9:55:10
 * @version: V1.0
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @History:// 历史修改记录  <author>  // 修改人
 * <time>    // 修改时间
 * <version> // 版本
 * <desc>    // 描述修改内容
 */
public class ServiceSQZH01 extends ServiceBase {

	/**
	 * 满意度综合查询初始化方法
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/4/4 19:45
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}


	/**
	 * 满意度综合查询方法
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/4/4 19:45
	 */
	public EiInfo query(EiInfo inInfo) {
		// 初始化页面总数
		int offset, limit;
		// 判断是否分页
		if (inInfo.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		Map<String, Object> map = new HashMap<>();
		if (inInfo.getBlocks().get("inqu_status") != null) {
			EiBlock eiBlock = (EiBlock) inInfo.getBlocks().get("inqu_status");
			if (eiBlock.getRows().get(0) != null) {
				map = (Map) eiBlock.getRows().get(0);
			}
		}
		List querySqManageScoreList = dao.query("SQZH01.query", map, offset, limit);
		int count = dao.count("SQZH01.query", map);
		inInfo.setRows("result", querySqManageScoreList);
		// 处理分页
		EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
		result.setAttr(new HashMap<>());
		if(result.getAttr().isEmpty()){
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			result.setAttr(rAttr);
		} else {
			result.getAttr().put("count", count);
		}
		return inInfo;
	}


	/**
	 * 浮点数相除，保留两位小数
	 *
	 * @param a     被除数
	 * @param b     除数
	 * @param scale 保留位数
	 * @return
	 * @Title: divide
	 * @return: double
	 */
	public static double divide(double a, double b, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(a));
		BigDecimal bd2 = new BigDecimal(Double.toString(b));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
