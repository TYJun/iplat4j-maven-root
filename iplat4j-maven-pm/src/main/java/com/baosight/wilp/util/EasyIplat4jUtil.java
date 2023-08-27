package com.baosight.wilp.util;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;
import com.baosight.iplat4j.core.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.*;

/**
 * 简化iplat4j开发工具类
 */
public class EasyIplat4jUtil {

	// 1.将前端查询条件转换为map（inqu_status）
	public static Map<String, Object> queryConditionToMap(EiInfo info, String blockId, List<String> queryCondition) {
		// 实例化Map
		Map<String, Object> map = new HashMap<>();
		// 获取分页信息
		Integer offset = 0;
		Integer limit = 10;
		EiBlock eiBlock = info.getBlock(blockId);
		if (eiBlock != null && !eiBlock.getAttr().isEmpty()) {
			offset = eiBlock.getAttr().get("offset") == null ? 0 : (Integer) eiBlock.getAttr().get("offset");
			limit = eiBlock.getAttr().get("limit") == null ? 0 : (Integer) eiBlock.getAttr().get("limit");
		}
		// 获取分页信息
		String page = (String) info.get("page");
		if (StringUtils.isNotEmpty(page)) {
			offset = (Integer.parseInt(page) - 1) * limit;
		}
		String size = (String) info.get("size");
		if (StringUtils.isNotEmpty(size)) {
			limit = Integer.parseInt(size);
		}
		map.put("offset", offset);
		map.put("limit", limit);
		// 判断是否存在查询条件
		if (info.getBlocks().containsKey("inqu_status")) {
			// 获取查询区域
			EiBlock queryArea = (EiBlock) info.getBlocks().get("inqu_status");
			// 获取查询条件
			List<Map<String, Object>> queryAreaList = queryArea.getRows();
			// 遍历查询条件
			for (String field : queryCondition) {
				// 存储
				map.put(field, queryAreaList.get(0).get(field));
			}
		}
		return map;
	}

	// 获取页面传过来的参数（有分页）
	public static Map<String, Object> buildParamter(EiInfo info, String blockId, List<String> fieldList) {
		Map<String, Object> param = new HashMap<>();
		for (String field : fieldList) {
			param.put(field, info.get(field));
		}
		// 获取分页信息
		EiBlock eiBlock = info.getBlock(blockId);
		Integer offset = 0;
		Integer limit = 10;
		if (eiBlock != null && !eiBlock.getAttr().isEmpty()) {
			offset = eiBlock.getAttr().get("offset") == null ? 0 : (Integer) eiBlock.getAttr().get("offset");
			limit = eiBlock.getAttr().get("limit") == null ? 0 : (Integer) eiBlock.getAttr().get("limit");
		}
		// 获取分页信息
		String page = (String) info.get("page");
		if (StringUtils.isNotEmpty(page)) {
			offset = (Integer.parseInt(page) - 1) * limit;
		}
		String size = (String) info.get("size");
		if (StringUtils.isNotEmpty(size)) {
			limit = Integer.parseInt(size);
		}
		param.put("offset", offset);
		param.put("limit", limit);
		return param;
	}

	// 构建返回对象
	// EiInfo--EiBlock--EiBlockMeta--EiColumn
	//
	public static EiInfo BuildOutEiInfo(EiInfo info, String blockId, EiBlockMeta eiBlockMeta, List list, Integer count) {
		// 处理EiInfo
		EiInfo outInfo = new EiInfo();
		// 如果入参不为空出参为入参
		if (info != null) {
			outInfo = info;
		}
		// 处理EiBlock
		blockId = StringUtils.isNotEmpty(blockId) ? blockId : "result";
		EiBlock eiBlock = outInfo.getBlock(blockId);
		if (eiBlock == null) {
			eiBlock = new EiBlock(blockId);
			if (eiBlockMeta == null) {
				if (CollectionUtils.isEmpty(list)) {
					eiBlockMeta = new EiBlockMeta();
				} else {
					eiBlockMeta = createBlockMeta((Map<String, Object>) list.get(0));
				}
				eiBlock.addBlockMeta(eiBlockMeta);
				eiBlock.setAttr(new HashMap<String, Object>());
				outInfo.addBlock(eiBlock);
			}
		}
		//
		eiBlock.addRows(list);
		// 处理分页
		// count为null是，总数为数据集总数，无分页
		count = count == null ? list.size() : count;
		if (eiBlock.getAttr().isEmpty()) {
			Map<String, Object> attrMap = new HashMap<>();
			attrMap.put("count", count);
			attrMap.put("offset", 0);
			attrMap.put("limit", list.size() < 10 ? 10 : list.size());
			attrMap.put("orderBy", "");
			attrMap.put("showCount", "true");
			eiBlock.setAttr(attrMap);
		} else {
			eiBlock.getAttr().put("count", count);
		}
		return outInfo;
	}

	// 根据Map构建EiBlockMeta
	public static EiBlockMeta createBlockMeta(Map<String, Object> map) {
		// 实例化
		EiBlockMeta eiBlockMeta = new EiBlockMeta();
		if (MapUtils.isNotEmpty(map)) {
			for (String key : map.keySet()) {
				eiBlockMeta.addMeta(new EiColumn(key));
			}
		}
		return eiBlockMeta;
	}

	// 将List<Map<String,Object>>类型的数据进行分割存储
	public static Map<String, Object> listToMap(List<Map<String, Object>> list) {
		HashMap<String, Object> listToMap = new HashMap<>();
		if (CollectionUtils.isNotEmpty(list)) {
			Set<String> set = list.get(0).keySet();
			for (String feild : set) {
				List feildList = new ArrayList();
				for (Map<String, Object> map : list) {
					feildList.add(map.get(feild));
				}
				listToMap.put(feild, feildList);
			}
		}
		return listToMap;
	}

	// listToString
	public static String listToString(List<String> list) {
		String listToString = "";
		for (int i = 0; i < list.size(); i++) {
			listToString += list.get(i) + ";";
		}
		return listToString.substring(0, listToString.length() - 1);
	}

	// listToList
	public static List<Map<String, Object>> listToList(List<Map<String, Object>> list,String[] str) {
		ArrayList arrayList = new ArrayList<>();
		// key
		List<String> stringList = Arrays.asList(str);
		//
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.get(0).get(str[0]).toString().split(";").length; i++) {
				Map<Object, Object> map = new HashMap<>();
				for (int j = 0; j < stringList.size(); j++) {
					map.put(str[j],list.get(0).get(str[j]).toString().split(";")[i]);
				}
				arrayList.add(map);
			}
		}
		return arrayList;
	}

	//

}
