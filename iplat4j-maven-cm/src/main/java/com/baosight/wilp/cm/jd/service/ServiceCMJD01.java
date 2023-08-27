package com.baosight.wilp.cm.jd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCMJD01 extends ServiceBase {

	/**
	 * 初始化方法
	 *
	 * @param info
	 * @return
	 */
	public EiInfo initLoad(EiInfo info) {
		return this.query(info);
	}

	/**
	 * 查询合同节点信息
	 *
	 * @param info
	 * @return
	 */
	public EiInfo query(EiInfo info) {
		int offset, limit;
		// 判断是否分页
		if (info.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) info.getBlocks().get("result");
			offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
			limit = result.getAttr().get("limit") == null ? 10 : (Integer) result.getAttr().get("limit");
		}
		Map<String, Object> map = new HashMap<>();
		if (info.getBlocks().containsKey("inqu_status")) {
			EiBlock block = info.getBlock("inqu_status");
			String nodeAutoNo = block.getCellStr(0, "nodeAutoNo");
			String nodeName = block.getCellStr(0, "nodeName");
			map.put("nodeAutoNo", nodeAutoNo);
			map.put("nodeName", nodeName);
		}
		List<Map<String, Object>> query = dao.query("CMJD01.query", map, offset, limit);
		int count = dao.count("CMJD01.query", map);
		info.setRows("result", query);
		// 处理分页
		EiBlock result = (EiBlock) info.getBlocks().get("result");
		result.setAttr(new HashMap<>());
		if (result.getAttr().isEmpty()) {
			Map<String, Object> rAttr = new HashMap<>();
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

	/**
	 * 合同节点删除方法
	 *
	 * @param info
	 * @return
	 */
	public EiInfo delete(EiInfo info) {
		String nodeId = (String) info.getAttr().get("nodeId");
		dao.delete("CMJD01.delete", nodeId);
		return info;
	}
}
