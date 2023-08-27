package com.baosight.wilp.fa.lb.service;

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
 * 固定资产类别管理逻辑类.
 * 1.固定资产类别初始化方法.
 * 2.查询固定资产类别的树结构.
 * 3.固定资产类别查询方法.
 * 4.删除固定资产类别信息.
 * 5.递归查询子节点.
 * 6.固定资产类别名称下拉框.
 *
 * @author zhaowei
 * @date 2022年05月17日 14:14
 */
public class ServiceFALB01 extends ServiceBase {
	/**
	 * 固定资产类别初始化方法.
	 * 1.调用查询方法.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/24 11:25
	 * @version v5.0.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 1.调用查询方法
		info.setCell("inqu_status",0,"faTypeId","root");
		return this.query(info);
	}

	/**
	 * 查询固定资产类别的树结构.
	 * 1.设置回显的最大条数，默认为10,现在设置为1000.
	 * 2.获取选中的树节点，初始为root.
	 * 3.通过树节点查询子节点.
	 * 4.将查询到的子节点跟父节点绑定.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/23 16:20
	 * @version v5.0.0
	 */
	public EiInfo queryFaTypeTree(EiInfo info) {
		// 1.设置回显的最大条数，默认为10,现在设置为1000
		info.set("result-limit", 1000);
		// 2.获取选中的树节点，初始为root
		String pEname = info.getCellStr(EiConstant.queryBlock, 0, "node");
		Map<String, Object> queryMap = new HashMap<>(4);
		queryMap.put("parentEname", pEname);
		// 3.通过树节点查询子节点
		List<Map<String, String>> faTypeTreeList = dao.query("FALB01.queryFaTypeTree", queryMap);
		EiInfo outInfo = new EiInfo();
		// 4.将查询到的子节点跟父节点绑定
		EiBlock outBlock = outInfo.addBlock(pEname);
		outBlock.addRows(faTypeTreeList);
		return outInfo;
	}

	/**
	 * 固定资产类别查询方法.
	 * 1.设置分页条件.
	 * 2.在初始化查询时，不存在inqu_status.
	 * 3.点击查询时获取集合，并且去除空字符.
	 * 4.查询结果并返回.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/24 11:22
	 * @version v5.0.0
	 */
	@Override
	public EiInfo query(EiInfo info) {
		// 1.设置分页条件
		Map<String, Object> pageMap = new HashMap<>(8);
		pageMap.put("offset", 0);
		pageMap.put("limit", 20);
		EiBlock eiBlock = (EiBlock) info.getBlocks().get("result");
		if (eiBlock != null){
			pageMap = eiBlock.getAttr();
		}
		Map<String, Object> paramsMap = new HashMap<>(4);
		// 2.在初始化查询时，不存在inqu_status
		if (info.getBlock("inqu_status") != null) {
			// 3.点击查询时获取集合，并且去除空字符
			paramsMap = info.getBlock("inqu_status").getRow(0);
		} else {
			return info;
		}
		// 4.查询结果并返回
		List<Map<String, Object>> resultList = dao.query("FALB01.queryAessettypeInfo", paramsMap, (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
		int count = dao.count("FALB01.queryAessettypeInfo", paramsMap);
		pageMap.put("count", count);
		if (CollectionUtils.isNotEmpty(resultList)) {
			info.setRows("result", resultList);
		}
		info.setAttr(pageMap);
		return info;
	}

	/**
	 * 删除固定资产类别信息
	 * 1.获取删除的节点.
	 * 2.遍历该节点的子节点.
	 * 3.将遍历的节点进行查询如果在固定资产信息中被使用则停止删除.
	 * 4.删除所有节点.
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/25 11:05
	 * @version v5.0.0
	 * @Todo 删除失败提示具体节点 [这个问题下次迭代时处理]
	 */
	@Override
	public EiInfo delete(EiInfo info) {
		// 1.获取删除的节点
		String faTypeId = info.getString("faTypeId");
		// 2.遍历该节点的子节点
		List<String> childernList = recursionChildern(faTypeId, new ArrayList<>());
		childernList.add(faTypeId);
		// 3.将遍历的节点进行查询如果在固定资产信息中被使用则停止删除
		List<String> typeCodeList = dao.query("FALB01.typeCodeList", childernList);
		List<String> usedFaTypeIdList = dao.query("FALB01.existsUsedFaTypeId", typeCodeList);
		if (CollectionUtils.isEmpty(usedFaTypeIdList)) {
			// 4.删除所有节点
			int count = dao.delete("FALB01.delete", childernList);
			info.setMsg("删除成功，删除" + count + "条数据");
			info.setStatus(200);
		} else {
			info.setMsg("该节点下存在被使用的编码，请先从固定资产档案中删除");
		}
		return info;
	}

	/**
	 * 递归查询子节点
	 * 1.查询该节点的子节点.
	 * 2.该节点不为空，就继续查询子节点.
	 * 3.递归调用.
	 *
	 * @param faTypeId
	 * @return java.lang.String
	 * @author zhaowei
	 * @date 2022/5/25 11:25
	 * @version v5.0.0
	 */
	@Transactional
	public List<String> recursionChildern(String faTypeId, List<String> childernAllList) {
		// 1.查询该节点的子节点
		List<String> childernList = dao.query("FALB01.recursionChildern", faTypeId);
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

	/**
	 * 固定资产类别名称下拉框
	 * 1.查询固定资产类别并返回给前端
	 *
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/5/26 10:33
	 * @version v5.0.0
	 */
	public EiInfo faTypeEFSelect(EiInfo info) {
		// 1.查询固定资产类别并返回给前端
		List<Map<String, Object>> faTypeEFSelectList = dao.query("FALB01.queryFaTypeEFSelect", info.getAttr());
		info.setRows("result", faTypeEFSelectList);
		return info;
	}
}