package com.baosight.wilp.ms.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;

public class DatagroupUtil {

	public static ArrayList<HashMap<String, Object>> getUserDataGroups() {

		EiInfo eiInfo = new EiInfo();
		String loginName = UserSession.getLoginName();
		eiInfo.set("loginName", loginName);
		eiInfo.set(EiConstant.serviceId, "S_XS_70");
		EiInfo info = XServiceManager.call(eiInfo);
		Map attr = info.getAttr();
		ArrayList<Map> results = (ArrayList<Map>) attr.get("result");
		ArrayList<HashMap<String, Object>> dataGroups = new ArrayList<HashMap<String, Object>>();
		for (Map map : results) {
			HashMap<String, Object> data = new HashMap<String, Object>();
			String orgEname = (String) map.get("orgEname");
			String orgCname = (String) map.get("orgCname");
			int sortIndex = (int) map.get("sortIndex");
			data.put("value", orgEname);
			data.put("text", orgCname);
			data.put("sortNo", sortIndex);
			dataGroups.add(data);
		}
		//根据序号对院区进行排序
		Collections.sort(dataGroups, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				int sortNo1 = (int) o1.get("sortNo");
				int sortNo2 = (int) o2.get("sortNo"); 
				return sortNo1 - sortNo2;
			}
		});

		return dataGroups;
	}

	public static String getDatagroupCode() {
		Object datagroupCode = UserSession.getInSessionProperty("datagroupCode");
		if (datagroupCode instanceof String) {
			return (String) datagroupCode;
		} else {
			return null;
		}
	}

}
