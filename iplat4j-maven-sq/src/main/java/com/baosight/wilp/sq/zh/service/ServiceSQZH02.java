package com.baosight.wilp.sq.zh.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaowei
 * @date 2022年04月04日 20:03
 */
public class ServiceSQZH02 extends ServiceBase {
	/**
	 * 弹出初始化方法
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/4/4 20:03
	 */
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}

	/**
	 * 问卷详情展示
	 *
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/4/4 20:03
	 */
	public EiInfo query(EiInfo inInfo) {
		// 初始化页面总数
		int offset, limit;
		// 判断是否分页
		if (inInfo.getBlocks().isEmpty()) {
			offset = 0;
			limit = 10;
		} else {
			EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
			offset = (Integer) result.getAttr().get("offset");
			limit = (Integer) result.getAttr().get("limit");
		}
		Map<String, Object> map = new HashMap<>();
		String batchNo = inInfo.getString("batchNo");
		inInfo.set("batchNo",inInfo.getString("batchNo"));
		String workName = (String) inInfo.getAttr().get("workName");
		String advice = (String) inInfo.getAttr().get("advice");
		String percent = (String) inInfo.getAttr().get("percent");
		String projectName = (String) inInfo.getAttr().get("projectName");
		String deptName = (String) inInfo.getAttr().get("deptName");
		map.put("batchNo", batchNo);
		map.put("workName",workName);
		map.put("advice",advice);
		map.put("percent",percent);
		map.put("projectName",projectName);
		map.put("deptName",deptName);
		List querySqManageScoreList = dao.query("SQZH02.query", map,offset, limit);
		int count = dao.count("SQZH02.query", map);
		inInfo.setRows("result", querySqManageScoreList);
		// 处理分页
		EiBlock result = (EiBlock) inInfo.getBlocks().get("result");
		result.setAttr(new HashMap<>());
		if(result.getAttr().isEmpty()){
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", offset);
			rAttr.put("limit", limit);
			rAttr.put("orderBy", "");
			rAttr.put("showCount", "true");
			result.setAttr(rAttr);
		} else {
			result.getAttr().put("count", count);
		}
		return inInfo;
	}
}
