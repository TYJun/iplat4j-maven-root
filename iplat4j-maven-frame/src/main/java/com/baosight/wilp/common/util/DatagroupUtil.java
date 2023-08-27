package com.baosight.wilp.common.util;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 院区工具类.
 * <p>
 * .
 * </p>
 *
 * @Title DatagroupUtil.java
 * @Author hcd, jzm
 * @Date 2021-08-17 10:10:10
 * @Version 1.0
 * @Copyright 2021 www.bonawise.com Inc. All rights reserved.
 * @History
 *
 */

public class DatagroupUtil {

	@SuppressWarnings("unchecked")
	public static ArrayList<HashMap<String, Object>> getUserDataGroups() {

		EiInfo eiInfo = new EiInfo();

		String workNo = UserSession.getLoginName();
		eiInfo.set("workNo", workNo);

		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getUserGroups");
		EiInfo info = XLocalManager.call(eiInfo);

		ArrayList<HashMap<String, Object>> dataGroups = (ArrayList<HashMap<String, Object>>) info.get("result");

		return dataGroups;
	}

	public static String getDatagroupCode() {

		EiInfo eiInfo = new EiInfo();
		String workNo = UserSession.getLoginName();
		eiInfo.set("workNo", workNo);

		eiInfo.set(EiConstant.serviceName, "AUFW01");
		eiInfo.set(EiConstant.methodName, "getGroupsToUser");
		EiInfo info = XLocalManager.call(eiInfo);

		String datagroupCode = info.get("datagroupCode") == null ? "" : info.getString("datagroupCode");

		return datagroupCode;
	}

}
