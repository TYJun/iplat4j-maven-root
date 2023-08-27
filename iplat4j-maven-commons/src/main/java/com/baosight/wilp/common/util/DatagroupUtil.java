package com.baosight.wilp.common.util;

import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.service.soa.XServiceManager;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class DatagroupUtil {

	public static ArrayList<HashMap<String, Object>> getUserDataGroups(String LoginName) {

		EiInfo eiInfo = new EiInfo();
		eiInfo.set("loginName", LoginName);
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
	
	public static ArrayList<HashMap<String, Object>> getUserDataGroups() {
		String loginName = UserSession.getLoginName();
		return getUserDataGroups(loginName);
		
	}
	
	/**
	 * 获取指定用户的iplat账套
	 * @Title: getIplatDatagroupCode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param workNo
	 * @param:  @return
	 * @return: String  
	 * @throws
	 */
	public static String getIplatDatagroupCode(String workNo){
		EiInfo eiInfo = new EiInfo();
		eiInfo.set("loginName", workNo);
		eiInfo.set(EiConstant.serviceName, "EU99");
		eiInfo.set(EiConstant.methodName, "getLastDataGroup");
	    EiInfo info = XLocalManager.call(eiInfo);
		return info.getString("code");
	}

	public static String getDatagroupCode() {
		Object datagroupCode = UserSession.getInSessionProperty("datagroupCode");
		if (datagroupCode instanceof String) {
			return (String) datagroupCode;
		} else {
			return null;
		}
	}
	
	public static String getDatagroupCode(String workNo) {
		workNo = StringUtils.isBlank(workNo) ? UserSession.getLoginName() : workNo;
		String datagroupCode = BaseDockingUtils.getUserGroupByWorkNo(workNo);
		return datagroupCode;
	}
	
	public static String getDatagroupCodeByDeptNum(String deptNum) {
		String datagroupCode = BaseDockingUtils.getUserGroupByDeptNum(deptNum);
		return datagroupCode;
	}

}
