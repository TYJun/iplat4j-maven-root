package com.baosight.wilp.cm.jd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.cm.util.CreateNoUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.xservices.xs.util.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServiceCMJD0101 extends ServiceBase {
	/**
	 * 合同编辑查询方法
	 * @author zhaowei
	 * @date 2022/2/11 13:59
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info) {
		// 实例化Map
		Map<String,Object> map = new HashMap();
		String id = (String) info.getAttr().get("id");
		String type = (String) info.getAttr().get("type");
		// 如果id不为空
		if(StringUtils.isNotEmpty(id)){
			map.put("nodeId",id);
			List<Map<String,Object>> query = dao.query("CMJD01.query", map);
			info.set("nodeId",query.get(0).get("nodeId"));
			info.set("nodeNames",query.get(0).get("nodeName"));
			info.set("nodeRemark",query.get(0).get("nodeRemark"));
		}
		return info;
	}

	/**
	 * 合同保存方法
	 * @param info
	 * @return
	 */
	public EiInfo save(EiInfo info) {
		// 获取人员信息
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String loginName = (String) staffByUserId.get("name");
		String workNo = (String) staffByUserId.get("workNo");
		Map<String, Object> map = new HashMap();
		String nodeAutoNo = CreateNoUtils.createNo("JD");
		String nodeName = (String) info.getAttr().get("nodeName");
		String nodeRemark = (String) info.getAttr().get("nodeRemark");
		String nodeId = (String) info.getAttr().get("nodeId");
		if (StringUtils.isNotEmpty(nodeId)){
			map.put("nodeId", nodeId);
			map.put("nodeName", nodeName);
			map.put("nodeRemark", nodeRemark);
			map.put("recRevisorNo", workNo);
			map.put("recRevisor", loginName);
			map.put("recReviseTime", DateUtils.curDateTimeStr19());
			dao.update("CMJD01.update", map);
			return info;
		}
		map.put("id", UUID.randomUUID().toString());
		map.put("nodeAutoNo", nodeAutoNo);
		map.put("nodeName", nodeName);
		map.put("nodeRemark", nodeRemark);
		map.put("recCreatorNo", workNo);
		map.put("recCreator", loginName);
		map.put("recCreateTime", DateUtils.curDateTimeStr19());
		dao.insert("CMJD01.save", map);
		return info;
	}
}
