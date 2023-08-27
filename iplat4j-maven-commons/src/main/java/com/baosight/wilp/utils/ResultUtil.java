package com.baosight.wilp.utils;

import com.baosight.iplat4j.core.ei.EiBlock;
import com.baosight.iplat4j.core.ei.EiBlockMeta;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.ei.EiInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultUtil {
	/**
	 * 获取页面传过来的参数
	 * @Title: buildParamter
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:  @param inInfo
	 * @param:  @param inBlock
	 * @param:  @param outBlock
	 * @param:  @return
	 * @return: Map<String,Object>  
	 * @throws
	 */
	public static Map<String,Object> buildParamter(EiInfo inInfo, String inBlock, String outBlock){
		Map<String,Object> param = new HashMap<>();
		//获取参数
		EiBlock block = inInfo.getBlock(inBlock);
		if(block != null && block.getRowCount() > 0){
			param = block.getRow(0);
		}
		//获取分页信息
		EiBlock result = inInfo.getBlock(outBlock);
		Integer offset = 0;Integer limit = 10;
		if(result != null && !result.getAttr().isEmpty()){
			offset = result.getAttr().get("offset") == null ? 0 : (Integer) result.getAttr().get("offset");
			limit = result.getAttr().get("limit") == null ? 0 : (Integer) result.getAttr().get("limit");
		}
		param.put("offset", offset);
		param.put("limit", limit);
		return param;
	}
	
	/**
	 * 返回结果
	 * @param inInfo
	 * @param list
	 * @param count
	 * @param blockId
	 * @return
	 */
	public static EiInfo buildResult(EiInfo inInfo, List list, Integer count, String blockId) {
		EiBlock result = inInfo.getBlock(blockId);
		if(result != null && !result.getAttr().isEmpty()){
			result.setRows(list);
			result.getAttr().put("count", count);
		} else {
			EiBlock resultBlock = new EiBlock(blockId);
			
			EiBlockMeta eiBlockMeta = new EiBlockMeta();
			Map<String, Object> map;
			if (list.size() > 0) {
				map = (Map<String, Object>) list.get(0);
			} else {
				map = new HashMap<>();
			}
			if(!map.isEmpty()){
				for(String key : map.keySet()){
					eiBlockMeta.addMeta(new EiColumn(key));
				}
			}
			
			resultBlock.setBlockMeta(eiBlockMeta);
			resultBlock.addRows(list);
			Map<String,Object> rAttr = new HashMap<>();
			rAttr.put("count", count);
			rAttr.put("offset", 0);
			rAttr.put("limit", 10);
			resultBlock.setAttr(rAttr);
			inInfo.addBlock(resultBlock);
		}
		return inInfo;
	}
}
