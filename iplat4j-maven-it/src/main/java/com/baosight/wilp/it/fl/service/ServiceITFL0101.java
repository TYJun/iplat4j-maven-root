package com.baosight.wilp.it.fl.service;

import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.util.DateUtils;
import com.baosight.wilp.common.util.BaseDockingUtils;
import com.baosight.wilp.it.utils.CreateNum;
import com.baosight.wilp.it.utils.IndependentTaskEnum;
import com.baosight.xservices.xs.util.UserSession;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * 第一段
 * 独立任务分类录入接口.
 * 1.独立任务分类录入、编辑初始化方法.
 * 2.独立任务分类保存方法，
 * 第二段
 * 独立任务分类录入、编辑初始化方法.
 * 独立任务保存方法.
 * 第三段
 * @author zhaowei
 * @date 2022年07月28日 10:47
 * @version V1.0
 */
public class ServiceITFL0101 extends ServiceBase {
	/**
	 * 第一段
	 * 独立任务分类录入、编辑初始化方法.
	 * 第二段
	 * 1.获取前端操作类型.
	 * 1.1.登记分支.
	 * 1.2.编辑分支.
	 * 1.2.1.获取选中的节点，构建map集合.
	 * 1.2.2.将构建的集合作为参数，调用数据库查询方法.
	 * 1.2.3.如果查询的结果不为空，则将数据进行封装返回.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/3 10:01
	 * @param info 
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	@Override
	public EiInfo initLoad(EiInfo info) {
		/*
		 * 1.获取前端操作类型
		 * 根据操作类型进入不同的分支
		 */
		String type = info.getString("type");
		switch (type){
			/*
			 * 1.1.登记分支
			 */
			case "enter":
				break;
			/*
			 * 1.2.编辑分支
			 * 1.2.1.获取选中的节点，构建map集合
			 * 1.2.2.将构建的集合作为参数，调用数据库查询方法
			 * 1.2.3.如果查询的结果不为空，则将数据进行封装返回
			 */
			case "edit":
				/*
				 * 1.2.1.获取选中的节点，构建map集合
				 */
				String itClassifyId = info.getString("itClassifyId");
				Map<String,Object> map = new HashMap<>(8);
				map.put("itClassifyId",itClassifyId);
				/*
				 * 1.2.2.将构建的集合作为参数，调用数据库查询方法
				 */
				if("all".equals(CreateNum.getDataGroupCode(null))){
					if (CreateNum.getDataGroupCodeAll(null)!=null){
						map.put("dataGroupCode", CreateNum.getDataGroupCodeAll(null));
						map.put("dataGroupCodeAll", StringUtils.join(CreateNum.getDataGroupCodeAll(null).toArray(),","));
					}
				}else {
					List<String> dataGroupCode = new ArrayList<>();
					dataGroupCode.add(CreateNum.getDataGroupCode(null));
					map.put("dataGroupCode", dataGroupCode);
				}
				List<Map<String,Object>> queryItClassifyInfoList = dao.query("ITFL01.queryItClassifyInfo", map);
				/*
				 * 1.2.3.如果查询的结果不为空，则将数据进行封装返回
				 */
				if (CollectionUtils.isNotEmpty(queryItClassifyInfoList)){
					info.set("inqu_status-0-itClassifyId",queryItClassifyInfoList.get(0).get("itClassifyId"));
					info.set("parentId",queryItClassifyInfoList.get(0).get("parentId"));
					info.set("parentName",queryItClassifyInfoList.get(0).get("parentName"));
					info.set("inqu_status-0-classifyNum",queryItClassifyInfoList.get(0).get("classifyNum"));
					info.set("inqu_status-0-classifyName",queryItClassifyInfoList.get(0).get("classifyName"));
					info.set("inqu_status-0-remark",queryItClassifyInfoList.get(0).get("remark"));
					info.set("inqu_status-0-archiveFlag",queryItClassifyInfoList.get(0).get("archiveFlag"));
				}
				break;
		}
		return info;
	}

	/*
	 * 第一段
	 * 独立任务分类保存方法，
	 * 第二段
	 * 1.构建Map集合，用于保存入参.
	 * 2.获取用户组信息.
	 * 3.获取前端操作类型.
	 * 3.1.录入分支.
	 * 3.2.编辑分支.
	 * 第三段
	 * @author zhaowei
	 * @date 2022/8/15 22:03
	 * @param info
	 * @return com.baosight.iplat4j.core.ei.EiInfo
	 * @version V1.0
	 */
	public EiInfo saveItClassifyInfo(EiInfo info) {
		/*
		 * 1.构建Map集合，用于保存入参
		 */
		Map<String, Object> paramsMap = new HashMap<>(16);
		/*
		 * 2.获取用户组信息
		 */
		Map<String, Object> staffByUserId = BaseDockingUtils.getStaffByWorkNo(UserSession.getUser().getUsername());
		/*
		 * 3.获取前端操作类型
		 * 根据操作类型进入不同的分支
		 */
		String type = info.getString("type");
		switch (type){
			/*
			 * 3.1.录入分支
			 * 三段式存在则通过前端三段式获取查询参数集合
			 * 并手动添加层级、序号、子节点、创建人、创建时间、账套信息
			 * 最后进行数据库保存操作
			 */
			case "enter":
				if (info.getBlock("inqu_status") != null) {
					paramsMap = info.getBlock("inqu_status").getRow(0);
					paramsMap.put("classifyNum", CreateNum.CreateNumByType(IndependentTaskEnum.FL.getAbbreviation()));
					paramsMap.put("itClassifyId", UUID.randomUUID().toString().replace("-",""));
					paramsMap.put("level","");
					paramsMap.put("isLeaf","");
					paramsMap.put("sortNo",0);
					paramsMap.put("recCreator",staffByUserId.get("workNo"));
					paramsMap.put("recCreateTime", DateUtils.curDateTimeStr19());
					if("all".equals(CreateNum.getDataGroupCode(null))){
						if (CreateNum.getDataGroupCodeAll(null)!=null){
							paramsMap.put("dataGroupCode", StringUtils.join(CreateNum.getDataGroupCodeAll(null).toArray(),","));
						}
					}else {
						paramsMap.put("dataGroupCode", CreateNum.getDataGroupCode(null));
					}
					paramsMap.put("archiveFlag",1);
				}
				dao.insert("ITFL01.saveItClassifyInfo",paramsMap);
				break;
			/*
			 * 3.2.编辑分支
			 * 三段式存在则通过前端三段式获取查询参数集合
			 * 并手动添加层级、序号、子节点、修改人、修改时间、账套信息
			 * 最后进行数据库更新操作
			 */
			case "edit":
				if (info.getBlock("inqu_status") != null) {
					paramsMap = info.getBlock("inqu_status").getRow(0);
					paramsMap.put("level","");
					paramsMap.put("isLeaf","");
					paramsMap.put("sortNo",0);
					paramsMap.put("recRevisor",staffByUserId.get("workNo"));
					paramsMap.put("recReviseTime", DateUtils.curDateTimeStr19());
				}
				dao.update("ITFL01.updateItClassifyInfo",paramsMap);
		}
		return info;
	}
}
