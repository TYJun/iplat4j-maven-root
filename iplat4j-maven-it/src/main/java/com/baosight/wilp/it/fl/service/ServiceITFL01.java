package com.baosight.wilp.it.fl.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiConstant;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.it.utils.CreateNum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 第一段
 * 独立任务分类接口.
 * 1.独立任务分类初始化方法.
 * 2.独立任务分类查询方法.
 * 3.独立任务分类查询树结构方法.
 * 4.独立任务分类移除方法.
 * 5.独立任务分类查询子节点方法.
 * 第二段
 * 通过分类编码、分类名称查询独立任务分类信息.
 * 独立任务分类查询树结构方法.
 * 独立任务分类递归查询子节点方法.
 * 第三段
 * @author zhaowei
 * @date 2022年07月26日 10:47
 * @version V1.0
 */
public class ServiceITFL01 extends ServiceBase {
	/**
	 * 第一段
	 * 独立任务分类初始化方法，调用独立任务分类查询方法.
	 * 第二段
	 * 通过账套（dataGroupCode）、分类编码、分类名称查询独立任务分类信息.
	 * 第三段
	 * @param inInfo
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/2 15:07
	 * @version V1.0
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		return this.query(inInfo);
	}

	/**
	 * 第一段
	 * 独立任务分类查询方法.
	 * 第二段
	 * 1.构建分页参数集合.
	 * 2.判断是否存在Block块.
	 * 3.构建查询参数集合.
	 * 4.进行数据库查询操作.
	 * 5.将查询记录返回并加上分页参数.
	 * 第三段
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/2 15:07
	 * @version V1.0
	 */
	@Override
	public EiInfo query(EiInfo info) {
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
		 * 3.构建查询参数集合
		 * 三段式存在则通过前端三段式获取查询参数集合
		 * 并且添加账套信息、父节点Id
		 */
		Map<String, Object> paramsMap = new HashMap<>(16);
		if (info.getBlock("inqu_status") != null) {
			paramsMap = info.getBlock("inqu_status").getRow(0);
		}
		paramsMap.put("parentId", info.getString("typeId"));
		if("all".equals(CreateNum.getDataGroupCode(null))){
			if (CreateNum.getDataGroupCodeAll(null)!=null){
				paramsMap.put("dataGroupCode", CreateNum.getDataGroupCodeAll(null));
				paramsMap.put("dataGroupCodeAll", StringUtils.join(CreateNum.getDataGroupCodeAll(null).toArray(),","));
			}
		}else {
			List<String> dataGroupCode = new ArrayList<>();
			dataGroupCode.add(CreateNum.getDataGroupCode(null));
			paramsMap.put("dataGroupCode", dataGroupCode);
		}
		/*
		 * 4.进行数据库查询操作
		 * 通过查询参数集合以及分页参数查询独立任务分类信息
		 * 通过账套（dataGroupCode）、分类编码、分类名称
		 */
		List<Map<String, Object>> resultList = dao.query("ITFL01.queryItClassifyInfo", paramsMap, (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
		int count = dao.count("ITFL01.queryItClassifyInfo", paramsMap);
		/*
		 * 5.将查询记录返回并加上分页参数
		 * 将查询到的独立任务分类信息总数添加到分页参数中
		 * 将独立任务分类信息保存到result中
		 * 将分页参数保存到info中
		 */
		pageMap.put("count", count);
		info.setRows("result", resultList);
		info.setAttr(pageMap);
		return info;
	}

	/**
	 * 第一段
	 * 独立任务分类查询树结构方法.
	 * 第二段
	 * 1.设置回显的最大条数，默认为10.
	 * 2.获取选中的树节点，初始为root.
	 * 3.构建查询参数集合.
	 * 4.调用数据库查询方法.
	 * 5.将查询到的子节点绑定到父节点下.
	 * 第三段
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @author zhaowei
	 * @date 2022/8/2 15:50
	 * @version V1.0
	 */
	public EiInfo queryItClassifyTree(EiInfo info) {
		/*
		 * 1.设置回显的最大条数，默认为10
		 */
		info.set("result-limit", 1000);
		/*
		 * 2.获取选中的树节点，初始为root
		 */
		String pEname = info.getCellStr(EiConstant.queryBlock, 0, "node");
		/*
		 * 3.构建查询参数集合
		 * 手动传参选择的树节点和账套
		 */
		Map<String, Object> queryMap = new HashMap<>(4);
		queryMap.put("parentEname", pEname);
		if("all".equals(CreateNum.getDataGroupCode(null))){
			if (CreateNum.getDataGroupCodeAll(null)!=null){
				queryMap.put("dataGroupCode", CreateNum.getDataGroupCodeAll(null));
				queryMap.put("dataGroupCodeAll", StringUtils.join(CreateNum.getDataGroupCodeAll(null).toArray(),","));
			}
		}else {
			List<String> dataGroupCode = new ArrayList<>();
			dataGroupCode.add(CreateNum.getDataGroupCode(null));
			queryMap.put("dataGroupCode", dataGroupCode);
		}
		/*
		 * 4.调用数据库查询方法
		 * 通过树节点查询子节点信息
		 */
		List<Map<String, Object>> itClassifyTreeList = dao.query("ITFL01.queryItClassifyTree", queryMap);
		EiInfo outInfo = new EiInfo();
		/*
		 * 5.将查询到的子节点绑定到父节点下
		 */
		EiBlock outBlock = outInfo.addBlock(pEname);
		outBlock.addRows(itClassifyTreeList);
		return outInfo;
	}

	/**
	 * 第一段
	 * 独立任务分类该节点所有节点删除方法.
	 * 第二段
	 * 1.构建list集合.
	 * 2.调用递归查询选中节点下的子节点方法.
	 * 3.通过构建的list集合，查询是否存在正在使用的任务分类.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 16:44
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo remove(EiInfo info) {
		/*
		 * 1.构建list集合
		 * 获取传入的节点信息
		 */
		List<String> list = new ArrayList<>();
		list.add(info.getString("itClassifyId"));
		/*
		 * 2.调用递归查询选中节点下的子节点方法
		 * 并将所有查到的节点保存到构建的集合中
		 * 去重后重新构建list集合
		 */
		List<String> nodesList = queryChildNodes(list, new ArrayList<>());
		list.addAll(nodesList);
		list = list.stream().distinct().collect(Collectors.toList());
		/*
		 * 3.通过构建的list集合，查询是否存在正在使用的任务分类
		 * 如果集合不为空则返回，提示状态199
		 * 否则调用数据库删除方法，删除该节点下的所有节点
		 */
		List<Map<String, Object>> taskIdList = dao.query("ITFL01.whetherExistTask", list);
		if (CollectionUtils.isNotEmpty(taskIdList)) {
			info.setStatus(199);
		} else {
			dao.delete("ITFL01.removeAllNodes", list);
		}
		return info;
	}

	/**
	 * 第一段
	 * 递归查询选中节点下的子节点方法.
	 * 第二段
	 * 1.调用数据库查询方法.
	 * 2.判断当前节点是否为空.
	 * 3.返回查询到的子节点.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/8 16:32
	 * @param itClassifyId
	 * @param nodesList
	 * @return java.util.List<java.lang.String>
	 * @version V1.0
	 */
	public List<String> queryChildNodes(List<String> itClassifyId,List<String> nodesList){
		/*
		 * 1.调用数据库查询方法
		 * 通过当前节点查找子节点
		 */
		List<String> nodeList = dao.query("ITFL01.queryChildNodes", itClassifyId);
		/*
		 * 2.判断当前节点是否为空
		 * 不为空则添加当前节点并进行递归
		 */
		if (CollectionUtils.isNotEmpty(nodeList)){
			nodesList.addAll(nodeList);
			queryChildNodes(nodeList,nodesList);
		}
		/*
		 * 3.返回查询到的子节点
		 */
		return nodesList;
	}
}
