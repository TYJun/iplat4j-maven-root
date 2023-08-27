package com.baosight.wilp.cm.jd.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.cm.util.CreateNoUtils;
import com.baosight.wilp.cm.util.EasyIplat4jUtil;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.utils.DatagroupUtil;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServiceCMJD0201 extends ServiceBase {
	/**
	 * 初始化查询
	 * @author zhaowei
	 * @date 2022/2/11 14:00
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo initLoad(EiInfo info){
		// 实例化Map
		Map<String,Object> map = new HashMap();
		String type = info.getString("type");
		// 判断是否是新增操作
		if(type.equals("add")){
			return info;
		}
		String scheduleId = info.getString("id");
		map.put("scheduleId",scheduleId);
		List<Map<String,Object>> query = dao.query("CMJD02.query", map);
		info.setAttr(query.get(0));
		String[] str = {"nodeAutoNo","nodeName","nodeRemark"};
		info.addRows("result",EasyIplat4jUtil.listToList(query,str));
		return info;
	}

	/**
	 * 合同登记新增
	 * @author zhaowei
	 * @date 2022/2/11 14:00
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 */
	public EiInfo insert(EiInfo info){
		// 1.获取账套信息、登录信息
		// 获取账套信息
		DatagroupUtil.getDatagroupCode();
		// 获取用户信息
		String createNo = UserSession.getUser().getUsername();
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		String createName = (String) staffByUserId.get("name");
		// 获取入参参数与分页信息
		Map<String, Object> map = new HashMap<>();
		String id = UUID.randomUUID().toString();
		String scheduleAuto = CreateNoUtils.createNo("SC");
		String scheduleName = info.getString("scheduleName");
		String scheduleRemark = info.getString("scheduleRemark");
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) info.get("resultList");
		Map<String, Object> resultMap = EasyIplat4jUtil.listToMap(resultList);
		String createTime = DateUtils.curDateTimeStr19();
		String nodeAutoNo = EasyIplat4jUtil.listToString((List<String>) resultMap.get("nodeAutoNo"));
		String nodeName = EasyIplat4jUtil.listToString((List<String>) resultMap.get("nodeName"));
		String nodeRemark = EasyIplat4jUtil.listToString((List<String>) resultMap.get("nodeRemark"));
		// 将获取的信息存储进map
		map.put("id", id);
		map.put("scheduleAuto", scheduleAuto);
		map.put("scheduleName", scheduleName);
		map.put("scheduleRemark", scheduleRemark);
		map.put("nodeAutoNo", nodeAutoNo);
		map.put("nodeName", nodeName);
		map.put("nodeRemark", nodeRemark);
		map.put("recCreatorNo", createNo);
		map.put("recCreator", createName);
		map.put("recCreateTime", createTime);
		// 如果进度Id不为空
		if (StringUtils.isNotEmpty(info.getString("scheduleId"))){
			map.put("scheduleId", info.getString("scheduleId"));
			map.put("recRevisorNo", createNo);
			map.put("recRevisor", createName);
			map.put("recReviseTime", createTime);
			dao.update("CMJD02.update", map);
			return info;
		}
		// 调用sql查询方法获取信息
		dao.insert("CMJD02.insert", map);
		// 将查询信息封装返回
		return info;
	}
}
