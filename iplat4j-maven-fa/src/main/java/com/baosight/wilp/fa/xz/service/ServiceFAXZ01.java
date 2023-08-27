package com.baosight.wilp.fa.xz.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.wilp.fa.utils.OneSelfUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 固定资产闲置接口.
 * 1.固定资产闲置初始化接口.
 * 2.固定资产闲置查询方法.
 * @author zhaowei
 * @date 2022年05月30日 12:09
 * @version V5.0.0
 */
public class ServiceFAXZ01 extends ServiceBase {
	/**
	 * 固定资产闲置初始化接口.
	 * 1.调用本类中的固定资产闲置查询方法.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/6 20:04
	 * @version V5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		return this.query(info);
	}

	/**
	 * 固定资产闲置查询方法.
	 * 1.构建分页参数集合.
	 * 2.判断是否存在Block块.
	 * 3.进行数据库查询操作.
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/6/6 20:04
	 * @version V5.0.0
	 */
	@Override
	public EiInfo query(EiInfo info) {
		String deptName = OneSelfUtils.specifyDept();
		if (StringUtils.isNotEmpty(deptName)){
			info.set("deptName",deptName);
		}
		/*
		 * 1.构建分页参数集合
		 * 传入初始初始偏移量（从那行进行返回）0、具体返回行数
		 */
		Map<String, Object> pageMap = new HashMap<>(8);
		pageMap.put("offset", 0);
		pageMap.put("limit", 10);
		/*
		 * 2.判断是否存在Block块
		 * 存在Block块则取blockId是result的分页参数
		 */
		if (info.getBlocks().size() > 0) {
			EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
			pageMap = eiBlock.getAttr();
		}
		/*
		 * 3.进行数据库查询操作
		 * 通过查询参数集合以及分页参数查询固定资产闲置信息
		 */
		List<Map<String, Object>> queryFaIdleInfo = dao.query("FAXZ01.query", info.getAttr(), (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
		int count = dao.count("FAXZ01.query", info.getAttr());
		pageMap.put("count", count);
		info.setRows("result", queryFaIdleInfo);
		info.setAttr(pageMap);
		return info;
	}
}
