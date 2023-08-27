package com.baosight.wilp.fa.zn.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 第二段
 * 第三段
 *
 * @author zhaowei
 * @version 5.0.0
 * @date 2022年11月27日 11:07
 */
public class ServiceFAZN01 extends ServiceBase {
	/**
	 * 初始化方法
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/29 8:05
	 */
	public EiInfo initLoad(EiInfo info) {
		return query(info);
	}

	/**
	 * 左侧树结构查询
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/29 9:27
	 */
	public EiInfo queryFaDeptTree(EiInfo info) {
		// 1.设置回显的最大条数，默认为10,现在设置为1000
		info.set("result-limit", 1000);
		// 2.获取选中的树节点，初始为root
		String pEname = info.getCellStr(EiConstant.queryBlock, 0, "node");
		Map<String, Object> queryMap = new HashMap<>(4);
		queryMap.put("parentEname", pEname);
		// 3.通过树节点查询子节点
		List<Map<String, Object>> faDeptTreeList = dao.query("FAZN01.queryFaDeptTree", queryMap);
		EiInfo outInfo = new EiInfo();
		// 4.将查询到的子节点跟父节点绑定
		EiBlock outBlock = outInfo.addBlock(pEname);
		outBlock.addRows(faDeptTreeList);
		return outInfo;
	}

	/**
	 * 右侧查询
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/29 9:27
	 */
	public EiInfo query(EiInfo info) {
		// 1.设置分页条件
		Map<String, Object> pageMap = new HashMap<>(8);
		pageMap.put("offset", 0);
		pageMap.put("limit", 20);
		if (info.getBlocks().size() > 0) {
			EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
			pageMap = eiBlock.getAttr();
		}
		Map map = info.getRow("inqu_status", 0);
		List query = dao.query("FAZN01.query", map, (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
		int count = dao.count("FAZN01.query", map);
		pageMap.put("count", count);
		info.setRows("result", query);
		info.setAttr(pageMap);
		return info;
	}

	/**
	 * 递归删除
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/12/29 9:27
	 */
	public EiInfo delete(EiInfo info) {
		// 1.获取删除的节点
		String deptId = info.getString("deptId");
		// 2.遍历该节点的子节点
		List<String> childernList = recursionChildern(deptId, new ArrayList<>());
		childernList.add(deptId);
		// 3.删除所有节点
		int count = dao.delete("FAZN01.delete", childernList);
		info.setMsg("删除成功，删除" + count + "条数据");
		info.setStatus(200);
		return info;
	}

	@Transactional
	public List<String> recursionChildern(String deptId, List<String> childernAllList) {
		// 1.查询该节点的子节点
		List<String> childernList = dao.query("FAZN01.recursionChildern", deptId);
		// 2.该节点不为空，就继续查询子节点
		if (CollectionUtils.isNotEmpty(childernList)) {
			childernAllList.addAll(childernList);
			for (String childern : childernList) {
				// 3.递归调用
				recursionChildern(childern, childernAllList);
			}
		}
		return childernAllList;
	}
}
