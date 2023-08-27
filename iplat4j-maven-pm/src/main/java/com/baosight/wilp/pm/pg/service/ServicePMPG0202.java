package com.baosight.wilp.pm.pg.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.util.EasyIplat4jUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicePMPG0202 extends ServiceBase {
	// 初始化查询
	public EiInfo initLoad(EiInfo info){
		String id = (String) info.get("typeId");
		HashMap map = new HashMap();
		map.put("id",id);
		List<Map<String,Object>> list = dao.query("PMPG02.queryPmTypeMsg", map);
		info.setAttr(list.get(0));
		String[] str = {"stageCode","stageName","stageRemark"};
		info.addRows("result",EasyIplat4jUtil.listToList(list,str));
		return info;
	}
}
