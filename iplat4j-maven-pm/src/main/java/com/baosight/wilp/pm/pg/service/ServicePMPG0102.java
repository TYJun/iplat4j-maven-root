package com.baosight.wilp.pm.pg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicePMPG0102 extends ServiceBase {
	// 初始化查询
	public EiInfo initLoad(EiInfo info){
		String id = (String) info.get("stageId");
		HashMap map = new HashMap();
		map.put("id",id);
		List<Map<String,Object>> list = dao.query("PMPG01.queryPmStageMsg", map);
		info.setAttr(list.get(0));
		return info;
	}
}
