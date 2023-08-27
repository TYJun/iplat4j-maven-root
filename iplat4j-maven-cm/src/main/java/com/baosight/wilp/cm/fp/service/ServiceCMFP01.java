package com.baosight.wilp.cm.fp.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCMFP01 extends ServiceBase {
	/**
	 * 合同发票初始化方法
	 * @author zhaowei
	 * @date 2022/2/11 13:58
	 * @param eiInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo eiInfo) {
		return this.query(eiInfo);
	}

	/**
	 * 合同发票查询方法
	 * @author zhaowei
	 * @date 2022/1/20 17:41
	 * @param eiInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo query(EiInfo eiInfo) {
		int offset, limit;
		// 判断是否分页
		if (eiInfo.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) eiInfo.getBlocks().get("result");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		Map<String, Object> map = new HashMap();
		map.put("invoiceAutoNo", (String) eiInfo.getAttr().get("invoiceAutoNo"));
		map.put("invoiceNo", (String) eiInfo.getAttr().get("invoiceNo"));
		map.put("invoiceStatus", (String) eiInfo.getAttr().get("invoiceStatus"));
		List<Map<String, Object>> query = dao.query("CMFP01.query", map, offset, limit);
		int count = dao.count("CMFP01.query", map);
		eiInfo.setRows("result", query);
		// 处理分页
		EiBlock result = (EiBlock) eiInfo.getBlocks().get("result");
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
		return eiInfo;
	}
}
