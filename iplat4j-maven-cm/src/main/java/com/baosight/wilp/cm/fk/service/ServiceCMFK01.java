package com.baosight.wilp.cm.fk.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCMFK01 extends ServiceBase {
	/**
	 * 合同付款管理初始化方法
	 * @author zhaowei
	 * @date 2022/2/11 13:57
	 * @param eiInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo eiInfo) {
		return this.query(eiInfo);
	}

	/**
	 * 合同付款管理查询
	 * @author zhaowei
	 * @date 2022/2/11 13:57
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo query(EiInfo info) {
		int offset, limit;
		// 判断是否分页
		if (info.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) info.getBlocks().get("result");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		Map<String, Object> map = new HashMap();
		map.put("paymentNo", (String) info.getAttr().get("paymentNo"));
		map.put("paymentStatus", (String) info.getAttr().get("paymentStatus"));
		List<Map<String, Object>> query = dao.query("CMFK01.query", map, offset, limit);
		int count = dao.count("CMFK01.query", map);
		info.setRows("result", query);
		// 处理分页
		EiBlock result = (EiBlock) info.getBlocks().get("result");
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
		return info;
	}
}
