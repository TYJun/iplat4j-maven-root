package com.baosight.wilp.sq.dx.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.sq.common.SqSmsConfig;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author zhaowei
 * @date 2022年03月16日 11:30
 */
public class ServiceSQDX01 extends ServiceBase {
	/**
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/17 12:16
	 */
	public EiInfo initLoad(EiInfo info) {
		// 调用初始化方法
		return super.initLoad(info, new SqSmsConfig());
	}

	/**
	 * 查询满意度短信配置
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/17 12:55
	 */
	public EiInfo querySQMsg(EiInfo inInfo) {
		EiInfo info = super.query(inInfo, "SQDX01.querySQMsg",new SqSmsConfig());
		info.set("data", info.getBlock("result").getRows());
		info.setMsg("刷新成功");
		return inInfo;
	}

	/**
	 * 保存满意度短信
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/3/17 12:55
	 */
	public EiInfo saveSQMsg(EiInfo info) {
		Object msgObject = info.get("list");
		saveSQMsg(msgObject);
		info.setMsg("保存成功");
		return info;
	}

	/**
	 * 保存短信配置
	 *
	 * @param msgObject
	 * @author zhaowei
	 * @date 2022/3/17 22:38
	 */
	public void saveSQMsg(Object msgObject) {
		List<Map<String, Object>> msgList = (List<Map<String, Object>>) msgObject;
		if (CollectionUtils.isNotEmpty(msgList)) {
			for (Map<String, Object> msgMap : msgList) {
				dao.delete("SQDX01.deleteSQMsg", msgMap);
				msgMap.put("datagroupCode", "");
				dao.insert("SQDX01.saveSQMsg", msgMap);
			}
		}
	}
}
