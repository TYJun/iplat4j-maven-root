package com.baosight.iplat4j.eu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.service.impl.ServiceEPBase;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.iplat4j.utils.DatagroupUtil;

public class ServiceEU99 extends ServiceEPBase {
	
	public EiInfo initLoad(EiInfo inInfo) {
		return inInfo;
	}


	public EiInfo setDataGroup(EiInfo inInfo) {
		Map attr = inInfo.getAttr();
		String loginName = UserSession.getLoginName();
		String code = (String) attr.get("code");
		String name = (String) attr.get("name");
		UserSession.setOutSessionProperty("datagroupCode",code);
		Map param = new HashMap();
		param.put("userCode", loginName);
		String platSchema = PlatApplicationContext.getProperty("platSchema");
		param.put("platSchema", platSchema);
		List list = dao.query("EU99.query", param);
		if(list.size()>0) {
			Map data = (Map) list.get(0);
			data.put("lastLoginTime", new Date());
			data.put("lastDataGroupCode", code);
			data.put("lastDataGroupName", name);
		
			dao.update("EU99.update", data);
		} else {
			Map data = new HashMap();
			data.put("id", UUID.randomUUID().toString());
			data.put("userCode", loginName);
			data.put("lastLoginTime", new Date());
			data.put("lastDataGroupCode", code);
			data.put("lastDataGroupName", name);
			data.put("platSchema", platSchema);
			dao.insert("EU99.insert", data);
		}
		return inInfo;
	}
	
	public EiInfo getLastDataGroup(EiInfo inInfo) {
		String loginName = UserSession.getLoginName();
		String platSchema = PlatApplicationContext.getProperty("platSchema");
		Map param = new HashMap();
		param.put("platSchema", platSchema);
		param.put("userCode", loginName);
		List list = dao.query("EU99.query", param);
		if(list.size()>0) {
			Map data = (Map) list.get(0);
			String code = (String) data.get("lastDataGroupCode");
			String name = (String) data.get("lastDataGroupName");
			Map attr = inInfo.getAttr();
			attr.put("code", code);
			attr.put("name", name);
			inInfo.setAttr(attr);
			UserSession.setOutSessionProperty("datagroupCode",code);
			
		}else {
			ArrayList<HashMap<String,Object>> dataGroups = DatagroupUtil.getUserDataGroups();
			if(dataGroups.size()>0) {
				HashMap<String, Object> map = dataGroups.get(0);
				String code = (String) map.get("value");
				String name = (String) map.get("text");
				Map attr = inInfo.getAttr();
				attr.put("code", code);
				attr.put("name", name);
				inInfo.setAttr(attr);
				UserSession.setOutSessionProperty("datagroupCode",code);
			}
		}
		return inInfo;
	}
}
