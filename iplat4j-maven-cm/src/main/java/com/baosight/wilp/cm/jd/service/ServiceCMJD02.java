package com.baosight.wilp.cm.jd.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCMJD02 extends ServiceBase {
	/**
	 * 合同进度初始化方法
	 *
	 * @param info
	 * @return
	 */
	public EiInfo initLoad(EiInfo info) {
		return this.query(info);
	}

	/**
	 * 合同进度查询方法
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
			String scheduleAutoNo = block.getCellStr(0, "scheduleAutoNo");
			String scheduleName = block.getCellStr(0, "scheduleName");
			map.put("scheduleAutoNo", scheduleAutoNo);
			map.put("scheduleName", scheduleName);
		}
		List<Map<String, Object>> query = dao.query("CMJD02.query", map);
		int count = dao.count("CMJD02.query", map);
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
	 * 合同进度删除方法
	 *
	 * @param info
	 * @return
	 */
	public EiInfo delete(EiInfo info) {
		Map<String, Object> map = new HashMap();
		String scheduleId = (String) info.getAttr().get("scheduleId");
		map.put("scheduleId", scheduleId);
		dao.delete("CMJD02.delete",map);
		return info;
	}
}
