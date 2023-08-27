package com.baosight.wilp.ss.bm.service;

import com.baosight.iplat4j.core.ei.*;
import com.baosight.iplat4j.core.exception.PlatException;
import com.baosight.iplat4j.core.service.impl.ServiceBase;
import com.baosight.iplat4j.core.service.soa.XLocalManager;
import com.baosight.iplat4j.core.util.ExceptionUtil;
import com.baosight.iplat4j.core.util.StringUtils;
import com.baosight.iplat4j.core.web.threadlocal.UserSession;
import com.baosight.wilp.ss.bm.domain.CarriageAddressTree;
import com.baosight.wilp.ss.bm.domain.SSBMSCDD01;
import com.baosight.wilp.ss.bm.utils.StringUtil;
import com.baosight.wilp.ss.bm.utils.UUIDUtils;
import com.baosight.wilp.ss.bm.utils.XServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 送餐地点管理service
 */
public class ServiceSSBMSCDD01 extends ServiceBase {

	public static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: initLoad初始化
	 * @Param
	 * @return:
	 */
	@Override
	public EiInfo initLoad(EiInfo inInfo) {
		EiBlock block = new EiBlock(EiConstant.resultBlock);
		block.addBlockMeta(new CarriageAddressTree().eiMetadata);
		EiInfo outInfo = new EiInfo();
		outInfo.addBlock(block);
		outInfo.setMsg("欢迎来到送餐地址管理");

		EiInfo initLoad = super.initLoad(inInfo);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		// 获取登陆者姓名，限制其权限
		paramMap.put("userId", UserSession.getLoginName());
		// 查询食堂
		List<HashMap<String, Object>> listCanteenType = dao.query("SSBMStxx01.getCanteenData", paramMap);
		initLoad.addBlock("canteenData").addRows(listCanteenType);

		// 初始化加载数据，building:楼
		paramMap.put("mealgroupTypeCode", "building");
		List<HashMap<String, Object>> listbuilding = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("building").setRows(listbuilding);

		// 初始化加载数据，floor:层
		paramMap.put("mealgroupTypeCode", "floor");
		List<HashMap<String, Object>> listfloor = dao.query("SSBMStxx01.getMealTypeData", paramMap);
		initLoad.addBlock("floor").setRows(listfloor);

		return initLoad;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: initMetaData初始化
	 * @Param
	 * @return:
	 */
	private EiBlockMeta initMetaData() {
		EiBlockMeta eiMetadata = new EiBlockMeta();
		EiColumn eiColumn = new EiColumn("label");
		eiColumn.setDescName("英文名");
		eiColumn.setNullable(false);
		eiColumn.setPrimaryKey(true);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("text");
		eiColumn.setDescName("中文名");
		eiColumn.setNullable(false);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("pId"); // 作为kendo.data.Model 不能出现id，parent列
		eiColumn.setDescName("父级英文名");
		eiColumn.setNullable(true);
		eiMetadata.addMeta(eiColumn);

		eiColumn = new EiColumn("leaf");
		eiColumn.setDescName("0存在 1不存在");
		eiColumn.setNullable(true);
		eiMetadata.addMeta(eiColumn);

		return eiMetadata;

	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: query查询
	 * @Param
	 * @return:
	 */
	@Override
	public EiInfo query(EiInfo inInfo) {

		EiInfo outInfo = super.query(inInfo, "SSBMSCDD01.query", new SSBMSCDD01());
		return outInfo;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: queryTree查询树信息
	 * @Param
	 * @return:
	 */
	public EiInfo queryTree(EiInfo inInfo) {
		inInfo.set("result-limit", 1000);
		String code = (String)inInfo.get("node");
		if(StringUtils.isNotEmpty(code)) {
		    inInfo.addBlock("inqu_status").setCell(0, "node", code);
		}
		EiInfo outInfo = super.query(inInfo, "SSBMSCDD01.queryTree", initMetaData());
		// 获取父节点id，此处的"node"与xml中queryTree方法的property相对应
		String pEname = outInfo.getCellStr(EiConstant.queryBlock, 0, "node");

		// 将查询结果blockid修改为父节点id值
		outInfo.getBlocks().put(pEname, outInfo.getBlock(EiConstant.resultBlock));
		outInfo.getBlocks().remove(EiConstant.resultBlock);
		//outInfo.setStatus(1);
		return outInfo;

	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: queryParentId查询父节点
	 * @Param
	 * @return:
	 */
	public void queryParentId(String id, List<String> list) {
		EiInfo info = new EiInfo();
		info.set("id", id);
		info.set(EiConstant.serviceName, "SSBMSCDD01");
		info.set(EiConstant.methodName, "queryChildren");
		EiInfo outInfo = XLocalManager.call(info);
		List<String> leaf = (List<String>) outInfo.get("list");
		//判断是否存在子节点
		if (leaf.isEmpty()) {
			// 不存在子节点
			return;
		} else {
			//add子节点
			list.addAll(leaf);
			for (int i = 0; i < leaf.size(); i++) {
				//获取父节点
				queryParentId(leaf.get(i), list);
			}
		}
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: queryChildren查询子节点
	 * @Param
	 * @return:
	 */
	public EiInfo queryChildren(EiInfo info) {
		List<String> leaf = dao.query("SSBMSCDD01.queryChildren", info.get("id"));
		info.set("list", leaf);
		return info;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: insert新增
	 * @Param
	 * @return:
	 */
	@Override
	public EiInfo insert(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			// 获取传参
			Map attr = inInfo.getAttr();
			// 接收弹窗数据
			ArrayList<HashMap<String, Object>> submitList = (ArrayList<HashMap<String, Object>>) attr.get("submit");
			String type=(String) attr.get("type");
			if (submitList != null && submitList.size() > 0) {
				for (int i = 0; i < submitList.size(); i++) {
					submitList.get(i).put("id", UUIDUtils.getUUID32());
				}
				// 将数据填充到result
				inInfo.addBlock("result").addRows(submitList);
				if("tree1".equals(type)) {
					HashMap<String, Object> map = submitList.get(0);
					outInfo = super.insert(inInfo, "SSBMSCDD01.insert");
					// 添加层时把父节点的hasChildren变更为0:有子节点
					dao.update("SSBMSCDD01.updateParent", map);
				} else if("result1".equals(type)){
					outInfo = super.insert(inInfo, "SSBMSCDD01.insertClass");
				}
				outInfo.setMsg("保存成功！");
				inInfo.setStatus(1);
			} else {
				outInfo.setMsg("数据提交失败！");
				inInfo.setStatus(-1);
			}
		} catch (PlatException e) {
			//对于唯一索引进行捕获
			String rootCauseMessage = ExceptionUtil.getRootCauseMessage(e);
			if(rootCauseMessage.indexOf("idx_only") > -1){
				inInfo.setMsg("该数据已存在，请勿重复添加！");
			}else{
				e.printStackTrace();
				inInfo.setMsg("新增失败！");
			}
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: update更新
	 * @Param
	 * @return:
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EiInfo update(EiInfo inInfo) {
		EiInfo outInfo = new EiInfo();
		try {
			// 获取传参
			Map attr = inInfo.getAttr();
			// 接收弹窗数据
			ArrayList<HashMap<String, Object>> updateList = (ArrayList<HashMap<String, Object>>) attr.get("submit");
			String type=(String) attr.get("type");
			if (updateList != null && updateList.size() > 0) {
				// 将数据填充到result
				inInfo.addBlock("result").addRows(updateList);
				
				if("tree1".equals(type)) {
					outInfo = super.update(inInfo, "SSBMSCDD01.update");
				} else if("result1".equals(type)){
					outInfo = super.update(inInfo, "SSBMSCDD01.updateClass");
				}
				outInfo.setMsg("更新成功！");
			} else {
				outInfo.setMsg("数据提交失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inInfo.setMsg("更新失败！");
			inInfo.setStatus(-1);
			return inInfo;
		}
		return outInfo;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: delete删除
	 * @Param
	 * @return:
	 */
	@Override
	public EiInfo delete(EiInfo inInfo) {
		EiInfo outInfo = super.delete(inInfo, "SSBMSCDD01.deleteAddressClass");
		return outInfo;
	}

	/**
	 * Todo(这里用一句话描述这个方法的作用)
	 *
	 * @Title: deleteBatch删除节点
	 * @Param
	 * @return:
	 */
	public EiInfo deleteBatch(EiInfo inInfo) {
		inInfo.set("result-limit", 1000);
		List<String> list = new ArrayList<String>();
		String topCode = inInfo.getAttr().get("nodeId").toString();
		String id = inInfo.getAttr().get("id").toString();
		dao.delete("SSBMSCDD01.delete", id);
		return inInfo;
	}
}
