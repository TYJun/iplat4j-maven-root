package com.baosight.wilp.it.dj.service;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.wilp.it.utils.CreateNum;
import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一段
 * 独立任务登记接口.
 * 1.独立任务登记初始化方法.
 * 2.独立任务登记查询方法.
 * 第二段
 * 通过账套（dataGroupCode）、登记状态（status）、任务单号（taskNo）、任务类别（parentId）、
 * 需求科室（reqDeptName）、服务科室（serveDeptName）、创建时间起（recCreateTimeS）、创建时间止（recCreateTimeE）
 * 获取独立任务列表信息
 * 第三段
 * @author zhaowei
 * @date 2022年07月28日 17:02
 * @version V1.0
 */
public class ServiceITDJ01 extends ServiceBase {
	/*
	 * 第一段
	 * 独立任务登记初始化方法，调用独立任务登记查询方法.
	 * 第二段
	 * 通过账套（dataGroupCode）、登记状态（status）、任务单号（taskNo）、服务分类（parentId）、
	 * 需求科室（reqDeptName）、服务科室（serveDeptName）、创建时间起（recCreateTimeS）、创建时间止（recCreateTimeE）
	 * 获取初始化独立任务列表信息
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/12 18:01
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v1.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		// 调用独立任务登记查询方法.
		return this.query(info);
	}

	/**
	 * 第一段
	 * 独立任务登记查询方法.
	 * 第二段
	 * 1.构建分页参数.
	 * 2.判断Block是否存在，存在则取出Block的分页参数.
	 * 3.构建查询参数.
	 * 4.添加账套信息.
	 * 5.进行数据库查询操作.
	 * 6.将查询记录返回并加上分页参数.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/15 10:09
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version v1.0
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
		 */
		Map<String, Object> paramsMap = new HashMap<>(16);
		if (info.getBlock("inqu_status") != null) {
			paramsMap = info.getBlock("inqu_status").getRow(0);
		}
		/*
		 * 4.添加账套信息
		 * 如果账套是all则查出当前账号拥有的所有院区
		 */
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
		 * 5.进行数据库查询操作
		 * 通过查询参数集合以及分页参数查询独立任务登记信息
		 * 通过账套（dataGroupCode）、登记状态（status）、任务单号（taskNo）、服务分类（parentId）、
		 * 需求科室（reqDeptName）、服务科室（serveDeptName）、创建时间起（recCreateTimeS）、创建时间止（recCreateTimeE）
		 */
		List<Map<String, Object>> queryItTaskInfoList = dao.query("ITDJ01.queryItTaskInfo", paramsMap, (Integer) pageMap.get("offset"), (Integer) pageMap.get("limit"));
		int count = dao.count("ITDJ01.queryItTaskInfo", paramsMap);
		/*
		 * 6.将查询记录返回并加上分页参数
		 * 将查询到的独立任务登记信息总数添加到分页参数中
		 * 将独立任务登记信息保存到result中
		 * 将分页参数保存到info中
		 */
		pageMap.put("count", count);
		info.setRows("result", queryItTaskInfoList);
		info.setAttr(pageMap);
		return info;
	}
}